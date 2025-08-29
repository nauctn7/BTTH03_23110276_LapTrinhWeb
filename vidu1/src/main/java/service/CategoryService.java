package service;

import java.util.List;
import models.Category;

public interface CategoryService {
    boolean insert(Category c);
    boolean update(Category c);
    boolean delete(int cateId, int userId);
    Category get(int cateId, int userId);
    List<Category> getAllByUser(int userId);
    List<Category> searchByUser(int userId, String keyword);
}
