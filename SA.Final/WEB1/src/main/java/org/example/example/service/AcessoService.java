package org.example.service;

import org.example.model.Acesso;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessoService {

    // Incluir Acesso
    public boolean incluirAcesso(Acesso acesso) throws ClassNotFoundException {
        String sql = "INSERT INTO acesso (nome) VALUES (?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, acesso.getNome());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do acesso: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Alterar Acesso
    public boolean alterarAcesso(Acesso acesso) throws ClassNotFoundException {
        String sql = "UPDATE acesso SET nome = ? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, acesso.getNome());
            stm.setInt(2, acesso.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do acesso: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Excluir Acesso
    public boolean excluirAcesso(int idAcesso) throws ClassNotFoundException {
        String sql = "DELETE FROM acesso WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idAcesso);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do acesso: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Listar todos os Acessos
    public List<Acesso> listarAcessos() throws ClassNotFoundException {
        List<Acesso> listaAcessos = new ArrayList<>();
        String sql = "SELECT id, nome FROM acesso ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Acesso acesso = new Acesso();
                acesso.setId(rs.getInt("id"));
                acesso.setNome(rs.getString("nome"));
                listaAcessos.add(acesso);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar acessos: " + e.getMessage());
            return null;
        }
        return listaAcessos;
    }

    // Consultar um Acesso
    public Acesso consultaAcesso(int idAcesso) throws ClassNotFoundException {
        Acesso acesso = null;
        String sql = "SELECT id, nome FROM acesso WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idAcesso);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                acesso = new Acesso();
                acesso.setId(rs.getInt("id"));
                acesso.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do acesso: " + e.getMessage());
            return null;
        }
        return acesso;
    }
}
