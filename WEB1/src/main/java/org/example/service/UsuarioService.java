package org.example.service;

import org.example.model.Usuario;
import org.example.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioService {

    // Inclusão de Usuario
    public boolean incluirUsuario(Usuario usuario) throws ClassNotFoundException {
        String sql = "INSERT INTO usuario (email, senha, dev_id) VALUES (?, ?, ?)"; // Incluí as colunas
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            // Usando os valores do objeto 'usuario'
            stm.setString(1, usuario.getEmail());
            stm.setString(2, usuario.getSenha());
            stm.setInt(3, usuario.getDevId());
            stm.execute();

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do usuário: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Autenticação do usuário
    public Usuario autenticarUsuario(Usuario usuario) throws ClassNotFoundException {
        String sql = "SELECT email FROM usuario WHERE email = ? AND senha = ?";
        Usuario usu = null;

        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            // Usando os valores do objeto 'usuario'
            stm.setString(1, usuario.getEmail());
            stm.setString(2, usuario.getSenha());
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                usu = new Usuario();
                usu.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Erro na autenticação do usuário: " + e.getMessage());
            return null;
        }
        return usu;
    }
}
