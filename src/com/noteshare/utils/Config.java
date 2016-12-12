package com.noteshare.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static Properties prop = new Properties();

	public Config(String path) {
		ClassLoader loader = Config.class.getClassLoader();
		InputStream is = loader.getResourceAsStream(path);
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String parseString(String key) {
		return prop.getProperty(key);
	}

	public int parseInt(String key) {
		return Integer.parseInt(prop.getProperty(key));
	}

	public double parseDouble(String key) {
		return Double.parseDouble(prop.getProperty(key));
	}

	public static void main(String[] args) {
		Config config = new Config("db.properties");
		System.out.println(config.parseString("url"));
	}
}
