package com.mycompany.automobile.persistence;

import com.mycompany.automobile.logic.Automobile;

public class PersistenceController {
	
	AutomobileJpaController autoController = null;

	public PersistenceController() {
		autoController = new AutomobileJpaController();
	}

	public void create(Automobile auto) {
		autoController.create(auto);
	}
	
	
	
}
