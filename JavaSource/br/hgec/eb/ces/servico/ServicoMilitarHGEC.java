package br.hgec.eb.ces.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.hgec.eb.ces.entidades.Militar;

@Stateless
public class ServicoMilitarHGEC {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Militar cadastrar(Militar militar) throws Exception {
		Militar existente = this.buscarMilitarPorCpf(militar.getCpf());
		if (existente == null) {
//			String dataString;
//			dataString = modificaDataEntrada(pessoa.getHorarioEntrada());
//			
////			//Converte a hora da entrada e string conforme formato ("dd/MM/yyyy hh:mm:ss")
////			Date horaEntrada = new Date();
////			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
////			String dataString = formato.format(horaEntrada);
//			pessoa.setHorarioEntrada(dataString);
//			
			this.entityManager.persist(militar);
			return militar;
		} else {
			throw new Exception("Militar já cadastrado!");
		}
	}

//	public String modificaDataEntrada (String string) {
//		Date horaEntrada = new Date();
//		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//		String dataString = formato.format(horaEntrada);
//		
//		return dataString;
//	}
	
	public void excluir(Militar militar) {
		this.entityManager.remove(this.entityManager.merge(militar));
	}

	public Militar editar(Militar militar) throws Exception {
		Militar existente = this.buscarMilitarPorCpf(militar.getNome());
		if (existente == null || existente.equals(militar)) {
			return this.entityManager.merge(militar);
		}else{
			throw new Exception("Militar já cadastrado");
		}
	}

	public List<Militar> listar() {
		Query query = this.entityManager.createQuery("FROM Militar m");
		return query.getResultList();
	}

	public Militar buscarMilitarPorCpf(String cpf) {
		Query query = this.entityManager.createQuery("FROM Militar m WHERE m.cpf=:p1");
		query.setParameter("p1", cpf);
		try {
			return (Militar) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
