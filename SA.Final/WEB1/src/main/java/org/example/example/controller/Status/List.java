package org.example.controller.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Status;
import org.example.service.StatusService;

import java.io.IOException;

// Endpoint for listing all statuses
@WebServlet("/status/list")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatusService statusService = new StatusService();
        try {
            java.util.List<Status> statuses = statusService.listarStatus();
            request.setAttribute("statuses", statuses);
            request.getRequestDispatcher("listaStatus.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroListar.html"); // Redirect to error page
        }
    }
}
