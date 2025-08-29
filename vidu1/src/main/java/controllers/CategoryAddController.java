package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.nio.file.*;

import models.User;
import models.Category;
import service.CategoryService;
import service.CategoryServiceImpl;

@WebServlet(urlPatterns = "/member/category/add")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // 5MB
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/view/member/category/add.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("account") : null;
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

        String name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            req.setAttribute("alert","Tên danh mục không được rỗng");
            req.getRequestDispatcher("/view/member/category/add.jsp").forward(req, resp);
            return;
        }

        // Nhận file ảnh (nếu có)
        Part filePart = req.getPart("image"); // <input name="image"> trong add.jsp
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            String uploadDir = req.getServletContext().getRealPath("/uploads");
            Files.createDirectories(Path.of(uploadDir));
            String original = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            fileName = System.currentTimeMillis() + "_" + original;
            filePart.write(uploadDir + java.io.File.separator + fileName);
        }

        Category c = new Category();
        c.setName(name);
        c.setImage(fileName);      // có thể null nếu không upload
        c.setUserId(u.getId());

        boolean ok = service.insert(c);
        if (ok) {
            resp.sendRedirect(req.getContextPath()+"/member/category/list");
        } else {
            req.setAttribute("alert","Thêm thất bại");
            req.getRequestDispatcher("/view/member/category/add.jsp").forward(req, resp);
        }
    }
}
