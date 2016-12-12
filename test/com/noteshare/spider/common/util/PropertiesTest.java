package com.noteshare.spider.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) throws IOException {
		URL url = ClassLoader.getSystemResource("");
		System.out.println(url.toString());
		Properties configProperties = new Properties();  
		FileInputStream in = new FileInputStream("E:\\gitcodes\\eclipsegitres\\SpliderDemo\\src\\db.properties");  
		configProperties.load(in);  
		in.close(); 
		String str = configProperties.getProperty("user");
		System.out.println(str);
	}
}
