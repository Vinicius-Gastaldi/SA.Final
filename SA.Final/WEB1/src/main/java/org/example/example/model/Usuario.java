package org.example.model;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private int DevId;
    private int PapelId;
    private int acessoId;

    public Usuario() {}

    public Usuario(int id, String email, String senha, int devId, int papel, int statusId) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.DevId = devId;
        this.PapelId = papel;
        this.acessoId = statusId;
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
    public int getAcessoId() {
        return acessoId;
    }
    public void setAcessoId(int acessoId) {
        this.acessoId = acessoId;
    }
}

