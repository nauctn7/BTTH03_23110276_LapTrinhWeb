# Bài thực hành 03 – Lập trình Web

**Họ tên**: Cáp Thanh Nhàn
**MSSV**: 23110276

## 📖 Giới thiệu

Bài thực hành số 03 môn Lập trình Web.
Tiếp nối BTTH02 (Login, Register, Forgot Password, Logout, CRUD Category)
## 📝 Yêu cầu bài tập

Thực hiện chức năng forget password, logout cho bài tập 02

Bảng category liên kết với bảng users (01 user có nhiều category), CRUD của category trong bài giảng.

## 📂 Cấu trúc thư mục
vidu1/                # Thư mục ví dụ trong BTTH03
 └─ src/main/java
     ├─ controllers/  # Servlet Controller (xử lý request/response)
     ├─ dao/          # Data Access Object (truy vấn DB với JDBC)
     ├─ models/       # Lớp mô hình (User, Category, …)
     ├─ service/      # Business Logic Layer
     ├─ utils/        # DBConnection, PasswordUtil, Constant, …
     └─ vn/iotstar/   # package chứa code chính
 └─ src/main/webapp   # JSP views, static files (css, js)
 └─ pom.xml           # Cấu hình Maven
