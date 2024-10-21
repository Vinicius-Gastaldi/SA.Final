package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;

public class UsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get email and password from the request (submitted by form)
        String vEmail = request.getParameter("email");
        String vSenha = request.getParameter("senha");

        // Create Usuario object and set the email and password
        Usuario usuario = new Usuario();
        usuario.setEmail(vEmail);
        usuario.setSenha(vSenha);

        // Call the service class to authenticate the user
        UsuarioService usuarioService = new UsuarioService();
        boolean isAuthenticated = false;
        try {
            isAuthenticated = usuarioService.autenticarUsuario(usuario);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Check if user is authenticated
        if (isAuthenticated) {
            // Create a session and store the authenticated user's email
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario.getId());
            session.setAttribute("papel", usuario.getPapelId());

            // Redirect to the main menu page
            response.sendRedirect("home.html");
        } else {
            // Redirect to the error page if authentication fails
            response.sendRedirect("erroLogin.html");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

    }
}
