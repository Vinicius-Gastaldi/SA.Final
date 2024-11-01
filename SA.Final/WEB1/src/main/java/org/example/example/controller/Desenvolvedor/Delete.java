package org.example.controller.Desenvolvedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.DesenvolvedorService;

import java.io.IOException;

// Endpoint for deleting developer
@WebServlet("/developer/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DesenvolvedorService desenvolvedorService = new DesenvolvedorService();
        int idDesenvolvedor = Integer.parseInt(request.getParameter("id"));

        try {
            boolean isDeleted = desenvolvedorService.excluirDesenvolvedor(idDesenvolvedor);
            if (isDeleted) {
                response.sendRedirect("sucesso.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroExcluir.html"); // Redirect to error page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroExcluir.html"); // Redirect to error page
        }
    }
}
