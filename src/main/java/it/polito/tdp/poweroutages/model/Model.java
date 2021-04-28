package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	int sommaClientiMigliore;
	List<PowerOutage> soluzioneMigliore;
	
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
		itera(0, 0, new ArrayList<PowerOutage>(), this.getPowerList(nerc_id), 0, maxOre, 0, maxAnni);
		
		return soluzioneMigliore;
	}
	
	public void itera(int livello, int indice, List<PowerOutage> parziale, List<PowerOutage> powerList, int sommaClienti, int maxOre, double sommaOre, int maxAnni) {
		
		if(parziale.size()>0 && (livello==powerList.size() || (sommaOre+powerList.get(indice).getHours())>maxOre || 
				(parziale.get(0).getYear()-powerList.get(indice).getYear())>maxAnni)) {
				
			if(sommaClienti>sommaClientiMigliore) {
				sommaClientiMigliore=sommaClienti;
				soluzioneMigliore = new ArrayList<>(parziale);
				return;
			}
			
		}
		
		
		if(indice<powerList.size()) {
			
			parziale.add(powerList.get(indice));
			
			itera(livello, indice+1, parziale, powerList, sommaClienti+powerList.get(indice).getCustomers_affected(), maxOre, 
					sommaOre+powerList.get(indice).getHours(), maxAnni);
			
			parziale.remove(powerList.get(indice));
		
		}else{
			
			itera(livello+1, livello+1, new ArrayList<PowerOutage>(), powerList, 0, maxOre, 0, maxAnni);
		}
		
	}
	
	

}
