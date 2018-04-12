package softEvolve.compile.gen;

import java.io.File;
import java.util.Map;

import common.constant.SysConstant;
import softEvolve.compile.project.Project;
import softEvolve.util.FileUtil;

public class MainGen {

	/**编译主基因
	 * @author lixingfa
     * @date 2018年4月11日下午9:32:21
	 * @param gens 基因字符串
	 * @param path 路径
	 * @return String
	 * @throws Exception
	 */
	public static String compile(String gens,String path) throws Exception{
		char[] gen = gens.toCharArray();
		return Project.init(gen[0], gen[1], gen[2], gen[3], gen[4],path);
	}
	
	
}
