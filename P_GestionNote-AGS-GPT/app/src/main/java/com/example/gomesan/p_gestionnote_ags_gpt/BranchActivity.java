package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.List;
/**
 * Author: Gomesan
 * Lieu: ETML
 * Description: Activité pour gérer l'interface de tous ce qui est branche
 */
public class BranchActivity extends ParameterActivity {

    //Déclaration des variables
    private ViewGroup layout;
    private DatabaseHelper db = new DatabaseHelper(this);
    private Button button;
    int i;


    //Constructeur de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Utilise la méthode de l'activité ParameterActivity pour changer le thème de l'activité
        applyTheme();
        //Charge les informations de base de l'activité
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        //Paramètre l'interface grille pour les boutons
        layout = (ViewGroup) findViewById(R.id.content);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.content);
        gridLayout.setColumnCount(3);

        //Récupère les informations du Main activity
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");

        //Déclare une liste d'objet de la classe BranchClass
        List<BranchClass> branchClasses = db.getAllBranch(years);
        //Boucle qui affiche les boutons selon le nombre d'objet dans la liste
        for (BranchClass c : branchClasses) {
            //Affiche les boutons
            showButton(c.getBraText(),c.getIdBranch());
        }

        //Déclare la variable en mettant le nombre d'objet dans la table pour permettre d'avoir le bon ID pour la création de branche
        i = db.getBranchCount();
        i++;
    }

    //Méthode qui permet d'instancier le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.branch_menu, menu);
        return true;
    }

    //Méthode qui permet pour chaque item dans le menu d'avoir une action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ) {
            //Permet de lancer l'activité des paramètre
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            //Permet de commencer un dialogue avec l'utilisateur pour créer une nouvelle branche
            case R.id.menu_branch:
               onCreateDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Méthode de click pour chaque bouton pour aller vers l'activié des notes
    public View.OnClickListener goToNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Déclare la nouvelle activité
            Intent i = new Intent(getApplicationContext(), NoteActivity.class);

            //Récupère les informations de l'ancienne activité
            Bundle extras = getIntent().getExtras();
            String years = extras.getString("year");

            //Instancie des paramètre en transmettre
            i.putExtra("year", years);
            i.putExtra("branchName", String.valueOf(v.getId()));

            //Lance l'activité avec les paramètres
            startActivity(i);
        }
    };

    //Méthode qui ouvre un dialogue pour pouvoir créer une nouvelle branche
    public void onCreateDialog() {
        //Ouvre le dialogue
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(BranchActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.layout_addbutton, null);

        //Récupère les information du bouton du dialogue
        Button bSend = (Button) mView.findViewById(R.id.bSend);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Appelle la méthode pour créer un bouton et lui transmet les informations du texte
                final EditText txtName = (EditText) mView.findViewById(R.id.txtName);
                addButtonBD(txtName.getText().toString());
            }
        });

        //Affiche le dialogue
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    //Permet de créer une nouvelle branche et affiche un bouton
    private void addButtonBD(String name){
        //Récupère les informations de l'ancienne activité
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");

        button = new Button(this);
        db.addBranch(new BranchClass(i ,name , years));
        button.setText(name);
        button.setId(i++);
        button.setHeight(200);
        button.setWidth(330);
        layout.addView(button);
        button.setOnClickListener(goToNote);
    }

    //Permet d'afficher un bouton pour chaque entité dans la liste
    private void showButton(String name, int id){
        button = new Button(this);
        button.setText(name);
        button.setId(id);
        button.setHeight(200);
        button.setWidth(330);
        layout.addView(button);
        button.setOnClickListener(goToNote);
    }
}
