/**
 * 
 */
package softEvolve.frame;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

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
	JButton testgoalButton = new JButton("测试连接");
	JButton analyze = new JButton("缺漏分析与补齐");
	JTextArea empMsgArea = new JTextArea(28,76);

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
		centerPanel.add(testgoalButton);
		centerPanel.add(analyze);
		analyze.setEnabled(false);
		add(BorderLayout.CENTER,centerPanel);
		//底部
		JPanel southPane = new JPanel();
		empMsgArea.setLineWrap(true);//换行
		empMsgArea.setWrapStyleWord(true);
		empMsgArea
		.setText("本功能用于在war包更新时，自动补齐数据库缺少的表和字段。1.4版新增。"
				+ "\n1、要求war包是基于mybaties架构开发的。"
				+ "\n2、项目war包地址选择到war包文件，SQL脚本地址选择到脚本所在的文件夹。"
				+ "\n3、先点击测试连接，确保数据库通畅；再点击执行，找出当前数据库要更新这个war包还欠缺哪些表或字段，然后程序会从脚本文件里找到对应的脚本，自动补全。"
				+ "\n4、与搭建空库不同的是，这里会先全部读取提供的SQL脚本。如果脚本有问题，工具会停下，并打印出错的SQL和所在的文件路径。\n查找有问题的脚本，修改正确后再次点击“缺漏分析与补齐”按钮即可。"
				+ "\n5、如果是因为读取文件出现乱码造成的错误，找到对应的文件，用文本打开，然后文件-另存为-编码那里选UTF-8，直接覆盖原来的文件，重新点击“缺漏分析与补齐”按钮即可。"
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
		analyze.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
//				initsourConn();//先检测数据库链接
//				if (sourConn != null) {
//					analyze();					
//				}
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
		testgoalButton.addActionListener(new ActionListener() {			
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
