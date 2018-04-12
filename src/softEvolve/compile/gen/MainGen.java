package softEvolve.compile.gen;

import java.io.File;
import java.util.Map;

import common.constant.SysConstant;

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
	
	/**
	 * buildJavaProject:(建立JavaProject工程)
	 * @author lixingfa
	 * @date 2018年4月12日下午12:16:37
	 * @param prefix
	 * @param fatherId
	 * @param motherId
	 * @return
	 */
	private static String buildJavaProject(String path,String prefix,String fatherId,String motherId)
			throws Exception{
		//1、获取项目的id，也是项目名称
		String id = GenCompile.getIdInfo(prefix);
		File project = new File(path + "/" + id + "/");
		if (!project.exists()) {//不存在
			if (!project.mkdir()) {
				throw new Exception(project.getPath() + "创建不成功\n");
			}
		}else {//清空文件夹
			FileUtil.FileDelete(project);			
		}
		//2、写入身份信息和基因
		File identity = new File(project.getPath() + "/" + SysConstant.IDENTITY + "/");
		identity.mkdir();
		File idFile = new File(identity.getPath() + "/" + SysConstant.ID + ".txt");
		StringBuffer idTxt = new StringBuffer(SysConstant.ID);
		idTxt.append("=").append(id).append("\r\n");
		idTxt.append(SysConstant.FATHER).append("=").append(fatherId).append("\r\n");
		idTxt.append(SysConstant.MOTHER).append("=").append(motherId).append("\r\n");
		FileUtil.writeTxtFile(idTxt.toString(), idFile.getPath());
		File gen = new File(project.getPath() + "/" + SysConstant.GEN + "/");
		gen.mkdir();
		
		return null;
	}
}
