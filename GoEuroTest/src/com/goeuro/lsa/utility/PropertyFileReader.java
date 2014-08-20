package com.goeuro.lsa.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	public static String URL;
	public static String OUT_FILE_NAME;

	static{
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("lsa_feeder.properties"));
			URL = prop.getProperty(Constants.URL);
			OUT_FILE_NAME = prop.getProperty(Constants.OUT_FILE_NAME);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
