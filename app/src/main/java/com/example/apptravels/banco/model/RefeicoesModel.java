package com.example.apptravels.banco.model;

import java.io.Serializable;

public class RefeicoesModel implements Serializable {

    public static final String TABELA_NOME = "tb_refeicoes";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_VIAGEM_ID = "viagem_id";
    public static final String COLUNA_CUSTO_POR_REFEICAO = "custo_por_refeicao";
    public static final String COLUNA_REFEICOES_POR_DIA = "refeicoes_por_dia";
    public static final String COLUNA_TOTAL_DIAS = "total_dias";
    public static final String COLUNA_CUSTO_TOTAL_REFEICOES = "custo_total_refeicoes";
    public static final String COLUNA_ADD_REFEICOES = "add_refeicoes";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_NOME + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_VIAGEM_ID + " INTEGER, "
            + COLUNA_CUSTO_POR_REFEICAO + " REAL, "
            + COLUNA_REFEICOES_POR_DIA + " INTEGER, "
            + COLUNA_TOTAL_DIAS + " INTEGER, "
            + COLUNA_CUSTO_TOTAL_REFEICOES + " REAL, "
            + COLUNA_ADD_REFEICOES + " INTEGER)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABELA_NOME;

    private long id;
    private long viagemId;
    private double custoPorRefeicao;
    private int refeicoesPorDia;
    private int totalDias;
    private double custoTotalRefeicoes;
    private boolean addRefeicoes;

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

    public double getCustoPorRefeicao() {
        return custoPorRefeicao;
    }

    public void setCustoPorRefeicao(double custoPorRefeicao) {
        this.custoPorRefeicao = custoPorRefeicao;
    }

    public int getRefeicoesPorDia() {
        return refeicoesPorDia;
    }

    public void setRefeicoesPorDia(int refeicoesPorDia) {
        this.refeicoesPorDia = refeicoesPorDia;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public double getCustoTotalRefeicoes() {
        return custoTotalRefeicoes;
    }

    public void setCustoTotalRefeicoes(double custoTotalRefeicoes) {
        this.custoTotalRefeicoes = custoTotalRefeicoes;
    }

    public boolean isAddRefeicoes() {
        return addRefeicoes;
    }

    public void setAddRefeicoes(boolean addRefeicoes) {
        this.addRefeicoes = addRefeicoes;
    }
}
