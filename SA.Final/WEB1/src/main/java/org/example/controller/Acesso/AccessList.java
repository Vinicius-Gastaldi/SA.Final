package org.example.controller.Acesso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Acesso;
import org.example.service.AcessoService;

import java.io.IOException;

@WebServlet("/access/list") // Endpoint for listing all access
public class AccessList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessoService acessoService = new AcessoService();
        try {
            java.util.List<Acesso> acessos = acessoService.listarAcessos();
            request.setAttribute("acessos", acessos);
            request.getRequestDispatcher("listaAcessos.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroListar.html");
        }
    }
}
