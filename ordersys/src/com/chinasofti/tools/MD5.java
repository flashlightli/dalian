package com.chinasofti.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String md5(String str) {
		if(str==null){
			return "";
		}
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(str.getBytes("UTF8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte s[] = m.digest();
		StringBuilder result = new StringBuilder("");
		for (int i = 0; i < s.length; i++) {
			result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
		}
		return result.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(md5("1234"));
	}

}
