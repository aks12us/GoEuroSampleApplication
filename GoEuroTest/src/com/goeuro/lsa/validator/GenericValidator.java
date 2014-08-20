package com.goeuro.lsa.validator;

import java.util.regex.Pattern;

import com.goeuro.lsa.utility.Constants;

public class GenericValidator {
	
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
	
	public static boolean checkNull(Object obj){
		boolean flag = true;
		
		if(obj.equals(Constants.NULL_OBJECT)){
			flag = false;
		}
		
		return flag;
	}
	
}
