package com.goeuro.lsa.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

public class Utility {
	static Logger logger = Logger.getLogger(Utility.class.toString());

	public static String[] trimArray(String[] array){

		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}
		return array;
	}

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
		}


	}

	public static void setUpTrustStore(){
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
			throws java.security.cert.CertificateException {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
			throws java.security.cert.CertificateException {
			}
		}};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			System.out.println("Message :"+e.getMessage());
		}
	}

}
