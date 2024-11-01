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

// Endpoint for creating a task
@WebServlet("/tarefa/create")
public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TarefaService tarefaService = new TarefaService();
        Tarefa tarefa = new Tarefa();

        // Extracting Tarefa details from the request
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

        boolean isCreated;
        try {
            isCreated = tarefaService.incluirTarefa(tarefa);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroCriacao.html");
            return;
        }

        if (isCreated) {
            response.sendRedirect("tarefaCriada.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroCriacao.html"); // Redirect to error page
        }
    }
}
