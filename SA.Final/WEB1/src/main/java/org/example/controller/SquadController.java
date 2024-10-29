package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Squad;
import org.example.service.SquadService;

import java.io.IOException;
import java.util.List;

public class SquadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        SquadService squadService = new SquadService();

        // Ação para inclusão
        if ("create".equals(action)) {
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
                response.sendRedirect("squadIncluido.html"); // Redirect to a success page
            } else {
                response.sendRedirect("erroInclusao.html"); // Redirect to an error page
            }
        }

        // Ação para atualização
        if ("update".equals(action)) {
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            int id = Integer.parseInt(request.getParameter("id"));

            Squad squad = new Squad();
            squad.setNome(nome);
            squad.setDescricao(descricao);
            squad.setId(id); // Set the ID for the update

            boolean isUpdated = false;
            try {
                isUpdated = squadService.alterarSquad(squad);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isUpdated) {
                response.sendRedirect("squadAlterado.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroAlteracao.html"); // Redirect to an error page
            }
        }

        // Ação para exclusão
        if ("delete".equals(action)) {
            int idSquad = Integer.parseInt(request.getParameter("id"));
            try {
                boolean isDeleted = squadService.excluirSquad(idSquad);
                if (isDeleted) {
                    response.sendRedirect("sucesso.html");
                } else {
                    response.sendRedirect("erroExcluir.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroExcluir.html");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SquadService squadService = new SquadService();
        String action = request.getParameter("action");

        // Ação para listar todos os squads
        if ("list".equals(action)) {
            try {
                List<Squad> squads = squadService.listarSquads();
                request.setAttribute("squads", squads);
                request.getRequestDispatcher("listaSquads.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        // Ação para consultar um squad
        if ("consult".equals(action)) {
            int idSquad = Integer.parseInt(request.getParameter("id"));
            try {
                Squad squad = squadService.consultaSquad(idSquad);
                if (squad != null) {
                    request.setAttribute("squad", squad);
                    request.getRequestDispatcher("consultaSquad.jsp").forward(request, response);
                } else {
                    response.sendRedirect("squadNaoEncontrado.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroConsulta.html");
            }
        }
    }
}
