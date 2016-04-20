package com.edinnova.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class StringUtil {
	public static Boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static String getMD5(String str) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(str.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (Exception e) {
			return str;
		}
	}
	
	public static String token() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public static String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String checksum(String s) {
		String checksum = "";
		try {
			checksum = org.apache.commons.codec.digest.DigestUtils.shaHex(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checksum;
	}

	public static String getRandomPassword() {
		final int MINLENGTHOFPASSWORD = 8;
		StringBuilder password = new StringBuilder();
		int j = 0;
		for (int i = 0; i < MINLENGTHOFPASSWORD; i++) {
			password.append(getRandomPasswordCharacters(j));
			j++;
			if (j == 2) {
				j = 0;
			}
		}
		return password.toString();
	}

	static String getRandomPasswordCharacters(int pos) {
		final String NUMBERS = "0123456789";
		final String UPPER_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String LOWER_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
		Random randomNum = new Random();
		StringBuilder randomChar = new StringBuilder();
		switch (pos) {
		case 0:
			randomChar.append(NUMBERS.charAt(randomNum.nextInt(NUMBERS.length() - 1)));
			break;
		case 1:
			randomChar.append(UPPER_ALPHABETS.charAt(randomNum.nextInt(UPPER_ALPHABETS.length() - 1)));
			break;
		case 2:
			randomChar.append(LOWER_ALPHABETS.charAt(randomNum.nextInt(LOWER_ALPHABETS.length() - 1)));
			break;
		}
		return randomChar.toString();
	}
	
	public static String encodeFileToBase64Binary(String fileName) throws Exception {
		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		String encodedString = Base64.encodeBase64String(bytes);
		return encodedString;
	}
	
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        throw new IOException("file is too large");
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}
}
