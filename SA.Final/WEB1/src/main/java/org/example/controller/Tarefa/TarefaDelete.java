package org.example.controller.Tarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.TarefaService;

import java.io.IOException;

// Endpoint for deleting a task
@WebServlet("/tarefa/delete")
public class TarefaDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TarefaService tarefaService = new TarefaService();
        int tarefaId = Integer.parseInt(request.getParameter("id"));

        boolean isDeleted;
        try {
            isDeleted = tarefaService.excluirTarefa(tarefaId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroExcluir.html");
            return;
        }

        if (isDeleted) {
            response.sendRedirect("sucesso.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroExcluir.html"); // Redirect to error page
        }
    }
}
