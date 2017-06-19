package com.library.utils;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;


public class Database {

    private static Connection getConnection(ServletContext servletContext) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String databaseUrl = servletContext.getInitParameter("databaseUrl") + "useSSL=false";
        Properties dbProps = new Properties();
        dbProps.put("user", servletContext.getInitParameter("databaseUsername"));
        dbProps.put("password", servletContext.getInitParameter("databasePassword"));
        return DriverManager.getConnection(databaseUrl, dbProps);
    }


    public static boolean isValidLibrarian(ServletContext servletContext, String username, String password) {
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                System.out.println("Password: " + storedPassword);
                return storedPassword.equals(PasswordUtils.generateHash(password + servletContext.getInitParameter("passwordSalt")));
            } else {
                System.out.println("Not Found!!!");
            }
            return false;
        } catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }
    }

}
