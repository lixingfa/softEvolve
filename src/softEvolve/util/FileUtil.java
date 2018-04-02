/**
 * 
 */
package softEvolve.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lixingfa
 * @date 2018年4月2日下午4:25:46
 * 
 */
public class FileUtil {
	/**文件内容列表<行列表>*/
	private static ArrayList<ArrayList<String>> fileContents = new ArrayList<ArrayList<String>>();
	
	/**
	 * 获取某个文件夹下所有文件及其子文件夹下所有文件的内容
	 * @param pagePath 文件（夹）路径
	 * @return 文件内容列表<行列表>
	 */
	public static ArrayList<ArrayList<String>> getFileContents(String pagePath){
		File pageFile = new File(pagePath);
		if (pageFile.isDirectory()) {//是目录
			System.out.println("\t目录"+pageFile.getName());
			File[] subFiles = pageFile.listFiles();
			for (int i = 0; i < subFiles.length; i++) {
				getFileContents(subFiles[i].getPath());
			}
		}else {
			try {
				fileContents.add(readFileByLines(pagePath));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("读取"+pageFile.getName()+"的内容");			
		}
		return fileContents;
	}
	
	/**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            FileUtil.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /** 以行为单位读取文件，常用于读面向行的格式化文件
     * 
     * @param fileName
     * @return 内容列表，每个值代表一行
     */
    public static ArrayList<String> readFileByLines(String fileName) throws Exception{
    	ArrayList<String> arrayList = new ArrayList<String>();
        BufferedReader reader = null;
        int line = 1;
        try {
        	String charset = codeString(fileName);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.forName(charset)));//gbk转utf-8
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	line++;
            	if (tempString.contains("--")) {//处理注释
            		String[] real = tempString.split("--");
            		arrayList.add(real[0]);
				}else {
					arrayList.add(tempString);					
				}
            }
            reader.close();
        } catch (IOException e) {
        	throw new Exception(fileName + "第" + line + "读取错误");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
	                } catch (IOException e1) {
	                }
            }
        }
        return arrayList;
    }

    /** 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param fileName 文件
     * @return 内容列表，每个值代表一行
     */
    public static ArrayList<String> readFileLines(String fileName) throws Exception{
        ArrayList<String> arrayList = new ArrayList<String>();
        BufferedReader reader = null;
        int line = 1;
        try {
            String charset = codeString(fileName);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.forName(charset)));//gbk转utf-8
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                line++;
                arrayList.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            throw new Exception(fileName + "第" + line + "读取错误");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return arrayList;
    }

    /** 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param file 文件
     * @return 内容列表，每个值代表一行
     */
    public static ArrayList<String> readFileLines(File file) throws Exception{
        return readFileLines(file.getAbsolutePath());
    }

    /** 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param fileName
     * @return 文件内容
     */
    public static String getFileString(String fileName) {
        StringBuffer sBuffer = new StringBuffer();
        BufferedReader reader = null;
        int line = 1;
        try {
            //reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.forName("GBK")));//gbk转utf-8
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sBuffer.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("读取第" + line + "发生错误");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sBuffer.toString();
    }

    /**
     * getDirectoryContent:(获取某个文件或文件夹下的内容，不包含子文件夹的，以文件、文件内容字符串的形式返回)
     * @author lixingfa
     * @date 2018年4月2日下午4:56:21
     * @param dirPath
     * @return Map<String, String>
     */
    public static Map<String, String> getDirectoryContent(String dirPath){
    	Map<String, String> map = new HashMap<String, String>();
    	File file = new File(dirPath);
    	if (file.isDirectory()) {//是目录
			File[] subFiles = file.listFiles();
			for (int i = 0; i < subFiles.length; i++) {
				String path = subFiles[0].getAbsolutePath();
				map.put(path, getFileString(path));
			}
		}else {
			map.put(dirPath, getFileString(dirPath));
		}
    	return map;
    }
    
    /**
     * 随机读取文件内容
     */
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 往文件里写入内容，建议是txt格式
     * @param content
     * @param filePath
     * @return
     * @throws IOException
     */
    public static boolean writeTxtFile(String content,String filePath) throws IOException {
        boolean flag = false;
        String filein = content + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
            	file.createNewFile();
            }
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }
    /**
     * 文件删除
     * @param file 文件或文件夹
     */
    public static void FileDelete(File file){
    	if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			for (int i = 0; i < subFiles.length; i++) {
				FileDelete(subFiles[i]);
			}
		}else {
			file.delete();
		}
    }
    
	/**
	 * 判断文件的编码格式
	 * @param fileName :file
	 * @return 文件编码格式
	 * @throws Exception
	 */
	public static String codeString(String fileName) throws Exception{
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
		int p = (bin.read() << 8) + bin.read();
		String code = null;		
		switch (p) {
			case 0xefbb:
				code = "UTF-8";
				break;
			case 0xfffe:
				code = "Unicode";
				break;
			case 0xfeff:
				code = "UTF-16BE";
				break;
			default:
				code = "GBK";
		}
		bin.close();
		return code;
	}
}
