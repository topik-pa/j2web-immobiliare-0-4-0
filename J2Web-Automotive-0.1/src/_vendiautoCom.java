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
	private final String USERNAME = VENDIAUTO_USERNAME;
	private final String PASSWORD = VENDIAUTO_PASSWORD;
	private final String HOST = "www.vendiauto.com";

	private final String COOKIE_DEFAULT_PATH = "/";
	private final String COOKIE_DEFAULT_DOMAIN = ".vendiauto.com";

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	//private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean modifica = false;

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
		mappaAssociativaInputValore = new Hashtable<String,String>();

		//La lista dei cookies inviati
		requestCookies = new ArrayList<BasicClientCookie>();		

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		//Inizializzazione scheda
		this.scheda=scheda;

		//Inizializzo gli headers
		inizializzaHeaders(requestHeaders, HOST);

		//Connessione 0 - GET della home page - Opzionale
		/*HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT, requestHeaders, null, DEBUG_MODE);
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/rivenditori/accedi.php", requestHeaders, null, DEBUG_MODE);
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
		//mappaAssociativaInputValore.put("Submit","***site***");
		mappaAssociativaInputValore.put("email",USERNAME);
		mappaAssociativaInputValore.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#boxint input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/rivenditori/accedi.php", postParameters, requestHeaders, null, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
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


		//Connessione 3 - GET della pagina "Area concessionario" - Opzionale
		/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area concessionario\"", URLROOT + "/rivenditori/" + location, requestHeaders, requestCookies, DEBUG_MODE);
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
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo annuncio\"", URLROOT + "/rivenditori/addnew.php", requestHeaders, requestCookies, DEBUG_MODE);
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

		//Adatto le select
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
		mappaAssociativaInputValore.put("alimentaz",scheda.carburanteVeicolo);
		mappaAssociativaInputValore.put("allestimento",scheda.versioneVeicolo);
		mappaAssociativaInputValore.put("anno",scheda.annoImmatricolazioneVeicolo);
		mappaAssociativaInputValore.put("cambio",scheda.tipologiaCambioVeicolo);
		mappaAssociativaInputValore.put("cilindrata",scheda.cilindrataVeicolo);
		mappaAssociativaInputValore.put("colore",scheda.coloreEsternoVeicolo);
		mappaAssociativaInputValore.put("garanzia","seleziona...");
		mappaAssociativaInputValore.put("input","Salva");
		mappaAssociativaInputValore.put("km",scheda.chilometraggioVeicolo);
		mappaAssociativaInputValore.put("kw",scheda.KWVeicolo);
		mappaAssociativaInputValore.put("mese",scheda.meseImmatricolazioneVeicolo);
		mappaAssociativaInputValore.put("send_button","    Salva    ");
		mappaAssociativaInputValore.put("tipo",scheda.tipologiaVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#form0 input, #form0 select, #form0 textarea", mappaAssociativaInputValore, mappaDeiParamerti);
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
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", URLROOT + "/rivenditori/inviodati.php", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

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
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
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

					Object[] response = connessione_9.post("Connessione 9 - Invio delle foto " + "-immagine " + i + "-", URLROOT + "/rivenditori/upload.php?salva=" + param2 + "-" + param1, reqEntity, requestHeaders, requestCookies, DEBUG_MODE);			

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


		//Connessione 5 - GET della pagina "Admin" per ottenere il codice inserzione
		HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_5.get("Connessione 5 - GET della pagina Admin", URLROOT + "/rivenditori/admin.php", requestHeaders, requestCookies, DEBUG_MODE);
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


	//Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaVeicolo scheda) {

		//Apro il browser e inserisco credenziali		
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(URLROOT));
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

		//Connessione 0 - GET della home page - Opzionale
		/*HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT, requestHeaders, null, DEBUG_MODE);
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/rivenditori/accedi.php", requestHeaders, null, DEBUG_MODE);
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
		//mappaAssociativaInputValore.put("Submit","***site***");
		mappaAssociativaInputValore.put("email",USERNAME);
		mappaAssociativaInputValore.put("password",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#boxint input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/rivenditori/accedi.php", postParameters, requestHeaders, null, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
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


		//Connessione 3 - POST di eliminazione annuncio		
		//postParameters.add(new BasicNameValuePair("button", "Cancella Record"));
		postParameters.add(new BasicNameValuePair("cancella", codiceInserzione));
		HttpPortalPostConnection connessione_3 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_3.post("Connessione 3 - POST di eliminazione annuncio", URLROOT + "/rivenditori/delete.php?Id=" + codiceInserzione, postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
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

		//Stampo a video un messaggio informativo
		if(!modifica) {
			messageEliminazioneOK(NOMEPORTALE);
		}
		else {
			System.out.println("Eliminata da: " + NOMEPORTALE);
		}

		return true;

	}


	//Metodi di supporto


}