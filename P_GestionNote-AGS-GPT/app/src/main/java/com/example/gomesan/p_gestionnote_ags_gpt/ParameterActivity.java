package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ParameterActivity extends AppCompatActivity {
    //Initialisation tableau de string pour le spinner
    private String[] arraySpinner;
    //String qui vont contenir la couleur du theme et du texte
    public static String themeColor;
    public static String themeTextColor;
    public static String passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Call de la méthode applyTheme
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);

        //Ajout du contenu dans le spinner
        this.arraySpinner = new String[] {
                "Rouge", "Bleu", "Vert", "Orange", "Jaune", "Rose", "Bleu Clair", "Brun", "Gris",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        Spinner s = (Spinner) findViewById(R.id.spinner);
        s.setAdapter(adapter);
        //initialisation du bouton et si le bouton est cliqué appel la méthode bChangeListener
        Button bChange = (Button) findViewById(R.id.bChange);
        bChange.setOnClickListener(bChangeListener);


        Button bChangePassword = (Button) findViewById(R.id.bPasswordEdit);
        bChangePassword.setOnClickListener(bChangePasswordListener);


    }

    private View.OnClickListener bChangePasswordListener = new View.OnClickListener() {
        //API pour le finishAffinity()
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            //Instancie l'EditText
            EditText txtPasswordLogin = (EditText) findViewById(R.id.txtPasswordEdit);
            //Création des SharedPreferences
            SharedPreferences passwordChange = getSharedPreferences("Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = passwordChange.edit();
            //récupération du login de l'utilisateur
            passwordLogin = txtPasswordLogin.getText().toString();
            //Ajoute dans les SharedPreferences le mot de passe
            editor.putString("passwordLogin",passwordLogin);
            //Commit pour sauvegarder ce qui a été enregistré dans les preferences
            editor.commit();

            //Créé une nouvelle activité Main Activity et ferme les toutes les autres
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    };

    public String getPassword(){
        //Création du Shared Preferences
        SharedPreferences passwordChange = getSharedPreferences("Data", Context.MODE_PRIVATE);
        //Récupération dans une variable le mot de passe
        //Mot de passe de base : .Etml-
        String storageThemeColor = passwordChange.getString("passwordLogin", ".Etml-");
        //Return le mot de passe
        return storageThemeColor;
    }

    //Méthode quand le bouton est touché
    private View.OnClickListener bChangeListener = new View.OnClickListener(){
        //API pour le finishAffinity()
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            //Ràcupére le contenu du spinner
            Spinner s = (Spinner) findViewById(R.id.spinner);
            String themePicked = s.getSelectedItem().toString();

            //Change le themeColor et themeTextColor selon le contenu du spinner
            switch (themePicked)
            {
                case "Rouge":
                    themeColor = "redTheme";
                    themeTextColor = "#FFFFFF";
                    break;
                case "Bleu":
                    themeColor = "blueTheme";
                    themeTextColor ="#FFFFFF";
                    break;
                case "Vert":
                    themeColor ="greenTheme";
                    themeTextColor ="#FFFFFF";
                    break;
                case "Orange":
                    themeColor ="orangeTheme";
                    themeTextColor ="#FFFFFF";
                    break;
                case "Jaune":
                    themeColor ="yellowTheme";
                    themeTextColor ="#000000";
                    break;
                case "Rose":
                    themeColor ="pinkTheme";
                    themeTextColor ="#FFFFFF";
                    break;
                case "Bleu Clair":
                    themeColor ="lightBlueTheme";
                    themeTextColor ="#FFFFFF";
                    break;
                case "Brun":
                    themeColor ="brownTheme";
                    themeTextColor ="#FFFFFF";
                    break;
                case "Gris":
                    themeColor ="greyTheme";
                    themeTextColor ="#FFFFFF";
                    break;
                default: themeColor = "AppTheme";
                    break;
            }
            //Créé une SharedPreferences et y stocke une donnée qui est reliée à une clé pour l'identifier
            //SharedPreferences agit un peu comme une base de donnée où chaque table (clé) contient une seule donnée
            //ça permet dans notre cas d'éviter de faire une table dans la base de donnée pour deux variable seulement
            SharedPreferences themeStorage = getSharedPreferences("Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = themeStorage.edit();
                              /*Clé         Donnée*/
            editor.putString("themeColor",themeColor);
            editor.putString("themeTextColor",themeTextColor);
            //Commit pour sauvegarder ce qui a été enregistré dans les preferences
            editor.commit();

            //Créé une nouvelle activité Main Activity et ferme les toutes les autres
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finishAffinity();

        }
    };

    //Méthode pour appliquer le théme
    public void applyTheme()
    {
        //Récupere les préférences utilisateurs
        SharedPreferences themeStorage = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String storageThemeColor = themeStorage.getString("themeColor", "blueTheme");
        String storageThemeTextColor = themeStorage.getString("themeTextColor", "#FFFFFF");

        //Applique le théme
        int styleId = getResources().getIdentifier(storageThemeColor, "style", getPackageName());
        setTheme(styleId);

        //Applique la couleur du texte sur le titre
        if(storageThemeTextColor == "#000000") {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Gestionnaire de notes ETML</font>"));
        }
        else
        {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Gestionnaire de notes ETML</font>"));
        }



    }

}
