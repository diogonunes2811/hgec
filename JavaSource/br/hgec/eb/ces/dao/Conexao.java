package br.hgec.eb.ces.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	
	static EntityManagerFactory emf = Persistence.
			createEntityManagerFactory("ces");
	
	public static EntityManager getInstance() {
		return emf.createEntityManager();
	}

}
