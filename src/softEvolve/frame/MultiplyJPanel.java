/**
 * 
 */
package softEvolve.frame;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import common.constant.SysConstant;
import softEvolve.compile.gen.GenCompile;
import softEvolve.gen.Multiply;
import softEvolve.util.FileUtil;
import softEvolve.util.SWingUtil;
/**
 * @author lixingfa
 * @date 2018年4月2日上午11:42:41
 * 
 */
public class MultiplyJPanel extends JPanel{
	private static final Logger log = Logger.getLogger(MultiplyJPanel.class);
	
	JTextField fatherPath = new JTextField(24);//
	JButton selectFatherPathButton = new JButton("选择");
	JTextField motherPath = new JTextField(24);//
	JButton selectMotherPathButton = new JButton("选择");
	JTextField sonPath = new JTextField(24);//
	JButton selectSonPathButton = new JButton("选择");
	
//	JTextField goalAddr = new JTextField(20);//
	JTextField goalUser = new JTextField(10);
	JPasswordField goalPw = new JPasswordField(10);
	JButton multiply = new JButton("繁衍");
	JTextArea empMsgArea = new JTextArea(33,76);

	
	private void multiply() throws Exception{
		//1、<基因的名称，基因的内容>
		String father = fatherPath.getText();
		String mother = motherPath.getText();
		if (StringUtils.isBlank(father)) {
			append("请选择父软件。");//放着先进的有性生殖不用而去考虑原始的单体繁殖，简直有病
			return;
		}
		if (StringUtils.isBlank(mother)) {
			append("请选择母软件。");//放着先进的有性生殖不用而去考虑原始的单体繁殖，简直有病
			return;
		}
		if (StringUtils.isBlank(sonPath.getText())) {
			append("请选择子代路径。");
			return;
		}
		Map<String, String> fatherGens = FileUtil.getDirectoryContent(father,1);
		Map<String, String> motherGens = FileUtil.getDirectoryContent(mother,1);
		if (fatherGens.isEmpty() || !fatherGens.containsKey(SysConstant.ID)) {
			append("父软件不是包含基因组的软件。");
			return;
		}
		if (motherGens.isEmpty() || !motherGens.containsKey(SysConstant.ID)) {
			append("母软件不是包含基因组的软件。");
			return;
		}
		append("获取父母双方基因完成。");
		//2、繁衍，双倍体一次可以得到四个后代
		//父母双方各翻倍、染色体部分交换、然后连续分裂两次，得到4个生殖细胞。再组成4个完整的原始细胞。
		Map<String, String>[] sons;
		try {
			sons = Multiply.multiply(fatherGens, motherGens);
		} catch (Exception e) {
			append("繁衍时发送错。father：" + father + "，mother:" + mother);
			append(e.getMessage());
			return;
		}
		append("子代4个原始细胞组合完成。");
		//3、根据需求重组子代基因
		append("根据需求重组子代基因。");
		
		append("重组子代基因完成。");
		//4、基因编译成源码		
		append("基因编码。");
		for (int i = 0; i < sons.length; i++) {
			try {
				GenCompile.compile(sons[i], sonPath.getText());				
			} catch (Exception e) {
				append("编译第" + i + "个子代时发生错误。错误信息：" + e.getMessage());
			}
		}
		append("基因编码完成。");
		//5、源码编译成可运行的机器码
		append("源码编译成可运行的机器码。");
		
		append("机器码编译完成。");
	}
	
	
	
	/**界面处理******************************************************************/
	public MultiplyJPanel() {		
		setLayout(new BorderLayout());//北、南、东、西、中布局
		//
		JPanel northPane = new JPanel();
		northPane.setLayout(new GridBagLayout());//流布局
		northPane.add(new JLabel("父软件地址："));
		northPane.add(fatherPath);
		northPane.add(selectFatherPathButton);
		northPane.add(new JLabel("母软件地址："));
		northPane.add(motherPath);
		northPane.add(selectMotherPathButton);		
		add(BorderLayout.NORTH,northPane);
		//中部
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());//流布局
		centerPanel.add(new JLabel("产生子代的地址："));
		centerPanel.add(sonPath);
		centerPanel.add(selectSonPathButton);
//		centerPanel.add(new JLabel("数据库地址："));
//		centerPanel.add(goalAddr);
//		centerPanel.add(new JLabel(" 用户名："));
//		centerPanel.add(goalUser);
//		centerPanel.add(new JLabel(" 密码："));
//		centerPanel.add(goalPw);
		centerPanel.add(multiply);
//		multiply.setEnabled(false);
		add(BorderLayout.CENTER,centerPanel);
		//底部
		JPanel southPane = new JPanel();
		empMsgArea.setLineWrap(true);//换行
		empMsgArea.setWrapStyleWord(true);
		empMsgArea
		.setText("通过模仿生物进化的功能，增强自主适应需求的能力。\n"
				+"让人寻求改变的是困境，让人不断前进的是梦想。\n"
				+"与创新相比，改变现状更难。\n"
				+"个性化的小需求，是市场的主流。因为高峰永远就那些而已。\n"
				);
		southPane.add(new JScrollPane(empMsgArea));
		add(BorderLayout.SOUTH,southPane);
		//
		initListener();
	}
	
	/**事件*************************************************************/
	private void initListener(){
		//分析缺漏，并补齐
		multiply.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					multiply();
				} catch (Exception e1) {
					append("发生错误：" + e1.getMessage());
				}
			}
		});
		
		//选择war目录
		selectFatherPathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				SWingUtil.chooser(fatherPath);
			}
		});
		
		selectMotherPathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				SWingUtil.chooser(motherPath);
			}
		});
		
		selectSonPathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				SWingUtil.chooser(sonPath);
			}
		});
	}
	
	private void append(String s){
		empMsgArea.append(s + "\n");
		empMsgArea.paintAll(empMsgArea.getGraphics());
	}
}
