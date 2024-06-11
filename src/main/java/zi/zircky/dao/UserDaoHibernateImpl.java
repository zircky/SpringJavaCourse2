package zi.zircky.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import zi.zircky.model.User;
import zi.zircky.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
  private static final SessionFactory sessionFactory;
  Transaction transaction = null;

  static {
    sessionFactory = Util.getSessionFactory();
  }

  public UserDaoHibernateImpl() {
  }

  @Override
  public void createUsersTable() {
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();
      String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";
      session.createNativeQuery(sql).executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void dropUsersTable() {
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();
      String sql = "DROP TABLE IF EXISTS users";
      session.createNativeQuery(sql).executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();
      User user = new User(name, lastName, age);
      session.save(user);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void removeUserById(long id) {
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();
      User user = session.get(User.class, id);
      if (user != null) {
        session.delete(user);
      }
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public List<User> getAllUsers() {
    List<User> users = null;
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();
      users = session.createQuery("from User", User.class).list();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return users;
  }

  @Override
  public void cleanUsersTable() {
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();
      String sql = "DELETE FROM users";
      session.createNativeQuery(sql).executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
}
