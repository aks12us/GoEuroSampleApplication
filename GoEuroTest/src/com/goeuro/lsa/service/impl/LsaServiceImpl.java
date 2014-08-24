/**
 * 
 */
package com.goeuro.lsa.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.goeuro.lsa.beans.LocationBean;
import com.goeuro.lsa.service.LsaService;
import com.goeuro.lsa.utility.Constants;
import com.goeuro.lsa.utility.Utility;

/**
 * This class is the implementation for the LsaService for documentation please refer the {@link LsaService}
 * @author Ankit Khare
 *
 */
public class LsaServiceImpl implements LsaService {
	Logger logger = Logger.getLogger(LsaServiceImpl.class.toString());
	public JsonArray callOutService(String serviceUrl,String searchString) {
		JsonArray jsonArray  = null;
		try {
			URL url = new URL(serviceUrl+searchString);
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is) ;
			jsonArray = rdr.readArray();
			logger.log(Level.INFO, "Record Received :"+jsonArray.toString());
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, e.getMessage());
			Utility.exitSystem();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
			Utility.exitSystem();
		}catch(Exception e){
			logger.log(Level.SEVERE, e.getMessage());
			Utility.exitSystem();
		}
		return jsonArray;
	}

	public List<LocationBean> storeArray(JsonArray jsonArray) {
		JsonArray results = jsonArray;
		List<LocationBean> locationBeansList = new ArrayList<LocationBean>();
		for (JsonObject result : results.getValuesAs(JsonObject.class)) {
			LocationBean locationBean = LocationBean.createLocationBean();
			locationBean.set_Type(result.getString(Constants._TYPE));
			locationBean.set_Id(result.getInt(Constants._ID));
			locationBean.setType(result.getString(Constants.TYPE));
			locationBean.setLatitude(result.getJsonObject(Constants.GEO_POSITION).getJsonNumber(Constants.LATITUDE));
			locationBean.setLongitude(result.getJsonObject(Constants.GEO_POSITION).getJsonNumber(Constants.LONGITUDE));
			String[] nameArray = result.getJsonString(Constants.NAME).getString().trim().split(Constants.COMMA);
			locationBean.setName(Arrays.asList(Utility.trimArray(nameArray)));
			locationBeansList.add(locationBean);
		}
		return locationBeansList;
	}


	public List<LocationBean> process(String queryString, List<LocationBean> locationList) {
		List<LocationBean> finalLocationBean = new ArrayList<LocationBean>();
		for (Iterator<LocationBean> iterator = locationList.iterator(); iterator.hasNext();) {
			LocationBean locationBean =  iterator.next();
			StringBuilder formatedName = locationBean.formattedName();

			if(formatedName.toString().toLowerCase().contains(queryString.toLowerCase())){
				finalLocationBean.add(locationBean);
			}else if((locationBean.get_Type().equalsIgnoreCase(queryString))){
				finalLocationBean.add(locationBean);
			}else if((locationBean.getType().equalsIgnoreCase(queryString))){
				finalLocationBean.add(locationBean);
			}
		}
		return finalLocationBean;
	}

	public StringBuilder buildExportData(List<LocationBean> finalData) {
		StringBuilder builder = new StringBuilder();

		builder.append(Constants._TYPE).append(Constants.COMMA);
		builder.append(Constants._ID).append(Constants.COMMA);
		builder.append(Constants.NAME).append(Constants.COMMA);
		builder.append(Constants.TYPE).append(Constants.COMMA);
		builder.append(Constants.LATITUDE).append(Constants.COMMA);
		builder.append(Constants.LONGITUDE).append(Constants.NEW_LINE);
		if(finalData.isEmpty()){
			builder.append(Constants.DEFAULT_MESSAGE);
		}else{
			for (LocationBean locationBean : finalData) {
				builder.append(locationBean.get_Type()).append(Constants.COMMA);
				builder.append(locationBean.get_Id()).append(Constants.COMMA);
				builder.append(locationBean.formattedName()).append(Constants.COMMA);	
				builder.append(locationBean.getType()).append(Constants.COMMA);
				builder.append(locationBean.getLatitude()).append(Constants.COMMA);
				builder.append(locationBean.getLongitude()).append(Constants.NEW_LINE);
			}
		}
		return builder;
	}


}
