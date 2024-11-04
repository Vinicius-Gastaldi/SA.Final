package org.example.controller.Desenvolvedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Desenvolvedor;
import org.example.service.DesenvolvedorService;

import java.io.IOException;

// Endpoint for consulting a developer
@WebServlet("/developer/consult")
public class DeveloperConsult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DesenvolvedorService desenvolvedorService = new DesenvolvedorService();
        int idDesenvolvedor = Integer.parseInt(request.getParameter("id"));

        try {
            Desenvolvedor desenvolvedor = desenvolvedorService.consultaDesenvolvedor(idDesenvolvedor);
            if (desenvolvedor != null) {
                request.setAttribute("desenvolvedor", desenvolvedor);
                request.getRequestDispatcher("consultaDesenvolvedor.jsp").forward(request, response);
            } else {
                response.sendRedirect("desenvolvedorNaoEncontrado.html"); // Redirect to not found page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html"); // Redirect to error page
        }
    }
}
