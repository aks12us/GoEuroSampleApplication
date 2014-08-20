/**
 * 
 */
package com.goeuro.lsa.service;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.goeuro.lsa.beans.LocationBean;

/**
 * @author Ankit-Reshu
 *
 */
public interface LsaService {
	JsonArray callOutService(String url,String searchString);
	List<LocationBean> storeArray(JsonArray jsonArray);
	List<LocationBean> process(String queryString,List<LocationBean> locationList);
	StringBuilder buildExportData(List<LocationBean> finalData);
	
}
