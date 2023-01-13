package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) {
		Properties config = new Properties();
		Properties or = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(in);
			in = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			or.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(config.getProperty("browser"));
		System.out.println(or.getProperty("bankManagerLoginButton_XPATH"));
	}
	
}
