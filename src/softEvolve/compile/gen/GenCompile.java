/**
 * 基因编码，将基因转化成具体的项目源码
 */
package softEvolve.compile.gen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import common.constant.SysConstant;

/**
 * @author lixingfa
 * @date 2018年4月11日上午10:36:43
 * 
 */
public class GenCompile {

	private Properties prop;
	/**
     * compile:(将基因组编译成源代码，生成工程放到指定路径下)
     * @author lixingfa
     * @date 2018年4月11日下午8:56:21
     * @param gens 基因组
     * @param path 路径
     * @return 
     */
	public static void compile(Map<String, String> gens,String path) throws Exception{
		//1、Main基因确定软件性质
		
		//2、
	}
	
	/**
	 * compile:(哪条基因将可以表达)
     * @author lixingfa
     * @date 2018年4月11日下午9:13:21
	 * @param father 父基因
	 * @param mother 母基因
	 * @return String
	 */
	private String theCompileGen(String father,String mother){
		char[] f = father.toCharArray();//TODO 有待优化
		Long total = 0L;
		for (int i = 0; i < 10; i++) {
			total = total + f[(int)(Math.random() * f.length)];
		}
		total = total % 2;
		if (total == 0) {
			return mother;
		}else {//再进行一轮竞争
			char[] m = mother.toCharArray();
			for (int i = 0; i < 10; i++) {
				total = total + m[(int)(Math.random() * f.length)];
			}
			total = total % 2;
			if (total == 0) {
				return mother;
			}else {
				return father;
			}
		}
	}
	
	/**
	 * 根据父母双方的id，获得子代的id
	 * @param prefix 六位前缀依次为使用的语言、语言版本、项目类型、界面、存储、
	 * @param fatherId 父id
	 * @param motherId 母id
	 * @return 完整的子代id文件中的信息
	 */
	public static String getIdInfo(String prefix,String fatherId,String motherId){
		StringBuffer id = new StringBuffer(SysConstant.ID);
		id.append("=");
		id.append(prefix);//前缀
		id.append(new SimpleDateFormat("yyyyMMddhhmmssSSSS").format(new Date()));//时间
		id.append("\r\n");
		id.append(SysConstant.FATHER).append("=").append(fatherId).append("\r\n");
		id.append(SysConstant.MOTHER).append("=").append(motherId).append("\r\n");
		return id.toString();
	}
}
