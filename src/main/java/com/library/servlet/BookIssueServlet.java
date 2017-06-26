package com.library.servlet;

import com.library.utils.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "BookIssueServlet", urlPatterns = {"/issue_book"})
public class BookIssueServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String bookIsbn = request.getParameter("Isbn");
        String memberMobileNo = request.getParameter("MobileNo");

        // Handles return of Book
        if (request.getParameter("Action").equals("RETURN")) {
            if (Database.memberBook(getServletContext(), bookIsbn, memberMobileNo, false)) {
                response.getWriter().write("Book Returned!<br><br>");
                request.getRequestDispatcher("issue_book.jsp").include(request, response);
            }
            return;
        }

        // Handles Issuing of Book
        if (Database.isAMember(getServletContext(), memberMobileNo)) {
            if (Database.isBookPresent(getServletContext(), bookIsbn)) {
                if (!Database.isBookAvailable(getServletContext(), bookIsbn)) {
                    response.getWriter().write("Requested Book is out of Stock!<br><br>");
                    request.getRequestDispatcher("librarian_home.jsp?show=books").include(request, response);
                    return;
                }
                if (Database.hasCrossedIssueLimit(getServletContext(), memberMobileNo)) {
                    response.getWriter().write("Cannot issue more than : " + getServletContext().getInitParameter("bookIssueLimit") + "!<br><br>");
                    request.getRequestDispatcher("librarian_home.jsp?show=books").include(request, response);
                    return;
                }
                if (Database.memberBook(getServletContext(), bookIsbn, memberMobileNo, true)) {
                    response.getWriter().write("Book Issued!<br><br>");
                    request.getRequestDispatcher("issue_book.jsp").include(request, response);
                }
            } else {
                response.getWriter().write("Book Not Present. Bring another.<br><br>");
                request.getRequestDispatcher("issue_book.jsp").include(request, response);
            }

        } else {
            response.getWriter().write("Not a Member.<br><br>");
            HttpSession session = request.getSession();
            if (session != null) {
                session.setAttribute(HomeServlet.MEMBERS_LIST, Database.getAllMembers(getServletContext()));
            }
            request.getRequestDispatcher("librarian_home.jsp?show=members").include(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
