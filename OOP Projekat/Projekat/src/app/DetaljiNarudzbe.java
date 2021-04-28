package app;

import javax.swing.JFrame;

import classes.ArtikalNarudzbe;
import classes.Kupac;
import classes.Narudzba;
import classes.ProdajnoMjesto;
import classes.Trgovac;
import database.Database;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;


import Util.Status;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DetaljiNarudzbe {

	private JFrame frameDetaljiNarudzbe;
	private Narudzba narudzba;
	private Kupac kupac;
	private Trgovac trgovac;
	@SuppressWarnings("unused")
	private int size;
	private JTable table;
	private static ArrayList<ArtikalNarudzbe> listaArtikala;
	/**
	 * Launch the application.
	 */
	public DetaljiNarudzbe(Narudzba n, Kupac k) {
		narudzba = n;
		kupac = k;
		initialize();
		frameDetaljiNarudzbe.setVisible(true);
	}

	public DetaljiNarudzbe(Narudzba n, Trgovac t) {
		this.narudzba = n;
		this.trgovac = t;
		this.kupac = n.getKupac();
		initialize();
		frameDetaljiNarudzbe.setVisible(true);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameDetaljiNarudzbe = new JFrame();
		frameDetaljiNarudzbe.setResizable(false);
		frameDetaljiNarudzbe.getContentPane().setBackground(Color.WHITE);
		frameDetaljiNarudzbe.setBounds(100, 100, 700, 477);
		frameDetaljiNarudzbe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameDetaljiNarudzbe.getContentPane().setLayout(null);
		frameDetaljiNarudzbe.setLocationRelativeTo(null);

		JPanel panelDetaljiNarudzbeHeading = new JPanel();
		panelDetaljiNarudzbeHeading.setBackground(new Color(0, 128, 128));
		panelDetaljiNarudzbeHeading.setBounds(75, 11, 550, 70);
		frameDetaljiNarudzbe.getContentPane().add(panelDetaljiNarudzbeHeading);
		panelDetaljiNarudzbeHeading.setLayout(new BorderLayout(0, 0));

		JLabel lblDetaljiNarudzbe = new JLabel("Detalji narudzbe");
		lblDetaljiNarudzbe.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDetaljiNarudzbe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetaljiNarudzbe.setForeground(Color.WHITE);
		lblDetaljiNarudzbe.setFont(new Font("Candara", Font.BOLD, 35));
		lblDetaljiNarudzbe.setBackground(Color.WHITE);
		panelDetaljiNarudzbeHeading.add(lblDetaljiNarudzbe, BorderLayout.CENTER);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(26, 95, 410, 170);
		frameDetaljiNarudzbe.getContentPane().add(panel1);

		JLabel labelStatusNarudzbe = new JLabel("Status narud\u017Ebe:");
		labelStatusNarudzbe.setVerticalAlignment(SwingConstants.TOP);
		labelStatusNarudzbe.setFont(new Font("Candara", Font.BOLD, 20));
		labelStatusNarudzbe.setBounds(10, 20, 155, 20);
		panel1.add(labelStatusNarudzbe);

		JLabel labelStatusNarudzbe1 = new JLabel("Na \u010Dekanju - Trgovac");
		labelStatusNarudzbe1.setVerticalAlignment(SwingConstants.TOP);
		labelStatusNarudzbe1.setFont(new Font("Candara", Font.PLAIN, 20));
		labelStatusNarudzbe1.setBounds(195, 20, 223, 20);
		panel1.add(labelStatusNarudzbe1);

		JLabel labelDatumNarudzbe = new JLabel("Datum narud\u017Ebe:");
		labelDatumNarudzbe.setVerticalAlignment(SwingConstants.TOP);
		labelDatumNarudzbe.setFont(new Font("Candara", Font.BOLD, 20));
		labelDatumNarudzbe.setBounds(10, 60, 155, 20);
		panel1.add(labelDatumNarudzbe);

		String pattern = "yyyy-MM-dd";

		DateFormat df = new SimpleDateFormat(pattern);

		System.out.println(narudzba.getDatumNarudzbe());
		Date datum = (Date) narudzba.getDatumNarudzbe();   

		System.out.println("novi date: " + datum);
		String datumString = df.format(datum);
		System.out.println("datum String: " + datumString);



		JLabel labelDatumNarudzbe1 = new JLabel(datumString);
		labelDatumNarudzbe1.setVerticalAlignment(SwingConstants.TOP);
		labelDatumNarudzbe1.setFont(new Font("Candara", Font.PLAIN, 20));
		labelDatumNarudzbe1.setBounds(195, 60, 223, 20);
		panel1.add(labelDatumNarudzbe1);

		JLabel labelDatumIsporuke = new JLabel("Datum isporuke:");
		labelDatumIsporuke.setVerticalAlignment(SwingConstants.TOP);
		labelDatumIsporuke.setFont(new Font("Candara", Font.BOLD, 20));
		labelDatumIsporuke.setBounds(10, 100, 155, 20);
		panel1.add(labelDatumIsporuke);

		if(narudzba.getDatumIsporuke() == null) {}
		JLabel labelDatumIsporuke1 = new JLabel("---");
		labelDatumIsporuke1.setVerticalAlignment(SwingConstants.TOP);
		labelDatumIsporuke1.setFont(new Font("Candara", Font.PLAIN, 20));
		labelDatumIsporuke1.setBounds(195, 100, 223, 20);
		panel1.add(labelDatumIsporuke1);
		if(narudzba.getDatumIsporuke() != null) {

			datum = (Date) narudzba.getDatumIsporuke();   

			datumString = df.format(datum);
			labelDatumIsporuke1.setText(datumString);
		}

		JLabel labelProdajnoMjesto = new JLabel("Prodajno mjesto:");
		labelProdajnoMjesto.setVerticalAlignment(SwingConstants.TOP);
		labelProdajnoMjesto.setFont(new Font("Candara", Font.BOLD, 20));
		labelProdajnoMjesto.setBounds(10, 140, 155, 20);
		panel1.add(labelProdajnoMjesto);

		JLabel labelProdajnoMjesto1 = new JLabel("---");
		labelProdajnoMjesto1.setVerticalAlignment(SwingConstants.TOP);
		labelProdajnoMjesto1.setFont(new Font("Candara", Font.PLAIN, 20));
		labelProdajnoMjesto1.setBounds(195, 140, 223, 20);
		panel1.add(labelProdajnoMjesto1);
		if(narudzba.getTrgovacId() != 0) {
			int pmId =narudzba.getTrgovac().getProdajno_mjesto_id();
			for(ProdajnoMjesto pm: ProdajnoMjesto.getListaProdajnihMjesta()) {
				if(pm.getId() == pmId) {

					labelProdajnoMjesto1.setText(pm.getDrzava() + " " + pm.getGrad());
				}
			}

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 286, 625, 141);
		frameDetaljiNarudzbe.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);



		JPanel panelDetaljiNarudzbeBtn = new JPanel();
		/*
		 * 
		 * panelDetaljiNarudzbeBtn.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseEntered(MouseEvent e) {
		 * panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110,
		 * 110)); }
		 * 
		 * @Override public void mouseExited(MouseEvent e) {
		 * if(panelDetaljiNarudzbeBtn.getBackground() != new Color(0,
		 * 100, 100)) panelDetaljiNarudzbeBtn.setBackground(new Color(0,
		 * 128, 128)); }
		 * 
		 * @Override public void mousePressed(MouseEvent e) {
		 * panelDetaljiNarudzbeBtn.setBackground(new Color(0, 100,
		 * 100)); // //this is to cancel the order // }
		 * 
		 * @Override public void mouseReleased(MouseEvent e) {
		 * panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110,
		 * 110)); } });
		 */
		panelDetaljiNarudzbeBtn.setLayout(null);
		panelDetaljiNarudzbeBtn.setBorder(null);
		panelDetaljiNarudzbeBtn.setBackground(new Color(0, 128, 128));
		panelDetaljiNarudzbeBtn.setBounds(492, 169, 100, 50);
		frameDetaljiNarudzbe.getContentPane().add(panelDetaljiNarudzbeBtn);



		JLabel lblOpcija = new JLabel("Otka\u017Ei");
		lblOpcija.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcija.setForeground(Color.WHITE);
		lblOpcija.setFont(new Font("Candara", Font.BOLD, 25));
		lblOpcija.setBackground(new Color(0, 100, 0));
		lblOpcija.setBounds(0, 11, 100, 39);
		panelDetaljiNarudzbeBtn.add(lblOpcija);

		JLabel lblTekstOpcije = new JLabel("Otkazivanje naru\u017Ebe");
		lblTekstOpcije.setVerticalAlignment(SwingConstants.TOP);
		lblTekstOpcije.setFont(new Font("Candara", Font.BOLD, 20));
		lblTekstOpcije.setBounds(457, 123, 187, 20);
		frameDetaljiNarudzbe.getContentPane().add(lblTekstOpcije);

		Object [] options = { "Da", "Ne" };

		//Ako je prikaz za kupca
		if(trgovac == null) {
			lblOpcija.setText("Otka\u017Ei");
			lblTekstOpcije.setText("Otkazivanje naru\u017Ebe");

			panelDetaljiNarudzbeBtn.addMouseListener(new MouseAdapter() {
				
					@Override
					public void mouseEntered(MouseEvent e) {
						panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						if(panelDetaljiNarudzbeBtn.getBackground() != new Color(0, 100, 100))
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 128, 128));
					}
					
					@Override
					public void mouseReleased(MouseEvent e) {
						panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
					}
				@Override
				public void mousePressed(MouseEvent e) {
					if(narudzba.getStatus() != Status.NA_CEKANJU) {
						JOptionPane.showMessageDialog(null, "Ne možete otkazati ovu narudžbu!", "Otkazivanje", JOptionPane.INFORMATION_MESSAGE);
					}else {

						//
						//this is to cancel the order
						// confirmation message
						if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da želite otkazati narudžbu?", "Otkazivanje",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
								options, options[0]) == JOptionPane.YES_OPTION) {
							otkaziNarudzbu(narudzba.getId());
							frameDetaljiNarudzbe.dispose();
							//System.out.println("Making new list of orders");
							//DashboardKupac.setNarudzbeKupca(kupac.getListaNarudzbiKupca());
							System.out.println("Forming the table");
							DashboardKupac.formTable(0);
						}

					}
				}
			});


		}
		//ako je prikaz za trgovca
		else {
			if(narudzba.getTrgovacId() == 0) {
				lblOpcija.setText("Prihvati");
				lblTekstOpcije.setText("Prihvatanje Narudzbe");

				panelDetaljiNarudzbeBtn.addMouseListener(new MouseAdapter() {

					
					@Override
					public void mouseEntered(MouseEvent e) {
						panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						if(panelDetaljiNarudzbeBtn.getBackground() != new Color(0, 100, 100))
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 128, 128));
					}
					
					@Override
					public void mouseReleased(MouseEvent e) {
						panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
					}
					@Override
					public void mousePressed(MouseEvent e) {
						panelDetaljiNarudzbeBtn.setBackground(new Color(0, 100, 100));



						prihvatiNarudzbu();
						DashboardTrgovac.formTable(DashboardTrgovac.getListaNarudzbi());
						//postavlja id trgovca narudzbe

					}
				});
			}
			else {



				if(narudzba.getDatumIsporuke() == null) {
					lblOpcija.setText("Isporuči");
					lblTekstOpcije.setText("Isporuka");
					lblTekstOpcije.setHorizontalAlignment(SwingConstants.CENTER);

					panelDetaljiNarudzbeBtn.addMouseListener(new MouseAdapter() {

						
						@Override
						public void mouseEntered(MouseEvent e) {
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							if(panelDetaljiNarudzbeBtn.getBackground() != new Color(0, 100, 100))
								panelDetaljiNarudzbeBtn.setBackground(new Color(0, 128, 128));
						}
						
						@Override
						public void mouseReleased(MouseEvent e) {
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
						}
						@Override
						public void mousePressed(MouseEvent e) {
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 100, 100));
							//
							//this is to deliver the order
							isporuci();
							//samo stavi datum narudzbe na danasnji
							frameDetaljiNarudzbe.revalidate();
							DashboardTrgovac.formTable(DashboardTrgovac.getListaNarudzbi());

						}
					});
				}

				else{
					lblOpcija.setText("Ažuriraj");
					lblTekstOpcije.setText("Ažuriraj datum isporuke");
					panelDetaljiNarudzbeBtn.addMouseListener(new MouseAdapter() {

						
						@Override
						public void mouseEntered(MouseEvent e) {
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							if(panelDetaljiNarudzbeBtn.getBackground() != new Color(0, 100, 100))
								panelDetaljiNarudzbeBtn.setBackground(new Color(0, 128, 128));
						}
						
						@Override
						public void mouseReleased(MouseEvent e) {
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 110, 110));
						}
						@Override
						public void mousePressed(MouseEvent e) {
							panelDetaljiNarudzbeBtn.setBackground(new Color(0, 100, 100));
							if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da želite promijeniti datum isporuke?", "Datum",
									JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
									options, options[0]) == JOptionPane.YES_OPTION) {
								String s =  (String) JOptionPane.showInputDialog(null, "Unesite datum isporuke u formatu \"yyyy-mm-dd\".", "Napomena", JOptionPane.PLAIN_MESSAGE, null, null, null);
								System.out.println(s);
								java.util.Date datum1;
								try {
									datum1 = new SimpleDateFormat("yyyy-MM-dd").parse(s);
									promijeniDatum(datum1);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								DashboardTrgovac.formTable(DashboardTrgovac.getListaNarudzbi());
								Date datum = (Date) narudzba.getDatumIsporuke();   
								String datumString = df.format(datum);
								labelDatumIsporuke1.setText(datumString);
							}

						}
					});

				}

			}

			//ponovo iz baze dovuci narudzbe
			//ponovno formirati sve tabele za trgovca

		}
		formTable();
	}
	public double getVrijednost () {
		double sum = 0.0;
		for(ArtikalNarudzbe temp : listaArtikala)
			sum += temp.getKolicina() * temp.getCijenaPoKomadu(); 
		return sum;
	}

	private void formTable() {
		DefaultTableModel dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			boolean [] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table.setModel(dtm);
		dtm.setColumnIdentifiers(new String [] { "ID proizvoda", "Naziv proizvoda", "Cijena", "Koli\u010dina", "Iznos" });
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		listaArtikala = new ArrayList<ArtikalNarudzbe>();
		for(ArtikalNarudzbe temp : ArtikalNarudzbe.getListaArtikalaNarudzbe()) {
			if(temp.getNarudzbaId() == narudzba.getId()) {
				listaArtikala.add(temp);
			}
		}

		for(ArtikalNarudzbe temp : listaArtikala) {
			dtm.addRow(new Object [] { temp.getProizvod().getId(), temp.getProizvod().getNaziv(), String.format("%.2f", temp.getCijenaPoKomadu()), String.format("%.2f", temp.getKolicina() + 0.0), String.format("%.2f", temp.getKolicina() * temp.getCijenaPoKomadu()) });
			System.out.println(temp.getProizvod().getId() + temp.getProizvod().getNaziv() + String.format("%.2f", temp.getCijenaPoKomadu()) + String.format("%.2f", temp.getKolicina() + 0.0) + String.format("%.2f", temp.getKolicina() * temp.getCijenaPoKomadu()) );
		}
		dtm.addRow(new Object [] { "", "", "", "UKUPNO:", String.format("%.2f", getVrijednost())});
		System.out.println("table formed!");
		System.out.println(listaArtikala);
	}



	private void otkaziNarudzbu (int id) {//ova f-ja se poziva u frejmu prikaz narudzbe i uzima trenutni id tj id narudzbe koja je //selektovana u tabeli

		izbrisiNarudzbu(id);
		izbrisiArtikleNarudzbe(narudzba);
		Narudzba.importNarudzbe();
		frameDetaljiNarudzbe.dispose();

	}
	private void prihvatiNarudzbu () {
		Object [] options = { "Da", "Ne" };
		if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da želite prihvatiti narudžbu?", "Datum",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
				options, options[0]) == JOptionPane.YES_OPTION) {
			izbrisiNarudzbu(narudzba.getId());
			dodajNarudzbu(narudzba.getId(), kupac.getId(), trgovac.getId(), narudzba.getDatumNarudzbe(), null, narudzba.getNapomena());
			Narudzba.importNarudzbe();
			frameDetaljiNarudzbe.dispose();
			DashboardTrgovac.popuniListuNarudzbi();
			DashboardTrgovac.formTable(DashboardTrgovac.getListaNarudzbi());
		}
		else {}


	}
	private void promijeniDatum (java.util.Date datum1) {


		izbrisiNarudzbu(narudzba.getId());
		dodajNarudzbu(narudzba.getId(), kupac.getId(), trgovac.getId(), narudzba.getDatumNarudzbe(), datum1, narudzba.getNapomena());
		Narudzba.importNarudzbe();
		DashboardTrgovac.popuniListuNarudzbi();
		DashboardTrgovac.formTable(DashboardTrgovac.getListaNarudzbi());
		frameDetaljiNarudzbe.dispose();


	}
	private void isporuci () {
		Object [] options = { "Da", "Ne" };
		if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da želite isporuciti ovu narudžbu?", "Prihvacanje",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
				options, options[0]) == JOptionPane.YES_OPTION) {

			izbrisiNarudzbu(narudzba.getId());
			@SuppressWarnings("unused")
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
			@SuppressWarnings("unused")
			java.util.Date date = new java.util.Date();
			dodajNarudzbu(narudzba.getId(), kupac.getId(), trgovac.getId(), narudzba.getDatumNarudzbe(), java.sql.Date.valueOf(LocalDate.now()), narudzba.getNapomena());
			Narudzba.importNarudzbe();
			DashboardTrgovac.popuniListuNarudzbi();
			frameDetaljiNarudzbe.dispose();
			DashboardTrgovac.formTable(DashboardTrgovac.getListaNarudzbi());
		}
		else {

		}
	}

	private void izbrisiNarudzbu(int id) {
		try {
			Database d = new Database();
			String query = "DELETE from narudzba where id = ?";
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
	private void izbrisiArtikleNarudzbe(Narudzba n) {
		try {
			Database d = new Database();
			String query = "DELETE from artikal_narudzbe where narudzba_id = ?";
			@SuppressWarnings("unused")
			Statement st = d.getConn().createStatement();

			PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
			preparedStmt.setInt(1, n.getId());

			// execute the preparedstatement
			preparedStmt.execute();

		}
		catch (SQLException e) {
			System.err.println(e);
		}
	}

	public static void dodajNarudzbu(int id, int kupac_id, int trgovac_id, java.util.Date date, java.util.Date datumIsporuke, String napomena) {
		try {
			Database d = new Database();
			String query = " insert into narudzba (id, kupac_id, trgovac_id, datum_narudzbe, datum_isporuke, napomena)"
					+ " values (?, ?, ?, ?, ?, ?)";
			@SuppressWarnings("unused")
			Statement st = d.getConn().createStatement();

			java.sql.Date sqlDate1 = new java.sql.Date(date.getTime()); 

			java.sql.Date sqlDate2;
			if(datumIsporuke != null)
				sqlDate2 = new java.sql.Date(datumIsporuke.getTime()); 
			else
				sqlDate2 = null;

			PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.setInt (2, kupac_id );
			preparedStmt.setInt (3, trgovac_id);
			preparedStmt.setDate (4,  (java.sql.Date) sqlDate1);
			preparedStmt.setDate (5,  (java.sql.Date) sqlDate2);
			preparedStmt.setString (6, napomena);


			// execute the preparedstatement
			preparedStmt.execute();

		}
		catch (SQLException e) {
			System.err.println(e);
		}

	}
}
