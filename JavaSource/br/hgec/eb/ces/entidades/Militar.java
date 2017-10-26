package br.hgec.eb.ces.entidades;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Militar extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Campo nome de guerra obrigatório.")
	private String nomeDeGuerra;
	
	@NotBlank(message="Campo posto e graduação obrigatório.")
	private String postoGraduacao;
	
	@NotBlank(message="Campo identidade militar obrigatório.")
	private String idtMilitar;
	
	private String password;

	public String getNomeDeGuerra() {
		return nomeDeGuerra;
	}

	public void setNomeDeGuerra(String nomeDeGuerra) {
		this.nomeDeGuerra = nomeDeGuerra;
	}

	public String getPostoGraduacao() {
		return postoGraduacao;
	}

	public void setPostoGraduacao(String postoGraduacao) {
		this.postoGraduacao = postoGraduacao;
	}

	public String getIdtMilitar() {
		return idtMilitar;
	}

	public void setIdtMilitar(String idtMilitar) {
		this.idtMilitar = idtMilitar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
