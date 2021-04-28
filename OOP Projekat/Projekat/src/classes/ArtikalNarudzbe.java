package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;


public class ArtikalNarudzbe {
    
    private int narudzbaId, proizvodId, kolicina, id;
    private double cijenaPoKomadu;
    private Proizvod proizvod;
    
    private static ArrayList<ArtikalNarudzbe> listaArtikalaNarudzbe;
    
    public ArtikalNarudzbe(int narudzbaId, int proizvodId, int kolicina, double cijenaPoKomadu, int id) 
    {
        this.narudzbaId = narudzbaId;
        this.proizvodId = proizvodId;
        this.kolicina = kolicina;
        this.cijenaPoKomadu = cijenaPoKomadu;
        this.id = id;
        for(Proizvod p: Proizvod.getListaProizvoda())
        	if(proizvodId == p.getId()) {
        		this.proizvod = p;
        	}
    }
    
    public int getNarudzbaId() {return this.narudzbaId;}
    public void setNarudzbaId(int narudzbaId) {this.narudzbaId = narudzbaId;}
    
    public int getProizvodId() {return this.proizvodId;}
    public void setProizvodId(int proizvodId) {this.proizvodId = proizvodId;}
    
    public int getKolicina() {return this.kolicina;}
    public void setKolicina(int kolicina) {this.kolicina = kolicina;}
    
    public double getCijenaPoKomadu() {return this.cijenaPoKomadu;}
    public void setCijenaPoKomadu(int cijenaPoKomadu) {this.cijenaPoKomadu = cijenaPoKomadu;}
    
    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    
    
    public Proizvod getProizvod() {
		return proizvod;
    }

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	
	public static ArrayList<ArtikalNarudzbe> getListaArtikalaNarudzbe() {
		return listaArtikalaNarudzbe;
	}

	public static ArrayList<ArtikalNarudzbe> importArtikleNarudzbe() {
		listaArtikalaNarudzbe = new ArrayList<>();
        try {
            Database d = new Database();
            String query1 = "SELECT * FROM artikal_narudzbe";
            Statement st;
            st = d.getConn().createStatement();
            ResultSet rs = st.executeQuery(query1); 
            ArtikalNarudzbe an;
            while(rs.next()) {
                an = new ArtikalNarudzbe(rs.getInt("narudzba_id"),rs.getInt("proizvod_id"), rs.getInt("kolicina"), rs.getDouble("cijena_po_komadu"),
                        rs.getInt("id"));  
                listaArtikalaNarudzbe.add(an);
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return listaArtikalaNarudzbe;
    }
    
    public String toString() {
        return "Artikal_narudzbe {narudzba_Id : " + narudzbaId + ", proizvod_Id : "+ proizvodId + ", kolicina: "+ kolicina
                + ", cijena_po_komadu : "+ cijenaPoKomadu + ", id : "+ id +" }";
    }

    
    
    
    
    
 
}