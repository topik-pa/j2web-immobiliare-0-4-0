import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
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
 * Questa classe definische i metodi e gli attributi dell'oggetto portale immobiliare, qui definiti in termini generici, vengono riscritti nelle classi più specifiche
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

public abstract class PortaleImmobiliare implements parametriGenerali {

	//Attributi
	String urlIcona;
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

	public PortaleImmobiliare (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {
	
		this.urlIcona = urlIcona;
		this.valoreLabel = valoreLabel;
		this.idPortale = idPortale;
		this.isActive = isActive;
		
		

	}
	
	
	//Metodi
	
	//Inserimento scheda (sovrascritto nelle sottoclassi)
	public abstract boolean inserisciScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException, UnsupportedEncodingException;
	
	//Visualizzazione scheda (sovrascritto nelle sottoclassi)
	public abstract boolean visualizzaScheda(SchedaImmobile scheda) throws HttpCommunicationException;

	//Eliminazione scheda (sovrascritto nelle sottoclassi)
	public abstract boolean cancellaScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException;
	 
    //Invio mail di conferma inserzione
  	static void sendConfirmationMail(SchedaImmobile scheda, String nomePortale, String codInserzione)   {
  				
  		final String USERNAME = BACKEND_EMAIL;
  		final String PASSWORD = BACKEND_EMAIL_PSW;
  		final String RECIPENTS = BACKEND_EMAIL + "," + EMAIL_UTENTE;
  		final String SUBJECT = "Scheda immobile inserita: " + scheda.codiceInserzione + " " + scheda.titoloAnnuncio;
  		
  	    final String SMTP_HOST = BACKEND_EMAIL_SMTP_HOST;
  	    final int SMTP_PORT = BACKEND_EMAIL_SMTP_PORT;
  	    final Session session = Session.getInstance(System.getProperties(), null);
  	    final Message msg = new MimeMessage(session);
  	    final String senderEmail = USERNAME.contains("@") ? USERNAME : (USERNAME + BACKEND_EMAIL_DOMAIN);
  	    
  	    String textBody = "";
  	    textBody += "\nProvincia: " + scheda.provincia + "\n";
  	    textBody += "Comune: " + scheda.comune + "\n";
  	    textBody += "CAP: " + scheda.cap + "\n";
	  	textBody += "Via/Piazza/Località: " + scheda.indirizzoLocalita + "\n";
	  	textBody += "Descrizione: " + scheda.testoAnnuncio + "\n";
	  	textBody += "Categoria: " + scheda.categoriaImmobile + "\n";
	  	textBody += "Tipologia: " + scheda.tipologiaImmobile + "\n";
	  	textBody += "Contratto: " + scheda.tipologiaContratto + "\n";
	  	textBody += "Nr. locali: " + scheda.numeroLocali + "\n";
	  	textBody += "Nr. bagni: " + scheda.numeroBagni + "\n";
	  	textBody += "Nr. camere: " + scheda.numeroCamere + "\n";
	  	textBody += "Superficie abitazione: " + scheda.superficieImmobile + "\n";
	  	textBody += "Riscaldamento: " + scheda.tipologiaRiscaldamento + "\n";
	  	textBody += "Clima: " + scheda.clima + "\n";
	  	textBody += "Certificazione: " + scheda.certificazioniEnergetiche + "\n";
	  	textBody += "Giardino: " + scheda.giardino + "\n";
	  	textBody += "Parcheggio: " + scheda.parcheggio + "\n";
	  	if(scheda.immagine1!=null) {
	  		textBody += "Immagine : " + scheda.immagine1.getName() + "\n";
	  	}
	  	if(scheda.immagine2!=null) {
	  		textBody += "Immagine : " + scheda.immagine2.getName() + "\n";
	  	}
	  	if(scheda.immagine3!=null) {
	  		textBody += "Immagine : " + scheda.immagine3.getName() + "\n";
	  	}
	  	if(scheda.immagine4!=null) {
	  		textBody += "Immagine : " + scheda.immagine4.getName() + "\n";
	  	}
	  	if(scheda.immagine5!=null) {
	  		textBody += "Immagine : " + scheda.immagine5.getName() + "\n";
	  	}
	  	if(scheda.immagine6!=null) {
	  		textBody += "Immagine : " + scheda.immagine6.getName() + "\n";
	  	}
	  	if(scheda.immagine7!=null) {
	  		textBody += "Immagine : " + scheda.immagine7.getName() + "\n";
	  	}
	  	if(scheda.immagine8!=null) {
	  		textBody += "Immagine : " + scheda.immagine8.getName() + "\n";
	  	}
	  	if(scheda.immagine9!=null) {
	  		textBody += "Immagine : " + scheda.immagine9.getName() + "\n";
	  	}
	  	if(scheda.immagine0!=null) {
	  		textBody += "Immagine : " + scheda.immagine0.getName() + "\n";
	  	}
	  	textBody += "Prezzo: " + scheda.prezzoImmobile + "\n";
	  	
	  	textBody += "Scheda inserita in: " + nomePortale + " " + "con codice " + codInserzione;
  	    
  	    
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
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "AddressException", JOptionPane.ERROR_MESSAGE);
  			e.printStackTrace();
  		} catch (MessagingException e) {
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "MessagingException", JOptionPane.ERROR_MESSAGE);
  			e.printStackTrace();
  		}
  	}
		
  	//Valutazione similarità tra stringhe
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
        for(int i=0; i<headers.length; i++) {       	
        	Header currentHeader = headers[i];
        	//Get cookie
        	if(currentHeader.getName().contains("Set-Cookie")) {
        		        		
        		String cookie_header = currentHeader.getValue();
        		int end = cookie_header.indexOf("=");
                String cookie_name = cookie_header.substring(0, end);                   
                int start = end + 1;
                end = cookie_header.indexOf(";");
                String cookie_value = cookie_header.substring(start, end);
                
                if(cookie_name.equals(cookieName)) {
                	//Cookie di sessione trovato
            		cookieHeaderFound = true;
            		//Stampo i valori trovati
            		System.out.println("Method: findSessionCookie \n" + "cookie_header-->"+cookie_header + "\ncookieName-->"+cookieName + "\ncookie_value-->"+cookie_value + "\ncookieDomain-->"+cookieDomain);
            		
            		//Aggiorno i parametri dei cookie (del portale chiamante)
            		SESSIONCOOKIEHEADER = cookie_header;
            		SESSIONCOOKIEVALUE = cookie_value;
                }   
        	}       	
        }       
        //Valori di ritorno
        return cookieHeaderFound?true:false;
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
}