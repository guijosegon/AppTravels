package com.example.apptravels.banco.model;

import java.io.Serializable;

public class EntretenimentoModel implements Serializable {

    public static final String TABELA_NOME = "tb_entretenimento";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_VIAGEM_ID = "viagem_id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_CUSTO = "custo";
    public static final String COLUNA_ADD_ENTRETENIMENTO = "add_entretenimento";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_NOME + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_VIAGEM_ID + " INTEGER, "
            + COLUNA_DESCRICAO + " TEXT, "
            + COLUNA_CUSTO + " REAL, "
            + COLUNA_ADD_ENTRETENIMENTO + " INTEGER)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABELA_NOME;

    private long id;
    private long viagemId;
    private String descricao;
    private double custo;
    private boolean addEntretenimento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getViagemId() {
        return viagemId;
    }

    public void setViagemId(long viagemId) {
        this.viagemId = viagemId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public boolean isAddEntretenimento() {
        return addEntretenimento;
    }

    public void setAddEntretenimento(boolean addEntretenimento) {
        this.addEntretenimento = addEntretenimento;
    }
}