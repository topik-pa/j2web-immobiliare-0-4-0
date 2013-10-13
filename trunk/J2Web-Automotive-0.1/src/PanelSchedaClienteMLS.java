import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComponent;

class PanelSchedaClienteMLS extends JPanel {   
	//JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
	private static final long serialVersionUID = 1L;
	
	SchedaCliente scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaClienteMLS(final SchedaCliente scheda, final LinkedList<SchedaCliente> listaSchedeCliente, final ButtonGroup radioGrpSchede) {
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
               System.out.println("Scheda selezionata: " + scheda.marcaVeicoloCliente + " per MLS..."); 
               
               JPanel pannelloInfoVeicoloMLS = J2Web_UI.getPanel_8();
               pannelloInfoVeicoloMLS.removeAll();
               pannelloInfoVeicoloMLS.updateUI();
               
               Component[] test = getParent().getComponents();
               for(int i=0; i<test.length; i++) {
            	   if(test[i].getClass().toString().contains("PanelSchedaCliente"))  {
            		   ((JComponent) test[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
            	   }
               }
               
               setBorder(new LineBorder(Color.ORANGE));
               
               matchClienteVeicoloMLS(scheda);
               
           }
		 });
		 add(schedaRadio, BorderLayout.NORTH);
		 
		 //La label delle schede
		 String labelScheda = scheda.nomeCliente + "-" + scheda.cognomeCliente + "-" + scheda.telefono1Cliente /*+ "-" + scheda.comune + "-" + scheda.regione + "-" + scheda.testoAnnuncio*/;
		 if(labelScheda.length()>16) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 15); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.LEFT);
		 label.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\imaginationLogo.png"));
		 add(label, BorderLayout.CENTER);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
		 JPanel panel_26 = new JPanel();
		 panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 add(panel_26, BorderLayout.SOUTH);
		 panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
       
       //add(new JLabel(labelSpaziatore));
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_26.add(horizontalGlue);
       
       
	 }
	 
	 private void  matchClienteVeicoloMLS(SchedaCliente schedaCliente) {
		 
		 JPanel pannelloMatchClienteVeicoloMLS = J2Web_UI.getPanel_15();
		 
		 pannelloMatchClienteVeicoloMLS.removeAll();
		 
		  Connection con = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;

	        String url = "jdbc:mysql://localhost:3306/veicoli";
	        String user = "testuser";
	        String password = "test623";
	        
	        
	        try {
	            
	            con = DriverManager.getConnection(url, user, password);
	            pst = con.prepareStatement("SELECT * FROM Autoveicoli WHERE Marca = " + "'" + scheda.marcaVeicoloCliente + "'");
	            rs = pst.executeQuery();

	            while (rs.next()) {
	                System.out.print(rs.getInt(1));
	                System.out.print(": ");
	                System.out.println(rs.getString(2));
	                System.out.print(rs.getString(3));
	                System.out.print(": ");
	                System.out.println(rs.getString(4));
	                
	                
	                SchedaVeicolo schedaVeicoloMLS = new SchedaVeicolo(rs);
	                
	                PanelSchedaVeicoloMLS panelSchedaVeicoloMLS = new PanelSchedaVeicoloMLS(schedaVeicoloMLS);
	                
	                pannelloMatchClienteVeicoloMLS.add(panelSchedaVeicoloMLS);
	                
	            }

	        } catch (SQLException ex) {
	                Logger lgr = Logger.getLogger(PanelSchedaClienteMLS.class.getName());
	                lgr.log(Level.SEVERE, ex.getMessage(), ex);

	        } finally {

	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (pst != null) {
	                    pst.close();
	                }
	                if (con != null) {
	                    con.close();
	                }

	            } catch (SQLException ex) {
	                Logger lgr = Logger.getLogger(PanelSchedaClienteMLS.class.getName());
	                lgr.log(Level.WARNING, ex.getMessage(), ex);
	            }
	        }
	        
	        pannelloMatchClienteVeicoloMLS.updateUI();
		 
		 
	 }

}