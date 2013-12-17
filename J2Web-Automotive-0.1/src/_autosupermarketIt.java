/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */ 

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author marco
 */

//La classe principale
public class _autosupermarketIt extends PortaleWeb {     

	//Variabili portale
	private final String NOMEPORTALE = "www.autosupermarket.it";
	private final String URLROOT = "http://www.autosupermarket.it";
	//private final String SECUREURLROOT = "https://secure.autoscout24.it";
	private final String USERNAME = "c2775833@drdrb.com";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.autosupermarket.it";
	//private final String HOST2 = "secure.autoscout24.it";
	//private final String HOST3 = "offerta.autoscout24.it";

	//private final String SECONDCOOKIENAME = "__RequestVerificationToken_Lw__";
	//private final String SECONDCOOKIEDOMAIN = "secure.autoscout24.it";
	//private final String SECONDCOOKIEHEADER = "";
	//private final String SECONDCOOKIEVALUE = "";


	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	//private BasicNameValuePair sessionCookie;
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
	String idModello;
	//String articleId;
	//String BaseData_ModelId;
	//String Equipment_EquipmentIds="";

	//Costruttore
	public _autosupermarketIt (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

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

		//autosupermarket accetto minimo due foto per annuncio
		int numeroFoto=0;
		for(int i=1; i<9; i++) { //max 8 foto
			if(scheda.arrayImages[i]!=null) {
				numeroFoto ++;
			}
		}
		if(numeroFoto<2) {
			JOptionPane.showMessageDialog(null, NOMEPORTALE + "Accetta solo annunci con minimo 2 immagini");
			return false;
		}

		//Inizializzazione scheda
		this.scheda=scheda;

		//Imposto le variabili per il session cookie
		SESSIONCOOKIENAME = "autosupermarket";
		SESSIONCOOKIEDOMAIN = ".www.autosupermarket.it";
		SESSIONCOOKIEHEADER = "";
		SESSIONCOOKIEVALUE = "";

		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		requestHeaders.clear();
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/mycar/index.html", requestHeaders, null, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];

				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				connessione_1.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
				setCookies(responseHeaders, requestCookies);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		tabellaDiDipendenza.put("user[_csrf_token]","***site***");
		tabellaDiDipendenza.put("user[password]", PASSWORD);
		tabellaDiDipendenza.put("user[username]",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#signinForm input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/mycar/signin.html", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("/mycar/index.html")) {
					responseBody = (String)response[1];
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
			tabellaDiDipendenza.clear();
			mappaDeiParamerti.clear();
			postParameters.clear();
		}


		//Connessione 3 - GET della pagina di I miei annunci
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina I miei annunci", URLROOT + location, requestHeaders, requestCookies, debugMode);
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


		//Connessione 4 - GET della pagina di inserzione annuncio
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina di inserzione annuncio",  URLROOT + "/mycar/new.html", requestHeaders, requestCookies, debugMode);
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


		//Connessione 5 - GET per ottenere il modella dell'auto
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("car[make_id]",scheda.marcaVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "select#car_make_id", tabellaDiDipendenza, mappaDeiParamerti);
		HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_5.get("Connessione 5 - GET per ottenere il modella dell'auto",  URLROOT + "/mycar/ajax-update-model.html?make=" + mappaDeiParamerti.get("car[make_id]"), requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				String responseBody2 = (String)response[1];

				//Parsing JSON della risposta
				JSONObject json = null;
				JSONArray jsonOptions = null;
				String labelAttuale = null;
				String valueAttuale = null;

				double resultComparation = 0;
				List<char[]> stringaScheda = bigram(scheda.modelloVeicolo.toLowerCase());
				try {
					json = new JSONObject(responseBody2);
					if(json.getString("status").equals("OK")) {
						jsonOptions = json.getJSONArray("options"); 

						for(int i=0; i<jsonOptions.length(); i++) {
							JSONObject currentJson = jsonOptions.getJSONObject(i);
							if(currentJson.has("label")) {
								labelAttuale = currentJson.getString("label");
								valueAttuale = currentJson.getString("value");

								List<char[]> stringaPortale = bigram(labelAttuale.toLowerCase()); 
								double actualResultComparation = dice(stringaPortale, stringaScheda);
								System.out.println("comp: " + actualResultComparation);
								if(actualResultComparation>=resultComparation && actualResultComparation>0.7) {
									resultComparation = actualResultComparation;
									idModello = valueAttuale;  								
								}
							}
						}
					}
					else {
						throw new HttpCommunicationException(new HttpWrongResponseBodyException("La GET ha ritornato un response inatteso"));
					}				
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Connessione 6 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("car[carana001_carlst001_listing_type_cod]", scheda.tipologiaVeicolo); //da fare sotto
		tabellaDiDipendenza.put("car[carana001_carlst002_interior_cod]", scheda.finitureInterneVeicolo);//da fare sotto
		tabellaDiDipendenza.put("car[carana001_carlst004_auto_body_cod]", scheda.carrozzeriaVeicolo);
		tabellaDiDipendenza.put("car[carana001_carlst005_auto_body_status_cod]", "Scegli...");
		tabellaDiDipendenza.put("car[carana001_carlst006_fuel_cod]", scheda.carburanteVeicolo);
		if(scheda.coloreMetalizzato) {
			tabellaDiDipendenza.put("car[carana001_carlst008_color_type_cod]", "Metalizzato");
		}
		else {
			tabellaDiDipendenza.put("car[carana001_carlst008_color_type_cod]", "Scegli...");
		}
		tabellaDiDipendenza.put("car[carana001_carlst009_drive_type_cod]", scheda.carrozzeriaVeicolo);
		tabellaDiDipendenza.put("car[carana001_carlst009_drive_type_cod]", scheda.tipologiaMotoreVeicolo);
		tabellaDiDipendenza.put("car[carana001_carlst010_trasmission_cod]", scheda.tipologiaCambioVeicolo);
		tabellaDiDipendenza.put("car[carana001_carlst015_version_des]", scheda.versioneVeicolo);
		tabellaDiDipendenza.put("car[carana001_carlst017_emission_cod]", scheda.classeEmissioniVeicolo);
		tabellaDiDipendenza.put("car[carana001_carlst019_price_type_cod]", "***site***");
		tabellaDiDipendenza.put("car[carana001_carlst021_guarantee_type_cod]", "Scegli...");
		
		//tabellaDiDipendenza.put("car[carana001_cv_num]", scheda.CVVeicolo);
		if(scheda.CVVeicolo.equals("")) {
			tabellaDiDipendenza.put("car[carana001_cv_num]", "***site***");
		}
		else {
			tabellaDiDipendenza.put("car[carana001_cv_num]", scheda.CVVeicolo);
		}
		
		tabellaDiDipendenza.put("car[carana001_door_num]", "Scegli...");
		tabellaDiDipendenza.put("car[carana001_engine_des]", scheda.cilindrataVeicolo);
		if(scheda.prezzoTrattabile)  {
			tabellaDiDipendenza.put("car[carana001_glblst001_condition_cod]", "Trattabili");
		}
		else {
			tabellaDiDipendenza.put("car[carana001_glblst001_condition_cod]", "Non Trattabili");
		}
		
		tabellaDiDipendenza.put("car[carana001_glblst002_color_cod]", scheda.coloreEsternoVeicolo);
		
		
		if(scheda.chilometraggioVeicolo.equals("")) {
			tabellaDiDipendenza.put("car[carana001_km_num]", "***site***");
		}
		else {
			tabellaDiDipendenza.put("car[carana001_km_num]", scheda.chilometraggioVeicolo);
		}
		
		//tabellaDiDipendenza.put("car[carana001_kw_num]", scheda.KWVeicolo);
		if(scheda.KWVeicolo.equals("")) {
			tabellaDiDipendenza.put("car[carana001_kw_num]", "***site***");
		}
		else {
			tabellaDiDipendenza.put("car[carana001_kw_num]", scheda.KWVeicolo);
		}
		
		tabellaDiDipendenza.put("car[carana001_last_review_num]", "Scegli...");
		tabellaDiDipendenza.put("car[carana001_month_num]", "0"+scheda.meseImmatricolazioneVeicoloIndex);
		tabellaDiDipendenza.put("car[carana001_notes_des]", scheda.descrizioneVeicolo);
		tabellaDiDipendenza.put("car[carana001_owner_num]", scheda.numeroPrecedentiProprietariVeicolo);
		tabellaDiDipendenza.put("car[carana001_public_price_num]", scheda.prezzoVeicolo);
		tabellaDiDipendenza.put("car[carana001_seat_num]", "0"+scheda.postiASedereVeicoloIndex);
		tabellaDiDipendenza.put("car[carana001_year_num]", "0"+scheda.annoImmatricolazioneVeicoloIndex);
		tabellaDiDipendenza.put("car[id]", ""); //non ha value nel DOM
		tabellaDiDipendenza.put("car[make_id]", scheda.marcaVeicolo);
		tabellaDiDipendenza.put("car[trattativa_riservata]", "***site***");
		tabellaDiDipendenza.put("car[carana001_status_flg]", "***site***");
		tabellaDiDipendenza.put("car[carana001_vat_deductible_flg]", "***site***");
		
		

		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "form#adv-form input, form#adv-form select, form#adv-form textarea", tabellaDiDipendenza, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);

		//Questi parametri li devo valorizzare qui
		postParameters.add(new BasicNameValuePair("car[model_id]", idModello));
		/*postParameters.add(new BasicNameValuePair("car[carana001_carlst001_listing_type_cod]", "1"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst002_interior_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst004_auto_body_cod]", "4"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst005_auto_body_status_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst006_fuel_cod]", "4"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst008_color_type_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst009_drive_type_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst010_trasmission_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst015_version_des]", ""));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst017_emission_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst019_price_type_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_carlst021_guarantee_type_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_cv_num]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_door_num]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_engine_des]", ""));
		postParameters.add(new BasicNameValuePair("car[carana001_glblst001_condition_cod]", "1"));
		postParameters.add(new BasicNameValuePair("car[carana001_glblst002_color_cod]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_km_num]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_kw_num]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_last_review_num]", ""));
		postParameters.add(new BasicNameValuePair("car[carana001_month_num]", "2"));
		postParameters.add(new BasicNameValuePair("car[carana001_notes_des]", ""));
		postParameters.add(new BasicNameValuePair("car[carana001_owner_num]", ""));
		postParameters.add(new BasicNameValuePair("car[carana001_public_price_num]", "12500"));
		postParameters.add(new BasicNameValuePair("car[carana001_seat_num]", ""));
		postParameters.add(new BasicNameValuePair("car[carana001_status_flg]", "1"));
		postParameters.add(new BasicNameValuePair("car[carana001_vat_deductible_flg]", "0"));
		postParameters.add(new BasicNameValuePair("car[carana001_year_num]", "2011"));
		postParameters.add(new BasicNameValuePair("car[id]", ""));
		postParameters.add(new BasicNameValuePair("car[make_id]", "1"));
		postParameters.add(new BasicNameValuePair("car[model_id]", "1711"));
		postParameters.add(new BasicNameValuePair("car[trattativa_riservata]", "0"));*/

		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri di annuncio", URLROOT + "/mycar/new.html", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("/manage_photo/id/")) {
					//responseBody = (String)response[1];
					int start = location.indexOf("/id/")+4;
					int end = location.indexOf(".html");
					codiceInserzione = location.substring(start, end);
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


		//Connessione 7 - GET della pagina di inserzione immagini
		HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_7.get("Connessione 7 - GET della pagina di inserzione immagini",  URLROOT + location, requestHeaders, requestCookies, debugMode);
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


		//Connessione 8 - Invio delle foto (passo 1)
		for(int i=1; i<9; i++) { //max 8 foto
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_8 = new HttpPortalPostConnection();        
				try {

					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					FileBody bin = new FileBody(scheda.arrayImages[i]);
					reqEntity.addPart("name", new StringBody("o_" + codiceInserzione + "_" + i + ".jpg") );
					reqEntity.addPart("file", bin );

					Object[] response = connessione_8.post("Connessione 8 - Invio delle foto (passo 1)" + "Immagine: " + i, URLROOT + "/mycar/plupload/id/" + codiceInserzione + ".html?position=" + i, reqEntity, requestHeaders, requestCookies, debugMode);			

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


		//Connessione 9 - POST delle immagini (passo 2)
		postParameters.add(new BasicNameValuePair("photos[photo_1][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_2][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_3][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_4][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_5][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_6][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_7][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_8][photo]", ""));

		HttpPortalPostConnection connessione_9 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_9.post("Connessione 9 - POST delle immagini (passo 2)", URLROOT + location, postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(!location.contains("manage_photo/id/" + codiceInserzione)) {
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


		//Connessione 10 - POST delle immagini (passo 3)
		postParameters.add(new BasicNameValuePair("to_publish", "1"));
		postParameters.add(new BasicNameValuePair("photos[photo_1][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_2][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_3][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_4][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_5][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_6][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_7][photo]", ""));
		postParameters.add(new BasicNameValuePair("photos[photo_8][photo]", ""));
		postParameters.add(new BasicNameValuePair("submit", "photos"));

		HttpPortalPostConnection connessione_10 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_10.post("Connessione 10 - POST delle immagini (passo 3)", URLROOT + location, postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(!location.contains("publish/id/" + codiceInserzione)) {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Location non corretta"));
				}
				else {
					inserimentoOK = true; //da togliere!
					responseBody = (String)response[1];
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


		//Connessione 11 - POST finale di pubblicazione
		/*postParameters.add(new BasicNameValuePair("publisher[mycar]", "on"));

		HttpPortalPostConnection connessione_11 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_11.post("Connessione 10 - POST finale di pubblicazione", URLROOT + location, postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(!location.contains("index.html")) {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Location non corretta"));
				}
				else {
					responseBody = (String)response[1];
					if(responseBody.contains("pubblicato correttamente")) {
						inserimentoOK = true;
					}
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

		codiceInserzione = scheda.getCodiceInserimento(idPortale);
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/mycar/index.html";
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

		//Imposto le variabili per il session cookie
		SESSIONCOOKIENAME = "autosupermarket";
		SESSIONCOOKIEDOMAIN = ".www.autosupermarket.it";
		SESSIONCOOKIEHEADER = "";
		SESSIONCOOKIEVALUE = "";

		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		requestHeaders.clear();
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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/mycar/index.html", requestHeaders, null, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];

				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				connessione_1.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
				setCookies(responseHeaders, requestCookies);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		tabellaDiDipendenza.put("user[_csrf_token]","***site***");
		tabellaDiDipendenza.put("user[password]", PASSWORD);
		tabellaDiDipendenza.put("user[username]",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#signinForm input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/mycar/signin.html", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("/mycar/index.html")) {
					responseBody = (String)response[1];
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
			tabellaDiDipendenza.clear();
			mappaDeiParamerti.clear();
			postParameters.clear();
		}


		//Connessione 3 - GET della pagina di I miei annunci
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina I miei annunci", URLROOT + location, requestHeaders, requestCookies, debugMode);
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
		HttpPortalPostConnection connessione_4 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_4.post("Connessione 4 - POST di eliminazione annuncio", URLROOT + "/mycar/delete/id/" + codiceInserzione + ".html", postParameters, requestHeaders, requestCookies, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(!location.contains("/mycar/index.html")) {
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


	//Metodi di supporto
	public String getBaseData_ModelId(String modelId) throws HttpCommunicationException {

		String returnValue=null; 

		//Connessione 4b - GET per ottenere il BaseData_ModelId
		HttpPortalGetConnection connessione_4b = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4b.get("Connessione 4b - GET per ottenere il BaseData_ModelId", "https://offerta.autoscout24.it/api/modelsC/" + modelId + "?_=1386424481611", requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				responseBody = (String)response[1];

				//Parse HMTL to retrieve some informations
				org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                       
				Elements optionElements = doc.getElementsByTag("option");
				if(optionElements!=null) {
					Iterator<Element> iterator = optionElements.iterator();
					while(iterator.hasNext()) {
						Element currentElement = iterator.next();
						if(currentElement.text().equals(scheda.modelloVeicolo)) {  
							returnValue = currentElement.val();
							System.out.println("method: getBaseData_ModelId-->" + returnValue);
						}
					}
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException | HttpCommunicationException e) {
			throw new HttpCommunicationException(e);
		}

		return returnValue;
	}


}