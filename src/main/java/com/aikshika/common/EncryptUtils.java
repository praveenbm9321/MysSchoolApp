package com.aikshika.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtils {
	
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	static BASE64Encoder enc = new BASE64Encoder();
	static BASE64Decoder dec = new BASE64Decoder();

	public static String base64encode(String text) {
		try {
			String rez = enc.encode(text.getBytes(DEFAULT_ENCODING));
			return rez;
			
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}// base64encode

	public static String base64decode(String text) {

		try {
			return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
		} catch (IOException e) {
			return null;
		}

	}// base64decode

	

	public static String xorMessage(String message, String key) {
		try {
			if (message == null || key == null)
				return null;

			char[] keys = key.toCharArray();
			char[] mesg = message.toCharArray();

			int ml = mesg.length;
			int kl = keys.length;
			char[] newmsg = new char[ml];

			for (int i = 0; i < ml; i++) {
				newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
			}// for i
			mesg = null;
			keys = null;
			return new String(newmsg);
		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		String message = EncryptUtils.xorMessage("Arshiya","1234");
		String encrypt = EncryptUtils.base64encode("Arshiya");
		System.out.println("Encrypted Text:"+encrypt);
		
		String decrypt = EncryptUtils.base64decode(encrypt);
		System.out.println("Decrypted Text:"+decrypt);
	}
}
