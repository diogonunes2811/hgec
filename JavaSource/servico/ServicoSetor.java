package servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Setor;

@Stateless
public class ServicoSetor {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Setor cadastrar(Setor setor) throws Exception {
		Setor existente = this.buscarSetorPorNome(setor.getNomeSetor());
		if (existente == null) {
			this.entityManager.persist(setor);
			return setor;
		} else {
			throw new Exception("Setor já cadastrado!");
		}
	}
	
	public void excluir(Setor setor) {
		this.entityManager.remove(this.entityManager.merge(setor));
	}

	public Setor editar(Setor setor) throws Exception {
		Setor existente = this.buscarSetorPorNome(setor.getNomeSetor());
		if (existente == null || existente.equals(setor)) {
			return this.entityManager.merge(setor);
		}else{
			throw new Exception("Setor já cadastrado");
		}
	}

	public List<Setor> listar() {
		Query query = this.entityManager.createQuery("FROM Setor s");
		return query.getResultList();
	}

	public Setor buscarSetorPorNome(String nome) {
		Query query = this.entityManager.createQuery("FROM Setor s WHERE s.nomeSetor=:p1");
		query.setParameter("p1", nome);
		try {
			return (Setor) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
