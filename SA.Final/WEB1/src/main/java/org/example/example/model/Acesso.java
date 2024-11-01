package org.example.model;

public class Acesso {
    private int id;       // ID do Acesso
    private String nome;  // Nome do Status (Ex: Bloqueado, Inativado, etc.)

    public Acesso() {}

    public Acesso(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Acesso{id=" + id + ", nome='" + nome + '\'' + '}';
    }
}
