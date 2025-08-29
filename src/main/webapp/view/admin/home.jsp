<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Quản Trị</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <%@ include file="../topbar.jsp" %>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Chào mừng đến với Trang Quản Trị!</h1>
        <c:if test="${sessionScope.account != null}">
            <p class="text-center">Xin chào, ${sessionScope.account.fullName}! Bạn là Quản trị viên.</p>
        </c:if>
        <p class="text-center">Đây là khu vực quản lý hệ thống.</p>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>