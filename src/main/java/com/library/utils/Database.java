package com.library.utils;

import com.library.beans.Book;
import com.library.beans.Librarian;
import com.library.beans.Member;

import javax.servlet.ServletContext;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;


@SuppressWarnings("Duplicates")
public class Database {

    public static final int BOOK_ISSUING_LIMIT = 2;

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
        String insertQuery = "INSERT INTO books(isbn_13, isbn_10, title, description, page_count, authors, publisher, publish_date, category, avg_rating, image_url, quantity, issued_qty) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, book.getIsbn13());
            preparedStatement.setString(2, book.getIsbn10());
            preparedStatement.setString(3, book.getTitle());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setInt(5, book.getPageCount());
            preparedStatement.setString(6, book.getAuthors());
            preparedStatement.setString(7, book.getPublisher());
            preparedStatement.setString(8, book.getPublishDate());
            preparedStatement.setString(9, book.getCategory());
            preparedStatement.setString(10, book.getAvgRating());
            preparedStatement.setString(11, book.getImageUrl());
            preparedStatement.setInt(12, book.getQuantity());
            preparedStatement.setInt(13, book.getIssuedQty());
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean editBook(ServletContext servletContext, Book book) {
        String updateQuery = "UPDATE books SET isbn_13 = ?, isbn_10 = ?, title = ?, description = ?, page_count = ?, authors = ?, publisher = ?, publish_date = ?, category = ?, avg_rating = ?, image_url = ?, quantity = ?, issued_qty = ? WHERE book_id = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, book.getIsbn13());
            preparedStatement.setString(2, book.getIsbn10());
            preparedStatement.setString(3, book.getTitle());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setInt(5, book.getPageCount());
            preparedStatement.setString(6, book.getAuthors());
            preparedStatement.setString(7, book.getPublisher());
            preparedStatement.setString(8, book.getPublishDate());
            preparedStatement.setString(9, book.getCategory());
            preparedStatement.setString(10, book.getAvgRating());
            preparedStatement.setString(11, book.getImageUrl());
            preparedStatement.setInt(12, book.getQuantity());
            preparedStatement.setInt(13, book.getIssuedQty());
            preparedStatement.setInt(14, book.getBookID());
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean deleteBook(ServletContext servletContext, int bookId) {
        String deleteQuery = "DELETE FROM books WHERE book_id = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, bookId);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
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


    public static boolean addMember(ServletContext servletContext, Member member) {
        String insertQuery = "INSERT INTO member(first_name, middle_name, last_name, email_id, occupation, addr_1, addr_2, area, zipcode, mobile_no/*, id_proof*/) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?/*, ?*/)";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getMiddleName());
            preparedStatement.setString(3, member.getLastName());
            preparedStatement.setString(4, member.getEmailId());
            preparedStatement.setString(5, member.getOccupation());
            preparedStatement.setString(6, member.getAddressLine1());
            preparedStatement.setString(7, member.getAddressLine2());
            preparedStatement.setString(8, member.getArea());
            preparedStatement.setString(9, member.getZipCode());
            preparedStatement.setString(10, member.getMobileNo());

//            If id proof is added update this
//            preparedStatement.setBlob(11, null);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean editMember(ServletContext servletContext, Member member) {
        String updateQuery = "UPDATE member SET first_name = ?, middle_name = ?, last_name = ?, email_id = ?, occupation = ?, addr_1 = ?, addr_2 = ?, area = ?, zipcode = ?, mobile_no = ?/*, id_proof = ? */WHERE member_id = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getMiddleName());
            preparedStatement.setString(3, member.getLastName());
            preparedStatement.setString(4, member.getEmailId());
            preparedStatement.setString(5, member.getOccupation());
            preparedStatement.setString(6, member.getAddressLine1());
            preparedStatement.setString(7, member.getAddressLine2());
            preparedStatement.setString(8, member.getArea());
            preparedStatement.setString(9, member.getZipCode());
            preparedStatement.setString(10, member.getMobileNo());
            preparedStatement.setInt(11, member.getMemberId());

            /*
            If id proof is added update this
            preparedStatement.setBlob(11, null);
            */
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean deleteMember(ServletContext servletContext, int memberId) {
        String deleteQuery = "DELETE FROM member WHERE member_id = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, memberId);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static List<Member> getAllMembers(ServletContext servletContext) {
        String query = "SELECT * FROM member";
        ArrayList<Member> membersList = new ArrayList<>();

        try (Connection connection = getConnection(servletContext)) {

            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    Member member = new Member();
                    member.setMemberId(resultSet.getInt("member_id"));
                    member.setFirstName(resultSet.getString("first_name"));
                    member.setMiddleName(resultSet.getString("middle_name"));
                    member.setLastName(resultSet.getString("last_name"));
                    member.setEmailId(resultSet.getString("email_id"));
                    member.setOccupation(resultSet.getString("occupation"));
                    member.setAddressLine1(resultSet.getString("addr_1"));
                    member.setAddressLine2(resultSet.getString("addr_2"));
                    member.setArea(resultSet.getString("area"));
                    member.setZipCode(resultSet.getString("zipcode"));
                    member.setMobileNo(resultSet.getString("mobile_no"));

                    // If id proof service is enabled check this
                    member.setIdProof(null);
                    membersList.add(member);
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return membersList;
    }


    public static boolean hasCrossedIssueLimit(ServletContext servletContext, String mobileNo) {
        String query = "SELECT COUNT(*) FROM book_issue WHERE member_id = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mobileNo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getInt(1) > BOOK_ISSUING_LIMIT) {
                        return true;
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean isAMember(ServletContext servletContext, String mobileNo) {
        String query = "SELECT * FROM member WHERE mobile_no = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mobileNo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean isBookPresent(ServletContext servletContext, String isbn) {
        String query = "SELECT * FROM books WHERE isbn_10 = ? OR isbn_13 = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean isBookAvailable(ServletContext servletContext, String isbn) {
        String query = "SELECT quantity, issued_qty FROM books WHERE isbn_10 = ? OR isbn_13 = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getInt("quantity") - resultSet.getInt("issued_qty") > 0) {
                        return true;
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    private static boolean bookIssued(ServletContext servletContext, String isbn) {
        String query = "UPDATE books SET issued_qty = issued_qty + 1 WHERE isbn_10 = ? OR isbn_13 = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, isbn);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    private static boolean bookReturned(ServletContext servletContext, String isbn) {
        String query = "UPDATE books SET issued_qty = issued_qty - 1 WHERE isbn_10 = ? OR isbn_13 = ?";
        try (Connection connection = getConnection(servletContext)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, isbn);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }


    public static boolean memberBook(ServletContext servletContext, String isbn, String mobileNo, boolean issue) {
        String issueQuery = "INSERT INTO book_issue(book_id, member_id, return_date) VALUES (?, ?, ?)";
        String returnQuery = "DELETE FROM book_issue WHERE book_id = ? AND member_id = ?";
        try (Connection connection = getConnection(servletContext)) {
            if (issue) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 14);
                PreparedStatement preparedStatement = connection.prepareStatement(issueQuery);
                preparedStatement.setString(1, isbn);
                preparedStatement.setString(2, mobileNo);
                preparedStatement.setString(3, simpleDateFormat.format(calendar.getTime()));
                if (preparedStatement.executeUpdate() > 0 && bookIssued(servletContext, isbn)) {
                    return true;
                }
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement(returnQuery);
                preparedStatement.setString(1, isbn);
                preparedStatement.setString(2, mobileNo);
                if (preparedStatement.executeUpdate() > 0 && bookReturned(servletContext, isbn)) {
                    return true;
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }

}