package backbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Militar;
import servico.ServicoLogin;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Militar militar;
	
	private List<Militar> militares;
	
//	@EJB
	private ServicoLogin servicoLogin;
	
	public LoginBean() {
		this.militar = new Militar();
		this.militares = new ArrayList<Militar>();
	}
	
	public String enviaLogin() {
		militar = servicoLogin.getLogin(militar.getCpf(), militar.getPassword());
        if (militar == null) {
        	militar = new Militar();
              FacesContext.getCurrentInstance().addMessage(
                         null,
                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                     "Erro no Login!"));
              return null;
        } else {
              return "/login";
        }
        
        
  }

	public Militar getMilitar() {
		return militar;
	}

	public void setMilitar(Militar militar) {
		this.militar = militar;
	}

	public List<Militar> getMilitares() {
		return militares;
	}

	public void setMilitares(List<Militar> militares) {
		this.militares = militares;
	}
	
}
