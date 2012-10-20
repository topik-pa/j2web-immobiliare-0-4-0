import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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

	public PortaleImmobiliare (String urlIcona, String valoreLabel, String idPortale) {
	
		this.urlIcona = urlIcona;
		this.valoreLabel = valoreLabel;
		this.idPortale = idPortale;

	}
	
	
	//Metodi
	
	//Inserimento scheda (sovrascritto nelle sottoclassi)
	public abstract boolean inserisciScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException;
	
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
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
  			e.printStackTrace();
  		} catch (MessagingException e) {
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
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
  

    //Trova e imposta il cookie di sessione
    public boolean findAndSetLocalCookie(HttpPortalConnection connessione, Header[] headers, String cookieName) throws HttpResponseException {
		
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
            		connessione.setSessionCookieHeader(cookie_header);
            		connessione.setSessionCookieName(cookieName);
            		connessione.setSessionCookieValue(cookie_value);
            		break;
                }   
        	}       	
        }       
        //Valori di ritorno
        return cookieHeaderFound?true:false;
	}
    
    //Metodo per ottenere le coordinate della città
  	public String getCoord(String indirizzo, String comune, String provincia, String regione) throws ParserConfigurationException, SAXException, IOException {
  		String latitudine;
  		String longitudine;
  		Document doc = null;
  		DocumentBuilder db = null;
  		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          
  		db = dbf.newDocumentBuilder();    
  		doc = db.parse("http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address=" + indirizzo + "%20" + comune + "%20" + provincia + "%20" + regione + "%20" + "Italia");
          
  		doc.getDocumentElement().normalize();
  		
        System.out.println("Root element " + doc.getDocumentElement().getNodeName());
        NodeList nodeLstLat = doc.getElementsByTagName("lat");       
        Node lat = nodeLstLat.item(1);
        NodeList nodeLstLon = doc.getElementsByTagName("lng");       
        Node lon = nodeLstLon.item(1);
             
        System.out.println("Latitudine e longitudine: " + lat.getTextContent() + " - " + lon.getTextContent());
      
        latitudine = lat.getTextContent();
        longitudine = lon.getTextContent();
        
        return latitudine + "$$$" + longitudine;       	
  	}
  
    
}