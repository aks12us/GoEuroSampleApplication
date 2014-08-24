package com.goeuro.lsa.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This is the Utility class and contains all the utility method.
 * <br/>
 * <b>1) trimArray
 * <br/>
 * <b>2) createCSVFile
 * @author Ankit Khare
 */
public class Utility {
	static Logger logger = Logger.getLogger(Utility.class.toString());

	/**
	 * This method takes input as String array and trims all the string and resturn the array back
	 * 
	 * @param array
	 * @return String[] 
	 */
	public static String[] trimArray(String[] array){
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}
		return array;
	}

	/**
	 * This method takes input as below mentioned and create the out Comma separated file
	 * @param fileData
	 * @param fileName
	 */
	public static void createCSVFile(StringBuilder fileData,String fileName){
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileData.toString());
			bw.close();
			logger.log(Level.INFO,"Output file is succesfully created .");
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
			Utility.exitSystem();
		}
	}
	
	/**
	 * This method is used to exit the system in case of fatalissue 
	 */
	public static void exitSystem(){
		System.exit(0);
	}

}
