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
		setMaximumSize(new Dimension(600, 130));

		//Radio button dei sottopannelli
		schedaRadio = new JRadioButton("Seleziona scheda");
		//Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		//radioGrpSchede.add(schedaRadio); 
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
		String labelScheda = scheda.marcaVeicolo + " " + scheda.modelloVeicolo + " " + scheda.versioneVeicolo + " - " + scheda.tipologiaVeicolo + " " + scheda.coloreEsternoVeicolo + " " + scheda.prezzoVeicolo + " - " + scheda.ragioneSociale + " " + scheda.nomeReferente;
		String tooltipScheda = labelScheda;
		if(labelScheda.length()>60) {	//è molto probabile che lo sia... :)
			labelScheda = labelScheda.substring(0, 60); 
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

			label.setIcon(new ImageIcon(resizedimg));
		}

		add(label, BorderLayout.CENTER);

		//Aggiungo una tooltip
		setToolTipText(tooltipScheda);		 

	}

	private void  mostraDettagliVeicoloMLS(SchedaVeicolo schedaVeicolo) {

		JPanel pannelloInfoVeicoloMLS = J2Web_UI.getPanel_8();

		pannelloInfoVeicoloMLS.removeAll();

		System.out.println("Info veicolo selezionato...");

		String[][] matrix = new String[1][5];

		for (int row = 0; row < matrix.length; row++) {
			// for (int column = 0; column < matrix[row].length; column++)
			matrix[row][0] = schedaVeicolo.marcaVeicolo;
			matrix[row][1] = schedaVeicolo.modelloVeicolo;
			matrix[row][2] = schedaVeicolo.versioneVeicolo;
			matrix[row][3] = schedaVeicolo.coloreEsternoVeicolo;
			matrix[row][4] = schedaVeicolo.chilometraggioVeicolo;
		}

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				matrix,
				new String[] {
						"Marca", "Modello", "Versione", "Colore", "Chilometri"
				}
				));
		pannelloInfoVeicoloMLS.add(table);
	}

}