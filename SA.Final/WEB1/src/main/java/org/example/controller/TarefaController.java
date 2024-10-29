package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Tarefa;
import org.example.service.TarefaService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TarefaController extends HttpServlet {

    private TarefaService tarefaService = new TarefaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        //Ação para adiconar uma nova tarefa
        if ("create".equals(action)) {
            Tarefa tarefa = extractTarefaFromRequest(request);
            boolean isCreated = false;
            try {
                isCreated = tarefaService.incluirTarefa(tarefa);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isCreated) {
                response.sendRedirect("tarefaCriada.html"); // Redirect to a success page
            } else {
                response.sendRedirect("erroCriacao.html"); // Redirect to an error page
            }
        }

        //Ação para atualizar uma tarefa ja existente
        if ("update".equals(action)) {
            Tarefa tarefa = extractTarefaFromRequest(request);
            tarefa.setId(Integer.parseInt(request.getParameter("id")));

            boolean isUpdated = false;
            try {
                isUpdated = tarefaService.alterarTarefa(tarefa);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isUpdated) {
                response.sendRedirect("tarefaAtualizada.html"); // Redirect to a success page
            } else {
                response.sendRedirect("erroAtualizacao.html"); // Redirect to an error page
            }
        }

        //Ação para deletar uma tarefa
        if ("delete".equals(action)) {
            int tarefaId = Integer.parseInt(request.getParameter("id"));
            try {
                boolean isDeleted = tarefaService.excluirTarefa(tarefaId);
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
        String action = request.getParameter("action");

        //Ação para listar todas as tarefas
        if ("list".equals(action)) {
            try {
                List<Tarefa> tarefas = tarefaService.listarTarefas();
                request.setAttribute("tarefas", tarefas);
                request.getRequestDispatcher("listaTarefas.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        //Ação para consultar uma Tarefa especifica
        if ("consult".equals(action)) {
            int tarefaId = Integer.parseInt(request.getParameter("id"));
            try {
                Tarefa tarefa = tarefaService.consultaTarefa(tarefaId);
                if (tarefa != null) {
                    request.setAttribute("tarefa", tarefa);
                    request.getRequestDispatcher("consultaTarefa.jsp").forward(request, response);
                } else {
                    response.sendRedirect("tarefaNaoEncontrada.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroConsulta.html");
            }
        }
    }

    //Metodo para extrair detalhes de tarefa do request
    private Tarefa extractTarefaFromRequest(HttpServletRequest request) throws ServletException {
        Tarefa tarefa = new Tarefa();
        tarefa.setDesenvolvedorId(Integer.parseInt(request.getParameter("desenvolvedorId")));
        tarefa.setTipoTarefaId(Integer.parseInt(request.getParameter("tipoTarefaId")));
        tarefa.setTitulo(request.getParameter("titulo"));
        tarefa.setDescricao(request.getParameter("descricao"));
        tarefa.setPrioridade(request.getParameter("prioridade"));
        tarefa.setStatusId(Integer.parseInt(request.getParameter("statusId")));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dataInicio = dateFormat.parse(request.getParameter("dataInicio"));
            Date dataFim = dateFormat.parse(request.getParameter("dataFim"));
            tarefa.setDataInicio(dataInicio);
            tarefa.setDataFim(dataFim);
        } catch (ParseException e) {
            throw new ServletException("Invalid date format", e);
        }

        return tarefa;
    }
}
