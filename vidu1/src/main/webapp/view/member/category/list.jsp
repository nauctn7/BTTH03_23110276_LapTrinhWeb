<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  // Chặn cache để không giữ ảnh cũ
  response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader("Expires", 0);
  String cacheBust = String.valueOf(System.currentTimeMillis());
%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <title>Quản lý Danh mục</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css" rel="stylesheet" />
  <style>
    body { background:#f2f6ff; }
    .sidebar { width:240px; background:#0d6efd; min-height:100vh; color:#fff; position:fixed; left:0; top:0; }
    .sidebar .brand { font-size:24px; font-weight:700; padding:20px; background:#0b5ed7; }
    .sidebar .menu a { display:block; padding:12px 20px; color:#fff; text-decoration:none; }
    .sidebar .menu a.active, .sidebar .menu a:hover { background:#dc3545; }
    .content { margin-left:240px; }
    .topbar { background:#0d6efd; color:#fff; padding:12px 20px; }
    .card { border:0; box-shadow:0 2px 10px rgba(0,0,0,.06); }
    table img { height:50px; border-radius:6px; }
    .dt-length select{ width:auto!important; }
  </style>
</head>
<body>
  <!-- Sidebar -->
  <div class="sidebar">
    <div class="brand">Dashboard</div>
    <div class="p-3 menu">
      <a href="${pageContext.request.contextPath}/member/category/list" class="active">Quản lý Danh mục</a>
      <a href="${pageContext.request.contextPath}/member/category/add">➕ Thêm danh mục mới</a>
      <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </div>
  </div>

  <!-- Content -->
  <div class="content">
    <div class="topbar d-flex justify-content-between">
      <div></div>
      <div>Xin chào <b>${sessionScope.account.fullName}</b>
        <a class="btn btn-sm btn-danger ms-3" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
      </div>
    </div>

    <div class="container py-4">
      <h3 class="text-danger mb-3">Quản lý danh mục</h3>

      <div class="card">
        <div class="card-header bg-light fw-semibold">Danh sách danh mục</div>
        <div class="card-body">
          <div class="mb-3">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/member/category/add">Thêm danh mục mới</a>
          </div>

          <table id="cateTable" class="table table-striped table-bordered align-middle">
            <thead class="table-light">
              <tr>
                <th>STT</th>
                <th>Hình ảnh</th>
                <th>Tên danh mục</th>
                <th style="width:140px;">Hành động</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="c" items="${cateList}" varStatus="st">
                <tr>
                  <td>${st.index + 1}</td>
                  <td>
                    <c:choose>
                      <c:when test="${not empty c.image}">
                        <img src="${pageContext.request.contextPath}/uploads/${c.image}?v=<%=cacheBust%>"
                             alt="${c.name}" />
                      </c:when>
                      <c:otherwise>
                        <img src="https://via.placeholder.com/80x50?text=No+Img" alt="no image" />
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td>${c.name}</td>
                  <td>
                    <a class="text-decoration-none me-3"
                       href="${pageContext.request.contextPath}/member/category/edit?id=${c.id}">Sửa</a>
                    <a class="text-decoration-none text-danger"
                       href="${pageContext.request.contextPath}/member/category/delete?id=${c.id}"
                       onclick="return confirm('Xóa danh mục này?')">Xóa</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>

    </div>
  </div>

  <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
  <script>
    $(function(){
      $('#cateTable').DataTable({
        language:{ lengthMenu: "_MENU_ records per page", search: "Search:" },
        pageLength: 10,
        order: [[0,'asc']]
      });
    });
  </script>
</body>
</html>
