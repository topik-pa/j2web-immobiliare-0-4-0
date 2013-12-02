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
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;

/**
 *
 * @author marco
 */

//La classe principale
public class _subitoIt extends PortaleWeb {     

	//Variabili portale
	private final String NOMEPORTALE = "www.subito.it";
	private final String URLROOT = "http://www.subito.it";
	private final String SECUREURLROOT = "https://www2.subito.it";
	private final String USERNAME = "c1669723@drdrb.com";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.subito.it";
	private final String HOST2 = "www2.subito.it";
	
	private final String SESSIONCOOKIENAME = "s";
	private final String SESSIONCOOKIEDOMAIN = ".subito.it";
	private final String SESSIONCOOKIEHEADER = "";
	private final String SESSIONCOOKIEVALUE = "";
	

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	private BasicNameValuePair sessionCookie;
	private boolean inserimentoOK = false;
	private boolean debugMode = true;

	//Strutture dati di supporto
	//Mappa dei parametri da inviare
	Map<String,String> mappaDeiParamerti;

	//Lista dei parametri inviati in una singola connessione
	List<NameValuePair> postParameters;

	//Lista degli headers inviati in una singola connessione
	List<NameValuePair> requestHeaders;
	
	//Lista dei cookies inviati in una singola connessione
	List<BasicClientCookie> requestCookies;

	//Mappa che rappresenta la tabella di dipendennza dei parametri da inviare
	Map<String,String> tabellaDiDipendenza;

	//La scheda immobile su cui si lavora
	SchedaVeicolo scheda;

	//Altre variabili di supporto a livello globale
	String var_idMarca;

	//Costruttore
	public _subitoIt (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

		super(icon, valoreLabel, idPortale, isActive);

		//La hashTable contenente i valori dei parametri da inviare durante la sessione
		mappaDeiParamerti =  new Hashtable<String,String>();

		//La lista dei parametri (nome-valore) inviati
		postParameters = new ArrayList<NameValuePair>();	

		//La lista degli header (nome-valore) inviati
		requestHeaders = new ArrayList<NameValuePair>();
		
		//La lista dei cookies inviati
		requestCookies = new ArrayList<BasicClientCookie>();

		//Iniziallizzo la tabella di dipendenza
		tabellaDiDipendenza = new Hashtable<String,String>();

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
		System.out.println("Inserimento scheda: " + scheda.codiceScheda + "...");

		//Inizializzazione scheda
		this.scheda=scheda;

		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		requestHeaders.add(new BasicNameValuePair("Host", HOST));
		requestHeaders.add(new BasicNameValuePair("User-Agent", USER_AGENT_VALUE));	
		requestHeaders.add(new BasicNameValuePair("Connection", CONNECTION));
		requestHeaders.add(new BasicNameValuePair("Cache-Control", CACHE_CONTROL));
		requestHeaders.add(new BasicNameValuePair("Accept-Language", ACCEPT_LANGUAGE));
		requestHeaders.add(new BasicNameValuePair("Accept", ACCEPT));

		//Connessione 0 - GET della home page - Opzionale
		HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT, requestHeaders, null, debugMode);
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", SECUREURLROOT + "/account/login_form/", requestHeaders, null, debugMode);
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
		tabellaDiDipendenza.put("login_email",USERNAME);
		tabellaDiDipendenza.put("login_passwd",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#boxLoginContainer input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(0);
		requestHeaders.add(new BasicNameValuePair("Host", HOST2));
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", SECUREURLROOT + "/account/login", postParameters, requestHeaders, null, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Gestione dei cookie
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				connessione_2.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
				setCookies(responseHeaders, requestCookies);			
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


		//Connessione 3 - GET della pagina di redirect dopo inserimento parametri login
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di redirect dopo inserimento parametri login", SECUREURLROOT + location, requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
			}
			else if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		
		
		//Connessione 4 - GET della pagina di redirect dopo inserimento parametri login
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina di redirect dopo inserimento parametri login", SECUREURLROOT + "/account/manageads/", requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 5 - GET della pagina "Inserisci il tuo annuncio"
		HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_5.get("Connessione 5 - GET della pagina \"Inserisci il tuo annuncio\"", SECUREURLROOT + "/ai/form/0", requestHeaders, requestCookies, debugMode);
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


