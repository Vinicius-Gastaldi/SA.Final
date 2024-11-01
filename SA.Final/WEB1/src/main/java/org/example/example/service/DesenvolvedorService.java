package org.example.service;

import org.example.model.Desenvolvedor;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesenvolvedorService {

    // Inclusão de Desenvolvedor
    public boolean incluirDesenvolvedor(Desenvolvedor desenvolvedor) throws ClassNotFoundException {
        String sql = "INSERT INTO desenvolvedor (nome, phone, squadid) VALUES (?, ?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            // Usando os valores do objeto 'desenvolvedor'
            stm.setString(1, desenvolvedor.getName());
            stm.setString(2, desenvolvedor.getPhone());
            stm.setInt(3, desenvolvedor.getSquadId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do desenvolvedor: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Alterar Desenvolvedor
    public boolean alterarDesenvolvedor(Desenvolvedor desenvolvedor) throws ClassNotFoundException {
        String sql = "UPDATE desenvolvedor SET nome = ?, phone = ?, squadid = ? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, desenvolvedor.getName());
            stm.setString(2, desenvolvedor.getPhone());
            stm.setInt(3, desenvolvedor.getSquadId());
            stm.setInt(4, desenvolvedor.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do desenvolvedor: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Excluir Desenvolvedor
    public boolean excluirDesenvolvedor(int idDesenvolvedor) throws ClassNotFoundException {
        if (temTarefasAssociadas(idDesenvolvedor)) {
            System.out.println("Erro: O desenvolvedor não pode ser excluído porque tem tarefas associadas.");
            return false; // Não pode excluir, pois há tarefas associadas
        }

        String sql = "DELETE FROM desenvolvedor WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idDesenvolvedor);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do desenvolvedor: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Verifica se o desenvolvedor tem tarefas associadas
    private boolean temTarefasAssociadas(int idDesenvolvedor) throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM tarefa WHERE desenvolvedor_id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idDesenvolvedor);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se houver tarefas associadas
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar tarefas associadas: " + e.getMessage());
        }
        return false;
    }

    // Listar todos os Desenvolvedores
    public List<Desenvolvedor> listarDesenvolvedores() throws ClassNotFoundException {
        List<Desenvolvedor> listaDesenvolvedores = new ArrayList<>();
        String sql = "SELECT id, nome, phone, squadid FROM desenvolvedor ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Desenvolvedor desenvolvedor = new Desenvolvedor();
                desenvolvedor.setId(rs.getInt("id"));
                desenvolvedor.setName(rs.getString("nome"));
                desenvolvedor.setPhone(rs.getString("phone"));
                desenvolvedor.setSquadId(rs.getInt("squadid"));
                listaDesenvolvedores.add(desenvolvedor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar desenvolvedores: " + e.getMessage());
            return null;
        }
        return listaDesenvolvedores;
    }

    // Listar Desenvolvedores por Squad ID
    public List<Desenvolvedor> listarDesenvolvedoresPorSquadId(int squadId) throws ClassNotFoundException {
        List<Desenvolvedor> listaDesenvolvedores = new ArrayList<>();
        String sql = "SELECT id, nome, phone, squadid FROM desenvolvedor WHERE squadid = ? ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, squadId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Desenvolvedor desenvolvedor = new Desenvolvedor();
                desenvolvedor.setId(rs.getInt("id"));
                desenvolvedor.setName(rs.getString("nome"));
                desenvolvedor.setPhone(rs.getString("phone"));
                desenvolvedor.setSquadId(rs.getInt("squadid"));
                listaDesenvolvedores.add(desenvolvedor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar desenvolvedores por Squad ID: " + e.getMessage());
            return null;
        }
        return listaDesenvolvedores;
    }

    // Consultar um Desenvolvedor
    public Desenvolvedor consultaDesenvolvedor(int idDesenvolvedor) throws ClassNotFoundException {
        Desenvolvedor desenvolvedor = null;
        String sql = "SELECT id, nome, phone, squadid FROM desenvolvedor WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idDesenvolvedor);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                desenvolvedor = new Desenvolvedor();
                desenvolvedor.setId(rs.getInt("id"));
                desenvolvedor.setName(rs.getString("nome"));
                desenvolvedor.setPhone(rs.getString("phone"));
                desenvolvedor.setSquadId(rs.getInt("squadid"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do desenvolvedor: " + e.getMessage());
            return null;
        }
        return desenvolvedor;
    }
}
