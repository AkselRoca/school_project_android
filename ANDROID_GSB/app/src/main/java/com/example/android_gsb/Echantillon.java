package com.example.android_gsb;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

public class Echantillon {

    protected String code;
    protected String libelle;
    protected String quantiteStock;

    public Echantillon(String code, String libelle, String quantiteStock) {
        this.code = code;
        this.libelle = libelle;
        this.quantiteStock = quantiteStock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(String quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

}