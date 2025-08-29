package service;

import java.util.List;
import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import models.Category;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao dao = new CategoryDaoImpl();

    @Override public boolean insert(Category c) { return dao.insert(c); }
    @Override public boolean update(Category c) { return dao.update(c); }
    @Override public boolean delete(int cateId, int userId) { return dao.delete(cateId, userId); }
    @Override public Category get(int cateId, int userId) { return dao.get(cateId, userId); }
    @Override public List<Category> getAllByUser(int userId) { return dao.getAllByUser(userId); }
    @Override public List<Category> searchByUser(int userId, String keyword) { return dao.searchByUser(userId, keyword); }
}
