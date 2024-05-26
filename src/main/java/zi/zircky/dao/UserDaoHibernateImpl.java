package zi.zircky.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zi.zircky.model.User;
import zi.zircky.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
  public UserDaoHibernateImpl() {
  }

  @Override
  public void createUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";
      session.createNativeQuery(sql).executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void dropUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      String sql = "DROP TABLE IF EXISTS users";
      session.createNativeQuery(sql).executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    try (Session session = Util.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      User user = new User(name, lastName, age);
      session.save(user);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void removeUserById(long id) {
    try (Session session = Util.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      User user = session.get(User.class, id);
      if (user != null) {
        session.delete(user);
      }
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<User> getAllUsers() {
    List<User> users = null;
    try (Session session = Util.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      users = session.createQuery("from User", User.class).list();
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }

  @Override
  public void cleanUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      String sql = "DELETE FROM users";
      session.createNativeQuery(sql).executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
