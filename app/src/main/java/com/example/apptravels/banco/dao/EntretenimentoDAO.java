package com.example.apptravels.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.apptravels.banco.DBOpenHelper;
import com.example.apptravels.banco.model.EntretenimentoModel;
import java.util.ArrayList;
import java.util.List;

public class EntretenimentoDAO extends AbstratDAO {

    private final String[]
            colunas = {
            EntretenimentoModel.COLUNA_ID,
            EntretenimentoModel.COLUNA_VIAGEM_ID,
            EntretenimentoModel.COLUNA_DESCRICAO,
            EntretenimentoModel.COLUNA_CUSTO,
            EntretenimentoModel.COLUNA_ADD_ENTRETENIMENTO
    };

    public EntretenimentoDAO(final Context contexto) {
        helper = new DBOpenHelper(contexto);
    }

    public long insert(EntretenimentoModel model) {

        long linhasAfetadas;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(EntretenimentoModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(EntretenimentoModel.COLUNA_DESCRICAO, model.getDescricao());
            values.put(EntretenimentoModel.COLUNA_CUSTO, model.getCusto());
            values.put(EntretenimentoModel.COLUNA_ADD_ENTRETENIMENTO, model.isAddEntretenimento() ? 1 : 0);

            linhasAfetadas = db.update(EntretenimentoModel.TABELA_NOME, values, EntretenimentoModel.COLUNA_VIAGEM_ID + " = ?", new String[]{String.valueOf(model.getViagemId())});
        } finally {
            Close();
        }

        return linhasAfetadas;
    }

    public int update(EntretenimentoModel model) {

        int linhasAfetadas = 0;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(EntretenimentoModel.COLUNA_VIAGEM_ID, model.getViagemId());
            values.put(EntretenimentoModel.COLUNA_DESCRICAO, model.getDescricao());
            values.put(EntretenimentoModel.COLUNA_CUSTO, model.getCusto());
            values.put(EntretenimentoModel.COLUNA_ADD_ENTRETENIMENTO, model.isAddEntretenimento() ? 1 : 0);

            linhasAfetadas = db.update(EntretenimentoModel.TABELA_NOME, values, EntretenimentoModel.COLUNA_VIAGEM_ID + " = ?", new String[]{String.valueOf(model.getViagemId())});
        } finally {
            Close();
        }

        return linhasAfetadas;
    }

    public List<EntretenimentoModel> getByViagemId(long viagemId) {
        List<EntretenimentoModel> lista = new ArrayList<>();
        try {
            Open();

            Cursor cursor = db.query(
                    EntretenimentoModel.TABELA_NOME,
                    colunas,
                    EntretenimentoModel.COLUNA_VIAGEM_ID + " = ?",
                    new String[]{String.valueOf(viagemId)},
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    EntretenimentoModel model = cursorToStructure(cursor);
                    lista.add(model);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        } finally {
            Close();
        }
        return lista;
    }

    private EntretenimentoModel cursorToStructure(Cursor cursor) {
        EntretenimentoModel model = new EntretenimentoModel();
        model.setId(cursor.getLong(0));
        model.setViagemId(cursor.getLong(1));
        model.setDescricao(cursor.getString(2));
        model.setCusto(cursor.getDouble(3));
        model.setAddEntretenimento(cursor.getInt(4) == 1);
        return model;
    }
}
