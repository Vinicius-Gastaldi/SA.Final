package org.example.model;

import java.util.Date;

public class Tarefa {
    private int id;
    private int desenvolvedorId;
    private int tipoTarefaId;
    private String titulo;
    private String descricao;
    private String prioridade;
    private int statusId;
    private Date DataInicio;
    private Date DataFim;

    public Tarefa() {
    }

    public Tarefa(int id, int desenvolvedorId, String titulo, String descricao, String prioridade, int statusId) {
        this.id = id;
        this.desenvolvedorId = desenvolvedorId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.statusId = statusId;

        this.DataInicio = new Date();
        this.DataFim = new Date();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDesenvolvedorId() {
        return desenvolvedorId;
    }
    public void setDesenvolvedorId(int desenvolvedorId) {
        this.desenvolvedorId = desenvolvedorId;
    }
    public int getTipoTarefaId() {
        return tipoTarefaId;
    }
    public void setTipoTarefaId(int tipoTarefaId) {
        this.tipoTarefaId = tipoTarefaId;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    public Date getDataInicio() {
        return DataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        DataInicio = dataInicio;
    }
    public Date getDataFim() {
        return DataFim;
    }
    public void setDataFim(Date dataFim) {
        DataFim = dataFim;
    }

}

