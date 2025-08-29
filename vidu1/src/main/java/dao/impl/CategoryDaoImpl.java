package dao.impl;

import dao.CategoryDao;
import models.Category;
import vn.iotstar.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public boolean insert(Category c) {
        final String sql = "INSERT INTO Category(cate_name, image, userId) VALUES (?, ?, ?)";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getImage()); // image có thể null
            ps.setInt(3, c.getUserId());
            int rows = ps.executeUpdate();
            System.out.println("[CategoryDaoImpl] insert rows=" + rows);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("[CategoryDaoImpl] insert error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Category c) {
        final String sql = "UPDATE Category SET cate_name=?, image=? WHERE cate_id=? AND userId=?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getImage()); // image có thể null (giữ ảnh cũ ở Controller)
            ps.setInt(3, c.getId());
            ps.setInt(4, c.getUserId());
            int rows = ps.executeUpdate();
            System.out.println("[CategoryDaoImpl] update rows=" + rows);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("[CategoryDaoImpl] update error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int cateId, int userId) {
        final String sql = "DELETE FROM Category WHERE cate_id=? AND userId=?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cateId);
            ps.setInt(2, userId);
            int rows = ps.executeUpdate();
            System.out.println("[CategoryDaoImpl] delete rows=" + rows);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("[CategoryDaoImpl] delete error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Category get(int cateId, int userId) {
        final String sql = "SELECT cate_id, cate_name, image, userId FROM Category WHERE cate_id=? AND userId=?";
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cateId);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getInt("cate_id"));
                    c.setName(rs.getString("cate_name"));
                    c.setImage(rs.getString("image"));
                    c.setUserId(rs.getInt("userId"));
                    return c;
                }
            }
        } catch (Exception e) {
            System.err.println("[CategoryDaoImpl] get error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAllByUser(int userId) {
        final String sql = "SELECT cate_id, cate_name, image, userId FROM Category WHERE userId=? ORDER BY cate_id DESC";
        List<Category> list = new ArrayList<>();
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getInt("cate_id"));
                    c.setName(rs.getString("cate_name"));
                    c.setImage(rs.getString("image"));
                    c.setUserId(rs.getInt("userId"));
                    list.add(c);
                }
            }
        } catch (Exception e) {
            System.err.println("[CategoryDaoImpl] getAllByUser error: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Category> searchByUser(int userId, String keyword) {
        final String sql =
            "SELECT cate_id, cate_name, image, userId FROM Category " +
            "WHERE userId=? AND cate_name LIKE ? ORDER BY cate_id DESC";
        List<Category> list = new ArrayList<>();
        try (Connection conn = new DBCon().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getInt("cate_id"));
                    c.setName(rs.getString("cate_name"));
                    c.setImage(rs.getString("image"));
                    c.setUserId(rs.getInt("userId"));
                    list.add(c);
                }
            }
        } catch (Exception e) {
            System.err.println("[CategoryDaoImpl] searchByUser error: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
