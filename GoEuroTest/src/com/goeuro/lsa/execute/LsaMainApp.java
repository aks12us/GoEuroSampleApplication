package com.goeuro.lsa.execute;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonArray;
import com.goeuro.lsa.beans.LocationBean;
import com.goeuro.lsa.service.LsaService;
import com.goeuro.lsa.service.impl.LsaServiceImpl;
import com.goeuro.lsa.utility.PropertyFileReader;
import com.goeuro.lsa.utility.Utility;
import com.goeuro.lsa.validator.GenericValidator;

/**
 * This class is the main execution start point and the complete execution flow 
 * <br/> is coded through different service method delegation.
 * @author Ankit Khare
 */
public class LsaMainApp {
	static Logger logger = Logger.getLogger(LsaMainApp.class.toString());
	/**
	 * This is the main method that takes the input as search string that
	 * <br/>would be processed   and out put file would be generated 
	 * <br/> <B>for e.g java LsaMainApp queryString </b> 
	 * @param queryString
	 */
	public static void main(String[] args) {

		String userInput= null;
		boolean isValid = true;
		try {
			userInput = args[0];
			isValid = GenericValidator.isValidString(userInput);
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.log(Level.SEVERE, "Please provide the input search criteria.");
			isValid = false;
			Utility.exitSystem();
		}
		if(isValid){
			LsaService lsaService = new LsaServiceImpl();
			JsonArray jsonArray1 = lsaService.callOutService(PropertyFileReader.URL,userInput);
			if(GenericValidator.checkNull(jsonArray1)){
				List<LocationBean> locationBeansList =   lsaService.storeArray(jsonArray1);
				List<LocationBean> finalLocationBeansList  = lsaService.process(userInput,locationBeansList);
				StringBuilder fileData = lsaService.buildExportData(finalLocationBeansList);
				Utility.createCSVFile(fileData, PropertyFileReader.OUT_FILE_NAME);
			}else{
				logger.log(Level.WARNING, "Server returned empty Result please refer to the exception");
			}
		}else{
			logger.log(Level.WARNING, "Please provide string without special character and numbers.");
		}
	}
}
