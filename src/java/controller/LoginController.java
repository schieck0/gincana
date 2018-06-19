package controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Grupo;
import service.GrupoService;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private Grupo grupo;

    @PostConstruct
    public void init() {
        grupo = new Grupo();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void login(ActionEvent event) {
        FacesMessage message = null;
        Grupo login = null;
        if (grupo.getNome().equals("adm") && grupo.getSenha().equals("12")) {
            login = new Grupo("ADMINISTRADOR");
            login.setAdmin(true);
        } else {
            login = GrupoService.validaLogin(grupo.getNome(), grupo.getSenha());
        }

        if (login != null) {
            grupo = login;
            grupo.setLogado(true);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo", login.getNome());
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Login inválido!");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void logout(ActionEvent event) {
        if (grupo != null) {
            grupo.setLogado(false);
            grupo = new Grupo();
        }
    }
}
