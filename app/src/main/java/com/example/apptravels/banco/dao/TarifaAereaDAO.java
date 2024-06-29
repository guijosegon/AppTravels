package com.example.apptravels.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.apptravels.banco.DBOpenHelper;
import com.example.apptravels.banco.model.TarifaAereaModel;

public class TarifaAereaDAO extends AbstratDAO {


    private final String[]
            colunas = {
            TarifaAereaModel.COLUNA_ID,
            TarifaAereaModel.COLUNA_VIAGEM_ID,
            TarifaAereaModel.COLUNA_CUSTO_POR_PESSOA,
            TarifaAereaModel.COLUNA_ALUGUEL_VEICULO,
            TarifaAereaModel.COLUNA_CUSTO_TOTAL_TARIFA,
            TarifaAereaModel.COLUNA_ADD_TARIFA
    };

    public TarifaAereaDAO(final Context contexto) {
        helper = new DBOpenHelper(contexto);
    }

    public long insert(TarifaAereaModel model) {

        long linhasAfetadas;

        try{

            Open();

            ContentValues values = new ContentValues();
            values.put(TarifaAereaModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(TarifaAereaModel.COLUNA_CUSTO_POR_PESSOA, model.getCustoPorPessoa());
            values.put(TarifaAereaModel.COLUNA_ALUGUEL_VEICULO, model.getAluguelVeiculo());
            values.put(TarifaAereaModel.COLUNA_CUSTO_TOTAL_TARIFA, model.getCustoTotalTarifa());
            values.put(TarifaAereaModel.COLUNA_ADD_TARIFA, model.isAddTarifa() ? 1 : 0);

            linhasAfetadas = db.insert(TarifaAereaModel.TABELA_NOME, null, values);

        }finally {
            Close();
        }

        return linhasAfetadas;
    }

    public int update(TarifaAereaModel model) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(TarifaAereaModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(TarifaAereaModel.COLUNA_CUSTO_POR_PESSOA, model.getCustoPorPessoa());
            values.put(TarifaAereaModel.COLUNA_ALUGUEL_VEICULO, model.getAluguelVeiculo());
            values.put(TarifaAereaModel.COLUNA_CUSTO_TOTAL_TARIFA, model.getCustoTotalTarifa());
            values.put(TarifaAereaModel.COLUNA_ADD_TARIFA, model.isAddTarifa() ? 1 : 0);

            linhasAfetadas = db.update(TarifaAereaModel.TABELA_NOME, values, TarifaAereaModel.COLUNA_VIAGEM_ID + " = ?", new String[]{String.valueOf(model.getViagemId())});

        } finally {
            Close();
        }

        return linhasAfetadas;
    }

    public TarifaAereaModel getByViagemId(long viagemId) {
        TarifaAereaModel model = null;
        try {
            Open();

            Cursor cursor = db.query(
                    TarifaAereaModel.TABELA_NOME,
                    colunas,
                    TarifaAereaModel.COLUNA_VIAGEM_ID + " = ?",
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


    private TarifaAereaModel cursorToStructure(Cursor cursor) {
        TarifaAereaModel model = new TarifaAereaModel();
        model.setId(cursor.getLong(0));
        model.setViagemId(cursor.getLong(1));
        model.setCustoPorPessoa(cursor.getDouble(2));
        model.setAluguelVeiculo(cursor.getDouble(3));
        model.setCustoTotalTarifa(cursor.getDouble(4));
        model.setAddTarifa(cursor.getInt(5) == 1);
        return model;
    }
}
