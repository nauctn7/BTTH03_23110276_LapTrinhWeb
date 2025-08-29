package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import service.UserServiceImpl;
import utils.EmailUtil;
import utils.OtpUtil;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập email đã đăng ký!");
            req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
            return;
        }

        if (!service.checkExistEmail(email)) {
            req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
            return;
        }

        // Tạo OTP + lưu vào session với hạn 5 phút
        String otp = OtpUtil.gen6();
        long expiresAt = System.currentTimeMillis() + 5 * 60 * 1000L;

        HttpSession session = req.getSession(true);
        session.setAttribute("fp.email", email);
        session.setAttribute("fp.otp", otp);
        session.setAttribute("fp.expiresAt", expiresAt);
        session.setAttribute("fp.attempts", 0); // đếm số lần nhập sai

        // Gửi mail
        String subject = "OTP đặt lại mật khẩu";
        String body = "Mã OTP của bạn là: " + otp + "\nCó hiệu lực trong 5 phút.\n"
                    + "Nếu không phải bạn yêu cầu, vui lòng bỏ qua email này.";
        boolean sent = EmailUtil.send(email, subject, body);

        if (!sent) {
            req.setAttribute("alert", "Không gửi được email OTP. Vui lòng thử lại.");
            req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
            return;
        }

        // Chuyển sang trang nhập OTP & mật khẩu mới
        resp.sendRedirect(req.getContextPath() + "/reset-password");
    }
}