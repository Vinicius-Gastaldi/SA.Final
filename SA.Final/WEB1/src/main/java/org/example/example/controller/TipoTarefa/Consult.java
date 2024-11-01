package org.example.controller.TipoTarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.TipoTarefa;
import org.example.service.TipoTarefaService;

import java.io.IOException;

// Endpoint for consulting a specific TipoTarefa
@WebServlet("/tipoTarefa/consult")
public class Consult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoTarefaService tipoTarefaService = new TipoTarefaService();
        int tipoTarefaId = Integer.parseInt(request.getParameter("id"));

        try {
            TipoTarefa tipoTarefa = tipoTarefaService.consultaTipoTarefa(tipoTarefaId);
            if (tipoTarefa != null) {
                request.setAttribute("tipoTarefa", tipoTarefa);
                request.getRequestDispatcher("consultaTipoTarefa.jsp").forward(request, response);
            } else {
                response.sendRedirect("tipoTarefaNaoEncontrado.html"); // Redirect to not found page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html"); // Redirect to error page
        }
    }
}
