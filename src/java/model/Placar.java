package model;

import java.io.Serializable;

public class Placar implements Serializable {

    private Grupo grupo;
    private int pontos;

    public Placar() {
    }

    public Placar(Grupo grupo, int pontos) {
        this.grupo = grupo;
        this.pontos = pontos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    
}
