package entidades;

import javax.persistence.Entity;

@Entity
public class Militar extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeDeGuerra;
	
	private String postoGraduacao;
	
	private String idtMilitar;

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
	
}
