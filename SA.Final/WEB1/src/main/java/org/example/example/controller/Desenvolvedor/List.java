package org.example.controller.Desenvolvedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Desenvolvedor;
import org.example.service.DesenvolvedorService;

import java.io.IOException;

// Endpoint for listing all developers
@WebServlet("/developer/list")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DesenvolvedorService desenvolvedorService = new DesenvolvedorService();
        try {
            java.util.List<Desenvolvedor> desenvolvedores = desenvolvedorService.listarDesenvolvedores();
            request.setAttribute("desenvolvedores", desenvolvedores);
            request.getRequestDispatcher("listaDesenvolvedores.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroListar.html");
        }
    }
}
