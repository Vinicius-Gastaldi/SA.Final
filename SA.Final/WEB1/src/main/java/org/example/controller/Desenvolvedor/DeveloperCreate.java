package org.example.controller.Desenvolvedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Desenvolvedor;
import org.example.service.DesenvolvedorService;

import java.io.IOException;

// Endpoint for creating developer
@WebServlet("/developer/create")
public class DeveloperCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DesenvolvedorService desenvolvedorService = new DesenvolvedorService();
        String nome = request.getParameter("nome");
        String phone = request.getParameter("phone");
        int squadId = Integer.parseInt(request.getParameter("squadId"));

        Desenvolvedor desenvolvedor = new Desenvolvedor();
        desenvolvedor.setName(nome);
        desenvolvedor.setPhone(phone);
        desenvolvedor.setSquadId(squadId);

        boolean isIncluded = false;
        try {
            isIncluded = desenvolvedorService.incluirDesenvolvedor(desenvolvedor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isIncluded) {
            response.sendRedirect("desenvolvedorIncluido.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroInclusao.html"); // Redirect to error page
        }
    }
}
