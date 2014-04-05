package zkl.dietitian.utils.base;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class StringUtils {
	
	/**
	 * 获得32位长度的UUID值
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获得64位长度的UUID值
	 * @return
	 */
	public static String getUUID64(){
		return getUUID() + getUUID();
	}
	
	
	/**
	 * 获得MD5加密内容
	 * @param value 原文
	 * @return 密文
	 */
	public static String getMD5Value(String value){ // 123
		try {
			// 获得加密对象
			MessageDigest messageDigest = MessageDigest.getInstance("mD5");
			
			byte[] bytes = messageDigest.digest(value.getBytes());
			
			//处理数据，将十进制变成16进制
			BigInteger bigInteger = new BigInteger(1, bytes);
			return bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return value;
	}

}
