package com.noteshare.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jsoup.nodes.Document;

import com.noteshare.spider.chinasky.services.RealTimeService;
import com.noteshare.spider.chinasky.services.impl.RealTimeServiceImpl;
import com.noteshare.spider.common.beans.RequestParams;
import com.noteshare.spider.common.util.SpiderUtil;

import net.sf.json.JSONObject;

public class Main{
	
	public static void main(String[] args) throws Exception {
		//创建jframe
		JFrame frame = new JFrame("FrameDemo");
		frame.setTitle("抓取中国天气数据");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
        frame.setVisible(true);
        //创建jpanel
		JPanel jpanel = new JPanel();
		jpanel.setBorder(new EmptyBorder(5,5,5,5));
		//采用绝对布局
		jpanel.setLayout(null);
		frame.setContentPane(jpanel);
		//创建按钮
		JButton button=new JButton("执行");
		button.setSize(60, 30);
		button.setLocation(0, 0);
		jpanel.add(button);
		/**
		 * 单击执行按钮开始获取数据
		 */
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Map<String, String> paramMap = new HashMap<String, String>();
					RequestParams resParam = new RequestParams("http://www.weather.com.cn/weather1d/101190202.shtml", 0, paramMap);
					Document doc = SpiderUtil.getDocument(resParam,null);
					RealTimeService realTimeService = new RealTimeServiceImpl();
					JSONObject json = realTimeService.getLiveIndex(doc);
					File file = new File("D:\\test.txt");
					FileOutputStream fos = new FileOutputStream(file);
					PrintWriter pw = new PrintWriter(fos);
					pw.write(json.toString());
					pw.close();
					
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}


