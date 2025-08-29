package controllers;

import service.UserService;
import service.UserServiceImpl;
import models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    public static final String COOKIE_REMEMBER = "username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nếu đã có session -> chuyển thẳng danh mục
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/member/category/list");
            return;
        }

        // Nếu có cookie remember -> auto login
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_REMEMBER.equals(cookie.getName())) {
                    String usernameInCookie = cookie.getValue();
                    UserService service = new UserServiceImpl();
                    User user = service.findByUserName(usernameInCookie);
                    if (user != null) {
                        session = req.getSession(true);
                        session.setAttribute("account", user);

                        // Điều hướng hợp lý sau auto-login (ưu tiên next nếu có)
                        String next = req.getParameter("next");
                        String dest = (next != null && !next.isBlank()) ? next : "/member/category/list";
                        resp.sendRedirect(req.getContextPath() + dest);
                        return;
                    }
                }
            }
        }

        // Hiển thị trang login (truyền tiếp biến next nếu có)
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        String next = req.getParameter("next");  // có thể null
        boolean isRememberMe = "on".equals(remember);

        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            // giữ lại next để submit lại không mất
            req.setAttribute("next", next);
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        User user = service.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            if (isRememberMe) {
                saveRememberMe(req, resp, username);
            }

            // Điều hướng: ưu tiên next, mặc định về list danh mục
            String dest = (next != null && !next.isBlank()) ? next : "/member/category/list";
            resp.sendRedirect(req.getContextPath() + dest);
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.setAttribute("next", next);
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletRequest req, HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
        cookie.setHttpOnly(true);                    // tăng bảo mật
        cookie.setMaxAge(30 * 24 * 60 * 60);        // 30 ngày
        cookie.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
        resp.addCookie(cookie);
    }
}
