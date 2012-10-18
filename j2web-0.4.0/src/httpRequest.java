/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


/**
 *
 * @author
 */

@SuppressWarnings({"rawtypes", "unchecked", "static-access", "unused"})
public class httpRequest {

  HttpURLConnection connection;
  OutputStream os = null;
  Map cookies = new HashMap();

  protected void connect() throws IOException {
    if (os == null) os = connection.getOutputStream();
  }

  protected void write(char c) throws IOException {
    connect();
    os.write(c);
  }

  protected void write(String s) throws IOException {
    connect();
    os.write(s.getBytes());
  }

  protected void newline() throws IOException {
    connect();
    write("\r\n");
  }

  protected void writeln(String s) throws IOException {
    connect();
    write(s);
    newline();
  }

  private static Random random = new Random();

  protected static String randomString() {
    return Long.toString(random.nextLong(), 36);
  }

  String boundary = "---------------------------" + randomString() + randomString() + randomString();

  private void boundary() throws IOException {
    write("--");
    write(boundary);

  }


  /**
   * Creates a new multipart POST HTTP request on a freshly opened URLConnection
   *
   * @param connection an already open URL connection
   * @throws IOException
   */
  public httpRequest(HttpURLConnection connection) throws IOException {

    this.connection = connection;

    //Parametri generali della connessione
    connection.setRequestMethod("POST");
    HttpURLConnection.setFollowRedirects(false);
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type","multipart/form-data; boundary=" + boundary);    
    //connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; boundary=" + boundary);
    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; it; rv:1.9.2.17) Gecko/20110422 Ubuntu/10.12 (maverick) Firefox/3.6.17"); // Do as if you're using Firefox 3.6.3.

  }


  /**
   * Creates a new multipart POST HTTP request for a specified URL
   *
   * @param url the URL to send request to
   * @throws IOException
   */
  public httpRequest(URL url) throws IOException {
	  
      this((HttpURLConnection) url.openConnection());

  }

  
  /**
   * Creates a new multipart POST HTTP request for a specified URL string
   *
   * @param urlString the string representation of the URL to send request to
   * @throws IOException
   */
  public httpRequest(String urlString) throws IOException {
    this(new URL(urlString));
  }


  
  
  public /*private*/ void postCookies() {
    StringBuffer cookieList = new StringBuffer();

    for (Iterator i = cookies.entrySet().iterator(); i.hasNext();) {
      Map.Entry entry = (Map.Entry)(i.next());
      cookieList.append(entry.getKey().toString() + "=" + entry.getValue());

      if (i.hasNext()) {
        cookieList.append("; ");
      }
    }
    if (cookieList.length() > 0) {
      connection.setRequestProperty("Cookie", cookieList.toString());
    }
  }

  
  /**
   * adds a cookie to the request
   * @param name cookie name
   * @param value cookie value
   * @throws IOException
   */
  public void setCookie(String name, String value) throws IOException {
    cookies.put(name, value);
  }

  
  /**
   * adds cookies to the request
   * @param cookies the cookie "name-to-value" map
   * @throws IOException
   */
  public void setCookies(Map cookies) throws IOException {
    if (cookies == null) return;
    this.cookies.putAll(cookies);
  }

  
  /**
   * adds cookies to the request
   * @param cookies array of cookie names and values (cookies[2*i] is a name, cookies[2*i + 1] is a value)
   * @throws IOException
   */
  public void setCookies(String[] cookies) throws IOException {
    if (cookies == null) return;
    for (int i = 0; i < cookies.length - 1; i+=2) {
      setCookie(cookies[i], cookies[i+1]);
    }
  }

  
  private void writeName(String name) throws IOException {
    newline();
    write("Content-Disposition: form-data; name=\"");
    write(name);
    write('"');
  }

  
  /**
   * adds a string parameter to the request
   * @param name parameter name
   * @param value parameter value
   * @throws IOException
   */
  public void setParameter(String name, String value) throws IOException {
    boundary();
    writeName(name);
    newline(); newline();
    writeln(value);
  }

  
  private static void pipe(InputStream in, OutputStream out) throws IOException {
    byte[] buf = new byte[500000];
    int nread;
    int navailable;
    int total = 0;
    synchronized (in) {
      while((nread = in.read(buf, 0, buf.length)) >= 0) {
        out.write(buf, 0, nread);
        total += nread;
      }
    }
    out.flush();
    buf = null;
  }

  
  /**
   * adds a file parameter to the request
   * @param name parameter name
   * @param filename the name of the file
   * @param is input stream to read the contents of the file from
   * @throws IOException
   */
  public void setParameter(String name, String filename, InputStream is) throws IOException {
    boundary();
    writeName(name);
    write("; filename=\"");
    write(filename);
    write('"');
    newline();
    write("Content-Type: ");
    String type = connection.guessContentTypeFromName(filename);
    if (type == null) type = "application/octet-stream";
    writeln(type);
    newline();
    pipe(is, os);
    newline();
  }

  
  /**
   * adds a file parameter to the request
   * @param name parameter name
   * @param file the file to upload
   * @throws IOException
   */
  public void setParameter(String name, File file) throws IOException {
    setParameter(name, file.getPath(), new FileInputStream(file));
  }

  
  /**
   * adds a parameter to the request; if the parameter is a File, the file is uploaded, otherwise the string value of the parameter is passed in the request
   * @param name parameter name
   * @param object parameter value, a File or anything else that can be stringified
   * @throws IOException
   */
  public void setParameter(String name, Object object) throws IOException {
    if (object instanceof File) {
      setParameter(name, (File) object);
    } else {
      setParameter(name, object.toString());
    }
  }

  
  /**
   * adds parameters to the request
   * @param parameters "name-to-value" map of parameters; if a value is a file, the file is uploaded, otherwise it is stringified and sent in the request
   * @throws IOException
   */
  public void setParameters(Map parameters) throws IOException {
    if (parameters == null) return;
    for (Iterator i = parameters.entrySet().iterator(); i.hasNext();) {
      Map.Entry entry = (Map.Entry)i.next();
      setParameter(entry.getKey().toString(), entry.getValue());
    }
  }

  
  /**
   * adds parameters to the request
   * @param parameters array of parameter names and values (parameters[2*i] is a name, parameters[2*i + 1] is a value); if a value is a file, the file is uploaded, otherwise it is stringified and sent in the request
   * @throws IOException
   */
  public void setParameters(Object[] parameters) throws IOException {
    if (parameters == null) return;
    for (int i = 0; i < parameters.length - 1; i+=2) {
      setParameter(parameters[i].toString(), parameters[i+1]);
    }
  }

  
  /**
   * posts the requests to the server, with all the cookies and parameters that were added
   * @return input stream with the server response
   * @throws IOException
   */
  public InputStream post() throws IOException {
    boundary();
    writeln("--");
    os.close();
    return connection.getInputStream();
  }

  
  /**
   * posts the requests to the server, with all the cookies and parameters that were added before (if any), and with parameters that are passed in the argument
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setParameters
   */
  public InputStream post(Map parameters) throws IOException {
    setParameters(parameters);
    return post();
  }

  
  /**
   * posts the requests to the server, with all the cookies and parameters that were added before (if any), and with parameters that are passed in the argument
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setParameters
   */
  public InputStream post(Object[] parameters) throws IOException {
    setParameters(parameters);
    return post();
  }

  
  /**
   * posts the requests to the server, with all the cookies and parameters that were added before (if any), and with cookies and parameters that are passed in the arguments
   * @param cookies request cookies
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setParameters
   * @see setCookies
   */
  public InputStream post(Map cookies, Map parameters) throws IOException {
    setCookies(cookies);
    setParameters(parameters);
    return post();
  }

  
  /**
   * posts the requests to the server, with all the cookies and parameters that were added before (if any), and with cookies and parameters that are passed in the arguments
   * @param cookies request cookies
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setParameters
   * @see setCookies
   */
  public InputStream post(String[] cookies, Object[] parameters) throws IOException {
    setCookies(cookies);
    setParameters(parameters);
    return post();
  }

  
  /**
   * post the POST request to the server, with the specified parameter
   * @param name parameter name
   * @param value parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public InputStream post(String name, Object value) throws IOException {
    setParameter(name, value);
    return post();
  }

  
  /**
   * post the POST request to the server, with the specified parameters
   * @param name1 first parameter name
   * @param value1 first parameter value
   * @param name2 second parameter name
   * @param value2 second parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public InputStream post(String name1, Object value1, String name2, Object value2) throws IOException {
    setParameter(name1, value1);
    return post(name2, value2);
  }

  
  /**
   * post the POST request to the server, with the specified parameters
   * @param name1 first parameter name
   * @param value1 first parameter value
   * @param name2 second parameter name
   * @param value2 second parameter value
   * @param name3 third parameter name
   * @param value3 third parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public InputStream post(String name1, Object value1, String name2, Object value2, String name3, Object value3) throws IOException {
    setParameter(name1, value1);
    return post(name2, value2, name3, value3);
  }

  
  /**
   * post the POST request to the server, with the specified parameters
   * @param name1 first parameter name
   * @param value1 first parameter value
   * @param name2 second parameter name
   * @param value2 second parameter value
   * @param name3 third parameter name
   * @param value3 third parameter value
   * @param name4 fourth parameter name
   * @param value4 fourth parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public InputStream post(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) throws IOException {
    setParameter(name1, value1);
    return post(name2, value2, name3, value3, name4, value4);
  }

  
  /**
   * posts a new request to specified URL, with parameters that are passed in the argument
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setParameters
   */
  public static InputStream post(URL url, Map parameters) throws IOException {
    return new ClientHttpRequest(url).post(parameters);
  }

  
  /**
   * posts a new request to specified URL, with parameters that are passed in the argument
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setParameters
   */
  public static InputStream post(URL url, Object[] parameters) throws IOException {
    return new ClientHttpRequest(url).post(parameters);
  }

  
  /**
   * posts a new request to specified URL, with cookies and parameters that are passed in the argument
   * @param cookies request cookies
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setCookies
   * @see setParameters
   */
  public static InputStream post(URL url, Map cookies, Map parameters) throws IOException {
    return new ClientHttpRequest(url).post(cookies, parameters);
  }

  
  /**
   * posts a new request to specified URL, with cookies and parameters that are passed in the argument
   * @param cookies request cookies
   * @param parameters request parameters
   * @return input stream with the server response
   * @throws IOException
   * @see setCookies
   * @see setParameters
   */
  public static InputStream post(URL url, String[] cookies, Object[] parameters) throws IOException {
    return new ClientHttpRequest(url).post(cookies, parameters);
  }

  
  /**
   * post the POST request specified URL, with the specified parameter
   * @param name parameter name
   * @param value parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public static InputStream post(URL url, String name1, Object value1) throws IOException {
    return new ClientHttpRequest(url).post(name1, value1);
  }

  
  /**
   * post the POST request to specified URL, with the specified parameters
   * @param name1 first parameter name
   * @param value1 first parameter value
   * @param name2 second parameter name
   * @param value2 second parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public static InputStream post(URL url, String name1, Object value1, String name2, Object value2) throws IOException {
    return new ClientHttpRequest(url).post(name1, value1, name2, value2);
  }

  
  /**
   * post the POST request to specified URL, with the specified parameters
   * @param name1 first parameter name
   * @param value1 first parameter value
   * @param name2 second parameter name
   * @param value2 second parameter value
   * @param name3 third parameter name
   * @param value3 third parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public static InputStream post(URL url, String name1, Object value1, String name2, Object value2, String name3, Object value3) throws IOException {
    return new ClientHttpRequest(url).post(name1, value1, name2, value2, name3, value3);
  }

  
  /**
   * post the POST request to specified URL, with the specified parameters
   * @param name1 first parameter name
   * @param value1 first parameter value
   * @param name2 second parameter name
   * @param value2 second parameter value
   * @param name3 third parameter name
   * @param value3 third parameter value
   * @param name4 fourth parameter name
   * @param value4 fourth parameter value
   * @return input stream with the server response
   * @throws IOException
   * @see setParameter
   */
  public static InputStream post(URL url, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) throws IOException {
    return new ClientHttpRequest(url).post(name1, value1, name2, value2, name3, value3, name4, value4);
  }



  
  //Metodi creati da Marco
  
  //Set the connection method (POST/GET)
  public void setconnectionProperty(String method) {
	  if(method.equals("POST")) {
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  } 
	    else { 
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }

  
  //Get the request method for the current connection
  public String getRequestMethod() {
	 	String requestMethod = "";
		requestMethod = connection.getRequestMethod();
	    return requestMethod;
	  }
  
  
  //Get the response string for the current connection
  public String getResponse() {
	 	String response = "";
		try {
			response = connection.getResponseMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return response;
	  }
  
  
  //Get the request headers
  public void getResponseHeaders() {
      String valore;
      String chiave;
      System.out.println("\n\nIntestazioni di risposta:");
      for(int i=0; i<25; i++) {
          valore = connection.getHeaderField(i);
          chiave = connection.getHeaderFieldKey(i);
          System.out.println(i + ": " + chiave + " + " + valore);
      }
  }
  
  //Get the Location header
  public String getLocationHeader() {
      String location="";
      for(int i=0; i<25; i++) {
    	  if(connection.getHeaderFieldKey(i)!=null) {
    		  if(connection.getHeaderFieldKey(i).contains("Location")) {
    			  location = connection.getHeaderField(i);
    		  }
    	  }
      }    			     		      	
	return location;
  }
  
  //Get the request properties
  public void getRequestHeaders() {
	  System.out.println("\n\nIntestazioni di richiesta:");
	  System.out.println(connection.getRequestMethod() + " " + connection.getURL() + " " );
	  
	  String Host = connection.getRequestProperty("Host");
      System.out.println("Host: " + Host);
      
      String User_Agent = connection.getRequestProperty("User-Agent");
      System.out.println("User-Agent: " + User_Agent);
      
      String Accept = connection.getRequestProperty("Accept");
      System.out.println("Accept: " + Accept);	  
	  
	  String from = connection.getRequestProperty("From");
      System.out.println("From: " + from);
      
      String Accept_Language = connection.getRequestProperty("Accept-Language");
      System.out.println("Accept-Language: " + Accept_Language);
      
      String Accept_Encoding = connection.getRequestProperty("Accept-Encoding");
      System.out.println("Accept-Encoding: " + Accept_Encoding);
      
      String Accept_Charset = connection.getRequestProperty("Accept-Charset");
      System.out.println("Accept-Charset: " + Accept_Charset);
      
      String Connection = connection.getRequestProperty("Connection");
      System.out.println("Connection: " + Connection);
      
      String Referer = connection.getRequestProperty("Referer");
      System.out.println("Referer: " + Referer);
      
      String Cookie = connection.getRequestProperty("Cookie");
      System.out.println("Cookie: " + Cookie);      
      
      System.out.println("\nAltri dati:");
      
      String Authorization = connection.getRequestProperty("Authorization");
      System.out.println("Authorization: " + Authorization);
      
      String Charge_To = connection.getRequestProperty("Charge-To");
      System.out.println("Charge-To: " + Charge_To);
      
      String If_Modified_Since = connection.getRequestProperty("If-Modified-Since");
      System.out.println("If-Modified-Since: " + If_Modified_Since);
      
      String Pragma = connection.getRequestProperty("Pragma");
      System.out.println("Pragma: " + Pragma);
     
      boolean userInteraction = connection.getAllowUserInteraction();
      System.out.println("User interaction: " + userInteraction);
      
      boolean followRedirects = HttpURLConnection.getFollowRedirects();
      System.out.println("Follow redirects: " + followRedirects);

      int connectTimeout = connection.getConnectTimeout();
      System.out.println("Connection timeout: " + connectTimeout);

      String encoding = connection.getContentEncoding();
      System.out.println("Encoding: " + encoding);

      int contentLenght = connection.getContentLength();
      System.out.println("Content Lenght: " + contentLenght);

      String contentType= connection.getContentType();
      System.out.println("Content Type: " + contentType);

      long date = connection.getDate();
      System.out.println("Date: " + date);

      long expiration = connection.getExpiration();
      System.out.println("Expiration: " + expiration);

      long lastModified = connection.getLastModified();
      System.out.println("Last Modified: " + lastModified);

      URL url = connection.getURL();
      System.out.println("URL: " + url);

    }
   

  public void setPostProperty(String name, String value) {
    connection.setRequestProperty(name, value);
  }

  
  public String getPostProperty(String name) {
    return connection.getRequestProperty(name);
  }
 

  public String getHeaderNameByIndex(int index) {
      String linea;
          linea = connection.getHeaderField(index);
          if (linea.contains("path=")) {
             int end = linea.indexOf("=");
             return linea.substring(0, end);
          }
          else {
              return linea;
        }
  }

  public String getHeaderValueByIndex(int index) {
      String linea;
          linea = connection.getHeaderField(index);
          if (linea.contains("path=")) {
              int start = linea.indexOf("=") + 1;
              int end = linea.indexOf(";");
              return linea.substring(start, end);
          }
          else {
              return linea;
        }
  }

  public String getCookieName() {       //Ritorna il nome del cookie in base al nome dell'header
      String cookie = "";
      String headerName=null;

      for (int i=1; (headerName = connection.getHeaderFieldKey(i))!=null; i++) {
        if (headerName.equals("Set-Cookie")) {
        	cookie = connection.getHeaderField(i);
          }
      }
        cookie = cookie.substring(0, cookie.indexOf(";"));
        String cookieName = cookie.substring(0, cookie.indexOf("="));
        String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
        
        return cookieName;
    }

  
  	public String getCookieValue() {       //Ritorna il valore del cookie in base al nome dell'header
      String cookie = "";
      String headerName=null;

      for (int i=1; (headerName = connection.getHeaderFieldKey(i))!=null; i++) {
        if (headerName.equals("Set-Cookie")) {
        	cookie = connection.getHeaderField(i);
          }
      }
        cookie = cookie.substring(0, cookie.indexOf(";"));
        String cookieName = cookie.substring(0, cookie.indexOf("="));
        String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());

        return cookieValue;
    }

    public String getCookieNameWithIndex(int index) {       //Ritorna il nome del cookie in base al numero di indice del campo
      String cookie = "";

      cookie = connection.getHeaderField(index);

      cookie = cookie.substring(0, cookie.indexOf(";"));
      String cookieName = cookie.substring(0, cookie.indexOf("="));
      String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());

      return cookieName;
    }

    
  public String getCookieValueWithIndex(int index) {       //Ritorna il valore del cookie in base al numero di indice del campo
      String cookie = "";

      cookie = connection.getHeaderField(index);

      cookie = cookie.substring(0, cookie.indexOf(";"));
      String cookieName = cookie.substring(0, cookie.indexOf("="));
      String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());

      return cookieValue;
    }

}