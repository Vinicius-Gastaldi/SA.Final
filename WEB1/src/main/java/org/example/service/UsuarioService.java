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
        String sql = "INSERT INTO usuario (email, senha, dev_id, papel) VALUES (?, ?, ?, ?)"; // Incluí a coluna papel
        try (Connection con = Conexao.conectar();
             PreparedStatement stm = con.prepareStatement(sql)) {

            // Usando os valores do objeto 'usuario'
            stm.setString(1, usuario.getEmail());
            stm.setString(2, usuario.getSenha());
            stm.setInt(3, usuario.getDevId());
            stm.setInt(4, usuario.getPapelId());

            stm.executeUpdate(); // use executeUpdate() para INSERT, UPDATE ou DELETE

        } catch (SQLException e) {
            System.out.println("Erro na inclusão do usuário: " + e.getMessage());
            return false;
        }
        return true;
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