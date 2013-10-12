/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;


/**
 *
 * @author marco
 */

//La classe principale
public class _immobiliareIt extends PortaleWeb {     

    //Variabili generali
	private final String NOMEPORTALE = "immobiliare.it";
	private final String URLROOT = "http://getrix.ekbl.net";	//immobiliare.it si appoggia a Getrix
	private final String URLROOT_2 = "http://media.getrix.ekbl.net";	//la root per le immagini è differente
	private final String USERNAME = "nfjrpnqp@sharklasers.com";
    private final String PASSWORD = "ts2cj1n3";
    	
    //private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
    private String codiceInserzione;
    private boolean inserimentoOK = false;
    private String location;
    private boolean debugMode = true;
    
    /*private String nomeImmagine0;
    private String nomeImmagine1;
    private String nomeImmagine2;
    private String nomeImmagine3;
    private String nomeImmagine4;
    private String nomeImmagine5;
    private String nomeImmagine6;
    private String nomeImmagine7;
    private String nomeImmagine8;
    private String nomeImmagine9;*/

    //Mappa dei parametri da inviare
    Map<String,String> mappaDeiParametri;
    
    //Lista dei parametri inviati in una singola connessione
    List<NameValuePair> postParameters;
    
    //Lista degli headers inviati in una singola connessione
    List<NameValuePair> requestHeaders; 

    //La scheda immobile su cui si lavora
    SchedaVeicolo scheda;
    
    //Lista di alcuni (non necessariamente tutti) parametri inviati
    String accedi;
    String backurl;
    String openTab;
    String password;
    String username;
    String categoria;
    String tipo;
    String fasciaprezzo;
    String gtxNumeroCamereDaLetto;
    String gtxAnnoCostruzione;
    String codice;
    String prezzo;
    String gtxNumeroSoggiornoSalotto;
    String piano;
    String tipologia;
    String sottotipologia;
    String locali;
    String gtxNumeroAltreCamereStanze;
    String numeroPiani;
    String stato;
    String superficie;
    String gtxNumeroPostiAuto;
    String tipoProprieta;
    String tipoMandato;
    String gtxIdContatto;
    String idCont;
    String classeImmobile;
    String terreno_proprieta;
    String gtxScadenzaMandato;
    String spese_condominiali;
    String in_asta;
    String a_reddito;
    String libero;
    String spese;
    String classe_energetica;
    String ipe;
    String ipe_unita_misura;
    String hidden_ipe;
    String hidden_default_mq;
    String hidden_default_mc;
    String costruttore;
    String statoCantiere;
    String riscaldamento;
    String cucina;
    String boxauto;
    String bagni;
    String idGiardino;
    String idArredamento;
    String gtxIngresso;
    String gtxRipostiglio;
    String gtxCantina;
    String gtxMansarda;
    String gtxTaverna; 
    String gtxInfissiEsterni; 
    String gtxImpiantoTv; 
    String gtxFibraOttica;
    String ascensore;
    String gtxImpiantoAllarme;
    String gtxCancelloElettrico;
    String flag_auto_it; 
    String descrizione_it; 
    String flag_auto_en; 
    String descrizione_en; 
    String flag_auto_de; 
    String descrizione_de; 
    String flag_auto_fr; 
    String descrizione_fr; 
    String flag_auto_es; 
    String descrizione_es; 
    String flag_auto_pt; 
    String descrizione_pt; 
    String flag_auto_ru; 
    String descrizione_ru; 
    String flag_auto_gr; 
    String descrizione_gr; 
    String remLen_it;
    String remLen_en;  
    String remLen_de;  
    String remLen_fr;  
    String remLen_es;  
    String remLen_pt;  
    String remLen_ru;  
    String remLen_gr;  
    String gtxTitolo_per_riviste; 
    String gtxDescrizione_per_riviste; 
    String idNaz;
    String maxRes;
    String s;
    String showNation;
    String callback;
    String _textComune;
    String action;
    String cap;
    String com_latitudine;
    String com_longitudine;
    String confirm_address;
    String flagIndirizzo;
    String flagIndirizzo_fake;
    String idAnnuncio;
    String idComune;
    String idLocalita;
    String idMacrozona;
    String idMicrozona;
    String idNazione;
    String idProvincia;
    String idRegione;
    String indirizzo;
    String indirizzo_fake;
    String indirizzo_fake_orig;
    String latitudine;
    String longitudine;
    String nextStep;
    String num_localita;
    String numeroCivico;
    String planimetria;
    String step;
    String textComune;
    String textNazione;
    String textProvincia;
    String textRegione;
    String virtual_tour;
    String zonaobbligatoria;
    String zoom;  
    String SelTipoPagamento;
    String address1;
    String amount;
    String azionePayPal;
    String bn;
    String business;
    String check_1;
    String check_108;
    String cmd;
    String currency_code;
    String first_name;
    String item_name;
    String item_number;
    String last_name;
    String lc;
    String no_note;
    String no_shipping;
    String notify_url;
    String prezzoTotale;
    String _return;
    String return_cancel;
    String richiesta;
    String zip;   
    
    
	//Costruttore
	public _immobiliareIt (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(urlIcona, valoreLabel, idPortale, isActive);
		
		//Inizializzo il cookie di sessione 
		SESSIONCOOKIENAME = "GETRIXSID";
		SESSIONCOOKIEDOMAIN = ".getrix.ekbl.net";
		
		//Inizializzo la mappa dei parametri
		mappaDeiParametri =  new Hashtable<String,String>();
	    
		//Inizializzo la lista che conterrà i parametri da inviare
	    postParameters = new ArrayList<NameValuePair>();
	    
	    //Inizializzo la lista che conterrà gli headers
	    requestHeaders = new ArrayList<NameValuePair>();
	
	}

	
	
	//Metodo per la valutazione dei parametri
	public void inizializzaParametri() throws HttpCommunicationException {
		
	}


	@Override
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential)
			throws HttpCommunicationException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean visualizzaScheda(SchedaVeicolo scheda)
			throws HttpCommunicationException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential)
			throws HttpCommunicationException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}