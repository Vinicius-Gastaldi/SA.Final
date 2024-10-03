package org.example.model;

public class Squad {
    private int Id;
    private String Nome;
    private String Descricao;
    private int deptoId;

    public Squad() {}
    public Squad(String nome, String descricao, int deptoId) {
        this.Nome = nome;
        this.Descricao = descricao;
        this.deptoId = deptoId;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
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
    public int getDeptoId() {
        return deptoId;
    }
    public void setDeptoId(int deptoId) {
        this.deptoId = deptoId;
    }
}
