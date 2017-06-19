package com.library.filter;

import com.library.utils.Database;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "AuthFilter", urlPatterns = "/login")
public class AuthFilter implements Filter {

    private FilterConfig filterConfig;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType("text/html");

        String userName = req.getParameter("UserName");
        String password = req.getParameter("Password");
        String type = req.getParameter("Type");

        ServletContext servletContext = filterConfig.getServletContext();
        switch (type) {
            case "admin":
                if (userName.equals(servletContext.getInitParameter("adminUserName"))
                        && password.equals(servletContext.getInitParameter("adminPassword"))) {
                    ((HttpServletResponse) resp).sendRedirect("adminHome.jsp");
                } else {
                    resp.getWriter().write("invalid credentials<br><br>");
                    servletContext.getRequestDispatcher("/index.html").include(req, resp);
                }
                break;
            case "librarian":
                if (Database.isValidLibrarian(servletContext, userName, password)) {
                    ((HttpServletResponse) resp).sendRedirect("librarianHome.jsp");
                } else {
                    resp.getWriter().write("invalid credentials<br><br>");
                    servletContext.getRequestDispatcher("/index.html").include(req, resp);
                }
                break;
        }

    }

    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

}
