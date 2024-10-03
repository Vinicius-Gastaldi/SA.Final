package org.example.model;

import java.util.Date;

public class Tarefa {
    private int id;
    private int usuarioId;
    private String titulo;
    private String descricao;
    private String prioridade;
    private Status status;

    private Date DataInicio;
    private Date DataFim;
}
