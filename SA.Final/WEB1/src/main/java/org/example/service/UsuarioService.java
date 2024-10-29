package org.example.service;

import org.example.model.Usuario;
import org.example.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    // Inclusão de Usuario
    public boolean incluirUsuario(Usuario usuario) throws ClassNotFoundException {
        String sql = "INSERT INTO usuario (email, senha, dev_Id, papel_id, acesso_id) VALUES (?, ?, ?, ?)"; // Incluí a coluna papel
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            // Usando os valores do objeto 'usuario'
            stm.setString(1, usuario.getEmail());
            stm.setString(2, usuario.getSenha());
            stm.setInt(3, usuario.getDevId());
            stm.setInt(4, usuario.getPapelId());
            stm.setInt(5, usuario.getAcessoId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do usuário: " + e.getMessage());
            return false;
        }
        return true;
    }
    public boolean alterarUsuario(Usuario usuario) throws ClassNotFoundException {
        String sql = "UPDATE usuario SET email = ?, senha = ?, dev_id = ?, papel_id = ?,acesso_id=? WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, usuario.getEmail());
            stm.setString(2, usuario.getSenha());
            stm.setInt(3, usuario.getDevId());
            stm.setInt(4, usuario.getPapelId());
            stm.setInt(5, usuario.getAcessoId());
            stm.setInt(6, usuario.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro na alteração do usuário: " + e.getMessage());
            return false;
        }
        return true;
    }
    // Excluir Usuario
    public boolean excluirUsuario(int idUsuario) throws ClassNotFoundException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idUsuario); // Usando o idUsuario passado como parâmetro
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Erro na exclusão do usuário: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Listar todos os Usuarios
    public List<Usuario> listarUsuarios() throws ClassNotFoundException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT id, email, senha, dev_id, papel_id, acesso_id FROM usuario ORDER BY id";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDevId(rs.getInt("dev_id"));
                usuario.setPapelId(rs.getInt("papel_id"));
                usuario.setAcessoId(rs.getInt("acesso_id"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
            return null;
        }
        return listaUsuarios;
    }

    // Consultar um Usuario
    public Usuario consultaUsuario(int idUsuario) throws ClassNotFoundException {
        Usuario usuario = null;
        String sql = "SELECT id, email, senha, dev_id, papel_id FROM usuario WHERE id = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, idUsuario); // Usando o idUsuario passado como parâmetro
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDevId(rs.getInt("dev_id"));
                usuario.setPapelId(rs.getInt("papel_id"));
                usuario.setAcessoId(rs.getInt("acesso_id"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta do usuário: " + e.getMessage());
            return null;
        }
        return usuario;
    }


    public boolean autenticarUsuario(Usuario usuario) throws ClassNotFoundException {
        String sql = "SELECT 1 FROM usuario WHERE email = ? AND senha = ?"; // Query to check if the user exists

        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            // Set the email and password from the passed Usuario object
            stm.setString(1, usuario.getEmail());
            stm.setString(2, usuario.getSenha());

            ResultSet rs = stm.executeQuery();

            // Return true if user exists, false otherwise
            if (rs.next()) {
                System.out.println("Usuário autenticado: " + usuario.getEmail());
                System.out.println("Senha: " + usuario.getSenha());
                return true;
            } else {
                System.out.println("Falha na autenticação: " + usuario.getEmail());
                System.out.println("Senha: " + usuario.getSenha());
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao autenticar o usuário: " + e.getMessage());
            return false;
        }
    }
}