package org.example.controller.Desenvolvedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Desenvolvedor;
import org.example.service.DesenvolvedorService;

import java.io.IOException;

// Endpoint for updating developer
@WebServlet("/developer/update")
public class Update extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DesenvolvedorService desenvolvedorService = new DesenvolvedorService();
        String nome = request.getParameter("nome");
        String phone = request.getParameter("phone");
        int squadId = Integer.parseInt(request.getParameter("squadId"));
        int id = Integer.parseInt(request.getParameter("id"));

        Desenvolvedor desenvolvedor = new Desenvolvedor();
        desenvolvedor.setId(id); // Set the ID for the update
        desenvolvedor.setName(nome);
        desenvolvedor.setPhone(phone);
        desenvolvedor.setSquadId(squadId);

        boolean isUpdated = false;
        try {
            isUpdated = desenvolvedorService.alterarDesenvolvedor(desenvolvedor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            response.sendRedirect("desenvolvedorAlterado.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAlteracao.html"); // Redirect to error page
        }
    }
}

