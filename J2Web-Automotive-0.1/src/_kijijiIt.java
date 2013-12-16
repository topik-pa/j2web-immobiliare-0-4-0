/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;



/**
 *
 * @author marco
 */

//La classe principale
public class _kijijiIt extends PortaleWeb {     

    //Variabili generali
	private final String NOMEPORTALE = "casab.it";
	private final String URLROOT = "http://casab.it";
	//private final String USERNAME = "hsttdjjh@sharklasers.com";
    //private final String PASSWORD = "password";
	
    //private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();   
    //private boolean inserimentoOK = false;
    //private boolean debugMode = true;

    //Mappa dei parametri da inviare
    Map<String,String> mappaDeiParamerti;
    
    //Lista dei parametri inviati in una singola connessione
    List<NameValuePair> postParameters;
    
    //Lista degli headers inviati in una singola connessione
    List<NameValuePair> requestHeaders;

    //La scheda immobile su cui si lavora
    SchedaVeicolo scheda;
    
    //Lista di alcuni dei parametri inviati
    String codiceInserzione;
    
	//Costruttore
	public _kijijiIt (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(icon, valoreLabel, idPortale, isActive);
		
		SESSIONCOOKIENAME = "PHPSESSID";
		SESSIONCOOKIEDOMAIN = "www.casab.it";
		
		mappaDeiParamerti =  new Hashtable<String,String>();
	    
	    postParameters = new ArrayList<NameValuePair>();	
	    
	    requestHeaders = new ArrayList<NameValuePair>();
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("Inserimento scheda: " + scheda.codiceScheda + "...");
    	
    	/*
    	//Inizializzazione parametri
    	this.scheda=scheda;
    	    	
    	//Inizializza i parametri http del portale 
		inizializzaParametri();
	
		
    	//Connessione 0 - GET della home page - Opzionale
    	HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
    	try {
			connessione_0.get("Connessione 0 - GET della home page", URLROOT, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 1 - GET della pagina di login
    	HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
    	try {
    		connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/page/14/login_agenzie.html", debugMode);
    		
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 2 - POST dei parametri di accesso (1)
    	HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("email", USERNAME));
        postParameters.add(new BasicNameValuePair("password", PASSWORD));
        
        try {        	
        	Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso (1)", URLROOT + "/funzioni/login_agenzia.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	//Trovo il cookie di sessione
        	findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
        	//Imposto il cookie di sessione per tutte le successive connessioni
        	connessione_2.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 2b - POST dei parametri di accesso (2)
    	HttpPortalPostConnection connessione_2b = new HttpPortalPostConnection();   	
        try {
        	MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        	reqEntity.addPart("email", new StringBody(USERNAME));
        	reqEntity.addPart("password", new StringBody(PASSWORD));
        	reqEntity.addPart("action", new StringBody("1"));
        	reqEntity.addPart("permalinkPaginaOspite", new StringBody("http://www.casab.it/page/14/login_agenzie.html"));
        	
        	//Immposto qui gli headers che saranno utilizzati in tutte le altre connessioni
        	requestHeaders = new ArrayList<NameValuePair>();
            requestHeaders.add(new BasicNameValuePair("Host", "www.casab.it"));
        	requestHeaders.add(new BasicNameValuePair("Cookie", SESSIONCOOKIENAME + "=" + SESSIONCOOKIEVALUE));
        	        	
        	connessione_2b.post("Connessione 2b - POST dei parametri di accesso (2)", URLROOT + "/page/14/login_agenzie.html", reqEntity, requestHeaders, debugMode);

        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
   
    	
    	//Connessione 3 - GET della pagina "Annunci immobiliari"
    	HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
    	try {
            connessione_3.get("Connessione 3 - GET della pagina \"Annunci immobiliari\"", URLROOT + "/area_riservata.php?pg=modimmo&tipo=age", requestHeaders, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 4 - GET della pagina "Inserisci un nuovo immobile"
    	HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
    	try {
			connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo immobile\"", URLROOT + "/area_riservata.php?pg=newimmo&tipo=age", requestHeaders, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 5 - GET della pagina "Inserisci un nuovo immobile (con parametro circa la tipologia)"
    	HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
    	try {
			connessione_5.get("Connessione 5 - GET della pagina \"Inserisci un nuovo immobile (con parametro circa la tipologia)\"", URLROOT + "/moduli/agenzie/immobile2.php?tipo=" + mappaDeiParamerti.get("tipo") + "&act=ins", requestHeaders, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 6 - POST dello step 1
    	HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("Categoria", mappaDeiParamerti.get("Categoria")));
        postParameters.add(new BasicNameValuePair("Codice", mappaDeiParamerti.get("Codice")));
        postParameters.add(new BasicNameValuePair("ComuneSM", mappaDeiParamerti.get("ComuneSM")));
        postParameters.add(new BasicNameValuePair("Locali", mappaDeiParamerti.get("Locali")));	//solo residenziali
        postParameters.add(new BasicNameValuePair("Motivazione", mappaDeiParamerti.get("Motivazione")));
        postParameters.add(new BasicNameValuePair("Nazione", mappaDeiParamerti.get("Nazione")));
        postParameters.add(new BasicNameValuePair("Prezzo_Richiesto", mappaDeiParamerti.get("Prezzo_Richiesto")));
        postParameters.add(new BasicNameValuePair("ProvinciaSM", mappaDeiParamerti.get("ProvinciaSM")));
        postParameters.add(new BasicNameValuePair("RegioneSM", mappaDeiParamerti.get("RegioneSM")));
        postParameters.add(new BasicNameValuePair("Totale_mq", mappaDeiParamerti.get("Totale_mq")));
        postParameters.add(new BasicNameValuePair("act", mappaDeiParamerti.get("act")));
        postParameters.add(new BasicNameValuePair("action", mappaDeiParamerti.get("action")));
        postParameters.add(new BasicNameValuePair("bagni", mappaDeiParamerti.get("bagni")));	//solo residenziali
        postParameters.add(new BasicNameValuePair("camere", mappaDeiParamerti.get("camere")));	//solo residenziali
        postParameters.add(new BasicNameValuePair("certificazione_acustica", mappaDeiParamerti.get("certificazione_acustica")));
        postParameters.add(new BasicNameValuePair("classe_energetica", mappaDeiParamerti.get("classe_energetica")));
        postParameters.add(new BasicNameValuePair("epi", mappaDeiParamerti.get("epi")));
        postParameters.add(new BasicNameValuePair("idImmobile", mappaDeiParamerti.get("idImmobile")));
        postParameters.add(new BasicNameValuePair("indirizzo", mappaDeiParamerti.get("indirizzo")));
        postParameters.add(new BasicNameValuePair("latitudine", mappaDeiParamerti.get("latitudine")));
        postParameters.add(new BasicNameValuePair("longitudine", mappaDeiParamerti.get("longitudine")));
        postParameters.add(new BasicNameValuePair("tipo", mappaDeiParamerti.get("tipo")));	
        
        //Rimuovo i parametri che non devono essere inviati
        postParameters.retainAll(removeNotUsedParams(postParameters));
        
        try {
        	Object[] response = connessione_6.post("Connessione 6 - POST dello step 1", URLROOT + "/moduli/agenzie/immobile2.php", postParameters, requestHeaders, debugMode);
        	String responseBody = (String)response[1];
        	
        	//Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody); 
            Elements inputElement = doc.select("input[name=idImmobile]");
            codiceInserzione = inputElement.attr("value");
            //System.out.println("CODICEINSERZIONE: " + codiceInserzione);
            mappaDeiParamerti.put("codiceInserzione", codiceInserzione);
            mappaDeiParamerti.put("idImmobile", codiceInserzione); //Da questo momento, questo parametro cambia valore
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}	
        
        
        //Connessione 7 - GET della pagina "Inserisci un nuovo immobile (step 2)"
    	HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
    	try {
			connessione_7.get("Connessione 7 - GET della pagina \"Inserisci un nuovo immobile (step 2)\"", URLROOT + "/moduli/agenzie/immobile3.php?idImmobile=" + mappaDeiParamerti.get("codiceInserzione") + "&tipo=" + mappaDeiParamerti.get("tipo") + "&act=ins", requestHeaders, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 8 - POST dello step 2
    	HttpPortalPostConnection connessione_8 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("Ascensore", mappaDeiParamerti.get("Ascensore"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Balconi", mappaDeiParamerti.get("Balconi"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Box", mappaDeiParamerti.get("Box"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Cucina", mappaDeiParamerti.get("Cucina"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Disposizione_interna", mappaDeiParamerti.get("Disposizione_interna")));
        postParameters.add(new BasicNameValuePair("Eta_Costruzione", mappaDeiParamerti.get("Eta_Costruzione"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Garage", mappaDeiParamerti.get("Garage"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Giardino", mappaDeiParamerti.get("Giardino"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Posto_Auto", mappaDeiParamerti.get("Posto_Auto"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Riscaldamento_Autonomo", mappaDeiParamerti.get("Riscaldamento_Autonomo"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("Terrazzo", mappaDeiParamerti.get("Terrazzo"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("act", mappaDeiParamerti.get("act")));
        postParameters.add(new BasicNameValuePair("action", mappaDeiParamerti.get("action")));
        postParameters.add(new BasicNameValuePair("costruzione_presente", mappaDeiParamerti.get("costruzione_presente"))); //solo ter
        postParameters.add(new BasicNameValuePair("arredato", mappaDeiParamerti.get("arredato"))); //solo comm e res
        postParameters.add(new BasicNameValuePair("attivita_consentite", mappaDeiParamerti.get("attivita_consentite"))); //solo commerciale
        postParameters.add(new BasicNameValuePair("esposizione", mappaDeiParamerti.get("esposizione"))); //solo res
        postParameters.add(new BasicNameValuePair("idImmobile", mappaDeiParamerti.get("idImmobile")));
        postParameters.add(new BasicNameValuePair("mq_agricoli", mappaDeiParamerti.get("mq_agricoli"))); //solo ter
        postParameters.add(new BasicNameValuePair("mq_edificabili", mappaDeiParamerti.get("mq_edificabili"))); //solo ter
        postParameters.add(new BasicNameValuePair("possibili_realizzazione", mappaDeiParamerti.get("possibili_realizzazione"))); //solo ter
        postParameters.add(new BasicNameValuePair("progetto_approvato", mappaDeiParamerti.get("progetto_approvato"))); //solo ter
        postParameters.add(new BasicNameValuePair("mq_coperti", mappaDeiParamerti.get("mq_coperti"))); //solo comm	
        postParameters.add(new BasicNameValuePair("mq_copribili", mappaDeiParamerti.get("mq_copribili"))); //solo comm
        postParameters.add(new BasicNameValuePair("mq_scoperti", mappaDeiParamerti.get("mq_scoperti"))); //solo comm	
        postParameters.add(new BasicNameValuePair("num_vetrine", mappaDeiParamerti.get("num_vetrine"))); //solo comm	
        postParameters.add(new BasicNameValuePair("piani_totali", mappaDeiParamerti.get("piani_totali"))); //solo residenziale
        postParameters.add(new BasicNameValuePair("posizione", mappaDeiParamerti.get("posizione")));
        postParameters.add(new BasicNameValuePair("riscaldamento_autonomo", mappaDeiParamerti.get("riscaldamento_autonomo"))); //solo commerciale
        postParameters.add(new BasicNameValuePair("tipo", mappaDeiParamerti.get("tipo")));
        postParameters.add(new BasicNameValuePair("tipo_terreno", mappaDeiParamerti.get("tipo_terreno"))); //solo ter
        
        //Rimuovo i parametri che non devono essere inviati
        postParameters.retainAll(removeNotUsedParams(postParameters));
        
        try {
        	connessione_8.post("Connessione 8 - POST dello step 2", URLROOT + "/moduli/agenzie/immobile3.php", postParameters, requestHeaders, debugMode);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 9 - GET della pagina "Inserisci un nuovo immobile (step 3)"
    	HttpPortalGetConnection connessione_9 = new HttpPortalGetConnection();
    	try {
			connessione_9.get("Connessione 9 - GET della pagina \"Inserisci un nuovo immobile (step 3)\"", URLROOT + "/moduli/agenzie/immobile4.php?idImmobile=" + mappaDeiParamerti.get("codiceInserzione") + "&tipo=" + mappaDeiParamerti.get("tipo") + "&act=ins", requestHeaders, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessioni 10 - inserimento immagini
    	for(int i=0; i<scheda.arrayImages.length; i++) {
    		if(scheda.arrayImages[i]!=null) {
    			HttpPortalPostConnection connessione_10 = new HttpPortalPostConnection();
    	    	
    			try {
	    			MultipartEntity reqEntity2 = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	    	        FileBody bin = new FileBody(scheda.arrayImages[i]);
	    	        reqEntity2.addPart("file", bin );
	    	        reqEntity2.addPart("action", new StringBody(mappaDeiParamerti.get("action")));
	    	        reqEntity2.addPart("tipo", new StringBody(mappaDeiParamerti.get("tipo")));
	    	        reqEntity2.addPart("act", new StringBody(mappaDeiParamerti.get("act")));
	    	        reqEntity2.addPart("idImmobile", new StringBody(mappaDeiParamerti.get("idImmobile")));
    	    	
	    	        connessione_10.post("Connessione 10_" + i + " - inserimento immagine " + i, URLROOT + "/moduli/agenzie/immobile4.php", reqEntity2, requestHeaders, debugMode);
    			} catch (IOException | RuntimeException e) {
    				throw new HttpCommunicationException(e);
    			}
    	    	finally {
    	    		postParameters.clear();
    	    	}
            }
    	}
    	
    	
    	//Connessione 11 - GET della pagina "Modifica gli immobili" per verificare l'inserimento
    	HttpPortalGetConnection connessione_11 = new HttpPortalGetConnection();
    	try {
    		Object[] response = connessione_11.get("Connessione 11 - GET della pagina \"Modifica gli immobili\" per verificare l'inserimento", URLROOT + "/area_riservata.php?pg=modimmo", requestHeaders, debugMode);
    		String responseBody = (String) response[1];
    		
    		if(responseBody.contains("idImmobile=" + mappaDeiParamerti.get("codiceInserzione"))) {
            	inserimentoOK = true;
            }
            else {
            	throw(new HttpWrongResponseBodyException("Non trovo un riferimento all'annuncio appena inserito. Non è possibile verificare l'effettivo inserimento"));
            }
		} catch (IOException | HttpWrongResponseBodyException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
      
    	
    	//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
    	if(inserimentoOK) {
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		scheda.aggiungiInserimentoPortale(idPortale, codiceInserzione);
    		      	
    		if(!isSequential) {   			
    			System.out.println("Inserita in: " + NOMEPORTALE);       		
        		
    			//Aggiorna i pulsanti del pannello inserimento
    			PanelSicronizzazioneConPortali.updatePanello(scheda, false);
    			
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
    	}*/
    	
    	JOptionPane.showMessageDialog(null, "Funzionalità non supportata", "Funzionalità non supportata", JOptionPane.WARNING_MESSAGE);
    	return false;
    	       
	}
	
    
    //Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaVeicolo scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codiceScheda + "...");
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/area_riservata.php?pg=modimmo&tipo=age";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);
			
		} catch (IOException e ) {
			//
		}
		
		return true;
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.codiceScheda + "...");
		
		codiceInserzione = scheda.getCodiceInserimento(idPortale);			
		
	
		//Connessione 0 - GET della home page - Opzionale
    	/*HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
    	try {
			connessione_0.get("Connessione 0 - GET della home page", URLROOT, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/
    	
    	
    	//Connessione 1 - GET della pagina di login
    	/*HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
    	try {
    		connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/page/14/login_agenzie.html", debugMode);
    		
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/
    	
    	
    	//Connessione 2 - POST dei parametri di accesso (1)
    	/*HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("email", USERNAME));
        postParameters.add(new BasicNameValuePair("password", PASSWORD));
        
        try {        	
        	Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso (1)", URLROOT + "/funzioni/login_agenzia.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	//Trovo il cookie di sessione
        	findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
        	//Imposto il cookie di sessione per tutte le successive connessioni
        	connessione_2.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}*/
        
        
        //Connessione 2b - POST dei parametri di accesso (2)
    	/*HttpPortalPostConnection connessione_2b = new HttpPortalPostConnection();   	
        try {
        	MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        	reqEntity.addPart("email", new StringBody(USERNAME));
        	reqEntity.addPart("password", new StringBody(PASSWORD));
        	reqEntity.addPart("action", new StringBody("1"));
        	reqEntity.addPart("permalinkPaginaOspite", new StringBody("http://www.casab.it/page/14/login_agenzie.html"));
        	
        	//Immposto qui gli headers che saranno utilizzati in tutte le altre connessioni
        	requestHeaders = new ArrayList<NameValuePair>();
            requestHeaders.add(new BasicNameValuePair("Host", "www.casab.it"));
        	requestHeaders.add(new BasicNameValuePair("Cookie", SESSIONCOOKIENAME + "=" + SESSIONCOOKIEVALUE));
        	        	
        	connessione_2b.post("Connessione 2b - POST dei parametri di accesso (2)", URLROOT + "/page/14/login_agenzie.html", reqEntity, requestHeaders, debugMode);

        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}*/
        
    	
    	//Connessione 3 - GET della pagina "Elimina immobile"
    	/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
    	try {
			connessione_3.get("Connessione 3 - GET della pagina \"Elimina immobile\"", URLROOT + "/moduli/agenzie/elimina_immobile.php?idImmobile=" + codiceInserzione, requestHeaders, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/
    	
        
        //Aggiorno la lista dei portali in cui è presenta la scheda corrente
  		scheda.eliminaInserimentoPortale(idPortale);			
  	 		
  		if(!isSequential) {
  			//Aggiorno i pulsanti del pannello inserimento
  			PanelSicronizzazioneConPortali.updatePanello(scheda, false);
  			
  			System.out.println("Eliminata da: " + NOMEPORTALE);
  		  	
  	  		//Stampo a video un messaggio informativo
  	        JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
		}
  		
  		return true;
	
	}
		
	
	//Metodo per la valutazione dei parametri
	public void inizializzaParametri() throws HttpCommunicationException {		

	}


}