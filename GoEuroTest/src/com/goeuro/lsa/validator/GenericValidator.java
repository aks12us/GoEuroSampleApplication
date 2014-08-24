package com.goeuro.lsa.validator;

import java.util.regex.Pattern;

import com.goeuro.lsa.utility.Constants;

/**
 * This is the validator class that dose some generic validatation and could be reused across application
 * @author Ankit Khare
 *
 */
public class GenericValidator {
	
	/**This method validate the string against  <b>null</b> , <b>blank</b> and some special character like <b>"!@#$%^&*()_"</b>
	 * @param str
	 * @return boolean 
	 */
	public static boolean isValidString(String str){
		boolean flag = true;
		
		if(Constants.BLANK.equalsIgnoreCase(str)){
			flag = false;
		}else if(str.equals(Constants.NULL_STRING)){
			flag = false;
		}else{
			String specialCharacter = "!@#$%^&*()_";
			String pattern = ".*[" + Pattern.quote(specialCharacter) + "].*";
			if(str.matches(pattern)){
				flag = false;
			}
		}
		return flag;
	}
	
	/** This method check if the object is null
	 * @param obj
	 * @return boolean
	 */
	public static boolean checkNull(Object obj){
		boolean flag = true;
		
		if(obj.equals(Constants.NULL_OBJECT)){
			flag = false;
		}
		return flag;
	}
}
