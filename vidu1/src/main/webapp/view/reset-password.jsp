<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center" style="min-height: 100vh;">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow-sm p-4">
                <h3 class="text-center mb-3">Đặt lại mật khẩu</h3>

                <c:if test="${alert != null}">
                    <div class="alert alert-danger text-center">${alert}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/reset-password" method="post">
                    <div class="mb-3">
                        <label class="form-label">Mã OTP</label>
                        <input type="text" class="form-control" name="otp" placeholder="Nhập OTP 6 số" maxlength="6" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mật khẩu mới</label>
                        <input type="password" class="form-control" name="password" placeholder="Mật khẩu mới" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Xác nhận mật khẩu mới</label>
                        <input type="password" class="form-control" name="confirm" placeholder="Nhập lại mật khẩu" required>
                    </div>
                    <button class="btn btn-success w-100">Xác nhận</button>
                </form>

                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/forgot-password">Gửi lại OTP</a> |
                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
