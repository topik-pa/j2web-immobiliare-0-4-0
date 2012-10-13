import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;


public class HttpPortalGetConnection extends HttpPortalConnection {
	
	HttpGet httpget;
	HttpClient httpclient;
	HttpResponse response;
	BasicHeader requestHeader;
	ResponseHandler<String> responseHandler;
	Header[] requestHeaders;
	Header[] responseHeaders;

	public HttpPortalGetConnection() {
				
		httpclient = new DefaultHttpClient();
		responseHandler = new BasicResponseHandler();
		
	}
	
	public void get(String url) throws IOException {
		
		httpget = new HttpGet(url);
		
		//Add request headers
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httpget.addHeader(requestHeader);
        
        //Set the cookies
        if(SESSIONCOOKIE_HEADER!=null) {
	        BasicCookieStore cookieStore = new BasicCookieStore(); 
	        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
	        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
	        cookie.setPath("/");           
	        cookieStore.addCookie(cookie); 
	        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
        }
        
        //Send the request
        response = httpclient.execute(httpget);
        
        //Get the response headers
        responseHeaders = response.getAllHeaders();
        
        //Print connection properties
        printConnectionProperties();
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
       
		
	}
	
	public void get(String url, String sessionCookieName) throws IOException, HttpResponseException {
		
		httpget = new HttpGet(url);
		
		//Add request headers
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httpget.addHeader(requestHeader);
        
        //Send the request
        response = httpclient.execute(httpget);
        
        //Get the response headers
        responseHeaders = response.getAllHeaders();
        
        //Set the local cookie
        setLocalCookie(sessionCookieName);
        
        //Print connection properties
        printConnectionProperties();
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
       
		
	}
	
	public void printConnectionProperties() throws IOException {
		
		//Show Request URL
		System.out.println("\n\n\n");
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httpget.getURI());
        System.out.println("----------------------------------------");
        
        //Show Request properties
        System.out.println("Request method: " + httpget.getMethod());
        System.out.println("Protocol version: " + httpget.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Show request headers
        requestHeaders = httpget.getAllHeaders(); 
        System.out.println("Request headers: " + requestHeaders.length + "\n");
        for(int i=0; i<requestHeaders.length; i++) {
        	System.out.println(requestHeaders[i]);
        }
        System.out.println("----------------------------------------");
        
        //Show response headers                          
        System.out.println("Response status: " + response.getStatusLine());
        System.out.println("Response headers: " + responseHeaders.length + "\n");
        for(int i=0; i<responseHeaders.length; i++) {
        	System.out.println(responseHeaders[i]);
        }
        System.out.println("----------------------------------------");
        
        //Show response body
        if(response.getStatusLine().toString().contains("200")) {
            System.out.println("Response body: \n");
            String responseBody = responseHandler.handleResponse(response);
            System.out.println(responseBody);
                       
        }
        else {
        	System.out.println("Nessuna risposta nel body"); 
        }       
        System.out.println("----------------------------------------");
        
        //Stampa fine connessione
        System.out.println("\n");
        System.out.println("----------------------------------------");
        System.out.println("Request end");
        System.out.println("----------------------------------------");
        System.out.println("\n\n\n");
        
	}
	
	public void setLocalCookie(String sessionCookieName) throws HttpResponseException {
		
		boolean cookieHeaderFound = false;
        for(int i=0; i<responseHeaders.length; i++) {       	
        	Header currentHeader = responseHeaders[i];
        	//Get cookie
        	if(currentHeader.getName().contains("Set-Cookie")) {
        		        		
        		String cookie_header = currentHeader.getValue();
        		int end = cookie_header.indexOf("=");
                String cookie_name = cookie_header.substring(0, end);                   
                int start = end + 1;
                end = cookie_header.indexOf(";");
                String cookie_value = cookie_header.substring(start, end);
                
                if(cookie_name.equals(sessionCookieName)) {
                	//Cookie di sessione trovato
            		cookieHeaderFound = true;
            		SESSIONCOOKIE_HEADER = cookie_header;
            		SESSIONCOOKIE_NAME = cookie_name;
            		SESSIONCOOKIE_VALUE = cookie_value;
            		break;
                }   
        	}       	
        }        
        //Se non trovo le intestazioni che cerco lancio una eccezione (è un errore non trovare i cookie di sessione se richiesti)
    	if(!cookieHeaderFound) {
    		throw(new HttpResponseException("Response header"));
    	}
	}
	
	public void setSessionCookieDomain(String cookieDomain) {
		SESSIONCOOKIE_DOMAIN = cookieDomain;
	}
	
	public void setSessionCookieName(String cookieName) {
		SESSIONCOOKIE_NAME = cookieName;
	}
	
	public void setSessionCookieValue(String cookieValue) {
		SESSIONCOOKIE_VALUE = cookieValue;
	}
	
	
}