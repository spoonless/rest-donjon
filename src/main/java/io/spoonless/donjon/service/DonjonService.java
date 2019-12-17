package io.spoonless.donjon.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DonjonService {
	
	private Salle entree;
	private Map<String, Salle> salles = new HashMap<String, Salle>();

	public DonjonService() {
		creerDonjon();
	}
	
	private Salle salle(String description) {
		Salle salle = new Salle(description);
		salles.put(salle.getId(), salle);
		return salle;
	}

	private void creerDonjon() {
		Salle salle1 = salle("Vous marchez le long d'un couloir et vous arrivez à une intersection");
		Salle salle2 = salle("Vous arrivez dans une salle au plafond bas avec un couloir à gauche et une porte en face");
		Salle salle21 = salle("Le couloir se prolonge jusqu'à une bifurcation.");
		Salle salle22 = salle("Vous entrez dans une salle avec au centre un puit");
		Salle salle221 = salle("Vous glissez lors de la descente du puit et vous vous écrasez au fond. Votre aventure s'achève ici !");
		Salle salle3 = salle("Vous arrivez au bout du couloir. Face à vous une porte !");
		Salle salle31 = salle("Vous entrez dans une salle mal éclairée. Un escalier mène au niveau inférieur.");
		Salle salleInf1 = salle("Vous arrivez dans une vaste salle : c'est la salle du trésor ! Malheureusement, le dragon qui garde le trésor est réveillé et vous a vu. Il crache son souffle de feu dans votre direction. Votre aventure s'achève ici !");
		
		salle1.addPorte("le couloir à gauche", salle2);
		salle1.addPorte("le couloir à droite", salle3);
		salle2.addPorte("le couloir à gauche", salle21);
		salle2.addPorte("la porte en face", salle22);
		salle21.addPorte("le couloir à gauche", salle3);
		salle21.addPorte("le couloir à droite", salle3);
		salle22.addPorte("le puit", salle221);
		salle3.addPorte("la porte en face", salle31);
		salle31.addPorte("l'escalier vers le niveau inférieur", salleInf1);
		
		this.entree = salle1;
	}
	
	public Optional<Salle> getById(String id) {
		return Optional.ofNullable(salles.get(id));
	}
	
	public Salle getEntree() {
		return entree;
	}

}
