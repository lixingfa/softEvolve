/**
 * 
 */
package softEvolve.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author lixingfa
 * @date 2018年4月2日上午11:50:16
 * 
 */
public class SWingUtil {

	/**文件选择器*/
	public static void chooser(JTextField textField){
		JFileChooser jfc = new JFileChooser(textField.getText());
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		jfc.showDialog(new JLabel(), "选择");
		File file=jfc.getSelectedFile();
		if(file != null){
			textField.setText(file.getAbsolutePath());
		}
	}
}
