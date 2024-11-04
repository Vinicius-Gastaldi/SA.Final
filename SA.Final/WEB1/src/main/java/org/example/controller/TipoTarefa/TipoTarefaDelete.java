package org.example.controller.TipoTarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.TipoTarefaService;

import java.io.IOException;

// Endpoint for deleting a TipoTarefa
@WebServlet("/tipoTarefa/delete")
public class TipoTarefaDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoTarefaService tipoTarefaService = new TipoTarefaService();
        int tipoTarefaId = Integer.parseInt(request.getParameter("id"));

        boolean isDeleted;
        try {
            isDeleted = tipoTarefaService.excluirTipoTarefa(tipoTarefaId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroExcluir.html");
            return;
        }

        if (isDeleted) {
            response.sendRedirect("tipoTarefaExcluido.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroExcluir.html"); // Redirect to error page
        }
    }
}
