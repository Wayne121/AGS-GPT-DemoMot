package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Créé par : André Gomes & Grégory Poget
 * Dans la période du 29 mai au 26 juin 2017
 * Description : Activité principale du site ou se retrouve l'utilisateur après s'ête log
 * ETML
 */

public class MainActivity extends ParameterActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Application du thème à l'activité
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation des différents boutons
        Button bFirstYear = (Button) findViewById(R.id.bFirstYear);
        bFirstYear.setOnClickListener(branchFirstYear);

        Button bSecondYear = (Button) findViewById(R.id.bSecondYear);
        bSecondYear.setOnClickListener(branchSecondYear);

        Button bThirdYear = (Button) findViewById(R.id.bThirdYear);
        bThirdYear.setOnClickListener(branchThirdYear);

        Button bFourthYear = (Button) findViewById(R.id.bFourthYear);
        bFourthYear.setOnClickListener(branchFourthYear);

        Button bResult = (Button) findViewById(R.id.bResult);
        bResult.setOnClickListener(noteResult);

    }

    //Si le bouton de 1ère année est appuyé
    private View.OnClickListener branchFirstYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Création d'une activité avec l'extra de l'année du bouton
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "1");
            startActivity(i);
        }
    };

    //Si le bouton de 2ème année est appuyé
    private View.OnClickListener branchSecondYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "2");
            Log.i("test", "2");
            startActivity(i);
        }
    };

    //Si le bouton de 3ème année est appuyé
    private View.OnClickListener branchThirdYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "3");
            startActivity(i);
        }
    };

    //Si le bouton de 4ème année est appuyé
    private View.OnClickListener branchFourthYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "4");
            startActivity(i);
        }
    };

    //Si le bouton de resultats est appuyé
    private View.OnClickListener noteResult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), ResultActivity.class);
            i.putExtra("nom", "George");
            startActivity(i);
        }
    };

    //Quand on clique sur les 3 ronds dans le coin supérieur droit
    public boolean onCreateOptionsMenu(Menu menu) {
        //Affiche le menu des branches
        getMenuInflater().inflate(R.menu.parameter_menu, menu);
        return true;
    }

    @Override
    //Quand on choisit une des options dans le menu
    public boolean onOptionsItemSelected(MenuItem item) {
        //fait un switch sur ce qui a été cliqué
        switch (item.getItemId() ) {
            //Si c'est le bouton option
            case R.id.menu_option:
                //Crée une activité option et la démarre
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
