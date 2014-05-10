/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */ 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author marco
 */

//La classe principale
public class _autoprontaconsegnaCom extends PortaleWeb {     

	//Variabili portale
	private final String NOMEPORTALE = "www.autoprontaconsegna.com";
	private final String URLROOT = "http://www.autoprontaconsegna.com";
	private final String USERNAME = AUTOPRONTACONSEGNA_USERNAME;
	private final String PASSWORD = AUTOPRONTACONSEGNA_PASSWORD;
	private final String HOST = "www.autoprontaconsegna.com";

	private final String COOKIE_DEFAULT_PATH = "/";
	private final String COOKIE_DEFAULT_DOMAIN = "www.autoprontaconsegna.com";

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean modifica = false;
	
	//Messaggi personalizzati per questo portale

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
	Map<String,String> mappaAssociativaInputValore;

	//La scheda immobile su cui si lavora
	SchedaVeicolo scheda;

	//Altre variabili di supporto a livello globale
	//

	//Costruttore
	public _autoprontaconsegnaCom (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

		super(icon, valoreLabel, idPortale, isActive);			

		//La hashTable contenente i valori dei parametri da inviare durante la sessione
		mappaDeiParamerti =  new Hashtable<String,String>();

		//La lista dei parametri (nome-valore) inviati
		postParameters = new ArrayList<NameValuePair>();	

		//La lista degli header (nome-valore) inviati
		requestHeaders = new ArrayList<NameValuePair>();

		//Iniziallizzo la tabella di dipendenza
		mappaAssociativaInputValore = new Hashtable<String,String>();

		//La lista dei cookies inviati
		requestCookies = new ArrayList<BasicClientCookie>();		

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		//In questo portale non si possono inserire veicoli che non siano nuovi o km0
		if(!scheda.tipologiaVeicolo.equals("Veicolo km 0") && !scheda.tipologiaVeicolo.equals("Veicolo nuovo")) {
			messageInserimentoKO(NOMEPORTALE);
			return false;
		}	

		//Inizializzazione scheda
		this.scheda=scheda;

		//Inizializzo gli headers
		inizializzaHeaders(requestHeaders, HOST);
		requestCookies.clear();

		//Connessione 0 - GET della home page - Opzionale
		/*HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT + "/IT/", requestHeaders, null, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/IT/login", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		mappaAssociativaInputValore.put("email",USERNAME);
		mappaAssociativaInputValore.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#login input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/IT/login", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
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
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}


		//Connessione 3 - GET della pagina "Area riservata" - Opzionale
		/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area riservata\"", URLROOT + location, requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 4 - GET della pagina "Inserisci un nuovo annuncio"
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo annuncio\"", URLROOT + "/IT/account/addannunci", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}

		//Alcune select vengono adattate per ottimizzare la compatibilità 
		Document doc = Jsoup.parse(responseBody);
		List<NameValuePair> lista = new LinkedList<NameValuePair>();
		lista.add(new BasicNameValuePair("2/3-Porte", "City car"));
		lista.add(new BasicNameValuePair("Cabrio", "Cabrio"));
		lista.add(new BasicNameValuePair("CoupÃ¨", "Coupè"));
		lista.add(new BasicNameValuePair("Fuoristrada", "SUV/Fuoristrada"));
		lista.add(new BasicNameValuePair("Station Wagon", "Station wagon"));
		lista.add(new BasicNameValuePair("4/5-Porte", "Berlina"));
		lista.add(new BasicNameValuePair("Monovolume", "Monovolume"));
		lista.add(new BasicNameValuePair("Van", "Furgoni/Van"));
		lista.add(new BasicNameValuePair("(Altro)", "Altro"));
		responseBody=adattaSelect(doc, "#carrozzeria", lista).toString();

		List<NameValuePair> lista2 = new LinkedList<NameValuePair>();
		lista2.add(new BasicNameValuePair("Arancione", "Arancione"));
		lista2.add(new BasicNameValuePair("Argento", "Argento"));
		lista2.add(new BasicNameValuePair("Beige", "Beige"));
		lista2.add(new BasicNameValuePair("Bianco", "Bianco"));
		lista2.add(new BasicNameValuePair("Blu", "Blu/Azzurro"));
		lista2.add(new BasicNameValuePair("Bronzo", "Bronzo"));
		lista2.add(new BasicNameValuePair("Giallo", "Giallo"));
		lista2.add(new BasicNameValuePair("Grigio", "Grigio"));
		lista2.add(new BasicNameValuePair("Viola", "Lilla"));
		lista2.add(new BasicNameValuePair("Marrone", "Marrone"));
		lista2.add(new BasicNameValuePair("Nero", "Nero"));
		lista2.add(new BasicNameValuePair("Oro", "Oro"));
		lista2.add(new BasicNameValuePair("Rosso", "Rosso"));
		lista2.add(new BasicNameValuePair("Verde", "Verde"));
		responseBody=adattaSelect(doc, "#colore", lista2).toString();

		List<NameValuePair> lista3 = new LinkedList<NameValuePair>();
		lista3.add(new BasicNameValuePair("Beige", "Beige"));
		lista3.add(new BasicNameValuePair("Grigio", "Grigio"));
		lista3.add(new BasicNameValuePair("Marrone", "Marrone"));
		lista3.add(new BasicNameValuePair("Nero", "Nero"));
		lista3.add(new BasicNameValuePair("", "Altro"));
		responseBody=adattaSelect(doc, "#coloreinterno", lista3).toString();

		List<NameValuePair> lista4 = new LinkedList<NameValuePair>();
		lista4.add(new BasicNameValuePair("Alcantara", "Alcantara"));
		lista4.add(new BasicNameValuePair("Pelle/Tessuto", "Pelle parziale"));
		lista4.add(new BasicNameValuePair("Pelle", "Pelle scamosciata"));
		lista4.add(new BasicNameValuePair("Pelle", "Pelle totale"));
		lista4.add(new BasicNameValuePair("Tessuto", "Stoffa"));
		lista4.add(new BasicNameValuePair("", "Altro"));
		responseBody=adattaSelect(doc, "#tipointerno", lista4).toString();

		List<NameValuePair> lista5 = new LinkedList<NameValuePair>();
		lista5.add(new BasicNameValuePair("Benzina", "Benzina"));
		lista5.add(new BasicNameValuePair("Diesel", "Diesel"));
		lista5.add(new BasicNameValuePair("GPL", "GPL"));
		lista5.add(new BasicNameValuePair("Metano", "Metano"));
		lista5.add(new BasicNameValuePair("Elettrica", "Elettrica/Benzina"));
		lista5.add(new BasicNameValuePair("Elettrica", "Elettrica/Diesel"));
		lista5.add(new BasicNameValuePair("Benzina", "Altro"));
		responseBody=adattaSelect(doc, "#alimentazione", lista5).toString();

		List<NameValuePair> lista6 = new LinkedList<NameValuePair>();
		lista6.add(new BasicNameValuePair("Automatico", "Automatico"));
		lista6.add(new BasicNameValuePair("Manuale", "Manuale"));
		lista6.add(new BasicNameValuePair("Sequenziale", "Semiautomatico"));
		lista6.add(new BasicNameValuePair("Manuale", "Nessuno"));
		lista6.add(new BasicNameValuePair("Manuale", "Seleziona"));
		responseBody=adattaSelect(doc, "#cambio", lista6).toString();


		//Connessione 6 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		mappaAssociativaInputValore.put("alimentazione",scheda.carburanteVeicolo);
		mappaAssociativaInputValore.put("allestimento",scheda.versioneVeicolo);
		mappaAssociativaInputValore.put("cambio",scheda.tipologiaCambioVeicolo);
		mappaAssociativaInputValore.put("carrozzeria",scheda.carrozzeriaVeicolo);	
		if(scheda.cilindrataVeicolo.equals("")) {
			mappaAssociativaInputValore.put("cilindrata","ND");
		}
		else {
			mappaAssociativaInputValore.put("cilindrata",scheda.cilindrataVeicolo);
		}		
		mappaAssociativaInputValore.put("colore",scheda.coloreEsternoVeicolo);
		mappaAssociativaInputValore.put("coloreinterno",scheda.coloreInterniVeicolo);
		mappaAssociativaInputValore.put("euro",scheda.classeEmissioniVeicolo);
		if(scheda.tipologiaVeicolo.equals("Veicolo km 0")) {
			mappaAssociativaInputValore.put("km","Km Zero");
			mappaAssociativaInputValore.put("kmshow","1");
			mappaAssociativaInputValore.put("annoimmatricolazione",scheda.annoImmatricolazioneVeicolo);
		}
		if(scheda.tipologiaVeicolo.equals("Veicolo nuovo")) {
			mappaAssociativaInputValore.put("km","Nuovo");
			mappaAssociativaInputValore.put("kmshow","0");
			mappaAssociativaInputValore.put("annoimmatricolazione","");
		}
		mappaAssociativaInputValore.put("marca",scheda.marcaVeicolo);
		mappaAssociativaInputValore.put("note", scheda.marcaVeicolo + " - " + scheda.modelloVeicolo + " - " + scheda.versioneVeicolo);
		mappaAssociativaInputValore.put("posti",scheda.postiASedereVeicolo);
		mappaAssociativaInputValore.put("prezzo",scheda.prezzoVeicolo);
		if(scheda.coloreMetalizzato) {mappaAssociativaInputValore.put("tipocolore","metallizzato");}else {mappaAssociativaInputValore.put("tipocolore","seleziona");}
		mappaAssociativaInputValore.put("tipointerno",scheda.finitureInterneVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#nuovoannuncio input, #nuovoannuncio select, #nuovoannuncio textarea", mappaAssociativaInputValore, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);	

		//Altri parametri calcolati qui
		String modello = null;
		try {
			modello = getModello(mappaDeiParamerti.get("marca"), scheda.modelloVeicolo);
		} catch (HttpWrongResponseBodyException e1) {
			e1.printStackTrace();
		}
		
		postParameters.remove(new BasicNameValuePair("modello", ""));
		postParameters.add(new BasicNameValuePair("modello", modello));
		if(scheda.disponibilitaABS) {postParameters.add(new BasicNameValuePair("optional_1", "1"));}
		if(scheda.disponibilitaAirBag) {postParameters.add(new BasicNameValuePair("optional_2", "2"));}
		if(scheda.disponibilitaAlzacristalliElettrici) {postParameters.add(new BasicNameValuePair("optional_5", "5"));}
		if(scheda.disponibilitaAntifurto) {postParameters.add(new BasicNameValuePair("optional_6", "6"));}
		if(scheda.disponibilitaRadioOLettoreCD) {postParameters.add(new BasicNameValuePair("optional_7", "7"));}
		if(scheda.disponibilitaCerchiInLega) {postParameters.add(new BasicNameValuePair("optional_9", "9"));}
		if(scheda.disponibilitaChiusuraCentralizzata) {postParameters.add(new BasicNameValuePair("optional_10", "10"));}
		if(scheda.disponibilitaClima) {postParameters.add(new BasicNameValuePair("optional_11", "11"));}
		if(scheda.disponibilitaContrlAutomTrazione) {postParameters.add(new BasicNameValuePair("optional_13", "13"));}
		if(scheda.disponibilitaESP) {postParameters.add(new BasicNameValuePair("optional_16", "16"));}
		if(scheda.disponibilitaGancioTraino) {postParameters.add(new BasicNameValuePair("optional_20", "20"));}
		if(scheda.disponibilitaAllestimentoHandicap) {postParameters.add(new BasicNameValuePair("optional_21", "21"));}
		if(scheda.disponibilitaImmobilizer) {postParameters.add(new BasicNameValuePair("optional_22", "22"));}
		if(scheda.disponibilitaParkDistControl) {postParameters.add(new BasicNameValuePair("optional_25", "25"));}
		if(scheda.disponibilitaPortaPacchi) {postParameters.add(new BasicNameValuePair("optional_26", "26"));}
		if(scheda.disponibilitaSediliRiscaldati) {postParameters.add(new BasicNameValuePair("optional_28", "28"));}
		if(scheda.disponibilitaServoSterzo) {postParameters.add(new BasicNameValuePair("optional_29", "29"));}
		if(scheda.disponibilitaNavigatoreSatellitare) {postParameters.add(new BasicNameValuePair("optional_30", "30"));}		

		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", URLROOT + "/IT/account/addannunci", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("id_newinserzione=")) {
					int start = location.indexOf("newinserzione=")+14;
					int end = location.length();
					codiceInserzione = location.substring(start, end);
					inserimentoOK = true;
				}
				else {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Location non corretta"));
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}

		/*Date date = new Date();
		long param1 = date.getTime();
		int param2 = 12345;
		for(int i=1; i<=8; i++) {
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_9 = new HttpPortalPostConnection();        
				try {

					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					FileBody bin = new FileBody(scheda.arrayImages[i]);
					reqEntity.addPart("Filedata", bin );
					reqEntity.addPart("Filename", new StringBody(bin.getFilename()) );
					reqEntity.addPart("Upload", new StringBody("Submit Query") );

					Object[] response = connessione_9.post("Connessione 9 - Invio delle foto", URLROOT + "/rivenditori/upload.php?salva=" + param2 + param1, reqEntity, requestHeaders, requestCookies, DEBUG_MODE);			

					//Controllo il response status
					BasicStatusLine responseStatus = (BasicStatusLine) response[2];
					if( (responseStatus.getStatusCode()!=200)) {
						throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
					}    	

				} catch (IOException | RuntimeException e) {
					throw new HttpCommunicationException(e);
				}
				finally {
					mappaAssociativaInputValore.clear();
					mappaDeiParamerti.clear();
					postParameters.clear();
				}
			}
		}*/

		//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
		if(inserimentoOK) {

			//Aggiorna la lista dei portali in cui è inserita la scheda
			scheda.aggiungiInserimentoPortale(idPortale, codiceInserzione);

			//Aggiorna i pulsanti del pannello inserimento
			PanelSicronizzazioneConPortali.updatePanello(scheda, false);

			//Mail e messaggio informativo OK
			if(!modifica) {
				sendConfirmationMail(scheda, NOMEPORTALE, scheda.codiceScheda);
				messageInserimentoOK(NOMEPORTALE);
			}	
			else {
				messageModificaOK(NOMEPORTALE);
			}

		}
		else {

			if(!modifica) {
				messageInserimentoKO(NOMEPORTALE);
			}
			else {
				messageModificaKO(NOMEPORTALE);
			}

		}

		return inserimentoOK;

	}


