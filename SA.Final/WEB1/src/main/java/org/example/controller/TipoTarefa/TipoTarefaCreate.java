package org.example.controller.TipoTarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.TipoTarefa;
import org.example.service.TipoTarefaService;

import java.io.IOException;

// Endpoint for creating a TipoTarefa
@WebServlet("/tipoTarefa/create")
public class TipoTarefaCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoTarefaService tipoTarefaService = new TipoTarefaService();
        TipoTarefa tipoTarefa = new TipoTarefa();

        // Extracting TipoTarefa details from the request
        tipoTarefa.setTitulo(request.getParameter("titulo"));
        tipoTarefa.setDescricao(request.getParameter("descricao"));

        boolean isIncluded;
        try {
            isIncluded = tipoTarefaService.incluirTipoTarefa(tipoTarefa);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroInclusao.html");
            return;
        }

        if (isIncluded) {
            response.sendRedirect("tipoTarefaIncluido.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroInclusao.html"); // Redirect to error page
        }
    }
}
