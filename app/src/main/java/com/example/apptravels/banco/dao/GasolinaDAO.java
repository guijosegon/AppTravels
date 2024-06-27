package com.example.apptravels.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.apptravels.banco.DBOpenHelper;
import com.example.apptravels.banco.model.GasolinaModel;

public class GasolinaDAO  extends AbstratDAO {

    private final String[]
            colunas = {
            GasolinaModel.COLUNA_ID,
            GasolinaModel.COLUNA_VIAGEM_ID,
            GasolinaModel.COLUNA_TOTAL_KM,
            GasolinaModel.COLUNA_KM_POR_LITRO,
            GasolinaModel.COLUNA_CUSTO_POR_LITRO,
            GasolinaModel.COLUNA_TOTAL_VEICULOS,
            GasolinaModel.COLUNA_CUSTO_TOTAL_GASOLINA,
            GasolinaModel.COLUNA_ADD_GASOLINA
    };

    public GasolinaDAO(final Context contexto) {
        helper = new DBOpenHelper(contexto);
    }

    public long insert(GasolinaModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(GasolinaModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(GasolinaModel.COLUNA_TOTAL_KM, model.getTotalKm());
            values.put(GasolinaModel.COLUNA_KM_POR_LITRO, model.getKmPorLitro());
            values.put(GasolinaModel.COLUNA_CUSTO_POR_LITRO, model.getCustoPorLitro());
            values.put(GasolinaModel.COLUNA_TOTAL_VEICULOS, model.getTotalVeiculos());
            values.put(GasolinaModel.COLUNA_CUSTO_TOTAL_GASOLINA, model.getCustoTotalGasolina());
            values.put(GasolinaModel.COLUNA_ADD_GASOLINA, model.isAddGasolina() ? 1 : 0);

            linhasAfetadas = db.insert(GasolinaModel.TABELA_NOME, null, values);
        }finally {
            Close();
        }

        return linhasAfetadas;
    }

    public int update(GasolinaModel model) {

        int linhasAfetadas = 0;

        try{
            Open();

            ContentValues values = new ContentValues();
            values.put(GasolinaModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(GasolinaModel.COLUNA_TOTAL_KM, model.getTotalKm());
            values.put(GasolinaModel.COLUNA_KM_POR_LITRO, model.getKmPorLitro());
            values.put(GasolinaModel.COLUNA_CUSTO_POR_LITRO, model.getCustoPorLitro());
            values.put(GasolinaModel.COLUNA_TOTAL_VEICULOS, model.getTotalVeiculos());
            values.put(GasolinaModel.COLUNA_CUSTO_TOTAL_GASOLINA, model.getCustoTotalGasolina());
            values.put(GasolinaModel.COLUNA_ADD_GASOLINA, model.isAddGasolina() ? 1 : 0);

            linhasAfetadas = db.update(GasolinaModel.TABELA_NOME, values, GasolinaModel.COLUNA_VIAGEM_ID + " = ?", new String[]{String.valueOf(model.getViagemId())});
        }finally {
            Close();
        }

        return linhasAfetadas;
    }

    public GasolinaModel getByViagemId(long viagemId) {
        GasolinaModel model = null;
        try {
            Open();

            Cursor cursor = db.query(
                    GasolinaModel.TABELA_NOME,
                    colunas,
                    GasolinaModel.COLUNA_VIAGEM_ID + " = ?",
                    new String[]{String.valueOf(viagemId)},
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                model = cursorToStructure(cursor);
                cursor.close();
            }
        } finally {
            Close();
        }
        return model;
    }

    private GasolinaModel cursorToStructure(Cursor cursor) {
        GasolinaModel model = new GasolinaModel();
        model.setId(cursor.getLong(0));
        model.setViagemId(cursor.getLong(1));
        model.setTotalKm(cursor.getDouble(2));
        model.setKmPorLitro(cursor.getDouble(3));
        model.setCustoPorLitro(cursor.getDouble(4));
        model.setTotalVeiculos(cursor.getInt(5));
        model.setCustoTotalGasolina(cursor.getDouble(6));
        model.setAddGasolina(cursor.getInt(7) == 1);
        return model;
    }
}
