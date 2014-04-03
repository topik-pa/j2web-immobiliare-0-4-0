/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */ 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
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
public class _vendiautoCom extends PortaleWeb {     

	//Variabili portale
	private final String NOMEPORTALE = "www.vendiauto.com";
	private final String URLROOT = "http://www.vendiauto.com";
	private final String USERNAME = "c3339097@drdrb.com";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.vendiauto.com";

	private final String SESSIONCOOKIENAME = "PHPSESSID";
	private final String SESSIONCOOKIEDOMAIN = "www.vendiauto.com";
	private final String SESSIONCOOKIEHEADER = "";
	private final String SESSIONCOOKIEVALUE = "";

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	//private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean modifica = false;
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
	public _vendiautoCom (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

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

		if(modifica) {			
			System.out.println("(MLS) Modifica scheda: " + scheda.codiceScheda + "...");
		}
		else  {
			System.out.println("(MLS) Inserimento scheda: " + scheda.codiceScheda + "...");	
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
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT, requestHeaders, null, debugMode);
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/rivenditori/accedi.php", requestHeaders, null, debugMode);
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
		tabellaDiDipendenza.put("Submit","***site***");
		tabellaDiDipendenza.put("email",USERNAME);
		tabellaDiDipendenza.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#boxint input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/rivenditori/accedi.php", postParameters, requestHeaders, null, debugMode);			

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


		//Connessione 3 - GET della pagina "Area concessionario" - Opzionale
		/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area concessionario\"", URLROOT + "/rivenditori/" + location, requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 4 - GET della pagina "Inserisci un nuovo annuncio" - Opzionale
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo annuncio\"", URLROOT + "/rivenditori/addnew.php", requestHeaders, requestCookies, debugMode);
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
		lista.add(new BasicNameValuePair("Benzina", "Benzina"));
		lista.add(new BasicNameValuePair("Diesel", "Diesel"));
		lista.add(new BasicNameValuePair("Benzina+GPL", "GPL"));
		lista.add(new BasicNameValuePair("Benzina+Metano", "Metano"));
		lista.add(new BasicNameValuePair("Benzina", "Elettrica/Benzina"));
		lista.add(new BasicNameValuePair("Diesel", "Elettrica/Diesel"));
		lista.add(new BasicNameValuePair("Selezionare...", "Altro"));
		responseBody=adattaSelect(doc, "#select", lista).toString();

		List<NameValuePair> lista2 = new LinkedList<NameValuePair>();
		lista2.add(new BasicNameValuePair("Epoca", "Veicolo d'epoca"));
		lista2.add(new BasicNameValuePair("Km 0", "Veicolo km 0"));
		lista2.add(new BasicNameValuePair("Nuovo", "Veicolo nuovo"));
		lista2.add(new BasicNameValuePair("Usato", "Veicolo usato"));
		lista2.add(new BasicNameValuePair("Aziendale", "Veicolo aziendale"));
		responseBody=adattaSelect(doc, "#spryselect3 select", lista2).toString();

		List<NameValuePair> lista3 = new LinkedList<NameValuePair>();
		lista3.add(new BasicNameValuePair("Automatico", "Automatico"));
		lista3.add(new BasicNameValuePair("Manuale", "Manuale"));
		lista3.add(new BasicNameValuePair("", "Semiautomatico"));
		lista3.add(new BasicNameValuePair("", "Nessuno"));
		responseBody=adattaSelect(doc, "select[name=cambio]", lista3).toString();

