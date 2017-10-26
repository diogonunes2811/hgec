package br.hgec.eb.ces.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.hgec.eb.ces.entidades.Setor;

public class SetorDao implements Dao<Setor> {

	@Override
	public void salvar(Setor s) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.persist(s);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void excluir(Setor s) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			s = em.merge(s);
			em.remove(s);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void alterar(Setor s) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.merge(s);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public List<Setor> listar() {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Query s = em.createQuery("from Setor");
		List<Setor> setores = s.getResultList();
		em.close();
		return setores;
	}

	@Override
	public Setor buscarPorId(Integer id) {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Setor setor = em.find(Setor.class, id);
		em.close();
		return setor;
	}

}
