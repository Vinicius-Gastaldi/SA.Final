package org.example.controller.Acesso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Acesso;
import org.example.service.AcessoService;

import java.io.IOException;

@WebServlet("/access/consult") // Endpoint for consulting access
public class Consult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessoService acessoService = new AcessoService();
        int idAcesso = Integer.parseInt(request.getParameter("id"));

        try {
            Acesso acesso = acessoService.consultaAcesso(idAcesso);
            if (acesso != null) {
                request.setAttribute("acesso", acesso);
                request.getRequestDispatcher("consultaAcesso.jsp").forward(request, response);
            } else {
                response.sendRedirect("acessoNaoEncontrado.html");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html");
        }
    }
}
