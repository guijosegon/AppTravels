package com.example.apptravels.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.apptravels.banco.model.UsuarioModel;
import com.example.apptravels.banco.model.ViagemModel;
import com.example.apptravels.banco.model.GasolinaModel;
import com.example.apptravels.banco.model.TarifaAereaModel;
import com.example.apptravels.banco.model.RefeicoesModel;
import com.example.apptravels.banco.model.HospedagemModel;
import com.example.apptravels.banco.model.EntretenimentoModel;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "travelfinances.db";
    private static final int DATABASE_VERSION = 2;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioModel.CREATE_TABLE);
        db.execSQL(ViagemModel.CREATE_TABLE);
        db.execSQL(GasolinaModel.CREATE_TABLE);
        db.execSQL(TarifaAereaModel.CREATE_TABLE);
        db.execSQL(RefeicoesModel.CREATE_TABLE);
        db.execSQL(HospedagemModel.CREATE_TABLE);
        db.execSQL(EntretenimentoModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UsuarioModel.DROP_TABLE);
        db.execSQL(ViagemModel.DROP_TABLE);
        db.execSQL(GasolinaModel.DROP_TABLE);
        db.execSQL(TarifaAereaModel.DROP_TABLE);
        db.execSQL(RefeicoesModel.DROP_TABLE);
        db.execSQL(HospedagemModel.DROP_TABLE);
        db.execSQL(EntretenimentoModel.DROP_TABLE);
        onCreate(db);
    }
}
