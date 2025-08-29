<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center" style="min-height: 100vh;">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow-sm p-4">
                <h3 class="text-center mb-3">Quên mật khẩu</h3>

                <c:if test="${alert != null}">
                    <div class="alert alert-danger text-center">${alert}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                    <div class="mb-3">
                        <label class="form-label">Email đã đăng ký</label>
                        <input type="email" class="form-control" name="email" placeholder="Nhập email" required>
                    </div>
                    <button class="btn btn-primary w-100">Gửi OTP</button>
                </form>

                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
