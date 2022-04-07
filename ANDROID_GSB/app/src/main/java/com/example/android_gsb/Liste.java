package com.example.android_gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import static com.example.android_gsb.BdAdapter.COL_CODE;
import static com.example.android_gsb.BdAdapter.COL_LIB;
import static com.example.android_gsb.BdAdapter.COL_STOCK;

public class Liste extends AppCompatActivity {
    private ListView listViewEchant;
    private BdAdapter echantBdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_entree);

        listViewEchant = (ListView) findViewById(R.id.ListeListViewEchant);

        echantBdd = new BdAdapter(this);
        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        Cursor leCurseur = echantBdd.getData();
        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(leCurseur.getCount()) + " echantillons dans la BD", Toast.LENGTH_LONG).show();
        // colonnes à afficher
        String[] colNoms = new String[] {
                COL_CODE,
                COL_LIB,
                COL_STOCK
        };
        // champs dans lesquelles afficher les colonnes
        int[] colNumeros = new int[] {
                R.id.listeTextViewCode, R.id.listeTextViewLib, R.id.listeTextViewStock
        };
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.list_entree, leCurseur, colNoms, colNumeros);

        // Assign adapter to ListView
        listViewEchant.setAdapter(dataAdapter);
        echantBdd.close();

    }

    //creation du sous menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.ajout:

                Intent intent = new Intent(Liste.this, AjoutEchantillon.class);

                startActivity(intent);

                return true;

            case R.id.liste:

                Intent intent2 = new Intent(Liste.this, Liste.class);

                startActivity(intent2);

                return true;

            case R.id.maj:

                Intent intent3 = new Intent(Liste.this, Maj.class);

                startActivity(intent3);

                return true;

            case R.id.quitter:

                Intent activityMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activityMain);

                return true;

        }

        return false;

    }

    @Override
    public void onBackPressed() {
        Intent activityMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activityMain);
        finish();
    }
}