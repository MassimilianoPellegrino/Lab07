package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

import it.polito.tdp.poweroutages.model.PowerOutage;

public class TestPowerOutagesDAO {
 
	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			//System.out.println(dao.getNercList()) ;
			for(PowerOutage p: dao.getPowerList(8)) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}
