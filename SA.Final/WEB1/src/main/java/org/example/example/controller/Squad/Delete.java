package org.example.controller.Squad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.SquadService;

import java.io.IOException;

// Endpoint for deleting a squad
@WebServlet("/squad/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadService squadService = new SquadService();
        int idSquad = Integer.parseInt(request.getParameter("id"));

        try {
            boolean isDeleted = squadService.excluirSquad(idSquad);
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
