//Questa classe definisce tutti i sottopannelli schede veicolo

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComponent;


class PanelSchedaVeicolo extends JPanel {   
	
	JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
	private static final long serialVersionUID = 1L;
	
	SchedaVeicolo scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaVeicolo(final SchedaVeicolo scheda, final LinkedList<SchedaVeicolo> listaSchedeVeicolo, final ButtonGroup radioGrpSchede) {
		 this.scheda = scheda;
		 idScheda = scheda.idScheda;
		 codiceScheda = scheda.codiceScheda;
		 
		 setLayout(new BorderLayout(0, 0));
		 setBorder(new LineBorder(Color.LIGHT_GRAY));
		 setMaximumSize(new Dimension(400, 130));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton("Seleziona scheda");
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
           public void actionPerformed(ActionEvent e) {
               System.out.println("Scheda selezionata: " + scheda.marcaVeicolo); 
               
               //Devo caricare la relativa hashtable contenente i portali in cui è inserita e gli associati codici di inserimento
               scheda.caricaTabellaHash();
               
               //Il pannello di destra (inserimento) deve essere aggiornato
               J2Web_UI.panelInserimentoInActiveMode(pannelloListaPortali, scheda, true);
               
               Component[] wrapperScheda = getParent().getComponents();
               for(int i=0; i<wrapperScheda.length; i++) {
            	   if(wrapperScheda[i].getClass().toString().contains("PanelSchedaVeicolo"))  {
            		   ((JComponent) wrapperScheda[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
            	   }
               }
               
               setBorder(new LineBorder(Color.ORANGE));
               
               matchVeicoloCliente(scheda);
           }
		 });
		 add(schedaRadio, BorderLayout.NORTH);
		 
		 //La label delle schede
		 String labelScheda = scheda.marcaVeicolo + "-" + scheda.modelloVeicolo + "-" + scheda.versioneVeicolo + "-" + scheda.carrozzeriaVeicolo + "-" + scheda.coloreEsternoVeicolo + "-" + scheda.descrizioneVeicolo;
		 if(labelScheda.length()>31) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 30); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.LEFT);
		 //System.out.println("test: " + scheda.imgFile1);
		 
		 BufferedImage imgtest = null;
		 if(scheda.arrayImages[1]!=null) {
			 try {
					imgtest = ImageIO.read(scheda.arrayImages[1]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Image resizedimg = imgtest.getScaledInstance(70, 50, Image.SCALE_FAST);          
	         
			 label.setIcon(new ImageIcon(resizedimg));
		 }
		
		 
		 
		 add(label, BorderLayout.CENTER);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
		 JPanel panel_26 = new JPanel();
		 panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 add(panel_26, BorderLayout.SOUTH);
		 panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
		 //Pulsante "Cancella"
		 btnCancellaScheda = new JButton("Cancella");
		 btnCancellaScheda.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              System.out.println("Cancella scheda veicolo: " + scheda.marcaVeicolo);
              
              //Rimozione di una scheda immobile dalla LinkedList
              ListIterator<SchedaVeicolo> iterator = listaSchedeVeicolo.listIterator();
          		while(iterator.hasNext()) {
	          		SchedaVeicolo schedaCorrente = iterator.next();
	          		//La rimozione avviene confrontando l'id univoco della scheda immobile
	          		if(schedaCorrente.idScheda==idScheda) {
	          			iterator.remove();
	          			System.out.println("Scheda veicolo rimossa dalla linkedlist");
	          		}
          		}
          	
	          	//Aggiorno il file dat delle schede
	          	j2web.salvaListaSchedeVeicoloCreate();
          	          	
	          	//Eliminazione del file dat con la hashtable
	          	File removeFile = new File("./schede/" + idScheda + "-" + idScheda + ".dat");
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
	          	//j2web_GUI.panelListaSchedeImmobile.updatePanello();
	          	J2Web_UI.aggiornaPannelloListaSchedeVeicolo();
          	
	          	//Aggiornamento del pannello di destra, la scheda corrente è stata cancellata
	          	//j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello();
	          	J2Web_UI.aggiornaPannelloListaPortaliSincronizzazione();
          	}
		 });
		 panel_26.add(btnCancellaScheda);
       
       //add(new JLabel(labelSpaziatore));
		//Component horizontalGlue = Box.createHorizontalGlue();
		//panel_26.add(horizontalGlue);
       
       
     //Pulsante "Esporta"
	 /*btnEsportaScheda = new JButton("Esporta in XML");
	 btnEsportaScheda.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Esporta scheda: " + scheda.marcaVeicolo);
        
    	            	
    }
 });
	 panel_26.add(btnEsportaScheda);*/
	 }
	
	 private void  matchVeicoloCliente(SchedaVeicolo schedaVeicolo) {
		 
		 JPanel pannelloMatchVeicoloCliente = J2Web_UI.getPanel_4();
		 
		 J2Web_UI.listSchedeClientiMatch.clear();
			
		 pannelloMatchVeicoloCliente.removeAll();
		 
		 //pannelloMatchVeicoloCliente.updateUI();
			
			//pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(7));
		 
		 boolean matchPositivo = false;
		 
		 
			ListIterator<SchedaCliente> iterator = J2Web_UI.listSchedeCliente.listIterator();
	     	while(iterator.hasNext()) {
	     		SchedaCliente schedaCorrente = iterator.next();
	     		System.out.println("test: " + schedaVeicolo.modelloVeicolo + schedaCorrente.modelloVeicoloCliente);
	     		if(schedaVeicolo.modelloVeicolo.equalsIgnoreCase(schedaCorrente.modelloVeicoloCliente)) {
	     			J2Web_UI.listSchedeClientiMatch.add(schedaCorrente);
	     		}
	     		
	     	}
	     	if(!J2Web_UI.listSchedeClientiMatch.isEmpty()){	
	     		matchPositivo = true;
	     	}
			
			
			if(!matchPositivo) {
				//Match negativo
				System.out.println("Non è stato trovato alcun cliente interessato al veicolo.");
	    		JPanel panelNessunMatch = new JPanel();
	            JLabel lblNessunMatch = new JLabel("Non è stato trovato alcun cliente interessato al veicolo.");                
	            panelNessunMatch.add(lblNessunMatch);
	            pannelloMatchVeicoloCliente.add(panelNessunMatch);
	    	}    	
	    	else {
	    		//Match positivo
	    		System.out.println("Trovati potenziali clienti  interessati al veicolo");
	    		
	    		String[][] matrix = new String[J2Web_UI.listSchedeClientiMatch.size()][5];
	    		
	    		for (int row = 0; row < matrix.length; row++) {
	    	       // for (int column = 0; column < matrix[row].length; column++)
	    	            matrix[row][0] = J2Web_UI.listSchedeClientiMatch.get(row).nomeCliente;
	    	            matrix[row][1] = J2Web_UI.listSchedeClientiMatch.get(row).cognomeCliente;
	    	            matrix[row][2] = J2Web_UI.listSchedeClientiMatch.get(row).emailCliente;
	    	            matrix[row][3] = J2Web_UI.listSchedeClientiMatch.get(row).telefono1Cliente;
	    	            matrix[row][4] = J2Web_UI.listSchedeClientiMatch.get(row).telefono2Cliente;
	    	    }
	    		
	    		JTable table = new JTable();
	    		table.setModel(new DefaultTableModel(
	    				matrix,
	    			new String[] {
	    				"Nome", "Cognome", "Email", "Telefono 1", "Telefono 2"
	    			}
	    		));
	    		pannelloMatchVeicoloCliente.add(table);
	    		
	    		
	    		
	    	}
	 }
}