/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.http.NameValuePair;


/**
 *
 * @author marco
 */

//La classe principale
public class _autoscout24It extends PortaleWeb {     

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

    
	//Costruttore
	public _autoscout24It (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {		
		
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
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential)	throws HttpCommunicationException, UnsupportedEncodingException {
		JOptionPane.showMessageDialog(null, "Funzionalità non ancora sviluppata", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}


	@Override
	public boolean visualizzaScheda(SchedaVeicolo scheda) throws HttpCommunicationException {
		JOptionPane.showMessageDialog(null, "Funzionalità non ancora sviluppata", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}


	@Override
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
		JOptionPane.showMessageDialog(null, "Funzionalità non ancora sviluppata", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
	
	
}