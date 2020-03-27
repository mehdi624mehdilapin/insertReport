package com.occCheckNoData.insertReportAsTable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class httpClient {
	
	public static void sender() {
		try {
			File file = new File(method.jsonPath);
			FileReader input = new FileReader(file);

			byte[] bytes = IOUtils.toByteArray(input);

			
			String webPage = "https://ldciofd.mo.sap.corp:44378/sap/stdf/table/TEST_AUTOMATION1/records?sap-client=200";
	        String USER="C5292600";
	        String PASSWORD ="uvmxu624GJK6DXMS@";

	        String authString = USER + ":" + PASSWORD;
	        
	        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
	        String authStringEnc = new String(authEncBytes);
	        

	        URL url = new URL(webPage);
	        HttpURLConnection  urlConnection = (HttpsURLConnection) url.openConnection();
	        
	        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
	        urlConnection.setRequestMethod("POST");
	        
	        urlConnection.setDoOutput(true);
	        OutputStream outStream = urlConnection.getOutputStream();
	        outStream.write(bytes); 
	        outStream.flush();
	        outStream.close();
	  
	        
	        ////////////////////////////////////////////////////////
	        
	        InputStream is = urlConnection.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);       
	        int numCharsRead;
	        char[] charArray = new char[1024];
	        StringBuffer sb = new StringBuffer();
	        while ((numCharsRead = isr.read(charArray)) > 0) {
	            sb.append(charArray, 0, numCharsRead);
	        }
	        String result = sb.toString();
	        int code = urlConnection.getResponseCode();
	        
	        
	        
	        System.out.println("the server response is : "+code);
	        System.out.println(result);
	        
	        is.close();
		}catch (IOException e) {
			    e.printStackTrace();
		
	}
		}

}
