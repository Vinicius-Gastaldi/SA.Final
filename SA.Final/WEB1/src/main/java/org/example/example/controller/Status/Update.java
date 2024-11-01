package org.example.controller.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Status;
import org.example.service.StatusService;

import java.io.IOException;

// Endpoint for updating a status
@WebServlet("/status/update")
public class Update extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatusService statusService = new StatusService();
        String nome = request.getParameter("nome");
        int id = Integer.parseInt(request.getParameter("id"));

        Status status = new Status();
        status.setId(id);
        status.setNome(nome);

        boolean isUpdated = false;
        try {
            isUpdated = statusService.alterarStatus(status);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            response.sendRedirect("statusAlterado.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAlteracao.html"); // Redirect to error page
        }
    }
}

