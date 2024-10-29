package org.example.model;

public class TipoTarefa {

    private int id;
    private String Titulo;
    private String descricao;

    public TipoTarefa() {}

    public TipoTarefa(int id, String Titulo, String descricao) {
        this.id = id;
        this.Titulo = Titulo;
        this.descricao = descricao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
