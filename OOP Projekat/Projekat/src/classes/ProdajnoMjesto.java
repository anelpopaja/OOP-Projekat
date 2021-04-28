package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;

public class ProdajnoMjesto {
    
    private int id;
    private String grad, drzava, adresa, telefon;
    
    private static ArrayList<ProdajnoMjesto> listaProdajnihMjesta;
    
    public ProdajnoMjesto(int id, String grad, String drzava, String adresa, String telefon) 
    {
        this.id = id;
        this.grad = grad;
        this.drzava = drzava;
        this.adresa = adresa;
        this.telefon = telefon;
    }
    
    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    
    public String getGrad() {return this.grad;}
    public void setGrad(String grad) {this.grad = grad;}
 
    public String getDrzava() {return this.drzava;}
    public void setDrzava(String drzava) {this.drzava = drzava;}
    
    public String getAdresa() {return this.adresa;}
    public void setAdresa(String adresa) {this.adresa = adresa;}
    
    public String getTelefon() {return this.telefon;}
    public void setTelefon(String telefon) {this.telefon = telefon;}
    
    public static ArrayList<ProdajnoMjesto> getListaProdajnihMjesta() {
		return listaProdajnihMjesta;
	}

	public static ArrayList<ProdajnoMjesto> importProdajnaMjesta() {
		listaProdajnihMjesta = new ArrayList<>();
        try {
            Database d = new Database();
            String query1 = "SELECT * FROM prodajno_mjesto";
            Statement st;
            st = d.getConn().createStatement();
            ResultSet rs = st.executeQuery(query1); 
            ProdajnoMjesto prodajnoMjesto;
            while(rs.next()) {
                prodajnoMjesto = new ProdajnoMjesto(rs.getInt("id"), rs.getString("grad"), rs.getString("drzava"), rs.getString("adresa"),
                        rs.getString("telefon"));  
                listaProdajnihMjesta.add(prodajnoMjesto);
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return listaProdajnihMjesta;
    }
    
    public String toString() {
        return "Prodajno mjesto {id : " + id + ", grad : "+ grad + ", drzava: "+ drzava +", adresa : "+ adresa + ", telefon : " + telefon + " }";
    }
    
    
 
}
