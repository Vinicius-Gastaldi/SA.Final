package org.example.controller.Acesso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Acesso;
import org.example.service.AcessoService;

import java.io.IOException;

@WebServlet("/access/update") // Endpoint for updating access
public class Update extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessoService acessoService = new AcessoService();
        String nome = request.getParameter("nome");
        int id = Integer.parseInt(request.getParameter("id"));

        Acesso acesso = new Acesso();
        acesso.setId(id);
        acesso.setNome(nome);

        boolean isUpdated = false;
        try {
            isUpdated = acessoService.alterarAcesso(acesso);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            response.sendRedirect("acessoAlterado.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAlteracao.html"); // Redirect to error page
        }
    }
}

