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
	
	private ArrayList<String> listaDr�ava;
	private ArrayList<Narudzba> listaNarudzbiZaSve;
	@SuppressWarnings("unused")
	private ArrayList<Narudzba> listaNarudzbiUDrzavi;
	
	public Narucivanje(Trgovac t) {
		this.t = t;
		init();
	}
	public void init() {
		
	}
	
	
	public ArrayList<String> getListaDr�ava(){
		listaDr�ava = new ArrayList<String>();
		for(ProdajnoMjesto p: ProdajnoMjesto.getListaProdajnihMjesta()) {
			if(!(listaDr�ava.contains(p.getDrzava())))
				listaDr�ava.add(p.getDrzava());
		}
		return listaDr�ava;
	}
	public ArrayList<ProdajnoMjesto> izborProdajnihMjesta(Narudzba narudzba){
		if(!(listaDr�ava.contains(narudzba.getKupac().getDrzava()))){
			listaNarudzbiZaSve.add(narudzba);
		}
		
		return null;
	}
}
