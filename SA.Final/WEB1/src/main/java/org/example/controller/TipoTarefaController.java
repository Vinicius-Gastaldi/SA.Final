package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.TipoTarefa;
import org.example.service.TipoTarefaService;

import java.io.IOException;
import java.util.List;

public class TipoTarefaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        TipoTarefaService tipoTarefaService = new TipoTarefaService();

        // Ação para incluir TipoTarefa
        if ("create".equals(action)) {
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");

            TipoTarefa tipoTarefa = new TipoTarefa();
            tipoTarefa.setTitulo(titulo);
            tipoTarefa.setDescricao(descricao);

            boolean isIncluded = false;
            try {
                isIncluded = tipoTarefaService.incluirTipoTarefa(tipoTarefa);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isIncluded) {
                response.sendRedirect("tipoTarefaIncluido.html");
            } else {
                response.sendRedirect("erroInclusao.html");
            }
        }

        // Ação para alterar TipoTarefa
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");

            TipoTarefa tipoTarefa = new TipoTarefa();
            tipoTarefa.setId(id);
            tipoTarefa.setTitulo(titulo);
            tipoTarefa.setDescricao(descricao);

            boolean isUpdated = false;
            try {
                isUpdated = tipoTarefaService.alterarTipoTarefa(tipoTarefa);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isUpdated) {
                response.sendRedirect("tipoTarefaAlterado.html");
            } else {
                response.sendRedirect("erroAlteracao.html");
            }
        }

        // Ação para excluir TipoTarefa
        if ("delete".equals(action)) {
            int idTipoTarefa = Integer.parseInt(request.getParameter("id"));
            try {
                boolean isDeleted = tipoTarefaService.excluirTipoTarefa(idTipoTarefa);
                if (isDeleted) {
                    response.sendRedirect("tipoTarefaExcluido.html");
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
        TipoTarefaService tipoTarefaService = new TipoTarefaService();
        String action = request.getParameter("action");

        // Ação para listar todos os TipoTarefa
        if ("list".equals(action)) {
            try {
                List<TipoTarefa> tiposTarefa = tipoTarefaService.listarTiposTarefa();
                request.setAttribute("tiposTarefa", tiposTarefa);
                request.getRequestDispatcher("listaTiposTarefa.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        // Ação para consultar um TipoTarefa
        if ("consult".equals(action)) {
            int idTipoTarefa = Integer.parseInt(request.getParameter("id"));
            try {
                TipoTarefa tipoTarefa = tipoTarefaService.consultaTipoTarefa(idTipoTarefa);
                if (tipoTarefa != null) {
                    request.setAttribute("tipoTarefa", tipoTarefa);
                    request.getRequestDispatcher("consultaTipoTarefa.jsp").forward(request, response);
                } else {
                    response.sendRedirect("tipoTarefaNaoEncontrado.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroConsulta.html");
            }
        }
    }
}
