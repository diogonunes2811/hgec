package br.hgec.eb.ces.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.hgec.eb.ces.entidades.Carro;

public class CarroDao implements Dao<Carro>{

	@Override
	public void salvar(Carro c) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void excluir(Carro c) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			c = em.merge(c);
			em.remove(c);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void alterar(Carro c) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public List<Carro> listar() {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Query c = em.createQuery("from Carro");
		List<Carro> Carros = c.getResultList();
		em.close();
		return Carros;
	}

	@Override
	public Carro buscarPorId(Integer id) {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Carro carro = em.find(Carro.class, id);
		em.close();
		return carro;
	}

}
