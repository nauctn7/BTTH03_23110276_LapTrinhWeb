package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import models.User;
import models.Category;
import service.CategoryService;
import service.CategoryServiceImpl;

@WebServlet(urlPatterns = "/member/category/list")
public class CategoryListController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("account") : null;
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

        String q = req.getParameter("q");
        List<Category> list = (q == null || q.isBlank())
                ? service.getAllByUser(u.getId())
                : service.searchByUser(u.getId(), q);

        req.setAttribute("cateList", list);
        req.getRequestDispatcher("/view/member/category/list.jsp").forward(req, resp);
    }
}
