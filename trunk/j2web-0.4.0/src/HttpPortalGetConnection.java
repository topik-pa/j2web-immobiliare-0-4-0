import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;


public class HttpPortalGetConnection extends HttpPortalConnection {
	
	//Costruttore
	public HttpPortalGetConnection() {

	}
	
	
	//GET di una risorsa
	public Object[] get(String connectionDescription, String url, boolean debugMode) throws IOException {
		
		//La risposta che verrà restituita
		Object[] headersAndBodyResponse = new Object[2];
				
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
        
        String responseBody = responseHandler.handleResponse(response);
               
    	//Get the response headers
        responseHeaders = response.getAllHeaders();
           
        if(debugMode) {
            //Print connection properties
            printConnectionProperties(connectionDescription, httpget, responseHeaders, responseBody);
        }        
        
        //Close the request
        httpclient.getConnectionManager().shutdown();
               
        //Return the headers and response body
        headersAndBodyResponse[0] = responseHeaders;
        headersAndBodyResponse[1] = responseBody;       
        return headersAndBodyResponse;      
		
	}	
	
}