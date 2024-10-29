package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Papel;
import org.example.service.PapelService;

import java.io.IOException;
import java.util.List;

public class PapelController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        PapelService papelService = new PapelService();

        // Ação para inclusão de Papel
        if ("create".equals(action)) {
            String nome = request.getParameter("nome");

            Papel papel = new Papel();
            papel.setNome(nome);

            boolean isIncluded = false;
            try {
                isIncluded = papelService.incluirPapel(papel);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isIncluded) {
                response.sendRedirect("papelIncluido.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroInclusao.html"); // Redirect to error page
            }
        }

        // Ação para atualização de Papel
        if ("update".equals(action)) {
            String nome = request.getParameter("nome");
            int id = Integer.parseInt(request.getParameter("id"));

            Papel papel = new Papel();
            papel.setId(id);
            papel.setNome(nome);

            boolean isUpdated = false;
            try {
                isUpdated = papelService.alterarPapel(papel);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isUpdated) {
                response.sendRedirect("papelAlterado.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroAlteracao.html"); // Redirect to error page
            }
        }

        // Ação para excluir Papel
        if ("delete".equals(action)) {
            int idPapel = Integer.parseInt(request.getParameter("id"));
            try {
                boolean isDeleted = papelService.excluirPapel(idPapel);
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
        PapelService papelService = new PapelService();
        String action = request.getParameter("action");

        // Ação para listar todos os Papeis
        if ("list".equals(action)) {
            try {
                List<Papel> papeis = papelService.listarPapeis();
                request.setAttribute("papeis", papeis);
                request.getRequestDispatcher("listaPapeis.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        // Ação para consultar um Papel
        if ("consult".equals(action)) {
            int idPapel = Integer.parseInt(request.getParameter("id"));
            try {
                Papel papel = papelService.consultaPapel(idPapel);
                if (papel != null) {
                    request.setAttribute("papel", papel);
                    request.getRequestDispatcher("consultaPapel.jsp").forward(request, response);
                } else {
                    response.sendRedirect("papelNaoEncontrado.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroConsulta.html");
            }
        }
    }
}
