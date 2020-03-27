package com.occCheckNoData.insertReportAsTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;


public class method {

	public static String Date, Time, link;
	public static String NumberOfErrors=null;
	public static  String status;
	public static String projectName=null; 
	public static String JENKINS_HOME=null;
	public static String JOB_NAME=null;
	public static String BUILD_NUMBER=null;
	public static String timeStamp=null;
	public static String Application=null;
	public static String jsonPath=null;
	public static String Scenarios=null;
	
	public static void tacCheckScenarios(File log) throws IOException {
		FileReader fileReader = new FileReader(log);
		BufferedReader buffereader = new BufferedReader(fileReader);
		String[] words=null;
		String input1="scenario(s)" ;
		String s;
		
		if(Application.equals("TAC")) {
			
			
			while(((s=buffereader.readLine())!=null))
			 {
				words=s.split(" "); 
				
             int count=0;
                                
				for (String word : words) 
				{
                                   
					count++;
                                    
					if (word.equals(input1)) {
						
					Scenarios=words[count];
						
						for (int i=(count+1) ; i<words.length; i++) {
							
							Scenarios= Scenarios.concat(" ").concat(words[i]);
							
						}
						
						}
					
						
					
		         }
			 }
			if (Scenarios==null) {
				
                           Scenarios="No issues";
				
			}
		}
			
		else {
			Scenarios="N/A";
			
		}
		
		
		fileReader.close();
	}
	
public static void appNames() throws IOException {
		
	String[] Table =link.split("/");
	
	for (String word : Table) {
		
		if((word.equals("occ_dash"))||(word.equals("occ_dash_alias"))) {
			 
			Application="OCC";
			
		}
		if((word.equals("ope_dash"))||(word.equals("ope_dash_alias"))) {
			 
			Application="OPE";
			
		}
		
		if((word.equals("tac_dash"))||(word.equals("tac_dash_alias"))) {
			 
			Application="TAC";
			
		}
	}
		
	}
   
	public static void setJsonPath() throws IOException {
		
		jsonPath = JENKINS_HOME.concat("\\json.txt");
		
	}
	
	public static void projeStatus(File log) throws IOException {
		
		FileReader fileReader = new FileReader(log);  
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String[] lineTab=null;
		String s;
		String input2="FAILED";
		String input3="PASSED";
		while((s=bufferedReader.readLine())!=null)
		 {
			lineTab=s.split(" ");
		for (String word : lineTab)
    	{     
    		
    		if ((word.equals(input2))||(word.equals(input3))) {
    			
    			status= lineTab[1];
    		}
    	}
		 }
		
		fileReader.close();
	}
	
public static void linkGetter(File log) throws IOException {
		
		FileReader fileReader = new FileReader(log);  
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String[] lineTab=null;
		String s;
		String input="--baseUrl=";
		
		
		while((s=bufferedReader.readLine())!=null)
		 {
			lineTab=s.split("https|,");
			int count=0;
		for (String word : lineTab)
    	{     
			count++;
			
    		if ((word.equals(input))) {
    			
    			link="https".concat(lineTab[count]);
    			
    		}
    	}
		 }
		System.out.println(link);
		fileReader.close();
	}
	
	public static void occErrorNumberCheck(File log) throws IOException {
		FileReader fileReader = new FileReader(log);
		BufferedReader buffereader = new BufferedReader(fileReader);
		String[] words=null;
		String input1="theResultIs";
		String input2="chikiwi";
		String s;
		int count=0;
		if(Application.equals("OCC") ){
			
			while(((s=buffereader.readLine())!=null))
			 {
				words=s.split(" "); 
				for (String word : words) 
				{
					if (word.equals(input1)) {
						int y;
						y=words.length;
						NumberOfErrors= words[y-1];
						
						}
					else if ((word.equals(input2))&&(count<2)) {
						
						NumberOfErrors= "instance not found";
						
						}
					else if(word.equals("chakala")) {NumberOfErrors="No authorization found";}
						
						
					
		         }
			 }
			if ((NumberOfErrors.equals("chakala")==false)&&(NumberOfErrors.equals("No authorization found")==false)&&(NumberOfErrors.equals("instance not found")==false)&&(NumberOfErrors.length()>3)) {
				
				NumberOfErrors="other error";
			}
		}
			
		else {
			NumberOfErrors="N/A";
			
		}
		System.out.println(method.NumberOfErrors);
		if((NumberOfErrors.equals("instance not found")==false)&&(NumberOfErrors.equals("N/A")==false)&&(NumberOfErrors.equals("other error")==false)&&(NumberOfErrors.equals("No authorization found")==false)) {
		int z =Integer.valueOf(NumberOfErrors);
		if(z<2) {NumberOfErrors= NumberOfErrors.concat(" Gadget impacted"); }else {NumberOfErrors= NumberOfErrors.concat(" Gadgets impacted");}
		}
		fileReader.close();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static void putData(JSONObject jsonObject) {
		
		if (Application.equals("OCC"))
		{
		jsonObject.put("Application", Application);
		jsonObject.put("JOB_NAME", JOB_NAME);
		jsonObject.put("JOB_STATUS", status);
		jsonObject.put("JOB_ERRORS", NumberOfErrors);	
		jsonObject.put("DATE1", timeStamp );
		jsonObject.put("INSTANCE_LINK" , link);
			
		}
		if (Application.equals("TAC"))
		{
		jsonObject.put("Application", Application);
		jsonObject.put("JOB_NAME", JOB_NAME);
		jsonObject.put("JOB_STATUS", status);
		jsonObject.put("JOB_ERRORS", Scenarios);	
		jsonObject.put("DATE1", timeStamp );
		jsonObject.put("INSTANCE_LINK" , link);
			
		}
		
		
		
	}
	
	public static void timeStamp(File log) throws IOException {
		
		FileReader fileReader = new FileReader(log);
		BufferedReader buffereader = new BufferedReader(fileReader);
		String s;
		String s0;
		String[] s1;
		String[] words;
		String input="onPrepare";
		while((s=buffereader.readLine())!=null)
		 {
			words=s.split(" "); 
			for (String word : words) 
			{
			if (word.equals(input))
			{
				s=words[0];
				
				s1=s.split(" ");
				s=s1[0];
				s1=s.split("-");
				s0=s1[0].concat(s1[1]).concat(s1[2]);
				s1=s0.split("T");
				Date=s1[0];
				s1=s1[1].split("\\.|:");
				Time=s1[0].concat(s1[1]).concat(s1[2]);
				
				timeStamp=Date.concat(Time);

			}
			
			}
			fileReader.close();
		 }
		
	
		
		
		
		
				
	}
	
	
	
}
