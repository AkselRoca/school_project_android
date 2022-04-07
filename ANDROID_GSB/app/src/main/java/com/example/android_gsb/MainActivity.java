package com.example.android_gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button ajout;
    private Button liste;
    private Button maj;
    private Menu Menu;

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

                Intent intent = new Intent(MainActivity.this, AjoutEchantillon.class);

                startActivity(intent);

                return true;

            case R.id.liste:

                Intent intent2 = new Intent(MainActivity.this, Liste.class);

                startActivity(intent2);

                return true;

            case R.id.maj:

                Intent intent3 = new Intent(MainActivity.this, Maj.class);

                startActivity(intent3);

                return true;

            case R.id.quitter:

                finish();

                return true;

        }

        return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testBd();

        this.ajout = (Button) findViewById(R.id.buttonAjout);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityAjout = new Intent(getApplicationContext(), AjoutEchantillon.class);
                startActivity(activityAjout);
                finish();
            }
        });

        this.liste = (Button) findViewById(R.id.buttonListe);
        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityListe = new Intent(getApplicationContext(), Liste.class);
                startActivity(activityListe);
                finish();
            }
        });

        this.maj = (Button) findViewById(R.id.buttonMaj);
        maj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityMaj = new Intent(getApplicationContext(), Maj.class);
                startActivity(activityMaj);
                finish();
            }
        });

    }

    public void testBd() {
        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);

        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        //On insère DES ECHANTILLONS DANS LA BD
        echantBdd.insererEchantillon(new Echantillon("A0001", "COND125", "15"));
        echantBdd.insererEchantillon(new Echantillon("A0001", "COND250", "25"));
        echantBdd.insererEchantillon(new Echantillon("A0002", "COND125", "30"));
        echantBdd.insererEchantillon(new Echantillon("A0002", "COND250", "40"));
        echantBdd.insererEchantillon(new Echantillon("B0001", "COND125", "15"));
        echantBdd.insererEchantillon(new Echantillon("B0001", "COND250", "10"));
        echantBdd.insererEchantillon(new Echantillon("B0002", "COND125", "10"));
        echantBdd.insererEchantillon(new Echantillon("B0002", "COND250", "10"));

        Cursor unCurseur = echantBdd.getData();
        System.out.println("il y a " + String.valueOf(unCurseur.getCount()) + " echantillons dans la BD");
        echantBdd.close();
    }



}