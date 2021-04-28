package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import classes.Proizvod;
import database.Database;

public class AzurirajArtikal {


	private JFrame inputFrameAzurirajArtikal;
	private JTextField textFieldOpis;
	private JTextField textFieldCijena;
	
	private int idTrenutnogProizvoda;
	private String nazivTrenutnogProizvoda;
	
	
	
	
	public AzurirajArtikal() {

		idTrenutnogProizvoda = DashboardTrgovac.idProizvoda;
		nazivTrenutnogProizvoda = DashboardTrgovac.nazivProizvoda;
		initialize();
		inputFrameAzurirajArtikal.setVisible(true);
	}


	@SuppressWarnings("unused")
	private void initialize() {

		inputFrameAzurirajArtikal = new JFrame();
		inputFrameAzurirajArtikal.setSize(600,600);
		inputFrameAzurirajArtikal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputFrameAzurirajArtikal.getContentPane().setLayout(null);
		inputFrameAzurirajArtikal.setResizable(false);
		inputFrameAzurirajArtikal.setLocationRelativeTo(null);
		inputFrameAzurirajArtikal.setTitle("Azuriranje Artikla");
		inputFrameAzurirajArtikal.getContentPane().setBackground(Color.white);
		inputFrameAzurirajArtikal.setResizable(false);

		JPanel panelDodajProdajnoMjestoHeading = new JPanel();
		panelDodajProdajnoMjestoHeading.setBackground(new Color(0, 128, 128));
		panelDodajProdajnoMjestoHeading.setBounds(20, 40, 560, 70);
		inputFrameAzurirajArtikal.getContentPane().add(panelDodajProdajnoMjestoHeading);
		panelDodajProdajnoMjestoHeading.setLayout(new BorderLayout(0, 0));

		JLabel lblHeadingDodajArtikal = new JLabel("Azuriranje Artikla");
		lblHeadingDodajArtikal.setVerticalAlignment(SwingConstants.BOTTOM);
		panelDodajProdajnoMjestoHeading.add(lblHeadingDodajArtikal, BorderLayout.CENTER);
		lblHeadingDodajArtikal.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadingDodajArtikal.setForeground(Color.WHITE);
		lblHeadingDodajArtikal.setFont(new Font("Candara", Font.BOLD, 35));
		lblHeadingDodajArtikal.setBackground(Color.WHITE);



		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 121, 560, 435);
		inputFrameAzurirajArtikal.getContentPane().add(panel);
		panel.setLayout(null);

		textFieldOpis = new JTextField();
		textFieldOpis.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldOpis.setColumns(10);
		textFieldOpis.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldOpis.setBounds(170, 120, 220, 33);
		panel.add(textFieldOpis);

		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setFont(new Font("Candara", Font.BOLD, 20));
		lblOpis.setBounds(170, 100, 220, 20);
		panel.add(lblOpis);

		textFieldCijena = new JTextField();
		textFieldCijena.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldCijena.setColumns(10);
		textFieldCijena.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldCijena.setBounds(170, 50, 220, 33);
		panel.add(textFieldCijena);

		JLabel lblCijena = new JLabel("Cijena");
		lblCijena.setFont(new Font("Candara", Font.BOLD, 20));
		lblCijena.setBounds(170, 30, 220, 20);
		panel.add(lblCijena);

		ButtonGroup btn_group_pol = new ButtonGroup();

		JPanel panelBtnDodaj = new JPanel();
		panelBtnDodaj.setLayout(null);
		panelBtnDodaj.setBorder(null);
		panelBtnDodaj.setBackground(new Color(0, 128, 128));
		panelBtnDodaj.setBounds(205, 330, 150, 50);
		panel.add(panelBtnDodaj);

		panelBtnDodaj.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 128, 128));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 100, 100));
				if(textFieldCijena.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Polje za cijenu ne smije biti prazno!", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				}else {
				Object [] options = { "Da", "Ne" };
				if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da želite ažurirati artikal?", "Otkazivanje",
										    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
										    options, options[0]) == JOptionPane.YES_OPTION) {
					
						
					DecimalFormat df = new DecimalFormat("0.00");
					
					try {

						Double value = Double.parseDouble(textFieldCijena.getText());
						 String cijena = df.format(value);
							value = Double.parseDouble(cijena);
							
							
							azurirajArtikal(idTrenutnogProizvoda, nazivTrenutnogProizvoda, textFieldOpis.getText(), value);
							Proizvod.importProizvode();
							
							DashboardTrgovac.popunitiTabeluArtikala();

		                    inputFrameAzurirajArtikal.dispose();

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Cijena mora biti broj! Azuriranje se poništava!", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);

					}
					

					}
					
                    
				}
               
                    
               
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel lblDodaj = new JLabel("Azuriraj");
		lblDodaj.setHorizontalAlignment(SwingConstants.CENTER);
		lblDodaj.setForeground(Color.WHITE);
		lblDodaj.setFont(new Font("Candara", Font.BOLD, 25)); 
		lblDodaj.setBackground(new Color(0, 100, 0));
		lblDodaj.setBounds(0, 11, 150, 39);
		panelBtnDodaj.add(lblDodaj);
	}



	@SuppressWarnings("unused")
	private int getIduciIDArtikla() {
		int location = Proizvod.getListaProizvoda().size()-1;
		Proizvod posljednji = Proizvod.getListaProizvoda().get(location);
		int id = posljednji.getId()+1;

		return id;
	}
	
	private void izbrisiProizvod(int id) {
		 try {
	            Database d = new Database();
	            String query = "DELETE from proizvod where id = ?";
	            @SuppressWarnings("unused")
				Statement st = d.getConn().createStatement();
	            
	            PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
	            preparedStmt.setInt(1, id);
	           
	              // execute the preparedstatement
	              preparedStmt.execute();
	            
	        }
	        catch (SQLException e) {
	            System.err.println(e);
	        }
	}
	
	
	private void azurirajArtikal(int id, String naziv, String opis, Double cijena) {
        izbrisiProizvod(id);
        
        DodajArtikal.dodajArtikal(id, naziv, opis, cijena);
        
    }
}

