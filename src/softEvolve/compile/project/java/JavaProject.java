/**
 * 
 */
package softEvolve.compile.project.java;

import java.io.File;
import java.util.Properties;

import softEvolve.compile.gen.GenCompile;
import softEvolve.util.FileUtil;

/**
 * @author lixingfa
 * @date 2018年4月12日下午5:42:58
 * 
 */
public class JavaProject {	
	protected static String JDKVersions;
	
	protected static String JDKPath;
	
	protected static Properties prop;
	
	/**
	 * 
	 * init:(这里用一句话描述这个方法的作用)
	 * @author lixingfa
	 * @date 2018年4月12日下午5:54:11
	 * @param projectType
	 * @param view
	 * @param store
	 * @return
	 */
	public static String init(char languageVersions,char projectType,char view,char store,String path) throws Exception{
//		prop = //properties/JAVA文件夹下的配置
		switch (languageVersions) {//JDK版本
			case '0':JDKVersions = "1.6";break;
			case '1':JDKVersions = "1.7";break;
			case '2':JDKVersions = "1.8";break;
			case '3':JDKVersions = "9";break;
			case '4':JDKVersions = "10";break;
			default:JDKVersions = "1.6";break;
		}
//		JDKPath = prop.getProperty("JDK" + JDKVersions);
		switch (projectType) {//项目类型
			case '0':return initJavaProject(languageVersions,view, store,path);
	
			default:return null;
		}
	}
	
	/**
	 * initJavaProject:(初始化java工程)
	 * @author lixingfa
	 * @date 2018年4月12日下午6:16:26
	 * @param languageVersions
	 * @param view
	 * @param store
	 * @return String 工程路径
	 */
	private static String initJavaProject(char languageVersions,char view,char store,String path) throws Exception{
		String id = GenCompile.getIdInfo("JavaProject0" + languageVersions + "0" + view + store);
		File project = new File(path + "/" + id + "/");
		if (!project.exists()) {//不存在
			if (!project.mkdir()) {
				throw new Exception(project.getPath() + "创建不成功\n");
			}
		}else {//清空文件夹
			FileUtil.FileDelete(project);			
		}
		
		return project.getPath();
	}
}
