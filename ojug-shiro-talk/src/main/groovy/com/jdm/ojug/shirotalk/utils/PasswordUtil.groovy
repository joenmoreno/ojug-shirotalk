package com.jdm.ojug.shirotalk.utils

import org.apache.shiro.crypto.RandomNumberGenerator
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha256Hash

class PasswordUtil {

	Object generateSalt() {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator()
		Object salt = rng.nextBytes()
		return salt
	}

	String generatePassword(String plainTextPassword, Object salt) {
		return new Sha256Hash(plainTextPassword, salt, 1024).toBase64();
	};
}
