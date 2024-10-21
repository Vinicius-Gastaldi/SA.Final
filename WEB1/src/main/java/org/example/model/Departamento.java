package org.example.model;

public class Departamento {
    private int idDepto;
    private String descDepto;

    public Departamento(){}

    public Departamento(int idDepto, String descDepto){
        this.idDepto = idDepto;
        this.descDepto = descDepto;
    }


    // area de getters e setters
    public int getIdDepto() {
        return idDepto;
    }
    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }
    public String getDescDepto() {
        return descDepto;
    }
    public void setDescDepto(String descDepto) {
        this.descDepto = descDepto;
    }

}

