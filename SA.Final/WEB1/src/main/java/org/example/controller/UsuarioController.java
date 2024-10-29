package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;
import java.util.List;

public class UsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        UsuarioService usuarioService = new UsuarioService();

        // Ação para autenticação
        if ("login".equals(action)) {
            // Get email and password from the request (submitted by form)
            String vEmail = request.getParameter("email");
            String vSenha = request.getParameter("senha");

            // Create Usuario object and set the email and password
            Usuario usuario = new Usuario();
            usuario.setEmail(vEmail);
            usuario.setSenha(vSenha);

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



        // Ação para inclusão
        if ("create".equals(action)) {

            String vEmail = request.getParameter("email");
            String vSenha = request.getParameter("senha");
            int devId = Integer.parseInt(request.getParameter("devId"));
            int papelId = Integer.parseInt(request.getParameter("papelId"));

            Usuario usuario = new Usuario();
            usuario.setEmail(vEmail);
            usuario.setSenha(vSenha);
            usuario.setDevId(devId);
            usuario.setPapelId(papelId);
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

        // Handle user update functionality
        if ("update".equals(action)) {
            String vEmail = request.getParameter("email");
            String vSenha = request.getParameter("senha");
            int devId = Integer.parseInt(request.getParameter("devId"));
            int papelId = Integer.parseInt(request.getParameter("papelId"));
            int id = Integer.parseInt(request.getParameter("id"));

            Usuario usuario = new Usuario();
            usuario.setEmail(vEmail);
            usuario.setSenha(vSenha);
            usuario.setDevId(devId);
            usuario.setPapelId(papelId);
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
        // Ação para excluir usuário
        if ("delete".equals(action)) {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioService usuarioService = new UsuarioService();
        String action = request.getParameter("action");

        // Ação para listar todos os usuários
        if ("list".equals(action)) {
            try {
                List<Usuario> usuarios = usuarioService.listarUsuarios();
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        // Ação para consultar um usuário
        if ("consult".equals(action)) {
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
}
