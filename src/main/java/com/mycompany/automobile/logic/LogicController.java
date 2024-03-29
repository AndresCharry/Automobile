package com.mycompany.automobile.logic;

import com.mycompany.automobile.persistence.PersistenceController;
import java.util.List;

public class LogicController {

	PersistenceController persistenceController = null;

	public LogicController() {
		persistenceController = new PersistenceController();
	}
		
	public void load(String model, String brand, String engine, String color,
		String plate, String numOfDoors) {
		Automobile auto = new Automobile();
		auto.setModel(model);
		auto.setBrand(brand);
		auto.setEngine(engine);
		auto.setColor(color);
		auto.setPlate(plate);
		auto.setNumberOfDoors(numOfDoors);
		
		persistenceController.create(auto);
		
	}

	public List<Automobile> bringingAutomobiles() {
		return persistenceController.findAutomobileEntities();
	}

	public void remove(int id) {
		persistenceController.remove(id);
	}

	public Automobile findAutomobile(int id) {
		return persistenceController.findAutomobile(id);
	}

	public void edit(Automobile auto, String model, String brand, String engine, String color,
		String plate, String numOfDoors) {
		auto.setModel(model);
		auto.setBrand(brand);
		auto.setEngine(engine);
		auto.setColor(color);
		auto.setPlate(plate);
		auto.setNumberOfDoors(numOfDoors);
		
		persistenceController.edit(auto);
	}
	
}
