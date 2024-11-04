package org.example.controller.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;

@WebServlet("/user/consult") // Endpoint for consult
public class UsuarioConsult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioService usuarioService = new UsuarioService();
        int idUsuario = Integer.parseInt(request.getParameter("id"));

        try {
            Usuario usuario = usuarioService.consultaUsuario(idUsuario);
            if (usuario != null) {
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("consultaUsuario.jsp").forward(request, response);
            } else {
                response.sendRedirect("usuarioNaoEncontrado.html");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroConsulta.html");
        }
    }
}
