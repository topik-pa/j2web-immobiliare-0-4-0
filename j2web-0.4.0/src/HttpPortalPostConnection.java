import java.io.IOException;
import java.util.List;
import org.apache.http.NameValuePair;
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
	
	
	//Costruttore
	public HttpPortalPostConnection() {
				
		httpclient = new DefaultHttpClient();
		responseHandler = new BasicResponseHandler();
		
	}
	
	
	public Object[] post(String url, List<NameValuePair> postParameters, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponse = new Object[2];
		
		//Inizializza la connessione
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
        
        String responseBody = responseHandler.handleResponse(response);
        
    	//Get the response headers
        responseHeaders = response.getAllHeaders();
            
        if(debugMode) {
            //Print connection properties
            printConnectionProperties();
        }
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
        
        //Return the headers and response body
        headersAndBodyResponse[0] = responseHeaders;
        headersAndBodyResponse[1] = responseBody;       
        return headersAndBodyResponse; 
        
	}
	
	
	public Object[] post(String url, MultipartEntity reqEntity, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponse = new Object[2];
		
		//Inizializza la connessione
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
        
        String responseBody = responseHandler.handleResponse(response);
                
    	//Get the response headers
        responseHeaders = response.getAllHeaders();
         
        if(debugMode) {
            //Print connection properties
            printConnectionProperties();
        }
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
		
        //Return the headers and response body
        headersAndBodyResponse[0] = responseHeaders;
        headersAndBodyResponse[1] = responseBody;       
        return headersAndBodyResponse;     
		
	}
	
	
}