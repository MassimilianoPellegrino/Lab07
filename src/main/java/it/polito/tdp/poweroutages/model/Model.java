package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	int sommaClientiMigliore;
	List<PowerOutage> soluzioneMigliore;
	List<PowerOutage> powerList;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	 
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutage> getPowerList(int nerc_id){
		return podao.getPowerList(nerc_id);
	}
	
	public List<PowerOutage> trovaSequenza(int nerc_id, int maxOre, int maxAnni){
		
		sommaClientiMigliore = 0;
		powerList = this.getPowerList(nerc_id);

		itera(0, new ArrayList<PowerOutage>(), maxOre, maxAnni);
		
		return soluzioneMigliore;
	}
	
	public void itera(int livello, List<PowerOutage> parziale, int maxOre, int maxAnni) {
		
		System.out.println(livello+"	"+parziale.size());
		
		double ore = sommaOre(parziale);		
		int anni = diffAnni(parziale);
		
		if(ore>maxOre || anni>maxAnni)
			return;
		
		if(livello == this.powerList.size())
			return;
		
		int clienti = sommaClienti(parziale);
		
		if(clienti>this.sommaClientiMigliore) {
			sommaClientiMigliore = clienti;
			soluzioneMigliore = new ArrayList<>(parziale);
			return;
		}
		
		parziale.add(this.powerList.get(livello));
		itera(livello+1, parziale, maxOre, maxAnni);
		
		parziale.remove(this.powerList.get(livello));
		itera(livello+1, parziale, maxOre, maxAnni);
		
		
	}

	private int sommaClienti(List<PowerOutage> parziale) {
		int i = 0;
		for(PowerOutage p: parziale)
			i+=p.getCustomers_affected();
		
		return i;
	}
	

	private int diffAnni(List<PowerOutage> parziale) {
		int d = 0;
		if(parziale.size()>0) {
			List<PowerOutage> lista = new ArrayList<>(parziale);
			Collections.sort(lista, new ComparatoreAnni());
		
			d = lista.get(lista.size()-1).getYear()-lista.get(0).getYear();
		}
		return d;
	}

	private double sommaOre(List<PowerOutage> parziale) {
		double i = 0.0;
		for(PowerOutage p: parziale)
			i+=p.getHours();
		
		return i;
	}
	
	

}
