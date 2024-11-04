package org.example.controller.Papel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Papel;
import org.example.service.PapelService;

import java.io.IOException;

// Endpoint for consulting a papel
@WebServlet("/papel/consult")
public class PapelConsult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PapelService papelService = new PapelService();
        int idPapel = Integer.parseInt(request.getParameter("id"));

        try {
            Papel papel = papelService.consultaPapel(idPapel);
            if (papel != null) {
                request.setAttribute("papel", papel);
                request.getRequestDispatcher("consultaPapel.jsp").forward(request, response);
            } else {
                response.sendRedirect("papelNaoEncontrado.html"); // Redirect to not found page
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html"); // Redirect to error page
        }
    }
}
