package org.example.controller.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.StatusService;

import java.io.IOException;

// Endpoint for deleting a status
@WebServlet("/status/delete")
public class StatusDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatusService statusService = new StatusService();
        int idStatus = Integer.parseInt(request.getParameter("id"));
        try {
            boolean isDeleted = statusService.excluirStatus(idStatus);
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
