package com.aikshika.common;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtil {
	private static final Random RANDOM = new SecureRandom();
	/** Length of password. @see #generateRandomPassword() */
	public static final int PASSWORD_LENGTH = 8;

	/**
	 * Generate a random String suitable for use as a temporary password.
	 *
	 * @return String suitable for use as a temporary password
	 * @since 2.4
	 */
	public static String generateRandomPassword() {
		// Pick from some letters that won't be easily mistaken for each
		// other. So, for example, omit o O and 0, 1 l and L.
		String letters = "1234567890";

		String pw = "";
		for (int i = 0; i < PASSWORD_LENGTH/2; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			pw += letters.substring(index, index + 1);
		}
		return pw;
	}
	public static String generateOTP()
	{
		String pw="";
		String otp="1234567890";
		for (int i = 0; i < PASSWORD_LENGTH/2; i++) {
			int index = (int) (RANDOM.nextDouble() * otp.length());
			pw += otp.substring(index, index + 1);
		}
		return pw;
	}


}
