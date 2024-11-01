package org.example.controller.Tarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Tarefa;
import org.example.service.TarefaService;

import java.io.IOException;

// Endpoint for consulting a specific task
@WebServlet("/tarefa/consult")
public class Consult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TarefaService tarefaService = new TarefaService();
        int tarefaId = Integer.parseInt(request.getParameter("id"));

        try {
            Tarefa tarefa = tarefaService.consultaTarefa(tarefaId);
            if (tarefa != null) {
                request.setAttribute("tarefa", tarefa);
                request.getRequestDispatcher("consultaTarefa.jsp").forward(request, response);
            } else {
                response.sendRedirect("tarefaNaoEncontrada.html"); // Redirect to not found page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html"); // Redirect to error page
        }
    }
}
