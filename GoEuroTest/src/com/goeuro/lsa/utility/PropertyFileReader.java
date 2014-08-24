package com.goeuro.lsa.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to load the property file and provided the same at the load time so that 
 * there is no need to read the information repeatedly since it exposes the details using static variable.
 * @author Ankit Khare
 */
public class PropertyFileReader {
	private static Logger logger = Logger.getLogger(PropertyFileReader.class.getName());
	public static String URL;
	public static String OUT_FILE_NAME;
	public static final String PROPERTY_FILE_NAME = "lsa_feeder.properties";

	static{
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(PROPERTY_FILE_NAME));
			URL = prop.getProperty(Constants.URL);
			OUT_FILE_NAME = prop.getProperty(Constants.OUT_FILE_NAME);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, ex.getMessage());
			Utility.exitSystem();
		}
	}
}
