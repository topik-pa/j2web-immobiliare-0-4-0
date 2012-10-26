/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

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
    //private final String CODICE_CLIENTE ="1340103900";

    private String CODICEINSERZIONE;    
    private String NOME_IMMAGINE_1;
    private String NOME_IMMAGINE_2;
    private String NOME_IMMAGINE_3;
    private String NOME_IMMAGINE_4;
    private String NOME_IMMAGINE_5;
    private String NOME_IMMAGINE_6;
    private String NOME_IMMAGINE_7;
    private String NOME_IMMAGINE_8;
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
			connessione_4.get("Connessione 4 - GET della pagina \"Inserisci annuncio\" (step 2)", URLROOT + "/agenzie/inserisci-annuncio.php?provincia=" + "UD", debugMode); //todo
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	/*Connessione di mezzo per recuperare le frazioni*/
    	
    	
    	//Connessione 5 - POST della pagina "Inserisci annuncio" (step 2)
    	HttpPortalPostConnection connessione_5 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("inserisci_annuncio", "1"));	//todo
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("y", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        postParameters.add(new BasicNameValuePair("x", "10"));
        try {
        	Object[] response = connessione_5.post("POST della pagina \"Inserisci annuncio\" (step 2)", URLROOT + "/agenzie/_inserisci-annuncio.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];	//todo
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 6 - GET di redirect
        HttpPortalGetConnection connessione_6 = new HttpPortalGetConnection();
    	try {
			connessione_6.get("Connessione 6 - GET di redirect", URLROOT + "dett-annuncio.php?id=" + "17889664", debugMode);	//todo
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 7 - GET della pagina di inserimento immagini
        HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
    	try {
			connessione_7.get("Connessione 7 - GET della pagina di inserimento immagini", URLROOT + "/agenzie/foto.php?id=" + "17889664", debugMode);	//todo
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
    	        	Object[] response = connessione_8.post("Connessioni 8 - inserimento immagine " + i, URLROOT + "/agenzie/_foto.php", reqEntity, debugMode);
    	        	String responseBody = (String)response[1];    				    		         
    			} catch (IOException e) {
    				throw new HttpCommunicationException(e);
    			}
    	    	finally {
    	    		postParameters.clear();
    	    	}
            }
    	}

    	
    	//Connessione 7 - POST dello step 1 (e unico...)
    	 	
        try {
        	Object[] response = connessione_7.post("Connessione 7 - POST dello step 1 (e unico...)", CASE24_URLROOT + "/area_clienti/annunci.php?pagina=1", postParameters, debugMode);
        	String responseBody = (String)response[1];
        	
			if(responseBody.contains("ANNUNCIO INSERITO CORRETTAMENTE")) {
            	INSERIMENTO_OK = true;
            }
            else {
            	throw(new HttpWrongResponseBodyException("Non trovo il testo \"ANNUNCIO INSERITO CORRETTAMENTE\" che stabilisce se l'annuncio è stato inserito correttamente"));
            }
            
	        //Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                       
            Elements linkElements = doc.getElementsByTag("a");
            if(linkElements!=null) {
	            Iterator<Element> iterator = linkElements.iterator();
	            while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	if(currentElement.html().contains("CODICE RIFERIMENTO AGENZIA:")) {
	            		String text = currentElement.html().substring(currentElement.html().indexOf("AGENZIA:")+8).trim();
	            		if(scheda.codiceInserzione.equals(text)) {
	            			CASE24_CODICEINSERZIONE = currentElement.attr("name");
	            		}		
	            		System.out.println("CODICEINSERZIONE: " + CASE24_CODICEINSERZIONE);
	            	}
	            }
            }
		} catch (IOException | HttpWrongResponseBodyException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
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
	public boolean visualizzaScheda(SchedaImmobile scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codiceInserzione + "...");
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/area_clienti/annunci.php";	//todo
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);
			
		} catch (IOException e ) {
			//manageErrors(e, 3);	//todo
		}
		
		return true;
	
	}

	
	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.codiceInserzione + "...");
		CODICEINSERZIONE = scheda.getCodiceInserimento(idPortale);	
		
		
		//Connessione 2 - GET della pagina "Area Riservata"
    	HttpPortalGetConnection connessione_2 = new HttpPortalGetConnection();
    	try {
			connessione_2.get("Connessione 2 - GET della pagina \"Area Riservata\"", URLROOT + "/area_clienti/include/ajax.php?tabella=utenti&username=" + CASE24_USERNAME + "&password=" + CASE24_PASSWORD, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
        
    	
        //Connessione 8 - POST della pagina Gestione annunci per eliminare un annuncio
    	HttpPortalPostConnection connessione_8 = new HttpPortalPostConnection();	
    	postParameters = new ArrayList<NameValuePair>();          
    	postParameters.add(new BasicNameValuePair("bottone_cancella", "Cancella annuncio"));
        postParameters.add(new BasicNameValuePair("cancella_annuncio", "cancella"));
        postParameters.add(new BasicNameValuePair("codice_inserzione", CODICEINSERZIONE));
        postParameters.add(new BasicNameValuePair("order_by", ""));
        postParameters.add(new BasicNameValuePair("order_direction", ""));
        postParameters.add(new BasicNameValuePair("page", ""));	
        try {
			connessione_8.post("Connessione 8 - POST della pagina Gestione annunci per eliminare un annuncio", URLROOT + "/area_clienti/annunci.php?pagina=1", postParameters, debugMode);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
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
		
		String inserisci_annuncio = "1";
		mappaDeiParamerti.put("inserisci_annuncio", inserisci_annuncio);
		
		String x = "10";
		mappaDeiParamerti.put("x", x);
		
		String y = "10";
		mappaDeiParamerti.put("y", y);
		
		String action = "inserisci_nuovo_annuncio";
		mappaDeiParamerti.put("action", action);
		
		String codice_cliente = CASE24_CODICE_CLIENTE;
		mappaDeiParamerti.put("codice_cliente", codice_cliente);
		
		String InserzionistaPrivato = "0";
		mappaDeiParamerti.put("InserzionistaPrivato", InserzionistaPrivato);
		
		String codice_inserzione = "";
		mappaDeiParamerti.put("codice_inserzione", codice_inserzione);
		
		String galleria_valore = "0";
		mappaDeiParamerti.put("galleria_valore", galleria_valore);
		
		String zona = "0";
		mappaDeiParamerti.put("zona", zona);
		
		String visua_indirizzo = "1";
		mappaDeiParamerti.put("visua_indirizzo", visua_indirizzo);
		
		String numero_camere_old = "";
		mappaDeiParamerti.put("numero_camere_old", numero_camere_old);
		
		String numero_piani = "";
		mappaDeiParamerti.put("numero_piani", numero_piani);
		
		String numero_terrazze = "0";
		mappaDeiParamerti.put("numero_terrazze", numero_terrazze);
		
		String numero_posti_auto_coperti = "0";
		mappaDeiParamerti.put("numero_posti_auto_coperti", numero_posti_auto_coperti);
		
		String numero_posti_auto = "0";
		mappaDeiParamerti.put("numero_posti_auto", numero_posti_auto);
		
		String numero_posti_auto_garage = "";
		mappaDeiParamerti.put("numero_posti_auto_garage", numero_posti_auto_garage);
		
		String riscaldamento_tipo = "";
		mappaDeiParamerti.put("riscaldamento_tipo", riscaldamento_tipo);
		
		String anno = "";
		mappaDeiParamerti.put("anno", anno);
		
		String spese_condominiali = "";
		mappaDeiParamerti.put("spese_condominiali", spese_condominiali);
		
		String stato_rogito = "";
		mappaDeiParamerti.put("stato_rogito", stato_rogito);
		
		String orientamento = "";
		mappaDeiParamerti.put("orientamento", orientamento);
		
		String scoperto = "";
		mappaDeiParamerti.put("scoperto", scoperto);
		
		String ipe = "";
		mappaDeiParamerti.put("ipe", ipe);
		
		String attivo = "1";
		mappaDeiParamerti.put("attivo", attivo);
		
		String opt_ascensore = "0";
		mappaDeiParamerti.put("opt_ascensore", opt_ascensore);
		
		String opt_servizi_disabili = "0";
		mappaDeiParamerti.put("opt_servizi_disabili", opt_servizi_disabili);
		
		String opt_angolo_cottura = "0";
		mappaDeiParamerti.put("opt_angolo_cottura", opt_angolo_cottura);
		
		String opt_satellite = "0";
		mappaDeiParamerti.put("opt_satellite", opt_satellite);
		
		String opt_arredato = "0";
		mappaDeiParamerti.put("opt_arredato", opt_arredato);
		
		String opt_bagno_con_vasca = "0";
		mappaDeiParamerti.put("opt_bagno_con_vasca", opt_bagno_con_vasca);
		
		String opt_caminetto = "0";
		mappaDeiParamerti.put("opt_caminetto", opt_caminetto);
		
		String opt_cantina = "0";
		mappaDeiParamerti.put("opt_cantina", opt_cantina);
		
		String opt_cassaforte = "0";
		mappaDeiParamerti.put("opt_cassaforte", opt_cassaforte);
		
		String opt_climatizzatore = "0";
		mappaDeiParamerti.put("opt_climatizzatore", opt_climatizzatore);
		
		String opt_cucina_vivibile = "0";
		mappaDeiParamerti.put("opt_cucina_vivibile", opt_cucina_vivibile);
		
		String opt_idromassaggio = "0";
		mappaDeiParamerti.put("opt_idromassaggio", opt_idromassaggio);
		
		String opt_allarme = "0";
		mappaDeiParamerti.put("opt_allarme", opt_allarme);
		
		String opt_lavanderia = "0";
		mappaDeiParamerti.put("opt_lavanderia", opt_lavanderia);
		
		String opt_mansarda = "0";
		mappaDeiParamerti.put("opt_mansarda", opt_mansarda);
		
		String opt_fotovoltaico = "0";
		mappaDeiParamerti.put("opt_fotovoltaico", opt_fotovoltaico);
		
		String opt_pannelli_solari = "0";
		mappaDeiParamerti.put("opt_pannelli_solari", opt_pannelli_solari);
		
		String opt_piscina = "0";
		mappaDeiParamerti.put("opt_piscina", opt_piscina);
		
		String opt_porta_blindata = "0";
		mappaDeiParamerti.put("opt_porta_blindata", opt_porta_blindata);
		
		String opt_possibilita_animali = "0";
		mappaDeiParamerti.put("opt_possibilita_animali", opt_possibilita_animali);
		
		String opt_ripostiglio = "0";
		mappaDeiParamerti.put("opt_ripostiglio", opt_ripostiglio);
		
		String opt_riscaldamento_pavimento = "0";
		mappaDeiParamerti.put("opt_riscaldamento_pavimento", opt_riscaldamento_pavimento);
		
		String opt_sauna = "0";
		mappaDeiParamerti.put("opt_sauna", opt_sauna);
		
		String opt_taverna = "0";
		mappaDeiParamerti.put("opt_taverna", opt_taverna);
		
		String opt_terra_cielo = "0";
		mappaDeiParamerti.put("opt_terra_cielo", opt_terra_cielo);
		
		String opt_travi = "0";
		mappaDeiParamerti.put("opt_travi", opt_travi);
		
		String opt_vista_panoramica = "0";
		mappaDeiParamerti.put("opt_vista_panoramica", opt_vista_panoramica);
		
		String opt_in_campagna = "0";
		mappaDeiParamerti.put("opt_in_campagna", opt_in_campagna);
		
		String optional_valore = "0";
		mappaDeiParamerti.put("optional_valore", optional_valore);
		
		Date now = new Date();  		  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		String data_annuncio = df.format(now);
		mappaDeiParamerti.put("data_annuncio", data_annuncio);
		
		String no_reload = Long.toString(now.getTime());
		no_reload = no_reload.substring(0, no_reload.length()-3);
		mappaDeiParamerti.put("no_reload", no_reload);
		
		String rif_agenzia = scheda.codiceInserzione;
		mappaDeiParamerti.put("rif_agenzia", rif_agenzia);
		
		String provincia = scheda.provincia;
		String codice_provincia_inserzione = "";
		switch (provincia)
		{
		    case "Agrigento":
		    	codice_provincia_inserzione = "084";
		        break;
		    case "Alessandria":
		    	codice_provincia_inserzione = "006";
		        break;
		    case "Ancona":
		    	codice_provincia_inserzione = "042";
		        break;
		    case "Arezzo":
		    	codice_provincia_inserzione = "051";
		        break;
		    case "Ascoli Piceno":
		    	codice_provincia_inserzione = "044";
		        break;
		    case "Asti":
		    	codice_provincia_inserzione = "005";
		        break;
		    case "Avellino":
		    	codice_provincia_inserzione = "064";
		        break;
		    case "Bari":
		    	codice_provincia_inserzione = "072";
		        break;
		    case "Barletta-Andria-Trani":
		    	codice_provincia_inserzione = "110";
		        break;
		    case "Belluno":
		    	codice_provincia_inserzione = "025";
		        break;
		    case "Benevento":
		    	codice_provincia_inserzione = "062";
		        break;
		    case "Bergamo":
		    	codice_provincia_inserzione = "016";
		        break;
		    case "Biella":
		    	codice_provincia_inserzione = "096";
		        break;
		    case "Bologna":
		    	codice_provincia_inserzione = "037";
		        break;
		    case "Bolzano":
		    	codice_provincia_inserzione = "021";
		        break;
		    case "Brescia":
		    	codice_provincia_inserzione = "017";
		        break;
		    case "Brindisi":
		    	codice_provincia_inserzione = "074";
		        break;
		    case "Cagliari":
		    	codice_provincia_inserzione = "092";
		        break;
		    case "Caltanissetta":
		    	codice_provincia_inserzione = "085";
		        break;
		    case "Campobasso":
		    	codice_provincia_inserzione = "070";
		        break;
		    case "Carbonia-Iglesias":
		    	codice_provincia_inserzione = "107";
		        break;
		    case "Caserta":
		    	codice_provincia_inserzione = "061";
		        break;
		    case "Catania":
		    	codice_provincia_inserzione = "087";
		        break;
		    case "Catanzaro":
		    	codice_provincia_inserzione = "079";
		        break;
		    case "Chieti":
		    	codice_provincia_inserzione = "069";
		        break;
		    case "Como":
		    	codice_provincia_inserzione = "013";
		        break;
		    case "Cosenza":
		    	codice_provincia_inserzione = "078";
		        break;
		    case "Cremona":
		    	codice_provincia_inserzione = "019";
		        break;
		    case "Crotone":
		    	codice_provincia_inserzione = "101";
		        break;
		    case "Cuneo":
		    	codice_provincia_inserzione = "004";
		        break;
		    case "Enna":
		    	codice_provincia_inserzione = "086";
		        break;
		    case "Fermo":
		    	codice_provincia_inserzione = "109";
		        break;
		    case "Ferrara":
		    	codice_provincia_inserzione = "038";
		        break;
		    case "Firenze":
		    	codice_provincia_inserzione = "048";
		        break;
		    case "Foggia":
		    	codice_provincia_inserzione = "071";
		        break;
		    case "Forlì-Cesena":
		    	codice_provincia_inserzione = "040";
		        break;
		    case "Frosinone":
		    	codice_provincia_inserzione = "060";
		        break;
		    case "Genova":
		    	codice_provincia_inserzione = "010";
		        break;
		    case "Gorizia":
		    	codice_provincia_inserzione = "031";
		        break;
		    case "Grosseto":
		    	codice_provincia_inserzione = "053";
		        break;
		    case "Imperia":
		    	codice_provincia_inserzione = "008";
		        break;
		    case "Isernia":
		    	codice_provincia_inserzione = "094";
		        break;
		    case "L'Aquila":
		    	codice_provincia_inserzione = "066";
		        break;
		    case "La Spezia":
		    	codice_provincia_inserzione = "011";
		        break;
		    case "Latina":
		    	codice_provincia_inserzione = "059";
		        break;
		    case "Lecce":
		    	codice_provincia_inserzione = "075";
		        break;
		    case "Lecco":
		    	codice_provincia_inserzione = "097";
		        break;
		    case "Livorno":
		    	codice_provincia_inserzione = "049";
		        break;
		    case "Lodi":
		    	codice_provincia_inserzione = "098";
		        break;
		    case "Lucca":
		    	codice_provincia_inserzione = "046";
		        break;
		    case "Macerata":
		    	codice_provincia_inserzione = "043";
		        break;
		    case "Mantova":
		    	codice_provincia_inserzione = "020";
		        break;
		    case "Massa-Carrara":
		    	codice_provincia_inserzione = "045";
		        break;
		    case "Matera":
		    	codice_provincia_inserzione = "077";
		        break;
		    case "Medio Campidano":
		    	codice_provincia_inserzione = "106";
		        break;
		    case "Messina":
		    	codice_provincia_inserzione = "083";
		        break;
		    case "Milano":
		    	codice_provincia_inserzione = "015";
		        break;
		    case "Modena":
		    	codice_provincia_inserzione = "036";
		        break;
		    case "Monza e della Brianza":
		    	codice_provincia_inserzione = "108";
		        break;
		    case "Napoli":
		    	codice_provincia_inserzione = "063";
		        break;
		    case "Novara":
		    	codice_provincia_inserzione = "003";
		        break;
		    case "Nuoro":
		    	codice_provincia_inserzione = "091";
		        break;
		    case "Ogliastra":
		    	codice_provincia_inserzione = "105";
		        break;
		    case "Olbia-Tempio":
		    	codice_provincia_inserzione = "104";
		        break;
		    case "Oristano":
		    	codice_provincia_inserzione = "095";
		        break;
		    case "Padova":
		    	codice_provincia_inserzione = "028";
		        break;
		    case "Palermo":
		    	codice_provincia_inserzione = "082";
		        break;
		    case "Parma":
		    	codice_provincia_inserzione = "034";
		        break;
		    case "Perugia":
		    	codice_provincia_inserzione = "054";
		        break;
		    case "Pesaro e Urbino":
		    	codice_provincia_inserzione = "041";
		        break;
		    case "Pescara":
		    	codice_provincia_inserzione = "068";
		        break;
		    case "Piacenza":
		    	codice_provincia_inserzione = "033";
		        break;
		    case "Pisa":
		    	codice_provincia_inserzione = "050";
		        break;
		    case "Pistoia":
		    	codice_provincia_inserzione = "047";
		        break;
		    case "Pordenone":
		    	codice_provincia_inserzione = "093";
		        break;
		    case "Potenza":
		    	codice_provincia_inserzione = "076";
		        break;
		    case "Prato":
		    	codice_provincia_inserzione = "100";
		        break;
		    case "Ragusa":
		    	codice_provincia_inserzione = "088";
		        break;
		    case "Ravenna":
		    	codice_provincia_inserzione = "039";
		        break;
		    case "Reggio di Calabria":
		    	codice_provincia_inserzione = "080";
		        break;
		    case "Reggio nell'Emilia":
		    	codice_provincia_inserzione = "036";
		        break;
		    case "Rieti":
		    	codice_provincia_inserzione = "057";
		        break;
		    case "Rimini":
		    	codice_provincia_inserzione = "099";
		        break;
		    case "Roma":
		    	codice_provincia_inserzione = "058";
		        break;
		    case "Rovigo":
		    	codice_provincia_inserzione = "029";
		        break;
		    case "Salerno":
		    	codice_provincia_inserzione = "065";
		        break;
		    case "Sassari":
		    	codice_provincia_inserzione = "090";
		        break;
		    case "Siena":
		    	codice_provincia_inserzione = "052";
		        break;
		    case "Siracusa":
		    	codice_provincia_inserzione = "089";
		        break;
		    case "Sondrio":
		    	codice_provincia_inserzione = "014";
		        break;
		    case "Taranto":
		    	codice_provincia_inserzione = "073";
		        break;
		    case "Teramo":
		    	codice_provincia_inserzione = "067";
		        break;
		    case "Terni":
		    	codice_provincia_inserzione = "055";
		        break;
		    case "Torino":
		    	codice_provincia_inserzione = "001";
		        break;
		    case "Trapani":
		    	codice_provincia_inserzione = "081";
		        break;
		    case "Trento":
		    	codice_provincia_inserzione = "022";
		        break;
		    case "Treviso":
		    	codice_provincia_inserzione = "026";
		        break;
		    case "Trieste":
		    	codice_provincia_inserzione = "032";
		        break;
		    case "Udine":
		    	codice_provincia_inserzione = "030";
		        break;
		    case "Valle d'Aosta":
		    	codice_provincia_inserzione = "007";
		        break;
		    case "Varese":
		    	codice_provincia_inserzione = "012";
		        break;
		    case "Venezia":
		    	codice_provincia_inserzione = "027";
		        break;
		    case "Verbano-Cusio-Ossola":
		    	codice_provincia_inserzione = "103";
		        break;
		    case "Vercelli":
		    	codice_provincia_inserzione = "002";
		        break;
		    case "Verona":
		    	codice_provincia_inserzione = "023";
		        break;
		    case "Vibo Valentia":
		    	codice_provincia_inserzione = "102";
		        break;
		    case "Vicenza":
		    	codice_provincia_inserzione = "024";
		        break;
		    case "Viterbo":
		    	codice_provincia_inserzione = "056";
		        break;		        
		}
		mappaDeiParamerti.put("codice_provincia_inserzione", codice_provincia_inserzione);
		
		String nameComune = scheda.comune;
		mappaDeiParamerti.put("nameComune", nameComune);
		
		String codice_comune_inserzione = "";
		/*HttpPortalGetConnection connessione_9 = new HttpPortalGetConnection();
    	try {
    		Object[] response = connessione_9.get(CASE24_URLROOT + "/area_clienti/include/ajax.php?funzione=select_geografico&etichetta=denominazione_comune&zona=X&valore_etichetta=" + codice_provincia_inserzione + "&valore_selezionato=&tabindex=3", debugMode);
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