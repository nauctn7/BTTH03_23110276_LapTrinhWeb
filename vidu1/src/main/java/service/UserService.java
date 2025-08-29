package service;

import models.User;

public interface UserService {
	User login(String username, String password);

    // Đăng ký theo tham số rời (bạn đang dùng)
    boolean register(String username, String password, String email, String fullname, String phone);

    // Chèn trực tiếp 1 user đã tạo sẵn — TRẢ VỀ boolean để biết có chèn thành công
    boolean insert(User user);

    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);

    User findByUserName(String username);
    User get(String username);  
 // NEW
    boolean updatePasswordByEmail(String email, String newPassword);
	}

