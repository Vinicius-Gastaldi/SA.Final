package org.example.service;

import org.example.model.Papel;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PapelService {

    // Incluir Papel
    public boolean incluirPapel(Papel papel) throws ClassNotFoundException {
        String sql = "INSERT INTO papel (nome) VALUES (?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, papel.getNome());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do papel: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Alterar Papel
    public boolean alterarPapel(Papel papel) throws ClassNotFoundException {
        String sql = "UPDATE papel SET nome = ? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, papel.getNome());
            stm.setInt(2, papel.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do papel: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Excluir Papel
    public boolean excluirPapel(int idPapel) throws ClassNotFoundException {
        String sql = "DELETE FROM papel WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idPapel);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do papel: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Listar todos os Papéis
    public List<Papel> listarPapeis() throws ClassNotFoundException {
        List<Papel> listaPapeis = new ArrayList<>();
        String sql = "SELECT id, nome FROM papel ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Papel papel = new Papel();
                papel.setId(rs.getInt("id"));
                papel.setNome(rs.getString("nome"));
                listaPapeis.add(papel);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar papéis: " + e.getMessage());
            return null;
        }
        return listaPapeis;
    }

    // Consultar um Papel
    public Papel consultaPapel(int idPapel) throws ClassNotFoundException {
        Papel papel = null;
        String sql = "SELECT id, nome FROM papel WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idPapel);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                papel = new Papel();
                papel.setId(rs.getInt("id"));
                papel.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do papel: " + e.getMessage());
            return null;
        }
        return papel;
    }
}
