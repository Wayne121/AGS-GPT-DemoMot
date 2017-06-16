package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Author: Gomesan
 * Lieu: ETML
 * Description: Activité principal de l'application
 */

public class MainActivity extends ParameterActivity {

    //Constructeur de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Utilise la méthode de l'activité ParameterActivity pour changer le thème de l'activité
        applyTheme();
        //Charge les informations de base de l'activité
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Déclare le bouton pour la première année
        Button bFirstYear = (Button) findViewById(R.id.bFirstYear);
        bFirstYear.setOnClickListener(branchFirstYear);

        //Déclare le bouton pour la seconde année
        Button bSecondYear = (Button) findViewById(R.id.bSecondYear);
        bSecondYear.setOnClickListener(branchSecondYear);

        //Déclare le bouton pour la troisième année
        Button bThirdYear = (Button) findViewById(R.id.bThirdYear);
        bThirdYear.setOnClickListener(branchThirdYear);

        //Déclare le bouton pour la quatrième année
        Button bFourthYear = (Button) findViewById(R.id.bFourthYear);
        bFourthYear.setOnClickListener(branchFourthYear);

        //Déclare le bouton pour les résultat
        Button bResult = (Button) findViewById(R.id.bResult);
        bResult.setOnClickListener(noteResult);

    }

    //Méthode de clique de la première année
    private View.OnClickListener branchFirstYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Renvoie vers l'activié des branches en transmettant des informations
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "1");
            startActivity(i);
        }
    };

    //Méthode de clique de la seconde année
    private View.OnClickListener branchSecondYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Renvoie vers l'activié des branches en transmettant des informations
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "2");
            Log.i("test", "2");
            startActivity(i);
        }
    };

    //Méthode de clique de la troisième année
    private View.OnClickListener branchThirdYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Renvoie vers l'activié des branches en transmettant des informations
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "3");
            startActivity(i);
        }
    };

    //Méthode de clique de la quatrième année
    private View.OnClickListener branchFourthYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Renvoie vers l'activié des branches en transmettant des informations
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "4");
            startActivity(i);
        }
    };

    //Méthode de clique des résultats
    private View.OnClickListener noteResult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Renvoie vers l'activié des résultats en transmettant des informations
            Intent i = new Intent(getApplicationContext(), NoteActivity.class);
            i.putExtra("nom", "George");
            startActivity(i);
        }
    };

    //Méthode qui permet d'instancier le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parameter_menu, menu);
        return true;
    }

    //Méthode qui permet pour chaque item dans le menu d'avoir une action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ) {
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
