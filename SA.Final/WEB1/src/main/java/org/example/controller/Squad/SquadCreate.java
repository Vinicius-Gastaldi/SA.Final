package org.example.controller.Squad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Squad;
import org.example.service.SquadService;

import java.io.IOException;

// Endpoint for creating a squad
@WebServlet("/squad/create")
public class SquadCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadService squadService = new SquadService();
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Squad squad = new Squad();
        squad.setNome(nome);
        squad.setDescricao(descricao);

        boolean isIncluded = false;
        try {
            isIncluded = squadService.incluirSquad(squad);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isIncluded) {
            response.sendRedirect("squadIncluido.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroInclusao.html"); // Redirect to error page
        }
    }
}
