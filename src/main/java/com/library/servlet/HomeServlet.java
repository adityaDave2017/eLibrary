package com.library.servlet;

import com.library.beans.Book;
import com.library.beans.Librarian;
import com.library.utils.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    static final String LIBRARIAN_LIST = "LIBRARIAN_LIST";
    static final String BOOKS_LIST = "BOOKS_LIST";

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String accountType = request.getAttribute(AuthenticationServlet.ACCOUNT_TYPE).toString();
        HttpSession session = request.getSession(true);
        switch (accountType) {
            case AuthenticationServlet.ADMIN_ACCOUNT:
                List<Librarian> librarians = Database.getAllLibrarians(getServletContext());
                session.setAttribute(LIBRARIAN_LIST, librarians);
                request.getRequestDispatcher("admin_home.jsp").forward(request, response);
                break;
            case AuthenticationServlet.LIBRARIAN_ACCOUNT:
                List<Book> books = Database.getAllBooks(getServletContext());
                session.setAttribute(BOOKS_LIST, books);
                session.setAttribute("userName",
                        Database.getLibrarianName(getServletContext(), request.getAttribute(AuthenticationServlet.LIBRARIAN_USERNAME).toString()));
                request.getRequestDispatcher("librarian_home.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
