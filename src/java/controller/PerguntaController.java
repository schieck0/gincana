package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Pergunta;
import model.Resposta;
import service.PerguntaService;
import service.RespostaService;

@ManagedBean
@ViewScoped
public class PerguntaController implements Serializable {

    private Pergunta pergunta;
    private Resposta resposta;

    @PostConstruct
    public void init() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        LoginController login = (LoginController) elContext.getELResolver().getValue(elContext, null, "loginController");

        if (!login.getGrupo().isAdmin()) {
            pergunta = PerguntaService.getNovaPergunta(login.getGrupo());
        } else {
            pergunta = new Pergunta();
        }

        resposta = new Resposta();
        resposta.setPergunta(pergunta);
        resposta.setGrupo(login.getGrupo());
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void enviarResposta(ActionEvent event) {
        FacesMessage message = null;
        resposta.setDataHora(new Date());
        try {
            RespostaService.save(resposta);
            message = new FacesMessage("Resposta Enviada!", "Aguarde nova pergunta.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERRO!", "Ocorreu um erro ao salvar a resposta.");
        }

        pergunta = null;
        resposta = null;

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void savePergunta(ActionEvent event) {
        FacesMessage message = null;
        try {
            PerguntaService.save(pergunta);
            message = new FacesMessage("Feito", "Salvo com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ERRO!", "Ocorreu um erro ao salvar a pergunta.");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
