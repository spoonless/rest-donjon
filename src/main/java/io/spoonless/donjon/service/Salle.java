package io.spoonless.donjon.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Salle {
	
	private String id;
	private String description;
	private Map<String, Salle> portes = new HashMap<String, Salle>();
	
	public Salle(String description) {
		this.id = UUID.randomUUID().toString();
		this.description = description;
	}
	
	public void addPorte(String description, Salle salle) {
		portes.put(description, salle);
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Map<String, Salle> getPortes() {
		return portes;
	}
	
	public boolean hasPortes() {
		return ! portes.isEmpty();
	}
	

}
