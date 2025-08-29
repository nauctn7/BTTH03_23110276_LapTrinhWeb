package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");
            if (u.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/view/admin/home.jsp"); // Thay đổi đường dẫn
            } else if (u.getRoleid() == 2) {
                resp.sendRedirect(req.getContextPath() + "/view/manager/home.jsp");
            } else if (u.getRoleid() == 5) {
                resp.sendRedirect(req.getContextPath() + "/view/home.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/view/home.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}