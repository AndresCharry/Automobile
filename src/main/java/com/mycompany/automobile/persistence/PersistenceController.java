package com.mycompany.automobile.persistence;

import com.mycompany.automobile.logic.Automobile;
import java.util.List;

public class PersistenceController {
	
	AutomobileJpaController autoController = null;

	public PersistenceController() {
		autoController = new AutomobileJpaController();
	}

	public void create(Automobile auto) {
		autoController.create(auto);
	}

	public List<Automobile> findAutomobileEntities() {
		return autoController.findAutomobileEntities();
	}
	
	
	
}
