package service;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;

public class UserServiceImpl implements UserService {
	  private final UserDao userDao = new UserDaoImpl();

	  @Override
	    public User login(String username, String password) {
	        User user = this.findByUserName(username);
	        if (user != null && password.equals(user.getPassWord())) { // So sánh plaintext (khuyến nghị dùng BCrypt)
	            return user;
	        }
	        return null;
	    }

	    @Override
	    public boolean register(String username, String password, String email, String fullname, String phone) {
	        // Chặn trùng username (có thể chặn thêm email nếu muốn)
	        if (userDao.checkExistUsername(username)) return false;

	        User u = new User();
	        u.setEmail(email);
	        u.setUserName(username);
	        u.setFullName(fullname);
	        u.setPassWord(password);
	        u.setRoleid(5);    // Role mặc định (đảm bảo tồn tại nếu có FK)
	        u.setPhone(phone);

	        boolean ok = userDao.insert(u); // DAO.insert phải trả về rowsAffected > 0
	        System.out.println("[UserServiceImpl] register result = " + ok);
	        return ok;
	    }

	    @Override
	    public boolean insert(User user) {
	        return userDao.insert(user);
	    }

	    @Override
	    public boolean checkExistEmail(String email) {
	        return userDao.checkExistEmail(email);
	    }

	    @Override
	    public boolean checkExistUsername(String username) {
	        return userDao.checkExistUsername(username);
	    }

	    @Override
	    public boolean checkExistPhone(String phone) {
	        return userDao.checkExistPhone(phone);
	    }

	    @Override
	    public User findByUserName(String username) {
	        return userDao.findByUserName(username);
	    }

	    @Override
	    public User get(String username) {
	        // Có thể tái sử dụng findByUserName để giữ đồng nhất
	        return userDao.findByUserName(username);
	    }
	    @Override
	    public boolean updatePasswordByEmail(String email, String newPassword) {
	        // Plaintext để tương thích hiện tại. (Xem mục 10 để dùng BCrypt)
	        return userDao.updatePasswordByEmail(email, newPassword);
	    }
	}