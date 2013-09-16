/*
 * Il pannello centrale, che contiene la lista delle schede create
 * Ultima modifica: 12 feb 2012
 *
 */

/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


//Pannello per la gestione delle schede immobile create dall'utente
public class PanelListaSchedeImmobili extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	String labelNessunaScheda ="Non \u00e8 stata inserita alcuna scheda immobile";
	
	//Serve per raggruppare i radio button in una struttura coerente
	public ButtonGroup radioGrpSchede = new ButtonGroup();	 

	//Costruttore del pannello
	public PanelListaSchedeImmobili() {
		
		setBorder(new TitledBorder(null, "Lista schede create", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
    	//Aggiorno il pannello con il contenuto della LinkedList che contiene le schede immobile
		popolaPannelloListaSchedeImmobile();
    	
    }	//Fine costruttore PannelloSchedeImmobili
        
    
    //Il pannello viene aggiornato ogni volta che una scheda viene creata o cancellata
    public void updatePanello() { 

    	//Rimuovo tutti i pannelli attualmente presenti
    	removeAll();
    	    	
    	//Aggiorno il pannello con il contenuto della LinkedList che contiene le schede immobile 
    	popolaPannelloListaSchedeImmobile();

		updateUI();		
    }
    
    public void popolaPannelloListaSchedeImmobile() {  	
    	//Aggiorno il pannello con il contenuto della LinkedList che contiene le schede immobile  	
    	add(Box.createVerticalStrut(7));
    	//Pannello senza schede
    	if(j2web_GUI.listSchedeImmobile.isEmpty()) {
    		System.out.println("La lista delle schede immobili è vuota.");
    		JPanel panelNessunaScheda = new JPanel();
            JLabel lblNessunaScheda = new JLabel(labelNessunaScheda);                
            panelNessunaScheda.add(lblNessunaScheda);
            add(panelNessunaScheda);
    	}
    	//Pannello con schede
    	else {
    		System.out.println("La lista delle schede immobili contiene delle schede.");
    		ListIterator<SchedaVeicolo> iterator = J2Web_UI.listSchedeVeicolo.listIterator();
        	while(iterator.hasNext()) {
        		SchedaVeicolo schedaCorrente = (SchedaVeicolo)iterator.next();
        		//I sottopannelli sono istanze di una classe comune definita più sotto
        		JPanel pannelloSchedaImmobile = new PannelloSchedaImmobile(schedaCorrente, j2web_GUI.listSchedeImmobile, radioGrpSchede);            
        		add(pannelloSchedaImmobile);
        		add(Box.createVerticalStrut(5));
        	}
    	}   	
    }

}   //Fine PannelloInserimento



//Questa classe definisce tutti i sottopannelli schede immobile
class PannelloSchedaImmobile extends JPanel {  
	
	private static final long serialVersionUID = 1L;
	
	SchedaVeicolo scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PannelloSchedaImmobile(final SchedaVeicolo scheda, final LinkedList<SchedaVeicolo> listaSchedeImmobile, final ButtonGroup radioGrpSchede) {
		 this.scheda = scheda;
		 idScheda = scheda.idScheda;
		 codiceScheda = scheda.codiceScheda;
		 
		 setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		 setBorder(new LineBorder(new Color(184,208,229)));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton();
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Scheda selezionata: " + scheda.codiceScheda); 
                 
                 //Devo caricare la relativa hashtable contenente i portali in cui è inserita e gli associati codici di inserimento
                 //scheda.caricaTabellaHash();
                 
                 //Il pannello di destra (inserimento) deve essere aggiornato
                 j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
                 
                 //Il pannello Form deve mostrari i dati relativi a tale scheda
                 //j2web_GUI.panelCreazioneSchedeImmobile.mostraSchedaSalvata(scheda);
             }
		 });
		 add(schedaRadio);
		 
		 //La label delle schede
		 String labelScheda = scheda.codiceScheda + "-" + scheda.codiceScheda + "-" + "-";
		 if(labelScheda.length()>31) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 30); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 add(label);
		 		 
		 add(new JLabel(labelSpaziatore));
		 
		 //Pulsante "Cancella"
		 btnCancellaScheda = new JButton("Cancella");
		 btnCancellaScheda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancella scheda: " + scheda.codiceScheda);
                
                //Rimozione di una scheda immobile dalla LinkedList
                ListIterator<SchedaVeicolo> iterator = listaSchedeImmobile.listIterator();
            	while(iterator.hasNext()) {
            		SchedaVeicolo schedaCorrente = (SchedaVeicolo)iterator.next();
            		//La rimozione avviene confrontando l'id univoco della scheda immobile
            		if(schedaCorrente.idScheda==idScheda) {
            			iterator.remove();
            			System.out.println("Scheda rimossa dalla linkedlist");
            		}
            	}
            	
            	//Aggiorno il file dat delle schede
            	j2web.salvaListaSchedeImmobiliCreate();
            	          	
            	//Eliminazione del file dat con la hashtable
            	File removeFile = new File("./schede/" + codiceScheda + "-" + idScheda + ".dat");
            	System.out.println("File da rimuovere: " + removeFile.getName());
            	if(removeFile.exists()) {
            		removeFile.setWritable(true);
            		boolean rimosso = removeFile.delete();
            		System.out.println("File rimosso: " + removeFile.getName() + " " + rimosso);
            	}
            	else {
            		System.out.println("File non esistente: " + removeFile.getName());
            	}
            	            	
            	//Aggiornamento del pannello centrale, la scheda corrente è stata cancellata
            	j2web_GUI.panelListaSchedeImmobile.updatePanello();
            	
            	//Aggiornamento del pannello di destra, la scheda corrente è stata cancellata
            	j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello();
            }
         });
         add(btnCancellaScheda);
	 }
	
}