	private String getModello(String marca, String modello) throws HttpCommunicationException, HttpWrongResponseBodyException {

		String ajaxResponse = null;

		//Connessione 4b - GET per ottenere il modello veicolo
		HttpPortalGetConnection connessione_4b = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4b.get("Connessione 4b -  GET per ottenere il modello veicolo", URLROOT + "/IT/ajaxannuncio?marca=" + marca + "&apc=1", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
				
				ajaxResponse = (String)response[1];

				org.jsoup.nodes.Document doc = Jsoup.parse(ajaxResponse);              
				Elements optionElements = doc.getElementsByTag("option");
				if(optionElements.isEmpty()) {
					throw(new HttpWrongResponseBodyException("Non ho trovato tag di tipo \"option\""));
				}
				else {
					Iterator<Element> iterator = optionElements.iterator();
					double resultComparation = 0.66; //messo un limite alto
					while(iterator.hasNext()) {
						Element currentElement = iterator.next();
						List<char[]> charListPortale = bigram(currentElement.text());
						List<char[]> charListImagination = bigram(modello);
						double actualResultComparation = dice(charListPortale, charListImagination);
						if(actualResultComparation>=resultComparation) {
							resultComparation = actualResultComparation;
							return currentElement.attr("value");            		
						}       		
					}
					return "9999"; //se non trova una valore con similarità alta torna "Altro"
				}
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}

	}


