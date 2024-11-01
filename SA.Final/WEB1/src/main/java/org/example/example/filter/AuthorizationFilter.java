package org.example.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.utils.Conexao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        Integer userRoleId = (Integer) (session != null ? session.getAttribute("papel") : null);
        String requestURI = httpRequest.getRequestURI();

        if (userRoleId == null || !isAuthorized(userRoleId, requestURI)) {
            // Redirect to a forbidden page if the user is unauthorized
            httpResponse.sendRedirect("/WEB1_war/forbidden.html"); // Specify the correct path
            return;
        }

        // Continue if the user is authorized
        chain.doFilter(request, response);
    }

    private boolean isAuthorized(int papelId, String pageUrl) {
        System.out.println("PapelId do usuario: " + papelId + " que esta tentando acessar o PageUrl: " + pageUrl);

        String sql = """
            SELECT 1 FROM papel_page_permissao rpa
            JOIN page p ON rpa.page_id = p.id
            WHERE rpa.papel_id = ? AND p.page_url = ?
        """;

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, papelId);
            stmt.setString(2, pageUrl);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if an authorized record is found
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error checking authorization for PapelId: " + papelId + " and PageUrl: " + pageUrl);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
