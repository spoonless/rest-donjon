package io.spoonless.donjon.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class DonjonService {
	
	private Salle entree;
	private Map<String, Salle> salles;

	public DonjonService() {
		creerDonjon();
	}

	private void creerDonjon() {
		Salle salle1 = new Salle("Vous marchez le long d'un couloir et vous arrivez à une intersection");
		
		Salle salle2 = new Salle("Vous arrivez dans une salle au plafond bas avec un couloir à gauche et une porte en face");
		
		Salle salle3 = new Salle("Vous arrivez au bout du couloir. Face à vous une porte !");
		
		salle1.addPorte("le couloir à gauche", salle2);
		salle1.addPorte("le couloir à droite", salle3);
		
		this.entree = salle1;
		this.salles = Arrays.asList(salle1, salle2, salle3)
				            .stream()
				            .collect(Collectors.toMap(Salle::getId, Function.identity()));
	}
	
	public Optional<Salle> getById(String id) {
		return Optional.ofNullable(salles.get(id));
	}
	
	public Salle getEntree() {
		return entree;
	}

}
