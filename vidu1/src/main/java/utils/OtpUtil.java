package utils;

import java.security.SecureRandom;

public class OtpUtil {
	private static final SecureRandom rnd = new SecureRandom();

    public static String gen6() {
        int n = 100000 + rnd.nextInt(900000); // 100000..999999
        return String.valueOf(n);
    }

}
