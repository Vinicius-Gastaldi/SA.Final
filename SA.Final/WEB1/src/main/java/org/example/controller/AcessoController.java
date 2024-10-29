package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Acesso;
import org.example.service.AcessoService;

import java.io.IOException;
import java.util.List;

public class AcessoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        AcessoService acessoService = new AcessoService();

        // Ação para inclusão de Acesso
        if ("create".equals(action)) {
            String nome = request.getParameter("nome");

            Acesso acesso = new Acesso();
            acesso.setNome(nome);

            boolean isIncluded = false;
            try {
                isIncluded = acessoService.incluirAcesso(acesso);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isIncluded) {
                response.sendRedirect("acessoIncluido.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroInclusao.html"); // Redirect to error page
            }
        }

        // Ação para atualização de Acesso
        if ("update".equals(action)) {
            String nome = request.getParameter("nome");
            int id = Integer.parseInt(request.getParameter("id"));

            Acesso acesso = new Acesso();
            acesso.setId(id);
            acesso.setNome(nome);

            boolean isUpdated = false;
            try {
                isUpdated = acessoService.alterarAcesso(acesso);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isUpdated) {
                response.sendRedirect("acessoAlterado.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroAlteracao.html"); // Redirect to error page
            }
        }

        // Ação para excluir Acesso
        if ("delete".equals(action)) {
            int idAcesso = Integer.parseInt(request.getParameter("id"));
            try {
                boolean isDeleted = acessoService.excluirAcesso(idAcesso);
                if (isDeleted) {
                    response.sendRedirect("sucesso.html"); // Redirect to success page
                } else {
                    response.sendRedirect("erroExcluir.html"); // Redirect to error page
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroExcluir.html");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessoService acessoService = new AcessoService();
        String action = request.getParameter("action");

        // Ação para listar todos os Acessos
        if ("list".equals(action)) {
            try {
                List<Acesso> acessos = acessoService.listarAcessos();
                request.setAttribute("acessos", acessos);
                request.getRequestDispatcher("listaAcessos.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        // Ação para consultar um Acesso
        if ("consult".equals(action)) {
            int idAcesso = Integer.parseInt(request.getParameter("id"));
            try {
                Acesso acesso = acessoService.consultaAcesso(idAcesso);
                if (acesso != null) {
                    request.setAttribute("acesso", acesso);
                    request.getRequestDispatcher("consultaAcesso.jsp").forward(request, response);
                } else {
                    response.sendRedirect("acessoNaoEncontrado.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroConsulta.html");
            }
        }
    }
}
