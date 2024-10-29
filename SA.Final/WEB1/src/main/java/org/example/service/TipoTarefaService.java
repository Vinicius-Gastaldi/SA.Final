package org.example.service;

import org.example.model.TipoTarefa;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoTarefaService {

    // Inclusão de TipoTarefa
    public boolean incluirTipoTarefa(TipoTarefa tipoTarefa) throws ClassNotFoundException {
        String sql = "INSERT INTO tipo_tarefa (titulo, descricao) VALUES (?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, tipoTarefa.getTitulo());
            stm.setString(2, tipoTarefa.getDescricao());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do tipo de tarefa: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Alterar TipoTarefa
    public boolean alterarTipoTarefa(TipoTarefa tipoTarefa) throws ClassNotFoundException {
        String sql = "UPDATE tipo_tarefa SET titulo = ?, descricao = ? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, tipoTarefa.getTitulo());
            stm.setString(2, tipoTarefa.getDescricao());
            stm.setInt(3, tipoTarefa.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do tipo de tarefa: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Excluir TipoTarefa com verificação de associação
    public boolean excluirTipoTarefa(int tipoTarefaId) throws ClassNotFoundException {

        // Verifica se há tarefas associadas ao TipoTarefa antes de excluir
        if (temTarefasAssociadas(tipoTarefaId)) {
            System.out.println("Não é possível excluir o tipo de tarefa. Existem tarefas associadas a ele.");
            return false;
        }

        String sql = "DELETE FROM tipo_tarefa WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, tipoTarefaId);
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do tipo de tarefa: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Método para verificar se há tarefas associadas ao TipoTarefa
    private boolean temTarefasAssociadas(int tipoTarefaId) throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS total FROM tarefa WHERE tipo_tarefa_id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, tipoTarefaId);
            ResultSet rs = stm.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                return true; // Há tarefas associadas ao TipoTarefa
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar associação de tarefas: " + e.getMessage());
        }
        return false;
    }

    // Listar todos os TipoTarefa
    public List<TipoTarefa> listarTiposTarefa() throws ClassNotFoundException {
        List<TipoTarefa> listaTiposTarefa = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao FROM tipo_tarefa ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                TipoTarefa tipoTarefa = new TipoTarefa();
                tipoTarefa.setId(rs.getInt("id"));
                tipoTarefa.setTitulo(rs.getString("titulo"));
                tipoTarefa.setDescricao(rs.getString("descricao"));
                listaTiposTarefa.add(tipoTarefa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar tipos de tarefa: " + e.getMessage());
            return null;
        }
        return listaTiposTarefa;
    }

    // Consultar um TipoTarefa
    public TipoTarefa consultaTipoTarefa(int tipoTarefaId) throws ClassNotFoundException {
        TipoTarefa tipoTarefa = null;
        String sql = "SELECT id, titulo, descricao FROM tipo_tarefa WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, tipoTarefaId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                tipoTarefa = new TipoTarefa();
                tipoTarefa.setId(rs.getInt("id"));
                tipoTarefa.setTitulo(rs.getString("titulo"));
                tipoTarefa.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do tipo de tarefa: " + e.getMessage());
            return null;
        }
        return tipoTarefa;
    }
}
