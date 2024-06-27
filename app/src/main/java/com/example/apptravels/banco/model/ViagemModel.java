package com.example.apptravels.banco.model;

import java.io.Serializable;

public class ViagemModel implements Serializable {

    public static final String TABELA_NOME = "tb_viagem";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_DESTINO = "destino";
    public static final String COLUNA_DATA_VIAGEM = "data_viagem";
    public static final String COLUNA_NUM_PESSOAS = "num_pessoas";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABELA_NOME + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_DESTINO + " TEXT NOT NULL, "
            + COLUNA_DATA_VIAGEM + " TEXT NOT NULL, "
            + COLUNA_NUM_PESSOAS + " INTEGER NOT NULL)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABELA_NOME;

    private long id;
    private String destino;
    private String dataViagem;
    private int numPessoas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(String dataViagem) {
        this.dataViagem = dataViagem;
    }

    public int getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(int numPessoas) {
        this.numPessoas = numPessoas;
    }
}
