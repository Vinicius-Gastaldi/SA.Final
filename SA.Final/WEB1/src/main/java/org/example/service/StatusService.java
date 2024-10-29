package org.example.service;

import org.example.model.Status;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusService {

    // Incluir Status
    public boolean incluirStatus(Status status) throws ClassNotFoundException {
        String sql = "INSERT INTO status (nome) VALUES (?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, status.getNome());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do status: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Alterar Status
    public boolean alterarStatus(Status status) throws ClassNotFoundException {
        String sql = "UPDATE status SET nome = ? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, status.getNome());
            stm.setInt(2, status.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do status: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Excluir Status
    public boolean excluirStatus(int idStatus) throws ClassNotFoundException {
        String sql = "DELETE FROM status WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idStatus);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do status: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Listar todos os Status
    public List<Status> listarStatus() throws ClassNotFoundException {
        List<Status> listaStatus = new ArrayList<>();
        String sql = "SELECT id, nome FROM status ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Status status = new Status();
                status.setId(rs.getInt("id"));
                status.setNome(rs.getString("nome"));
                listaStatus.add(status);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar status: " + e.getMessage());
            return null;
        }
        return listaStatus;
    }

    // Consultar um Status
    public Status consultaStatus(int idStatus) throws ClassNotFoundException {
        Status status = null;
        String sql = "SELECT id, nome FROM status WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idStatus);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                status = new Status();
                status.setId(rs.getInt("id"));
                status.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do status: " + e.getMessage());
            return null;
        }
        return status;
    }
}
