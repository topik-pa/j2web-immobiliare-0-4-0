/**
 * Questa classe definisce i metodi e gli attributi dell'oggetto portale web, qui definiti in termini generici, vengono riscritti nelle classi più specifiche
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.Iterator;


public abstract class PortaleWeb implements parametriGenerali {

	//Attributi
	ImageIcon icon;
	String valoreLabel;
	String idPortale;
	boolean isActive;	

	//Cookie
	/*protected static String SESSIONCOOKIEHEADER;
	protected static String SESSIONCOOKIENAME;
	protected static String SESSIONCOOKIEVALUE;
	protected static String SESSIONCOOKIEDOMAIN;*/

	//La label che identifica i parametri da non inviare
	protected static String dontSendThisParam = "***DONOTSEND***";

	//Costruttore
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

	//Invio mail di conferma inserzione (al cliente e al backend)
	static void sendConfirmationMail(SchedaVeicolo scheda, String nomePortale, String codInserzione)   {

		final String USERNAME = BACKEND_EMAIL;
		final String PASSWORD = BACKEND_EMAIL_PSW;
		final String RECIPENTS = BACKEND_EMAIL + "," + EMAIL_UTENTE;
		final String SUBJECT = "Scheda inserita: " + scheda.codiceScheda + " -- " + scheda.marcaVeicolo + " " + scheda.modelloVeicolo + " " + scheda.versioneVeicolo;

		final String SMTP_HOST = BACKEND_EMAIL_SMTP_HOST;
		final int SMTP_PORT = BACKEND_EMAIL_SMTP_PORT;
		final Session session = Session.getInstance(System.getProperties(), null);
		final Message msg = new MimeMessage(session);
		final String senderEmail = USERNAME.contains("@") ? USERNAME : (USERNAME + BACKEND_EMAIL_DOMAIN);

		String textBody = "";	  	
		textBody +="E' stata inserita una scheda nel portale: " + nomePortale + ".\n";
		textBody +="\n\nDati scheda:\n";
		textBody +="Codice scheda: " + scheda.codiceScheda;
		textBody +="\nCodice di inserimento nel portale: " + codInserzione;
		textBody +="\nVeicolo: " + scheda.marcaVeicolo + " " + scheda.modelloVeicolo + " " + scheda.versioneVeicolo;
		textBody +="\nPortale di inserimento: " + nomePortale;


		try {
			msg.setFrom(new InternetAddress(senderEmail));
			final Address[] recipientAddresses = InternetAddress.parse(RECIPENTS);
			msg.setRecipients(Message.RecipientType.TO, recipientAddresses);

			msg.setSentDate(new Date());
			msg.setSubject(SUBJECT);

			msg.setText(textBody);

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

	//Valutazione similarità  tra stringhe
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

	//Trova il cookie di sessione e lo esporta a livello di classe portale
	/*@Deprecated
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
				if(cookie_header.contains(";")) {
					end = cookie_header.indexOf(";");
				}
				else {
					end = cookie_header.length();
				}
				cookie_value = cookie_header.substring(start, end);

				if(cookie_name.equals(cookieName)) {
					//Cookie di sessione trovato
					cookieHeaderFound = true;            		

					//Aggiorno i parametri dei cookie (del portale chiamante)
					SESSIONCOOKIENAME = cookieName;
					SESSIONCOOKIEDOMAIN = cookieDomain;
					SESSIONCOOKIEHEADER = cookie_header;
					SESSIONCOOKIEVALUE = cookie_value;

					//Stampo i valori trovati
					System.out.println("Method: findSessionCookie \n" + "cookie_header-->"+cookie_header + "\ncookieName-->"+cookieName + "\ncookie_value-->"+cookie_value + "\ncookieDomain-->"+cookieDomain);
				}   
			}       	
		}		

		//Valori di ritorno
		return cookieHeaderFound?true:false;
	}*/

	//Ottiene i cookie
	/*@Deprecated
	public boolean setCookies(Header[] inputHeaders, List<BasicClientCookie> outputCookies) {

		boolean cookiesFound = false;

		String current_cookie_header;
		String current_cookie_name;
		String current_cookie_value;
		String current_cookie_path;
		String current_cookie_domain = null;
		String cookie_header_substring;
		BasicClientCookie currentCookie;

		for(int i=0; i<inputHeaders.length; i++) {       	
			Header currentHeader = inputHeaders[i];
			//Get cookie
			if(currentHeader.getName().contains("Set-Cookie")) {
				cookiesFound = true;

				//header completo del cookie
				current_cookie_header = currentHeader.getValue();
				int end = current_cookie_header.indexOf("=");

				//nome del cookie
				current_cookie_name = current_cookie_header.substring(0, end);                   

				//valore del cookie
				int start = end + 1;
				if(current_cookie_header.contains(";")) {
					end = current_cookie_header.indexOf(";");
				}
				else {
					end = current_cookie_header.length();
				}
				current_cookie_value = current_cookie_header.substring(start, end);

				//path del cookie
				if(current_cookie_header.contains("path=")) {
					start = current_cookie_header.indexOf("path=");				
					cookie_header_substring = current_cookie_header.substring(start);
					end = cookie_header_substring.indexOf(";");
					if(end==-1) {
						end = cookie_header_substring.length();
					}
					current_cookie_path = cookie_header_substring.substring(5, end);
				}
				else {
					current_cookie_path = "/";
				}	

				//domain del cookie
				if(current_cookie_header.contains("domain=")) {
					start = current_cookie_header.indexOf("domain=");				
					cookie_header_substring = current_cookie_header.substring(start);
					end = cookie_header_substring.indexOf(";");
					if(end==-1) {
						end = cookie_header_substring.length();
					}
					current_cookie_domain = cookie_header_substring.substring(7, end);
				}
				else {
					current_cookie_domain = "www.cuboauto.it";
				}


				currentCookie = new BasicClientCookie(current_cookie_name, current_cookie_value);
				currentCookie.setPath(current_cookie_path);
				if(current_cookie_domain!=null) {
					currentCookie.setDomain(current_cookie_domain);
				}

				outputCookies.add(currentCookie);
			}       	
		}

		//Stampo i valori trovati
		System.out.println("Method: setCookies \n" + outputCookies);

		return cookiesFound;

	}*/


	//Ottiene i cookie
	public boolean setCookies(Header[] inputHeaders, List<BasicClientCookie> outputCookies, String defaultPath, String defaultDomain) {

		boolean cookiesFound = false;

		String current_cookie_header;
		String current_cookie_name;
		String current_cookie_value;
		String current_cookie_path;
		String current_cookie_domain = null;
		String cookie_header_substring;
		BasicClientCookie currentCookie;

		for(int i=0; i<inputHeaders.length; i++) {       	
			Header currentHeader = inputHeaders[i];
			//Get cookie
			if(currentHeader.getName().contains("Set-Cookie")) {
				cookiesFound = true;

				//header completo del cookie
				current_cookie_header = currentHeader.getValue();
				int end = current_cookie_header.indexOf("=");

				//nome del cookie
				current_cookie_name = current_cookie_header.substring(0, end);                   

				//valore del cookie
				int start = end + 1;
				if(current_cookie_header.contains(";")) {
					end = current_cookie_header.indexOf(";");
				}
				else {
					end = current_cookie_header.length();
				}
				current_cookie_value = current_cookie_header.substring(start, end);

				//path del cookie
				if(current_cookie_header.contains("path=")) {
					start = current_cookie_header.indexOf("path=");				
					cookie_header_substring = current_cookie_header.substring(start);
					end = cookie_header_substring.indexOf(";");
					if(end==-1) {
						end = cookie_header_substring.length();
					}
					current_cookie_path = cookie_header_substring.substring(5, end);
				}
				else {
					current_cookie_path = defaultPath;
				}	

				//domain del cookie
				if(current_cookie_header.contains("domain=")) {
					start = current_cookie_header.indexOf("domain=");				
					cookie_header_substring = current_cookie_header.substring(start);
					end = cookie_header_substring.indexOf(";");
					if(end==-1) {
						end = cookie_header_substring.length();
					}
					current_cookie_domain = cookie_header_substring.substring(7, end);
				}
				else {
					current_cookie_domain = defaultDomain;
				}

				//Creazione del nuovo cookie
				currentCookie = new BasicClientCookie(current_cookie_name, current_cookie_value);
				currentCookie.setPath(current_cookie_path);
				if(current_cookie_domain!=null) {
					currentCookie.setDomain(current_cookie_domain);
				}

				//Inserimento nella struttura in output (eventuali cookie con lo stesso nome vengono sovrascritti)
				if(!outputCookies.isEmpty()) {
					List<BasicClientCookie> cookiesToRemove = new ArrayList<BasicClientCookie>();
					List<BasicClientCookie> cookiesToAdd = new ArrayList<BasicClientCookie>();
					Iterator<BasicClientCookie> iterator = outputCookies.iterator();
					while(iterator.hasNext()) {
						BasicClientCookie currentSavedCookie = iterator.next();
						if(currentSavedCookie.getName().equals(currentCookie.getName())) {
							cookiesToRemove.add(currentSavedCookie);
							cookiesToAdd.add(currentCookie);
						}
						else {
							cookiesToAdd.add(currentCookie);
						}
					}
					outputCookies.removeAll(cookiesToRemove);
					outputCookies.addAll(cookiesToAdd);
				}
				else {
					outputCookies.add(currentCookie);
				}

			}       	
		}

		//Stampo i valori trovati
		System.out.println("Method: setCookies \n" + outputCookies);

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

	//Metodo per ottenere le coordinate della città
	/*public Map<String,String> getLatLonCoord(String indirizzo, String comune, String provincia, String regione) throws ParserConfigurationException, SAXException, IOException {
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
	}*/

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
	/*@Deprecated
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
	}*/

	//Metodo per evitare l'invio di parametri non voluti
	public Map<String, String> removeNotUsedParams(Map<String, String> paramMap) {	

		Map<String, String> cleanedMap = paramMap;

		Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, String>  currentParam = (Entry<String, String>) iterator.next();
			if(currentParam.getValue() == dontSendThisParam || currentParam.getKey() == "")  {
				iterator.remove();	
			}
		}
		return cleanedMap;
	}

	//Ritorna il valore di un input dato il valore nella scheda e l'elemento input del DOM
	public String getParamValue(String valueScheda, Element domElement) {

		String returnValue = "";

		switch (domElement.nodeName()) {
		//Se è una select, il valore ritornato è la value della option che più assomiglia al valore della scheda
		case "select":	

			if(valueScheda.equals(dontSendThisParam)) {
				returnValue = valueScheda;
			}
			else {
				String nameElemento = domElement.attr("name");
				Elements childrens = domElement.children();
				if(childrens.isEmpty()) {
					return "Nessun elemento option";
				}

				Iterator<Element> iterator = childrens.iterator();
				String valueSchedaMinuscolo = valueScheda.toLowerCase();
				List<char[]> stringaScheda = bigram(valueSchedaMinuscolo);
				double resultComparation = 0;
				while(iterator.hasNext()) {
					Element currentElement = iterator.next();
					String valueCurrentElemMinuscolo = currentElement.text().toLowerCase();
					List<char[]> stringaPortale = bigram(valueCurrentElemMinuscolo);        		
					double actualResultComparation = dice(stringaPortale, stringaScheda);
					System.out.println("Comparazione " + nameElemento + " " + valueSchedaMinuscolo + "/" + valueCurrentElemMinuscolo + ": " + actualResultComparation);
					if(actualResultComparation>resultComparation) {
						resultComparation = actualResultComparation;
						returnValue = currentElement.attr("value");            		
					}       		
				}
			}		
			break;
			//Se si tratta di un input, di una textarea o di un button: ritora il valore della scheda senza ulteriori modifiche	
		case "input":
			returnValue = valueScheda;
			break;

		case "textarea":
			returnValue = valueScheda;
			break;

		case "button":
			returnValue = valueScheda;
			break;

		default:
			System.out.println("Method getParamValue: " +  "attenzione - input non elaborato-->" + domElement.nodeName());

		}

		return returnValue;
	}

	//Valuta i parametri presenti nella tabella di dipendenza
	public void valutaParametri(String dom, String selettore, Map<String,String> inputMap, Map<String,String> outputMap) {

		//Nome/valore degli input esaminati e valore associato all'input stesso
		String paramName;
		String paramValue;
		String paramType;
		String dipendenza;

		//Parsing del DOM e estrazione degli input
		org.jsoup.nodes.Document doc = Jsoup.parse(dom);
		Elements inputElements = doc.select(selettore);

		if(inputElements!=null) {
			Iterator<Element> iterator = inputElements.iterator();
			while(iterator.hasNext()) {
				Element currentElement = iterator.next();
				paramName = currentElement.attr("name");
				paramValue = currentElement.attr("value");
				paramType = currentElement.attr("type");
				dipendenza = inputMap.get(paramName); //controllo se l'input corrente ha un valore associato nella mappaAssociativa			

				//Se esiste un valore di dipendenza, allora ricalcola il value del parametro
				if(dipendenza != null) {
					paramValue = getParamValue(dipendenza, currentElement); //il valore dell'elemento corrente deve essere calcolato in base ai valori presenti nella scheda veicolo
					outputMap.put(paramName, paramValue);
				}
				//Se il parametro non ha dipendenze (prende il valore del DOM) inseriscilo sse non si tratta di una checkbox
				if(dipendenza==null && !paramType.equalsIgnoreCase("checkbox")) {
					outputMap.put(paramName, paramValue);
				}

			}	
		}

		removeNotUsedParams(outputMap);
		System.out.println("Method valutaParametri (parametri inviati nella connessione attuale): " +  "mappaDeiParametri-->" + outputMap.toString());
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

	//Adatta le select del DOM originario per renderle compatibili a quelle di j2web
	public org.jsoup.nodes.Document adattaSelect(org.jsoup.nodes.Document doc, String selettore, List<NameValuePair> listaAssociativa) {

		Element select = ((Element) doc).select(selettore).first();
		Elements options = select.children();

		Iterator<Element> iterator = options.iterator();
		while(iterator.hasNext()) {
			iterator.next().remove();
		}

		Iterator<NameValuePair> iterator2 = listaAssociativa.iterator();
		while(iterator2.hasNext()) {

			NameValuePair currentListElement = iterator2.next();

			String newOptionElement = "<option value=\"" + currentListElement.getName() + "\">" + currentListElement.getValue() + "</option>"; 
			select.append(newOptionElement);
		}

		return doc;

	}

	//Ritorna il valore di un cookie data una lista di cookie e il nome del cookie stesso
	public String returnCookieValue(List<BasicClientCookie> cookieList, String cookieName) {

		String cookieValue = "";

		Iterator<BasicClientCookie> iterator = cookieList.iterator();
		while(iterator.hasNext()) {

			BasicClientCookie currentCookie = iterator.next();

			if(currentCookie.getName().equals(cookieName)) {
				cookieValue = currentCookie.getValue();
			}
		}

		return cookieValue;

	}

	//Pulizia delle strutture dati passate
	public void clearStruttureDati(Map<String, String> input1, Map<String, String> input2, List<NameValuePair> input3) {
		input1.clear();
		input2.clear();
		input3.clear();
	}

	//Reset e inizializzazione degli headers
	public void inizializzaHeaders(List<NameValuePair> requestHeaders, String Host) {
		requestHeaders.clear();
		requestHeaders.add(new BasicNameValuePair("Host", Host));
		requestHeaders.add(new BasicNameValuePair("User-Agent", USER_AGENT_VALUE));	
		requestHeaders.add(new BasicNameValuePair("Connection", CONNECTION));
		requestHeaders.add(new BasicNameValuePair("Cache-Control", CACHE_CONTROL));
		requestHeaders.add(new BasicNameValuePair("Accept-Language", ACCEPT_LANGUAGE));
		requestHeaders.add(new BasicNameValuePair("Accept", ACCEPT));
	}

	//Messaggio inserimento annuncio OK
	public void messageInserimentoOK(String nomePortale) {
		JOptionPane.showMessageDialog(null, "Scheda veicolo inserita in: " + nomePortale, "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);
	}

	//Messaggio inserimento annuncio KO
	public void messageInserimentoKO(String nomePortale) {
		JOptionPane.showMessageDialog(null, "Impossibile sincronizzare l'annuncio o annuncio non compatibile con il portale: " + nomePortale + ".\n\nVerificare che:\nLa combinazione marca/modello sia prevista nel portale di inserimento\nNon si sia raggiunto il limite di annunci pubblicabili\nSi stiano rispettando i vincoli di inserimento del portale (per esempio: numero minimo e dimensioni delle immagini da pubblicare)", "Errore", JOptionPane.ERROR_MESSAGE);
	}

	//Messaggio eliminazione annuncio OK
	public void messageEliminazioneOK(String nomePortale) {
		JOptionPane.showMessageDialog(null, "Scheda veicolo eliminata da: " + nomePortale);
	}

	//Messaggio eliminazione annuncio KO
	public void messageEliminazioneKO(String nomePortale) {
		JOptionPane.showMessageDialog(null, "Problemi nell\'eliminazione scheda in: " + nomePortale + ".\n\n Verificare l\'eliminazione", "Errore", JOptionPane.ERROR_MESSAGE);
	}

	//Messaggio modifica annuncio OK
	public void messageModificaOK(String nomePortale) {
		JOptionPane.showMessageDialog(null, "Scheda veicolo sincronizzata in: " + nomePortale, "Scheda sincronizzata", JOptionPane.INFORMATION_MESSAGE);
	}

	//Messaggio modifica annuncio KO
	public void messageModificaKO(String nomePortale) {
		JOptionPane.showMessageDialog(null, "Problemi nella sincronizzazione scheda in: " + nomePortale + ".\n\n Verificare la sincronizzazione", "Errore", JOptionPane.ERROR_MESSAGE);
	}

}