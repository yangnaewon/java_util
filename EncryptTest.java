package naewon;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class EncryptTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("##### Encrypt Test Start");
		String testStr = "ABCD1234가나다라";
		
		String encStr = "";
		
		try {
			encStr = EncryptUtil.encrypt(testStr);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("##### Encoding String=[" + encStr + "]");
		
		String decStr = "";
		
		try {
			decStr = EncryptUtil.decrypt(encStr);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("##### Decoding String=[" + decStr + "]");
		
		System.out.println("##### Encrypt Test End");		
	}

}
