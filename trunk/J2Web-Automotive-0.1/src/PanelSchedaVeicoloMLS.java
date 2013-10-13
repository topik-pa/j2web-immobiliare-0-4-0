//Questa classe definisce tutti i sottopannelli schede veicolo

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

class PanelSchedaVeicoloMLS extends JPanel {   
	
	JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
	private static final long serialVersionUID = 1L;
	
	SchedaVeicolo scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaVeicoloMLS(final SchedaVeicolo scheda) {
		 this.scheda = scheda;
		 idScheda = scheda.idScheda;
		 codiceScheda = scheda.codiceScheda;
		 
		 setLayout(new BorderLayout(0, 0));
		 setBorder(new LineBorder(Color.LIGHT_GRAY));
		 setMaximumSize(new Dimension(400, 130));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton("Seleziona scheda");
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 //radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
         public void actionPerformed(ActionEvent e) {
             System.out.println("Scheda selezionata: " + scheda.marcaVeicolo); 
             
             
             Component[] wrapperScheda = getParent().getComponents();
             for(int i=0; i<wrapperScheda.length; i++) {
          	   if(wrapperScheda[i].getClass().toString().contains("PanelSchedaVeicolo"))  {
          		   ((JComponent) wrapperScheda[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
          	   }
             }
             
             setBorder(new LineBorder(Color.ORANGE));
             
             mostraDettagliVeicoloMLS(scheda);
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
		 
		 BufferedImage imgtest = null;
		 if(scheda.arrayImages[1]!=null) {
			 try {
					imgtest = ImageIO.read(scheda.arrayImages[1]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Image resizedimg = imgtest.getScaledInstance(70, 50, Image.SCALE_FAST);          
	         //new ImageIcon(resizedimg);
	         
			 label.setIcon(new ImageIcon(resizedimg));
		 }
		
		 
		 
		 add(label, BorderLayout.CENTER);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
		 
	 }
	
	 private void  mostraDettagliVeicoloMLS(SchedaVeicolo schedaVeicolo) {
		 
		 JPanel pannelloInfoVeicoloMLS = J2Web_UI.getPanel_8();
		 
		 //J2Web_UI.listSchedeClientiMatch.clear();
			
		 pannelloInfoVeicoloMLS.removeAll();
		 
		 //pannelloMatchVeicoloCliente.updateUI();
			
			//pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(7));
		 
		 
			
	
	    		
	    		System.out.println("Info veicolo selezionato...");
	    		
	    		String[][] matrix = new String[1][3];
	    		
	    		for (int row = 0; row < matrix.length; row++) {
	    	       // for (int column = 0; column < matrix[row].length; column++)
	    	            matrix[row][0] = schedaVeicolo.marcaVeicolo;
	    	            matrix[row][1] = schedaVeicolo.modelloVeicolo;
	    	            matrix[row][2] = schedaVeicolo.versioneVeicolo;
	    	            //matrix[row][3] = J2Web_UI.listSchedeClientiMatch.get(row).telefono1Cliente;
	    	            //matrix[row][4] = J2Web_UI.listSchedeClientiMatch.get(row).telefono2Cliente;
	    	    }
	    		
	    		JTable table = new JTable();
	    		table.setModel(new DefaultTableModel(
	    				matrix,
	    			new String[] {
	    				"Marca", "Modello", "Versione"
	    			}
	    		));
	    		pannelloInfoVeicoloMLS.add(table);
	    		
	    		
	    		
	    	
	 }
}