import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;


public class HttpPortalConnection implements parametriGenerali {
	
	//Variabili di connessione
	protected static final String USER_AGENT = USER_AGENT_VALUE;
	protected static String SESSIONCOOKIE_HEADER;
	protected static String SESSIONCOOKIE_NAME;
	protected static String SESSIONCOOKIE_VALUE;
	protected static String SESSIONCOOKIE_DOMAIN;
	
	protected HttpGet httpget;
	protected HttpPost httppost;
	protected HttpClient httpclient;
	protected HttpResponse response;
	protected BasicHeader requestHeader;
	protected ResponseHandler<String> responseHandler;
	protected Header[] requestHeaders;
	protected Header[] responseHeaders;
    
	//Costruttore
	public HttpPortalConnection() {
		httpclient = new DefaultHttpClient();
		responseHandler = new BasicResponseHandler();
	}
	
	//Stampa a video le proprietà della connessione corrente
	public void printConnectionProperties(String connectionDescription, HttpRequestBase httpRequest, Header[] responseHeaders, String responseBody) throws IOException {
		
		//Show Request URL
		System.out.println("\n\n\n");
        System.out.println("----------------------------------------");
        System.out.println("Executing request: " + connectionDescription);
        System.out.println("Request uri: " + httpRequest.getURI());
        System.out.println("----------------------------------------");
        
        //Show Request cookies
        System.out.println("Session cookie header: " + SESSIONCOOKIE_HEADER);
        System.out.println("Session cookie name: " + SESSIONCOOKIE_NAME);
        System.out.println("Session cookie value: " + SESSIONCOOKIE_VALUE);
        System.out.println("Session cookie domain: " + SESSIONCOOKIE_DOMAIN);
        System.out.println("----------------------------------------");
        
        //Show Request properties
        System.out.println("Request method: " + httpRequest.getMethod());
        System.out.println("Protocol version: " + httpRequest.getProtocolVersion());
        System.out.println("----------------------------------------");
        
        //Show request headers
        requestHeaders = httpRequest.getAllHeaders(); 
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
	 
	//Metodi Set per definire il cookie di sessione
	public void setSessionCookieDomain(String cookieDomain) {
		SESSIONCOOKIE_DOMAIN = cookieDomain;
	}
	
	public void setSessionCookieHeader(String cookieHeader) {
		SESSIONCOOKIE_HEADER = cookieHeader;
	}
	
	public void setSessionCookieName(String cookieName) {
		SESSIONCOOKIE_NAME = cookieName;
	}
	
	public void setSessionCookieValue(String cookieValue) {
		SESSIONCOOKIE_VALUE = cookieValue;
	}
	

}