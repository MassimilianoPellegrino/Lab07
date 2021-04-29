package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getNercList());
		//System.out.println(model.trovaSequenza(8, 100, 2));
		for(PowerOutage p: model.trovaSequenza(7, 200, 4)) {
			System.out.println(p);
		}
	} 

}
