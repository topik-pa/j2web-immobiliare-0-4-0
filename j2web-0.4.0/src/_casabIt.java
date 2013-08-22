/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.IOException;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;


/**
 *
 * @author marco
 */

//La classe principale
public class _casabIt extends PortaleImmobiliare {     

    //Variabili generali
	private final String NOMEPORTALE = "casab.it";
	private final String URLROOT = "http://casab.it";
	private final String USERNAME = "hsttdjjh@sharklasers.com";
    private final String PASSWORD = "password";
	
    //private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();   
    private boolean inserimentoOK = false;
    private boolean debugMode = true;
    
    /*private String nomeImmagine0;
    private String nomeImmagine1;
    private String nomeImmagine2;
    private String nomeImmagine3;
    private String nomeImmagine4;
    private String nomeImmagine5;
    private String nomeImmagine6;
    private String nomeImmagine7;
    private String nomeImmagine8;
    private String nomeImmagine9;*/

    //Mappa dei parametri da inviare
    Map<String,String> mappaDeiParamerti;
    
    //Lista dei parametri inviati in una singola connessione
    List<NameValuePair> postParameters;
    
    //Lista degli headers inviati in una singola connessione
    List<NameValuePair> requestHeaders;

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;
    
    //Lista di alcuni dei parametri inviati
    String idNazione;
    String tipo;
    String Categoria;
    String Codice;
    String ComuneSM;
    String Locali;
    String Motivazione;
    String Nazione;
    String Prezzo_Richiesto;
    String ProvinciaSM;
    String RegioneSM;
    String Totale_mq;
    String act;
    String action;
    String bagni;
    String camere;
    String certificazione_acustica;  //non supportato
    String classe_energetica;
    String epi;
    String idImmobile;
    String indirizzo;
    String latitudine;
    String longitudine;
    String codiceInserzione;
    String Ascensore;
    String Balconi;	//non supportato
    String Box;  //non supportato
    String Cucina;  //non supportato
    String Disposizione_interna;
    String Eta_Costruzione;
    String Garage;
    String Giardino;
    String Posto_Auto;
    String Riscaldamento_Autonomo;
    String Terrazzo;  //non supportato
    String costruzione_presente;  //non supportato
    String arredato;
    String attivita_consentite;  //non supportato
    String esposizione;  //non supportato
    String mq_agricoli;  //non supportato
    String mq_edificabili;  //non supportato
    String possibili_realizzazione;  //non supportato
    String progetto_approvato;  //non supportato
    String mq_coperti;  //non supportato
    String mq_copribili;  //non supportato
    String mq_scoperti;  //non supportato
    String num_vetrine;  //non supportato
    String piani_totali;
    String posizione;  //non supportato
    String riscaldamento_autonomo;
    String tipo_terreno;
    
    
	//Costruttore
	public _casabIt (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(urlIcona, valoreLabel, idPortale, isActive);
		
		SESSIONCOOKIENAME = "PHPSESSID";
		SESSIONCOOKIEDOMAIN = "www.casab.it";
		
		mappaDeiParamerti =  new Hashtable<String,String>();
	    
	    postParameters = new ArrayList<NameValuePair>();	
	    
	    requestHeaders = new ArrayList<NameValuePair>();
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("Inserimento scheda: " + scheda.codiceInserzione + "...");
    	
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
	public boolean visualizzaScheda(SchedaImmobile scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codiceInserzione + "...");
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
	public boolean cancellaScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.codiceInserzione + "...");
		
