package util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	
	private final static Logger log = LoggerFactory.getLogger(StringUtil.class);
	
	public static String encodeString(String str,String decode,String encode){
		String result = null;
		try {
			result = new String(str.getBytes(decode),encode);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
}
