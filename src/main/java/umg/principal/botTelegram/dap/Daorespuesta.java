package umg.principal.botTelegram.dap;

import umg.principal.botTelegram.db.DatabaseConnection;
import umg.principal.botTelegram.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Daorespuesta {
    public void updateUserrespuesta(User user) throws SQLException {
        String query = "UPDATE tb_respuesta SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setString(6, user.getActivo());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        }
    }
    public void deleteUserByEmailrespuesta(String email) throws SQLException {
        String query = "DELETE FROM  tb_respuesta WHERE correo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }
    public void insertUserrespuesta(User user) throws SQLException {
        String query = "INSERT INTO tb_respuesta (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setString(6, user.getActivo());
            statement.executeUpdate();
        }
    }
    public User getUserByTelegramIdrespuesta(long telegramid) throws SQLException {
        String query = "SELECT * FROM tb_respuesta WHERE telegramid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, telegramid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("idusuario"));
                user.setCarne(resultSet.getString("carne"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getString("activo"));
                return user;
            }
        }
        return null;
    }
}
