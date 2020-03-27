package com.occCheckNoData.insertReportAsTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class insertReportAsTable {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		try {
			
			File log= new File("C:\\Users\\C5292600\\eclipse-workspace\\resolution.txt");
			FileReader fileReader = new FileReader(log);
			BufferedReader buffereader = new BufferedReader(fileReader);
			String[] words=null;
			String s;
			int count=0;
			while(((s=buffereader.readLine())!=null))
			 {
				words=s.split("\"");
				
				for (String word : words) 
				{
                                   
					
                                    
					if (word.equals("Category")) {
						count++;
					}
				}
				System.out.println(count);
			 }
			/* method.JENKINS_HOME =System.getenv("JENKINS_HOME");
			 method.JOB_NAME =System.getenv("JOB_NAME");
			 method.BUILD_NUMBER =System.getenv("BUILD_NUMBER");
			 String logLink=  method.JENKINS_HOME.concat("//jobs//").concat(method.JOB_NAME).concat("//builds//").concat(method.BUILD_NUMBER).concat("//log");
	
			method.setJsonPath();
			 
			 JSONArray array=new JSONArray();
			 
			 JSONObject  jsonObject1=new JSONObject();
			 
			 File log= new File(logLink);

				method.projeStatus(log);
				
                method.linkGetter(log);
				
				method.appNames();
				
				method.occErrorNumberCheck(log);
				
				method.tacCheckScenarios(log);
				
				method.timeStamp(log);
				
				
				
				method.putData(jsonObject1);
				
				array.add(jsonObject1);
	    

	    	
	        FileWriter file = new FileWriter(method.jsonPath);
	        file.write(array.toJSONString());
            file.close();*/
	     } catch (IOException e) {
	        
	   	 
	        e.printStackTrace();
	     }
	//httpClient.sender(); 
	}
}
