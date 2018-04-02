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

import org.apache.log4j.Logger;

import softEvolve.util.FileUtil;
import softEvolve.util.SWingUtil;
/**
 * @author lixingfa
 * @date 2018年4月2日上午11:42:41
 * 
 */
public class MultiplyFrame extends JPanel{
	private static final Logger log = Logger.getLogger(MultiplyFrame.class);
	
	JTextField fatherPath = new JTextField(24);//项目源码地址
	JButton selectfatherPathButton = new JButton("选择");
	JTextField motherPath = new JTextField(24);//SQL脚本地址
	JButton selectmotherPathButton = new JButton("选择");
	JTextField goalAddr = new JTextField(20);//源数据库地址
	JTextField goalUser = new JTextField(10);
	JPasswordField goalPw = new JPasswordField(10);
	JButton requirement = new JButton("需求变更录入");
	JButton multiply = new JButton("繁衍");
	JTextArea empMsgArea = new JTextArea(28,76);

	
	private void multiply() throws Exception{
		//1、<基因的名称，基因的内容>
		Map<String, String> fatherGens = FileUtil.getDirectoryContent(fatherPath.getText());
		Map<String, String> motherGens = FileUtil.getDirectoryContent(motherPath.getText());
		//2、繁衍，双倍体一次可以得到四个后代
		
	}
	
	
	
	/**界面处理******************************************************************/
	public MultiplyFrame() {		
		setLayout(new BorderLayout());//北、南、东、西、中布局
		//
		JPanel northPane = new JPanel();
		northPane.setLayout(new GridBagLayout());//流布局
		northPane.add(new JLabel("父软件地址："));
		northPane.add(fatherPath);
		northPane.add(selectfatherPathButton);
		northPane.add(new JLabel("母软件地址："));
		northPane.add(motherPath);
		northPane.add(selectmotherPathButton);		
		add(BorderLayout.NORTH,northPane);
		//中部
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());//流布局
		centerPanel.add(new JLabel("数据库地址："));
		centerPanel.add(goalAddr);
		centerPanel.add(new JLabel(" 用户名："));
		centerPanel.add(goalUser);
		centerPanel.add(new JLabel(" 密码："));
		centerPanel.add(goalPw);
		centerPanel.add(requirement);
		centerPanel.add(multiply);
		multiply.setEnabled(false);
		add(BorderLayout.CENTER,centerPanel);
		//底部
		JPanel southPane = new JPanel();
		empMsgArea.setLineWrap(true);//换行
		empMsgArea.setWrapStyleWord(true);
		empMsgArea
		.setText("本功能用于在war包更新时，自动补齐数据库缺少的表和字段。1.4版新增。"
				+ "\n6、本功能不包含初始化数据的执行。\n"
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
		selectfatherPathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				SWingUtil.chooser(fatherPath);
			}
		});
		
		selectmotherPathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				SWingUtil.chooser(motherPath);
			}
		});
		
		//测试数据库
		requirement.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
//				initsourConn();
			}
		});
	}
	
	private void append(String s){
		empMsgArea.append(s + "\n");
		empMsgArea.paintAll(empMsgArea.getGraphics());
	}
}
