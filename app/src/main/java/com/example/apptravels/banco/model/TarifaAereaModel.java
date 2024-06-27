package com.example.apptravels.banco.model;

import java.io.Serializable;

public class TarifaAereaModel implements Serializable {

    public static final String TABELA_NOME = "tb_tarifa_aerea";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_VIAGEM_ID = "viagem_id";
    public static final String COLUNA_CUSTO_POR_PESSOA = "custo_por_pessoa";
    public static final String COLUNA_ALUGUEL_VEICULO = "aluguel_veiculo";
    public static final String COLUNA_CUSTO_TOTAL_TARIFA = "custo_total_tarifa";
    public static final String COLUNA_ADD_TARIFA = "add_tarifa";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_NOME + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_VIAGEM_ID + " INTEGER, "
            + COLUNA_CUSTO_POR_PESSOA + " REAL, "
            + COLUNA_ALUGUEL_VEICULO + " REAL, "
            + COLUNA_CUSTO_TOTAL_TARIFA + " REAL, "
            + COLUNA_ADD_TARIFA + " INTEGER)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABELA_NOME;

    private long id;
    private long viagemId;
    private double custoPorPessoa;
    private double aluguelVeiculo;
    private double custoTotalTarifa;
    private boolean addTarifa;

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

    public double getCustoPorPessoa() {
        return custoPorPessoa;
    }

    public void setCustoPorPessoa(double custoPorPessoa) {
        this.custoPorPessoa = custoPorPessoa;
    }

    public double getAluguelVeiculo() {
        return aluguelVeiculo;
    }

    public void setAluguelVeiculo(double aluguelVeiculo) {
        this.aluguelVeiculo = aluguelVeiculo;
    }

    public double getCustoTotalTarifa() {
        return custoTotalTarifa;
    }

    public void setCustoTotalTarifa(double custoTotalTarifa) {
        this.custoTotalTarifa = custoTotalTarifa;
    }

    public boolean isAddTarifa() {
        return addTarifa;
    }

    public void setAddTarifa(boolean addTarifa) {
        this.addTarifa = addTarifa;
    }
}
