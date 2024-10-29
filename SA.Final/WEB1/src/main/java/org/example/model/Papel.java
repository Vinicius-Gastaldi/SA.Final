package org.example.model;

public class Papel {
    private int id;       // ID do Papel
    private String nome;  // Nome do Papel (e.g., Admin, Desenvolvedor, etc.)

    public Papel() {}

    public Papel(int id, String nome) {
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
        return "Papel{id=" + id + ", nome='" + nome + '\'' + '}';
    }
}
