package org.example.controller.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Status;
import org.example.service.StatusService;

import java.io.IOException;

// Endpoint for consulting a status
@WebServlet("/status/consult")
public class Consult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatusService statusService = new StatusService();
        int idStatus = Integer.parseInt(request.getParameter("id"));
        try {
            Status status = statusService.consultaStatus(idStatus);
            if (status != null) {
                request.setAttribute("status", status);
                request.getRequestDispatcher("consultaStatus.jsp").forward(request, response);
            } else {
                response.sendRedirect("statusNaoEncontrado.html"); // Redirect to not found page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html"); // Redirect to error page
        }
    }
}
