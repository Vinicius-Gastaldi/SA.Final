package org.example.controller.Papel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Papel;
import org.example.service.PapelService;

import java.io.IOException;

// Endpoint for listing all papeis
@WebServlet("/papel/list")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PapelService papelService = new PapelService();
        try {
            java.util.List<Papel> papeis = papelService.listarPapeis();
            request.setAttribute("papeis", papeis);
            request.getRequestDispatcher("listaPapeis.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroListar.html");
        }
    }
}
