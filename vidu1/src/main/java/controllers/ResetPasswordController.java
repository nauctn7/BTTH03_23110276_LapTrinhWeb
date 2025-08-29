package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nếu chưa có email trong session (chưa gửi OTP) thì đá về forgot
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("fp.email") == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }
        req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }

        String otp = req.getParameter("otp");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        String sessEmail = (String) session.getAttribute("fp.email");
        String sessOtp = (String) session.getAttribute("fp.otp");
        Long expiresAt = (Long) session.getAttribute("fp.expiresAt");
        Integer attempts = (Integer) session.getAttribute("fp.attempts");
        if (attempts == null) attempts = 0;

        if (sessEmail == null || sessOtp == null || expiresAt == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }

        // Hết hạn?
        if (System.currentTimeMillis() > expiresAt) {
            clearSession(session);
            req.setAttribute("alert", "OTP đã hết hạn. Vui lòng yêu cầu lại.");
            req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra input
        if (otp == null || otp.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập đầy đủ OTP và mật khẩu mới.");
            req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
            return;
        }
        if (!password.equals(confirm)) {
            req.setAttribute("alert", "Xác nhận mật khẩu không khớp.");
            req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
            return;
        }

        // Hạn chế đoán OTP
        if (attempts >= 5) {
            clearSession(session);
            req.setAttribute("alert", "Bạn đã nhập sai quá số lần cho phép. Vui lòng yêu cầu OTP lại.");
            req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
            return;
        }

        // So khớp OTP
        if (!otp.equals(sessOtp)) {
            session.setAttribute("fp.attempts", attempts + 1);
            req.setAttribute("alert", "OTP không đúng. Vui lòng thử lại.");
            req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
            return;
        }

        // OTP đúng → cập nhật mật khẩu
        boolean ok = service.updatePasswordByEmail(sessEmail, password);
        clearSession(session);

        if (ok) {
            req.setAttribute("alert", "Đặt lại mật khẩu thành công! Vui lòng đăng nhập.");
            req.setAttribute("success", true);
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Có lỗi hệ thống khi đặt lại mật khẩu. Hãy thử lại.");
            req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
        }
    }

    private void clearSession(HttpSession session) {
        session.removeAttribute("fp.email");
        session.removeAttribute("fp.otp");
        session.removeAttribute("fp.expiresAt");
        session.removeAttribute("fp.attempts");
    }
}
