package naewon;

import javax.xml.bind.DatatypeConverter;

/**
 * <pre>
 * Base64 관련 클래스
 * 
 * byte형 배열을 받아 Base64 인코딩 기능
 * Base64형식으로 인코딩된 문자열을 byte 배열로 디코딩 기능
 * </pre>
 * @path naewon
 * @file Base64.java
 * @author yangnaewon
 * @date 2016.11.02
 *
 */
public class Base64 {

	/**
	 * <pre>
	 * byte 배열을 입력받아 Base64 인코딩된 문자열 반환
	 * </pre>
	 * @param data 인코딩 될 byte배열 데이터
	 * @return str Base64형식으로 인코딩된 문자
	 */
	public static String encode(byte[] data) {
		String str = DatatypeConverter.printBase64Binary(data);
		return str;
	}

	/**
	 * <pre>
	 * Base64 인코딩된 문자열을 입력받아 디코딩된 byte 배열로 반환
	 * </pre>
	 * @param encodeStr 인코딩된 문자열
	 * @return 디코딩된 byte형 배열
	 */
	public static byte[] decode(String encodeStr) {
		return DatatypeConverter.parseBase64Binary(encodeStr);
	}
}
