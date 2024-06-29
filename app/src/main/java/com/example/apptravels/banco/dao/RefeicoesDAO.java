package com.example.apptravels.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.apptravels.banco.DBOpenHelper;
import com.example.apptravels.banco.model.RefeicoesModel;

public class RefeicoesDAO extends AbstratDAO {

    private final String[]
            colunas = {
            RefeicoesModel.COLUNA_ID,
            RefeicoesModel.COLUNA_VIAGEM_ID,
            RefeicoesModel.COLUNA_CUSTO_POR_REFEICAO,
            RefeicoesModel.COLUNA_REFEICOES_POR_DIA,
            RefeicoesModel.COLUNA_TOTAL_DIAS,
            RefeicoesModel.COLUNA_CUSTO_TOTAL_REFEICOES,
            RefeicoesModel.COLUNA_ADD_REFEICOES
    };

    public RefeicoesDAO(final Context contexto) {
        helper = new DBOpenHelper(contexto);
    }

    public long insert(RefeicoesModel model) {

        long linhasAfetadas;

        try{

            Open();

            ContentValues values = new ContentValues();
            values.put(RefeicoesModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(RefeicoesModel.COLUNA_CUSTO_POR_REFEICAO, model.getCustoPorRefeicao());
            values.put(RefeicoesModel.COLUNA_REFEICOES_POR_DIA, model.getRefeicoesPorDia());
            values.put(RefeicoesModel.COLUNA_TOTAL_DIAS, model.getTotalDias());
            values.put(RefeicoesModel.COLUNA_CUSTO_TOTAL_REFEICOES, model.getCustoTotalRefeicoes());
            values.put(RefeicoesModel.COLUNA_ADD_REFEICOES, model.isAddRefeicoes() ? 1 : 0);

            linhasAfetadas = db.insert(RefeicoesModel.TABELA_NOME, null, values);

        } finally {
            Close();
        }

        return linhasAfetadas;
    }

    public int update(RefeicoesModel model) {

        int linhasAfetadas = 0;

        try {

            Open();

            ContentValues values = new ContentValues();
            values.put(RefeicoesModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(RefeicoesModel.COLUNA_CUSTO_POR_REFEICAO, model.getCustoPorRefeicao());
            values.put(RefeicoesModel.COLUNA_REFEICOES_POR_DIA, model.getRefeicoesPorDia());
            values.put(RefeicoesModel.COLUNA_TOTAL_DIAS, model.getTotalDias());
            values.put(RefeicoesModel.COLUNA_CUSTO_TOTAL_REFEICOES, model.getCustoTotalRefeicoes());
            values.put(RefeicoesModel.COLUNA_ADD_REFEICOES, model.isAddRefeicoes() ? 1 : 0);

            linhasAfetadas = db.update(RefeicoesModel.TABELA_NOME, values, RefeicoesModel.COLUNA_VIAGEM_ID + " = ?", new String[]{String.valueOf(model.getViagemId())});

        } finally {
            Close();
        }

        return linhasAfetadas;
    }

    public RefeicoesModel getByViagemId(long viagemId) {
        RefeicoesModel model = null;
        try {
            Open();

            Cursor cursor = db.query(
                    RefeicoesModel.TABELA_NOME,
                    colunas,
                    RefeicoesModel.COLUNA_VIAGEM_ID + " = ?",
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


    private RefeicoesModel cursorToStructure(Cursor cursor) {
        RefeicoesModel model = new RefeicoesModel();
        model.setId(cursor.getLong(0));
        model.setViagemId(cursor.getLong(1));
        model.setCustoPorRefeicao(cursor.getDouble(2));
        model.setRefeicoesPorDia(cursor.getInt(3));
        model.setTotalDias(cursor.getInt(4));
        model.setCustoTotalRefeicoes(cursor.getDouble(5));
        model.setAddRefeicoes(cursor.getInt(6) == 1);
        return model;
    }
}
