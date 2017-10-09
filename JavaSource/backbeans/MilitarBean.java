package backbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Militar;
import servico.ServicoMilitarHGEC;

@Named
@ViewScoped
public class MilitarBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Militar militar;
	
	private List<Militar> militares;
	
	@EJB
	private ServicoMilitarHGEC servicoMilitarHGEC;
	
	public MilitarBean() {
		this.militar = new Militar();
		this.militares = new ArrayList<Militar>();
	}
	
	public void cadastrar() {
		try {
			this.servicoMilitarHGEC.cadastrar(this.militar);
			this.militar = new Militar();
			FacesMessage msg = new FacesMessage("Militar cadastrado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void excluir(Militar militar) {
		this.servicoMilitarHGEC.excluir(militar);
		FacesMessage msg = new FacesMessage("Militar excluído com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void editar() {
		try {
			this.servicoMilitarHGEC.editar(this.militar);
			FacesMessage msg = new FacesMessage("Militar alterado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	public void selecionarMilitar(Militar militar) {
		this.militar = militar;
	}

	public Militar getMilitar() {
		return militar;
	}

	public void setMilitar(Militar militar) {
		this.militar = militar;
	}

	public List<Militar> getMilitares() {
		this.militares = this.servicoMilitarHGEC.listar();
		return militares;
	}

	public void setMilitares(List<Militar> militares) {
		this.militares = militares;
	}

	public ServicoMilitarHGEC getServicoMilitarHGEC() {
		return servicoMilitarHGEC;
	}

	public void setServicoMilitarHGEC(ServicoMilitarHGEC servicoMilitarHGEC) {
		this.servicoMilitarHGEC = servicoMilitarHGEC;
	}
}
