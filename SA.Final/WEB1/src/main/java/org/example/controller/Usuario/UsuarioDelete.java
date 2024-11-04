package org.example.controller.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.UsuarioService;

import java.io.IOException;

@WebServlet("/user/delete") // Endpoint for delete
public class UsuarioDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioService usuarioService = new UsuarioService();
        int idUsuario = Integer.parseInt(request.getParameter("id"));

        try {
            boolean isDeleted = usuarioService.excluirUsuario(idUsuario);
            if (isDeleted) {
                response.sendRedirect("sucesso.html");
            } else {
                response.sendRedirect("erroExcluir.html");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("erroExcluir.html");
        }
    }
}
