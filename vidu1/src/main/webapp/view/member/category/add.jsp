<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Thêm danh mục</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .sidebar { width:240px; background:#0d6efd; min-height:100vh; color:#fff; position:fixed; left:0; top:0; }
    .sidebar .brand { font-size:24px; font-weight:700; padding:20px; background:#0b5ed7; }
    .sidebar .menu a { display:block; padding:12px 20px; color:#fff; text-decoration:none; }
    .sidebar .menu a.active, .sidebar .menu a:hover { background:#dc3545; }
    .content { margin-left:240px; }
    .topbar { background:#0d6efd; color:#fff; padding:12px 20px; }
    .card { border:0; box-shadow:0 2px 10px rgba(0,0,0,.06); }
  </style>
</head>
<body>
  <div class="sidebar">
    <div class="brand">Dashboard</div>
    <div class="p-3 menu">
      <a href="${pageContext.request.contextPath}/member/category/list">Quản lý Danh mục</a>
      <a class="active" href="${pageContext.request.contextPath}/member/category/add">➕ Thêm danh mục mới</a>
      <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </div>
  </div>

  <div class="content">
    <div class="topbar d-flex justify-content-between"><div></div>
      <div>Xin chào <b>${sessionScope.account.fullName}</b></div></div>

    <div class="container py-4">
      <div class="card">
        <div class="card-header bg-light fw-semibold">Thêm danh mục</div>
        <div class="card-body">
          <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
          </c:if>

          <!-- NHỚ: multipart/form-data để upload -->
          <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/member/category/add">
            <div class="mb-3">
              <label class="form-label">Tên danh mục</label>
              <input class="form-control" name="name" required>
            </div>
            <div class="mb-3">
              <label class="form-label">Ảnh đại diện</label>
              <input type="file" class="form-control" name="image" accept="image/*">
              <div class="form-text">Chấp nhận ảnh (jpg, png, webp). Tối đa ~5MB.</div>
            </div>
            <button class="btn btn-success">Lưu</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/member/category/list">Hủy</a>
          </form>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
