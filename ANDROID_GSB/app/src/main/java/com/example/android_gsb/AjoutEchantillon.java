package com.example.android_gsb;

import android.content.Intent;
import android.database.Cursor;
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

public class AjoutEchantillon extends AppCompatActivity {
    EditText editTextCode, editTextLib, editTextStock;

    Button buttonEnvoyer;
    private Button buttonQuitter;

    private String code;
    private String lib;
    private String stock;
    BdAdapter myDb;
    private TextView textViewHello;
    private android.view.Menu Menu;

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

                Intent intent = new Intent(AjoutEchantillon.this, AjoutEchantillon.class);

                startActivity(intent);

                return true;

            case R.id.liste:

                Intent intent2 = new Intent(AjoutEchantillon.this, Liste.class);

                startActivity(intent2);

                return true;

            case R.id.maj:

                Intent intent3 = new Intent(AjoutEchantillon.this, Maj.class);

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
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ajout_echantillon);

        initialisations();

        buttonQuitter.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                finish(); //fermeture de la fenêtre

            }

        });
    }

    public void initialisations() {
        // récupération de l'EditText grâce à son ID
        editTextCode = (EditText) findViewById(R.id.ajoutEditTextCode);
        // récupération de l'EditText grâce à son ID
        editTextLib = (EditText) findViewById(R.id.ajoutEditTextLib);
        // récupération de l'EditText grâce à son ID
        editTextStock = (EditText) findViewById(R.id.ajoutEditTextStock);
        // récupération du Button grâce à son ID
        buttonEnvoyer = (Button) findViewById(R.id.ajoutButtonAjouter);
        // récupération du Button grâce à son ID
        buttonQuitter = (Button) findViewById(R.id.ajoutButtonQuitter);

        // récupération du TextView grâce à son ID
        textViewHello = (TextView) findViewById(R.id.textViewHello);

        // association d'un écouteur d'évenement à l'événement Clic sur le bouton buttonEnvoyer
        buttonEnvoyer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // on récupère le texte écrit dans l'objet EditText
                code = editTextCode.getText().toString();
                lib = editTextLib.getText().toString();
                stock = editTextStock.getText().toString();

                Echantillon unEchant = new Echantillon(code, lib, stock);

                String text = editTextCode.getText().toString().trim();
                String text2 = editTextLib.getText().toString().trim();
                String text3 = editTextStock.getText().toString().trim();

                if (TextUtils.isEmpty(text) || TextUtils.isEmpty(text2) || TextUtils.isEmpty(text3)) {
                    editTextCode.setError("Saisir un code*");
                    editTextLib.setError("Saisir un libellé*");
                    editTextStock.setError("Saisir un stock*");
                } else if (tryLib(lib) == true) {
                    editTextLib.setError("Libellé déjà existant");
                } else {
                    addData(unEchant);
                    // on affiche "lib = editTextLib.getText().toString();" dans une pop-up qui s'affiche
                    // quelques seconde en bas d'écran
                    Toast.makeText(getApplicationContext(), "Code : " + code + " | Libellé : " + lib + " | Quantité :" + stock + " ajouté à la BDD", Toast.LENGTH_LONG).show();

                    // on affiche "Hello votrePrenom ! " dans l'objet TextView placé sous la zone de saisie
                    textViewHello.setText("Code : " + code + " | Libellé : " + lib + " | Quantité :" + stock + " ajouté à la BDD");
                }


            }
        });

        // association d'un écouteur d'évenement à l'événement Quitter
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });



    }
    public void addData(Echantillon unEchant) {
        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);

        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        //On insère DES ECHANTILLONS DANS LA BD
        echantBdd.insererEchantillon(unEchant);
        echantBdd.close();

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



    @Override
    public void onBackPressed() {
        Intent activityMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activityMain);
        finish();
    }
}