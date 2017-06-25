package com.library.servlet;

import com.library.utils.BooksFinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "BookFinderServlet", urlPatterns = {"/find"})
public class BookFinderServlet extends HttpServlet {

    private static final String FOUND_BOOKS_LIST = "FOUND_BOOKS_LIST";

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute(FOUND_BOOKS_LIST, BooksFinder.searchBook(isbn));
        }
        request.getRequestDispatcher("op_books.jsp?add=true").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