	//Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaVeicolo scheda) {

		System.out.println("Visualizzazione scheda: " + scheda.codiceScheda + "...");

		codiceInserzione = scheda.getCodiceInserimento(idPortale);
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT;
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e ) {
			//
		}

		return true;
	}


	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		//La scheda è da aggiornare
		if(J2Web_UI.protoScheda!=null) {
			modifica = true;
		}

		//Inizializzazione scheda
		this.scheda=scheda;

		//Ottengo il codice con cui è stata inserita la scheda
		codiceInserzione = scheda.getCodiceInserimento(idPortale);
		
		//Inizializzo gli headers
		inizializzaHeaders(requestHeaders, HOST);
		requestCookies.clear();

		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/IT/login", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		mappaAssociativaInputValore.put("email",USERNAME);
		mappaAssociativaInputValore.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#login input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/IT/login", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
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
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}


		//Connessione 3 - GET della pagina "Area annunci"
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area annunci\"", URLROOT + "/IT/account/annunci", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 4 - POST di eliminazione annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		mappaAssociativaInputValore.put("abilita_" + codiceInserzione ,"1");
		mappaAssociativaInputValore.put("deleteAnnuncio",codiceInserzione);
		//mappaAssociativaInputValore.put("subform","1");

		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#visibile input", mappaAssociativaInputValore, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);	

		HttpPortalPostConnection connessione_4 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_4.post("Connessione 4 - POST di eliminazione annuncio", URLROOT + "/IT/account/annunci", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if((responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));			
			}  	
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}

		//Aggiorno la lista dei portali in cui è presenta la scheda corrente
		scheda.eliminaInserimentoPortale(idPortale);			

		//Aggiorno i pulsanti del pannello inserimento
		PanelSicronizzazioneConPortali.updatePanello(scheda, false);

		System.out.println("Eliminata da: " + NOMEPORTALE);

		//Stampo a video un messaggio informativo
		if(!modifica) {
			messageEliminazioneOK(NOMEPORTALE);
		}

		return true;

	}

}