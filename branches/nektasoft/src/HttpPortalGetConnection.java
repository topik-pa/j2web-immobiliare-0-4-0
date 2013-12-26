import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpPortalGetConnection extends HttpPortalConnection {

	protected ResponseHandler<String> responseHandler;
	protected BasicCookieStore cookieStore;

	//Costruttore
	public HttpPortalGetConnection() {
		responseHandler = new BasicResponseHandler();
	}

	//GET di una risorsa
	public Object[] get(String connectionDescription, String url, List<NameValuePair> requestHeaders, List<BasicClientCookie> requestCookies, boolean debugMode) throws IOException {

		//La risposta che verrà  restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];

		//Inizializza la connessione
		httpget = new HttpGet(url);

		//Add request headers
		if(requestHeaders!=null) {
			BasicHeader newHeader;
			BasicNameValuePair currentHeaderListItem;
			Iterator<NameValuePair> headersIterator = requestHeaders.iterator();
			while(headersIterator.hasNext()) {
				currentHeaderListItem = (BasicNameValuePair) headersIterator.next();
				newHeader = new BasicHeader(currentHeaderListItem.getName(), currentHeaderListItem.getValue());
				httpget.addHeader(newHeader);
			}
		}		

		//Set the cookies
		if(requestCookies!=null) {
			cookieStore = new BasicCookieStore();

			//BasicHeader newHeader;
			BasicClientCookie currentCookie;
			Iterator<BasicClientCookie> cookiesIterator = requestCookies.iterator();
			while(cookiesIterator.hasNext()) {
				currentCookie = (BasicClientCookie) cookiesIterator.next();
				currentCookie.setDomain(SESSIONCOOKIE_DOMAIN);
				currentCookie.setPath("/"); 
				cookieStore.addCookie(currentCookie);

			}			
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

		//Get the response cookies
		if(requestCookies!=null) {
			responseCookies = cookieStore.getCookies();
		}

		if(debugMode) {
			//Print connection properties
			printConnectionProperties(connectionDescription, httpget, responseCookies, null, null, responseHeaders, responseBody);
		}        

		//Close the request
		httpclient.getConnectionManager().shutdown();

		//Return the headers and response body
		headersAndBodyResponseAndStatus[0] = responseHeaders;
		headersAndBodyResponseAndStatus[1] = responseBody;
		headersAndBodyResponseAndStatus[2] = response.getStatusLine();    
		return headersAndBodyResponseAndStatus;     

	}

	//GET di una risorsa
	@Deprecated
	public Object[] get(String connectionDescription, String url, boolean debugMode) throws IOException {

		//La risposta che verrÃ  restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];

		//Inizializza la connessione
		httpget = new HttpGet(url);

		//Add request headers
		userAgentHeader = new BasicHeader("User-Agent", parametriGenerali.USER_AGENT_VALUE);
		httpget.addHeader(userAgentHeader);

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

	//GET di una risorsa
	@Deprecated
	public Object[] get(String connectionDescription, String url, List<NameValuePair> requestHeaders, boolean debugMode) throws IOException {

		//La risposta che verrÃ  restituita
		Object[] headersAndBodyResponseAndStatus = new Object[3];

		//Inizializza la connessione
		httpget = new HttpGet(url);

		//Add request headers (comprende i cookies)
		BasicHeader newHeader;
		BasicNameValuePair currentHeaderListItem;
		Iterator<NameValuePair> headersIterator = requestHeaders.iterator();
		while(headersIterator.hasNext()) {
			currentHeaderListItem = (BasicNameValuePair) headersIterator.next();
			if(currentHeaderListItem.getName()!= "User-Agent") { //Non aggiungo unn ulteriore header User-Agent
				newHeader = new BasicHeader(currentHeaderListItem.getName(), currentHeaderListItem.getValue());
				httpget.addHeader(newHeader);
			}
		}
		//L'header User-Agent Ã¨ aggiunto sempre in modo predefinito
		userAgentHeader = new BasicHeader("User-Agent", parametriGenerali.USER_AGENT_VALUE);
		httpget.addHeader(userAgentHeader);

		//Set the cookies
		if(isSessionCookieSet==true) {
			BasicCookieStore cookieStore = new BasicCookieStore(); 

			BasicClientCookie cookie = new BasicClientCookie(SESSIONCOOKIE_NAME, SESSIONCOOKIE_VALUE);
			cookie.setDomain(SESSIONCOOKIE_DOMAIN);
			cookie.setPath("/"); 

			BasicClientCookie cookie2 = new BasicClientCookie("bottega", "mc1xd556c15d3430e17411fd1fdf1aa880dd0c09666a");
			cookie2.setDomain(SESSIONCOOKIE_DOMAIN);
			cookie2.setPath("/");

			BasicClientCookie cookie3 = new BasicClientCookie("account", "mc1x50d3f5d11689e02d890a11d4a78c8420fb083e26");
			cookie3.setDomain(SESSIONCOOKIE_DOMAIN);
			cookie3.setPath("/");

			cookieStore.addCookie(cookie);
			cookieStore.addCookie(cookie2); 
			cookieStore.addCookie(cookie3); 
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