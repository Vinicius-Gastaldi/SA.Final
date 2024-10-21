package org.example.model;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private int DevId;
    private int PapelId;

    public Usuario() {}
    public Usuario(int id, String email, String senha, int devId, int papel) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.DevId = devId;
        this.PapelId = papel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getDevId() {
        return DevId;
    }
    public void setDevId(int devId) {
        DevId = devId;
    }
    public int getPapelId() {
        return PapelId;
    }
    public void setPapelId(int papelId) {
        this.PapelId = papelId;
    }
}
