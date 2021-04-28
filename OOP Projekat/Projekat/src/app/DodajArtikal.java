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

public class DodajArtikal {


	private JFrame inputFrameDodajArtikal;
	private JTextField textFieldOpis;
	private JTextField textFieldCijena;
	private JTextField textFieldNaziv;
	
	
	
	
	public DodajArtikal() {
		initialize();
		inputFrameDodajArtikal.setVisible(true);
	}


	private void initialize() {
		inputFrameDodajArtikal = new JFrame();
		inputFrameDodajArtikal.setSize(600,600);
		inputFrameDodajArtikal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputFrameDodajArtikal.getContentPane().setLayout(null);
		inputFrameDodajArtikal.setResizable(false);
		inputFrameDodajArtikal.setLocationRelativeTo(null);
		inputFrameDodajArtikal.setTitle("Unos Trgovca");
		inputFrameDodajArtikal.getContentPane().setBackground(Color.white);

		JPanel panelDodajProdajnoMjestoHeading = new JPanel();
		panelDodajProdajnoMjestoHeading.setBackground(new Color(0, 128, 128));
		panelDodajProdajnoMjestoHeading.setBounds(20, 40, 560, 70);
		inputFrameDodajArtikal.getContentPane().add(panelDodajProdajnoMjestoHeading);
		panelDodajProdajnoMjestoHeading.setLayout(new BorderLayout(0, 0));

		JLabel lblHeadingDodajArtikal = new JLabel("Dodavanje Artikla");
		lblHeadingDodajArtikal.setVerticalAlignment(SwingConstants.BOTTOM);
		panelDodajProdajnoMjestoHeading.add(lblHeadingDodajArtikal, BorderLayout.CENTER);
		lblHeadingDodajArtikal.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadingDodajArtikal.setForeground(Color.WHITE);
		lblHeadingDodajArtikal.setFont(new Font("Candara", Font.BOLD, 35));
		lblHeadingDodajArtikal.setBackground(Color.WHITE);



		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 121, 560, 435);
		inputFrameDodajArtikal.getContentPane().add(panel);
		panel.setLayout(null);

		textFieldOpis = new JTextField();
		textFieldOpis.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldOpis.setColumns(10);
		textFieldOpis.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldOpis.setBounds(170, 190, 220, 33);
		panel.add(textFieldOpis);

		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setFont(new Font("Candara", Font.BOLD, 20));
		lblOpis.setBounds(170, 170, 220, 20);
		panel.add(lblOpis);

		textFieldCijena = new JTextField();
		textFieldCijena.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldCijena.setColumns(10);
		textFieldCijena.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldCijena.setBounds(170, 120, 220, 33);
		panel.add(textFieldCijena);

		JLabel lblCijena = new JLabel("Cijena");
		lblCijena.setFont(new Font("Candara", Font.BOLD, 20));
		lblCijena.setBounds(170, 100, 220, 20);
		panel.add(lblCijena);

		textFieldNaziv = new JTextField();
		textFieldNaziv.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldNaziv.setColumns(10);
		textFieldNaziv.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldNaziv.setBounds(170, 50, 220, 33);
		panel.add(textFieldNaziv);

		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Candara", Font.BOLD, 20));
		lblNaziv.setBounds(170, 30, 200, 20);
		panel.add(lblNaziv);

		@SuppressWarnings("unused")
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
				if(textFieldNaziv.getText().equals("") ||  textFieldCijena.getText() == "") {
					JOptionPane.showMessageDialog(null, "Jedno ili više obaveznih polja je prazno!", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				}else {
				Object [] options = { "Da", "Ne" };
				if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da želite dodati artikal?", "Otkazivanje",
										    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
										    options, options[0]) == JOptionPane.YES_OPTION) {
					
					
					

					DecimalFormat df = new DecimalFormat("0.00");
					Double value = Double.parseDouble(textFieldCijena.getText());
					String cijena = df.format(value);
					value = Double.parseDouble(cijena);
					
					
					dodajArtikal(getIduciIDArtikla(), textFieldNaziv.getText(), textFieldOpis.getText(), value);
					Proizvod.importProizvode();
                    

					DashboardTrgovac.popunitiTabeluArtikala();
					inputFrameDodajArtikal.dispose();
					}
					
                    
				}
               

                
				}
			

			@Override
			public void mouseReleased(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel lblDodaj = new JLabel("Dodaj");
		lblDodaj.setHorizontalAlignment(SwingConstants.CENTER);
		lblDodaj.setForeground(Color.WHITE);
		lblDodaj.setFont(new Font("Candara", Font.BOLD, 25)); 
		lblDodaj.setBackground(new Color(0, 100, 0));
		lblDodaj.setBounds(0, 11, 150, 39);
		panelBtnDodaj.add(lblDodaj);
	}



	private int getIduciIDArtikla() {
		int location = Proizvod.getListaProizvoda().size()-1;
		Proizvod posljednji = Proizvod.getListaProizvoda().get(location);
		int id = posljednji.getId()+1;

		return id;
	}
	
	
	
	public static void dodajArtikal(int id, String naziv, String opis, double cijena) {
        try {
            Database d = new Database();
            String query = " insert into proizvod (id, naziv, opis, cijena)"
                    + " values (?, ?, ?, ?)";
            @SuppressWarnings("unused")
			Statement st = d.getConn().createStatement();
 
            PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
             preparedStmt.setInt(1, id);
             preparedStmt.setString (2, naziv);
             preparedStmt.setString (3, opis);
             preparedStmt.setDouble(4, cijena);
           
              // execute the preparedstatement
              preparedStmt.execute();
            
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        
    }
}

