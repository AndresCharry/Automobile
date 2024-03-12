package com.mycompany.automobile.persistence;

import com.mycompany.automobile.logic.Automobile;
import com.mycompany.automobile.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	public void remove(int id) {
		try {
			autoController.destroy(id);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Automobile findAutomobile(int id) {
		return autoController.findAutomobile(id);
	}

	public void edit(Automobile auto) {
		try {
			autoController.edit(auto);
		} catch (Exception ex) {
			Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
}
