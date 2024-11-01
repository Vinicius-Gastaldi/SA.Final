package org.example.controller.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;

@WebServlet("/user/create") // Endpoint for create
public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioService usuarioService = new UsuarioService();

        String vEmail = request.getParameter("email");
        String vSenha = request.getParameter("senha");
        int devId = Integer.parseInt(request.getParameter("devId"));
        int papelId = Integer.parseInt(request.getParameter("papelId"));
        int acessoId = Integer.parseInt(request.getParameter("acessoId"));

        Usuario usuario = new Usuario();
        usuario.setEmail(vEmail);
        usuario.setSenha(vSenha);
        usuario.setDevId(devId);
        usuario.setPapelId(papelId);
        usuario.setAcessoId(acessoId);

        boolean isIncluded = false;
        try {
            isIncluded = usuarioService.incluirUsuario(usuario);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isIncluded) {
            response.sendRedirect("usuarioIncluido.html"); // Redirect to a success page
        } else {
            response.sendRedirect("erroInclusao.html"); // Redirect to an error page
        }
    }
}
