package io.spoonless.donjon.controleur;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import io.spoonless.donjon.service.Salle;

public class SalleRepresentation extends RepresentationModel<SalleRepresentation> {
	
	private static final String ENTREE_LINK_REL = "http://donjon.fr/entree";
	private static final String PORTE_LINK_REL = "http://donjon.fr/porte";
	private String description;

	public SalleRepresentation(Salle salle) {
		this.description = salle.getDescription();
		this.add(linkTo(DonjonControleur.class).slash(salle.getId()).withSelfRel());
		for(var porte : salle.getPortes().entrySet()) {
			Link porteLink = linkTo(DonjonControleur.class).slash(porte.getValue().getId())
					                                       .withRel(PORTE_LINK_REL)
					                                       .withName(porte.getKey());
			this.add(porteLink);
		}
		if (!salle.hasPortes()) {
			this.add(genererLinkEntree());
		}
	}
	
	public SalleRepresentation(String description) {
		this.description = description;
		this.add(genererLinkEntree());
	}

	private Link genererLinkEntree() {
		return linkTo(DonjonControleur.class).withRel(ENTREE_LINK_REL).withName("Retour à l'entrée du donjon");
	}
	
	public String getDescription() {
		return description;
	}

}
