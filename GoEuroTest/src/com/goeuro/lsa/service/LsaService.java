/**
 * 
 */
package com.goeuro.lsa.service;
import java.util.List;

import javax.json.JsonArray;

import com.goeuro.lsa.beans.LocationBean;


/**
 * This is the interface that declare the services offered by the Location Search API
 * 
 * @author Ankit Khare
 *
 */
public interface LsaService {
	/**
	 * This is the common method that would be used to call the external service with the required parameters 
	 * <br/> as search string
	 * @param url -The url for the endpoint
	 * @param searchString- string that would be searched 
	 * @return {@link JsonArray}
	 */
	JsonArray callOutService(String url,String searchString);
	/**
	 * This method converts the jasonArray to the Location Bean list object where the desired result is stored
	 * @param jsonArray
	 * @return List {@link LocationBean}
	 */
	List<LocationBean> storeArray(JsonArray jsonArray);
	/**
	 * This method is to process any further results
	 * @param queryString
	 * @param locationList
	 * @return List {@link LocationBean}
	 */
	List<LocationBean> process(String queryString,List<LocationBean> locationList);
	/**This method process the complete results and dumps the result in a csv file the name of the output file
	 * <br/> is configurable from the property file please refer lsa_feeder.properties 
	 * @param finalData
	 * @return {@link StringBuilder}
	 */
	StringBuilder buildExportData(List<LocationBean> finalData);

}
