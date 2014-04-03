/**
 * Sincronizzazione con: DB MLS
 * @author marco
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import javax.swing.ImageIcon;
//import javax.swing.JOptionPane;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONObject;


//La classe principale
public class _portaleMLS extends PortaleWeb { 
	
	private final String NOMEPORTALE = "www.auto-nuove-usate.it - Multi Level Sharing per l\'automotive";
	private final String URLROOT = "www.auto-nuove-usate.it";

	private boolean inserimentoOK = false;
	private boolean eliminazioneOK = false;
	private boolean modifica = false;

	//Costruttore
	public _portaleMLS (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

		super(icon, valoreLabel, idPortale, isActive);

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException, UnsupportedEncodingException {
		
		if(modifica) {			
			System.out.println("(MLS) Modifica scheda: " + scheda.codiceScheda + "...");
		}
		else  {
			System.out.println("(MLS) Inserimento scheda: " + scheda.codiceScheda + "...");	
		}
		
		String Immagine1, Immagine2, Immagine3, Immagine4, Immagine5, Immagine6, Immagine7, Immagine8, Immagine9, Immagine10;
		Immagine1 = Immagine2 = Immagine3 = Immagine4 = Immagine5 = Immagine6 = Immagine7 = Immagine8 = Immagine9 = Immagine10 = "NULL";
		String queryString = "";

		//Inserimento delle immagini sul server remoto
		for(int i=1; i<scheda.arrayImages.length; i++) {
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_inserimentoImmagineInDB = new HttpPortalPostConnection();
				MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

				//Trovo l'estensione del file
				String currentFileType = "";
				int x = scheda.arrayImages[i].getName().lastIndexOf('.');
				if (x > 0) {
					currentFileType = scheda.arrayImages[i].getName().substring(x);
				}

				//Il nome del file sul server remoto
				String fileName = scheda.codiceScheda + "_img_" + i + currentFileType;

				//Preparo il parametro HTTP
				FileBody bin = new FileBody(scheda.arrayImages[i]);
				reqEntity.addPart("file", bin);
				reqEntity.addPart("fileName", new StringBody(fileName));

				try {
					connessione_inserimentoImmagineInDB.post("Connessioni per l'inserimento della scheda immobile nel portale immobiliare - inserimento immagine " + i + " - " + fileName, urlInserimentoImmaginiInRemoto, reqEntity, null, null, true);
				} catch (IOException e) {
					throw new HttpCommunicationException(e);
				}

				switch (i) {
				case 1:
					Immagine1 = fileName;
					break;
				case 2:
					Immagine2 = fileName;
					break;
				case 3:
					Immagine3 = fileName;
					break;
				case 4:
					Immagine4 = fileName;
					break;
				case 5:
					Immagine5 = fileName;
					break;
				case 6:
					Immagine6 = fileName;
					break;
				case 7:
					Immagine7 = fileName;
					break;
				case 8:
					Immagine8 = fileName;
					break;
				case 9:
					Immagine9 = fileName;
					break;			
				default:
					Immagine10 = fileName;
					break;
				}
			}
		}

		String thisCarburante;
		switch (scheda.carburanteVeicolo) {
		case "Benzina":
			thisCarburante = "1";
			break;
		case "Diesel":
			thisCarburante = "2";
			break;
		case "Gas":
			thisCarburante = "3";
			break;
		case "Gpl":
			thisCarburante = "4";
			break;
		case "Metano":
			thisCarburante = "5";
			break;
		case "Ibrida":
			thisCarburante = "6";
			break;
		case "Etanolo":
			thisCarburante = "7";
			break;
		case "Elettrica":
			thisCarburante = "8";
			break;
		case "Idrogeno":
			thisCarburante = "9";
			break;
		case "Elettrica/Benzina":
			thisCarburante = "6";
			break;
		case "Elettrica/Diesel":
			thisCarburante = "6";
			break;
		default:
			thisCarburante = "1";
			break;
		}

		String thisTipologia;
		switch (scheda.tipologiaVeicolo) {
		case "Veicolo d'epoca":
			thisTipologia = "4";
			break;
		case "Veicolo km 0":
			thisTipologia = "1";
			break;
		case "Veicolo nuovo":
			thisTipologia = "2";
			break;
		case "Veicolo usato":
			thisTipologia = "3";
			break;
		case "Veicolo aziendale":
			thisTipologia = "3";
			break;
		default:
			thisTipologia = "3";
			break;
		}

		String thisCarrozzeria;
		switch (scheda.carrozzeriaVeicolo) {
		case "City car":
			thisCarrozzeria = "1";
			break;
		case "Cabrio":
			thisCarrozzeria = "7";
			break;
		case "Coupé":
			thisCarrozzeria = "6";
			break;
		case "SUV/Fuoristrada":
			thisCarrozzeria = "5";
			break;
		case "Station wagon":
			thisCarrozzeria = "3";
			break;
		case "Berlina":
			thisCarrozzeria = "2";
			break;
		case "Monovolume":
			thisCarrozzeria = "4";
			break;
		case "Furgoni/Van":
			thisCarrozzeria = "8";
			break;
		case "Altro":
			thisCarrozzeria = "1";
			break;
		default:
			thisCarrozzeria = "1";
			break;
		}

		String thisColoreEsterno;
		switch (scheda.coloreEsternoVeicolo) {
		case "Arancione":
			thisColoreEsterno = "1";
			break;
		case "Argento":
			thisColoreEsterno = "2";
			break;
		case "Beige":
			thisColoreEsterno = "3";
			break;
		case "Bianco":
			thisColoreEsterno = "4";
			break;
		case "Blu/Azzurro":
			thisColoreEsterno = "5";
			break;
		case "Bronzo":
			thisColoreEsterno = "6";
			break;
		case "Giallo":
			thisColoreEsterno = "7";
			break;
		case "Grigio":
			thisColoreEsterno = "8";
			break;
		case "Lilla":
			thisColoreEsterno = "9";
			break;
		case "Marrone":
			thisColoreEsterno = "10";
			break;
		case "Nero":
			thisColoreEsterno = "11";
			break;
		case "Oro":
			thisColoreEsterno = "12";
			break;
		case "Rosso":
			thisColoreEsterno = "13";
			break;
		case "Verde":
			thisColoreEsterno = "14";
			break;
		default:
			thisColoreEsterno = "5";
			break;
		}

		queryString = "?v=101";
		queryString += "&idutente=" + IDUTENTE;
		queryString += "&Marca=" + URLEncoder.encode(scheda.marcaVeicolo, "UTF-8");
		queryString += "&Modello=" + URLEncoder.encode(scheda.modelloVeicolo, "UTF-8");
		queryString += "&Versione=" + URLEncoder.encode(scheda.versioneVeicolo, "UTF-8");
		queryString += "&Descrizione=" + URLEncoder.encode(scheda.descrizioneVeicolo.replace("'", "''"), "UTF-8");
		queryString += "&MeseImmatricolazione=" + (scheda.meseImmatricolazioneVeicoloIndex-1);
		queryString += "&AnnoImmatricolazione=" + (scheda.annoImmatricolazioneVeicoloIndex==1?"0":scheda.annoImmatricolazioneVeicolo);
		queryString += "&idCarburante=" + thisCarburante;
		queryString += "&idTipologia=" + thisTipologia;
		queryString += "&idCarrozzeria=" + thisCarrozzeria;
		queryString += "&PostiASedere=" + (scheda.postiASedereVeicoloIndex==0?"0":scheda.postiASedereVeicoloIndex);
		queryString += "&PotenzaKW=" + scheda.KWVeicolo;
		queryString += "&PotenzaCV=" + scheda.CVVeicolo;
		queryString += "&idColoreEsterno=" + thisColoreEsterno;
		queryString += "&Metallizzato=" + (scheda.coloreMetalizzato?"1":"0");
		queryString += "&PrecedentiProprietari=" + (scheda.numeroPrecedentiProprietariVeicoloIndex==0?"0":scheda.postiASedereVeicoloIndex);
		queryString += "&Chilometraggio=" + URLEncoder.encode(scheda.chilometraggioVeicolo, "UTF-8");
		queryString += "&Prezzo=" + URLEncoder.encode(scheda.prezzoVeicolo, "UTF-8");
		queryString += "&PrezzoConcessionari=" + URLEncoder.encode(scheda.prezzoVeicoloCondivisione, "UTF-8");
		queryString += "&condividiveicolo=" + "1"; //Se lo inserisce tramite j2web, lo vuole condividere...
		//queryString += "&Trattabile=" + (scheda.prezzoTrattabile?"1":"0");
		queryString += "&Contratto=" + URLEncoder.encode(scheda.tipologiaContrattoVeicolo, "UTF-8");
		queryString += "&FinitureInterni=" + (scheda.finitureInterneVeicoloIndex==0?"ND":URLEncoder.encode(scheda.finitureInterneVeicolo, "UTF-8"));
		queryString += "&ColoreInterni=" + (scheda.coloreInterniVeicoloIndex==0?"ND":URLEncoder.encode(scheda.coloreInterniVeicolo, "UTF-8"));
		queryString += "&ABS=" + (scheda.disponibilitaABS?"1":"0");
		queryString += "&Airbag=" + (scheda.disponibilitaAirBag?"1":"0");
		queryString += "&Antifurto=" + (scheda.disponibilitaAntifurto?"1":"0");
		queryString += "&ChiusuraCentralizzata=" + (scheda.disponibilitaChiusuraCentralizzata?"1":"0");
		queryString += "&ControlloAutomTrazione=" + (scheda.disponibilitaContrlAutomTrazione?"1":"0");
		queryString += "&ESP=" + (scheda.disponibilitaESP?"1":"0");
		queryString += "&Immobilizer=" + (scheda.disponibilitaImmobilizer?"1":"0");
		queryString += "&FreniADisco=" + (scheda.disponibilitaFreniADisco?"1":"0");
		queryString += "&AlzacristalliElettrici=" + (scheda.disponibilitaAlzacristalliElettrici?"1":"0");
		queryString += "&Clima=" + (scheda.disponibilitaClima?"1":"0");
		queryString += "&NavigatoreSatellitare=" + (scheda.disponibilitaNavigatoreSatellitare?"1":"0");
		queryString += "&RadioCD=" + (scheda.disponibilitaRadioOLettoreCD?"1":"0");
		queryString += "&ParkDistControl=" + (scheda.disponibilitaParkDistControl?"1":"0");
		queryString += "&SediliRiscaldati=" + (scheda.disponibilitaSediliRiscaldati?"1":"0");
		queryString += "&Servosterzo=" + (scheda.disponibilitaServoSterzo?"1":"0");
		queryString += "&VolanteMultifunzione=" + (scheda.disponibilitaVolanteMultifunzione?"1":"0");
		queryString += "&Handicap=" + (scheda.disponibilitaAllestimentoHandicap?"1":"0");
		queryString += "&CerchiInLega=" + (scheda.disponibilitaCerchiInLega?"1":"0");
		queryString += "&GancioTraino=" + (scheda.disponibilitaGancioTraino?"1":"0");
		queryString += "&Portapacchi=" + (scheda.disponibilitaPortaPacchi?"1":"0");
		queryString += "&SediliSportivi=" + (scheda.disponibilitaSediliSportivi?"1":"0");
		queryString += "&Motore=" + (scheda.tipologiaMotoreVeicoloIndex==0?"ND":URLEncoder.encode(scheda.tipologiaMotoreVeicolo, "UTF-8"));
		queryString += "&Cambio=" + (scheda.tipologiaCambioVeicoloIndex==0?"ND":URLEncoder.encode(scheda.tipologiaCambioVeicolo, "UTF-8"));
		queryString += "&NumRapporti=" + (scheda.numeroRapportiVeicoloIndex==0?"0":scheda.numeroRapportiVeicoloIndex-2);
		queryString += "&Cilindrata=" + (scheda.cilindrataVeicolo==""?"0":scheda.cilindrataVeicolo);
		queryString += "&ClasseEmissione=" + (scheda.classeEmissioniVeicoloIndex==0?"ND":URLEncoder.encode(scheda.classeEmissioniVeicolo, "UTF-8"));
		queryString += "&ConsumoMedio=" + (scheda.consumoMedioVeicolo==""?"0.0":URLEncoder.encode(scheda.consumoMedioVeicolo, "UTF-8"));
		queryString += "&Immagine1=" + Immagine1;
		queryString += "&Immagine2=" + Immagine2;
		queryString += "&Immagine3=" + Immagine3;
		queryString += "&Immagine4=" + Immagine4;
		queryString += "&Immagine5=" + Immagine5;
		queryString += "&Immagine6=" + Immagine6;
		queryString += "&Immagine7=" + Immagine7;
		queryString += "&Immagine8=" + Immagine8;
		queryString += "&Immagine9=" + Immagine9;
		queryString += "&Immagine10=" + Immagine10;
		queryString += "&UrlYT=" + (scheda.urlVideoYouTube==""?"ND":URLEncoder.encode(scheda.urlVideoYouTube, "UTF-8"));
		queryString += "&RagioneSociale=" + URLEncoder.encode(scheda.ragioneSociale, "UTF-8");
		queryString += "&Indirizzo=" + URLEncoder.encode(scheda.Indirizzo, "UTF-8");
		queryString += "&TelefonoGenerico=" + URLEncoder.encode(scheda.Telefono, "UTF-8");
		queryString += "&NomeReferente=" + URLEncoder.encode(scheda.nomeReferente, "UTF-8");
		queryString += "&TelefonoReferente=" + URLEncoder.encode(scheda.TelefonoReferente, "UTF-8");
		queryString += "&EmailReferente=" + URLEncoder.encode(scheda.emailReferente, "UTF-8");
		queryString += "&IdScheda=" + scheda.codiceScheda;

		//Invio la richiesta al server remoto per l'inserzione dei dati veicolo nel DB
		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
		try {
			getInfoVeicolo.get("GET", urlTunnelDBNekta + queryString, null, null, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Query inviata: " + queryString);
		
		//Invio la richiesta al server remoto per la verifica dell'inserimento
		queryString = "?v=203";
		queryString += "&codiceScheda=" + scheda.codiceScheda;
		HttpPortalGetConnection verificaInserimentoVeicolo = new HttpPortalGetConnection();
		try {
			Object[] response = verificaInserimentoVeicolo.get("GET", urlTunnelDBNekta + queryString, null, null, true);
			String responseBody = (String)response[1];
			JSONObject json = null;
			json = new JSONObject(responseBody);

			if(!json.get("affectedrows").equals("0")) {

				System.out.println("Inserita in: " + "PORTALE MLS");  

				inserimentoOK = true;
				
				//Aggiorna la lista dei portali in cui è inserita la scheda
				scheda.aggiungiInserimentoPortale(idPortale, scheda.codiceScheda);
				
				//Aggiorna i pulsanti del pannello sincronizzazione
				PanelSicronizzazioneConPortali.updatePanello(scheda, false);
				
				//Mail e messaggio informativo OK
				if(!modifica) {
					sendConfirmationMail(scheda, "PORTALE MLS", scheda.codiceScheda);

					messageInserimentoOK("PORTALE MLS");
				}	
				else {
					messageModificaOK("PORTALE MLS");
				}

			}
			//Mail e messaggio informativo KO
			else {
				inserimentoOK = false;

				if(!modifica) {
					messageInserimentoKO("PORTALE MLS");
				}
				else {
					messageModificaKO("PORTALE MLS");
				}
				
				return inserimentoOK;
				
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Query inviata: " + queryString);


		//Tracking dell'evento inserzione di una scheda veicolo in MLS
		System.out.print("Tracking dell'evento inserzione di una scheda veicolo in MLS...");
		try {
			j2web.trackEvent("inserimentoMLSSchedaVeicolo_j2web_" + j2web_version + "_" + EMAIL_UTENTE, scheda.codiceScheda);
		} catch (IOException e) {
		}
		System.out.print(" fatto." + "\n");

		modifica = false;
		
		return inserimentoOK;    
	}


	//Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaVeicolo scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.idScheda + "...");

		//JOptionPane.showMessageDialog(null, "Attualmente il portale non supporta questa funzionalità", "Richiesta non eseguibile", JOptionPane.INFORMATION_MESSAGE);
		//Apro il browser		
			try {
			String url = URLROOT;
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);
	
		} catch (IOException e ) {
			//Eventualità non gestita
		}

		return true;
	}


	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
		
		//La scheda è da aggiornare
		if(scheda.isOnThisPortal(idPortale)) {
			modifica = true;
		}
		
		System.out.println("Eliminazione scheda: " + scheda.idScheda + "...");

		String queryString = "";
		
		queryString = "?v=305";
		queryString += "&codiceScheda=" + scheda.codiceScheda;

		//Invio la richiesta al server remoto
		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
		try {
			getInfoVeicolo.get("GET", urlTunnelDBNekta + queryString, null, null, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Query inviata: " + queryString);

		//Invio la richiesta al server remoto per la verifica dell'eliminazione
		queryString = "?v=203";
		queryString += "&codiceScheda=" + scheda.codiceScheda;
		HttpPortalGetConnection verificaEliminazioneVeicolo = new HttpPortalGetConnection();
		try {
			Object[] response = verificaEliminazioneVeicolo.get("GET", urlTunnelDBNekta + queryString, null, null, true);
			String responseBody = (String)response[1];
			JSONObject json = null;
			try {
				json = new JSONObject(responseBody);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(json.get("affectedrows").equals("0")) {
				eliminazioneOK = true;

				//Aggiorno la lista dei portali in cui è presenta la scheda corrente
				scheda.eliminaInserimentoPortale(idPortale);

				//Aggiorna i pulsanti del pannello sincronizzazione
				PanelSicronizzazioneConPortali.updatePanello(scheda, false);

				//Stampo a video un messaggio informativo
				if(!modifica) {
					messageEliminazioneOK("PORTALE MLS");
				}
			}
			else {
				eliminazioneOK = false;

				//Stampo a video un messaggio informativo
				messageEliminazioneKO("PORTALE MLS");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		//Tracking dell'evento eliminazione di una scheda veicolo in MLS
		System.out.print("Tracking dell'evento eliminazione di una scheda veicolo in MLS...");
		try {
			j2web.trackEvent("eliminazioneMLSSchedaVeicolo_j2web_" + j2web_version + "_" + EMAIL_UTENTE, scheda.codiceScheda);
		} catch (IOException e) {
		}
		System.out.print(" fatto." + "\n");

		return eliminazioneOK;

	}


}