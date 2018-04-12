/**
 * 
 */
package softEvolve.compile.project;

import softEvolve.compile.project.java.JavaProject;

/**
 * @author lixingfa
 * @date 2018年4月12日下午5:46:42
 * 
 */
public class Project {

	/**
	 * build:(根据条件初始化工程)
	 * @author lixingfa
	 * @date 2018年4月12日下午5:52:16
	 * @param language 语言
	 * @param languageVersions 语言版本
	 * @param projectType 项目类型
	 * @param View 界面类型
	 * @param store 存储类型
	 * @return 构建的项目路径
	 */
	public static String init(char language,char languageVersions,char projectType,char view,char store,String path) 
	throws Exception{
		switch (language) {
			case '0': return JavaProject.init(languageVersions, projectType,view,store,path);
			
			default:return null;
		}
	}
}
