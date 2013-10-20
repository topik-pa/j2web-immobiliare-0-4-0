//Questa classe definisce tutti i sottopannelli schede cliente

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Box;
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

class PanelSchedaCliente extends JPanel implements parametriGenerali{   
	
	private static final long serialVersionUID = 1L;
	
	SchedaCliente scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaCliente(final SchedaCliente scheda, final LinkedList<SchedaCliente> listaSchedeCliente, final ButtonGroup radioGrpSchede) {
		 this.scheda = scheda;
		 idScheda = scheda.idSchedaCliente;
		 codiceScheda = scheda.codiceSchedaCliente;
		 
		 setLayout(new BorderLayout(0, 0));
		 setBorder(new LineBorder(Color.LIGHT_GRAY));
		 setMaximumSize(new Dimension(600, 130));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton("Seleziona scheda");
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
           public void actionPerformed(ActionEvent e) {
               System.out.println("Scheda cliente selezionata: " + scheda.marcaVeicoloCliente); 
               
               Component[] test = getParent().getComponents();
               for(int i=0; i<test.length; i++) {
            	   if(test[i].getClass().toString().contains("PanelSchedaCliente"))  {
            		   ((JComponent) test[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
            	   }
               }
               
               setBorder(new LineBorder(Color.ORANGE));
               
               matchClienteVeicolo(scheda);
           }
		 });
		 add(schedaRadio, BorderLayout.NORTH);
		 
		 //La label delle schede
		 String labelScheda = scheda.nomeCliente + "-" + scheda.cognomeCliente + "-" + scheda.telefono1Cliente + "-" + scheda.emailCliente;
		 labelScheda+="                                        "; //Aggiungo 40 caratteri spazio
		 if(labelScheda.length()>38) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 38); 
		 }		 
		 labelScheda+="...";
		 
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.LEFT);
		 
		 if(scheda.titoloCliente.equals("signora")) {
			 label.setIcon(new ImageIcon(pathImmagini + "icon_woman.png"));
		 }
		 else {
			 label.setIcon(new ImageIcon(pathImmagini + "icon_man.png"));
		 }
		 
		 
		 add(label, BorderLayout.CENTER);
		 
		 //Aggiungo una tooltip
		 String tooltipScheda = scheda.nomeCliente + "-" + scheda.cognomeCliente + "-" + scheda.telefono1Cliente + "-" + scheda.emailCliente;
		 setToolTipText(tooltipScheda);
		 
		 JPanel panel_26 = new JPanel();
		 panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 add(panel_26, BorderLayout.SOUTH);
		 panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
		 //Pulsante "Cancella"
		 btnCancellaScheda = new JButton("Cancella");
		 btnCancellaScheda.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              System.out.println("Cancella scheda cliente: " + scheda.marcaVeicoloCliente);
              
              //Rimozione di una scheda immobile dalla LinkedList
              ListIterator<SchedaCliente> iterator = listaSchedeCliente.listIterator();
          		while(iterator.hasNext()) {
	          		SchedaCliente schedaCorrente = iterator.next();
	          		//La rimozione avviene confrontando l'id univoco della scheda immobile
	          		if(schedaCorrente.idSchedaCliente==idScheda) {
	          			iterator.remove();
	          			System.out.println("Scheda cliente rimossa dalla linkedlist");
	          		}
          		}
          	
	          	//Aggiorno il file dat delle schede
	          	j2web.salvaListaSchedeClienteCreate();
          	          	  	            	
	          	//Aggiornamento del pannello centrale, la scheda corrente è stata cancellata
	          	//j2web_GUI.panelListaSchedeImmobile.updatePanello();
	          	J2Web_UI.aggiornaPannelloListaSchedeCliente();
          	
          	}
		 });
		 panel_26.add(btnCancellaScheda);
       
        //add(new JLabel(labelSpaziatore));
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_26.add(horizontalGlue);     
       
	 }

	 //Funzione di matching tra clienti e veicoli salvati in locale
	 private void  matchClienteVeicolo(SchedaCliente schedaCliente) {
		 
		 JPanel pannelloMatchClienteVeicolo = J2Web_UI.getPanel_6();
		 
		 J2Web_UI.listSchedeVeicoliMatch.clear();
			
		 pannelloMatchClienteVeicolo.removeAll();
		 
		 boolean matchPositivo = false; 
		 
			ListIterator<SchedaVeicolo> iterator = J2Web_UI.listSchedeVeicolo.listIterator();
	     	while(iterator.hasNext()) {
	     		SchedaVeicolo schedaCorrente = iterator.next();
	     		
	     		if(
	     				(schedaCliente.marcaVeicoloCliente.equalsIgnoreCase(schedaCorrente.marcaVeicolo) && schedaCliente.modelloVeicoloCliente.equalsIgnoreCase(schedaCorrente.modelloVeicolo)) ||
	     				(schedaCliente.versioneVeicoloCliente.equalsIgnoreCase(schedaCorrente.versioneVeicolo)) ||
	     				(schedaCliente.marcaVeicoloCliente.equalsIgnoreCase(schedaCorrente.marcaVeicolo) && schedaCliente.tipologiaCarburanteVeicoloCliente.equalsIgnoreCase(schedaCorrente.carburanteVeicolo)) ||
	     				(schedaCliente.marcaVeicoloCliente.equalsIgnoreCase(schedaCorrente.marcaVeicolo) && schedaCliente.coloreEsternoVeicoloCliente.equalsIgnoreCase(schedaCorrente.coloreEsternoVeicolo)) ||
	     				(schedaCliente.marcaVeicoloCliente.equalsIgnoreCase(schedaCorrente.marcaVeicolo) && schedaCliente.tipologiaVeicoloCliente.equalsIgnoreCase(schedaCorrente.tipologiaVeicolo))
	     				
	     			) {
	     			J2Web_UI.listSchedeVeicoliMatch.add(schedaCorrente);
	     		}
	     		
	     	}
	     	if(!J2Web_UI.listSchedeVeicoliMatch.isEmpty()){	
	     		matchPositivo = true;
	     	}
			
			
			if(!matchPositivo) {
				//Match negativo
				System.out.println("Non è stato trovato alcun veicolo intteressante per il cliente.");
	    		JPanel panelNessunMatch = new JPanel();
	            JLabel lblNessunMatch = new JLabel("Non è stato trovato alcun veicolo intteressante per il cliente.");                
	            panelNessunMatch.add(lblNessunMatch);
	            pannelloMatchClienteVeicolo.add(panelNessunMatch);
	    	}    	
	    	else {
	    		//Match positivo
	    		System.out.println("Trovati potenziali veicoli interessanti per il cliente");
	    		
	    		String[][] matrix = new String[J2Web_UI.listSchedeVeicoliMatch.size()][5];
	    		
	    		for (int row = 0; row < matrix.length; row++) {
	    	            matrix[row][0] = J2Web_UI.listSchedeVeicoliMatch.get(row).marcaVeicolo;
	    	            matrix[row][1] = J2Web_UI.listSchedeVeicoliMatch.get(row).modelloVeicolo;
	    	            matrix[row][2] = J2Web_UI.listSchedeVeicoliMatch.get(row).versioneVeicolo;
	    	            matrix[row][3] = J2Web_UI.listSchedeVeicoliMatch.get(row).coloreEsternoVeicolo;
	    	            matrix[row][4] = J2Web_UI.listSchedeVeicoliMatch.get(row).prezzoVeicolo;
	    	    }
	    		
	    		JTable table = new JTable();
	    		table.setModel(new DefaultTableModel(
	    				matrix,
	    			new String[] {
	    				"Marca", "Modello", "Versione", "Colore", "Prezzo"
	    			}
	    		));
	    		pannelloMatchClienteVeicolo.add(table);
	    		
	    	}
	 }
	 
}