package org.example.controller.Tarefa;

import org.example.model.Tarefa;
import org.example.service.TarefaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/tarefa/list")
public class Lists extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Lists.class.getName());
    private final TarefaService tarefaService = new TarefaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get filter parameters from request
            String tipoTarefaId = request.getParameter("tipoTarefaId");
            String statusId = request.getParameter("statusId");
            String desenvolvedorId = request.getParameter("desenvolvedorId");
            Date dataInicio = request.getParameter("dataInicio") != null ? Date.valueOf(request.getParameter("dataInicio")) : null;
            Date dataFim = request.getParameter("dataFim") != null ? Date.valueOf(request.getParameter("dataFim")) : null;

            List<Tarefa> tarefas;

            // Check if any filter parameter is provided
            if (tipoTarefaId != null || statusId != null || desenvolvedorId != null || dataInicio != null || dataFim != null) {
                // Call listarTarefasComFiltro if there are filter parameters
                tarefas = tarefaService.listarTarefas(tipoTarefaId, statusId, dataInicio, dataFim, desenvolvedorId);
            } else {
                // Call listarTarefas without filters
                tarefas = tarefaService.listarTarefas(null, null, null, null, null);
            }

            // Set tarefas as a request attribute and forward to JSP
            request.setAttribute("tarefas", tarefas);
            request.getRequestDispatcher("/WEB-INF/views/tarefaList.jsp").forward(request, response);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar tarefas", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar tarefas");
        }
    }
}
