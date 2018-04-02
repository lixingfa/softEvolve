/**
 * 
 */
package softEvolve.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * @author lixingfa
 * @date 2018年4月2日上午11:40:18
 * 
 */
public class MainFrame extends JFrame{

	JTabbedPane tab = new JTabbedPane();// 存放选项卡的组件	
	
	/**系统配置*/
//	PropConfig propConfig = null;
	
	/**面板***************************************************************/
	private void initTab(){
		tab.addTab("繁衍", new MultiplyFrame());	
		add(tab);
		initPro();
	}
	
	/**初始化*****************************************************************/
	private void initPro(){
		//初始化配置类
		try {
//			propConfig = new PropConfig(sqlDataJPanel.propPath.getText());//不合理的写法
			//空库面板
//			dataBaseJPanel.setPropConfig(propConfig);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "初始化配置文件发生错误。\n错误信息：" + e.getMessage()
					,"发生错误",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public MainFrame(){
		super("软件进化平台 1.0");
		int WIDTH = 860;
		int HEIGHT = 650;
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//禁止改变大小
//		setIconImage(new ImageIcon(getClass().getResource("mingdong.png")).getImage());
		//设置窗口的位置
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();  //系统对象获取工具
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width - WIDTH)/2;
		int y=(height - HEIGHT)/2;
		this.setLocation(x,y);//居中显示
		//关闭时也关闭数据库链接
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
			}
		});		
		//tab组件
		initTab();
		setVisible(true);// 设置窗体可见
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
