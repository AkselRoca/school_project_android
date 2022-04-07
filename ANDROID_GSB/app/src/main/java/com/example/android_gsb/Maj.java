package com.example.android_gsb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Maj extends AppCompatActivity {
    private Button buttonQuitter;
    EditText editTextLib, editTextStock;

    Button ajoutButtonAjouter, majButtonSupprimer;

    private String code;
    private String unCode;
    private String stock;
    private String unLib;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maj);

        Button buttonQuitter = (Button) findViewById(R.id.ajoutButtonQuitter);
        // récupération de l'EditText grâce à son ID
        editTextLib = (EditText) findViewById(R.id.majEditTextLib);

        // récupération de l'EditText grâce à son ID
        editTextStock = (EditText) findViewById(R.id.majEditTextStock);
        // récupération du Button grâce à son ID
        ajoutButtonAjouter = (Button) findViewById(R.id.majButtonMaj);
        majButtonSupprimer = (Button) findViewById(R.id.majButtonSupprimer);
        // on récupère le texte écrit dans l'objet EditText


        initialisationsMaj();

        buttonQuitter.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                finish(); //fermeture de la fenêtre

            }

        });
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

                Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Maj.this, AjoutEchantillon.class);

                startActivity(intent);

                return true;

            case R.id.liste:

                Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(Maj.this, Liste.class);

                startActivity(intent2);

                return true;

            case R.id.maj:

                Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();

                Intent intent3 = new Intent(Maj.this, Maj.class);

                startActivity(intent3);

                return true;

            case R.id.quitter:

                Intent activityMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activityMain);

                return true;

        }

        return false;

    }

    public void initialisationsMaj() {

        // association d'un écouteur d'évenement à l'événement Clic sur le bouton buttonEnvoyer
        ajoutButtonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unLib = editTextLib.getText().toString();
                stock = editTextStock.getText().toString();

                String text = editTextLib.getText().toString().trim();
                String text3 = editTextStock.getText().toString().trim();
                if (TextUtils.isEmpty(text) || TextUtils.isEmpty(text3)) {
                    editTextLib.setError("Saisir un libellé*");
                    editTextStock.setError("Saisir un stock*");
                } else if (tryLib(unLib) != true) {
                    editTextLib.setError("Le libellé n'existe pas");

                } else if (tryLib(unLib) == true) {
                    updateData(unLib, stock);
                }


            }
        });

        // association d'un écouteur d'évenement à l'événement Clic sur le bouton buttonEnvoyer
        majButtonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unLib = editTextLib.getText().toString();
                stock = editTextStock.getText().toString();

                String text = editTextLib.getText().toString().trim();
                String text3 = editTextStock.getText().toString().trim();
                if (TextUtils.isEmpty(text) || TextUtils.isEmpty(text3)) {
                    editTextLib.setError("Saisir un libellé*");
                    editTextStock.setError("Saisir un stock*");
                } else if (tryLib(unLib) != true) {
                    editTextLib.setError("Le libellé n'existe pas");
                } else if (tryLib(unLib) == true) {
                    updateDataDelete(unLib, stock);
                }


            }
        });


    }

    public void updateData(String lib, String stock) {

        Echantillon unEchant = new Echantillon(null, null, null);

        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);

        //On ouvre la base de données pour écrire dedans
        echantBdd.open();

        if (echantBdd.getEchantillonWithLib(lib) != null) {
            unEchant = echantBdd.getEchantillonWithLib(lib);
        }


        String stockDebase = unEchant.getQuantiteStock();

        int result = Integer.parseInt(stockDebase) + Integer.parseInt(stock);

        unEchant.setQuantiteStock(String.valueOf(result));

        //On insère DES ECHANTILLONS DANS LA BD
        echantBdd.updateEchantillon(unEchant.getCode(), unEchant);
        echantBdd.close();
        Toast.makeText(getApplicationContext(), stock + " ajouté au stock !", Toast.LENGTH_LONG).show();

    }
    public void updateDataDelete(String lib, String stock) {

        Echantillon unEchant = new Echantillon(null, null, null);

        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);

        //On ouvre la base de données pour écrire dedans
        echantBdd.open();

        if (echantBdd.getEchantillonWithLib(lib) != null) {
            unEchant = echantBdd.getEchantillonWithLib(lib);
        }

        String stockDebase = unEchant.getQuantiteStock();

        int result = Integer.parseInt(stockDebase) - Integer.parseInt(stock);

        unEchant.setQuantiteStock(String.valueOf(result));

        //On insère DES ECHANTILLONS DANS LA BD
        echantBdd.updateEchantillon(unEchant.getCode(), unEchant);
        echantBdd.close();
        Toast.makeText(getApplicationContext(), stock + " supprimé du stock !", Toast.LENGTH_LONG).show();

    }


    public boolean tryLib(String lib) {
        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);

        //On ouvre la base de données pour écrire dedans
        echantBdd.open();

        if (echantBdd.verifLib(lib) == true) {
            return true;
        } else {
            echantBdd.close();
            return false;
        }

    }

}