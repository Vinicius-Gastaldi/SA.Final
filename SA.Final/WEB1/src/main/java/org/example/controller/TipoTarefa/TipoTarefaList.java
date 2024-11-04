package org.example.controller.TipoTarefa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.TipoTarefa;
import org.example.service.TipoTarefaService;

import java.io.IOException;

// Endpoint for listing all TipoTarefa
@WebServlet("/tipoTarefa/list")
public class TipoTarefaList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoTarefaService tipoTarefaService = new TipoTarefaService();
        try {
            java.util.List<TipoTarefa> tiposTarefa = tipoTarefaService.listarTiposTarefa();
            request.setAttribute("tiposTarefa", tiposTarefa);
            request.getRequestDispatcher("listaTiposTarefa.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroListar.html"); // Redirect to error page
        }
    }
}
