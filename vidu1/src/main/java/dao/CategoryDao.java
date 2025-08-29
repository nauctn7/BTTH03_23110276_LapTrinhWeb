package dao;

import java.util.List;
import models.Category;

public interface CategoryDao {
    boolean insert(Category c);
    boolean update(Category c);         // chỉ cập nhật cate của đúng user
    boolean delete(int cateId, int userId);
    Category get(int cateId, int userId);
    List<Category> getAllByUser(int userId);
    List<Category> searchByUser(int userId, String keyword);
}