		//Connessione 6 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("check_type_diff", "1");
		tabellaDiDipendenza.put("category", "Auto"); //Auto
		tabellaDiDipendenza.put("animal_type","");
		tabellaDiDipendenza.put("office_type",""); 
		tabellaDiDipendenza.put("room_type",""); 
		tabellaDiDipendenza.put("ship_type",""); 
		tabellaDiDipendenza.put("vehicle_type",""); 
		tabellaDiDipendenza.put("caravan_type",""); 
		tabellaDiDipendenza.put("sport_type",""); 
		tabellaDiDipendenza.put("children_type",""); 
		tabellaDiDipendenza.put("children_age",""); 
		tabellaDiDipendenza.put("hobby_type",""); 
		tabellaDiDipendenza.put("audiovideo_type",""); 
		tabellaDiDipendenza.put("bicycle_type","");
		tabellaDiDipendenza.put("phone_type",""); 
		tabellaDiDipendenza.put("bikebrand","");
		tabellaDiDipendenza.put("bikeversion",""); 
		tabellaDiDipendenza.put("moto_type","");
		tabellaDiDipendenza.put("computer_type",""); 
		tabellaDiDipendenza.put("clothing_type","");
		tabellaDiDipendenza.put("clothing_gender",""); 
		tabellaDiDipendenza.put("clothing_number","");
		tabellaDiDipendenza.put("region", "Friuli-Venezia Giulia"); //Friuli-Venezia Giulia
		tabellaDiDipendenza.put("city","Udine"); //Udine
		tabellaDiDipendenza.put("town","Udine"); //Udine
		tabellaDiDipendenza.put("zone","");
		tabellaDiDipendenza.put("company_ad", "1"); //Azienda
		tabellaDiDipendenza.put("name","autoeauto"); //autoeauto
		tabellaDiDipendenza.put("servicetype","Seleziona la tipologia");
		tabellaDiDipendenza.put("phone",scheda.Telefono);
		tabellaDiDipendenza.put("type","s"); //Vendita
		tabellaDiDipendenza.put("carbrand",scheda.marcaVeicolo);
		tabellaDiDipendenza.put("carmodel","XXX"); //da aggiungere
		tabellaDiDipendenza.put("regdate",scheda.annoImmatricolazioneVeicolo);
		tabellaDiDipendenza.put("carversion", "XXX"); //da fare
		tabellaDiDipendenza.put("mileage","2"); //da fare
		tabellaDiDipendenza.put("fuel",scheda.carburanteVeicolo);
		tabellaDiDipendenza.put("country","Seleziona");
		tabellaDiDipendenza.put("car_type",scheda.carrozzeriaVeicolo);
		tabellaDiDipendenza.put("gearbox",scheda.tipologiaCambioVeicolo);
		tabellaDiDipendenza.put("pollution",scheda.classeEmissioniVeicolo);
		tabellaDiDipendenza.put("seats",scheda.postiASedereVeicolo);
		tabellaDiDipendenza.put("doors","Porte");
		tabellaDiDipendenza.put("color","3"); //da fare
		tabellaDiDipendenza.put("subject",scheda.marcaVeicolo + " " + scheda.modelloVeicolo);
		tabellaDiDipendenza.put("price",scheda.prezzoVeicolo);
		tabellaDiDipendenza.put("gender","Scegli");
		tabellaDiDipendenza.put("smoker","Scegli");
		tabellaDiDipendenza.put("cubic_capacity",scheda.cilindrataVeicolo);
		tabellaDiDipendenza.put("rooms","Seleziona");
		tabellaDiDipendenza.put("size","");
		tabellaDiDipendenza.put("length","");
		tabellaDiDipendenza.put("contract_type","Seleziona il tipo di contratto");
		tabellaDiDipendenza.put("degree","Seleziona il tuo titolo di studio");
		tabellaDiDipendenza.put("worklevel","Seleziona l'inquadramento");
		tabellaDiDipendenza.put("workhours","Seleziona l'orario di lavoro");
		tabellaDiDipendenza.put("body",scheda.descrizioneVeicolo);
		tabellaDiDipendenza.put("cites_cert_numb","");
		tabellaDiDipendenza.put("cites_cert_date","");
		tabellaDiDipendenza.put("cites_cert_from","");
		tabellaDiDipendenza.put("show_map",""); //non mostrare la mappa
		tabellaDiDipendenza.put("address","");
		tabellaDiDipendenza.put("latitude","");
		tabellaDiDipendenza.put("longitude","");
		tabellaDiDipendenza.put("zoom","");
		tabellaDiDipendenza.put("image","");
		tabellaDiDipendenza.put("accept_equal_opp","1"); //annuncio per ambo i sessi
		tabellaDiDipendenza.put("accept_term_of_use","1"); //accetto i termini d'uso
		tabellaDiDipendenza.put("validate","continua");
		
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#content form input, #content form select, #content form textarea", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", SECUREURLROOT + "/ai/verify/0", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(!location.contains("/ai/preview")) {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Header Location non previsto 1"));
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto 2"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			postParameters.clear();
			mappaDeiParamerti.clear();
			tabellaDiDipendenza.clear();
		}


		//Connessione 7 - GET della pagina "Preview annuncio" - Opzionale
		HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
		try {
			Object[] response =  connessione_7.get("Connessione 7 - GET della pagina \"Preview annuncio\"", SECUREURLROOT + location, requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}

/*
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


		//Connessione 9 - Invio delle foto
		for(int i=1; i<scheda.arrayImages.length; i++) {
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_9 = new HttpPortalPostConnection();        
				try {

					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					FileBody bin = new FileBody(scheda.arrayImages[i]);
					reqEntity.addPart("foto", bin );
					reqEntity.addPart("id", new StringBody(codiceInserzione) );
					reqEntity.addPart("Submit", new StringBody("Invia la foto") );

					Object[] response = connessione_9.post("Connessione 9 - Invio delle foto", URLROOT + "/concessionari/_foto.php", reqEntity, requestHeaders, debugMode);			

					//Controllo il response status
					BasicStatusLine responseStatus = (BasicStatusLine) response[2];
					if( (responseStatus.getStatusCode()!=302)) {
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
			}
		}*/
		
		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		//requestHeaders.remove(sessionCookie);

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

		codiceInserzione = scheda.getCodiceInserimento(idPortale);
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/concessionari/dett-annuncio.php?id=" + codiceInserzione;
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
				sessionCookie = new BasicNameValuePair("Cookie", "PHPSESSID" + "=" + SESSIONCOOKIEVALUE);
				requestHeaders.add(sessionCookie);
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


		//Connessione 3 - GET della pagina di eliminazione annuncio
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di eliminazione annuncio", URLROOT + "/concessionari/_del_annunci.php?id=" + codiceInserzione, requestHeaders, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (!(responseStatus.getStatusCode()==200 || responseStatus.getStatusCode()==302))) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
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