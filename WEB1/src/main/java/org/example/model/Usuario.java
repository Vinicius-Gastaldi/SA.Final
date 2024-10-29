package org.example.model;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private int DevId;
    private Papel papel;

    public Usuario() {}
    public Usuario(int id, String email, String senha, int devId, Papel papel) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.DevId = devId;
        this.papel = papel;
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
    public Papel getPapel() {
        return papel;
    }
    public void setPapel(Papel papel) {
        this.papel = papel;
    }
}
