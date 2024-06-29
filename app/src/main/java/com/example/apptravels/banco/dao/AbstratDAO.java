package com.example.apptravels.banco.dao;

import android.database.sqlite.SQLiteDatabase;
import com.example.apptravels.banco.DBOpenHelper;

public abstract class AbstratDAO {

    protected SQLiteDatabase db;
    protected DBOpenHelper helper;

    protected final void Open() {
        if (db == null || !db.isOpen()) {
            db = helper.getWritableDatabase();
        }
    }

    protected final void Close() {
        if (db != null && db.isOpen()) {
            helper.close();
        }
    }
}
