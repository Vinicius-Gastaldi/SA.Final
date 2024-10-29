package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Desenvolvedor;
import org.example.service.DesenvolvedorService;

import java.io.IOException;
import java.util.List;

public class DesenvolvedorController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DesenvolvedorService desenvolvedorService = new DesenvolvedorService();

        // Ação para inclusão
        if ("create".equals(action)) {
            String nome = request.getParameter("nome");
            String phone = request.getParameter("phone");
            int squadId = Integer.parseInt(request.getParameter("squadId"));

            Desenvolvedor desenvolvedor = new Desenvolvedor();
            desenvolvedor.setName(nome);
            desenvolvedor.setPhone(phone);
            desenvolvedor.setSquadId(squadId);

            boolean isIncluded = false;
            try {
                isIncluded = desenvolvedorService.incluirDesenvolvedor(desenvolvedor);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isIncluded) {
                response.sendRedirect("desenvolvedorIncluido.html"); // Redirect to a success page
            } else {
                response.sendRedirect("erroInclusao.html"); // Redirect to an error page
            }
        }

        // Ação para atualização
        if ("update".equals(action)) {
            String nome = request.getParameter("nome");
            String phone = request.getParameter("phone");
            int squadId = Integer.parseInt(request.getParameter("squadId"));
            int id = Integer.parseInt(request.getParameter("id"));

            Desenvolvedor desenvolvedor = new Desenvolvedor();
            desenvolvedor.setName(nome);
            desenvolvedor.setPhone(phone);
            desenvolvedor.setSquadId(squadId);
            desenvolvedor.setId(id); // Set the ID for the update

            boolean isUpdated = false;
            try {
                isUpdated = desenvolvedorService.alterarDesenvolvedor(desenvolvedor);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isUpdated) {
                response.sendRedirect("desenvolvedorAlterado.html"); // Redirect to success page
            } else {
                response.sendRedirect("erroAlteracao.html"); // Redirect to an error page
            }
        }

        // Ação para exclusão
        if ("delete".equals(action)) {
            int idDesenvolvedor = Integer.parseInt(request.getParameter("id"));
            try {
                boolean isDeleted = desenvolvedorService.excluirDesenvolvedor(idDesenvolvedor);
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
        DesenvolvedorService desenvolvedorService = new DesenvolvedorService();
        String action = request.getParameter("action");

        // Ação para listar todos os desenvolvedores
        if ("list".equals(action)) {
            try {
                List<Desenvolvedor> desenvolvedores = desenvolvedorService.listarDesenvolvedores();
                request.setAttribute("desenvolvedores", desenvolvedores);
                request.getRequestDispatcher("listaDesenvolvedores.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        if ("Squadlist".equals(action)) {
            try {
                int squadId = Integer.parseInt(request.getParameter("squadId"));
                List<Desenvolvedor> desenvolvedores = desenvolvedorService.listarDesenvolvedoresPorSquadId(squadId);
                request.setAttribute("desenvolvedores", desenvolvedores);
                request.getRequestDispatcher("listaDesenvolvedores.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroListar.html");
            }
        }

        // Ação para consultar um desenvolvedor
        if ("consult".equals(action)) {
            int idDesenvolvedor = Integer.parseInt(request.getParameter("id"));
            try {
                Desenvolvedor desenvolvedor = desenvolvedorService.consultaDesenvolvedor(idDesenvolvedor);
                if (desenvolvedor != null) {
                    request.setAttribute("desenvolvedor", desenvolvedor);
                    request.getRequestDispatcher("consultaDesenvolvedor.jsp").forward(request, response);
                } else {
                    response.sendRedirect("desenvolvedorNaoEncontrado.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("erroConsulta.html");
            }
        }
    }
}
