package org.example.controller.Squad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Squad;
import org.example.service.SquadService;

import java.io.IOException;

// Endpoint for consulting a squad
@WebServlet("/squad/consult")
public class SquadConsult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadService squadService = new SquadService();
        int idSquad = Integer.parseInt(request.getParameter("id"));

        try {
            Squad squad = squadService.consultaSquad(idSquad);
            if (squad != null) {
                request.setAttribute("squad", squad);
                request.getRequestDispatcher("consultaSquad.jsp").forward(request, response);
            } else {
                response.sendRedirect("squadNaoEncontrado.html"); // Redirect to not found page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html"); // Redirect to error page
        }
    }
}
