/**
 * 
 */
package softEvolve.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * @author lixingfa
 * @date 2018年4月4日下午2:51:13
 * 
 */
public class Util {
	private static final Logger log = Logger.getLogger(Util.class);

	public static Long IdToLong(String id){
		Long total = 0L;
		if (id == null || "".equals(id)) {
			return total;
		}
		char[] c = id.toCharArray();
		//A-Z 65-90
		int length = c.length - 1;
		for (int i = 0; i <= length; i++) {
			double d = Math.pow(10, length - i - 1) * 36;
			if (i == length) {
				d = 1;
			}
			if (c[i] > '9') {
				total = total + (c[i] - 'A' + 11) * (long)d;//10是0-9的10，相减比实际多少了1
			}else {
				total = total + (c[i] - '0' + 1) * (long)d ;
			}
		}
		return total;
	}
	
//	public static void main(String[] args) {
//		System.out.println(IdToLong("100"));
//	}
	
}
