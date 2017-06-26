package com.library.servlet;

import com.library.bean.Librarian;
import com.library.utils.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "OpLibrarianServlet", urlPatterns = {"/op_librarian"})
public class OpLibrarianServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        response.setContentType("text/html");

        String name = request.getParameter("Name");
        String username = request.getParameter("Username");
        String emailId = request.getParameter("EmailId");
        String mobileNo = request.getParameter("MobileNo");

        Librarian librarian = new Librarian();
        librarian.setLibrarianId(0);
        librarian.setUsername(username);
        librarian.setPassword(null);
        librarian.setName(name);
        librarian.setMobileNo(mobileNo);
        librarian.setEmailId(emailId);

        String edit = request.getParameter("edit");
        String add = request.getParameter("add");
        String delete = request.getParameter("delete");
        if (edit != null && edit.equals("true")) {
            int librarianId = Integer.valueOf(request.getParameter("LibrarianId"));

            librarian.setLibrarianId(librarianId);

            if (Database.opLibrarian(getServletContext(), librarian, true)) {
                HttpSession session = request.getSession();
                session.setAttribute(HomeServlet.LIBRARIAN_LIST, Database.getAllLibrarians(getServletContext()));
                request.getRequestDispatcher("admin_home.jsp").forward(request, response);
                response.getWriter().write("Edit Successful!!!");
            }

        } else if (add != null && add.equals("true")) {
            String password = request.getParameter("Password");
            librarian.setPassword(password);

            if (Database.opLibrarian(getServletContext(), librarian, false)) {
                HttpSession session = request.getSession();
                session.setAttribute(HomeServlet.LIBRARIAN_LIST, Database.getAllLibrarians(getServletContext()));
                request.getRequestDispatcher("admin_home.jsp").forward(request, response);
                response.getWriter().write("Added Successful!!!");
            }
        } else if (delete != null && delete.equals("true")) {

            int librarianId = Integer.valueOf(request.getParameter("librarian_id"));
            if (Database.removeLibrarian(getServletContext(), librarianId)) {
                HttpSession session = request.getSession();
                session.setAttribute(HomeServlet.LIBRARIAN_LIST, Database.getAllLibrarians(getServletContext()));
                request.getRequestDispatcher("admin_home.jsp").forward(request, response);
                response.getWriter().write("Delete  Successful!!!");
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
