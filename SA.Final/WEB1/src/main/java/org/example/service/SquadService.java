package org.example.service;

import org.example.model.Squad;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SquadService {

    // Inclusão de Squad
    public boolean incluirSquad(Squad squad) throws ClassNotFoundException {
        String sql = "INSERT INTO squad (nome, descricao) VALUES (?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            // Usando os valores do objeto 'squad'
            stm.setString(1, squad.getNome());
            stm.setString(2, squad.getDescricao());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do squad: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Alterar Squad
    public boolean alterarSquad(Squad squad) throws ClassNotFoundException {
        String sql = "UPDATE squad SET nome = ?, descricao = ? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, squad.getNome());
            stm.setString(2, squad.getDescricao());
            stm.setInt(3, squad.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do squad: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Excluir Squad
    public boolean excluirSquad(int idSquad) throws ClassNotFoundException {
        String sql = "DELETE FROM squad WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idSquad);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do squad: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Listar todos os Squads
    public List<Squad> listarSquads() throws ClassNotFoundException {
        List<Squad> listaSquads = new ArrayList<>();
        String sql = "SELECT id, nome, descricao FROM squad ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Squad squad = new Squad();
                squad.setId(rs.getInt("id"));
                squad.setNome(rs.getString("nome"));
                squad.setDescricao(rs.getString("descricao"));
                listaSquads.add(squad);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar squads: " + e.getMessage());
            return null;
        }
        return listaSquads;
    }

    // Consultar um Squad
    public Squad consultaSquad(int idSquad) throws ClassNotFoundException {
        Squad squad = null;
        String sql = "SELECT id, nome, descricao FROM squad WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idSquad);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                squad = new Squad();
                squad.setId(rs.getInt("id"));
                squad.setNome(rs.getString("nome"));
                squad.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do squad: " + e.getMessage());
            return null;
        }
        return squad;
    }
}
