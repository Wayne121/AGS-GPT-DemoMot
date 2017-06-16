package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Author: Gomesan
 * Lieu: ETML
 * Description: Activité de connexion pour acceder a l'application
 */

public class loginActivity extends ParameterActivity {
    //Déclaration des variables
    private Button bLogin;
    private EditText txtLogin;

    //Constructeur de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Charge les informations de base de l'activité
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instancie les infromations de l'interface graphique
        bLogin = (Button)findViewById(R.id.bConnexion);
        txtLogin = (EditText) findViewById(R.id.txtLogin);

        //click du bouton bLogin qui permet de vérifier les informations du mot de passe entrée
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Vérifie si le mot de passe entrée corespond au mot de passe configurer
                if(txtLogin.getText().toString().equals(getPassword())) {
                    Toast.makeText(getApplicationContext(),
                            "Redirection...",Toast.LENGTH_SHORT).show();

                    //Redirige vers l'activité princpal
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    //Affiche si le mot de passe est eronné
                    Toast.makeText(getApplicationContext(), "Mot de passe erroné",Toast.LENGTH_SHORT).show();
                    txtLogin.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
