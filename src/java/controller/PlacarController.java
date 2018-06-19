package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Placar;
import service.RespostaService;

@ManagedBean
@ViewScoped
public class PlacarController implements Serializable {

    public List<Placar> getPlacar() {
        return RespostaService.getPlacar();
    }

}
