package org.example.controller.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;

@WebServlet("/user/update") // Endpoint for update
public class Update extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioService usuarioService = new UsuarioService();

        String vEmail = request.getParameter("email");
        String vSenha = request.getParameter("senha");
        int devId = Integer.parseInt(request.getParameter("devId"));
        int papelId = Integer.parseInt(request.getParameter("papelId"));
        int acessoId = Integer.parseInt(request.getParameter("acessoId"));
        int id = Integer.parseInt(request.getParameter("id"));


        Usuario usuario = new Usuario();
        usuario.setEmail(vEmail);
        usuario.setSenha(vSenha);
        usuario.setDevId(devId);
        usuario.setPapelId(papelId);
        usuario.setAcessoId(acessoId);
        usuario.setId(id); // Ensure the ID is set for the update

        boolean isUpdated = false;
        try {
            isUpdated = usuarioService.alterarUsuario(usuario);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isUpdated) {
            response.sendRedirect("usuarioAlterado.html"); // Redirect to success page
        } else {
            response.sendRedirect("erroAlteracao.html"); // Redirect to error page
        }
    }
}

