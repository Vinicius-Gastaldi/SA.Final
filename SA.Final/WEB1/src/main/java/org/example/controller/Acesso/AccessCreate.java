package org.example.controller.Acesso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Acesso;
import org.example.service.AcessoService;

import java.io.IOException;

@WebServlet("/access/create") // Endpoint for creating access
public class AccessCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessoService acessoService = new AcessoService();
        String nome = request.getParameter("nome");

        Acesso acesso = new Acesso();
        acesso.setNome(nome);

        boolean isIncluded = false;
        try {
            isIncluded = acessoService.incluirAcesso(acesso);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isIncluded) {
            response.sendRedirect("acessoIncluido.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroInclusao.html"); // Redirect to error page
        }
    }
}
