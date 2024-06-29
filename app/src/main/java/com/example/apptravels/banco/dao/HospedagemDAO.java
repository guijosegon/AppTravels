package com.example.apptravels.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.apptravels.banco.DBOpenHelper;
import com.example.apptravels.banco.model.HospedagemModel;

public class HospedagemDAO extends AbstratDAO {

    private final String[]
            colunas = {
            HospedagemModel.COLUNA_ID,
            HospedagemModel.COLUNA_VIAGEM_ID,
            HospedagemModel.COLUNA_CUSTO_POR_NOITE,
            HospedagemModel.COLUNA_TOTAL_NOITES,
            HospedagemModel.COLUNA_TOTAL_QUARTOS,
            HospedagemModel.COLUNA_CUSTO_TOTAL_HOSPEDAGEM,
            HospedagemModel.COLUNA_ADD_HOSPEDAGEM
    };

    public HospedagemDAO(final Context contexto) {
        helper = new DBOpenHelper(contexto);
    }

    public long insert(HospedagemModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(HospedagemModel.COLUNA_CUSTO_POR_NOITE, model.getCustoPorNoite());
            values.put(HospedagemModel.COLUNA_TOTAL_NOITES, model.getTotalNoites());
            values.put(HospedagemModel.COLUNA_TOTAL_QUARTOS, model.getTotalQuartos());
            values.put(HospedagemModel.COLUNA_CUSTO_TOTAL_HOSPEDAGEM, model.getCustoTotalHospedagem());
            values.put(HospedagemModel.COLUNA_ADD_HOSPEDAGEM, model.isAddHospedagem() ? 1 : 0);

            linhasAfetadas = db.insert(HospedagemModel.TABELA_NOME, null, values);
        }finally {
            Close();
        }

        return linhasAfetadas;
    }

    public int update(HospedagemModel model) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(HospedagemModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(HospedagemModel.COLUNA_CUSTO_POR_NOITE, model.getCustoPorNoite());
            values.put(HospedagemModel.COLUNA_TOTAL_NOITES, model.getTotalNoites());
            values.put(HospedagemModel.COLUNA_TOTAL_QUARTOS, model.getTotalQuartos());
            values.put(HospedagemModel.COLUNA_CUSTO_TOTAL_HOSPEDAGEM, model.getCustoTotalHospedagem());
            values.put(HospedagemModel.COLUNA_ADD_HOSPEDAGEM, model.isAddHospedagem() ? 1 : 0);

            linhasAfetadas = db.update(HospedagemModel.TABELA_NOME, values, HospedagemModel.COLUNA_VIAGEM_ID + " = ?", new String[]{String.valueOf(model.getViagemId())});
        } finally {
            Close();
        }

        return linhasAfetadas;
    }

    public HospedagemModel getByViagemId(long viagemId) {
        HospedagemModel model = null;
        try {
            Open();

            Cursor cursor = db.query(
                    HospedagemModel.TABELA_NOME,
                    colunas,
                    HospedagemModel.COLUNA_VIAGEM_ID + " = ?",
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

    private HospedagemModel cursorToStructure(Cursor cursor) {
        HospedagemModel model = new HospedagemModel();
        model.setId(cursor.getLong(0));
        model.setViagemId(cursor.getLong(1));
        model.setCustoPorNoite(cursor.getDouble(2));
        model.setTotalNoites(cursor.getInt(3));
        model.setTotalQuartos(cursor.getInt(4));
        model.setCustoTotalHospedagem(cursor.getDouble(5));
        model.setAddHospedagem(cursor.getInt(6) == 1);
        return model;
    }
}