		//Connessione 6 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("alimentaz",scheda.carburanteVeicolo);
		tabellaDiDipendenza.put("allestimento",scheda.versioneVeicolo);
		tabellaDiDipendenza.put("anno",scheda.annoImmatricolazioneVeicolo);
		tabellaDiDipendenza.put("cambio",scheda.tipologiaCambioVeicolo);
		tabellaDiDipendenza.put("cilindrata",scheda.cilindrataVeicolo);
		tabellaDiDipendenza.put("colore",scheda.coloreEsternoVeicolo);
		tabellaDiDipendenza.put("garanzia","seleziona...");
		tabellaDiDipendenza.put("input","Salva");
		tabellaDiDipendenza.put("km",scheda.chilometraggioVeicolo);
		tabellaDiDipendenza.put("kw",scheda.KWVeicolo);
		tabellaDiDipendenza.put("mese",scheda.meseImmatricolazioneVeicolo);
		tabellaDiDipendenza.put("send_button","    Salva    ");
		tabellaDiDipendenza.put("tipo",scheda.tipologiaVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#form0 input, #form0 select, #form0 textarea", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);	

		//Altri parametri calcolati qui
		postParameters.add(new BasicNameValuePair("prezzo", scheda.prezzoVeicolo));
		postParameters.add(new BasicNameValuePair("descrizione", scheda.descrizioneVeicolo));
		postParameters.add(new BasicNameValuePair("condiz", "Ottime"));
		postParameters.add(new BasicNameValuePair("convalida", "180"));
		postParameters.add(new BasicNameValuePair("cod_modello", scheda.modelloVeicolo));
		postParameters.add(new BasicNameValuePair("cod_marca", scheda.marcaVeicolo));

		if(scheda.disponibilitaABS) {postParameters.add(new BasicNameValuePair("optional[12]", "ABS"));}
		if(scheda.disponibilitaABS) {postParameters.add(new BasicNameValuePair("optional[12]", "ABS"));}
		if(scheda.disponibilitaAirBag) {postParameters.add(new BasicNameValuePair("optional[13]", "Airbag"));}
		if(scheda.disponibilitaAntifurto) {postParameters.add(new BasicNameValuePair("optional[18]", "Antifurto"));}
		if(scheda.disponibilitaChiusuraCentralizzata) {postParameters.add(new BasicNameValuePair("optional[17]", "Chiusura centralizzata"));}
		if(scheda.disponibilitaESP) {postParameters.add(new BasicNameValuePair("optional[21]", "ESP"));}
		if(scheda.disponibilitaImmobilizer) {postParameters.add(new BasicNameValuePair("optional[19]", "Immobilizzatore elettronico"));}
		if(scheda.disponibilitaAlzacristalliElettrici) {postParameters.add(new BasicNameValuePair("optional[4]", "Alzacristalli elettrici"));}
		if(scheda.disponibilitaClima) {postParameters.add(new BasicNameValuePair("optional[1]", "Climatizzatore"));}
		if(scheda.disponibilitaNavigatoreSatellitare) {postParameters.add(new BasicNameValuePair("optional[5]", "Sistema di navigazione"));}
		if(scheda.disponibilitaRadioOLettoreCD) {postParameters.add(new BasicNameValuePair("optional[10]", "Autoradio"));}
		if(scheda.disponibilitaParkDistControl) {postParameters.add(new BasicNameValuePair("optional[31]", "Sensori di Parcheggio"));}
		if(scheda.disponibilitaSediliRiscaldati) {postParameters.add(new BasicNameValuePair("optional[8]", "Sedili riscaldati"));}
		if(scheda.disponibilitaServoSterzo) {postParameters.add(new BasicNameValuePair("optional[27]", "Servosterzo"));}
		if(scheda.disponibilitaVolanteMultifunzione) {postParameters.add(new BasicNameValuePair("optional[43]", "Comandi al volante"));}
		if(scheda.disponibilitaAllestimentoHandicap) {postParameters.add(new BasicNameValuePair("optional[11]", "Adatta a portatori di handicap"));}
		if(scheda.disponibilitaCerchiInLega) {postParameters.add(new BasicNameValuePair("optional[24]", "Cerchi in lega"));}
		if(scheda.disponibilitaGancioTraino) {postParameters.add(new BasicNameValuePair("optional[29]", "Gancio traino"));}
		if(scheda.disponibilitaPortaPacchi) {postParameters.add(new BasicNameValuePair("optional[25]", "Portapacchi"));}

		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", URLROOT + "/rivenditori/inviodati.php", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				responseBody = (String)response[1];	
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


		/*Date date = new Date();
		long param1 = date.getTime();
		int param2 = 13936;
		for(int i=1; i<=8; i++) {
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_9 = new HttpPortalPostConnection();        
				try {

					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					FileBody bin = new FileBody(scheda.arrayImages[i]);
					reqEntity.addPart("Filedata", bin );
					reqEntity.addPart("Filename", new StringBody(bin.getFilename()) );
					reqEntity.addPart("Upload", new StringBody("Submit Query") );

					Object[] response = connessione_9.post("Connessione 9 - Invio delle foto " + "-immagine " + i + "-", URLROOT + "/rivenditori/upload.php?salva=" + param2 + "-" + param1, reqEntity, requestHeaders, requestCookies, debugMode);			

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
		}*/


		//Connessione 5 - GET della pagina "Admin" per ottenere il codice inserzione
		HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_5.get("Connessione 5 - GET della pagina Admin", URLROOT + "/rivenditori/admin.php", requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];

				Document dom = Jsoup.parse(responseBody);
				Elements tr = ((Element) dom).select("#dati tbody tr");
				Element firstTr = tr.get(1);
				codiceInserzione = ((Element) firstTr).select("td").first().text();

				inserimentoOK = true;
			}
		} catch (IOException | RuntimeException e) {
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

				//Mail e messaggio informativo OK
				if(!modifica) {
					sendConfirmationMail(scheda, NOMEPORTALE, scheda.codiceScheda);

					messageInserimentoOK(NOMEPORTALE);
				}	
				else {
					messageModificaOK(NOMEPORTALE);
				}
				
			}

			return inserimentoOK;        	
		}
		else {

			if(!modifica) {
				messageInserimentoKO(NOMEPORTALE);
			}
			else {
				messageModificaKO(NOMEPORTALE);
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
		
		//La scheda è da aggiornare
		if(scheda.isOnThisPortal(idPortale)) {
			modifica = true;
		}		

		System.out.println("Eliminazione scheda: " + scheda.codiceScheda + "...");

		codiceInserzione = scheda.getCodiceInserimento(idPortale);

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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/rivenditori/accedi.php", requestHeaders, null, debugMode);
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
		tabellaDiDipendenza.put("Submit","***site***");
		tabellaDiDipendenza.put("email",USERNAME);
		tabellaDiDipendenza.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#boxint input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/rivenditori/accedi.php", postParameters, requestHeaders, null, debugMode);			

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


		//Connessione 3 - POST di eliminazione annuncio		
		postParameters.add(new BasicNameValuePair("button", "Cancella Record"));
		postParameters.add(new BasicNameValuePair("cancella", codiceInserzione));
		HttpPortalPostConnection connessione_3 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_3.post("Connessione 3 - POST di eliminazione annuncio", URLROOT + "/rivenditori/delete.php?Id=" + codiceInserzione, postParameters, requestHeaders, requestCookies, debugMode);			

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

		//Aggiorno la lista dei portali in cui è presenta la scheda corrente
		scheda.eliminaInserimentoPortale(idPortale);			

		if(!isSequential) {
			//Aggiorno i pulsanti del pannello inserimento
			PanelSicronizzazioneConPortali.updatePanello(scheda, false);

			System.out.println("Eliminata da: " + NOMEPORTALE);

			//Stampo a video un messaggio informativo
			if(!modifica) {
				messageEliminazioneOK(NOMEPORTALE);
			}
		}

		return true;

	}


	//Metodi di supporto


}