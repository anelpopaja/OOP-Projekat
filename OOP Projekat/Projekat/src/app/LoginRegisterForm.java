package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import classes.ArtikalNarudzbe;
import classes.Kupac;
import classes.Narudzba;
import classes.ProdajnoMjesto;
import classes.Proizvod;
import classes.Trgovac;
import database.Database;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.Component;

public class LoginRegisterForm {

	private JFrame frame;
	private JButton button_pressed;
	private JPasswordField passwordField_reg_lozinka_1;
	private ArrayList<Trgovac> trgovci = new ArrayList<Trgovac>();
	private ArrayList<Kupac> kupci= new ArrayList<Kupac>();
	private static Kupac trenutniKupac;
	private static Trgovac trenutniTrgovac;
	
	
	public LoginRegisterForm() {
		initialize();
		frame.setVisible(true);
		
	}
	
	private void initialize() {
		//Iz baze sve izvaditi u liste
		
		uzimanjePodatakaIzBaze();
		
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Anel\\Downloads\\shopify-bag.png"));
		frame.setBounds(100, 100, 750, 680);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, "name_970766763486700");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		JPanel panel_welcome = new JPanel();		panel_welcome.setBackground(Color.WHITE);
		layeredPane.add(panel_welcome, "name_970772762961800");
		panel_welcome.setLayout(null);
		
		JPanel panel_prijava = new JPanel();
		panel_prijava.setBackground(Color.WHITE);
		layeredPane.add(panel_prijava, "name_970781967545600");
		
