package backbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Pessoa;
import servico.ServicoPessoa;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	
	private List<Pessoa> pessoas;
	
	@EJB
	private ServicoPessoa servicoPessoa;
	
	public PessoaBean() {
		this.pessoa = new Pessoa();
		this.pessoas = new ArrayList<Pessoa>();
	}
	
	public void cadastrar() {
		try {
			this.servicoPessoa.cadastrar(this.pessoa);
			this.pessoa = new Pessoa();
			FacesMessage msg = new FacesMessage("Pessoa cadastrado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void excluir(Pessoa pessoa) {
		this.servicoPessoa.excluir(pessoa);
		FacesMessage msg = new FacesMessage("Pessoa excluído com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void editar() {
		try {
			this.servicoPessoa.editar(this.pessoa);
			FacesMessage msg = new FacesMessage("Pessoa alterado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	public void selecionarPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		this.pessoas = this.servicoPessoa.listar();
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
}
