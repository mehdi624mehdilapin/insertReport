package com.occCheckNoData.insertReportAsTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.naming.AuthenticationException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class httpClient {
	
	private static  String URL= null;

    private static  String PASSWORD =null;

    private static  String USER = null;
    
    public static void getUserAndPassword(File credentials) throws IOException {
		FileReader fileReader = new FileReader(credentials);
		BufferedReader buffereader = new BufferedReader(fileReader);
		String[] words=null;
		String input1="USER" ;
		String input2="PASSWORD" ;
		String input3="URL" ;
		String s;
			
			while(((s=buffereader.readLine())!=null))
			 {
				words=s.split("="); 
             
				for (String word : words) 
				{
                      
					if (word.equals(input1)) {
						
						USER=words[1];
					   
						}
					else if (word.equals(input2)) {
						
						PASSWORD=words[1];  
					}
					else if (word.equals(input3)) {
						
						URL=words[1];  
					}

		         }
			 }

		fileReader.close();
	}
 
	
	public static void send() throws ClientProtocolException, IOException, AuthenticationException{ 
		
		File file = new File(method.jsonPath);
		
		FileEntity entity = new FileEntity(file, 
		    ContentType.create("plain/text", "UTF-8"));
        HttpPost httppost = new HttpPost(URL);
        HttpGet httpget = new HttpGet(URL);
        
        httppost.setEntity(entity); 
        
        HttpHost target
                = new HttpHost(httpget.getURI().getHost(), 44378, "https");
        
        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials(USER, PASSWORD);
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                credentials);

        
        BasicCookieStore cookieStore =  new BasicCookieStore();

        CloseableHttpClient httpclient
                = HttpClients.custom().setDefaultCookieStore( cookieStore)
                        .setDefaultCredentialsProvider(credsProvider).build();

            HttpClientContext localContext = HttpClientContext.create();
            
            AuthCache authCache = new BasicAuthCache();
            
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);

            
           
            localContext.setAuthCache(authCache);

            CloseableHttpResponse response;

            response = httpclient.execute(target, httppost, localContext);
            
            
            
            String line1 =  response.getStatusLine().toString();
            
            String[] temp = line1.split(" ");
            String statusText=temp[1];
            for(int i=2;i<temp.length;i++)
            statusText=statusText.concat(" ").concat(temp[i]);
            
            System.out.println(statusText);
    
            httpclient.close();
		
		
    }
}