package br.hgec.eb.ces.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.hgec.eb.ces.entidades.Carro;

@Path("/controlerest")
public class CesRest {
	
	private EntityManager entityManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carro> listar() {
		Query query = this.entityManager.createQuery("FROM Carro c");
		return query.getResultList();
	}

}
