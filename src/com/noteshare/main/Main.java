package com.noteshare.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.noteshare.utils.Config;

/**
 * @ClassName			: Main 
 * @Description			: 程序入口类
 * @author 				： NoteShare 
 * @date 				： 2016年12月11日 下午10:15:10
 */
public class Main {

	public static void main(String[] args) throws Exception {
		/**====================================窗口的绘制=============================start==*/
		// 创建jframe
		JFrame frame = new JFrame("FrameDemo");
		frame.setResizable(false);
		frame.setTitle("抓取中国天气数据");
		frame.setBounds(400, 400, 400, 140);
		// 创建jpanel
		JPanel mainJpanel = new JPanel();
		// 采用绝对布局
		mainJpanel.setLayout(new BorderLayout());
		frame.setContentPane(mainJpanel);
		//==========================窗口头部表单区=================start
		//==========================窗口中部说明区=================start
		JPanel centerJPanel = new JPanel();
		Label desLabel = new Label("You can click on the set button below to set the task time interval,");
		Label desLabel2 = new Label(" the interval of the default time of 1 minute.                       ");
		centerJPanel.add(desLabel);
		centerJPanel.add(desLabel2);
		mainJpanel.add(centerJPanel, BorderLayout.CENTER);
		//==========================窗口中部说明区=================end
		//==========================窗口底部按钮区==================start
		//创建按钮面板
		JPanel soutchJpanel = new JPanel();
		soutchJpanel.setLayout(new FlowLayout());
		// 创建开始按钮
		final JButton startButton = new JButton("开始");
		startButton.setSize(60, 30);
		startButton.setVisible(true);
		soutchJpanel.add(startButton);
		//创建结束按钮
		final JButton endButton = new JButton("停止");
		endButton.setSize(60,30);
		soutchJpanel.add(endButton);
		endButton.setVisible(false);
		//创建间隔时间设置按钮
		final JButton setButton = new JButton("设置");
		setButton.setSize(60, 30);
		soutchJpanel.add(setButton);
		mainJpanel.add(soutchJpanel,BorderLayout.PAGE_END);
		//这个要放在后面，会触发所有可见组件的 paint方法
		frame.setVisible(true);
		/**====================================窗口的绘制=============================end==*/
		/**====================================业务处理=============================start==*/
		//定义定时器
		final Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00
		//定义任务
		final Task task = new Task();
		Config config = new Config("properties.properties");
		//默认间隔时间
		int defaultperiod = 6000;
		try{
			defaultperiod = config.parseInt("defaultperiod");
			if(0 == defaultperiod){
				defaultperiod = 6000;
			}
		}catch(Exception e){}
		timer.schedule(task, time, defaultperiod);
		/**====================================业务处理=============================end==*/
		/**===========================================事件定义==========================start=======*/
		/**
		 * 单击执行按钮开始获取数据
		 */
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startButton.setVisible(false);
				endButton.setVisible(true);
				task.setFlag(true);
			}
		});
		
		/**
		 * 结束按钮监听鼠标点击事件
		 */
		endButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startButton.setVisible(true);
				endButton.setVisible(false);
				task.setFlag(false);
			}
		});
		/**
		 * 设置按钮鼠标单击事件
		 */
		setButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] obj2 ={ "1分钟", "2分钟", "5分钟","10分钟","20分钟"};  
				String value = (String) JOptionPane.showInputDialog(null,"请选择任务间隔时间单位为分钟:\n", "间隔时间", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj2, "1分钟");
				if(null != value){
					value = value.replace("分钟", "");
					long period = Integer.valueOf(value) * 60 * 1000;
					task.setPeriod(period);
				}
			}
		});
		
		/**
		 * 窗口关闭事件监听
		 */
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
            	//销毁定时器
                System.exit(0);	
            }
        });
		/**===========================================事件定义==========================end=======*/
	}
}
