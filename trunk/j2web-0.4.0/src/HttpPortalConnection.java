import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;


public class HttpPortalConnection implements parametriGenerali {
	
	//Variabili di connessione
	protected static final String USER_AGENT = USER_AGENT_VALUE;
	protected static String SESSIONCOOKIE_HEADER;
	protected static String SESSIONCOOKIE_NAME;
	protected static String SESSIONCOOKIE_VALUE;
	protected static String SESSIONCOOKIE_DOMAIN;
	protected static boolean isSessionCookieSet = false;
	
	protected HttpGet httpget;
	protected HttpPost httppost;
	protected HttpClient httpclient;
	protected HttpResponse response;
	protected BasicHeader requestHeader;
	/*protected ResponseHandler<String> responseHandler;*/
	protected Header[] requestHeaders;
	protected Header[] responseHeaders;
    
	//Costruttore
	public HttpPortalConnection() {
		httpclient = new DefaultHttpClient();
		/*responseHandler = new BasicResponseHandler();*/
	}
	
	//Stampa a video le proprietà della connessione corrente
	public void printConnectionProperties(String connectionDescription, HttpRequestBase httpRequest, Header[] responseHeaders, String responseBody, List<NameValuePair> postParameters) throws IOException {
		
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
        
        if(postParameters!=null) {
        	//Show post parameters
            System.out.println("Post parameters: " + "\n");
            Iterator<NameValuePair> iterator = postParameters.iterator();
            while(iterator.hasNext()) {
            	BasicNameValuePair currentParam = (BasicNameValuePair) iterator.next();
            	System.out.println(currentParam.getName() + "-->" + currentParam.getValue());  	
            }
            System.out.println("----------------------------------------");
        }     
        
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
	public void setSessionCookie(String cookieHeader, String cookieName, String cookieValue, String cookieDomain) {
		SESSIONCOOKIE_DOMAIN = cookieDomain;
		SESSIONCOOKIE_HEADER = cookieHeader;
		SESSIONCOOKIE_NAME = cookieName;
		SESSIONCOOKIE_VALUE = cookieValue;
		isSessionCookieSet = true;
	}

}