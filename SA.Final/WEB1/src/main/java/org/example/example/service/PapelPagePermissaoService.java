package org.example.service;

import org.example.model.PapelPagePermissao;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PapelPagePermissaoService {

    public boolean addPermission(PapelPagePermissao permission) {
        String sql = "INSERT INTO papel_page_permissao (papel_id, page_id) VALUES (?, ?)";
        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, permission.getPapelId());
            statement.setInt(2, permission.getPageId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePermission(PapelPagePermissao permission) {
        String sql = "UPDATE papel_page_permissao SET papel_id = ?, page_id = ? WHERE id = ?";
        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, permission.getPapelId());
            statement.setInt(2, permission.getPageId());
            statement.setInt(3, permission.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePermission(int id) {
        String sql = "DELETE FROM papel_page_permissao WHERE id = ?";
        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PapelPagePermissao> getPermissionsForRole(int papelId) {
        String sql = "SELECT * FROM papel_page_permissao WHERE papel_id = ?";
        List<PapelPagePermissao> permissions = new ArrayList<>();
        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, papelId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PapelPagePermissao permission = new PapelPagePermissao();
                permission.setId(resultSet.getInt("id"));
                permission.setPapelId(resultSet.getInt("papel_id"));
                permission.setPageId(resultSet.getInt("page_id"));
                permissions.add(permission);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    public List<PapelPagePermissao> getPermissionsForPage(int pageId) {
        String sql = "SELECT * FROM papel_page_permissao WHERE page_id = ?";
        List<PapelPagePermissao> permissions = new ArrayList<>();
        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pageId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PapelPagePermissao permission = new PapelPagePermissao();
                permission.setId(resultSet.getInt("id"));
                permission.setPapelId(resultSet.getInt("papel_id"));
                permission.setPageId(resultSet.getInt("page_id"));
                permissions.add(permission);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    public List<PapelPagePermissao> getAllPermissions() {
        String sql = "SELECT * FROM papel_page_permissao";
        List<PapelPagePermissao> permissions = new ArrayList<>();
        try (Connection connection = Conexao.conectar();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                PapelPagePermissao permission = new PapelPagePermissao();
                permission.setId(resultSet.getInt("id"));
                permission.setPapelId(resultSet.getInt("papel_id"));
                permission.setPageId(resultSet.getInt("page_id"));
                permissions.add(permission);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    public PapelPagePermissao getPermissionById(int id) {
        String sql = "SELECT * FROM papel_page_permissao WHERE id = ?";
        try (Connection connection = Conexao.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PapelPagePermissao permission = new PapelPagePermissao();
                permission.setId(resultSet.getInt("id"));
                permission.setPapelId(resultSet.getInt("papel_id"));
                permission.setPageId(resultSet.getInt("page_id"));
                return permission;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
