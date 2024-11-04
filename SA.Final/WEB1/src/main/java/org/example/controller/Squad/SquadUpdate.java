package org.example.controller.Squad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Squad;
import org.example.service.SquadService;

import java.io.IOException;

// Endpoint for updating a squad
@WebServlet("/squad/update")
public class SquadUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadService squadService = new SquadService();
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        int id = Integer.parseInt(request.getParameter("id"));

        Squad squad = new Squad();
        squad.setId(id); // Set the ID for the update
        squad.setNome(nome);
        squad.setDescricao(descricao);

        boolean isUpdated = false;
        try {
            isUpdated = squadService.alterarSquad(squad);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            response.sendRedirect("squadAlterado.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAlteracao.html"); // Redirect to error page
        }
    }
}

