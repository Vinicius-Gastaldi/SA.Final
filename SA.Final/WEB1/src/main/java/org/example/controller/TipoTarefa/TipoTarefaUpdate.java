package org.example.controller.TipoTarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.TipoTarefa;
import org.example.service.TipoTarefaService;

import java.io.IOException;

// Endpoint for updating a TipoTarefa
@WebServlet("/tipoTarefa/update")
public class TipoTarefaUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoTarefaService tipoTarefaService = new TipoTarefaService();
        TipoTarefa tipoTarefa = new TipoTarefa();

        // Extracting TipoTarefa details from the request
        tipoTarefa.setId(Integer.parseInt(request.getParameter("id"))); // Set ID for the update
        tipoTarefa.setTitulo(request.getParameter("titulo"));
        tipoTarefa.setDescricao(request.getParameter("descricao"));

        boolean isUpdated;
        try {
            isUpdated = tipoTarefaService.alterarTipoTarefa(tipoTarefa);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroAlteracao.html");
            return;
        }

        if (isUpdated) {
            response.sendRedirect("tipoTarefaAlterado.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAlteracao.html"); // Redirect to error page
        }
    }
}

