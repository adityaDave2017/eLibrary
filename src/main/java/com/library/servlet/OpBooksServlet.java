package com.library.servlet;

import com.library.bean.Book;
import com.library.utils.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "OpBooksServlet", urlPatterns = {"/op_books"})
public class OpBooksServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String addParam = request.getParameter("add");
        if (addParam != null && addParam.equals("true")) {
            Book book = new Book();
            book.setIsbn13(request.getParameter("isbn13"));
            book.setIsbn10(request.getParameter("isbn10"));
            book.setTitle(request.getParameter("title"));
            book.setDescription(request.getParameter("description"));
            book.setAuthors(request.getParameter("authors"));
            book.setPublisher(request.getParameter("publisher"));
            book.setPublishDate(request.getParameter("publishDate"));
            book.setCategory(request.getParameter("category"));
            book.setAvgRating(request.getParameter("avgRating"));
            book.setImageUrl(request.getParameter("imageUrl"));
            book.setQuantity(Integer.valueOf(request.getParameter("quantity")));
            book.setIssuedQty(Integer.valueOf(request.getParameter("issuedQty")));

            if (Database.addBook(getServletContext(), book)) {
                response.getWriter().write("Added Successfully!<br><br>");
                HttpSession session = request.getSession();
                if (session != null) {
                    session.setAttribute(HomeServlet.BOOKS_LIST, Database.getAllBooks(getServletContext()));
                }
                request.getRequestDispatcher("librarian_home.jsp").include(request, response);
            }
        }

        String editParam = request.getParameter("edit");
        if (editParam != null && editParam.equals("true")) {
            Book book = new Book();
            book.setBookID(Integer.valueOf(request.getParameter("bookId")));
            book.setIsbn13(request.getParameter("isbn13"));
            book.setIsbn10(request.getParameter("isbn10"));
            book.setTitle(request.getParameter("title"));
            book.setDescription(request.getParameter("description"));
            book.setAuthors(request.getParameter("authors"));
            book.setPublisher(request.getParameter("publisher"));
            book.setPublishDate(request.getParameter("publishDate"));
            book.setCategory(request.getParameter("category"));
            book.setAvgRating(request.getParameter("avgRating"));
            book.setImageUrl(request.getParameter("imageUrl"));
            book.setQuantity(Integer.valueOf(request.getParameter("quantity")));
            book.setIssuedQty(Integer.valueOf(request.getParameter("issuedQty")));

            if (Database.editBook(getServletContext(), book)) {
                response.getWriter().write("Edit Successful!<br><br>");
                HttpSession session = request.getSession();
                if (session != null) {
                    session.setAttribute(HomeServlet.BOOKS_LIST, Database.getAllBooks(getServletContext()));
                }
                request.getRequestDispatcher("librarian_home.jsp").include(request, response);
            }

        }

        String deleteParam = request.getParameter("delete");
        if (deleteParam != null && deleteParam.equals("true")) {
            if (Database.deleteBook(getServletContext(), Integer.valueOf(request.getParameter("bookId")))) {
                response.getWriter().write("Delete Successful!<br><br>");
                HttpSession session = request.getSession();
                if (session != null) {
                    session.setAttribute(HomeServlet.BOOKS_LIST, Database.getAllBooks(getServletContext()));
                }
                request.getRequestDispatcher("librarian_home.jsp").include(request, response);
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
