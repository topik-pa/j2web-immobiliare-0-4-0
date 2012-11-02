import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;


public class HttpPortalGetConnection extends HttpPortalConnection {
	
	protected ResponseHandler<String> responseHandler;
	
	//Costruttore
	public HttpPortalGetConnection() {
		responseHandler = new BasicResponseHandler();
	}
	
	
	//GET di una risorsa
	public Object[] get(String connectionDescription, String url, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];
				
		//Inizializza la connessione
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
            printConnectionProperties(connectionDescription, httpget, responseHeaders, responseBody);
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