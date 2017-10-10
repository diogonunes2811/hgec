package servico;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Militar;

@Stateless
public class ServicoLogin {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Militar getLogin(String nomeMiliar, String password) {
		Query query = this.entityManager.createQuery("SELECT u from Militar m where m.cpf = :p1 and m.password = :p2");
		query.setParameter("p1", nomeMiliar);
		query.setParameter("p2", password);
		try {
			return (Militar) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
