<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
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
        .login-card {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        .login-card h2 {
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
        .btn-login {
            background-color: #007bff;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-login:hover {
            background-color: #0056b3;
        }
        .alert-danger {
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <div class="login-card">
        <h2>Đăng nhập</h2>
        <c:if test="${alert != null}">
            <div class="alert alert-danger text-center">${alert}</div>
        </c:if>
        <form action="login" method="post">
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
            <div class="form-check mb-3">
                <input type="checkbox" name="remember" class="form-check-input" id="remember">
                <label class="form-check-label" for="remember">Nhớ mật khẩu</label>
            </div>
            <button type="submit" class="btn btn-login btn-block text-white">Đăng nhập</button>
        </form>
    </div>
    <div class="text-center mt-3">
    <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
</div>
    

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>