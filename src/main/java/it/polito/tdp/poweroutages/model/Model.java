package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	int sommaClientiMigliore;
	List<PowerOutage> soluzioneMigliore;
	List<PowerOutage> powerList;
	int maxOre;
	int maxAnni;
	
	public Model() {
		this.podao = new PowerOutageDAO();
	}
	 
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutage> getPowerList(int nerc_id){
		return podao.getPowerList(nerc_id);
	}
	
	public List<PowerOutage> trovaSequenza(int nerc_id, int maxOre, int maxAnni){
		
		this.maxOre=maxOre;
		this.maxAnni=maxAnni;
		this.sommaClientiMigliore = 0;
		this.powerList = this.getPowerList(nerc_id);

		itera(0, new ArrayList<PowerOutage>());
		
		return soluzioneMigliore;
	}
	
	public void itera(int livello, List<PowerOutage> parziale) {
		
		//System.out.println(parziale.size());
		
		double ore = sommaOre(parziale);		
		int anni = diffAnni(parziale);
		int clienti = sommaClienti(parziale);
		
		if(ore>maxOre || anni>maxAnni)
			return;
		
		if(clienti>this.sommaClientiMigliore) {
			sommaClientiMigliore = clienti;
			soluzioneMigliore = new ArrayList<>(parziale);
		}
		
		if(livello == this.powerList.size())
			return;
		
		PowerOutage po = this.powerList.get(livello);
		
		if(!parziale.contains(po)) {
			parziale.add(po);
			itera(livello+1, parziale);
			parziale.remove(po);	
		}
		itera(livello+1, parziale);
		
		
	}

	public int sommaClienti(List<PowerOutage> parziale) {
		int i = 0;
		
		if(parziale!=null) {
			for(PowerOutage p: parziale)
				i+=p.getCustomers_affected();
		}
		return i;
	}
	
	public class ComparatoreAnni implements Comparator<PowerOutage> {

		@Override
		public int compare(PowerOutage o1, PowerOutage o2) {
			// TODO Auto-generated method stub
			return o1.getYear()-o2.getYear();
		}

	}

	public int diffAnni(List<PowerOutage> parziale) {
		int d = 0;
		
		if(parziale!=null && parziale.size()>1) {
			List<PowerOutage> lista = new ArrayList<>(parziale);
			Collections.sort(lista, new ComparatoreAnni());
		
			d = lista.get(lista.size()-1).getYear()-lista.get(0).getYear();
		}
		return d;
	}

	public double sommaOre(List<PowerOutage> parziale) {
		double i = 0.0;
		
		if(parziale!=null) {
			for(PowerOutage p: parziale)
				i+=p.getHours();
		}
		return i;
	}
	
	

}
