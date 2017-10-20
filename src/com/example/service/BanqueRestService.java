package com.example.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import metier.entities.Compte;
import metier.session.IBanqueLocal;

@Stateless
@Path("/")
public class BanqueRestService {

	@EJB
	private IBanqueLocal metier;

	@POST
	@Path("/addCompte")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte addCompteBySolde(@PathParam(value="solde") double solde) {
		Compte compte = new Compte();
		compte.setSolde(solde);
		compte.setDateCreation(new Date());
		return metier.addCompte(compte);
	}

	@GET
	@Path("/comptes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte getCompte(@PathParam(value="code")  Long code) {
		return metier.getCompte(code);
	}

	@GET
	@Path("/comptes")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})// on peut utiliser les deux types json et xml 
																		//(voir Compte.java @XmlRootElement)
	public List<Compte> listComptes() {
		return metier.listComptes();
	}

	@PUT
	@Path("/verser")
	@Produces(MediaType.APPLICATION_JSON)
	public void verser(@PathParam(value="code") Long code, @PathParam(value="mt") double mt) {
		metier.verser(code, mt);
	}

	@PUT
	@Path("/retirer")
	@Produces(MediaType.APPLICATION_JSON)
	public void retirer(@PathParam(value="code") Long code, @PathParam(value="mt") double mt) {
		metier.retirer(code, mt);
	}

	@PUT
	@Path("/virement")
	@Produces(MediaType.APPLICATION_JSON)
	public void virement(@PathParam(value="cp1") Long cp1, @PathParam(value="cp2") Long cp2, @PathParam(value="mt") double mt) {
		metier.virement(cp1, cp2, mt);
	}
	
	
}
