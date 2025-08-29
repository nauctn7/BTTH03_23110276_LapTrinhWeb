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

@WebServlet(urlPatterns = "/member/category/edit")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("account") : null;
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

        int id = Integer.parseInt(req.getParameter("id"));
        Category c = service.get(id, u.getId());
        if (c == null) { resp.sendError(404); return; }

        req.setAttribute("cate", c);
        req.getRequestDispatcher("/view/member/category/edit.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("account") : null;
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String oldImage = req.getParameter("oldImage"); // hidden trong edit.jsp

        if (name == null || name.isBlank()) {
            req.setAttribute("alert","Tên danh mục không được rỗng");
            req.setAttribute("cate", service.get(id, u.getId()));
            req.getRequestDispatcher("/view/member/category/edit.jsp").forward(req, resp);
            return;
        }

        // Nhận file mới (nếu có)
        Part filePart = req.getPart("image");
        String fileName = oldImage; // mặc định giữ ảnh cũ
        if (filePart != null && filePart.getSize() > 0) {
            String uploadDir = req.getServletContext().getRealPath("/uploads");
            Files.createDirectories(Path.of(uploadDir));
            String original = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            fileName = System.currentTimeMillis() + "_" + original;
            filePart.write(uploadDir + java.io.File.separator + fileName);
        }

        Category c = new Category();
        c.setId(id);
        c.setName(name);
        c.setImage(fileName);
        c.setUserId(u.getId());

        boolean ok = service.update(c);
        if (ok) {
            resp.sendRedirect(req.getContextPath()+"/member/category/list");
        } else {
            req.setAttribute("alert","Cập nhật thất bại");
            req.setAttribute("cate", service.get(id, u.getId()));
            req.getRequestDispatcher("/view/member/category/edit.jsp").forward(req, resp);
        }
    }
}
