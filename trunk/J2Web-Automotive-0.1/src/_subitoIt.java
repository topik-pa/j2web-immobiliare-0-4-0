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
/*import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;*/
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
	private final String USERNAME = "marco--pavan@libero.it";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.subito.it";
	private final String HOST2 = "www2.subito.it";

	private final String COOKIE_DEFAULT_PATH = "/";
	private final String COOKIE_DEFAULT_DOMAIN = ".subito.it";

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	int random = (int)Math.random() * 100000;
	private String codiceInserzione = Integer.toString(random);
	private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean modifica = false; //è sempre false per questo portale
	
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
		mappaAssociativaInputValore = new Hashtable<String,String>();

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		//Inizializzazione scheda
		this.scheda=scheda;

		//Inizializzo gli headers
		inizializzaHeaders(requestHeaders, HOST);
		requestCookies.clear();

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
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", SECUREURLROOT + "/account/login_form/", requestHeaders, requestCookies, DEBUG_MODE);
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
		mappaAssociativaInputValore.put("login_email",USERNAME);
		mappaAssociativaInputValore.put("login_passwd",PASSWORD);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#boxLoginContainer input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(0);
		requestHeaders.add(new BasicNameValuePair("Host", HOST2));
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", SECUREURLROOT + "/account/login", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Gestione dei cookie
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


		//Connessione 3 - GET della pagina di redirect dopo inserimento parametri login - (Opzionale)
		/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di redirect dopo inserimento parametri login", SECUREURLROOT + location, requestHeaders, requestCookies, DEBUG_MODE);
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
		}*/


		//Connessione 4 - GET della pagina di redirect dopo inserimento parametri login - la connessione precedente non mi ritorna la location (Opzionale)
		/*HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina di redirect dopo inserimento parametri login - la connessione precedente non mi ritorna la location", SECUREURLROOT + "/account/manageads/", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 5 - GET della pagina "Inserisci il tuo annuncio"
		HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_5.get("Connessione 5 - GET della pagina \"Inserisci il tuo annuncio\"", SECUREURLROOT + "/ai/form/0", requestHeaders, requestCookies, DEBUG_MODE);
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


		//Connessione5b - POST delle immagini
		for(int i=1; i<scheda.arrayImages.length; i++) {
			if(scheda.arrayImages[i]!=null && i<=6) {

				//Raccolgo i parametri nella tabella di dipendenza
				MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
				FileBody bin = new FileBody(scheda.arrayImages[i]);

				HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
				try {   
					reqEntity.addPart("image", bin );
					reqEntity.addPart("check_type_diff", new StringBody("0") );
					reqEntity.addPart("category", new StringBody("2") );
					reqEntity.addPart("animal_type", new StringBody("") );
					reqEntity.addPart("office_type", new StringBody("") );
					reqEntity.addPart("room_type", new StringBody("") );
					reqEntity.addPart("ship_type", new StringBody("") );
					reqEntity.addPart("vehicle_type", new StringBody("") );
					reqEntity.addPart("caravan_type", new StringBody("") );
					reqEntity.addPart("sport_type", new StringBody("") );
					reqEntity.addPart("children_type", new StringBody("") );
					reqEntity.addPart("children_age", new StringBody("") );
					reqEntity.addPart("hobby_type", new StringBody("") );
					reqEntity.addPart("audiovideo_type", new StringBody("") );
					reqEntity.addPart("bicycle_type", new StringBody("") );
					reqEntity.addPart("bikeversion", new StringBody("") );
					reqEntity.addPart("moto_type", new StringBody("") );
					reqEntity.addPart("computer_type", new StringBody("") );
					reqEntity.addPart("clothing_type", new StringBody("") );
					reqEntity.addPart("clothing_gender", new StringBody("") );
					reqEntity.addPart("region", new StringBody("0") );
					reqEntity.addPart("city", new StringBody("0") );
					reqEntity.addPart("town", new StringBody("") );
					reqEntity.addPart("zone", new StringBody("") );
					reqEntity.addPart("company_ad", new StringBody("0") );
					reqEntity.addPart("name", new StringBody("") );
					reqEntity.addPart("email", new StringBody("") );
					reqEntity.addPart("phone", new StringBody("") );
					reqEntity.addPart("type", new StringBody("s") );
					reqEntity.addPart("carbrand", new StringBody("") );
					reqEntity.addPart("regdate", new StringBody("0") );
					reqEntity.addPart("mileage", new StringBody("") );
					reqEntity.addPart("fuel", new StringBody("") );
					reqEntity.addPart("car_type", new StringBody("") );
					reqEntity.addPart("gearbox", new StringBody("") );
					reqEntity.addPart("pollution", new StringBody("") );
					reqEntity.addPart("seats", new StringBody("0") );
					reqEntity.addPart("doors", new StringBody("0") );
					reqEntity.addPart("color", new StringBody("") );
					reqEntity.addPart("subject", new StringBody("") );
					reqEntity.addPart("price", new StringBody("") );
					reqEntity.addPart("body", new StringBody("") );
					reqEntity.addPart("cites_cert_numb", new StringBody("") );
					reqEntity.addPart("cites_cert_date", new StringBody("") );
					reqEntity.addPart("cites_cert_from", new StringBody("") );
					reqEntity.addPart("show_map", new StringBody("0") );
					reqEntity.addPart("address", new StringBody("") );
					reqEntity.addPart("latitude", new StringBody("") );
					reqEntity.addPart("longitude", new StringBody("") );
					reqEntity.addPart("zoom", new StringBody("") );
					reqEntity.addPart("accept_equal_opp", new StringBody("1") );
					reqEntity.addPart("validate", new StringBody("continua") );
					reqEntity.addPart("extra_images", new StringBody("Carica") );

					Object[] response = connessione_6.post("Connessione5b - POST delle immagini", SECUREURLROOT + "/ai/verify/0", reqEntity, requestHeaders, requestCookies, DEBUG_MODE);			

					//Controllo il response status
					BasicStatusLine responseStatus = (BasicStatusLine) response[2];
					if( (responseStatus.getStatusCode()==302)) {
						Header[] responseHeaders = (Header[])response[0];				
						//Gestione dei cookie
						setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
						//Trovo la location
						location = getHeaderValueByName(responseHeaders, "Location");
						/*if(!location.contains("/ai/form/1")) {
							throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Header Location non previsto"));
						}*/
					}
					else {
						throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
					}    	

				} catch (IOException | RuntimeException e) {
					throw new HttpCommunicationException(e);
				}
				finally {
				}
			}
		}


		//Connessione 6 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		//mappaAssociativaInputValore.put("check_type_diff", "***site***");
		if(scheda.veicolo.equals("auto")) {
			mappaAssociativaInputValore.put("category", "Auto"); //Auto
		}
		mappaAssociativaInputValore.put("animal_type","Seleziona il tipo");
		mappaAssociativaInputValore.put("office_type","Seleziona il tipo di ufficio o locale commerciale"); 
		mappaAssociativaInputValore.put("room_type","Seleziona il tipo di stanza"); 
		mappaAssociativaInputValore.put("ship_type","Seleziona il tipo di imbarcazione"); 
		mappaAssociativaInputValore.put("vehicle_type","Seleziona il tipo di veicolo"); 
		mappaAssociativaInputValore.put("caravan_type","Seleziona il tipo di veicolo"); 
		mappaAssociativaInputValore.put("sport_type","Seleziona il tipo di sport"); 
		mappaAssociativaInputValore.put("children_type","Seleziona il tipo di oggetto"); 
		mappaAssociativaInputValore.put("children_age","Seleziona la fascia d'et"); 
		mappaAssociativaInputValore.put("hobby_type","Seleziona il tipo di hobby"); 
		mappaAssociativaInputValore.put("audiovideo_type","Seleziona il prodotto"); 
		mappaAssociativaInputValore.put("bicycle_type","Seleziona il tipo di bicicletta");
		mappaAssociativaInputValore.put("phone_type","Scegli"); 
		mappaAssociativaInputValore.put("bikebrand","Seleziona la marca");
		mappaAssociativaInputValore.put("bikeversion",";Seleziona prima il modell"); 
		mappaAssociativaInputValore.put("moto_type","Seleziona tipologia");
		mappaAssociativaInputValore.put("computer_type","Scegli"); 
		mappaAssociativaInputValore.put("clothing_type","Scegli");
		mappaAssociativaInputValore.put("clothing_gender","Scegli"); 
		mappaAssociativaInputValore.put("clothing_number","Scegli");
		mappaAssociativaInputValore.put("region", REGIONE_UTENTE); //Friuli-Venezia Giulia
		mappaAssociativaInputValore.put("zone","");
		mappaAssociativaInputValore.put("company_ad", "1"); //pubblica come Azienda
		mappaAssociativaInputValore.put("name",RAGIONESOCIALE_UTENTE); //autoeauto
		mappaAssociativaInputValore.put("servicetype","Seleziona la tipologia");
		mappaAssociativaInputValore.put("phone",TELEFONO_UTENTE); //deve essere preventivamente validato da subito.it
		//mappaAssociativaInputValore.put("phone_hidden","***site***");
		mappaAssociativaInputValore.put("type","s"); //Vendita 
		mappaAssociativaInputValore.put("carbrand",scheda.marcaVeicolo);

		if(scheda.annoImmatricolazioneVeicolo.equals("Da immatricolare")) {
			mappaAssociativaInputValore.put("regdate","2014");
		}
		else {
			mappaAssociativaInputValore.put("regdate",scheda.annoImmatricolazioneVeicolo);
		}

		mappaAssociativaInputValore.put("carversion", "");
		mappaAssociativaInputValore.put("fuel",scheda.carburanteVeicolo);
		mappaAssociativaInputValore.put("country","Seleziona");
		mappaAssociativaInputValore.put("car_type",scheda.carrozzeriaVeicolo);
		mappaAssociativaInputValore.put("gearbox",scheda.tipologiaCambioVeicolo);
		mappaAssociativaInputValore.put("pollution",scheda.classeEmissioniVeicolo);
		mappaAssociativaInputValore.put("seats",""+scheda.postiASedereVeicolo);
		mappaAssociativaInputValore.put("doors","Porte");
		switch (scheda.coloreEsternoVeicolo) {
		case "Bianco":
			mappaAssociativaInputValore.put("color","1");
			break;
		case "Grigio":
			mappaAssociativaInputValore.put("color","2");	
			break;
		case "Marrone":
			mappaAssociativaInputValore.put("color","3");
			break;
		case "Nero":
			mappaAssociativaInputValore.put("color","4");
			break;
		case "Rosso":
			mappaAssociativaInputValore.put("color","5");
			break;
		case "Verde":
			mappaAssociativaInputValore.put("color","6");
			break;
		case "Giallo":
			mappaAssociativaInputValore.put("color","7");
			break;
		case "Blu":
			mappaAssociativaInputValore.put("color","8");
			break;			
		default:
			mappaAssociativaInputValore.put("color","");
			break;
		}
		mappaAssociativaInputValore.put("subject",scheda.marcaVeicolo + " " + scheda.modelloVeicolo + " " + scheda.versioneVeicolo + " " + scheda.tipologiaContrattoVeicolo);
		mappaAssociativaInputValore.put("price",scheda.prezzoVeicolo);
		mappaAssociativaInputValore.put("gender","Scegli");
		mappaAssociativaInputValore.put("smoker","Scegli");
		mappaAssociativaInputValore.put("cubic_capacity",scheda.cilindrataVeicolo);
		mappaAssociativaInputValore.put("rooms","Seleziona");
		mappaAssociativaInputValore.put("size","");
		mappaAssociativaInputValore.put("length","");
		mappaAssociativaInputValore.put("contract_type","Seleziona il tipo di contratto");
		mappaAssociativaInputValore.put("degree","Seleziona il tuo titolo di studio");
		mappaAssociativaInputValore.put("worklevel","Seleziona l'inquadramento");
		mappaAssociativaInputValore.put("workhours","Seleziona l'orario di lavoro");
		mappaAssociativaInputValore.put("body",scheda.descrizioneVeicolo);
		mappaAssociativaInputValore.put("cites_cert_numb","");
		mappaAssociativaInputValore.put("cites_cert_date","");
		mappaAssociativaInputValore.put("cites_cert_from","");
		mappaAssociativaInputValore.put("show_map","0"); //non mostrare la mappa
		mappaAssociativaInputValore.put("address","");
		mappaAssociativaInputValore.put("latitude","");
		mappaAssociativaInputValore.put("longitude","");
		mappaAssociativaInputValore.put("zoom","");
		mappaAssociativaInputValore.put("image","");
		mappaAssociativaInputValore.put("accept_equal_opp","1"); //annuncio per ambo i sessi
		mappaAssociativaInputValore.put("accept_term_of_use","1"); //accetto i termini d'uso
		mappaAssociativaInputValore.put("validate","continua");

		if(!scheda.chilometraggioVeicolo.equals("")) {
			String mileage = "";
			int chilometraggio = Integer.parseInt(scheda.chilometraggioVeicolo);
			if(chilometraggio==0) {mileage="0";}
			if(chilometraggio>0) {mileage="1";}
			if(chilometraggio>5000) {mileage="2";}
			if(chilometraggio>10000) {mileage="3";}
			if(chilometraggio>15000) {mileage="4";}
			if(chilometraggio>20000) {mileage="5";}
			if(chilometraggio>25000) {mileage="6";}
			if(chilometraggio>30000) {mileage="7";}
			if(chilometraggio>35000) {mileage="8";}
			if(chilometraggio>40000) {mileage="9";}
			if(chilometraggio>45000) {mileage="10";}
			if(chilometraggio>50000) {mileage="11";}
			if(chilometraggio>55000) {mileage="12";}
			if(chilometraggio>60000) {mileage="13";}
			if(chilometraggio>65000) {mileage="14";}
			if(chilometraggio>70000) {mileage="15";}
			if(chilometraggio>75000) {mileage="16";}
			if(chilometraggio>80000) {mileage="17";}
			if(chilometraggio>85000) {mileage="18";}
			if(chilometraggio>90000) {mileage="19";}
			if(chilometraggio>95000) {mileage="20";}
			if(chilometraggio>100000) {mileage="21";}
			if(chilometraggio>110000) {mileage="22";}
			if(chilometraggio>120000) {mileage="23";}
			if(chilometraggio>130000) {mileage="24";}
			if(chilometraggio>140000) {mileage="25";}
			if(chilometraggio>150000) {mileage="26";}
			if(chilometraggio>160000) {mileage="27";}
			if(chilometraggio>170000) {mileage="28";}
			if(chilometraggio>180000) {mileage="29";}
			if(chilometraggio>190000) {mileage="30";}
			if(chilometraggio>200000) {mileage="31";}
			if(chilometraggio>250000) {mileage="32";}
			if(chilometraggio>300000) {mileage="33";}
			if(chilometraggio>350000) {mileage="34";}
			if(chilometraggio>400000) {mileage="35";}
			if(chilometraggio>450000) {mileage="36";}
			if(chilometraggio>500000) {mileage="37";}

			postParameters.add(new BasicNameValuePair("mileage", mileage));
		}

		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#content form input, #content form select, #content form textarea", mappaAssociativaInputValore, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);

		//Questi parametri li devo valorizzare qui
		postParameters.add(new BasicNameValuePair("city", SUBITOIT_CODCITY)); //Udine
		postParameters.add(new BasicNameValuePair("town", SUBITOIT_CODTOWN));  //Udine

		var_idMarca = mappaDeiParamerti.get("carbrand");
		String carmodel = getCarmodel(scheda.modelloVeicolo, var_idMarca);

		postParameters.add(new BasicNameValuePair("carmodel", carmodel));


		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", SECUREURLROOT + "/ai/verify/0", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("/ai/preview")) {
					inserimentoOK=true;
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
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}


		//Connessione 7 - GET della pagina "Preview annuncio" - Opzionale
		HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
		try {
			Object[] response =  connessione_7.get("Connessione 7 - GET della pagina \"Preview annuncio\"", SECUREURLROOT + location, requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 8 - POST della conferma alla pubblicazione
		/*HttpPortalPostConnection connessione_8 = new HttpPortalPostConnection();
		try {    
			postParameters.add(new BasicNameValuePair("payment_type", "cc"));

			Object[] response = connessione_8.post("Connessione 8 - POST della conferma alla pubblicazione", SECUREURLROOT + "/ai/create/0", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("/ai/confirm/")) {
					inserimentoOK=true;
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


	private String getCarmodel(String modelloVeicolo, String idMarca) throws HttpCommunicationException {

		String idModello = null;

		HttpPortalGetConnection connessione_a = new HttpPortalGetConnection();
		try {
			Object[] response =  connessione_a.get("Connessione a - GET per ottenere l'idModello", SECUREURLROOT + "/templates/common/carmodels.html?cb=" + idMarca, requestHeaders, requestCookies, DEBUG_MODE);
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

		String[] parts = responseBody.split("~");
		for(int i = 0; i<parts.length; i++) {
			if(parts[i].contains(modelloVeicolo)) {
				int start = parts[i].indexOf("|")+1;
				idModello = parts[i].substring(start);
				return idModello;
			}
		}

		return idModello;
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

		JOptionPane.showMessageDialog(null, "Non è prevista l'eliminazione automatica della scheda da: " + NOMEPORTALE + "\n\nPer elimibare la scheda, procedere direttamente attraverso il portale Web.", "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);

		return true;

	}


}