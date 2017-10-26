package br.hgec.eb.ces.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.hgec.eb.ces.entidades.Militar;

public class MilitarDao implements Dao<Militar> {

	@Override
	public void salvar(Militar m) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.persist(m);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void excluir(Militar m) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			m = em.merge(m);
			em.remove(m);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void alterar(Militar m) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.merge(m);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public List<Militar> listar() {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Query m = em.createQuery("from Militar");
		List<Militar> militares = m.getResultList();
		em.close();
		return militares;
	}

	@Override
	public Militar buscarPorId(Integer id) {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Militar militar = em.find(Militar.class, id);
		em.close();
		return militar;
	}

}
