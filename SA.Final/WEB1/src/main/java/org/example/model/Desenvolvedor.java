package org.example.model;

public class Desenvolvedor {
    private int id;
    private String nome;
    private String phone;
    private int squadId;

    public Desenvolvedor() {}

    public Desenvolvedor(int id, String name, String phone, int squadId) {
        this.id = id;
        this.nome = name;
        this.phone = phone;
        this.squadId = squadId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return nome;
    }
    public void setName(String name) {
        this.nome = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getSquadId() {
        return squadId;
    }
    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }
}
