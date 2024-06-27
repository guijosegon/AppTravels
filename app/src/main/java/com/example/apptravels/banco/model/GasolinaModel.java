package com.example.apptravels.banco.model;

import java.io.Serializable;

public class GasolinaModel implements Serializable {

    public static final String TABELA_NOME = "tb_gasolina";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_VIAGEM_ID = "viagem_id";
    public static final String COLUNA_TOTAL_KM = "total_km";
    public static final String COLUNA_KM_POR_LITRO = "km_por_litro";
    public static final String COLUNA_CUSTO_POR_LITRO = "custo_por_litro";
    public static final String COLUNA_TOTAL_VEICULOS = "total_veiculos";
    public static final String COLUNA_CUSTO_TOTAL_GASOLINA = "custo_total_gasolina";
    public static final String COLUNA_ADD_GASOLINA = "add_gasolina";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_NOME + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_VIAGEM_ID + " INTEGER, "
            + COLUNA_TOTAL_KM + " REAL, "
            + COLUNA_KM_POR_LITRO + " REAL, "
            + COLUNA_CUSTO_POR_LITRO + " REAL, "
            + COLUNA_TOTAL_VEICULOS + " INTEGER, "
            + COLUNA_CUSTO_TOTAL_GASOLINA + " REAL, "
            + COLUNA_ADD_GASOLINA + " INTEGER)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABELA_NOME;

    private long id;
    private long viagemId;
    private double totalKm;
    private double kmPorLitro;
    private double custoPorLitro;
    private int totalVeiculos;
    private double custoTotalGasolina;
    private boolean addGasolina;

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

    public double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    public double getKmPorLitro() {
        return kmPorLitro;
    }

    public void setKmPorLitro(double kmPorLitro) {
        this.kmPorLitro = kmPorLitro;
    }

    public double getCustoPorLitro() {
        return custoPorLitro;
    }

    public void setCustoPorLitro(double custoPorLitro) {
        this.custoPorLitro = custoPorLitro;
    }

    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(int totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }

    public double getCustoTotalGasolina() {
        return custoTotalGasolina;
    }

    public void setCustoTotalGasolina(double custoTotalGasolina) {
        this.custoTotalGasolina = custoTotalGasolina;
    }

    public boolean isAddGasolina() {
        return addGasolina;
    }

    public void setAddGasolina(boolean addGasolina) {
        this.addGasolina = addGasolina;
    }
}
