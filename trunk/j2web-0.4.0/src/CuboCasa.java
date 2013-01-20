/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

/**
 *
 * @author marco
 */

//La classe principale
public class CuboCasa extends PortaleImmobiliare {     

    //Variabili generali
	private final String NOMEPORTALE = "cubocasa.it";
	private final String SESSIONCOOKIENAME = "PHPSESSID";
	private final String SESSIONCOOKIEDOMAIN = "www.cubocasa.it";
	private final String URLROOT = "http://www.cubocasa.it";
	private final String USERNAME = "testAccount01";
    private final String PASSWORD = "test1234";
    private String codiceInserzione;    
    private boolean inserimentoOK = false;
    private boolean debugMode = true;
    
    //Altre variabili
    String matchedComune = "";  

    //Mappa dei parametri da inviare
    Map<String,String> mappaDeiParamerti;
    
    //Lista dei parametri inviati in una singola connessione
    List<NameValuePair> postParameters;  

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;   	   
    
    
	//Costruttore
	public CuboCasa (String urlIcona, String valoreLabel, String idPortale) {		
		
		super(urlIcona, valoreLabel, idPortale);
		
		mappaDeiParamerti =  new Hashtable<String,String>();
	    
	    postParameters = new ArrayList<NameValuePair>();		
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("Inserimento scheda: " + scheda.codiceInserzione + "...");
    	
    	//Inizializzazione scheda
    	this.scheda=scheda;
    	     	
    	//Inizializza i parametri http del portale 
		inizializzaParametri();
    	
    	//Connessione 0 - GET della home page
    	HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
    	try {
			connessione_0.get("Connessione 0 - GET della home page", URLROOT, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	  	
    	//Connessione 1 - POST della pagina di login
    	HttpPortalPostConnection connessione_1 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("Submit", "Entra"));
        postParameters.add(new BasicNameValuePair("email", USERNAME));
        postParameters.add(new BasicNameValuePair("from_login", "/"));
        postParameters.add(new BasicNameValuePair("password", PASSWORD));
        try {
        	Object[] response = connessione_1.post("Connessione 1 - POST della pagina di login", URLROOT + "/_login.php", postParameters, debugMode);
			Header[] responseHeaders = (Header[])response[0];
    		findAndSetLocalCookie(connessione_1, responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}	
    	
    	//Connessione 2 - GET di redirect 
    	HttpPortalGetConnection connessione_2 = new HttpPortalGetConnection();
    	try {
    		connessione_2.get("Connessione 2 - GET di redirect", URLROOT + "/agenzie/index.php", debugMode);    		    	
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	   	
    	//Connessione 3 - GET della pagina "Inserisci annuncio" (step 1)
    	HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
    	try {
			connessione_3.get("Connessione 3 - GET della pagina \"Inserisci annuncio\" (step 1)", URLROOT + "/agenzie/inserisci-annuncio.php", debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	   	
    	//Connessione 4 - GET della pagina "Inserisci annuncio" (step 2)
    	HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();    	
    	try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci annuncio\" (step 2)", URLROOT + "/agenzie/inserisci-annuncio.php?provincia=" + mappaDeiParamerti.get("provincia"), debugMode);
			String responseBody = (String)response[1];
			org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);
			Elements optionElements = doc.select("#idComune option");
			Iterator<Element> iterator = optionElements.iterator();
			//Comparazione della similarità  tra stringhe
            double resultComparation = 0;
            while(iterator.hasNext()) {
            	Element currentOption = iterator.next();         	
            	List<char[]> textPortale = bigram(currentOption.text());
        		List<char[]> textJ2Web = bigram(scheda.comune);       		
        		double actualResultComparation = dice(textPortale, textJ2Web);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			matchedComune = currentOption.attr("value");            		
        		}       		
        		System.out.println("Risultato comparazione: " + resultComparation);
        		System.out.println("idComune: " + matchedComune);      		
            }
            mappaDeiParamerti.put("idComune", matchedComune);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		} 	
    	  	
    	//Connessione 5 - POST della pagina "Inserisci annuncio" (step 2)
    	HttpPortalPostConnection connessione_5 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("Submit", "Salva Annuncio"));
        postParameters.add(new BasicNameValuePair("anno", mappaDeiParamerti.get("anno")));
        postParameters.add(new BasicNameValuePair("cantina", mappaDeiParamerti.get("cantina")));
        postParameters.add(new BasicNameValuePair("cap", mappaDeiParamerti.get("cap")));
        postParameters.add(new BasicNameValuePair("certificato_ipe", mappaDeiParamerti.get("certificato_ipe")));
        postParameters.add(new BasicNameValuePair("citta_annuncio", mappaDeiParamerti.get("citta_annuncio")));
        postParameters.add(new BasicNameValuePair("classe_energetica", mappaDeiParamerti.get("classe_energetica")));
        postParameters.add(new BasicNameValuePair("code_maps", mappaDeiParamerti.get("code_maps")));
        postParameters.add(new BasicNameValuePair("codice", mappaDeiParamerti.get("codice")));
        if(scheda.clima.contains("Aria condizionata")) {
        	postParameters.add(new BasicNameValuePair("condizionato", mappaDeiParamerti.get("condizionato")));
        }
        postParameters.add(new BasicNameValuePair("contratto", mappaDeiParamerti.get("contratto")));
        postParameters.add(new BasicNameValuePair("cucina", mappaDeiParamerti.get("cucina")));
        postParameters.add(new BasicNameValuePair("descrizione", mappaDeiParamerti.get("descrizione")));
        postParameters.add(new BasicNameValuePair("garage", mappaDeiParamerti.get("garage")));
        postParameters.add(new BasicNameValuePair("giardino", mappaDeiParamerti.get("giardino")));
        postParameters.add(new BasicNameValuePair("idComune", mappaDeiParamerti.get("idComune")));
        postParameters.add(new BasicNameValuePair("idFrazione", mappaDeiParamerti.get("idFrazione")));
        postParameters.add(new BasicNameValuePair("idTipologia", mappaDeiParamerti.get("idTipologia")));
        postParameters.add(new BasicNameValuePair("indirizzo", mappaDeiParamerti.get("indirizzo")));
        postParameters.add(new BasicNameValuePair("ipe", mappaDeiParamerti.get("ipe")));
        postParameters.add(new BasicNameValuePair("latitudine", mappaDeiParamerti.get("latitudine")));
        postParameters.add(new BasicNameValuePair("longitudine", mappaDeiParamerti.get("longitudine")));
        postParameters.add(new BasicNameValuePair("mq", mappaDeiParamerti.get("mq")));
        postParameters.add(new BasicNameValuePair("n_bagni", mappaDeiParamerti.get("n_bagni")));
        postParameters.add(new BasicNameValuePair("n_camere", mappaDeiParamerti.get("n_camere")));
        postParameters.add(new BasicNameValuePair("piano", mappaDeiParamerti.get("piano")));
        postParameters.add(new BasicNameValuePair("postoauto", mappaDeiParamerti.get("postoauto")));
        postParameters.add(new BasicNameValuePair("prezzo", mappaDeiParamerti.get("prezzo")));
        postParameters.add(new BasicNameValuePair("provincia", mappaDeiParamerti.get("provincia")));
        postParameters.add(new BasicNameValuePair("riscaldamento", mappaDeiParamerti.get("riscaldamento")));
        postParameters.add(new BasicNameValuePair("soffitta", mappaDeiParamerti.get("soffitta")));
        postParameters.add(new BasicNameValuePair("stato", mappaDeiParamerti.get("stato")));
        postParameters.add(new BasicNameValuePair("supiani", mappaDeiParamerti.get("supiani")));
        postParameters.add(new BasicNameValuePair("zona", mappaDeiParamerti.get("zona")));
        try {
        	Object[] response = connessione_5.post("Connessione 5 - POST della pagina \"Inserisci annuncio\" (step 2)", URLROOT + "/agenzie/_inserisci-annuncio.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];	     	
        	BasicStatusLine responseStatus = (BasicStatusLine) response[2];
        	if( (responseStatus.getStatusCode()==302)) {
        		String locationHeader = getHeaderValueByName(responseHeaders, "Location");
        		if(locationHeader.contains("id=")) {
        			codiceInserzione = locationHeader.substring(locationHeader.indexOf("id=")+3);
        			System.out.println("Codice inserzione: " + codiceInserzione);
            		inserimentoOK = true;
        		}	
        	}
        	else {
        		throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto: mi aspettavo un 302"));
        	}
        	
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
               
        //Connessione 6 - GET di redirect
        HttpPortalGetConnection connessione_6 = new HttpPortalGetConnection();
    	try {
			connessione_6.get("Connessione 6 - GET di redirect", URLROOT + "/agenzie/dett-annuncio.php?id=" + codiceInserzione, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	   	
    	//Connessione 7 - GET della pagina di inserimento immagini
        HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
    	try {
			connessione_7.get("Connessione 7 - GET della pagina di inserimento immagini", URLROOT + "/agenzie/foto.php?id=" + codiceInserzione, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	   	
    	//Connessioni 8 - inserimento immagine
    	for(int i=0; i<scheda.arrayImages.length; i++) {
    		if(scheda.arrayImages[i]!=null) {
    			HttpPortalPostConnection connessione_8 = new HttpPortalPostConnection();
    	    	
    			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
    	        FileBody bin = new FileBody(scheda.arrayImages[i]); 	        
    	        try {
    	        	reqEntity.addPart("foto", bin);
        	        reqEntity.addPart("id", new StringBody(codiceInserzione));
					reqEntity.addPart("Submit", new StringBody("Invia la foto"));
				} catch (UnsupportedEncodingException e1) {
					throw new HttpCommunicationException(e1);
				}
    	    	
    	        try {
    	        	connessione_8.post("Connessioni 8 - inserimento immagine " + i, URLROOT + "/agenzie/_foto.php", reqEntity, debugMode);
    			} catch (IOException e) {
    				throw new HttpCommunicationException(e);
    			}
    	    	finally {
    	    		postParameters.clear();
    	    	}
            }
    	}
    		    	    
    	//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
    	if(inserimentoOK) {
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		scheda.aggiungiInserimentoPortale(idPortale, codiceInserzione);
    		      	
    		if(!isSequential) {   			
    			System.out.println("Inserita in: " + NOMEPORTALE);       		
        		
    			//Aggiorna i pulsanti del pannello inserimento
    			j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
    			
    			//Invio mail di conferma inserimento 
            	sendConfirmationMail(scheda, NOMEPORTALE, codiceInserzione);
           	
            	//Stampo a video un messaggio informativo
                JOptionPane.showMessageDialog(null, "Scheda immobile inserita in: " + NOMEPORTALE, "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);
              
    		}
    		
    		return inserimentoOK;        	
        	
    	}
    	else {
    		
    		if(!isSequential) {
    			//Stampo a video un messaggio informativo
        		JOptionPane.showMessageDialog(null, "Problemi nell'inserimento scheda in: " + NOMEPORTALE + ".\n Verificare l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);	
    		}
    		
    		return inserimentoOK;
 		
    	}
       
	}
	 
    //Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaImmobile scheda) throws HttpCommunicationException {
		System.out.println("Visualizzazione scheda: " + scheda.codiceInserzione + "...");
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/agenzie/index.php";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);
			
		} catch (IOException e ) {
			throw new HttpCommunicationException(e);
		}
		
		return true;
	
	}

	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.codiceInserzione + "...");
		codiceInserzione = scheda.getCodiceInserimento(idPortale);	
			
		//Connessione 1 - POST della pagina di login
    	HttpPortalPostConnection connessione_1 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("Submit", "Entra"));
        postParameters.add(new BasicNameValuePair("email", USERNAME));
        postParameters.add(new BasicNameValuePair("from_login", "/"));
        postParameters.add(new BasicNameValuePair("password", PASSWORD));
        try {
        	Object[] response = connessione_1.post("POST della pagina di login", URLROOT + "/_login.php", postParameters, debugMode);
			Header[] responseHeaders = (Header[])response[0];
    		findAndSetLocalCookie(connessione_1, responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
    		connessione_1.setSessionCookieDomain(SESSIONCOOKIEDOMAIN);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}       
    	
        //Connessione 9 - GET della pagina Gestione annunci per eliminare un annuncio
    	HttpPortalGetConnection connessione_9 = new HttpPortalGetConnection();
    	try {
			connessione_9.get("Connessione 9 - GET della pagina Gestione annunci per eliminare un annuncio", URLROOT + "/agenzie/_del_annunci.php?id=" + codiceInserzione, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}	
    	       
        //Aggiorno la lista dei portali in cui è presenta la scheda corrente
  		scheda.eliminaInserimentoPortale(idPortale);			
  	 		
  		if(!isSequential) {
  			//Aggiorno i pulsanti del pannello inserimento
  			j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
  			
  			System.out.println("Eliminata da: " + NOMEPORTALE);
  		  	
  	  		//Stampo a video un messaggio informativo
  	        JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
		}
  		
  		return true;
	
	}
		
	//Metodo per la valutazione dei parametri
	public void inizializzaParametri() throws HttpCommunicationException  {		
		
		String provincia = "";
		switch (scheda.provincia)
		{
		    case "Agrigento":
		    	provincia = "AG";
		        break;
		    case "Alessandria":
		    	provincia = "AL";
		        break;
		    case "Ancona":
		    	provincia = "AN";
		        break;
		    case "Aosta":
		    	provincia = "AO";
		        break;
		    case "Arezzo":
		    	provincia = "AR";
		        break;
		    case "Ascoli Piceno":
		    	provincia = "AP";
		        break;
		    case "Asti":
		    	provincia = "AT";
		        break;
		    case "Avellino":
		    	provincia = "AV";
		        break;
		    case "Bari":
		    	provincia = "BA";
		        break;
		    case "Barletta-Andria-Trani":
		    	provincia = "";//???
		        break;
		    case "Belluno":
		    	provincia = "BL";
		        break;
		    case "Benevento":
		    	provincia = "BN";
		        break;
		    case "Bergamo":
		    	provincia = "BG";
		        break;
		    case "Biella":
		    	provincia = "BI";
		        break;
		    case "Bologna":
		    	provincia = "BO";
		        break;
		    case "Bolzano":
		    	provincia = "BZ";
		        break;
		    case "Brescia":
		    	provincia = "BS";
		        break;
		    case "Brindisi":
		    	provincia = "BR";
		        break;
		    case "Cagliari":
		    	provincia = "CA";
		        break;
		    case "Caltanissetta":
		    	provincia = "CL";
		        break;
		    case "Campobasso":
		    	provincia = "CB";
		        break;
		    case "Carbonia Iglesias":
		    	provincia = "CI";
		        break;
		    case "Caserta":
		    	provincia = "CE";
		        break;
		    case "Catania":
		    	provincia = "CT";
		        break;
		    case "Catanzaro":
		    	provincia = "CZ";
		        break;
		    case "Chieti":
		    	provincia = "CH";
		        break;
		    case "Como":
		    	provincia = "CO";
		        break;
		    case "Cosenza":
		    	provincia = "CS";
		        break;
		    case "Cremona":
		    	provincia = "CR";
		        break;
		    case "Crotone":
		    	provincia = "KR";
		        break;
		    case "Cuneo":
		    	provincia = "CN";
		        break;
		    case "Enna":
		    	provincia = "EN";
		        break;
		    case "Fermo":
		    	provincia = "";//??
		        break;
		    case "Ferrara":
		    	provincia = "FE";
		        break;
		    case "Firenze":
		    	provincia = "FI";
		        break;
		    case "Foggia":
		    	provincia = "FG";
		        break;
		    case "Forlì-Cesena":
		    	provincia = "FO";
		        break;
		    case "Frosinone":
		    	provincia = "FR";
		        break;
		    case "Genova":
		    	provincia = "GE";
		        break;
		    case "Gorizia":
		    	provincia = "GO";
		        break;
		    case "Grosseto":
		    	provincia = "GR";
		        break;
		    case "Imperia":
		    	provincia = "IM";
		        break;
		    case "Isernia":
		    	provincia = "IS";
		        break;
		    case "L'Aquila":
		    	provincia = "AQ";
		        break;
		    case "La Spezia":
		    	provincia = "SP";
		        break;
		    case "Latina":
		    	provincia = "LT";
		        break;
		    case "Lecce":
		    	provincia = "LE";
		        break;
		    case "Lecco":
		    	provincia = "LC";
		        break;
		    case "Livorno":
		    	provincia = "LI";
		        break;
		    case "Lodi":
		    	provincia = "LO";
		        break;
		    case "Lucca":
		    	provincia = "LU";
		        break;
		    case "Macerata":
		    	provincia = "MC";
		        break;
		    case "Mantova":
		    	provincia = "MN";
		        break;
		    case "Massa-Carrara":
		    	provincia = "MS";
		        break;
		    case "Matera":
		    	provincia = "MT";
		        break;
		    case "Medio Campidano":
		    	provincia = "MD";
		        break;
		    case "Messina":
		    	provincia = "ME";
		        break;
		    case "Milano":
		    	provincia = "MI";
		        break;
		    case "Modena":
		    	provincia = "MO";
		        break;
		    case "Monza e della Brianza":
		    	provincia = "";//???
		        break;
		    case "Napoli":
		    	provincia = "NA";
		        break;
		    case "Novara":
		    	provincia = "NO";
		        break;
		    case "Nuoro":
		    	provincia = "NU";
		        break;
		    case "Ogliastra":
		    	provincia = "OG";
		        break;
		    case "Olbia-Tempio":
		    	provincia = "OT";
		        break;
		    case "Oristano":
		    	provincia = "OR";
		        break;
		    case "Padova":
		    	provincia = "PD";
		        break;
		    case "Palermo":
		    	provincia = "PA";
		        break;
		    case "Parma":
		    	provincia = "PR";
		        break;
		    case "Pavia":
		    	provincia = "PV";
		        break;
		    case "Perugia":
		    	provincia = "PG";
		        break;
		    case "Pesaro e Urbino":
		    	provincia = "PU";
		        break;
		    case "Pescara":
		    	provincia = "PE";
		        break;
		    case "Piacenza":
		    	provincia = "PC";
		        break;
		    case "Pisa":
		    	provincia = "PI";
		        break;
		    case "Pistoia":
		    	provincia = "PT";
		        break;
		    case "Pordenone":
		    	provincia = "PN";
		        break;
		    case "Potenza":
		    	provincia = "PZ";
		        break;
		    case "Prato":
		    	provincia = "PO";
		        break;
		    case "Ragusa":
		    	provincia = "RG";
		        break;
		    case "Ravenna":
		    	provincia = "RA";
		        break;
		    case "Reggio Calabria":
		    	provincia = "RC";
		        break;
		    case "Reggio Emilia":
		    	provincia = "RE";
		        break;
		    case "Rieti":
		    	provincia = "RI";
		        break;
		    case "Rimini":
		    	provincia = "RN";
		        break;
		    case "Roma":
		    	provincia = "RM";
		        break;
		    case "Rovigo":
		    	provincia = "RO";
		        break;
		    case "Salerno":
		    	provincia = "SA";
		        break;
		    case "Sassari":
		    	provincia = "SS";
		        break;
		    case "Savona":
		    	provincia = "SV";
		        break;
		    case "Siena":
		    	provincia = "SI";
		        break;
		    case "Siracusa":
		    	provincia = "SO";
		        break;
		    case "Sondrio":
		    	provincia = "";//???
		        break;
		    case "Taranto":
		    	provincia = "TA";
		        break;
		    case "Teramo":
		    	provincia = "TE";
		        break;
		    case "Terni":
		    	provincia = "TR";
		        break;
		    case "Torino":
		    	provincia = "TO";
		        break;
		    case "Trapani":
		    	provincia = "TP";
		        break;
		    case "Trento":
		    	provincia = "TN";
		        break;
		    case "Treviso":
		    	provincia = "TV";
		        break;
		    case "Trieste":
		    	provincia = "TS";
		        break;
		    case "Udine":
		    	provincia = "UD";
		        break;
		    case "Valle d'Aosta":
		    	provincia = "";//???
		        break;
		    case "Varese":
		    	provincia = "VA";
		        break;
		    case "Venezia":
		    	provincia = "VE";
		        break;
		    case "Verbano-Cusio-Ossola":
		    	provincia = "VB";
		        break;
		    case "Vercelli":
		    	provincia = "VC";
		        break;
		    case "Verona":
		    	provincia = "VR";
		        break;
		    case "Vibo Valentia":
		    	provincia = "VV";
		        break;
		    case "Vicenza":
		    	provincia = "VI";
		        break;
		    case "Viterbo":
		    	provincia = "VT";
		        break;		        
		}
		mappaDeiParamerti.put("provincia", provincia);	
				
		String anno =  scheda.annoCostruzione;
		mappaDeiParamerti.put("anno", anno);
		
		String cantina = scheda.cantina?"Presente":"Non disponibile";
		mappaDeiParamerti.put("cantina", cantina);
		
		String cap = scheda.cap;
		mappaDeiParamerti.put("cap", cap);
		
		String certificato_ipe = "";
		switch (scheda.certificazioneIpe) {
			case "Immobile certificato":
				certificato_ipe = "C";
				break;		
			case "Immobile esente da certificazione":
				certificato_ipe = "E";
				break;		
			case "Documentazione non esistente":	
				certificato_ipe = "N";
				break;
			default:
				certificato_ipe = "N";
				break;
		}
		mappaDeiParamerti.put("certificato_ipe", certificato_ipe);
				
		String citta_annuncio = scheda.comune;
		mappaDeiParamerti.put("citta_annuncio", citta_annuncio);
				
		String classe_energetica = "";
		switch (scheda.certificazioniEnergetiche)
		{
		    case "Nessuna":
		    	classe_energetica = "";
		        break;
		    case "Certificazione energetica A++":
		    	classe_energetica = "A";
		    	break;
		    case "Certificazione energetica A+":
		    	classe_energetica = "A";
		    	break;
		    case "Certificazione energetica A":
		    	classe_energetica = "A";
		    	break;
		    case "Certificazione energetica B":
		    	classe_energetica = "B";
		    	break;
		    case "Certificazione energetica C":
		    	classe_energetica = "C";
		    	break;
		    case "Certificazione energetica D":
		    	classe_energetica = "D";
		    	break;
		    case "Certificazione energetica E":
		    	classe_energetica = "E";
		    	break;
		    case "Certificazione energetica F":
		    	classe_energetica = "F";
		    	break;
		    case "Certificazione energetica G":
		    	classe_energetica = "G";
		    	break;
		    default:
		    	classe_energetica = "G";
		}
		mappaDeiParamerti.put("classe_energetica", classe_energetica);
		
		String code_maps = "200";
		mappaDeiParamerti.put("code_maps", code_maps);
				
		String codice = scheda.codiceInserzione;
		mappaDeiParamerti.put("codice", codice);
				
		String condizionato = "si";	//inviato sse è selezionato (checkbox)
		mappaDeiParamerti.put("condizionato", condizionato);
				
		String contratto = "";
		switch (scheda.tipologiaContratto) {
			case "Affitto":
				contratto = "affitto";
				break;
			case "Vendita":
				contratto = "vendita";
				break;
			default:
				contratto = "vacanze";
				break;
		}
		mappaDeiParamerti.put("contratto", contratto);
		
		String cucina = ""; //non previsto da J2Web
		mappaDeiParamerti.put("cucina", cucina);
				
		String descrizione = scheda.testoAnnuncio;
		mappaDeiParamerti.put("descrizione", descrizione);
				
		String garage = scheda.parcheggio.contains("Garage")?"Presente":"Non disponibile";
		mappaDeiParamerti.put("garage", garage);
				
		String giardino = (scheda.giardino.contains("Giardino condominiale") || scheda.giardino.contains("Giardino ad uso esclusivo"))?"Presente":"Non disponibile";
		mappaDeiParamerti.put("giardino", giardino);
				
		String idComune = matchedComune;
		mappaDeiParamerti.put("idComune", idComune);		
		
		String idFrazione = "";	//non supportata
		mappaDeiParamerti.put("idFrazione", idFrazione);
				
		String idTipologia = "";
		switch (scheda.tipologiaImmobile)
		{
		    case "Appartamento":
		    	idTipologia = "1";
		        break;
		    case "Attico":
		    	idTipologia = "12";
		        break;
		    case "Bifamiliare":
		    	idTipologia = "2";
		        break;
		    case "Casa":
		    	idTipologia = "2";
		        break;
		    case "Garage":
		    	idTipologia = "10";
		        break;
		    case "Palazzo":
		    	idTipologia = "31";
		        break;
		    case "Rustico":
		    	idTipologia = "5";
		        break;
		    case "Terreno agricolo":
		    	idTipologia = "7";
		        break;
		    case "Terreno edificabile":
		    	idTipologia = "34";
		        break;
		    case "Villa":
		    	idTipologia = "11";
		        break;
		    case "Villaschiera":
		    	idTipologia = "3";
		        break;
		    case "Agriturismo":
		    	idTipologia = "28";
		        break;
		    case "Albergo":
		    	idTipologia = "19";
		        break;
		    case "Bar":
		    	idTipologia = "20";
		        break;
		    case "Negozio":
		    	idTipologia = "9";
		        break;
		    case "Ristorante":
		    	idTipologia = "21";
		        break;
		    case "Ufficio":
		    	idTipologia = "6";
		        break;
		    case "Capannone":
		    	idTipologia = "15";
		        break;
		    case "Laboratorio":
		    	idTipologia = "17";
		        break;
		    case "Magazzino":
		    	idTipologia = "18";
		        break;
		}
		mappaDeiParamerti.put("idTipologia", idTipologia);
		
		String indirizzo = scheda.indirizzoLocalita;
		mappaDeiParamerti.put("indirizzo", indirizzo);		
		
		String ipe = "23.38 kWh/mq anno";
		mappaDeiParamerti.put("ipe", ipe);		
		
		Map<String, String> latLon;
		try {
			latLon = getLatLonCoord(scheda.indirizzoLocalita, scheda.comune, scheda.provincia, scheda.regione);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new HttpCommunicationException(e);
		}
		
		String latitudine = latLon.get("latitudine");
		mappaDeiParamerti.put("latitudine", latitudine);
		
		String longitudine = latLon.get("longitudine");
		mappaDeiParamerti.put("longitudine", longitudine);
				
		String mq = scheda.superficieImmobile;
		mappaDeiParamerti.put("mq", mq);		
		
		String n_bagni = scheda.numeroBagni;
		mappaDeiParamerti.put("n_bagni", n_bagni);
				
		String n_camere = scheda.numeroCamere;
		mappaDeiParamerti.put("n_camere", n_camere);
				
		String piano = "";
		switch (scheda.piano) {
		    case "Piano terra":
		    	piano = "0";
		        break;
		    case "Primo piano":
		    	piano = "1";
		    	break;
		    default:
		    	piano = "";
		}
		mappaDeiParamerti.put("piano", piano);
				
		String postoauto = (scheda.parcheggio.contains("Posto auto coperto") || scheda.parcheggio.contains("Posto auto scoperto"))?"Presente":"Non disponibile";
		mappaDeiParamerti.put("postoauto", postoauto);		
		
		String prezzo = scheda.prezzoImmobile;
		mappaDeiParamerti.put("prezzo", prezzo);		
		
		String riscaldamento = "";
		switch (scheda.tipologiaRiscaldamento) {
		    case "Assente":
		    	riscaldamento = "";
		        break;
		    case "Centralizzato":
		    	riscaldamento = "centralizzato";
		    	break;
		    case "Autonomo":
		    	riscaldamento = "autonomo";
		    	break;
		    case "Stufa":
		    	riscaldamento = "";
		    	break;
		    default:
		    	riscaldamento = "";
			}
		mappaDeiParamerti.put("riscaldamento", riscaldamento);
				
		String soffitta = "";	//non supportato
		mappaDeiParamerti.put("soffitta", soffitta);
				
		String stato = "";
		switch (scheda.statoImmobile)
    	{
    	    case "Nuovo":
    	    	stato = "nuovo";
    	        break;
    	    case "Ristrutturato":
    	    	stato = "ristrutturato";
    	    	break;
    	    case "Da ristrutturare":
    	    	stato = "da ristrutturare";
    	    	break;
    	    case "In buono stato":
    	    	stato = "nuovo";
    	    	break;
    	    case "Abitabile":
    	    	stato = "abitabile";
    	    	break;
    	    case "Ottimo":
    	    	stato = "nuovo";
    	    	break;
    	    case "In costruzione":
    	    	stato = "in costruzione";
    	    	break;
    	    default:
    	    	stato = "";
    	}
		mappaDeiParamerti.put("stato", stato);		
		
		String supiani = scheda.numeroTotalePiani;
		mappaDeiParamerti.put("supiani", supiani);		
		
		String zona = scheda.indirizzoLocalita;
		mappaDeiParamerti.put("zona", zona);
		
	}
	
	
}