package org.example.model;

public class Squad {
    private int id;
    private String Nome;
    private String Descricao;

    public Squad() {}

    public Squad(String nome, String descricao, int deptoId) {
        this.Nome = nome;
        this.Descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getDescricao() {
        return Descricao;
    }
    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
