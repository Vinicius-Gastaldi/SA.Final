package org.example.controller.Tarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Tarefa;
import org.example.service.TarefaService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Endpoint for updating a task
@WebServlet("/tarefa/update")
public class TarefaUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TarefaService tarefaService = new TarefaService();
        Tarefa tarefa = new Tarefa();

        // Extracting Tarefa details from the request
        tarefa.setId(Integer.parseInt(request.getParameter("id"))); // Set ID for the update
        tarefa.setDesenvolvedorId(Integer.parseInt(request.getParameter("desenvolvedorId")));
        tarefa.setTipoTarefaId(Integer.parseInt(request.getParameter("tipoTarefaId")));
        tarefa.setTitulo(request.getParameter("titulo"));
        tarefa.setDescricao(request.getParameter("descricao"));
        tarefa.setPrioridade(request.getParameter("prioridade"));
        tarefa.setStatusId(Integer.parseInt(request.getParameter("statusId")));

        // Parsing the date fields
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dataInicio = dateFormat.parse(request.getParameter("dataInicio"));
            Date dataFim = dateFormat.parse(request.getParameter("dataFim"));
            tarefa.setDataInicio(dataInicio);
            tarefa.setDataFim(dataFim);
        } catch (ParseException e) {
            throw new ServletException("Invalid date format", e);
        }

        boolean isUpdated;
        try {
            isUpdated = tarefaService.alterarTarefa(tarefa);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroAtualizacao.html");
            return;
        }

        if (isUpdated) {
            response.sendRedirect("tarefaAtualizada.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAtualizacao.html"); // Redirect to error page
        }
    }
}

