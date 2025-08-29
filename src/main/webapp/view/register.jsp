<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f7fa;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .register-card {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        .register-card h2 {
            color: #333;
            text-align: center;
            margin-bottom: 1.5rem;
        }
        .form-group label {
            font-weight: 500;
            color: #555;
        }
        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
        .btn-register {
            background-color: #28a745;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-register:hover {
            background-color: #218838;
        }
        .alert-success, .alert-danger {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <div class="register-card">
        <h2>Đăng ký</h2>

        <!-- Thông báo -->
        <c:if test="${alert != null}">
            <c:choose>
                <c:when test="${success != null && success}">
                    <div class="alert alert-success text-center d-flex flex-column gap-2">
                        <span>${alert}</span>
                        <!-- Nút đăng nhập ngay khi đăng ký thành công -->
                        <a class="btn btn-primary btn-sm"
                           href="${pageContext.request.contextPath}/login">
                           Đăng nhập ngay
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-danger text-center">${alert}</div>
                </c:otherwise>
            </c:choose>
        </c:if>

        <!-- Form đăng ký -->
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group mb-3">
                <label for="username">Tài khoản</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Nhập tài khoản" required>
                </div>
            </div>
            <div class="form-group mb-3">
                <label for="password">Mật khẩu</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
                </div>
            </div>
            <div class="form-group mb-3">
                <label for="email">Email</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Nhập email" required>
                </div>
            </div>
            <div class="form-group mb-3">
                <label for="fullname">Họ tên</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-user-tag"></i></span>
                    <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Nhập họ tên" required>
                </div>
            </div>
            <div class="form-group mb-3">
                <label for="phone">Điện thoại</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-phone"></i></span>
                    <input type="tel" id="phone" name="phone" class="form-control" placeholder="Nhập số điện thoại">
                </div>
            </div>
            <button type="submit" class="btn btn-register btn-block text-white w-100">Đăng ký</button>
        </form>

        <!-- Hàng dưới form: nút dẫn sang đăng nhập -->
        <div class="text-center mt-3">
            <span>Đã có tài khoản?</span>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary btn-sm ms-1">Đăng nhập
            </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
