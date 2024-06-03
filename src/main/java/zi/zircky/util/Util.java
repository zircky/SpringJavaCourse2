package zi.zircky.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import zi.zircky.model.User;

import java.util.Properties;

public class Util {
  private static String url = "jdbc:mysql://localhost:3306/mydbtest";
  private static String username = "root";
  private static String password = "zIRCKY159";

  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.connection.url", url);
        settings.put("hibernate.connection.username", username);
        settings.put("hibernate.connection.password", password);
        settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.hbm2ddl.auto", "update");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sessionFactory;
  }
}
