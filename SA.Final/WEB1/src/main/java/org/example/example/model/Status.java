package org.example.model;

public class Status {
    private int id;       // ID do Status
    private String nome;  // Nome do Status (e.g., Pendencia, em prgresso, etc.)

    public Status() {}

    public Status(int id, String nome) {
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
        return "Status{id=" + id + ", nome='" + nome + '\'' + '}';
    }
}
