package backbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Setor;
import servico.ServicoSetor;

@Named
@ViewScoped
public class SetorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Setor setor;
	
	private List<Setor> setores;
	
	@EJB
	private ServicoSetor servicoSetor;
	
	public SetorBean() {
		this.setor = new Setor();
		this.setores = new ArrayList<Setor>();
	}
	
	public void cadastrar() {
		try {
			this.servicoSetor.cadastrar(this.setor);
			this.setor = new Setor();
			FacesMessage msg = new FacesMessage("Setor cadastrado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void excluir(Setor setor) {
		this.servicoSetor.excluir(setor);
		FacesMessage msg = new FacesMessage("Setor excluído com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void editar() {
		try {
			this.servicoSetor.editar(this.setor);
			FacesMessage msg = new FacesMessage("Setor alterado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	public void selecionarSetor(Setor setor) {
		this.setor = setor;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getSetores() {
		this.setores = this.servicoSetor.listar();
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
}
