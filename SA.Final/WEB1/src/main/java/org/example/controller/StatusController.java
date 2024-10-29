package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Status;
import org.example.service.StatusService;

import java.io.IOException;
import java.util.List;

public class StatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        StatusService statusService = new StatusService();

        // Ação para inclusão de Status
        if ("create".equals(action)) {
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

        // Ação para atualização de Status
        if ("update".equals(action)) {
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

        // Ação para excluir Status
        if ("delete".equals(action)) {
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
                response.sendRedirect("erroExcluir.html");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatusService statusService = new StatusService();
        String action = request.getParameter("action");

        // Ação para listar todos os Status
        if ("list".equals(action)) {
            try {
                List<Status> statuses = statusService.listarStatus();
                request.setAttribute("statuses", statuses);
                request.getRequestDispatcher("listaStatus.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        // Ação para consultar um Status
        if ("consult".equals(action)) {
            int idStatus = Integer.parseInt(request.getParameter("id"));
            try {
                Status status = statusService.consultaStatus(idStatus);
                if (status != null) {
                    request.setAttribute("status", status);
                    request.getRequestDispatcher("consultaStatus.jsp").forward(request, response);
                } else {
                    response.sendRedirect("statusNaoEncontrado.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroConsulta.html");
            }
        }
    }
}
