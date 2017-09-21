package backbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Carro;
import servico.ServicoCarro;

@Named
@ViewScoped
public class CarroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Carro carro;
	
	private List<Carro> carros;
	
	@EJB
	private ServicoCarro servicoCarro;
	
	public CarroBean() {
		this.carro = new Carro();
		this.carros = new ArrayList<Carro>();
	}
	
	public void cadastrar() {
		try {
			this.servicoCarro.cadastrar(this.carro);
			this.carro = new Carro();
			FacesMessage msg = new FacesMessage("Carro cadastrado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void excluir(Carro carro) {
		this.servicoCarro.excluir(carro);
		FacesMessage msg = new FacesMessage("Carro excluído com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void editar() {
		try {
			this.servicoCarro.editar(this.carro);
			FacesMessage msg = new FacesMessage("Carro alterado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	public void selecionarCarro(Carro carro) {
		this.carro = carro;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getCarros() {
		this.carros = this.servicoCarro.listar();
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
}
