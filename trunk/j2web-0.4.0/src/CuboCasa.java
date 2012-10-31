/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.awt.Component;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author marco
 */

//La classe principale
public class CuboCasa extends PortaleImmobiliare {     

    //Parametri generali
	private final String NOMEPORTALE = "cubocasa.it";
	private final String SESSIONCOOKIENAME = "PHPSESSID";
	private final String SESSIONCOOKIEDOMAIN = "www.cubocasa.it";
	private final String URLROOT = "http://www.cubocasa.it";
	private final String USERNAME = "testAccount01";
    private final String PASSWORD = "test1234";

    private String CODICEINSERZIONE;    
    /*private String NOME_IMMAGINE_1;
    private String NOME_IMMAGINE_2;
    private String NOME_IMMAGINE_3;
    private String NOME_IMMAGINE_4;
    private String NOME_IMMAGINE_5;
    private String NOME_IMMAGINE_6;
    private String NOME_IMMAGINE_7;
    private String NOME_IMMAGINE_8;*/
    private boolean INSERIMENTO_OK = false;
    private boolean debugMode = true;
   

    Map<String,String> mappaDeiParamerti;
    
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
    	
    	//Inizializzazione parametri
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
        	Object[] response = connessione_1.post("POST della pagina di login", URLROOT + "/_login.php", postParameters, debugMode);
			Header[] responseHeaders = (Header[])response[0];
    		findAndSetLocalCookie(connessione_1, responseHeaders, SESSIONCOOKIENAME);
    		connessione_1.setSessionCookieDomain(SESSIONCOOKIEDOMAIN);
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
			connessione_4.get("Connessione 4 - GET della pagina \"Inserisci annuncio\" (step 2)", URLROOT + "/agenzie/inserisci-annuncio.php?provincia=" + mappaDeiParamerti.get("provincia"), debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	/*Connessione di mezzo per recuperare le frazioni*/
    	
    	
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
        postParameters.add(new BasicNameValuePair("condizionato", mappaDeiParamerti.get("condizionato")));
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
        	Object[] response = connessione_5.post("POST della pagina \"Inserisci annuncio\" (step 2)", URLROOT + "/agenzie/_inserisci-annuncio.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];	
        	
        	String responseStatus = (String)response[2];
        	if( (responseStatus.contains("302"))) {
        		System.out.println("Bingo!"); //todo: rimuovere
        		CODICEINSERZIONE = getHeaderValueByName(responseHeaders, "Location");
        		System.out.println("Bingo!" + CODICEINSERZIONE);  //todo: rimuovere
        		INSERIMENTO_OK = true;
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
			connessione_6.get("Connessione 6 - GET di redirect", URLROOT + "dett-annuncio.php?id=" + CODICEINSERZIONE, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 7 - GET della pagina di inserimento immagini
        HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
    	try {
			connessione_7.get("Connessione 7 - GET della pagina di inserimento immagini", URLROOT + "/agenzie/foto.php?id=" + CODICEINSERZIONE, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessioni 8 - inserimento immagine
    	for(int i=0; i<scheda.arrayImages.length; i++) {
    		if(scheda.arrayImages[i]!=null) {
    			HttpPortalPostConnection connessione_8 = new HttpPortalPostConnection();
    	    	
    			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
    	        FileBody bin = new FileBody(scheda.arrayImages[i]);
    	        reqEntity.addPart("foto", bin );
    	    	
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
    	if(INSERIMENTO_OK) {
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		scheda.aggiungiInserimentoPortale(idPortale, CODICEINSERZIONE);
    		      	
    		if(!isSequential) {   			
    			System.out.println("Inserita in: " + NOMEPORTALE);       		
        		
    			//Aggiorna i pulsanti del pannello inserimento
    			j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
    			
    			//Invio mail di conferma inserimento 
            	sendConfirmationMail(scheda, NOMEPORTALE, CODICEINSERZIONE);
           	
            	//Stampo a video un messaggio informativo
                JOptionPane.showMessageDialog(null, "Scheda immobile inserita in: " + NOMEPORTALE, "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);
              
    		}
    		
    		return INSERIMENTO_OK;        	
        	
    	}
    	else {
    		
    		if(!isSequential) {
    			//Stampo a video un messaggio informativo
        		JOptionPane.showMessageDialog(null, "Problemi nell'inserimento scheda in: " + NOMEPORTALE + ".\n Verificare l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);	
    		}
    		
    		return INSERIMENTO_OK;
 		
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
		CODICEINSERZIONE = scheda.getCodiceInserimento(idPortale);	
		
		
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
    		findAndSetLocalCookie(connessione_1, responseHeaders, SESSIONCOOKIENAME);
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
			connessione_9.get("Connessione 3 - GET della pagina Gestione annunci per eliminare un annuncio", URLROOT + "/agenzie/_del_annunci.php?id=" + CODICEINSERZIONE, debugMode);
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
	public void inizializzaParametri()  {		
		
		String provincia = scheda.provincia;
		switch (provincia)
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
		    case "Carbonia-Iglesias":
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
		    case "Reggio di Calabria":
		    	provincia = "RC";
		        break;
		    case "Reggio nell'Emilia":
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
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		Date now = new Date();  		  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		String data_annuncio = df.format(now);
		mappaDeiParamerti.put("data_annuncio", data_annuncio);
		
		String no_reload = Long.toString(now.getTime());
		no_reload = no_reload.substring(0, no_reload.length()-3);
		mappaDeiParamerti.put("no_reload", no_reload);
		
		String rif_agenzia = scheda.codiceInserzione;
		mappaDeiParamerti.put("rif_agenzia", rif_agenzia);
		
		
		
		String nameComune = scheda.comune;
		mappaDeiParamerti.put("nameComune", nameComune);
		
		String codice_comune_inserzione = "";
		/*HttpPortalGetConnection connessione_9 = new HttpPortalGetConnection();
    	try {
    		Object[] response = connessione_9.get(CASE24_URLROOT + "/area_clienti/include/ajax.php?funzione=select_geografico&etichetta=denominazione_comune&zona=X&valore_etichetta=" + provincia + "&valore_selezionato=&tabindex=3", debugMode);
    		String responseBody = (String)response[1];
    		
    		org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);              
            //Ottengo il valore del parametro Provincia
            Elements optionElements = doc.getElementsByTag("option");
            if(optionElements.isEmpty()) {
            	throw(new HttpResponseException("Non ho trovato tag di tipo \"option\""));
            }
            else {
            	Iterator<Element> iterator = optionElements.iterator();
            	double resultComparation = 0;
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	List<char[]> charListPortale = bigram(currentElement.html());
	        		List<char[]> charListImagination = bigram(nameComune);
	        		double actualResultComparation = dice(charListPortale, charListImagination);
	        		if(actualResultComparation>=resultComparation) {
	        			resultComparation = actualResultComparation;
	        			codice_comune_inserzione = currentElement.attr("value");            		
	        		}       		
            	}
            }
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}*/
    	mappaDeiParamerti.put("codice_comune_inserzione", codice_comune_inserzione);
		
		mappaDeiParamerti.put("codice_comune_inserzione", codice_comune_inserzione);				
		
		String indirizzo = scheda.indirizzoLocalita;
		mappaDeiParamerti.put("indirizzo", indirizzo);
		
		String tipo_proposta = "";
		switch (scheda.tipologiaContratto)
		{
		    case "Affitto":
		    	tipo_proposta = "Affitti";
		        break;
		    case "Vendita":
		    	tipo_proposta = "Vendita";
		    	break;
		}
		mappaDeiParamerti.put("tipo_proposta", tipo_proposta);
		
		String tipo_immobile = "";
		switch (scheda.tipologiaImmobile)
		{
		    case "Appartamento":
		    	tipo_immobile = "16";
		        break;
		    case "Attico":
		    	tipo_immobile = "26";
		        break;
		    case "Bifamiliare":
		    	tipo_immobile = "19";
		        break;
		    case "Casa":
		    	tipo_immobile = "19";
		        break;
		    case "Garage":
		    	tipo_immobile = "22";
		        break;
		    case "Palazzo":
		    	tipo_immobile = "20";
		        break;
		    case "Rustico":
		    	tipo_immobile = "24";
		        break;
		    case "Terreno":
		    	tipo_immobile = "27";
		        break;
		    case "Villa":
		    	tipo_immobile = "20";
		        break;
		    case "Villaschiera":
		    	tipo_immobile = "19";
		        break;
		    case "Agriturismo":
		    	tipo_immobile = "25";
		        break;
		    case "Albergo":
		    	tipo_immobile = "25";
		        break;
		    case "Bar":
		    	tipo_immobile = "21";
		        break;
		    case "Negozio":
		    	tipo_immobile = "23";
		        break;
		    case "Ristorante":
		    	tipo_immobile = "25";
		        break;
		    case "Ufficio":
		    	tipo_immobile = "23";
		        break;
		    case "Capannone":
		    	tipo_immobile = "23";
		        break;
		    case "Laboratorio":
		    	tipo_immobile = "23";
		        break;
		    case "Magazzino":
		    	tipo_immobile = "23";
		        break;
		}
		mappaDeiParamerti.put("tipo_immobile", tipo_immobile);
		
		String superficie = scheda.superficieImmobile;
		mappaDeiParamerti.put("superficie", superficie);
		
		String prezzo = scheda.prezzoImmobile;
		mappaDeiParamerti.put("prezzo", prezzo);
		
		String numero_camere_bis = "";
		switch (scheda.comboBoxNumeroCamereIndex)
		{
			case 0:
				numero_camere_bis = "";
				break;
			case 1:
		    	numero_camere_bis = "1";
		        break;
		    case 2:
		    	numero_camere_bis = "2";
		    	break;
		    case 3:
		    	numero_camere_bis = "3";
		    	break;
		    case 4:
		    	numero_camere_bis = "4";
		    	break;
		    case 5:
		    	numero_camere_bis = "5";
		    	break;
		    default:
		    	numero_camere_bis = "6";
		}
		mappaDeiParamerti.put("numero_camere_bis", numero_camere_bis);
		
		String numero_bagni = "";
		switch (scheda.numeroBagni)
		{
		    case "1":
		    	numero_bagni = "1";
		        break;
		    case "2":
		    	numero_bagni = "2";
		    	break;
		    case "3":
		    	numero_bagni = "3";
		    	break;
		    case "4":
		    	numero_bagni = "4";
		    	break;
		    case "5":
		    	numero_bagni = "5";
		    	break;
		    default:
		    	numero_bagni = "";
		}
		mappaDeiParamerti.put("numero_bagni", numero_bagni);
		
		String descrizione = scheda.testoAnnuncio;
		mappaDeiParamerti.put("descrizione", descrizione);
		
		String stato_immobile = "";
		switch (scheda.statoImmobile)
    	{
    	    case "Nuovo":
    	    	stato_immobile = "88";
    	        break;
    	    case "Ristrutturato":
    	    	stato_immobile = "91";
    	    	break;
    	    case "Da ristrutturare":
    	    	stato_immobile = "4";
    	    	break;
    	    case "In buono stato":
    	    	stato_immobile = "";
    	    	break;
    	    case "Abitabile":
    	    	stato_immobile = "";
    	    	break;
    	    case "Ottimo":
    	    	stato_immobile = "";
    	    	break;
    	    case "In costruzione":
    	    	stato_immobile = "87";
    	    	break;
    	    default:
    	    	stato_immobile = "";
    	}
		mappaDeiParamerti.put("stato_immobile", stato_immobile);
		
		String riscaldamento = "";
		switch (scheda.tipologiaRiscaldamento)
		{
		    case "Assente":
		    	riscaldamento = "";
		        break;
		    case "Centralizzato":
		    	riscaldamento = "107";
		    	break;
		    case "Autonomo":
		    	riscaldamento = "106";
		    	break;
		    case "Stufa":
		    	riscaldamento = "";
		    	break;
		    default:
		    	riscaldamento = "";
		}
		mappaDeiParamerti.put("riscaldamento", riscaldamento);
		
		String tipologia_giardino = "";
		switch (scheda.giardino)
		{
		    case "Assente":
		    	tipologia_giardino = "";
		        break;
		    case "Giardino condominiale":
		    	tipologia_giardino = "117";
		    	break;
		    case "Giardino ad uso esclusivo":
		    	tipologia_giardino = "116";
		    	break;
		    default:
		    	tipologia_giardino = "";
		}
		mappaDeiParamerti.put("tipologia_giardino", tipologia_giardino);
		
		String classe_energetica = "";
		switch (scheda.certificazioniEnergetiche)
		{
		    case "Nessuna":
		    	classe_energetica = "130";
		        break;
		    case "Certificazione energetica A++":
		    	classe_energetica = "122";
		    	break;
		    case "Certificazione energetica A+":
		    	classe_energetica = "122";
		    	break;
		    case "Certificazione energetica A":
		    	classe_energetica = "123";
		    	break;
		    case "Certificazione energetica B":
		    	classe_energetica = "124";
		    	break;
		    case "Certificazione energetica C":
		    	classe_energetica = "125";
		    	break;
		    case "Certificazione energetica D":
		    	classe_energetica = "126";
		    	break;
		    case "Certificazione energetica E":
		    	classe_energetica = "127";
		    	break;
		    case "Certificazione energetica F":
		    	classe_energetica = "128";
		    	break;
		    case "Certificazione energetica G":
		    	classe_energetica = "129";
		    	break;
		    default:
		    	classe_energetica = "";
		}
		mappaDeiParamerti.put("classe_energetica", classe_energetica);

	}
	
	
}