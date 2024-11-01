package org.example.controller.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;

@WebServlet("/user/list") // Endpoint for list
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioService usuarioService = new UsuarioService();
        try {
            java.util.List<Usuario> usuarios = usuarioService.listarUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroListar.html");
        }
    }
}
