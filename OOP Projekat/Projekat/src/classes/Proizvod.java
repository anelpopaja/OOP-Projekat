package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;


public class Proizvod {
    private int id;
    private String naziv , opis;
    private double cijena;
    
    private static ArrayList<Proizvod> listaProizvoda;
    
    
	public Proizvod(int id, String naziv, String opis, double cijena) 
    {
        this.id = id;
        this.naziv = naziv;
        this.opis = null;
        this.cijena = cijena;
    }
    
    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    
    public String getNaziv() {return this.naziv;}
    public void setPrezime(String naziv) {this.naziv = naziv;}
    
    public String getOpis() {return this.opis;}
    public void setOpis() {this.opis = null;}
    
    public double getCijena() {return this.cijena;} 
    
    public void setCijena(double cijena) {this.cijena = cijena;}
    
    public static ArrayList<Proizvod> getListaProizvoda() {
		return listaProizvoda;
	}


    
    public static ArrayList<Proizvod> importProizvode() {
    	listaProizvoda = new ArrayList<>();
        try {
        	Database d = new Database();
            String query1 = "SELECT * FROM proizvod";
            Statement st;
            st = d.getConn().createStatement();
            ResultSet rs = st.executeQuery(query1); 
            Proizvod proizvod;
            while(rs.next()) {
                proizvod = new Proizvod(rs.getInt("id"), rs.getString("naziv"), rs.getString("opis"), rs.getDouble("cijena"));  
                listaProizvoda.add(proizvod);
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return listaProizvoda;
    }
    public String toString() {
        return "Proizvod {id : " + id + ", naziv : "+ naziv + ", opis: "+ opis +", cijena : "+ cijena + " }";
    }
    
 
}