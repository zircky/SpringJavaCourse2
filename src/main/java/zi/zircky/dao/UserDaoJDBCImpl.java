package zi.zircky.dao;

import zi.zircky.model.User;
import zi.zircky.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
  public UserDaoJDBCImpl() {}

  @Override
  public void createUsersTable() {
    try (Connection conn = Util.getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void dropUsersTable() {
    try (Connection conn = Util.getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.executeUpdate("DROP TABLE IF EXISTS users");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    try (Connection conn = Util.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, lastName);
      preparedStatement.setByte(3, age);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void removeUserById(long id) {
    try (Connection conn = Util.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
      preparedStatement.setLong(1, id);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    try (Connection conn = Util.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
      while (rs.next()) {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setLastName(rs.getString("lastName"));
        user.setAge(rs.getByte("age"));
        users.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return users;
  }

  @Override
  public void cleanUsersTable() {
    try (Connection conn = Util.getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.executeUpdate("DELETE FROM users");
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
