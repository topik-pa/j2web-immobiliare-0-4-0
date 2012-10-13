import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;


public class HttpPortalPostConnection extends HttpPortalConnection {
	
	HttpPost httppost;
	HttpClient httpclient;
	HttpResponse response;
	BasicHeader requestHeader;
	ResponseHandler<String> responseHandler;
	Header[] requestHeaders;
	Header[] responseHeaders;

	public HttpPortalPostConnection() {
				
		httpclient = new DefaultHttpClient();
		responseHandler = new BasicResponseHandler();
		
	}
	
	public String post(String url, List<NameValuePair> postParameters) throws IOException {
		
		httppost = new HttpPost(url);
		
		//Add request headers
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader);
        
        //Add request parameters
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
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
        response = httpclient.execute(httppost);
        
        //Get the response headers
        responseHeaders = response.getAllHeaders();
        
        //Print connection properties
        printConnectionProperties(postParameters);
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
       
        //Return the response body
        String responseBody = responseHandler.handleResponse(response);
        return responseBody;
        
	}
	
	
	public String post(String url, MultipartEntity reqEntity) throws IOException {
		
		httppost = new HttpPost(url);
		
		//Add request headers
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader);
        
        //Add request parameters
        httppost.setEntity(reqEntity);
        
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
        response = httpclient.execute(httppost);
        
        //Get the response headers
        responseHeaders = response.getAllHeaders();
        
        //Print connection properties
        printConnectionProperties();
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
		
        //Return the response body
        String responseBody = responseHandler.handleResponse(response);
        return responseBody;      
		
	}
	
	
	public void printConnectionProperties() throws IOException {
		
		//Show Request URL
		System.out.println("\n\n\n");
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Show Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Show Cookie properties
        System.out.println("Session cookie header: " + SESSIONCOOKIE_HEADER);
        System.out.println("Session cookie name: " + SESSIONCOOKIE_NAME);
        System.out.println("Session cookie value: " + SESSIONCOOKIE_VALUE);
        System.out.println("Session cookie domain: " + SESSIONCOOKIE_DOMAIN);
        System.out.println("----------------------------------------");
        
        //Show request headers
        requestHeaders = httppost.getAllHeaders(); 
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
	
	
	public void printConnectionProperties(List<NameValuePair> postParameters) throws IOException {
		
		//Show Request URL
		System.out.println("\n\n\n");
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + httppost.getURI());
        System.out.println("----------------------------------------");
        
        //Show Request properties
        System.out.println("Request method: " + httppost.getMethod());
        System.out.println("Protocol version: " + httppost.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Show Cookie properties
        System.out.println("Session cookie header: " + SESSIONCOOKIE_HEADER);
        System.out.println("Session cookie name: " + SESSIONCOOKIE_NAME);
        System.out.println("Session cookie value: " + SESSIONCOOKIE_VALUE);
        System.out.println("Session cookie domain: " + SESSIONCOOKIE_DOMAIN);
        System.out.println("----------------------------------------");
        
        //Show sent parameters
        Iterator<NameValuePair> iterator = postParameters.iterator();
    	while(iterator.hasNext()) {
    		NameValuePair currentParam = (NameValuePair) iterator.next();
    		System.out.println("Name: " + currentParam.getName() + "\tValue: " + currentParam.getValue() + "\n");
    	}
    	System.out.println("----------------------------------------");
        
        //Show request headers
        requestHeaders = httppost.getAllHeaders(); 
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