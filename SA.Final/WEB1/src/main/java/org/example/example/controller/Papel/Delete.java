package org.example.controller.Papel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.PapelService;

import java.io.IOException;

// Endpoint for deleting papel
@WebServlet("/papel/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PapelService papelService = new PapelService();
        int idPapel = Integer.parseInt(request.getParameter("id"));

        try {
            boolean isDeleted = papelService.excluirPapel(idPapel);
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
