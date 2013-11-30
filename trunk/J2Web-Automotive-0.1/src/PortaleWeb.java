import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.Iterator;




/*
 * Questa classe definische i metodi e gli attributi dell'oggetto portale web, qui definiti in termini generici, vengono riscritti nelle classi piÃ¹ specifiche
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

public abstract class PortaleWeb implements parametriGenerali {

	//Attributi
	ImageIcon icon;
	String valoreLabel;
	String idPortale;
	boolean isActive;	

	//Cookie
	protected static String SESSIONCOOKIEHEADER;
	protected static String SESSIONCOOKIENAME;
	protected static String SESSIONCOOKIEVALUE;
	protected static String SESSIONCOOKIEDOMAIN;

	//La label che identifica i parametri da non inviare
	protected static String dontSendThisParam = "***DONOTSEND***";

	public PortaleWeb (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {

		this.icon = icon;
		this.valoreLabel = valoreLabel;
		this.idPortale = idPortale;
		this.isActive = isActive;

	}


	//Metodi

	//Inserimento scheda (sovrascritto nelle sottoclassi)
	public abstract boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException, UnsupportedEncodingException;

	//Visualizzazione scheda (sovrascritto nelle sottoclassi)
	public abstract boolean visualizzaScheda(SchedaVeicolo scheda) throws HttpCommunicationException;

	//Eliminazione scheda (sovrascritto nelle sottoclassi)
	public abstract boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException;

	//Invio mail di conferma inserzione
	static void sendConfirmationMail(SchedaVeicolo scheda, String nomePortale, String codInserzione)   {

		final String USERNAME = BACKEND_EMAIL;
		final String PASSWORD = BACKEND_EMAIL_PSW;
		final String RECIPENTS = BACKEND_EMAIL + "," + EMAIL_UTENTE;
		final String SUBJECT = "Scheda inserita: " + scheda.codiceScheda + " " + scheda.marcaVeicolo + " " + scheda.modelloVeicolo + " " + scheda.versioneVeicolo ;

		final String SMTP_HOST = BACKEND_EMAIL_SMTP_HOST;
		final int SMTP_PORT = BACKEND_EMAIL_SMTP_PORT;
		final Session session = Session.getInstance(System.getProperties(), null);
		final Message msg = new MimeMessage(session);
		final String senderEmail = USERNAME.contains("@") ? USERNAME : (USERNAME + BACKEND_EMAIL_DOMAIN);

		String textBody = "";	  	
		textBody += "E' stata inserita una scheda nel portale: " + nomePortale + ".";
		textBody += "Il codice della scheda Ã¨: \n" + scheda.codiceScheda + "/n" + "Il codice di inserimento Ã¨: /n" + codInserzione;


		try {
			msg.setFrom(new InternetAddress(senderEmail));
			final Address[] recipientAddresses = InternetAddress.parse(RECIPENTS);
			msg.setRecipients(Message.RecipientType.TO, recipientAddresses);

			msg.setSentDate(new Date());
			msg.setSubject(SUBJECT);

			msg.setText("Dati scheda: \n\n " + textBody);

			final Transport transport = session.getTransport("smtps");
			transport.connect(SMTP_HOST, SMTP_PORT, USERNAME, PASSWORD);
			transport.sendMessage(msg, recipientAddresses);
			transport.close();

		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("sendConfirmationMail"), "AddressException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("sendConfirmationMail"), "MessagingException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	//Valutazione similaritÃ  tra stringhe
	public static List<char[]> bigram(String input)
	{
		ArrayList<char[]> bigram = new ArrayList<char[]>();
		for (int i = 0; i < input.length() - 1; i++)
		{
			char[] chars = new char[2];
			chars[0] = input.charAt(i);
			chars[1] = input.charAt(i+1);
			bigram.add(chars);
		}
		return bigram;
	}      
	public static double dice(List<char[]> bigram1, List<char[]> bigram2)
	{
		List<char[]> copy = new ArrayList<char[]>(bigram2);
		int matches = 0;
		for (int i = bigram1.size(); --i >= 0;)
		{
			char[] bigram = bigram1.get(i);
			for (int j = copy.size(); --j >= 0;)
			{
				char[] toMatch = copy.get(j);
				if (bigram[0] == toMatch[0] && bigram[1] == toMatch[1])
				{
					copy.remove(j);
					matches += 2;
					break;
				}
			}
		}
		return (double) matches / (bigram1.size() + bigram2.size());
	}

	//Trova il cookie di sessione
	public boolean findSessionCookie(Header[] headers, String cookieName, String cookieDomain) {

		boolean cookieHeaderFound = false;

		String cookie_header = "";
		String cookie_value = "";

		for(int i=0; i<headers.length; i++) {       	
			Header currentHeader = headers[i];
			//Get cookie
			if(currentHeader.getName().contains("Set-Cookie")) {

				cookie_header = currentHeader.getValue();
				int end = cookie_header.indexOf("=");
				String cookie_name = cookie_header.substring(0, end);                   
				int start = end + 1;
				end = cookie_header.indexOf(";");
				cookie_value = cookie_header.substring(start, end);

				if(cookie_name.equals(cookieName)) {
					//Cookie di sessione trovato
					cookieHeaderFound = true;            		

					//Aggiorno i parametri dei cookie (del portale chiamante)
					SESSIONCOOKIEHEADER = cookie_header;
					SESSIONCOOKIEVALUE = cookie_value;
					
					//Stampo i valori trovati
					System.out.println("Method: findSessionCookie \n" + "cookie_header-->"+cookie_header + "\ncookieName-->"+cookieName + "\ncookie_value-->"+cookie_value + "\ncookieDomain-->"+cookieDomain);
				}   
			}       	
		}		

		//Valori di ritorno
		return cookieHeaderFound?true:false;
	}

	public boolean setCookies(Header[] inputHeaders, List<BasicClientCookie> outputHeaders) {
		
		boolean cookiesFound = false;
		
		String current_cookie_header;
		String current_cookie_name;
		String current_cookie_value;
		BasicClientCookie currentCookie;
		
		for(int i=0; i<inputHeaders.length; i++) {       	
			Header currentHeader = inputHeaders[i];
			//Get cookie
			if(currentHeader.getName().contains("Set-Cookie")) {
				cookiesFound = true;
				
				current_cookie_header = currentHeader.getValue();
				int end = current_cookie_header.indexOf("=");
				current_cookie_name = current_cookie_header.substring(0, end);                   
				int start = end + 1;
				end = current_cookie_header.indexOf(";");
				current_cookie_value = current_cookie_header.substring(start, end);
				
				//Stampo i valori trovati
				System.out.println("Method: setCookies \n" + "cookie_header-->"+current_cookie_header + "\ncookieName-->"+current_cookie_name + "\ncookie_value-->"+current_cookie_value);

				currentCookie = new BasicClientCookie(current_cookie_name, current_cookie_value);
				outputHeaders.add(currentCookie);
			}       	
		}
		
		return cookiesFound;
		
	}
	
	//Ritorna il valore di una header dato il nome dell'header stesso
	public String getHeaderValueByName(Header[] headers, String headerName) {

		String headerValue = "Header non trovato";

		//boolean headerFound = false;
		for(int i=0; i<headers.length; i++) {       	
			Header currentHeader = headers[i];
			//Get cookie
			if(currentHeader.getName().contains(headerName)) {     		        		
				headerValue = currentHeader.getValue();	
				//Stampo i valori trovati
				System.out.println("Method: getHeaderValueByName \n" + "headerValue-->" + headerValue);
			}       	
		}       
		//Valore tornato
		return headerValue;
	}

	//Metodo per ottenere le coordinate della cittÃ 
	public Map<String,String> getLatLonCoord(String indirizzo, String comune, String provincia, String regione) throws ParserConfigurationException, SAXException, IOException {
		Map<String,String> mappaLatLon = new Hashtable<String,String>();
		String url;
		String latitudine;
		String longitudine;
		Document doc = null;
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		NodeList nodelist;
		Node latitude = null, longitude = null;

		db = dbf.newDocumentBuilder();
		url = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address=" + URLEncoder.encode(indirizzo, "UTF-8") + "%20" + URLEncoder.encode(comune, "UTF-8") + "%20" + URLEncoder.encode(provincia, "UTF-8") + "%20" + URLEncoder.encode(regione, "UTF-8") + "%20" + "Italia";	
		doc = db.parse(url);

		doc.getDocumentElement().normalize();
		nodelist = doc.getElementsByTagName("location");
		if(nodelist!=null) {
			Node locationNode = nodelist.item(0);
			NodeList locationNodeChilds = locationNode.getChildNodes();
			latitude = locationNodeChilds.item(1);
			longitude = locationNodeChilds.item(3);
		} 
		latitudine = latitude.getTextContent();
		longitudine = longitude.getTextContent();

		mappaLatLon.put("latitudine", latitudine);
		mappaLatLon.put("longitudine", longitudine);

		return mappaLatLon;       	
	}

	//Metodo per ottenere il valore di un input dato il nome dell'input stesso
	public String getInputValueByName(String responseBody, String inputName) {
		String inputValue = null;

		org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);
		Elements inputElements = doc.getElementsByTag("input");
		if(inputElements!=null) {
			Iterator<Element> iterator = inputElements.iterator();
			while(iterator.hasNext()) {
				Element currentElement = iterator.next();
				if(currentElement.attr("name").equals(inputName)) {
					inputValue = currentElement.attr("value");
				}
			}
		}
		System.out.println("Method: getInputValueByName: " + inputName + " --> " + inputValue);
		return inputValue; 
	}

	//Metodo per evitare l'invio di parametri
	public List<NameValuePair> removeNotUsedParams(List<NameValuePair> paramList) {	

		List<NameValuePair> cleanedList = paramList;

		Iterator<NameValuePair> iterator = cleanedList.iterator();
		while(iterator.hasNext()) {
			BasicNameValuePair currentParam = (BasicNameValuePair) iterator.next();
			if(currentParam.getValue() == dontSendThisParam)  {
				iterator.remove();	
			}
		}
		return cleanedList;
	}

	//Ritorna il valore di un input dato il valore nella scheda e l'elemento input del DOM
  	public String getParamValue(String valueScheda, Element domElement) {
  		
  		String returnValue = "";
  		
  		switch (domElement.nodeName()) {
		case "select":		
			Elements childrens = domElement.children();
			if(childrens.isEmpty()) {
            	return "Nessun elemento option";
            }
			
			Iterator<Element> iterator = childrens.iterator();
			List<char[]> stringaScheda = bigram(valueScheda.toLowerCase());
        	double resultComparation = 0;
        	while(iterator.hasNext()) {
            	Element currentElement = iterator.next();
            	List<char[]> stringaPortale = bigram(currentElement.text().toLowerCase());        		
        		double actualResultComparation = dice(stringaPortale, stringaScheda);
        		System.out.println("comp: " + actualResultComparation);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			returnValue = currentElement.attr("value");            		
        		}       		
        	}
			break;
			
		case "input":
			if(domElement.attr("type").equals("text") || domElement.attr("type").equals("password") || domElement.attr("type").equals("submit") || domElement.attr("type").equals("hidden")) {
				returnValue = valueScheda;
			}
			break;
			
		case "textarea":
			returnValue = valueScheda;
			break;
			
		case "button":
			returnValue = valueScheda;
			break;
			
		default:
			System.out.println("Method getParamValue: " +  "input non elaborato-->" + domElement.nodeName());
			
		}
  		
  		return returnValue;
  	}

  	//Valuta i parametri presenti nella tabella di dipendenza
  	public void valutaParametri(String dom, String selettore, Map<String,String> inputMap, Map<String,String> outputMap) {
		
		String paramName;
		String paramValue;
		String dipendenza;
		
		org.jsoup.nodes.Document doc = Jsoup.parse(dom);
		Elements inputElements = doc.select(selettore);
		
		if(inputElements!=null) {
			Iterator<Element> iterator = inputElements.iterator();
			while(iterator.hasNext()) {
				Element currentElement = iterator.next();
				paramName = currentElement.attr("name");
				dipendenza = inputMap.get(paramName);
				if(dipendenza != null) {
					paramValue = getParamValue(dipendenza, currentElement);
					outputMap.put(paramName, paramValue);
				}
				else {
					System.out.println("Method valutaParametri: " +  "input non presente nella tabella di dipendenza-->" + currentElement.attr("name") + "(" + currentElement.nodeName() + ")");
				}
			}	
		}
		System.out.println("Method valutaParametri : " +  "mappaDeiParametri-->" + outputMap.toString());
	}
  	
  	//Prepara i parametri POST da inviare nella connessione corrente
  	public void setPostParameters(Map<String,String> inputMap, List<NameValuePair> outputList) {
  		if(!inputMap.isEmpty()) {
			Iterator<Entry<String, String>> iterator = inputMap.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<String,String> currentParam = (Map.Entry<String,String>)iterator.next();
				outputList.add(new BasicNameValuePair((String)currentParam.getKey(), (String)currentParam.getValue()));			
			}	
		}
  	}
  	
  	
}