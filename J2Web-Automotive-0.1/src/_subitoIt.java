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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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

/**
 *
 * @author marco
 */

//La classe principale
public class _subitoIt extends PortaleWeb {     

	//Variabili generali
	private final String NOMEPORTALE = "www.subito.it";
	private final String URLROOT = "http://www.subito.it";
	private final String USERNAME = "c1669723@drdrb.com";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.subito.it";

	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean debugMode = true;

	//Mappa dei parametri da inviare
	Map<String,String> mappaDeiParamerti;

	//Lista dei parametri inviati in una singola connessione
	List<NameValuePair> postParameters;

	//Lista degli headers inviati in una singola connessione
	List<NameValuePair> requestHeaders;

	//La scheda immobile su cui si lavora
	SchedaVeicolo scheda;

	//Lista di alcuni dei parametri inviati	
	String idMarca;

	//Costruttore
	public _subitoIt (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

		super(icon, valoreLabel, idPortale, isActive);

		//Nome e dominio del cookie di sessione
		SESSIONCOOKIENAME = "PHPSESSID";
		SESSIONCOOKIEDOMAIN = ".subito.it";

		//La hashTable contenente i valori dei parametri da inviare durante la sessione
		mappaDeiParamerti =  new Hashtable<String,String>();

		//La lista dei parametri (nome-valore) inviati
		postParameters = new ArrayList<NameValuePair>();	

		//La lista degli header (nome-valore) inviati
		requestHeaders = new ArrayList<NameValuePair>();

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
		System.out.println("Inserimento scheda: " + scheda.codiceScheda + "...");
		
		//Inizializzazione parametri
		this.scheda=scheda;
		
		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		requestHeaders = new ArrayList<NameValuePair>();
		requestHeaders.add(new BasicNameValuePair("Host", HOST));
		
		//Tabella di dipendenza dei parametri
		Map<String,String> tabellaDiDipendenza = new Hashtable<String,String>();
		tabellaDiDipendenza.put("password",PASSWORD);
		tabellaDiDipendenza.put("submit","Entra");
		tabellaDiDipendenza.put("username",USERNAME);
		tabellaDiDipendenza.put("idMarca",scheda.marcaVeicolo);
		

		//Connessione 0 - GET della home page - Opzionale
		HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT + "/index.php", debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/login.php", debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
	
		
		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo e valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#frmLogin input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/_login.php", postParameters, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				//Aggiungo il cookie di sessione ai requestHeaders
				requestHeaders.add(new BasicNameValuePair("Cookie", "PHPSESSID" + "=" + SESSIONCOOKIEVALUE));
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			postParameters.clear();
			mappaDeiParamerti.clear();
		}


		//Connessione 3 - GET della pagina "Area concessionario" - Opzionale
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area concessionario\"", URLROOT + "/" + location, requestHeaders, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 4 - GET della pagina "Inserisci un nuovo annuncio"
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo annuncio\"", URLROOT + "/concessionari/inserisci-annuncio.php", requestHeaders, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		
		
		//Connessione 5 - GET della pagina "Inserisci un nuovo annuncio (con parametro circa la marca veicolo)"
		//Raccolgo e valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "select#idMarca", tabellaDiDipendenza, mappaDeiParamerti);
		HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_5.get("Connessione 5 - GET della pagina \"Inserisci un nuovo annuncio (con parametro circa la marca veicolo)\"", URLROOT + "/concessionari/inserisci-annuncio.php?idMarca=" + mappaDeiParamerti.get("idMarca"), requestHeaders, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		mappaDeiParamerti.clear();
		
		
		//Connessione 6 - POST dei parametri di accesso
		//Raccolgo e valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#centrale form input", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/concessionari/_inserisci-annuncio.php", postParameters, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("?id=")) {
					int start = location.indexOf("?id=");
					int end = location.length();
					location = location.substring(start, end);
				}
				else {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Header Location non previsto"));
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			postParameters.clear();
			mappaDeiParamerti.clear();
		}
		

