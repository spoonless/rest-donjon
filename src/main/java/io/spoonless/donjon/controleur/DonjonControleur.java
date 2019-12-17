package io.spoonless.donjon.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.spoonless.donjon.service.DonjonService;
import io.spoonless.donjon.service.Salle;

@RestController
public class DonjonControleur {
	
	@Autowired
	private DonjonService donjon;
	
	@ExceptionHandler(SalleIntrouvableException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public SalleRepresentation handle(SalleIntrouvableException e) {
		SalleRepresentation salleRepresentation = new SalleRepresentation("La salle n'existe pas");
		return salleRepresentation;
	}

	@GetMapping(path="/", produces = "application/hal+json")
	public SalleRepresentation entrer() throws SalleIntrouvableException {
		return getSalle(donjon.getEntree().getId());
	}

	@GetMapping(path="/{id}", produces = "application/hal+json")
	public SalleRepresentation getSalle(@PathVariable String id) throws SalleIntrouvableException {
		Salle salle = donjon.getById(id).orElseThrow(SalleIntrouvableException::new);
		return new SalleRepresentation(salle);
	}
	
}
