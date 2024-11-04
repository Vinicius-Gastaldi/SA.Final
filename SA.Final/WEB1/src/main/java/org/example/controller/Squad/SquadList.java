package org.example.controller.Squad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Squad;
import org.example.service.SquadService;

import java.io.IOException;

// Endpoint for listing all squads
@WebServlet("/squad/list")
public class SquadList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadService squadService = new SquadService();
        try {
            java.util.List<Squad> squads = squadService.listarSquads();
            request.setAttribute("squads", squads);
            request.getRequestDispatcher("listaSquads.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroListar.html"); // Redirect to error page
        }
    }
}
