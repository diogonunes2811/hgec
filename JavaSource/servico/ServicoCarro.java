package servico;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Carro;

@Stateless
public class ServicoCarro {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Carro cadastrar(Carro carro) throws Exception {
		Carro existente = this.buscarCarroPorPlaca(carro.getPlaca());
		if (existente == null) {
			String dataString;
			dataString = modificaDataEntrada(carro.getHorarioEntrada());
			
//			//Converte a hora da entrada e string conforme formato ("dd/MM/yyyy hh:mm:ss")
//			Date horaEntrada = new Date();
//			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//			String dataString = formato.format(horaEntrada);
			carro.setHorarioEntrada(dataString);
			
			this.entityManager.persist(carro);
			return carro;
		} else {
			throw new Exception("Carro já cadastrado!");
		}
	}

	public String modificaDataEntrada (String string) {
		Date horaEntrada = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String dataString = formato.format(horaEntrada);
		
		return dataString;
	}
	
	public void excluir(Carro carro) {
		this.entityManager.remove(this.entityManager.merge(carro));
	}

	public Carro editar(Carro carro) throws Exception {
		Carro existente = this.buscarCarroPorPlaca(carro.getPlaca());
		if (existente == null || existente.equals(carro)) {
			return this.entityManager.merge(carro);
		}else{
			throw new Exception("Carro já cadastrado");
		}
	}

	public List<Carro> listar() {
		Query query = this.entityManager.createQuery("FROM Carro c");
		return query.getResultList();
	}

	public Carro buscarCarroPorPlaca(String placa) {
		Query query = this.entityManager.createQuery("FROM Carro c WHERE c.placa=:p1");
		query.setParameter("p1", placa);
		try {
			return (Carro) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
