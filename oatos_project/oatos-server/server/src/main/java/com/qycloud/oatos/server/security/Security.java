package com.qycloud.oatos.server.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;

/**
 * 服务器端实现加密算法。
 * 
 * @author 郭天良
 */
public class Security {
	// token 分隔符
	private final static String SEPARATOR = "@";
	// 用户密码的截取点和偏移位数，从 point 位置，截取 offset 个字符，这样密码无法用 SHA256 字典进行猜测
	private final static int point = 25;
	private final static int offset = 4;
	// 服务器默认私钥
	private static String SERVER_KEY = "lal1uj3fe7bz0vlak1q5qcclt070jswp";
	// token 中密钥的长度
	private static int KeyLength = 24;
	// token 默认长度
	private static int DefaultLength = 64;

	// token 过期时间
	private static int HOLD_TIME = 172800000; // 2 * 24 * 60 * 60 * 1000;
	// token 干扰字符
	private static String HOLD_CHAR = "6";

	private final static int size = 256;
	// 加密用随机串，干扰加密结果，增加解密的复杂度
	private final byte sbox[] = new byte[size];
	private int i;
	private int j;

	/**
	 * 转换二进制码为十六进制码
	 * 
	 * @param s
	 *            String to be converted
	 * @return Hex equivalent of the input string
	 */
	public static String byteStringToHexString(final String s) {
		StringBuilder hexString = new StringBuilder(65);
		for (int i = 0; i < s.length(); i++) {
			hexString.append(byteToHexChars(s.charAt(i)));
		}
		return hexString.toString();
	}

	/**
	 * 转换一个二进制字符为十六进制字符
	 * 
	 * @param i
	 *            Number to be converted
	 * @return Hex equivalent, in two characters.
	 */
	private static String byteToHexChars(final int i) {
		final String s = "0" + Integer.toHexString(i);
		return s.substring(s.length() - 2);
	}

	/**
	 * 十六进制字符转换为二进制字符
	 * 
	 * @param s
	 * @return Original string
	 */
	public static String hexStringToByteString(final String s) {
		StringBuilder byteString = new StringBuilder(65);
		for (int i = 0, len = s.length(); i < len; i += 2) {
			byteString
					.append((char) Integer.parseInt(s.substring(i, i + 2), 16));
		}
		return byteString.toString();
	}

	public static String SHA256(final String text) {
		MessageDigest messageDigesSHA256 = null;
		try {
			messageDigesSHA256 = MessageDigest.getInstance("SHA-256");
		} catch (final NoSuchAlgorithmException ex) {
			System.out.println("ServerKey config error!");
			ex.printStackTrace();
		}
		messageDigesSHA256.update(text.getBytes());

		byte byteData[] = messageDigesSHA256.digest();

		StringBuilder hexString = new StringBuilder(65);
		for (int i = 0, len = byteData.length; i < len; i++) {
			hexString.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return hexString.toString();
	}

	public static String randomCharString() {
		return randomCharString(DefaultLength);
	}

	/**
	 * 得到一个指定长度的随机字符
	 * @param length
	 * @return
	 */
	public static String randomCharString(int length) {
		Random random = new Random();
		char[] radStr = "1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < length; i++) {
			randBuffer[i] = radStr[random.nextInt(36)];
		}

		return new String(randBuffer);
	}
	
	/**
	 * 解密
	 * @param plaintext
	 * @return
	 */
	private String codeDecode(final String plaintext) {
		byte x;
		StringBuilder mString = new StringBuilder(65);
		final int pl = plaintext.length();
		for (int k = 0; k < pl; k++) {
			i = i + 1 & 0xff;
			j = j + sbox[i] & 0xff;

			x = sbox[i];
			sbox[i] = sbox[j];
			sbox[j] = x;
			mString.append((char) (plaintext.charAt(k) ^ sbox[sbox[i] + sbox[j]
					& 0xff] & 0xff));
		}
		return mString.toString();
	}

	/**
	 * 用一个 Key，加密文本，对称加密
	 * 
	 * @param key
	 * @param plaintext
	 * @return
	 */
	public String codeDecode(final String key, final String plaintext) {

		setUp(key);
		return codeDecode(plaintext);
	}

	/**
	 * 设定 Key，用于加密
	 * 
	 * @param key
	 */
	private void setUp(final String key) {
		int k;
		byte x;

		for (i = 0; i < size; i++) {
			sbox[i] = (byte) i;
		}

		final int kl = key.length();
		for (i = 0, j = 0, k = 0; i < 256; i++) {
			j = j + sbox[i] + key.charAt(k) & 0xff;
			k = (k + 1) % kl;

			x = sbox[i];
			sbox[i] = sbox[j];
			sbox[j] = x;
		}

		i = 0;
		j = 0;
	}

	/**
	 * 生成用户的令牌
	 * 
	 * @param userKey
	 * @return
	 */
	public static String CreateToken(String userId) {
		// 生成一个密钥
		String key = randomCharString(KeyLength);
		// 令牌产生时间
		long date = Calendar.getInstance().getTimeInMillis();

		// 结合服务器端的密钥生成验证码
		String mdKey = SHA256(userId + key + SERVER_KEY).substring(0,
				DefaultLength - KeyLength);

		// 组合验证码和密钥
		String token = userId + SEPARATOR + mdKey + key + HOLD_CHAR + date;

		return token;
	}

	/**
	 * 验证用户令牌
	 * 
	 * @param userToken
	 * @return
	 */
	public static boolean CheckToken(String userToken) {
		int index = userToken.indexOf(SEPARATOR);
		String userId = userToken.substring(0, index);
		String token = userToken.substring(index + 1);

		// 检查令牌是否过期
		long createTime = Long.parseLong(token.substring(DefaultLength + 1));
		long currentTime = Calendar.getInstance().getTimeInMillis();
		if ((currentTime - createTime) > HOLD_TIME) {
			System.out.println("check token time expired, userToken:"
					+ userToken);
			return false;
		} else {
			token = token.substring(0, DefaultLength);
		}

		// 截取验证码
		String mdKey = token.substring(0, DefaultLength - KeyLength);
		// 截取密钥
		String key = token.substring(DefaultLength - KeyLength, DefaultLength);

		// 生成验证码
		String newKey = SHA256(userId + key + SERVER_KEY).substring(0,
				DefaultLength - KeyLength);

		// return mdKey.hashCode() == newKey.hashCode();
		// 验证令牌是否合法
		boolean check = mdKey.equals(newKey);
		if (!check) {
			System.out.println("check token error, userToken:" + userToken
					+ ", mdKey:" + mdKey + ", userId:" + userId + ", key:"
					+ key + ", newKey:" + newKey + ", token:" + token);
		}
		return check;
	}

	/**
	 * 生成用户密码验证串， 在用户的密码的密文中截取子串，使用用户无法通过 SHA,MD5 数据库字典猜出用户密码
	 * 
	 * @param mdPassword
	 * @return
	 */
	public static String CreatePassword(String mdPassword) {
		// 构造用户密码的验证串
		return mdPassword.substring(0, point)
				+ mdPassword.substring(point + offset);
	}

}
