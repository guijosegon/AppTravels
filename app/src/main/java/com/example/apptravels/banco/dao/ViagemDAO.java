package com.example.apptravels.banco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.apptravels.banco.DBOpenHelper;
import com.example.apptravels.banco.model.ViagemModel;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO extends AbstratDAO{

    private final String[]
            colunas = {
            ViagemModel.COLUNA_ID,
            ViagemModel.COLUNA_DESTINO,
            ViagemModel.COLUNA_DATA_VIAGEM,
            ViagemModel.COLUNA_NUM_PESSOAS
    };

    public ViagemDAO(final Context context) {
        helper = new DBOpenHelper(context);
    }

    public void OpenBanco(){
        Open();
    }

    public void CloseBanco(){
        Close();
    }

    public long Insert(ViagemModel model) {
        long linhasAfetadas;

        try{
            Open();

            ContentValues values = new ContentValues();
            values.put(ViagemModel.COLUNA_DESTINO, model.getDestino());
            values.put(ViagemModel.COLUNA_DATA_VIAGEM, model.getDataViagem());
            values.put(ViagemModel.COLUNA_NUM_PESSOAS, model.getNumPessoas());

            linhasAfetadas = db.insert(ViagemModel.TABELA_NOME, null, values);
        }finally {
           Close();
        }

        return linhasAfetadas;
    }

    public ViagemModel getById(long id) {
        ViagemModel viagem = null;

        try {
            Open();
            Cursor cursor = db.query(ViagemModel.TABELA_NOME, colunas, ViagemModel.COLUNA_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                viagem = new ViagemModel();
                viagem.setId(cursor.getLong(0));
                viagem.setDestino(cursor.getString(1));
                viagem.setDataViagem(cursor.getString(2));
                viagem.setNumPessoas(cursor.getInt(3));
                cursor.close();
            }
        } finally {
            Close();
        }
        return viagem;
    }

    public List<ViagemModel> getAllViagens() {
        List<ViagemModel> viagens = new ArrayList<>();

        try {
            Open();

            Cursor cursor = db.query(ViagemModel.TABELA_NOME, null, null, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ViagemModel viagem = cursorToStructure(cursor);
                    viagens.add(viagem);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }finally {
           Close();
        }

        return viagens;
    }

    public void delete(long id) {
        try {
            Open();
            db.delete(ViagemModel.TABELA_NOME, ViagemModel.COLUNA_ID + " = ?", new String[]{String.valueOf(id)});
        } finally {
            Close();
        }
    }

    private ViagemModel cursorToStructure(Cursor cursor) {
        ViagemModel model = new ViagemModel();
        model.setId(cursor.getLong(0));
        model.setDestino(cursor.getString(1));
        model.setDataViagem(cursor.getString(2));
        model.setNumPessoas(cursor.getInt(3));
        return model;
    }
}
