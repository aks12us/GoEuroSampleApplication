package com.goeuro.lsa.beans;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonNumber;

import com.goeuro.lsa.utility.Constants;
import com.goeuro.lsa.validator.GenericValidator;

/**
 * This is the bean that stores the structure for the Jason response from the API
 * <br/>For <b>e.g
 *  <br/>[  
   <br/>{  
   <br/>"_id":377078,
      <br/>"key":null,
      <br/>"name":"Potsdam",
      <br/>"fullName":"Potsdam, Germany",
      <br/>"iata_airport_code":null,
      <br/>"type":"location",
      <br/>"country":"Germany",
      <br/>"geo_position":{  
         <br/>"latitude":52.39886,
         <br/>"longitude":13.06566
      <br/>},
      <br/>"locationId":9254,
      <br/>"inEurope":true,
      <br/>"countryCode":"DE",
      <br/>"coreCountry":true,
      <br/>"distance":null
   <br/>}.
 * @author Ankit Khare
 *
 */
public class LocationBean {
	private String _Type;
	private double _Id;
	private List<String> name = new ArrayList<String>();
	private String type;
	private JsonNumber longitude;
	private JsonNumber latitude;
	private String currentSearchName = "";
	
	public static LocationBean createLocationBean(){
		return new LocationBean();
	}

	/**
	 * @return
	 */
	public String get_Type() {
		return _Type;
	}

	public void set_Type(String type) {
		_Type = type;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double get_Id() {
		return _Id;
	}

	public void set_Id(double id) {
		_Id = id;
	}

	public JsonNumber getLongitude() {
		return longitude;
	}

	public void setLongitude(JsonNumber longitude) {
		this.longitude = longitude;
	}

	public JsonNumber getLatitude() {
		return latitude;
	}

	public void setLatitude(JsonNumber latitude) {
		this.latitude = latitude;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public String getCurrentSearchName() {
		return currentSearchName;
	}

	public void setCurrentSearchName(String currentSearchName) {
		this.currentSearchName = currentSearchName;
	}
	
	public StringBuilder formattedName() {
		StringBuilder builder = new StringBuilder();
		
		if(GenericValidator.isValidString(getCurrentSearchName())){
			builder.append(getCurrentSearchName());
		}else{
			if(name != null){
				String[] strArray = (String[]) name.toArray();
				for (int i = 0; i < strArray.length; i++) {
					if( i < ( strArray.length-1 )){
						builder.append(strArray[i]).append(Constants.PIPE_SEPERATOR);
					}else{
						builder.append(strArray[i]);
					}
				}
			}
			
		}
		return builder;
	}
}
