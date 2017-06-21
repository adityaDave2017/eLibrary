package com.library.servlet;

import com.library.utils.Database;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AuthenticationServlet", urlPatterns = "/authenticate")
public class AuthenticationServlet extends HttpServlet {

    static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    static final String ADMIN_ACCOUNT = "ADMIN_ACCOUNT";
    static final String LIBRARIAN_ACCOUNT = "LIBRARIAN_ACCOUNT";

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String userName = request.getParameter("UserName");
        String password = request.getParameter("Password");
        String type = request.getParameter("Type");

        ServletContext servletContext = getServletContext();
        switch (type) {
            case "admin":
                if (userName.equals(servletContext.getInitParameter("adminUserName"))
                        && password.equals(servletContext.getInitParameter("adminPassword"))) {
                    request.setAttribute(ACCOUNT_TYPE, ADMIN_ACCOUNT);
                    request.getRequestDispatcher("/home").forward(request, response);
                } else {
                    response.getWriter().write("invalid credentials");
                    servletContext.getRequestDispatcher("/index.html").include(request, response);
                }
                break;
            case "librarian":
                if (Database.isValidLibrarian(servletContext, userName, password)) {
                    request.setAttribute(ACCOUNT_TYPE, LIBRARIAN_ACCOUNT);
                    response.sendRedirect("/home");
                } else {
                    response.getWriter().write("invalid credentials<br><b");
                    servletContext.getRequestDispatcher("/index.html").include(request, response);
                }
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
