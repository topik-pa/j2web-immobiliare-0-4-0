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
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
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
	private final String USERNAME = "HeadyElf@mailinator.com";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.autoprontaconsegna.com";

	private final String SESSIONCOOKIENAME = "PHPSESSID";
	private final String SESSIONCOOKIEDOMAIN = "www.autoprontaconsegna.com";
	private final String SESSIONCOOKIEHEADER = "";
	private final String SESSIONCOOKIEVALUE = "";

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

	//Lista dei cookies inviati in una singola connessione
	List<BasicClientCookie> requestCookies;

	//Mappa che rappresenta la tabella di dipendennza dei parametri da inviare
	Map<String,String> tabellaDiDipendenza;

	//La scheda immobile su cui si lavora
	SchedaVeicolo scheda;

	//Altre variabili di supporto a livello globale
	//String var_idMarca;

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
		tabellaDiDipendenza = new Hashtable<String,String>();

		//La lista dei cookies inviati
		requestCookies = new ArrayList<BasicClientCookie>();		

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		System.out.println("Inserimento scheda: " + scheda.codiceScheda + "...");

		if(!scheda.tipologiaVeicolo.equals("Veicolo km 0") && !scheda.tipologiaVeicolo.equals("Veicolo nuovo")) {
			messageInserimentoKO(NOMEPORTALE);
			return false;
		}	

		//Inizializzazione scheda
		this.scheda=scheda;

		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		requestHeaders.clear();
		requestHeaders.add(new BasicNameValuePair("Host", HOST));
		requestHeaders.add(new BasicNameValuePair("User-Agent", USER_AGENT_VALUE));	
		requestHeaders.add(new BasicNameValuePair("Connection", CONNECTION));
		requestHeaders.add(new BasicNameValuePair("Cache-Control", CACHE_CONTROL));
		requestHeaders.add(new BasicNameValuePair("Accept-Language", ACCEPT_LANGUAGE));
		requestHeaders.add(new BasicNameValuePair("Accept", ACCEPT));

		//Connessione 0 - GET della home page - Opzionale
		/*HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT + "/IT/", requestHeaders, null, debugMode);
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/IT/login", requestHeaders, null, debugMode);
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
		tabellaDiDipendenza.put("email",USERNAME);
		tabellaDiDipendenza.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#login input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/IT/login", postParameters, requestHeaders, null, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
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


		//Connessione 3 - GET della pagina "Area riservata" - Opzionale
		/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area riservata\"", URLROOT + location, requestHeaders, requestCookies, debugMode);
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
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo annuncio\"", URLROOT + "/IT/account/addannunci", requestHeaders, requestCookies, debugMode);
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
		tabellaDiDipendenza.put("alimentazione",scheda.carburanteVeicolo);
		tabellaDiDipendenza.put("allestimento",scheda.versioneVeicolo);
		tabellaDiDipendenza.put("cambio",scheda.tipologiaCambioVeicolo);
		tabellaDiDipendenza.put("carrozzeria",scheda.carrozzeriaVeicolo);
		tabellaDiDipendenza.put("cilindrata",scheda.cilindrataVeicolo);
		tabellaDiDipendenza.put("colore",scheda.coloreEsternoVeicolo);
		tabellaDiDipendenza.put("coloreinterno",scheda.coloreInterniVeicolo);
		tabellaDiDipendenza.put("euro",scheda.classeEmissioniVeicolo);
		if(scheda.tipologiaVeicolo.equals("Veicolo km 0")) {
			tabellaDiDipendenza.put("km","Km Zero");
			tabellaDiDipendenza.put("kmshow","1");
			tabellaDiDipendenza.put("annoimmatricolazione",scheda.annoImmatricolazioneVeicolo);
		}
		if(scheda.tipologiaVeicolo.equals("Veicolo nuovo")) {
			tabellaDiDipendenza.put("km","Nuovo");
			tabellaDiDipendenza.put("kmshow","0");
			tabellaDiDipendenza.put("annoimmatricolazione","");
		}
		tabellaDiDipendenza.put("marca",scheda.marcaVeicolo);
		tabellaDiDipendenza.put("note", scheda.marcaVeicolo + " - " + scheda.modelloVeicolo + " - " + scheda.versioneVeicolo);
		tabellaDiDipendenza.put("porte","");
		tabellaDiDipendenza.put("posti",scheda.postiASedereVeicolo);
		tabellaDiDipendenza.put("prezzo",scheda.prezzoVeicolo);
		tabellaDiDipendenza.put("prezzolistino","");
		if(scheda.coloreMetalizzato) {
			tabellaDiDipendenza.put("tipocolore","metallizzato");
		}
		else {
			tabellaDiDipendenza.put("tipocolore","seleziona");
		}
		tabellaDiDipendenza.put("tipointerno",scheda.finitureInterneVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#nuovoannuncio input, #nuovoannuncio select, #nuovoannuncio textarea", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);	

		//Altri parametri calcolati qui
		String modello = null;
		try {
			modello = getModello(mappaDeiParamerti.get("marca"), scheda.modelloVeicolo);
		} catch (HttpWrongResponseBodyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		if(scheda.disponibilitaNavigatoreSattelitare) {postParameters.add(new BasicNameValuePair("optional_30", "30"));}		

		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", URLROOT + "/IT/account/addannunci", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
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
			postParameters.clear();
			mappaDeiParamerti.clear();
			tabellaDiDipendenza.clear();
		}

		/*

		Date date = new Date();
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

					Object[] response = connessione_9.post("Connessione 9 - Invio delle foto", URLROOT + "/rivenditori/upload.php?salva=" + param2 + param1, reqEntity, requestHeaders, requestCookies, debugMode);			

					//Controllo il response status
					BasicStatusLine responseStatus = (BasicStatusLine) response[2];
					if( (responseStatus.getStatusCode()!=200)) {
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
		}


		 */

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
				//JOptionPane.showMessageDialog(null, "Scheda immobile inserita in: " + NOMEPORTALE, "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);
				messageInserimentoOK(NOMEPORTALE);
			}

			return inserimentoOK;        	
		}
		else {

			if(!isSequential) {
				//Stampo a video un messaggio informativo
				messageInserimentoKO(NOMEPORTALE);
			}

			return inserimentoOK;
		}

	}


	private String getModello(String marca, String modello) throws HttpCommunicationException, HttpWrongResponseBodyException {

		String ajaxResponse = null;

		//Connessione 4b - GET per ottenere il modello veicolo
		HttpPortalGetConnection connessione_4b = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4b.get("Connessione 4b -  GET per ottenere il modello veicolo", URLROOT + "/IT/ajaxannuncio?marca=" + marca + "&apc=1", requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/IT/login", requestHeaders, null, debugMode);
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
		tabellaDiDipendenza.put("email",USERNAME);
		tabellaDiDipendenza.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#login input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/IT/login", postParameters, requestHeaders, null, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				connessione_2.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
				setCookies(responseHeaders, requestCookies);
				//Trovo la location
				//location = getHeaderValueByName(responseHeaders, "Location");
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


		//Connessione 3 - GET della pagina "Area annunci"
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area annunci\"", URLROOT + "/IT/account/annunci", requestHeaders, requestCookies, debugMode);
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


		//Connessione 4 - POST di eliminazione annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("abilita_" + codiceInserzione ,"1");
		tabellaDiDipendenza.put("deleteAnnuncio",codiceInserzione);
		tabellaDiDipendenza.put("subform","1");

		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#visibile input", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);	

		HttpPortalPostConnection connessione_4 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_4.post("Connessione 4 - POST di eliminazione annuncio", URLROOT + "/IT/account/annunci", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if((responseStatus.getStatusCode()!=200)) {
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

		//Aggiorno la lista dei portali in cui è presenta la scheda corrente
		scheda.eliminaInserimentoPortale(idPortale);			

		if(!isSequential) {
			//Aggiorno i pulsanti del pannello inserimento
			PanelSicronizzazioneConPortali.updatePanello(scheda, false);

			System.out.println("Eliminata da: " + NOMEPORTALE);

			//Stampo a video un messaggio informativo
			messageEliminazioneOK(NOMEPORTALE);
		}

		return true;

	}
	

}