package org.example.controller.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Status;
import org.example.service.StatusService;

import java.io.IOException;

// Endpoint for creating a status
@WebServlet("/status/create")
public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatusService statusService = new StatusService();
        String nome = request.getParameter("nome");

        Status status = new Status();
        status.setNome(nome);

        boolean isIncluded = false;
        try {
            isIncluded = statusService.incluirStatus(status);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isIncluded) {
            response.sendRedirect("statusIncluido.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroInclusao.html"); // Redirect to error page
        }
    }
}
