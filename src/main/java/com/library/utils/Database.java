package com.library.utils;

import com.library.beans.Book;
import com.library.beans.Librarian;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM librarian WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
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


    public static List<Librarian> getAllLibrarians(ServletContext servletContext) {
        ArrayList<Librarian> librarians = new ArrayList<>();
        try (Connection connection = getConnection(servletContext)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM librarian");
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setLibrarianId(resultSet.getInt("librarian_id"));
                librarian.setUsername(resultSet.getString("username"));
                librarian.setPassword(resultSet.getString("password"));
                librarian.setName(resultSet.getString("name"));
                librarian.setMobileNo(resultSet.getString("mobile_no"));
                librarian.setEmailId(resultSet.getString("email_id"));
                librarians.add(librarian);
            }
            return librarians;
        } catch (Exception exc) {
            exc.printStackTrace();
            return librarians;
        }
    }


    public static List<Book> getAllBooks(ServletContext servletContext) {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = getConnection(servletContext)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookID(resultSet.getInt("book_id"));
                book.setIsbn13(resultSet.getString("isbn_13"));
                book.setIsbn10(resultSet.getString("isbn_10"));
                book.setTitle(resultSet.getString("title"));
                book.setDescription(resultSet.getString("description"));
                book.setPageCount(resultSet.getInt("page_count"));
                book.setAuthors(resultSet.getString("authors"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setPublishDate(resultSet.getString("publish_date"));
                book.setCategory(resultSet.getString("category"));
                book.setAvgRating(resultSet.getString("avg_rating"));
                book.setImageUrl(resultSet.getString("image_url"));
                book.setQuantity(resultSet.getInt("quantity"));
                books.add(book);
            }
            return books;
        } catch (Exception exc) {
            exc.printStackTrace();
            return books;
        }
    }


    public static boolean addBook(ServletContext servletContext, Book book) {

        return false;
    }


    public static boolean editBook(ServletContext servletContext, Book book) {
        return false;
    }


    public static boolean deleteBook(ServletContext servletContext, int bookId) {
        return false;
    }


    public static boolean opLibrarian(ServletContext servletContext, Librarian librarian, boolean edit) {
        String insertQuery = "INSERT INTO librarian(name, username, password, email_id, mobile_no) VALUES(?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE librarian SET name = ?, username = ?, email_id = ?, mobile_no = ? WHERE librarian_id = ?";

        try (Connection connection = getConnection(servletContext)) {

            PreparedStatement preparedStatement;
            if (!edit) {
                preparedStatement =  connection.prepareStatement(insertQuery);
                preparedStatement.setString(3, librarian.getPassword());
                preparedStatement.setString(4, librarian.getEmailId());
                preparedStatement.setString(5, librarian.getMobileNo());
            } else {
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(3, librarian.getEmailId());
                preparedStatement.setString(4, librarian.getMobileNo());
                preparedStatement.setInt(5, librarian.getLibrarianId());
            }

            preparedStatement.setString(1, librarian.getName());
            preparedStatement.setString(2, librarian.getUsername());

            if(preparedStatement.executeUpdate() != 0) {
                return true;
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return false;
    }


    public static String getLibrarianName(ServletContext servletContext, String userName) {
        String query = "SELECT * FROM librarian WHERE username = ?";
        String name = "";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return name;
    }

    public static boolean removeLibrarian(ServletContext servletContext, int librarianId) {
        String deleteQuery = "DELETE FROM librarian WHERE librarian_id = ?";

        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, librarianId);
            if(preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return false;
    }


}
