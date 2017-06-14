package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class BranchActivity extends ParameterActivity {
    //Initialisation du ViewGroup
    private ViewGroup layout;

    private String themeColor = ParameterActivity.themeColor;
    private String themeTextColor = ParameterActivity.themeTextColor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Gestionnaire de notes ETML</font>"));
        //Initialisation du layout de base
        setContentView(R.layout.activity_branch);
        //Initialisation du GridLayout sur celui de base
        GridLayout gridLayout = (GridLayout) findViewById(R.id.content);
        //Boucle qui affiche toutes les branches
        for(int i =0; i <14; i++) {
            //Création d'un bouton
            Button btn = new Button(this);
            //Texte du bouton = Nom de la branche
            String txt = "Branch" + i;
            btn.setText(txt);
            //Nombre de colonnes et lignes dans le GridLayout
            gridLayout.setColumnCount(3);
            gridLayout.setRowCount(7);
            //Création de paramètres sur le layout
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            //Marges
            layoutParams.setMargins(5,3, 0, 0); // left, top, right, bottom
            //Ajout du bouton à la ligne
            btn.setLayoutParams(new TableRow.LayoutParams(0,i));
            //Set la taille du boutons (200 Hauteur / 330 Largeur)
            btn.setHeight(200);
            btn.setWidth(330);
            //Mets les paramètres sur le bouton
            btn.setLayoutParams(layoutParams);
            //Affichage du bouton sur le GridLayout
            gridLayout.addView(btn,i);
        }

    }

    private View.OnClickListener noteLaco = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), NoteActivity.class);
            i.putExtra("nom", "George");
            startActivity(i);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.branch_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ) {
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.menu_branch:
                Toast.makeText(this, "Settings menu selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