		/*
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
		}*/


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
		}

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
			PanelSicronizzazioneConPortali.updatePanello(scheda, false);

			System.out.println("Eliminata da: " + NOMEPORTALE);

			//Stampo a video un messaggio informativo
			JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
		}

		return true;

	}


	//Metodo per la valutazione dei parametri
	public boolean inizializzaParametri() throws HttpCommunicationException {		
		
		switch (scheda.marcaVeicolo)
		{
		case "Abarth":
			idMarca = "130";
			break;
		case "AC":
			idMarca = "1";
			break;
		case "Acura":
			idMarca = "2";
			break;
		case "Alfa Romeo":
			idMarca = "4";
			break;
		case "Allard":
			idMarca = "";
			break;
		case "Alpina":
			idMarca = "13";
			break;
		case "Alpine":
			idMarca = "";
			break;
		case "Alvis":
			idMarca = "";
			break;
		case "AMC":
			idMarca = "125";
			break;
		case "Ariel":
			idMarca = "126";
			break;
		case "Armstrong Siddeley":
			idMarca = "";
			break;
		case "Ascari":
			idMarca = "";
			break;
		case "Aston Martin":
			idMarca = "6";
			break;
		case "Audi":
			idMarca = "7";
			break;
		case "Austin":
			idMarca = "8";
			break;
		case "Austin-Healey":
			idMarca = "";
			break;
		case "Autobianchi":
			idMarca = "9";
			break;
		case "Auverland":
			idMarca = "";
			break;
		case "Avanti":
			idMarca = "";
			break;
		case "Beijing":
			idMarca = "";
			break;
		case "Bentley":
			idMarca = "10";
			break;
		case "Berkeley":
			idMarca = "";
			break;
		case "Bitter":
			idMarca = "";
			break;
		case "Bizzarrini":
			idMarca = "";
			break;
		case "BMW":
			idMarca = "12";
			break;
		case "Brilliance":
			idMarca = "14";
			break;
		case "Bristol":
			idMarca = "";
			break;
		case "Bugatti":
			idMarca = "15";
			break;
		case "Buick":
			idMarca = "16";
			break;
		case "Cadillac":
			idMarca = "17";
			break;
		case "Caterham":
			idMarca = "19";
			break;
		case "Checker":
			idMarca = "";
			break;
		case "Chevrolet":
			idMarca = "21";
			break;
		case "Chrysler":
			idMarca = "22";
			break;
		case "Citroen":
			idMarca = "23";
			break;
		case "Dacia":
			idMarca = "25";
			break;
		case "Daewoo":
			idMarca = "26";
			break;
		case "DAF":
			idMarca = "27";
			break;
		case "Daihatsu":
			idMarca = "28";
			break;
		case "Daimler":
			idMarca = "";
			break;
		case "Datsun":
			idMarca = "";
			break;
		case "De Tomaso":
			idMarca = "30";
			break;
		case "DKW":
			idMarca = "";
			break;
		case "Dodge":
			idMarca = "31";
			break;
		case "Donkervoort":
			idMarca = "32";
			break;
		case "Eagle":
			idMarca = "";
			break;
		case "Fairthorpe":
			idMarca = "";
			break;
		case "Ferrari":
			idMarca = "33";
			break;
		case "Fiat":
			idMarca = "34";
			break;
		case "Fisker":
			idMarca = "";
			break;
		case "Ford":
			idMarca = "35";
			break;
		case "GAZ":
			idMarca = "138";
			break;
		case "Geely":
			idMarca = "144";
			break;
		case "Ginetta":
			idMarca = "39";
			break;
		case "GMC":
			idMarca = "40";
			break;
		case "Holden":
			idMarca = "";
			break;
		case "Honda":
			idMarca = "42";
			break;
		case "Hudson":
			idMarca = "";
			break;
		case "Humber":
			idMarca = "";
			break;
		case "Hummer":
			idMarca = "43";
			break;
		case "Hyundai":
			idMarca = "44";
			break;
		case "Infiniti":
			idMarca = "45";
			break;
		case "Innocenti":
			idMarca = "46";
			break;
		case "Isuzu":
			idMarca = "47";
			break;
		case "Italdesign":
			idMarca = "";
			break;
		case "Jaguar":
			idMarca = "49";
			break;
		case "Jeep":
			idMarca = "51";
			break;
		case "Jensen":
			idMarca = "";
			break;
		case "Kia":
			idMarca = "53";
			break;
		case "Koenigsegg":
			idMarca = "142";
			break;
		case "Lada":
			idMarca = "55";
			break;
		case "Lamborghini":
			idMarca = "56";
			break;
		case "Lancia":
			idMarca = "57";
			break;
		case "Land Rover":
			idMarca = "58";
			break;
		case "Lexus":
			idMarca = "60";
			break;
		case "Lincoln":
			idMarca = "62";
			break;
		case "Lotec":
			idMarca = "";
			break;
		case "Lotus":
			idMarca = "63";
			break;
		case "Luxgen":
			idMarca = "";
			break;
		case "Mahindra":
			idMarca = "64";
			break;
		case "Marcos":
			idMarca = "65";
			break;
		case "Maserati":
			idMarca = "66";
			break;
		case "Matra-Simca":
			idMarca = "";
			break;
		case "Maybach":
			idMarca = "67";
			break;
		case "Mazda":
			idMarca = "68";
			break;
		case "MCC":
			idMarca = "";
			break;
		case "McLaren":
			idMarca = "154";
			break;
		case "Mercedes-Benz":
			idMarca = "69";
			break;
		case "Mercury":
			idMarca = "70";
			break;
		case "MG":
			idMarca = "71";
			break;
		case "Mini":
			idMarca = "73";
			break;
		case "Mitsubishi":
			idMarca = "74";
			break;
		case "Monteverdi":
			idMarca = "";
			break;
		case "Moretti":
			idMarca = "";
			break;
		case "Morgan":
			idMarca = "75";
			break;
		case "Morris":
			idMarca = "";
			break;
		case "Nissan":
			idMarca = "77";
			break;
		case "Noble":
			idMarca = "124";
			break;
		case "NSU":
			idMarca = "152";
			break;
		case "Oldsmobile":
			idMarca = "78";
			break;
		case "Opel":
			idMarca = "80";
			break;
		case "Packard":
			idMarca = "";
			break;
		case "Pagani":
			idMarca = "82";
			break;
		case "Panoz":
			idMarca = "";
			break;
		case "Peugeot":
			idMarca = "84";
			break;
		case "Pininfarina":
			idMarca = "";
			break;
		case "Plymouth":
			idMarca = "86";
			break;
		case "Pontiac":
			idMarca = "88";
			break;
		case "Porsche":
			idMarca = "89";
			break;
		case "Proton":
			idMarca = "90";
			break;
		case "Reliant":
			idMarca = "";
			break;
		case "Renault":
			idMarca = "92";
			break;
		case "Riley":
			idMarca = "";
			break;
		case "Rolls-Royce":
			idMarca = "93";
			break;
		case "Rover":
			idMarca = "94";
			break;
		case "Saab":
			idMarca = "95";
			break;
		case "Saleen":
			idMarca = "";
			break;
		case "Samsung":
			idMarca = "";
			break;
		case "Saturn":
			idMarca = "";
			break;
		case "Scion":
			idMarca = "";
			break;
		case "Seat":
			idMarca = "97";
			break;
		case "Simca":
			idMarca = "";
			break;
		case "Singer":
			idMarca = "";
			break;
		case "Skoda":
			idMarca = "98";
			break;
		case "Smart":
			idMarca = "99";
			break;
		case "Spyker":
			idMarca = "131";
			break;
		case "SsangYong":
			idMarca = "100";
			break;
		case "SSC":
			idMarca = "";
			break;
		case "Steyr":
			idMarca = "";
			break;
		case "Studebaker":
			idMarca = "";
			break;
		case "Subaru":
			idMarca = "101";
			break;
		case "Sunbeam":
			idMarca = "";
			break;
		case "Suzuki":
			idMarca = "102";
			break;
		case "Talbot":
			idMarca = "103";
			break;
		case "Tata":
			idMarca = "104";
			break;
		case "Tatra":
			idMarca = "";
			break;
		case "Tesla":
			idMarca = "150";
			break;
		case "Toyota":
			idMarca = "106";
			break;
		case "Trabant":
			idMarca = "107";
			break;
		case "Triumph":
			idMarca = "109";
			break;
		case "TVR":
			idMarca = "111";
			break;
		case "Vauxhall":
			idMarca = "112";
			break;
		case "Vector":
			idMarca = "";
			break;
		case "Venturi":
			idMarca = "113";
			break;
		case "Volkswagen":
			idMarca = "114";
			break;
		case "Volvo":
			idMarca = "115";
			break;
		case "Wartburg":
			idMarca = "116";
			break;
		case "Westfield":
			idMarca = "";
			break;
		case "Willys-Overland":
			idMarca = "";
			break;
		case "Xedos":
			idMarca = "";
			break;
		case "Zagato":
			idMarca = "";
			break;
		case "Zastava":
			idMarca = "119";
			break;
		case "ZAZ":
			idMarca = "147";
			break;
		case "Zenvo":
			idMarca = "";
			break;
		case "ZIL":
			idMarca = "";
			break;
		default:
			idMarca = "";
		}
		if(idMarca.equals("")) {return false;}
		mappaDeiParamerti.put("idMarca", idMarca);
		
		
		return true;
		
	}

	
}