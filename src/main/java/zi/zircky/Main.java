package zi.zircky;

import zi.zircky.model.User;
import zi.zircky.service.UserService;
import zi.zircky.service.UserServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    UserService userService = new UserServiceImpl();

    userService.createUsersTable();

    userService.saveUser("John", "Doe", (byte) 30);
    userService.saveUser("Jane", "Doe", (byte) 25);
    userService.saveUser("Jim", "Beam", (byte) 35);
    userService.saveUser("Jack", "Daniels", (byte) 40);

    for (User user : userService.getAllUsers()) {
      System.out.println(user);
    }

    userService.cleanUsersTable();
    userService.dropUsersTable();
  }
}