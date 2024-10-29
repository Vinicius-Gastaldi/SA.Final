package org.example.model;

public class TipoTarefa {

    private int idTipoTarefa;
    private String Titulo;
    private String descricao;

    public int getIdTipoTarefa() {
        return idTipoTarefa;
    }
    public void setIdTipoTarefa(int idTipoTarefa) {
        this.idTipoTarefa = idTipoTarefa;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }

}
