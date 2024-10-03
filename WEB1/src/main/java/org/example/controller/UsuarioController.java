package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vEmail = request.getParameter("email");
        String vSenha = request.getParameter("senha");

        Usuario usu = new Usuario();
        usu.setEmail(vEmail);
        usu.setSenha(vSenha);

        UsuarioService usuarioService = new UsuarioService();

        try {
            if (usuarioService.incluirUsuario(usu)) {
                response.sendRedirect("index.html");
            } else {
                response.sendRedirect("erro.html");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

