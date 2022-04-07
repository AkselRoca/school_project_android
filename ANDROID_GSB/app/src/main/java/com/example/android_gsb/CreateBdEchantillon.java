package com.example.android_gsb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateBdEchantillon extends SQLiteOpenHelper {

    private static final String TABLE_ECHANT = "echantillons";
    static final String COL_ID = "_id";
    private static final String COL_CODE = "CODE";
    private static final String COL_LIB = "LIB";
    private static final String COL_STOCK = "STOCK";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_ECHANT + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_CODE + " TEXT NOT NULL, " + COL_LIB + " TEXT NOT NULL, " +
            COL_STOCK + " TEXT NOT NULL);";

    public CreateBdEchantillon(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer une
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);

    }

    @Override // appelée si la version de la base a changé
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut  supprimer la table et de la recréer
        db.execSQL("DROP TABLE " + TABLE_ECHANT + ";");
        onCreate(db);
    }
}