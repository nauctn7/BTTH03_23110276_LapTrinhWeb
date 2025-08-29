package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.UserDao;
import models.User;
import vn.iotstar.DBCon;

public class UserDaoImpl implements UserDao {

    @Override
    public User findByUserName(String username) {
        final String sql = "SELECT userId, email, username, fullname, password, roleid, phone "
                         + "FROM [Users] WHERE username = ?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("userId"));
                    u.setEmail(rs.getString("email"));
                    u.setUserName(rs.getString("username"));
                    u.setFullName(rs.getString("fullname"));
                    u.setPassWord(rs.getString("password"));
                    u.setRoleid(rs.getInt("roleid"));
                    u.setPhone(rs.getString("phone"));
                    return u;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[UserDaoImpl] findByUserName error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(User user) {
        final String sql = "INSERT INTO [Users] (email, username, fullname, password, roleid, phone) "
                         + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());   // Khuyến nghị: hash bằng BCrypt thay vì lưu plaintext
            ps.setInt(5, user.getRoleid());
            ps.setString(6, user.getPhone());

            int rows = ps.executeUpdate();
            System.out.println("[UserDaoImpl] insert rowsAffected = " + rows);

            // Debug nhanh: đếm tổng số bản ghi (xác nhận chèn vào đúng DB/bảng)
            try (PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) AS total FROM [Users]");
                 ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    System.out.println("[UserDaoImpl] Users.total = " + rs.getInt("total"));
                }
            }

            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[UserDaoImpl] insert error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        final String sql = "SELECT 1 FROM [Users] WHERE email = ?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[UserDaoImpl] checkExistEmail error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        final String sql = "SELECT 1 FROM [Users] WHERE username = ?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[UserDaoImpl] checkExistUsername error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        final String sql = "SELECT 1 FROM [Users] WHERE phone = ?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[UserDaoImpl] checkExistPhone error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public User get(String username) {
        // Có thể tái sử dụng findByUserName để đồng nhất hành vi
        return findByUserName(username);
    }
    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        final String sql = "UPDATE [Users] SET password = ? WHERE email = ?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            int rows = ps.executeUpdate();
            System.out.println("[UserDaoImpl] updatePasswordByEmail rowsAffected = " + rows);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[UserDaoImpl] updatePasswordByEmail error: " + e.getMessage());
            return false;
        }
    }
}