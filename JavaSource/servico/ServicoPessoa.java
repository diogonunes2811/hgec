package servico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Pessoa;


@Stateless
public class ServicoPessoa {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Pessoa cadastrar(Pessoa pessoa) throws Exception {
		Pessoa existente = this.buscarPessoaPorNome(pessoa.getCpf());
		if (existente == null) {
			String dataString;
			dataString = modificaDataEntrada(pessoa.getHorarioEntrada());
			
//			//Converte a hora da entrada e string conforme formato ("dd/MM/yyyy hh:mm:ss")
//			Date horaEntrada = new Date();
//			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//			String dataString = formato.format(horaEntrada);
			pessoa.setHorarioEntrada(dataString);
			
			this.entityManager.persist(pessoa);
			return pessoa;
		} else {
			throw new Exception("Pessoa já cadastrada!");
		}
	}

	public String modificaDataEntrada (String string) {
		Date horaEntrada = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String dataString = formato.format(horaEntrada);
		
		return dataString;
	}
	
	public void excluir(Pessoa pessoa) {
		this.entityManager.remove(this.entityManager.merge(pessoa));
	}

	public Pessoa editar(Pessoa pessoa) throws Exception {
		Pessoa existente = this.buscarPessoaPorNome(pessoa.getNome());
		if (existente == null || existente.equals(pessoa)) {
			return this.entityManager.merge(pessoa);
		}else{
			throw new Exception("Pessoa já cadastrada");
		}
	}

	public List<Pessoa> listar() {
		Query query = this.entityManager.createQuery("FROM Pessoa p");
		return query.getResultList();
	}

	public Pessoa buscarPessoaPorNome(String cpf) {
		Query query = this.entityManager.createQuery("FROM Pessoa p WHERE p.cpf=:p1");
		query.setParameter("p1", cpf);
		try {
			return (Pessoa) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
