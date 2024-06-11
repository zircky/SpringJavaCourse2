package zi.zircky.service;

import zi.zircky.dao.UserDao;
import zi.zircky.dao.UserDaoHibernateImpl;
import zi.zircky.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
  private UserDao userDaoHibernate = new UserDaoHibernateImpl();


  @Override
  public void createUsersTable() {
    userDaoHibernate.createUsersTable();
  }

  @Override
  public void dropUsersTable() {
    userDaoHibernate.dropUsersTable();
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    userDaoHibernate.saveUser(name, lastName, age);
    System.out.println("User с именем - " + name + " добавлен в базу данных");
  }

  @Override
  public void removeUserById(long id) {
    userDaoHibernate.removeUserById(id);
  }

  @Override
  public List<User> getAllUsers() {
    return userDaoHibernate.getAllUsers();
  }

  @Override
  public void cleanUsersTable() {
    userDaoHibernate.cleanUsersTable();
  }
}
