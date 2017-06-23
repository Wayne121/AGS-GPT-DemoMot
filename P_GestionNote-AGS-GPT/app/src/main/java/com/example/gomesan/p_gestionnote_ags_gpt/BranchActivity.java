package com.example.gomesan.p_gestionnote_ags_gpt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
 * Créé par : André Gomes & Grégory Poget
 * Dans la période du 29 mai au 26 juin 2017
 * Description : Activité affichant chaque branches créées par l'utilisateur
 * Stockage dans une base de données les différentes branches
 * ETML
 */


public class BranchActivity extends ParameterActivity {

    //Création de différentes variables
    private ViewGroup layout;
    private DatabaseHelper db = new DatabaseHelper(this);
    private Button button;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Application du thème
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        //Initialisation du gridLayout
        layout = (ViewGroup) findViewById(R.id.content);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.content);
        //3 colonnes de widget dans le gridLayout
        gridLayout.setColumnCount(3);
        //Récuperation des extras fournis dans l'activité précédente
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");

        //Pour chaque branches
        List<BranchClass> branchClasses = db.getAllBranch(years);
        for (BranchClass c : branchClasses) {
            //Appele la méthode showButton
            showButton(c.getBraText(),c.getIdBranch());
        }
        //variable i égal au nombre de branche dans la base de donnée
        //Permet de faire un log avec ce nombre pour voir dans la console Android Studio combien on en a
        i = db.getBranchCount();
        Log.i("test", String.valueOf(i));
        i++;
        Log.i("test", String.valueOf(i));
    }

    @Override
    //Quand on clique sur les 3 ronds dans le coin supérieur droit
    public boolean onCreateOptionsMenu(Menu menu) {
        //Affiche le menu des branches
        getMenuInflater().inflate(R.menu.branch_menu, menu);
        return true;
    }

    @Override
    //Quand on choisit une des options dans le menu
    public boolean onOptionsItemSelected(MenuItem item) {
        //fait un switch sur ce qui a été cliqué
        switch (item.getItemId() ) {
            //Si c'est le bouton d'option
            case R.id.menu_option:
                //Crée une activité option et la démarre
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            //Si c'est le bouton Ajouter une branche
            case R.id.menu_branch:
                //Appele la méthode onCreateDialog
                onCreateDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Si le bouton d'une branche est cliqué
    public View.OnClickListener goToNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Préparation d'une activité de notes
            Intent i = new Intent(getApplicationContext(), NoteActivity.class);
            //Récuperation des extras fournis dans l'activité précédente
            Bundle extras = getIntent().getExtras();
            String years = extras.getString("year");
            //Insertion d'Extra pour la prochaine activité incluant l'année et le nom de la branche cliquée
            i.putExtra("year", years);
            i.putExtra("branchName", String.valueOf(v.getId()));
            //Démarrage de l'activité
            startActivity(i);
        }
    };

    //Méthode créant un dialogue
    public void onCreateDialog() {
        //Nouveau dialogue selon le layout de "layout_addbutton"
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(BranchActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.layout_addbutton, null);
        //Initialisation du boutton d'envoi
        Button bSend = (Button) mView.findViewById(R.id.bSend);
        //Quand le bouton Envoyer est cliqué
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialisation d'un editText
                final EditText txtName = (EditText) mView.findViewById(R.id.txtName);
                //Appel de la méthode addButtonBD avec le contenu de l'editText en paramètre
                addButtonBD(txtName.getText().toString());
            }
        });
        //Affichage du dialogue
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    //Méthode ajoutant un bouton à l'interface
    private void addButtonBD(String name){
        //Récuperation des extras fournis dans l'activité précédente
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");

        //Création du bouton selon l'année et son nom
        button = new Button(this);
        db.addBranch(new BranchClass(i ,name , years));
        button.setText(name);
        button.setId(i++);
        button.setHeight(200);
        button.setWidth(330);
        layout.addView(button);
        button.setOnClickListener(goToNote);
    }

    //Affichage du bouton si il existe déjà
    private void showButton(String name, int id){
        //Affichage du bouton selon son nom et id
        button = new Button(this);
        button.setText(name);
        button.setId(id);
        button.setHeight(200);
        button.setWidth(330);
        layout.addView(button);
        button.setOnClickListener(goToNote);
       // i = id;
    }
}
