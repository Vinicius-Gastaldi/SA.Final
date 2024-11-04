package org.example.controller.Papel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Papel;
import org.example.service.PapelService;

import java.io.IOException;

// Endpoint for creating papel
@WebServlet("/papel/create")
public class PapelCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PapelService papelService = new PapelService();
        String nome = request.getParameter("nome");

        Papel papel = new Papel();
        papel.setNome(nome);

        boolean isIncluded = false;
        try {
            isIncluded = papelService.incluirPapel(papel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isIncluded) {
            response.sendRedirect("papelIncluido.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroInclusao.html"); // Redirect to error page
        }
    }
}
