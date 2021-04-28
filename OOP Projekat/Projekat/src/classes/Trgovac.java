package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;

public class Trgovac extends Korisnik{
	
	private int prodajno_mjesto_id;
	
	private static ArrayList<Trgovac> listaTrgovaca;
	
	public Trgovac(int id, String korisnicko_ime, String ime, String prezime, String lozinka, String pol,
			String telefon, String email, int prodajno_mjesto_id) {
		super(id, korisnicko_ime, ime, prezime, lozinka, pol, telefon, email);
		this.prodajno_mjesto_id = prodajno_mjesto_id;
	}
	
	public static ArrayList<Trgovac> importTrgovce() {
		listaTrgovaca = new ArrayList<>();
		try {
			Database d = new Database();
			String query1 = "SELECT * FROM trgovac";
			Statement st;
			st = d.getConn().createStatement();
			ResultSet rs = st.executeQuery(query1); 
			Trgovac trgovac;
			while(rs.next()) {
				trgovac = new Trgovac(rs.getInt("id"), rs.getString("korisnicko_ime"), rs.getString("ime"), rs.getString("prezime"),
						rs.getString("lozinka"), rs.getString("pol"), rs.getString("telefon"),  rs.getString("email"),
						rs.getInt("prodajno_mjesto_id")); 
				listaTrgovaca.add(trgovac);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaTrgovaca;
	}

	public int getProdajno_mjesto_id() {
		return prodajno_mjesto_id;
	}

	public void setProdajno_mjesto_id(int prodajno_mjesto_id) {
		this.prodajno_mjesto_id = prodajno_mjesto_id;
	}
	

	public static ArrayList<Trgovac> getListaTrgovaca() {
		return listaTrgovaca;
	}

	@Override
	public String toString() {
		return super.toString() + " prodajno_mjesto_id=" + prodajno_mjesto_id + "]\n";
	}


	
	
}
