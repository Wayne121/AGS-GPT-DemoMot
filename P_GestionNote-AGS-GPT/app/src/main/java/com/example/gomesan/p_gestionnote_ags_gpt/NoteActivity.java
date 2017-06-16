package com.example.gomesan.p_gestionnote_ags_gpt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Author: Gomesan
 * Lieu: ETML
 * Description: Activité pour gérer l'interface de tous ce qui est note
 */
public class NoteActivity extends ParameterActivity {

    //Déclaration des variables
    private ViewGroup layout;
    private TextView txtOne;
    private TextView txtTwo;
    private DatabaseHelper db = new DatabaseHelper(this);

    //Constructeur de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Utilise la méthode de l'activité ParameterActivity pour changer le thème de l'activité
        applyTheme();
        //Charge les informations de base de l'activité
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        layout = (ViewGroup) findViewById(R.id.content);

        //Récupère les informations du Main activity
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");
        String branchName = extras.getString("branchName");

        //Déclare une liste d'objet de la classe MarkClass
        List<MarkClass> markClassList = db.getAllMark(years,branchName);
        //Boucle qui affiche les boutons selon le nombre d'objet dans la liste
        for (MarkClass c : markClassList) {
            //Affiche les notes
            displayNote(c.getMarName(), c.getMarNote());
        }
    }

    //Méthode qui permet d'instancier le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    //Méthode qui permet pour chaque item dans le menu d'avoir une action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Permet de lancer l'activité des paramètre
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            //Permet de commencer un dialogue avec l'utilisateur pour créer une nouvelle note
            case R.id.menu_note:
                onCreateDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Méthode qui permet d'afficher les notes
    @SuppressLint("InlinedApi")
    private void displayNote(String name, String mark) {

        //Déclare l'interface qui permet d'afficher les notes
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.layout_shownote;
        LinearLayout relativeLayout = (LinearLayout) inflater.inflate(id, null, false);

        //Déclare les variables de l'interface
        txtOne = (TextView) relativeLayout.findViewById(R.id.txtViewOne);
        txtTwo = (TextView) relativeLayout.findViewById(R.id.txtViewTwo);

        //Set les informations pour l'affichage
        txtOne.setText(name);
        txtTwo.setText(mark);

        //Ajoute à l'interface la nouvelle interface
        layout.addView(relativeLayout);
    }

    //Peremt d'ouvrir un dialogue avec l'utilisateur
    public void onCreateDialog() {
        //Ouvre le dialogue
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(NoteActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.layout_addnote, null);

        //Récupère les information du bouton du dialogue
        Button bSend = (Button) mView.findViewById(R.id.bSend);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Appelle la méthode pour créer un bouton et lui transmet les informations du texte
                final EditText txtName = (EditText) mView.findViewById(R.id.txtName);
                final EditText txtNote = (EditText) mView.findViewById(R.id.txtNote);
                addNoteDB(txtName.getText().toString(), txtNote.getText().toString());
            }
        });

        //Affiche le dialogue
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    //Permet de créer une nouvelle note et affiche un bouton
    private void addNoteDB(String name, String note){

        //Récupère les informations de l'ancienne activité
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");
        String branchName = extras.getString("branchName");

        //Appelle la méthode pour ajouter une note
        db.addMark(new MarkClass(name, note,years,branchName));
        //Affiche la nouvelle note
        displayNote(name, note);

    }
}
