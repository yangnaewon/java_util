package naewon;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {
	
	/**
	 * 암호화 모듈
	 */
	private static Cipher cipher;
	
	private static String seed = "uA3bxszhJgCJ8+4ngajCvQ\\=\\=";
	
	/**
	 * IvParameterSpec seed
	 */
	private static final byte[] SEED = Base64.decode(seed);
	
	/**
	 * SecretKey 문자열
	 */
	private static final String SECRET_KEY = "bHRr9Fpl6zOR6QkP1t/QcA\\=\\=";
	
	private static SecretKey secretKey;
	
	
	static
	{
		try {
			EncryptUtil.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			secretKey = EncryptUtil.toSecretKey(SECRET_KEY);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] genSeed(int len)
	{
		SecureRandom random = new SecureRandom();
		return random.generateSeed(len);
	}
	
	public static SecretKey genSecretKey() throws NoSuchAlgorithmException
	{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom();
		keyGen.init(128, random);
		return keyGen.generateKey();
	}
	
	public static String genSecretKeyString() throws NoSuchAlgorithmException
	{
		SecretKey secretKey = EncryptUtil.genSecretKey();
		byte[] secretKeyBytes = secretKey.getEncoded();
		return Base64.encode(secretKeyBytes);
	}
	
	public static String toString(SecretKey secretKey) throws NoSuchAlgorithmException
	{
		byte[] secretKeyBytes = secretKey.getEncoded();
		return Base64.encode(secretKeyBytes);
	}
	
	public static SecretKey toSecretKey(String secretKeyStr)
	{
		byte[] secretKeyBytes = Base64.decode(secretKeyStr);
		return new SecretKeySpec(secretKeyBytes, "AES");
	}
	
	public static String encrypt(String rawData) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException
	{
		return EncryptUtil.encryptByAesCbcPkcs5Padding(rawData);
	}
	
	private static synchronized String encryptByAesCbcPkcs5Padding(String rawData) throws InvalidKeyException, InvalidAlgorithmParameterException,
	IllegalBlockSizeException, BadPaddingException
	{
		EncryptUtil.cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(SEED));
		byte[] encrypted = EncryptUtil.cipher.doFinal(rawData.getBytes());
		return Base64.encode(encrypted);	
	}
	
	public static String decrypt(String encodeData) throws InvalidKeyException, InvalidAlgorithmParameterException,
	IllegalBlockSizeException, BadPaddingException
	{
		return EncryptUtil.decryptByAesCbcPkcs5Padding(encodeData);
	}
	
	private static synchronized String decryptByAesCbcPkcs5Padding(String encodeData) throws InvalidKeyException, InvalidAlgorithmParameterException,
	IllegalBlockSizeException, BadPaddingException
	{
		byte[] encodeDataBytes = Base64.decode(encodeData);
		EncryptUtil.cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(SEED));
		byte[] returnBytes = EncryptUtil.cipher.doFinal(encodeDataBytes);
		return new String(returnBytes);			
	}
}
