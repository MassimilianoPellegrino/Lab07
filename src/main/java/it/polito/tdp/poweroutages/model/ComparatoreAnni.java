package it.polito.tdp.poweroutages.model;

import java.util.Comparator;

public class ComparatoreAnni implements Comparator<PowerOutage> {

	@Override
	public int compare(PowerOutage o1, PowerOutage o2) {
		// TODO Auto-generated method stub
		return o1.getYear()-o2.getYear();
	}

}
