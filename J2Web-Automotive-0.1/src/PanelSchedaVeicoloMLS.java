/**
 * Questa classe definisce tutti i sottopannelli schede veicolo nella sezione MLS
 * @author marco - marcopavan.mp@gmail.com 
 */

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
import javax.swing.ButtonGroup;
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

	private static final long serialVersionUID = 5282968848109414898L;

	JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	static ButtonGroup buttonGroupSchedeVeicoloMLS = new ButtonGroup(); 

	SchedaVeicolo scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;

	String labelSpaziatore = "   "; 

	//Costruttore
	public PanelSchedaVeicoloMLS(final SchedaVeicolo scheda) {
		this.scheda = scheda;
		idScheda = scheda.idScheda;
		codiceScheda = scheda.codiceScheda;

		setLayout(new BorderLayout(0, 0));
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setMaximumSize(new Dimension(700, 130));

		//Radio button dei sottopannelli
		schedaRadio = new JRadioButton("Seleziona scheda");
		//Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		buttonGroupSchedeVeicoloMLS.add(schedaRadio); 
		//Clicco su una radio button di una scheda
		schedaRadio.addActionListener(new ActionListener() {			 
			public void actionPerformed(ActionEvent e) {
				System.out.println("Scheda selezionata: " + scheda.codiceScheda);          

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
		String linea1 = scheda.marcaVeicolo + " " + scheda.modelloVeicolo;	
		String linea2 = scheda.versioneVeicolo;if(linea2.length()>45) {linea2 = linea2.substring(0, 44);}
		String linea3 = scheda.carrozzeriaVeicolo + " " + scheda.coloreEsternoVeicolo;if(linea3.length()>45) {linea3 = linea3.substring(0, 44);}		
		String labelScheda = "<html><p style='padding:5px;'><strong>" + linea1 + "</strong><br/><i>" + linea2 + "</i><br/>" + linea3 + "</p></html>";	
		String tooltipScheda = labelScheda;

		JLabel label = new JLabel(labelScheda);
		Font font = new Font("Monospaced", Font.PLAIN, 11);
		label.setFont(font);
		label.setHorizontalTextPosition(SwingConstants.LEFT);

		BufferedImage imgtest = null;
		Image resizedimg = null;
		if(scheda.arrayImages[1]!=null) {
			try {
				imgtest = ImageIO.read(scheda.arrayImages[1]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			resizedimg = imgtest.getScaledInstance(70, 50, Image.SCALE_FAST);          
		}
		else {
			resizedimg = new BufferedImage(70,50,BufferedImage.TYPE_INT_ARGB_PRE);
		}

		add(label, BorderLayout.CENTER);
		add(new JLabel(" ", new ImageIcon(resizedimg), JLabel.RIGHT),BorderLayout.EAST);

		//Aggiungo una tooltip
		setToolTipText(tooltipScheda);		 

	}

	//Mostra i dettagli del veicolo MLS attualmente selezionato
	private void  mostraDettagliVeicoloMLS(SchedaVeicolo schedaVeicolo) {

		JPanel pannelloInfoVeicoloMLS = J2Web_UI.getPanel_8();

		pannelloInfoVeicoloMLS.removeAll();

		System.out.println("Info veicolo selezionato...");

		String[][] matrix = new String[1][11];

		for (int row = 0; row < matrix.length; row++) {
			matrix[row][0] = schedaVeicolo.marcaVeicolo;
			matrix[row][1] = schedaVeicolo.modelloVeicolo;
			matrix[row][2] = schedaVeicolo.versioneVeicolo;
			matrix[row][3] = schedaVeicolo.coloreEsternoVeicolo;
			matrix[row][4] = schedaVeicolo.chilometraggioVeicolo;
			matrix[row][5] = schedaVeicolo.prezzoVeicolo;
			matrix[row][6] = schedaVeicolo.tipologiaContrattoVeicolo;
			matrix[row][7] = schedaVeicolo.ragioneSociale;
			matrix[row][8] = schedaVeicolo.Indirizzo;
			matrix[row][9] = schedaVeicolo.TelefonoReferente;
			matrix[row][10] = schedaVeicolo.nomeReferente;
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				matrix,
				new String[] {
						"Marca", "Modello", "Versione", "Colore", "Chilometri", "Prezzo", "Contratto", "Concessionario", "Indirizzo", "Telefono", "Referente" 
				}
				));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);

		pannelloInfoVeicoloMLS.add(table.getTableHeader(), BorderLayout.PAGE_START);
		pannelloInfoVeicoloMLS.add(table, BorderLayout.CENTER);
	}

}