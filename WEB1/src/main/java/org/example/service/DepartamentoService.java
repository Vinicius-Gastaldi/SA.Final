package org.example.service;

import org.example.model.Departamento;
import org.example.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoService {
    // Inclusão de Departamento
    public boolean incluirDepartamento(Departamento departamento) throws ClassNotFoundException {
        String sql = "INSERT INTO departamento (descdepto) VALUES (?)"; // SQL corrigido para incluir a coluna
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, departamento.getDescDepto()); // Usando o objeto departamento
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na inclusão do departamento: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Alterar Departamento
    public boolean alterarDepartamento(Departamento departamento) throws ClassNotFoundException {
        String sql = "UPDATE departamento SET descdepto = ? WHERE iddepto = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, departamento.getDescDepto());
            stm.setInt(2, departamento.getIdDepto());
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do departamento: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Excluir Departamento
    public boolean excluirDepartamento(int idDepto) throws ClassNotFoundException {
        String sql = "DELETE FROM departamento WHERE iddepto = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idDepto); // Usando o idDepto passado como parâmetro
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do departamento: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Listar todos os Departamentos
    public List<Departamento> listarDeptos() throws ClassNotFoundException {
        List<Departamento> listaDepto = new ArrayList<>();
        String sql = "SELECT iddepto, descdepto FROM departamento ORDER BY iddepto";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setIdDepto(rs.getInt("iddepto"));
                dep.setDescDepto(rs.getString("descdepto"));
                listaDepto.add(dep);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar departamentos: " + e.getMessage());
            return null;
        }
        return listaDepto;
    }

    // Consultar um Departamento
    public Departamento consultaDepto(int idDepto) throws ClassNotFoundException {
        Departamento dep = null;
        String sql = "SELECT iddepto, descdepto FROM departamento WHERE iddepto = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idDepto); // Usando o idDepto passado como parâmetro
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                dep = new Departamento();
                dep.setIdDepto(rs.getInt("iddepto"));
                dep.setDescDepto(rs.getString("descdepto"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do departamento: " + e.getMessage());
            return null;
        }
        return dep;
    }
}
