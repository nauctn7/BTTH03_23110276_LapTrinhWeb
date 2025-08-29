package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import models.User;
import service.CategoryService;
import service.CategoryServiceImpl;

@WebServlet(urlPatterns = "/member/category/delete")
public class CategoryDeleteController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        models.User u = (session != null) ? (User) session.getAttribute("account") : null;
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

        int id = Integer.parseInt(req.getParameter("id"));
        service.delete(id, u.getId());
        resp.sendRedirect(req.getContextPath()+"/member/category/list");
    }
}
