package softEvolve.compile.gen;

import java.util.Map;

public class MainGen {

	/**编译主基因
	 * @author lixingfa
     * @date 2018年4月11日下午9:32:21
	 * @param gens 基因字符串
	 * @param path 路径
	 * @return String
	 * @throws Exception
	 */
	public static String compile(String gens,String path,String fatherId,String motherId) throws Exception{
		char[] gen = gens.toCharArray();
		//第一位处理
		switch (gen[0]) {
			case '0':java(gen[1],gen[2]);break;
			default:break;
		}
		return null;
	}
	
	/**
	 * @param jdk
	 * @param projectType
	 * @return
	 */
	private static String java(char jdk,char projectType){
		
		return null;
	}
	
	private static String buildJavaProject(String jdk,String jdkPath,String path,String fatherId,String motherId){
		String id = GenCompile.getIdInfo(prefix, fatherId, motherId);
		return null;
	}
}
