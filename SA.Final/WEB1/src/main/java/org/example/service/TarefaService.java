package org.example.service;

import org.example.model.Tarefa;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TarefaService {

    private static final Logger LOGGER = Logger.getLogger(TarefaService.class.getName());

    public boolean incluirTarefa(Tarefa tarefa) throws ClassNotFoundException {
        String sql = "INSERT INTO tarefa (desenvolvedor_id, tipo_tarefa_id, titulo, descricao, prioridade, status_id, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            configurarParametrosTarefa(stm, tarefa);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro na inclusão da tarefa", e);
            return false;
        }
    }

    public boolean alterarTarefa(Tarefa tarefa) throws ClassNotFoundException {
        String sql = "UPDATE tarefa SET desenvolvedor_id = ?, tipo_tarefa_id = ?, titulo = ?, descricao = ?, prioridade = ?, status_id = ?, data_inicio = ?, data_fim = ? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            configurarParametrosTarefa(stm, tarefa);
            stm.setInt(9, tarefa.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro na alteração da tarefa", e);
            return false;
        }
    }

    public boolean excluirTarefa(int tarefaId) throws ClassNotFoundException {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, tarefaId);
            stm.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro na exclusão da tarefa", e);
            return false;
        }
    }

    public List<Tarefa> listarTarefas() throws ClassNotFoundException {
        return listarTarefasComFiltro(null, -1);
    }

    public List<Tarefa> listarPorTipoTarefa(int tipoTarefaId) throws ClassNotFoundException {
        return listarTarefasComFiltro("tipo_tarefa_id = ?", tipoTarefaId);
    }

    public Tarefa consultaTarefa(int tarefaId) throws ClassNotFoundException {
        String sql = "SELECT * FROM tarefa WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, tarefaId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return criarTarefa(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro na consulta da tarefa", e);
        }
        return null;
    }

    private List<Tarefa> listarTarefasComFiltro(String whereClause, int tipoTarefaId) throws ClassNotFoundException {
        List<Tarefa> listaTarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa";
        if (whereClause != null) {
            sql += " WHERE " + whereClause;
        }
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            if (tipoTarefaId != -1) {
                stm.setInt(1, tipoTarefaId);
            }
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    listaTarefas.add(criarTarefa(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar tarefas", e);
        }
        return listaTarefas;
    }

    private void configurarParametrosTarefa(PreparedStatement stm, Tarefa tarefa) throws SQLException {
        stm.setInt(1, tarefa.getDesenvolvedorId());
        stm.setInt(2, tarefa.getTipoTarefaId());
        stm.setString(3, tarefa.getTitulo());
        stm.setString(4, tarefa.getDescricao());
        stm.setString(5, tarefa.getPrioridade());
        stm.setInt(6, tarefa.getStatusId());
        stm.setDate(7, new java.sql.Date(tarefa.getDataInicio().getTime()));
        stm.setDate(8, new java.sql.Date(tarefa.getDataFim().getTime()));
    }

    private Tarefa criarTarefa(ResultSet rs) throws SQLException {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(rs.getInt("id"));
        tarefa.setDesenvolvedorId(rs.getInt("desenvolvedor_id"));
        tarefa.setTipoTarefaId(rs.getInt("tipo_tarefa_id"));
        tarefa.setTitulo(rs.getString("titulo"));
        tarefa.setDescricao(rs.getString("descricao"));
        tarefa.setPrioridade(rs.getString("prioridade"));
        tarefa.setStatusId(rs.getInt("status_id"));
        tarefa.setDataInicio(rs.getDate("data_inicio"));
        tarefa.setDataFim(rs.getDate("data_fim"));
        return tarefa;
    }
}