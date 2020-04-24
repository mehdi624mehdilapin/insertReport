package com.occCheckNoData.insertReportAsTable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.http.auth.AuthenticationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class insertReportAsTable {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, AuthenticationException, javax.naming.AuthenticationException {
		
		try {
			
			
			 method.JENKINS_HOME =System.getenv("JENKINS_HOME");
			 method.JOB_NAME =System.getenv("JOB_NAME");
			 method.BUILD_NUMBER =System.getenv("BUILD_NUMBER");
			 String logLink=  method.JENKINS_HOME.concat("//jobs//").concat(method.JOB_NAME).concat("//builds//").concat(method.BUILD_NUMBER).concat("//log");
			 String CREDENTIALS= method.JENKINS_HOME.concat("//").concat("credentials.txt");
			 method.setJsonPath();
			 
			 JSONArray array=new JSONArray();
			 
			 JSONObject  jsonObject1=new JSONObject();
			 
			 File log= new File(logLink);
			 File credentials= new File(CREDENTIALS); 

				method.projeStatus(log);
				
				method.assertion(log);
				
                method.linkGetter(log);
                
				method.appNames();
				
				method.occErrorNumberCheck(log);
				
				method.tacCheckScenarios(log);
				
				method.opeTileCheck(log);
				
				
				
				method.timeStamp(log);
				
				
				
				method.putData(jsonObject1);
				
				array.add(jsonObject1);
	    

	    	
	        FileWriter file = new FileWriter(method.jsonPath);
	        file.write(array.toJSONString());
            file.close();
            
            httpClient.getUserAndPassword(credentials);
            httpClient.send();
            
	     } catch (IOException e) {
	        
	   	 
	        e.printStackTrace();
	     }
		
	}
}
