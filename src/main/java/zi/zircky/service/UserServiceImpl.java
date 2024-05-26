package zi.zircky.service;

import zi.zircky.dao.UserDao;
import zi.zircky.dao.UserDaoJDBCImpl;
import zi.zircky.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
  private UserDao userDao = new UserDaoJDBCImpl();


  @Override
  public void createUsersTable() {
    userDao.createUsersTable();
  }

  @Override
  public void dropUsersTable() {
    userDao.dropUsersTable();
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    userDao.saveUser(name, lastName, age);
    System.out.println("User с именем - " + name + " добавлен в базу данных");
  }

  @Override
  public void removeUserById(long id) {
    userDao.removeUserById(id);
  }

  @Override
  public List<User> getAllUsers() {
    return userDao.getAllUsers();
  }

  @Override
  public void cleanUsersTable() {
    userDao.cleanUsersTable();
  }
}
