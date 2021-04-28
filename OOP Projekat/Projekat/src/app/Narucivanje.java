package app;

import java.util.ArrayList;

import classes.Narudzba;
import classes.ProdajnoMjesto;
import classes.Trgovac;

public class Narucivanje {
	
	@SuppressWarnings("unused")
	private String drzava;
	@SuppressWarnings("unused")
	private Trgovac t;
	
	private ArrayList<String> listaDržava;
	private ArrayList<Narudzba> listaNarudzbiZaSve;
	@SuppressWarnings("unused")
	private ArrayList<Narudzba> listaNarudzbiUDrzavi;
	
	public Narucivanje(Trgovac t) {
		this.t = t;
		init();
	}
	public void init() {
		
	}
	
	
	public ArrayList<String> getListaDržava(){
		listaDržava = new ArrayList<String>();
		for(ProdajnoMjesto p: ProdajnoMjesto.getListaProdajnihMjesta()) {
			if(!(listaDržava.contains(p.getDrzava())))
				listaDržava.add(p.getDrzava());
		}
		return listaDržava;
	}
	public ArrayList<ProdajnoMjesto> izborProdajnihMjesta(Narudzba narudzba){
		if(!(listaDržava.contains(narudzba.getKupac().getDrzava()))){
			listaNarudzbiZaSve.add(narudzba);
		}
		
		return null;
	}
}
