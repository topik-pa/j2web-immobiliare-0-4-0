import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpPortalPostConnection extends HttpPortalConnection {
	
	protected ResponseHandler<String> responseHandler;
	
	//Costruttore
	public HttpPortalPostConnection() {
		responseHandler = new BasicResponseHandler();
	}
	
	
	//POST URLENCODED VALUES
	public Object[] post(String connectionDescription, String url, List<NameValuePair> postParameters, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];
		
		//Inizializza la connessione
		httppost = new HttpPost(url);
		
		//Add request headers
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader);
        
        //Add request parameters
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        if(isSessionCookieSet==true) {
	        BasicCookieStore cookieStore = new BasicCookieStore(); 
	        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
	        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
	        cookie.setPath("/");           
	        cookieStore.addCookie(cookie); 
	        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
        }
        
        //Send the request
        response = httpclient.execute(httppost);
        
        //Get the response body
        HttpEntity responseEntity = response.getEntity();
        String responseBody = "";
        if(responseEntity!=null) {
        	responseBody = EntityUtils.toString(responseEntity);
        }

    	//Get the response headers
        responseHeaders = response.getAllHeaders();
            
        if(debugMode) {
            //Print connection properties
        	printConnectionProperties(connectionDescription, httppost, responseHeaders, responseBody, postParameters);
        }
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
        
        //Return the headers and response body
        headersAndBodyResponseAndStatus[0] = responseHeaders;
        headersAndBodyResponseAndStatus[1] = responseBody;
        headersAndBodyResponseAndStatus[2] = response.getStatusLine();    
        return headersAndBodyResponseAndStatus; 
        
	}
	
	//POST MULTIPART/FORM-DATA
	public Object[] post(String connectionDescription, String url, MultipartEntity reqEntity, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];
		
		//Inizializza la connessione
		httppost = new HttpPost(url);
		
		//Add request headers
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader);
        
        //Add request parameters
        httppost.setEntity(reqEntity);
        
        //Set the cookies
        if(isSessionCookieSet==true) {
	        BasicCookieStore cookieStore = new BasicCookieStore(); 
	        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
	        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
	        cookie.setPath("/");           
	        cookieStore.addCookie(cookie); 
	        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
        }
        
        //Send the request
        response = httpclient.execute(httppost);
        
        //Get the response body
        HttpEntity responseEntity = response.getEntity();
        String responseBody = "";
        if(responseEntity!=null) {
        	responseBody = EntityUtils.toString(responseEntity);
        }
                
    	//Get the response headers
        responseHeaders = response.getAllHeaders();
         
        if(debugMode) {
            //Print connection properties
        	printConnectionProperties(connectionDescription, httppost, responseHeaders, responseBody, reqEntity);
        }
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
		
        //Return the headers and response body
        headersAndBodyResponseAndStatus[0] = responseHeaders;
        headersAndBodyResponseAndStatus[1] = responseBody;
        headersAndBodyResponseAndStatus[2] = response.getStatusLine();    
        return headersAndBodyResponseAndStatus;     
		
	}	
		
	//POST URLENCODED VALUES (2)
	public Object[] post(String connectionDescription, String url, List<NameValuePair> postParameters, List<NameValuePair> requestHeaders, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];
		
		//Inizializza la connessione
		httppost = new HttpPost(url);
		
		//Add request headers
		BasicHeader newHeader;
		BasicNameValuePair currentHeaderListItem;
		Iterator<NameValuePair> headersIterator = requestHeaders.iterator();
        while(headersIterator.hasNext()) {
        	currentHeaderListItem = (BasicNameValuePair) headersIterator.next();
        	if(currentHeaderListItem.getName()!= "User-Agent") { //Non aggiungo unn ulteriore header User-Agent
	        	newHeader = new BasicHeader(currentHeaderListItem.getName(), currentHeaderListItem.getValue());
	        	httppost.addHeader(newHeader);
        	}
        }
        //L'header User-Agent è aggiunto sempre in modo predefinito
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader);
        
        //Add request parameters
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
        httppost.setEntity(formEntity);
        
        //Set the cookies
        if(isSessionCookieSet==true) {
	        BasicCookieStore cookieStore = new BasicCookieStore(); 
	        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
	        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
	        cookie.setPath("/");           
	        cookieStore.addCookie(cookie); 
	        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
        }
        
        //Send the request
        response = httpclient.execute(httppost);
        
        //Get the response body
        HttpEntity responseEntity = response.getEntity();
        String responseBody = "";
        if(responseEntity!=null) {
        	responseBody = EntityUtils.toString(responseEntity);
        }

    	//Get the response headers
        responseHeaders = response.getAllHeaders();
            
        if(debugMode) {
            //Print connection properties
        	printConnectionProperties(connectionDescription, httppost, responseHeaders, responseBody, postParameters);
        }
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
        
        //Return the headers and response body
        headersAndBodyResponseAndStatus[0] = responseHeaders;
        headersAndBodyResponseAndStatus[1] = responseBody;
        headersAndBodyResponseAndStatus[2] = response.getStatusLine();    
        return headersAndBodyResponseAndStatus; 
        
	}
	
	
	//POST MULTIPART/FORM-DATA (2)
	public Object[] post(String connectionDescription, String url, MultipartEntity reqEntity, List<NameValuePair> requestHeaders, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];
		
		//Inizializza la connessione
		httppost = new HttpPost(url);
		
		//Add request headers
		BasicHeader newHeader;
		BasicNameValuePair currentHeaderListItem;
		Iterator<NameValuePair> headersIterator = requestHeaders.iterator();
        while(headersIterator.hasNext()) {
        	currentHeaderListItem = (BasicNameValuePair) headersIterator.next();
        	if(currentHeaderListItem.getName()!= "User-Agent") { //Non aggiungo unn ulteriore header User-Agent
	        	newHeader = new BasicHeader(currentHeaderListItem.getName(), currentHeaderListItem.getValue());
	        	httppost.addHeader(newHeader);
        	}
        }
        //L'header User-Agent è aggiunto sempre in modo predefinito
        requestHeader = new BasicHeader("User-Agent", USER_AGENT);
        httppost.addHeader(requestHeader);
        
        //Add request parameters
        httppost.setEntity(reqEntity);
        
        //Set the cookies
        if(isSessionCookieSet==true) {
	        BasicCookieStore cookieStore = new BasicCookieStore(); 
	        BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
	        cookie.setDomain(SESSIONCOOKIE_DOMAIN);
	        cookie.setPath("/");           
	        cookieStore.addCookie(cookie); 
	        ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
        }
        
        //Send the request
        response = httpclient.execute(httppost);
        
        //Get the response body
        HttpEntity responseEntity = response.getEntity();
        String responseBody = "";
        if(responseEntity!=null) {
        	responseBody = EntityUtils.toString(responseEntity);
        }
                
    	//Get the response headers
        responseHeaders = response.getAllHeaders();
         
        if(debugMode) {
            //Print connection properties
        	printConnectionProperties(connectionDescription, httppost, responseHeaders, responseBody, reqEntity);
        }
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
		
        //Return the headers and response body
        headersAndBodyResponseAndStatus[0] = responseHeaders;
        headersAndBodyResponseAndStatus[1] = responseBody;
        headersAndBodyResponseAndStatus[2] = response.getStatusLine();    
        return headersAndBodyResponseAndStatus;     
		
	}
	
}