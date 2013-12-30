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
public class _cuboAutoIt extends PortaleWeb {     

	//Variabili portale
	private final String NOMEPORTALE = "www.cuboauto.it";
	private final String URLROOT = "http://www.cuboauto.it";
	private final String USERNAME = "topik123";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.cuboauto.it";

	private final String SESSIONCOOKIENAME = "PHPSESSID";
	private final String SESSIONCOOKIEDOMAIN = "www.cuboauto.it";
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

		//La lista dei cookies inviati
		requestCookies = new ArrayList<BasicClientCookie>();

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		System.out.println("Inserimento scheda: " + scheda.codiceScheda + "...");

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
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT + "/index.php", requestHeaders, null, debugMode);
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/login.php", requestHeaders, null, debugMode);
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
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/_login.php", postParameters, requestHeaders, null, debugMode);			

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


		//Connessione 3 - GET della pagina "Area concessionario" - Opzionale
		/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area concessionario\"", URLROOT + "/" + location, requestHeaders, requestCookies, debugMode);
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
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo annuncio\"", URLROOT + "/concessionari/inserisci-annuncio.php", requestHeaders, requestCookies, debugMode);
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
			Object[] response = connessione_5.get("Connessione 5 - GET della pagina \"Inserisci un nuovo annuncio (con parametro circa la marca veicolo)\"", URLROOT + "/concessionari/inserisci-annuncio.php?idMarca=" + mappaDeiParamerti.get("idMarca"), requestHeaders, requestCookies, debugMode);
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
		if(scheda.annoImmatricolazioneVeicolo.equals("Anno")) {
			tabellaDiDipendenza.put("annoimmatricolazione","vuoto");
		}
		else {
			tabellaDiDipendenza.put("annoimmatricolazione",scheda.annoImmatricolazioneVeicolo);
		}
		tabellaDiDipendenza.put("annoprossimarevisione","vuoto");
		tabellaDiDipendenza.put("cambio",scheda.tipologiaCambioVeicolo);
		tabellaDiDipendenza.put("chilometri",scheda.chilometraggioVeicolo);
		tabellaDiDipendenza.put("cilindrata",scheda.cilindrataVeicolo);
		tabellaDiDipendenza.put("cilindri","vuoto");
		tabellaDiDipendenza.put("codice",(scheda.marcaVeicolo+"-"+scheda.modelloVeicolo+"-"+scheda.coloreEsternoVeicolo+"-"+scheda.tipologiaContrattoVeicolo).replace(" " , ""));
		tabellaDiDipendenza.put("coloredett","");
		tabellaDiDipendenza.put("coloreesterno",scheda.coloreEsternoVeicolo);
		if(scheda.coloreMetalizzato){tabellaDiDipendenza.put("metallizzato","1");}	
		if(scheda.coloreInterniVeicolo.equals("Seleziona")) {
			tabellaDiDipendenza.put("coloreinterni","vuoto");
		}
		else {
			tabellaDiDipendenza.put("coloreinterni",scheda.coloreInterniVeicolo);
		}	
		tabellaDiDipendenza.put("contratto",scheda.tipologiaContrattoVeicolo);
		tabellaDiDipendenza.put("descrizione",scheda.descrizioneVeicolo);
		tabellaDiDipendenza.put("idAlimentazione",scheda.carburanteVeicolo);
		tabellaDiDipendenza.put("idCarrozzeria",scheda.carrozzeriaVeicolo);
		tabellaDiDipendenza.put("idMarca",var_idMarca);
		tabellaDiDipendenza.put("idMarca2",var_idMarca);
		tabellaDiDipendenza.put("idModello",scheda.modelloVeicolo);
		tabellaDiDipendenza.put("idTipologia",scheda.tipologiaVeicolo);
		if(scheda.meseImmatricolazioneVeicolo.equals("Mese")) {
			tabellaDiDipendenza.put("meseimmatricolazione","vuoto");
		}
		else {
			tabellaDiDipendenza.put("meseimmatricolazione","0"+scheda.meseImmatricolazioneVeicoloIndex);
		}
		tabellaDiDipendenza.put("meseprossimarevisione","vuoto");

		if(scheda.classeEmissioniVeicolo.equals("Seleziona")) {
			tabellaDiDipendenza.put("normativa","vuoto");
		}
		else {
			tabellaDiDipendenza.put("normativa",scheda.classeEmissioniVeicolo);
		}
		tabellaDiDipendenza.put("peso","");
		tabellaDiDipendenza.put("porte","");
		tabellaDiDipendenza.put("prezzo",scheda.prezzoVeicolo);
		tabellaDiDipendenza.put("provimmatricolazione","vuoto");
		if(scheda.numeroRapportiVeicolo.equals("Seleziona")) {
			tabellaDiDipendenza.put("rapporti","vuoto");
		}
		else {
			tabellaDiDipendenza.put("rapporti",scheda.numeroRapportiVeicolo);
		}	
		if(scheda.postiASedereVeicolo.equals("Seleziona")) {
			tabellaDiDipendenza.put("sedili","vuoto");
		}
		else {
			tabellaDiDipendenza.put("sedili",scheda.postiASedereVeicolo);
		}
		tabellaDiDipendenza.put("versione",scheda.versioneVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#centrale form input, #centrale form select, #centrale form textarea", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);

		if(mappaDeiParamerti.get("idModello").equals("")) {
			messageInserimentoKO(NOMEPORTALE);
			return false;
		}

		//Aggiungo qui questi parametri perchè se li aggiungessi nella tabellaDiDipendenza si sovrascriverebbero (hanno lo stesso nome)
		if(scheda.disponibilitaABS){ postParameters.add(new BasicNameValuePair("sicurezza[]", "1")); }
		if(scheda.disponibilitaAirBag){ postParameters.add(new BasicNameValuePair("sicurezza[]", "2")); postParameters.add(new BasicNameValuePair("sicurezza[]", "4"));}
		if(scheda.disponibilitaAntifurto){ postParameters.add(new BasicNameValuePair("sicurezza[]", "128")); }
		if(scheda.disponibilitaChiusuraCentralizzata){ postParameters.add(new BasicNameValuePair("comfort[]", "64")); }
		if(scheda.disponibilitaContrlAutomTrazione){ postParameters.add(new BasicNameValuePair("sicurezza[]", "512")); }
		if(scheda.disponibilitaESP){ postParameters.add(new BasicNameValuePair("sicurezza[]", "2048")); }
		if(scheda.disponibilitaImmobilizer){ postParameters.add(new BasicNameValuePair("sicurezza[]", "256")); }
		if(scheda.disponibilitaAlzacristalliElettrici){ postParameters.add(new BasicNameValuePair("comfort[]", "4")); }
		if(scheda.disponibilitaClima){ postParameters.add(new BasicNameValuePair("comfort[]", "1")); }
		if(scheda.disponibilitaRadioOLettoreCD){ postParameters.add(new BasicNameValuePair("audio[]", "8")); postParameters.add(new BasicNameValuePair("audio[]", "2")); }
		if(scheda.disponibilitaParkDistControl){ postParameters.add(new BasicNameValuePair("comfort[]", "8192")); }
		if(scheda.disponibilitaSediliRiscaldati){ postParameters.add(new BasicNameValuePair("comfort[]", "32")); }
		if(scheda.disponibilitaServoSterzo){ postParameters.add(new BasicNameValuePair("comfort[]", "128")); }
		if(scheda.disponibilitaVolanteMultifunzione){ postParameters.add(new BasicNameValuePair("audio[]", "128")); }
		if(scheda.disponibilitaCerchiInLega){ postParameters.add(new BasicNameValuePair("linea[]", "2")); }
		if(scheda.disponibilitaGancioTraino){ postParameters.add(new BasicNameValuePair("varie[]", "16")); }
		if(scheda.disponibilitaPortaPacchi){ postParameters.add(new BasicNameValuePair("varie[]", "8")); }

		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", URLROOT + "/concessionari/_inserisci-annuncio.php", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];

				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("?id=")) {
					int start = location.indexOf("?id=")+4;
					int end = location.length();
					codiceInserzione = location.substring(start, end);

					//L'inserzione è assicurata
					inserimentoOK = true;
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
		/*HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
		try {
			Object[] response =  connessione_7.get("Connessione 7 - GET della pagina \"Dettaglio annuncio\"", URLROOT + "/" + location, requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 8 - GET della pagina "Inserisci una nuova foto" - Opzionale
		HttpPortalGetConnection connessione_8 = new HttpPortalGetConnection();
		try {
			Object[] response =  connessione_8.get("Connessione 8 - GET della pagina \"Inserisci una nuova foto\"", URLROOT + "/concessionari/foto.php?id=" + codiceInserzione, requestHeaders, requestCookies, debugMode);
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

					Object[] response = connessione_9.post("Connessione 9 - Invio delle foto", URLROOT + "/concessionari/_foto.php", reqEntity, requestHeaders, requestCookies, debugMode);			

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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/login.php", requestHeaders, null, debugMode);
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
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/_login.php", postParameters, requestHeaders, null, debugMode);			

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


		//Connessione 3 - GET della pagina di eliminazione annuncio
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di eliminazione annuncio", URLROOT + "/concessionari/_del_annunci.php?id=" + codiceInserzione, requestHeaders, requestCookies, debugMode);
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
			//JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
			messageEliminazioneOK(NOMEPORTALE);
		}

		return true;

	}


}