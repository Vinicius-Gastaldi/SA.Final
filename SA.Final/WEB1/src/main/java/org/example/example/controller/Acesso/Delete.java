package org.example.controller.Acesso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.AcessoService;

import java.io.IOException;

@WebServlet("/access/delete") // Endpoint for deleting access
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessoService acessoService = new AcessoService();
        int idAcesso = Integer.parseInt(request.getParameter("id"));

        try {
            boolean isDeleted = acessoService.excluirAcesso(idAcesso);
            if (isDeleted) {
                response.sendRedirect("sucesso.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroExcluir.html"); // Redirect to error page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroExcluir.html");
        }
    }
}
