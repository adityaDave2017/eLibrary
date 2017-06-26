package com.library.servlet;

import com.library.bean.Member;
import com.library.utils.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "OpMemberServlet", urlPatterns = {"/op_member"})
public class OpMemberServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String addParam = request.getParameter("add");
        if (addParam != null && addParam.equals("true")) {
            Member member = new Member();
            member.setFirstName(request.getParameter("FirstName"));
            member.setMiddleName(request.getParameter("MiddleName"));
            member.setLastName(request.getParameter("LastName"));
            member.setEmailId(request.getParameter("EmailId"));
            member.setOccupation(request.getParameter("Occupation"));
            member.setAddressLine1(request.getParameter("Addr1"));
            member.setAddressLine2(request.getParameter("Addr2"));
            member.setArea(request.getParameter("Area"));
            member.setZipCode(request.getParameter("ZipCode"));
            member.setMobileNo(request.getParameter("MobileNo"));
            if (Database.addMember(getServletContext(), member)) {
                response.getWriter().write("Added Successfully<br><br>");
                HttpSession session = request.getSession();
                if (session != null) {
                    session.setAttribute(HomeServlet.MEMBERS_LIST, Database.getAllMembers(getServletContext()));
                }
            } else {
                response.getWriter().write("Some Error Occurred<br><br>");
            }
            request.getRequestDispatcher("librarian_home.jsp?show=members").include(request, response);
        }

        String editParam = request.getParameter("edit");
        if (editParam != null && editParam.equals("true")) {
            Member member = new Member();
            member.setMemberId(Integer.valueOf(request.getParameter("MemberId")));
            member.setFirstName(request.getParameter("FirstName"));
            member.setMiddleName(request.getParameter("MiddleName"));
            member.setLastName(request.getParameter("LastName"));
            member.setEmailId(request.getParameter("EmailId"));
            member.setOccupation(request.getParameter("Occupation"));
            member.setAddressLine1(request.getParameter("Addr1"));
            member.setAddressLine2(request.getParameter("Addr2"));
            member.setArea(request.getParameter("Area"));
            member.setZipCode(request.getParameter("ZipCode"));
            member.setMobileNo(request.getParameter("MobileNo"));
            if (Database.editMember(getServletContext(), member)) {
                response.getWriter().write("Edited Successfully<br><br>");
                HttpSession session = request.getSession();
                if (session != null) {
                    session.setAttribute(HomeServlet.MEMBERS_LIST, Database.getAllMembers(getServletContext()));
                }
            } else {
                response.getWriter().write("Some Error Occurred<br><br>");
            }
            request.getRequestDispatcher("librarian_home.jsp?show=members").include(request, response);
        }

        String deleteParam = request.getParameter("delete");
        if (deleteParam != null && deleteParam.equals("true")) {
            if (Database.deleteMember(getServletContext(), Integer.valueOf(request.getParameter("memberId")))) {
                response.getWriter().write("Delete Successfully<br><br>");
                HttpSession session = request.getSession();
                if (session != null) {
                    session.setAttribute(HomeServlet.MEMBERS_LIST, Database.getAllMembers(getServletContext()));
                }
            } else {
                response.getWriter().write("Some Error Occurred<br><br>");
                HttpSession session = request.getSession();
                if (session != null) {
                    session.setAttribute(HomeServlet.MEMBERS_LIST, Database.getAllMembers(getServletContext()));
                }
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
