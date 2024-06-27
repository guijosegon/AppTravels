package com.example.apptravels.banco.model;

import java.io.Serializable;

public class HospedagemModel implements Serializable {

    public static final String TABELA_NOME = "tb_hospedagem";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_VIAGEM_ID = "viagem_id";
    public static final String COLUNA_CUSTO_POR_NOITE = "custo_por_noite";
    public static final String COLUNA_TOTAL_NOITES = "total_noites";
    public static final String COLUNA_TOTAL_QUARTOS = "total_quartos";
    public static final String COLUNA_CUSTO_TOTAL_HOSPEDAGEM = "custo_total_hospedagem";
    public static final String COLUNA_ADD_HOSPEDAGEM = "add_hospedagem";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_NOME + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_VIAGEM_ID + " INTEGER, "
            + COLUNA_CUSTO_POR_NOITE + " REAL, "
            + COLUNA_TOTAL_NOITES + " INTEGER, "
            + COLUNA_TOTAL_QUARTOS + " INTEGER, "
            + COLUNA_CUSTO_TOTAL_HOSPEDAGEM + " REAL, "
            + COLUNA_ADD_HOSPEDAGEM + " INTEGER)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABELA_NOME;

    private long id;
    private long viagemId;
    private double custoPorNoite;
    private int totalNoites;
    private int totalQuartos;
    private double custoTotalHospedagem;
    private boolean addHospedagem;

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

    public double getCustoPorNoite() {
        return custoPorNoite;
    }

    public void setCustoPorNoite(double custoPorNoite) {
        this.custoPorNoite = custoPorNoite;
    }

    public int getTotalNoites() {
        return totalNoites;
    }

    public void setTotalNoites(int totalNoites) {
        this.totalNoites = totalNoites;
    }

    public int getTotalQuartos() {
        return totalQuartos;
    }

    public void setTotalQuartos(int totalQuartos) {
        this.totalQuartos = totalQuartos;
    }

    public double getCustoTotalHospedagem() {
        return custoTotalHospedagem;
    }

    public void setCustoTotalHospedagem(double custoTotalHospedagem) {
        this.custoTotalHospedagem = custoTotalHospedagem;
    }

    public boolean isAddHospedagem() {
        return addHospedagem;
    }

    public void setAddHospedagem(boolean addHospedagem) {
        this.addHospedagem = addHospedagem;
    }
}