		JPanel panel_registracija = new JPanel();
		panel_registracija.setBackground(Color.WHITE);
		layeredPane.add(panel_registracija, "name_970847337231000");
		
		
		//editing first panel
		//Adding banner image
		JLabel banner_with_icon = new JLabel("");
		banner_with_icon.setBounds(10, 30, 720, 90);
		banner_with_icon.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Anel\\Downloads\\Shopify_Logo.png");
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(350, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		banner_with_icon.setIcon(imageIcon);
		panel_welcome.add(banner_with_icon);
		
		JTextPane txt_prijavite_se = new JTextPane();
		txt_prijavite_se.setEditable(false);
		txt_prijavite_se.setFont(new Font("Arial", Font.BOLD, 25));
		txt_prijavite_se.setText("Prijavite se kao kupac ili trgovac");
		txt_prijavite_se.setBounds(177, 150, 400, 38);
		panel_welcome.add(txt_prijavite_se);
		
		JButton btn_kupac = new JButton("KUPAC");
		btn_kupac.setForeground(new Color(0, 0, 0));
		btn_kupac.setBackground(new Color(0, 128, 128));
		btn_kupac.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		btn_kupac.setBounds(284, 230, 165, 63);
		panel_welcome.add(btn_kupac);
		btn_kupac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_pressed = btn_kupac;
				layeredPane.removeAll();
				layeredPane.add(panel_prijava);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		
		JButton btn_trgovac = new JButton("TRGOVAC");
		btn_trgovac.setForeground(Color.BLACK);
		btn_trgovac.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		btn_trgovac.setBackground(new Color(0, 128, 128));
		btn_trgovac.setBounds(284, 330, 165, 63);
		panel_welcome.add(btn_trgovac);
		btn_trgovac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_pressed = btn_trgovac;
				layeredPane.removeAll();
				layeredPane.add(panel_prijava);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		
		JTextPane txt_nemate_racun = new JTextPane();
		txt_nemate_racun.setEditable(false);
		txt_nemate_racun.setText("Nemate ra\u010Dun?");
		txt_nemate_racun.setFont(new Font("Arial", Font.BOLD, 25));
		txt_nemate_racun.setBounds(271, 480, 204, 38);
		panel_welcome.add(txt_nemate_racun);
		
		JTextPane txt_registrujte_se = new JTextPane();
		txt_registrujte_se.setEditable(false);
		txt_registrujte_se.setForeground(new Color(0, 128, 128));
		txt_registrujte_se.setText("Registrujte se kao kupac");
		txt_registrujte_se.setFont(new Font("Arial", Font.PLAIN, 25));
		txt_registrujte_se.setBounds(226, 515, 300, 38);
		txt_registrujte_se.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_welcome.add(txt_registrujte_se);
		
		
		txt_registrujte_se.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txt_registrujte_se.setForeground(new Color(0, 128, 178));
			}
			public void mouseReleased(MouseEvent e) {
				txt_registrujte_se.setForeground(new Color(0, 128, 128));
			}
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_registracija);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		//editing 2nd panel
		
		JLabel banner_with_icon1 = new JLabel("");
		banner_with_icon1.setBounds(10, 30, 720, 90);
		banner_with_icon1.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\Anel\\Downloads\\Shopify_Logo.png");
		Image image1 = imageIcon1.getImage(); // transform it 
		Image newimg1 = image1.getScaledInstance(350, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon1 = new ImageIcon(newimg1);
		panel_prijava.setLayout(null);
		banner_with_icon1.setIcon(imageIcon1);
		panel_prijava.add(banner_with_icon1);
		
		JTextPane txt_prijava = new JTextPane();
		txt_prijava.setEditable(false);
		txt_prijava.setText("Prijava");
		txt_prijava.setFont(new Font("Arial", Font.BOLD, 25));
		txt_prijava.setBounds(325, 150, 100, 40);
		panel_prijava.add(txt_prijava);
		
		JTextField textField_korisnicko_ime = new JTextField();
		textField_korisnicko_ime.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_korisnicko_ime.setBounds(190, 240, 360, 40);
		textField_korisnicko_ime.setBorder(new LineBorder(new Color(0, 128, 128)));
		panel_prijava.add(textField_korisnicko_ime);
		
		JPasswordField passwordField_password = new JPasswordField();
		passwordField_password.setFont(new Font("Arial", Font.PLAIN, 20));
		passwordField_password.setForeground(new Color(0, 0, 0));
		passwordField_password.setBounds(190, 335, 360, 40);
		passwordField_password.setBorder(new LineBorder(new Color(0, 128, 128)));
		panel_prijava.add(passwordField_password);
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		
		JTextPane txt_nazad_prijava = new JTextPane();
		txt_nazad_prijava.setBackground(new Color(255, 255, 255));
		txt_nazad_prijava.setText("<< Nazad");
		txt_nazad_prijava.setForeground(new Color(0, 128, 128));
		txt_nazad_prijava.setFont(new Font("Arial", Font.PLAIN, 25));
		txt_nazad_prijava.setEditable(false);
		txt_nazad_prijava.setBounds(30, 560, 135, 40);
		txt_nazad_prijava.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_prijava.add(txt_nazad_prijava);
		
		
		JLabel txtpn_prijava_error = new JLabel("Pogre\u0161no korisni\u010Dko ime ili lozinka!");
		txtpn_prijava_error.setForeground(Color.RED);
		txtpn_prijava_error.setBackground(Color.WHITE);
		txtpn_prijava_error.setHorizontalAlignment(SwingConstants.CENTER);
		txtpn_prijava_error.setFont(new Font("Arial", Font.PLAIN, 18));
		txtpn_prijava_error.setBounds(190, 398, 360, 30);
		panel_prijava.add(txtpn_prijava_error);
		txtpn_prijava_error.setVisible(false);
		
		JButton btn_prijavi_se = new JButton("PRIJAVI SE");
		btn_prijavi_se.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//get the username and password
				String username = textField_korisnicko_ime.getText();
				String password = String.valueOf(passwordField_password.getPassword());
				
				if(button_pressed == btn_kupac) {
					System.out.println("button kupac");
					boolean provjera = false;
					for (Kupac kupac : kupci) {
						if(kupac.getKorisnicko_ime().trim().equals(username) && kupac.getLozinka().trim().contentEquals(password)) {
							System.out.println("Successful login! kupac");
							trenutniKupac = kupac;
							provjera = true;
							//If login successful open new window
							frame.dispose();
							@SuppressWarnings("unused")
							DashboardKupac dashboardK = new DashboardKupac();
							break;
						}
					}
					if(!provjera) {
						txtpn_prijava_error.setVisible(true);
					}
				}
				else {
					System.out.println("button trgovac");
					boolean provjera = false;
					for (Trgovac trgovac : trgovci) {
						if(trgovac.getKorisnicko_ime().trim().equals(username) && trgovac.getLozinka().trim().contentEquals(password)) {
							System.out.println("Successful login! trgovac");
							trenutniTrgovac = trgovac;
							provjera = true;
							frame.dispose();
							@SuppressWarnings("unused")
							DashboardTrgovac dashboardT = new DashboardTrgovac();
							break;
						}
					}
					if(!provjera) {
						txtpn_prijava_error.setVisible(true);
					}
				}
		 	}
		});
		btn_prijavi_se.setForeground(Color.BLACK);
		btn_prijavi_se.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		btn_prijavi_se.setBackground(new Color(0, 128, 128));
		btn_prijavi_se.setBounds(270, 450, 200, 60);
		panel_prijava.add(btn_prijavi_se);
		
		
		
		JLabel txt_korisnicko_ime = new JLabel("Korisnicko Ime:");
		txt_korisnicko_ime.setFont(new Font("Arial", Font.BOLD, 20));
		txt_korisnicko_ime.setBounds(190, 200, 360, 30);
		panel_prijava.add(txt_korisnicko_ime);
		
		JLabel txt_password = new JLabel("Lozinka:");
		txt_password.setFont(new Font("Arial", Font.BOLD, 20));
		txt_password.setBounds(190, 295, 360, 30);
		panel_prijava.add(txt_password);
		
		/*
		JLabel lblNewLabel = new JLabel("");
		ImageIcon imageIconPass1 = new ImageIcon("C:\\Users\\Anel\\Downloads\\kisspng-computer-icons-clip-art-right-eye-5ae0d720ef0c16.2816819115246845769792.jpg");
		Image imagePass1 = imageIconPass1.getImage(); // transform it 
		Image newImgPass1 = imagePass1.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIconPass1 = new ImageIcon(newImgPass1);
		lblNewLabel.setIcon(imageIconPass1);
		panel.add(lblNewLabel);
		*/
		
		
		txt_nazad_prijava.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txt_nazad_prijava.setForeground(new Color(0, 128, 178));
			}
			public void mouseReleased(MouseEvent e) {
				txt_nazad_prijava.setForeground(new Color(0, 128, 128));
			}
			public void mouseClicked(MouseEvent e) {
				txtpn_prijava_error.setVisible(false);
				layeredPane.removeAll();
				layeredPane.add(panel_welcome);
				layeredPane.repaint();
				layeredPane.revalidate();
				textField_korisnicko_ime.setText(null);
				passwordField_password.setText(null);
			}
		});
		
		
		
		//EDITING 4TH PANEL

		
		JLabel banner_with_icon2 = new JLabel("");
		banner_with_icon2.setBounds(10, 30, 720, 90);
		banner_with_icon2.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imageIcon2 = new ImageIcon("C:\\Users\\Anel\\Downloads\\Shopify_Logo.png");
		Image image2 = imageIcon2.getImage(); // transform it 
		Image newimg2 = image2.getScaledInstance(350, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);
		panel_registracija.setLayout(null);
		banner_with_icon2.setIcon(imageIcon2);
		panel_registracija.add(banner_with_icon2);
		
		JTextPane txt_registracija = new JTextPane();
		txt_registracija.setText("Registracija");
		txt_registracija.setFont(new Font("Arial", Font.BOLD, 25));
		txt_registracija.setBounds(290, 130, 145, 40);
		panel_registracija.add(txt_registracija);
		

		JTextField textfield_reg_korisnicko_ime;
		JTextField textfield_reg_telefon;
		JTextField textfield_reg_ime;
		JTextField textfield_reg_email;
		JTextField textfield_reg_drzava;
		JTextField textfield_reg_prezime;
		JTextField textfield_reg_grad;
		JTextField textfield_reg_adresa;
		JTextField textfield_reg_postanski_broj;
		JPasswordField passwordField_reg_lozinka;
		
		
		textfield_reg_korisnicko_ime = new JTextField();
		textfield_reg_korisnicko_ime.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_korisnicko_ime.setBounds(30, 210, 195, 33);
		textfield_reg_korisnicko_ime.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_korisnicko_ime);
		textfield_reg_korisnicko_ime.setColumns(10);
		
		textfield_reg_telefon = new JTextField();
		textfield_reg_telefon.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_telefon.setColumns(10);
		textfield_reg_telefon.setBounds(30, 360, 195, 33);
		textfield_reg_telefon.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_telefon);
		
		JTextPane txt_reg_korisnicko_ime = new JTextPane();
		txt_reg_korisnicko_ime.setEditable(false);
		txt_reg_korisnicko_ime.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_korisnicko_ime.setText("Korisni\u010Dko ime");
		txt_reg_korisnicko_ime.setBounds(30, 180, 195, 20);
		panel_registracija.add(txt_reg_korisnicko_ime);
		
		JTextPane txt_reg_lozinka = new JTextPane();
		txt_reg_lozinka.setEditable(false);
		txt_reg_lozinka.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_lozinka.setText("Lozinka");
		txt_reg_lozinka.setBounds(30, 260, 195, 20);
		panel_registracija.add(txt_reg_lozinka);
		
		JTextPane txt_reg_telefon = new JTextPane();
		txt_reg_telefon.setEditable(false);
		txt_reg_telefon.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_telefon.setText("Telefon");
		txt_reg_telefon.setBounds(30, 330, 195, 20);
		panel_registracija.add(txt_reg_telefon);
		
		textfield_reg_ime = new JTextField();
		textfield_reg_ime.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_ime.setColumns(10);
		textfield_reg_ime.setBounds(265, 210, 195, 33);
		textfield_reg_ime.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_ime);
		
		textfield_reg_email = new JTextField();
		textfield_reg_email.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_email.setColumns(10);
		textfield_reg_email.setBounds(500, 289, 195, 33);
		textfield_reg_email.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_email);
		
		textfield_reg_drzava = new JTextField();
		textfield_reg_drzava.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_drzava.setColumns(10);
		textfield_reg_drzava.setBounds(265, 360, 195, 33);
		textfield_reg_drzava.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_drzava);
		
		JTextPane txt_reg_ime = new JTextPane();
		txt_reg_ime.setEditable(false);
		txt_reg_ime.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_ime.setText("Ime");
		txt_reg_ime.setBounds(265, 180, 195, 20);
		panel_registracija.add(txt_reg_ime);
		
		JTextPane txt_reg_email = new JTextPane();
		txt_reg_email.setEditable(false);
		txt_reg_email.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_email.setText("Email");
		txt_reg_email.setBounds(500, 260, 195, 20);
		panel_registracija.add(txt_reg_email);
		
		JTextPane txt_reg_drzava = new JTextPane();
		txt_reg_drzava.setEditable(false);
		txt_reg_drzava.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_drzava.setText("Dr\u017Eava");
		txt_reg_drzava.setBounds(265, 330, 195, 20);
		panel_registracija.add(txt_reg_drzava);
		
		textfield_reg_prezime = new JTextField();
		textfield_reg_prezime.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_prezime.setColumns(10);
		textfield_reg_prezime.setBounds(500, 210, 195, 33);
		textfield_reg_prezime.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_prezime);
		
		textfield_reg_grad = new JTextField();
		textfield_reg_grad.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_grad.setColumns(10);
		textfield_reg_grad.setBounds(500, 360, 195, 33);
		textfield_reg_grad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_grad);
		
		JTextPane txt_reg_prezime = new JTextPane();
		txt_reg_prezime.setEditable(false);
		txt_reg_prezime.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_prezime.setText("Prezime");
		txt_reg_prezime.setBounds(500, 180, 195, 20);
		panel_registracija.add(txt_reg_prezime);
		
		JTextPane txt_reg_pol = new JTextPane();
		txt_reg_pol.setEditable(false);
		txt_reg_pol.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_pol.setText("Pol");
		txt_reg_pol.setBounds(500, 400, 195, 20);
		panel_registracija.add(txt_reg_pol);
		
		JTextPane txt_reg_grad = new JTextPane();
		txt_reg_grad.setEditable(false);
		txt_reg_grad.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_grad.setText("Grad");
		txt_reg_grad.setBounds(510, 330, 195, 20);
		panel_registracija.add(txt_reg_grad);
		
		textfield_reg_adresa = new JTextField();
		textfield_reg_adresa.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_adresa.setColumns(10);
		textfield_reg_adresa.setBounds(30, 430, 195, 33);
		textfield_reg_adresa.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_adresa);
		
		JTextPane txt_reg_adresa = new JTextPane();
		txt_reg_adresa.setEditable(false);
		txt_reg_adresa.setText("Adresa");
		txt_reg_adresa.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_adresa.setBounds(30, 400, 195, 20);
		panel_registracija.add(txt_reg_adresa);
		
		textfield_reg_postanski_broj = new JTextField();
		textfield_reg_postanski_broj.setFont(new Font("Arial", Font.PLAIN, 15));
		textfield_reg_postanski_broj.setColumns(10);
		textfield_reg_postanski_broj.setBounds(265, 430, 195, 33);
		textfield_reg_postanski_broj.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(textfield_reg_postanski_broj);
		
		JTextPane txt_reg_postanski_broj = new JTextPane();
		txt_reg_postanski_broj.setEditable(false);
		txt_reg_postanski_broj.setText("Po\u0161tanski broj");
		txt_reg_postanski_broj.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_postanski_broj.setBounds(265, 400, 195, 20);
		panel_registracija.add(txt_reg_postanski_broj);
		
		passwordField_reg_lozinka = new JPasswordField();
		passwordField_reg_lozinka.setBounds(30, 290, 195, 33);
		passwordField_reg_lozinka.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		panel_registracija.add(passwordField_reg_lozinka);
		
		JTextPane txt_nazad_reg = new JTextPane();
		txt_nazad_reg.setText("<< Nazad");
		txt_nazad_reg.setForeground(new Color(0, 128, 128));
		txt_nazad_reg.setFont(new Font("Arial", Font.PLAIN, 25));
		txt_nazad_reg.setEditable(false);
		txt_nazad_reg.setBounds(30, 560, 135, 40);
		txt_nazad_reg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_registracija.add(txt_nazad_reg);
		
		//dugme za registraciju
		
		
		
		JTextPane txt_reg_lozinka_1 = new JTextPane();
		txt_reg_lozinka_1.setText("Potvrdi lozinku");
		txt_reg_lozinka_1.setFont(new Font("Arial", Font.BOLD, 15));
		txt_reg_lozinka_1.setEditable(false);
		txt_reg_lozinka_1.setBounds(265, 260, 195, 20);
		panel_registracija.add(txt_reg_lozinka_1);
		
		passwordField_reg_lozinka_1 = new JPasswordField();
		passwordField_reg_lozinka_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 128)));
		passwordField_reg_lozinka_1.setBounds(265, 287, 195, 33);
		panel_registracija.add(passwordField_reg_lozinka_1);
		
		JRadioButton rdbtn_reg_musko = new JRadioButton("Mu\u0161ko");
		rdbtn_reg_musko.setSelected(true);
		rdbtn_reg_musko.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtn_reg_musko.setBackground(Color.WHITE);
		rdbtn_reg_musko.setBounds(500, 430, 97, 33);
		panel_registracija.add(rdbtn_reg_musko);
		
		JRadioButton rdbtn_reg_zensko = new JRadioButton("\u017Densko");
		rdbtn_reg_zensko.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtn_reg_zensko.setBackground(Color.WHITE);
		rdbtn_reg_zensko.setBounds(598, 430, 97, 33);
		panel_registracija.add(rdbtn_reg_zensko);
		
		ButtonGroup btn_group_pol = new ButtonGroup();
	    btn_group_pol.add(rdbtn_reg_musko);
	    btn_group_pol.add(rdbtn_reg_zensko);
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		
		
		txt_nazad_reg.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txt_nazad_reg.setForeground(new Color(0, 128, 128));
			}
			public void mouseReleased(MouseEvent e) {
				txt_nazad_reg.setForeground(new Color(0, 128, 178));
			}
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_welcome);
				layeredPane.repaint();
				layeredPane.revalidate();
				
				textfield_reg_korisnicko_ime.setText(null);
				textfield_reg_telefon.setText(null);
				textfield_reg_ime.setText(null);
				textfield_reg_email.setText(null);
				textfield_reg_drzava.setText(null);
				textfield_reg_prezime.setText(null);
				textfield_reg_grad.setText(null);
				textfield_reg_adresa.setText(null);
				textfield_reg_postanski_broj.setText(null);
				passwordField_reg_lozinka.setText(null);
			}
		});
		
		JLabel txtpn_reg_error = new JLabel("Gre\u0161ka pri unosu!");
		txtpn_reg_error.setForeground(new Color(255, 0, 0));
		txtpn_reg_error.setHorizontalAlignment(SwingConstants.CENTER);
		txtpn_reg_error.setFont(new Font("Arial", Font.PLAIN, 18));
		txtpn_reg_error.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtpn_reg_error.setBounds(116, 487, 502, 32);
		panel_registracija.add(txtpn_reg_error);
		txtpn_reg_error.setVisible(false);
		
		JButton btn_registruj_se = new JButton("REGISTRUJ SE");
		btn_registruj_se.setForeground(Color.BLACK);
		btn_registruj_se.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		btn_registruj_se.setBackground(new Color(0, 128, 128));
		btn_registruj_se.setBounds(270, 530, 200, 60);
		
		btn_registruj_se.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpn_reg_error.setVisible(false);
				
				//verifying that the fields are not empty
				String korisnicko_ime = textfield_reg_korisnicko_ime.getText();
				String ime = textfield_reg_ime.getText();
				String prezime = textfield_reg_prezime.getText();
				String lozinka = String.valueOf(passwordField_reg_lozinka.getPassword());
				String lozinka_1 = String.valueOf(passwordField_reg_lozinka_1.getPassword());
				String telefon = textfield_reg_telefon.getText();
				String email = textfield_reg_email.getText();
				String adresa = textfield_reg_adresa.getText();
				String grad = textfield_reg_grad.getText();
				String drzava = textfield_reg_drzava.getText();
				String postanski_broj = textfield_reg_postanski_broj.getText();
				String pol = "M";
				
				if(rdbtn_reg_zensko.isSelected()) {
					pol = "Z";
				}
				
				boolean uslov = true;
				
				////verifying that the fields are not empty
				if(korisnicko_ime.trim().equals("") || ime.trim().equals("") || prezime.trim().equals("") || 
						lozinka.trim().equals("") || lozinka_1.trim().equals("") || email.trim().equals("") || 
						grad.trim().equals("") || drzava.trim().equals("") || postanski_broj.trim().equals("")) {
					
					txtpn_reg_error.setText("Jedno ili više obaveznih polja je prazno!");
					txtpn_reg_error.setVisible(true);
					uslov = false;
				}
				else if(!lozinka.equals(lozinka_1)) {

					System.out.println("lozinka1: " + lozinka);

					System.out.println("lozinka2: " + lozinka_1);
					txtpn_reg_error.setText("Lozinke se ne podudaraju!");
					txtpn_reg_error.setVisible(true);
					uslov = false;
				}
				if(uslov) {
					
					//provjera da li se u bazi nalazi to ime
					
					System.out.println("Provjera usernamea u bazi");
					boolean provjera = false;
					for (Kupac kupac : kupci) {
						if(kupac.getKorisnicko_ime().trim().equals(korisnicko_ime)) {
							System.out.println("Vec postoji korisnicko ime");
							provjera = true;
							txtpn_reg_error.setText("Korisnicko ime je zauzeto!");
							txtpn_reg_error.setVisible(true);
							break;
						}
					}
					if(!provjera) {
						System.out.println("Provjera je prosla smoothly");
						txtpn_reg_error.setVisible(false);
						PreparedStatement ps;
						String registerUserQuerry = "INSERT INTO `seminarski_ors1`.`kupac` (`korisnicko_ime`, `ime`, `prezime`, `lozinka`, `telefon`, `adresa`, `grad`,"
								+ "`drzava`, `postanski_broj`, `pol`, `email`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
						
						Database d = new Database();
						try {
							
							ps = d.getConn().prepareStatement(registerUserQuerry);
							ps.setString(1, korisnicko_ime);
							ps.setString(2, ime);
							ps.setString(3, prezime);
							ps.setString(4, lozinka_1);
							ps.setString(5, telefon);
							ps.setString(6, adresa);
							ps.setString(7, grad);
							ps.setString(8, drzava);
							ps.setString(9, postanski_broj);
							ps.setString(10, pol);
							ps.setString(11, email); 
							
							if(ps.executeUpdate() != 0) {
								txtpn_reg_error.setText("Vas racun je uspjesno kreiran!");
								trgovci = Trgovac.importTrgovce();
								kupci = Kupac.importKupce();
								txtpn_reg_error.setVisible(true);
							}
							else {
								txtpn_reg_error.setText("Greška! Provjerite unesene podatke!");
								txtpn_reg_error.setVisible(true);
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						 
					}
				}
				
				
			}
		});
		

		panel_registracija.add(btn_registruj_se);
		
		
	}
	
	private void uzimanjePodatakaIzBaze() {
		
		
		//Raspored je bitan
		//Proizvod uvijek ispred Artikal narudzbe
		//Artikal narudzbe uvijek na vrh
		
		Proizvod.importProizvode();
		ArtikalNarudzbe.importArtikleNarudzbe();
		
		Narudzba.importNarudzbe();
		kupci = Kupac.importKupce();
		ProdajnoMjesto.importProdajnaMjesta();
		trgovci = Trgovac.importTrgovce();
	}
	

	public static Kupac getTrenutniKupac() {
		return trenutniKupac;
	}

	public static Trgovac getTrenutniTrgovac() {
		return trenutniTrgovac;
	}
}
