package br.hgec.eb.ces.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.hgec.eb.ces.entidades.Pessoa;

public class PessoaDao implements Dao<Pessoa>{

	@Override
	public void salvar(Pessoa p) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void excluir(Pessoa p) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			p = em.merge(p);
			em.remove(p);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public void alterar(Pessoa p) {
		// TODO Auto-generated method stub
		EntityManager em = Conexao.getInstance();
		try {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	@Override
	public List<Pessoa> listar() {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Query p = em.createQuery("from Pessoa");
		List<Pessoa> pessoas = p.getResultList();
		em.close();
		return pessoas;
	}

	@Override
	public Pessoa buscarPorId(Integer id) {
		// TODO Auto-generated method stub
//		return null;
		EntityManager em = Conexao.getInstance();
		Pessoa pessoa = em.find(Pessoa.class, id);
		em.close();
		return pessoa;
	}

}
