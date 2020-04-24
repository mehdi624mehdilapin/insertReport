package com.occCheckNoData.insertReportAsTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;


public class method {

	public static String Date, Time, link;
	public static  String status;
	public static String projectName=null; 
	public static String JENKINS_HOME=null;
	public static String JOB_NAME=null;
	public static String BUILD_NUMBER=null;
	public static String timeStamp=null;
	public static String Application=null;
	public static String jsonPath=null;
	public static String Scenarios="null";
	public static int NumberOfErrors=0;
	public static int Scenario=0;
	public static int greyTile=0;
	
	
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
						  
						words=Scenarios.split(",");
						
						Scenario= words.length;
						}
					
						
					
		         }
			 }
			
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
	
public static void assertion(File log) throws IOException {
		
		FileReader fileReader = new FileReader(log);  
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String[] lineTab=null;
		String s;
		String input1="NotReacheable_ERR_CONNECTION_TIMED_OUT";
		while((s=bufferedReader.readLine())!=null)
		 {
			lineTab=s.split(" ");
		for (String word : lineTab)
    	{     
    		
    		if (word.equals(input1)) {
    			
    			status=status.concat(" ").concat("( system not Reachable)") ;
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
		fileReader.close();
	}
	
	public static void occErrorNumberCheck(File log) throws IOException {
		FileReader fileReader = new FileReader(log);
		BufferedReader buffereader = new BufferedReader(fileReader);
		String[] words=null;
		String input1="theResultIs";
		String s;
		
		
		if(Application.equals("OCC")){
			
			while(((s=buffereader.readLine())!=null))
			 {
				
				words=s.split(" "); 
				String temp="null";
				for (String word : words) 
				{
					if(word.equals("RESULT"))
						temp="ok";
					
					if(temp.equals("ok") && word.equals(input1)){
					int y;
					y=words.length;

						String string= words[y-1];

						NumberOfErrors=Integer.parseInt(string);
						
						}
                   
					
					
					
					
					
		         }
			 }
			 }
			
			
		
		
		fileReader.close();
	}
	
	public static void opeTileCheck(File log) throws IOException {
		FileReader fileReader = new FileReader(log);
		BufferedReader buffereader = new BufferedReader(fileReader);
		String[] words=null;
		String input1="theResultIs";
		String s;
		if(Application.equals("OPE") ){
			
			while(((s=buffereader.readLine())!=null))
			 {
				words=s.split(" "); 
				String temp="null";
				for (String word : words) 
				{
					if(word.equals("RESULT"))
						temp="ok";
					
					if(temp.equals("ok") && word.equals(input1)){
					int y;
					y=words.length;

						String string= words[y-1];

						greyTile=Integer.parseInt(string);
						
						}
			 }
			
			}
		
		}
		
		
		fileReader.close();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static void putData(JSONObject jsonObject) {
		
		if (Application.equals("OCC"))
		{
		jsonObject.put("Application", Application);
		jsonObject.put("Test_Case_Name", JOB_NAME);
		jsonObject.put("Test_Status", status);
		jsonObject.put("Number_Of_Errors", NumberOfErrors);	
		jsonObject.put("DATE1", timeStamp );
		
			
		}
		if (Application.equals("TAC"))
		{
		jsonObject.put("Application", Application);
		jsonObject.put("Test_Case_Name", JOB_NAME);
		jsonObject.put("Test_Status", status);
		jsonObject.put("Number_Of_Errors", Scenario);	
		jsonObject.put("DATE1", Date );
		
			
		}
		
		if (Application.equals("OPE"))
		{
		jsonObject.put("Application", Application);
		jsonObject.put("Test_Case_Name", JOB_NAME);
		jsonObject.put("Test_Status", status);
		jsonObject.put("Number_Of_Errors", greyTile);	
		jsonObject.put("DATE1", Date );
		
			
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
			
		 }
		
	
		
		
		
		
		fileReader.close();		
	}
	
	
	
}
