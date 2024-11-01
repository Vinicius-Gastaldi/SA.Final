package org.example.controller.Papel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Papel;
import org.example.service.PapelService;

import java.io.IOException;

// Endpoint for updating papel
@WebServlet("/papel/update")
public class Update extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PapelService papelService = new PapelService();
        String nome = request.getParameter("nome");
        int id = Integer.parseInt(request.getParameter("id"));

        Papel papel = new Papel();
        papel.setId(id); // Set the ID for the update
        papel.setNome(nome);

        boolean isUpdated = false;
        try {
            isUpdated = papelService.alterarPapel(papel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            response.sendRedirect("papelAlterado.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAlteracao.html"); // Redirect to error page
        }
    }
}