		codiceInserzione = scheda.getCodiceInserimento(idPortale);			
		
	
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
        
    	
    	//Connessione 3 - GET della pagina "Elimina immobile"
    	HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
    	try {
			connessione_3.get("Connessione 3 - GET della pagina \"Elimina immobile\"", URLROOT + "/moduli/agenzie/elimina_immobile.php?idImmobile=" + codiceInserzione, requestHeaders, debugMode);
    	} catch (IOException | RuntimeException e) {
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
	public void inizializzaParametri() throws HttpCommunicationException {			
		
		switch (scheda.categoriaImmobile)
    	{
    	    case "Residenziale":
    	    	tipo = "res";
    	        break;
    	    case "Commerciale":
    	    	tipo = "com";
    	    	break;
    	    case "Industriale":
    	    	tipo = "";
    	    	break;
    	    case "Terreni":
    	    	tipo = "";
    	    	break;
    	    default:
    	    	tipo = "";
    	}
		mappaDeiParamerti.put("tipo", tipo);
		
		
		switch (scheda.tipologiaImmobile)
		{
		    case "Appartamento":
		    	Categoria = "1";
		        break;
		    case "Attico":
		    	Categoria = "2";
		        break;
		    case "Bifamiliare":
		    	Categoria = "16";
		        break;
		    case "Casa":
		    	Categoria = "5";
		        break;
		    case "Garage":
		    	Categoria = "4";
		        break;
		    case "Palazzo":
		    	Categoria = "9";
		        break;
		    case "Rustico":
		    	Categoria = "10";
		        break;
		    case "Villa":
		    	Categoria = "17";
		        break;
		    case "Villaschiera":
		    	Categoria = "15";
		        break;
		    case "Agriturismo":
		    	Categoria = "19";
		        break;
		    case "Albergo":
		    	Categoria = "20";
		        break;
		    case "Bar":
		    	Categoria = "23";
		        break;
		    case "Negozio":
		    	Categoria = "29";
		        break;
		    case "Ristorante":
		    	Categoria = "30";
		        break;
		    case "Ufficio":
		    	Categoria = "33";
		        break;
		    case "Capannone":
		    	Categoria = "24";
		        break;
		    case "Laboratorio":
		    	Categoria = "26";
		        break;
		    case "Magazzino":
		    	Categoria = "28";
		        break;
		    case "Terreno residenziale":
		    	Categoria = "14";
		        break;
		    case "Terreno agricolo":
		    	Categoria = "13";
		        break;
		    case "Terreno industriale":
		    	Categoria = "32";
		        break;
		    default:
		    	Categoria = "";
		}
		mappaDeiParamerti.put("Categoria", Categoria);	
		
		
		idNazione = "91";	//91 è Italia (default)
		mappaDeiParamerti.put("idNazione", idNazione);
		
		
		switch (scheda.regione) {
			case "Abruzzo":
		    	RegioneSM = "13";
		        break;
		    case "Basilicata":
		    	RegioneSM = "16";
		    	break;
		    case "Calabria":
		    	RegioneSM = "18";
		    	break;
		    case "Campania":
		    	RegioneSM = "15";
		    	break;
		    case "Emilia-Romagna":
		    	RegioneSM = "8";
		    	break;
		    case "Friuli-Venezia Giulia":
		    	RegioneSM = "5";
		    	break;
		    case "Lazio":
		    	RegioneSM = "11";
		    	break;
		    case "Liguria":
		    	RegioneSM = "3";
		    	break;
		    case "Lombardia":
		    	RegioneSM = "4";
		    	break;
		    case "Marche":
		    	RegioneSM = "12";
		    	break;
		    case "Molise":
		    	RegioneSM = "14";
		    	break;
		    case "Piemonte":
		    	RegioneSM = "2";
		    	break;
		    case "Puglia":
		    	RegioneSM = "17";
		    	break;
		    case "Sardegna":
		    	RegioneSM = "20";
		    	break;
		    case "Sicilia":
		    	RegioneSM = "19";
		    	break;
		    case "Toscana":
		    	RegioneSM = "0";
		    	break;
		    case "Trentino-Alto Adige":
		    	RegioneSM = "7";
		    	break;
		    case "Umbria":
		    	RegioneSM = "10";
		    	break;
		    case "Valle d'Aosta":
		    	RegioneSM = "1";
		    	break;
		    case "Veneto":
		    	RegioneSM = "6";
		    	break;
		    default:
		    	RegioneSM = "0";
			}
		mappaDeiParamerti.put("RegioneSM", RegioneSM);
		
		
		//Recupero del codice della Provincia
		ProvinciaSM = "";
		HttpPortalGetConnection connessione_a = new HttpPortalGetConnection();
    	try {    		
    		Object[] response = connessione_a.get("Recupero del codice della Provincia", URLROOT + "/funzioni/select_provinceSM.php?idNazione=" + mappaDeiParamerti.get("idNazione") + "&idRegione=" + mappaDeiParamerti.get("RegioneSM") + "&idProvincia=0", debugMode);
    		String responseBody = (String)response[1];
    		
    		org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);              
            //Ottengo il valore del parametro Provincia
            Elements optionElements = doc.getElementsByTag("option");
            if(optionElements.isEmpty()) {
            	throw(new HttpWrongResponseBodyException("Non ho trovato tag di tipo \"option\""));
            }
            else {
            	Iterator<Element> iterator = optionElements.iterator();
            	double resultComparation = 0;
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	List<char[]> charListPortale = bigram(currentElement.html());
	        		List<char[]> charListImagination = bigram(scheda.provincia);
	        		double actualResultComparation = dice(charListPortale, charListImagination);
	        		if(actualResultComparation>=resultComparation) {
	        			resultComparation = actualResultComparation;
	        			ProvinciaSM = currentElement.attr("value");            		
	        		}       		
            	}
            }
		} catch (IOException | HttpWrongResponseBodyException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	mappaDeiParamerti.put("ProvinciaSM", ProvinciaSM);
    	
    	
		//Recupero del codice del Comune
    	ComuneSM = "";
		HttpPortalGetConnection connessione_b = new HttpPortalGetConnection();
    	try {
    		Object[] response = connessione_b.get("Recupero del codice del Comune", URLROOT + "/funzioni/select_comuniSM.php?idNazione=" + mappaDeiParamerti.get("idNazione") + "&idProvincia=" + mappaDeiParamerti.get("ProvinciaSM"), debugMode);
    		String responseBody = (String)response[1];
    		
    		org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);              
            //Ottengo il valore del parametro Provincia
            Elements optionElements = doc.getElementsByTag("option");
            if(optionElements.isEmpty()) {
            	throw(new HttpWrongResponseBodyException("Non ho trovato tag di tipo \"option\""));
            }
            else {
            	Iterator<Element> iterator = optionElements.iterator();
            	double resultComparation = 0;
            	while(iterator.hasNext()) {
	            	Element currentElement = iterator.next();
	            	List<char[]> charListPortale = bigram(currentElement.html());
	        		List<char[]> charListImagination = bigram(scheda.comune);
	        		double actualResultComparation = dice(charListPortale, charListImagination);
	        		if(actualResultComparation>=resultComparation) {
	        			resultComparation = actualResultComparation;
	        			ComuneSM = currentElement.attr("value");            		
	        		}       		
            	}
            }
		} catch (IOException | HttpWrongResponseBodyException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	mappaDeiParamerti.put("ComuneSM", ComuneSM);
		
    	
    	Codice = scheda.codiceInserzione;
		mappaDeiParamerti.put("Codice", Codice);
		
		
		switch (scheda.numeroLocali)
    	{
    	    case "1":
    	    	Locali = "1";
    	        break;
    	    case "2":
    	    	Locali = "2";
    	    	break;
    	    case "3":
    	    	Locali = "3";
    	    	break;
    	    case "4":
    	    	Locali = "4";
    	    	break;
    	    case "5":
    	    	Locali = "5";
    	    	break;
    	    case "6":
    	    	Locali = "6";
    	    	break;
    	    case "7":
    	    	Locali = "7";
    	    	break;
    	    case ">7":
    	    	Locali = "8";
    	    	break;
    	    default:
    	    	Locali = "0";
    	}
		mappaDeiParamerti.put("Locali", Locali);
		
		
		switch (scheda.tipologiaContratto)
		{
		    case "Affitto":
		    	Motivazione = "2";
		        break;
		    case "Vendita":
		    	Motivazione = "1";
		    	break;
		    default:
		    	Motivazione = "";
		}
		mappaDeiParamerti.put("Motivazione", Motivazione);
		
		
		Nazione = "91";
		mappaDeiParamerti.put("Nazione", Nazione);	
		
		
		Prezzo_Richiesto = scheda.prezzoImmobile;
		mappaDeiParamerti.put("Prezzo_Richiesto", Prezzo_Richiesto);
			
		
		Totale_mq = scheda.superficieImmobile;
		mappaDeiParamerti.put("Totale_mq", Totale_mq);
		
		
		act = "ins";
		mappaDeiParamerti.put("act", act);
		
		
		action = "1";
		mappaDeiParamerti.put("action", action);
		
		
		switch (scheda.numeroBagni)
		{
		    case "1":
		    	bagni = "1";
		        break;
		    case "2":
		    	bagni = "2";
		    	break;
		    case "3":
		    	bagni = "3";
		    	break;
		    case "4":
		    	bagni = "4";
		    	break;
		    case "5":
		    	bagni = "5";
		    	break;
		    case ">5":
		    	bagni = "6";
		    	break;
		    default:
		    	bagni = "0";
		}
		mappaDeiParamerti.put("bagni", bagni);
		
		
		switch (scheda.numeroCamere)
		{
			case "1":
				camere = "1";
		        break;
		    case "2":
		    	camere = "2";
		    	break;
		    case "3":
		    	camere = "3";
		    	break;
		    case "4":
		    	camere = "4";
		    	break;
		    case "5":
		    	camere = "5";
		    	break;
		    case ">5":
		    	camere = "6";
		    	break;
		    default:
		    	camere = "0";
		}
		mappaDeiParamerti.put("camere", camere);
		
		
		certificazione_acustica = "";
		mappaDeiParamerti.put("certificazione_acustica", certificazione_acustica);
		
		
		switch (scheda.certificazioniEnergetiche)
		{
		    case "Certificazione energetica A+":
		    	classe_energetica = "a+";
		    	break;
		    case "Certificazione energetica A":
		    	classe_energetica = "a";
		    	break;
		    case "Certificazione energetica B":
		    	classe_energetica = "b";
		    	break;
		    case "Certificazione energetica C":
		    	classe_energetica = "c";
		    	break;
		    case "Certificazione energetica D":
		    	classe_energetica = "d";
		    	break;
		    case "Certificazione energetica E":
		    	classe_energetica = "e";
		    	break;
		    case "Certificazione energetica F":
		    	classe_energetica = "f";
		    	break;
		    case "Certificazione energetica G":
		    	classe_energetica = "g";
		    	break;
		    case "Non specificata":
		    	classe_energetica = "";
		        break;
		    default:
		    	classe_energetica = "";
		}
		mappaDeiParamerti.put("classe_energetica", classe_energetica);
			
		
		switch (scheda.certificazioneIpe)
		{
		    case "Immobile certificato":
		    	epi = "Immobile certificato";
		    	break;
		    case "Immobile esente da certificazione":
		    	epi = "Immobile esente da certificazione";
		    	break;
		    case "Documentazione non esistente":
		    	epi = "Documentazione non esistente";
		    	break;
		    default:
		    	epi = "";
		}
		mappaDeiParamerti.put("epi", epi);
		
		
		idImmobile = "";
		mappaDeiParamerti.put("idImmobile", idImmobile);
		
		
		indirizzo = scheda.indirizzoLocalita;
		mappaDeiParamerti.put("indirizzo", indirizzo);
		
		
		Map<String, String> latLon;
		try {
			latLon = getLatLonCoord(scheda.indirizzoLocalita, scheda.comune, scheda.provincia, scheda.regione);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new HttpCommunicationException(e);
		}
		
		latitudine = latLon.get("latitudine");
		mappaDeiParamerti.put("latitudine", latitudine);
		
		longitudine = latLon.get("longitudine");
		mappaDeiParamerti.put("longitudine", longitudine);
		
		
		Ascensore = scheda.ascensore?"1":"0";
		mappaDeiParamerti.put("Ascensore", Ascensore);
		
		Balconi = "";
		mappaDeiParamerti.put("Balconi", Balconi);
		
		Box = "";
		mappaDeiParamerti.put("Box", Box);
		
		Cucina = "";
		mappaDeiParamerti.put("Cucina", Cucina);
		
		
		Disposizione_interna = scheda.testoAnnuncio;
		mappaDeiParamerti.put("Disposizione_interna", Disposizione_interna);
			
		
		//if(Integer.parseInt(scheda.annoCostruzione)>=1950) {
			//Eta_Costruzione =  scheda.annoCostruzione;
		//}
		//else {
			Eta_Costruzione = "";
		//}
		mappaDeiParamerti.put("Eta_Costruzione", Eta_Costruzione);
		
		
		switch (scheda.giardino)
		{
		    case "Assente":
		    	Giardino = "";
		        break;
		    case "Giardino condominiale":
		    	Giardino = "Comune";
		    	break;
		    case "Giardino ad uso esclusivo":
		    	Giardino = "Privato";
		    	break;
		    default:
		    	Giardino = "";
		}
		mappaDeiParamerti.put("Giardino", Giardino);
		
		
		switch (scheda.parcheggio)
		{
		    case "Nessuno":
		    	Posto_Auto = "";
		        break;
		    case "Garage":
		    	Garage =  "Presente";  //la input testuale prende anche parole come value
		    	Posto_Auto = "";
		    	break;
		    case "Posto auto coperto":
		    	Posto_Auto = "Coperto";
		    	break;
		    case "Posto auto scoperto":
		    	Posto_Auto = "Scoperto";
		    	break;
		    default:
		    	Posto_Auto = "";
		    	Garage = "";
		}
		mappaDeiParamerti.put("Posto_Auto", Posto_Auto);
		mappaDeiParamerti.put("Garage", Garage);
		
		
		switch (scheda.tipologiaRiscaldamento) {	//nell'area commerciale il parametro è scritto in maniera differente
		    case "Assente":
		    	Riscaldamento_Autonomo = "Assente";
		    	riscaldamento_autonomo = "Assente";
		        break;
		    case "Centralizzato":
		    	Riscaldamento_Autonomo = "Centralizzato";
		    	riscaldamento_autonomo = "Centralizzato";
		    	break;
		    case "Autonomo":
		    	Riscaldamento_Autonomo = "Autonomo";
		    	riscaldamento_autonomo = "Autonomo";
		    	break;
		    case "Stufa":
		    	Riscaldamento_Autonomo = "Autonomo"; //è autonomo...
		    	riscaldamento_autonomo = "Autonomo";
		    	break;
		    default:
		    	Riscaldamento_Autonomo = "";
		    	riscaldamento_autonomo = "";
			}
			
		switch (scheda.clima)
		{
		    case "Assente":
		        break;
		    case "Aria condizionata":
		    	Riscaldamento_Autonomo = "Climatizzato";	//dò precedenza al clima
		    	riscaldamento_autonomo = "Climatizzato";	//dò precedenza al clima
		    	break;
		    case "Climatizzatore":
		    	break;
		    default:
		}
		mappaDeiParamerti.put("Riscaldamento_Autonomo", Riscaldamento_Autonomo);
		mappaDeiParamerti.put("riscaldamento_autonomo", Riscaldamento_Autonomo);
		
		
		Terrazzo = "";
		mappaDeiParamerti.put("Terrazzo", Terrazzo);
		
		
		costruzione_presente = "";
		mappaDeiParamerti.put("costruzione_presente", costruzione_presente);
			
		
		switch (scheda.arredamenti)
		{
		    case "Arredato":
		    	arredato = "1";
		        break;
		    case "Semi arredato":
		    	arredato = "0";
		    	break;
		    case "Non arredato":
		    	arredato = "0";
		    	break;
		    default:
		    	arredato = "0";
		}
		mappaDeiParamerti.put("arredato", arredato);
		
		
		attivita_consentite = "";
		mappaDeiParamerti.put("attivita_consentite", attivita_consentite);
		
		
		esposizione = "";
		mappaDeiParamerti.put("esposizione", esposizione);
		
		
		mq_agricoli = "";
		mappaDeiParamerti.put("mq_agricoli", mq_agricoli);
		
		
		mq_edificabili = "";
		mappaDeiParamerti.put("mq_edificabili", mq_edificabili);
		
		
		possibili_realizzazione = "";
		mappaDeiParamerti.put("possibili_realizzazione", possibili_realizzazione);
		
		
		progetto_approvato = "";
		mappaDeiParamerti.put("progetto_approvato", progetto_approvato);
		
		
		mq_coperti = "";
		mappaDeiParamerti.put("mq_coperti", mq_coperti);
		
		
		mq_copribili = "";
		mappaDeiParamerti.put("mq_copribili", mq_copribili);
		
		
		mq_scoperti = "";
		mappaDeiParamerti.put("mq_scoperti", mq_scoperti);
		
		
		num_vetrine = "";
		mappaDeiParamerti.put("num_vetrine", num_vetrine);
		
		
		//if(Integer.parseInt(scheda.numeroTotalePiani)<=70) {
			piani_totali =  scheda.numeroTotalePiani;
		//}
		//else {
			//piani_totali = "";
		//}
		mappaDeiParamerti.put("piani_totali", piani_totali);
		
		
		posizione = "";
		mappaDeiParamerti.put("posizione", posizione);
		
		
		tipo_terreno = "";
		mappaDeiParamerti.put("tipo_terreno", tipo_terreno);
		

	}
	
	
}