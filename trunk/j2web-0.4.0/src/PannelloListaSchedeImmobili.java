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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


//Pannello per la gestione delle schede immobile create dall'utente
public class PannelloListaSchedeImmobili extends JPanel implements parametriGenerali {
	private static final long serialVersionUID = 1L;
	
	public ButtonGroup radioGrpSchede = new ButtonGroup();	//Serve per raggruppare i radio button in una struttura coerente 

	//Costruttore del pannello
	@SuppressWarnings("unchecked")
	public PannelloListaSchedeImmobili() {
		
		setBorder(new TitledBorder(null, "Lista schede create", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        //Lettura schede dal file .dat 
        File file = new File(datFilePath);
    	if(file.exists()) {
    		System.out.println("File .dat trovato.");
    		if(file.length()!=0) {
    			try {
    				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
    				j2web_GUI.listSchedeImmobile = (LinkedList<SchedaImmobile>)inputFile.readObject();
    				inputFile.close();
    			} catch (FileNotFoundException e) {
    				JOptionPane.showMessageDialog(null, "File .dat non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
    				e.printStackTrace();
    			} catch (IOException e) {
    				JOptionPane.showMessageDialog(null, "Impossibile accedere al file .dat: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
    				e.printStackTrace();
    			} catch (ClassNotFoundException e) {
    				JOptionPane.showMessageDialog(null, "Errore generico", "Errore", JOptionPane.ERROR_MESSAGE);
    			}   
    		}   				
    	}
    	else {
    		System.out.println("File .dat non trovato.");
    	}
    	
    	//Aggiorno il pannello con il contenuto della LinkedList che contiene le schede immobile
    	//Pannello senza schede   	
    	if(j2web_GUI.listSchedeImmobile.isEmpty()) {
    		System.out.println("listSchedeImmobile vuota");
    		JPanel panelNessunaScheda = new JPanel();
    		add(Box.createVerticalStrut(48));
            JLabel lblNessunaScheda = new JLabel("Non \u00e8 stata inserita alcuna scheda immobile");                
            panelNessunaScheda.add(lblNessunaScheda);
            add(panelNessunaScheda);
    	}
    	//Pannello con schede
    	else {
    		System.out.println("listSchedeImmobile con schede");
            add(Box.createVerticalStrut(48));
    		ListIterator<SchedaImmobile> iterator = j2web_GUI.listSchedeImmobile.listIterator();
        	while(iterator.hasNext()) {
        		SchedaImmobile schedaCorrente = (SchedaImmobile)iterator.next();
        		//I sottopannelli sono istanze di una classe comune definita più sotto
        		JPanel pannelloSchedaImmobile = new PannelloSchedaImmobile(schedaCorrente, j2web_GUI.listSchedeImmobile, radioGrpSchede);            
        		add(pannelloSchedaImmobile);
        		add(Box.createVerticalStrut(5));
        	}
    	}
    }	//Fine costruttore PannelloSchedeImmobili
        
    
    //Il pannello viene aggiornato ogni volta che una scheda viene creata o cancellata, senza parametri formali il metodo riporta il pannello nella sua forma originaria 
    public void updatePanello() { 

    	//Rimuovo tutti i pannelli attualmente presenti
    	removeAll();
    	
    	//Aggiorno il pannello con il contenuto della LinkedList che contiene le schede immobile
    	//Pannello senza schede
    	if(j2web_GUI.listSchedeImmobile.isEmpty()) {
    		System.out.println("Linkedlist vuota");
    		JPanel panelNessunaScheda = new JPanel();
    		add(Box.createVerticalStrut(48));
            JLabel lblNessunaScheda = new JLabel("Non \u00e8 stata inserita alcuna scheda immobile");                
            panelNessunaScheda.add(lblNessunaScheda);
            add(panelNessunaScheda);
    	}
    	//Pannello con schede
    	else {
    		System.out.println("Linkedlist con schede");
            add(Box.createVerticalStrut(48));
    		ListIterator<SchedaImmobile> iterator = j2web_GUI.listSchedeImmobile.listIterator();
        	while(iterator.hasNext()) {
        		SchedaImmobile schedaCorrente = (SchedaImmobile)iterator.next();
        		//I sottopannelli sono istanze di una classe comune definita più sotto
        		JPanel pannelloSchedaImmobile = new PannelloSchedaImmobile(schedaCorrente, j2web_GUI.listSchedeImmobile, radioGrpSchede);            
        		add(pannelloSchedaImmobile);
        		add(Box.createVerticalStrut(5));
        	}
    	}

		updateUI();		
    }

}   //Fine PannelloInserimento



//Questa classe definisce tutti i sottopannelli presenti quando esistono schede immobile
class PannelloSchedaImmobile extends JPanel implements parametriGenerali {  
	private static final long serialVersionUID = 1L;
	
	SchedaImmobile scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	
	 public PannelloSchedaImmobile(final SchedaImmobile scheda, final LinkedList<SchedaImmobile> listaSchedeImmobile, final ButtonGroup radioGrpSchede) { // l'attributo final è stato suggerito dal debugger, da rivedere...
		 this.scheda = scheda;
		 idScheda = scheda.idScheda;
		 codiceScheda = scheda.codiceInserzione;
		 
		 setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		 setBorder(new LineBorder(new Color(184,208,229)));
		 	
		 //Radio button dei sottopannelli
		 JRadioButton schedaRadio = new JRadioButton();
		 radioGrpSchede.add(schedaRadio); //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 schedaRadio.addActionListener(new ActionListener() {
			 //Clicco su una radio button di una scheda
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Scheda selezionata: " + scheda.codiceInserzione); 
                 
                 //Devo caricare la relativa hashtable contenente i portali in cui è inserita e glòi associati codici di inserimento
                 scheda.caricaMappaPortaliOspitanti();
                 
                 //Il pannello di destra (inserimento) deve essere aggiornato
                 j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda);
                 
                 //Il pannello Form deve mostrari i dati relativi a tale scheda
                 j2web_GUI.panelCreazioneSchedeImmobile.mostraSchedaSalvata(scheda);
             }
		 });
		 add(schedaRadio);
		 
		 //La label delle schede
		 String labelScheda = " " + scheda.codiceInserzione + " - " + scheda.titoloAnnuncio + " - " + scheda.provincia + " - " + scheda.comune + " - " + scheda.testoAnnuncio;
		 if(labelScheda.length()>30) {
			 labelScheda = labelScheda.substring(0, 30); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 12);
		 label.setFont(font);
		 add(label);
		 		 
		 add(new JLabel("  "));
		 
		 //Pulsante "Cancella"
		 btnCancellaScheda = new JButton("Cancella");
		 btnCancellaScheda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Premuto CANCELLA SCHEDA: " + scheda.codiceInserzione);
                
                //Rimozione di una scheda immobile dalla LinkedList
                ListIterator<SchedaImmobile> iterator = listaSchedeImmobile.listIterator();
            	while(iterator.hasNext()) {
            		SchedaImmobile schedaCorrente = (SchedaImmobile)iterator.next();
            		//La rimozione avviene confrontando l'id univoco della scheda immobile
            		if(schedaCorrente.idScheda==idScheda) {
            			iterator.remove();
            			System.out.println("Scheda rimossa dalla linkedlist");
            		}
            	}
            	//Aggiorno il file dat relativo alle schede immobile
            	try {
         		   File file = new File(datFilePath);
         	    	if(file.exists()) {
         	    		System.out.println("File .dat trovato.");
         	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
						outputFile.writeObject(j2web_GUI.listSchedeImmobile);
						outputFile.close();
						System.out.println("File .dat aggiornato");
         	    	}
         	    	else {
         	    		System.out.println("File non .dat trovato.");
         	    	}
				
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "File .dat non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Impossibile accedere al file .dat: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
            	
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
         
         add(new JLabel(" "));        
	 }
	
}