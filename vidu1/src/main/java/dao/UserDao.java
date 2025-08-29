package dao;

import models.User;

public interface UserDao {
	User findByUserName(String username);
    boolean insert(User user);                 // <— đổi từ void sang boolean
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    User get(String username);
    // NEW
    boolean updatePasswordByEmail(String email, String newPassword);

	 
}
