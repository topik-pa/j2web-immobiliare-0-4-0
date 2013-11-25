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
public class _cuboAutoIt extends PortaleWeb {     

	//Variabili portale
	private final String NOMEPORTALE = "www.cuboauto.it";
	private final String URLROOT = "http://www.cuboauto.it";
	private final String USERNAME = "topik123";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.cuboauto.it";
	private final String SESSIONCOOKIENAME = "PHPSESSID";
	private final String SESSIONCOOKIEDOMAIN = "www.cuboauto.it";

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean debugMode = true;

	//Strutture dati di supporto
	//Mappa dei parametri da inviare
	Map<String,String> mappaDeiParamerti;

	//Lista dei parametri inviati in una singola connessione
	List<NameValuePair> postParameters;

	//Lista degli headers inviati in una singola connessione
	List<NameValuePair> requestHeaders;
	
	//Mappa che rappresenta la tabella di dipendennza dei parametri da inviare
	Map<String,String> tabellaDiDipendenza;

	//La scheda immobile su cui si lavora
	SchedaVeicolo scheda;
	
	//Altre variabili di supporto a livello globale
	String var_idMarca;

	//Costruttore
	public _cuboAutoIt (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

		super(icon, valoreLabel, idPortale, isActive);		

		//La hashTable contenente i valori dei parametri da inviare durante la sessione
		mappaDeiParamerti =  new Hashtable<String,String>();

		//La lista dei parametri (nome-valore) inviati
		postParameters = new ArrayList<NameValuePair>();	

		//La lista degli header (nome-valore) inviati
		requestHeaders = new ArrayList<NameValuePair>();

		//Iniziallizzo la tabella di dipendenza
		tabellaDiDipendenza = new Hashtable<String,String>();
	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
		System.out.println("Inserimento scheda: " + scheda.codiceScheda + "...");
		
		//Inizializzazione scheda
		this.scheda=scheda;
		
		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		requestHeaders = new ArrayList<NameValuePair>();
		requestHeaders.add(new BasicNameValuePair("Host", HOST));
		

		//Connessione 0 - GET della home page - Opzionale
		HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT + "/index.php", requestHeaders, debugMode);
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/login.php", requestHeaders, debugMode);
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
		//Raccolgo i parametri nella tabella di dipendennza
		tabellaDiDipendenza.put("password",PASSWORD);
		tabellaDiDipendenza.put("submit","Entra");
		tabellaDiDipendenza.put("username",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#frmLogin input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/_login.php", postParameters, requestHeaders, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				connessione_2.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
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
			tabellaDiDipendenza.clear();
			mappaDeiParamerti.clear();
			postParameters.clear();
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
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("idMarca",scheda.marcaVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
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
		finally {
			var_idMarca = mappaDeiParamerti.get("idMarca");
			tabellaDiDipendenza.clear();
			mappaDeiParamerti.clear();	
		}
			
		
		
		//Connessione 6 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("CV",scheda.CVVeicolo);
		tabellaDiDipendenza.put("KW",scheda.KWVeicolo);
		tabellaDiDipendenza.put("Submit","Salva Annuncio"); 
		tabellaDiDipendenza.put("annoimmatricolazione",scheda.annoImmatricolazioneVeicolo);
		tabellaDiDipendenza.put("annoprossimarevisione","");
		tabellaDiDipendenza.put("cambio",scheda.tipologiaCambioVeicolo);
		tabellaDiDipendenza.put("chilometri",scheda.chilometraggioVeicolo);
		tabellaDiDipendenza.put("cilindrata",scheda.cilindrataVeicolo);
		tabellaDiDipendenza.put("cilindri","");
		tabellaDiDipendenza.put("codice",scheda.marcaVeicolo+scheda.modelloVeicolo+scheda.coloreEsternoVeicolo); //da aggiungere in form
		tabellaDiDipendenza.put("coloredett","");
		tabellaDiDipendenza.put("coloreesterno",scheda.coloreEsternoVeicolo);
		tabellaDiDipendenza.put("coloreinterni",scheda.coloreInterniVeicolo);
		tabellaDiDipendenza.put("contratto","Vendita"); //da aggiungere in form
		tabellaDiDipendenza.put("descrizione",scheda.descrizioneVeicolo);
		tabellaDiDipendenza.put("idAlimentazione",scheda.carburanteVeicolo);
		tabellaDiDipendenza.put("idCarrozzeria",""); //da implementare come ogbbligatorio
		tabellaDiDipendenza.put("idMarca",var_idMarca);
		tabellaDiDipendenza.put("idMarca2",var_idMarca);
		tabellaDiDipendenza.put("idModello",scheda.modelloVeicolo);
		tabellaDiDipendenza.put("idTipologia",scheda.tipologiaVeicolo);
		tabellaDiDipendenza.put("meseimmatricolazione",scheda.meseImmatricolazioneVeicolo);
		tabellaDiDipendenza.put("meseprossimarevisione","");
		tabellaDiDipendenza.put("normativa",scheda.classeEmissioniVeicolo);
		tabellaDiDipendenza.put("peso","");
		tabellaDiDipendenza.put("porte","");
		tabellaDiDipendenza.put("prezzo",scheda.prezzoVeicolo);
		tabellaDiDipendenza.put("provimmatricolazione","");
		tabellaDiDipendenza.put("rapporti",scheda.numeroRapportiVeicolo);
		tabellaDiDipendenza.put("sedili",scheda.postiASedereVeicolo);
		tabellaDiDipendenza.put("versione",scheda.versioneVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#centrale form input, #centrale form select, #centrale form textarea", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", URLROOT + "/concessionari/_inserisci-annuncio.php", postParameters, requestHeaders, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("?id=")) {
					int start = location.indexOf("?id=");
					int end = location.length();
					codiceInserzione = location.substring(start, end);
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
			tabellaDiDipendenza.clear();
		}
		

        //Connessione 7 - GET della pagina "Dettaglio annuncio" - Opzionale
    	HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
    	try {
    		Object[] response =  connessione_7.get("Connessione 7 - GET della pagina \"Dettaglio annuncio\"", URLROOT + "/" + location, requestHeaders, debugMode);
    		//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 8 - GET della pagina "Inserisci una nuova foto" - Opzionale
    	HttpPortalGetConnection connessione_8 = new HttpPortalGetConnection();
    	try {
    		Object[] response =  connessione_8.get("Connessione 8 - GET della pagina \"Inserisci una nuova foto\"", URLROOT + "/concessionari/foto.php?id=" + codiceInserzione, requestHeaders, debugMode);
    		//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


    	/*//Connessione 8 - POST dello step 2
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

	
}