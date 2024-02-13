package clinic_app;

import java.sql.*;

public class DatabaseConnection {
   private Connection connection;
   private final String url;
   private final String username;
   private final String password;

   public DatabaseConnection() {
      this.url = "jdbc:mysql://localhost:3306/clinic_app";
      this.username = "root";
      this.password = "Root_2023";
   }

   public Connection getConnection() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(url, username, password);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return connection;
   }

   public void closeConnection() {
      try {
         connection.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}

