package com.example.gomesan.p_gestionnote_ags_gpt;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
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
 * Créé par : André Gomes & Grégory Poget
 * Dans la période du 29 mai au 26 juin 2017
 * Description : Activité affichant les notes et notes pondérées ajoutées par l'élève
 * ETML
 */

public class NoteActivity extends ParameterActivity {


    //Création de différentes variables
    private ViewGroup layout;
    private TextView moyenne;
    private TextView txtOne;
    private TextView txtTwo;
    private DatabaseHelper db = new DatabaseHelper(this);
    private int nbNote =0;
    private double calculmoy=0;
    private int totalPond;
    private boolean isPond;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        //Initialisation du layout et d'un TextView
        layout = (ViewGroup) findViewById(R.id.content);
        moyenne = (TextView) findViewById(R.id.moyenne);
        //Récuperation des extras fournis dans l'activité précédente
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");
        String branchName = extras.getString("branchName");

        //Pour chaque note
        List<MarkClass> markClassList = db.getAllMark(years,branchName);
        for (MarkClass c : markClassList) {

            //appel de la méthode displayNote
            displayNote(c.getMarName(), c.getMarNote(), c.getMarPourcent());
            //Si la pondération est pas null
            if(c.getMarPourcent() != null)
            {
                //Algorithme de calcul de la moyenne
                totalPond = totalPond + Integer.parseInt(c.getMarPourcent());
                calculmoy = calculmoy + Double.parseDouble(c.getMarPourcent()) * Double.parseDouble(c.getMarNote());
                //Variable pour dire que la branche est pondérée
                isPond = true;
            }
            //Si il n'y a pas de pondération
            else {
                //Algorithme de calcul de la moyenne
                nbNote++;
                calculmoy = calculmoy + Double.parseDouble(c.getMarNote());
            }
        }
        //Si la branche est pondérée
        if(isPond == true && calculmoy != 0)
        {
            //Algorithme de calcul de la moyenne
            calculmoy = calculmoy / totalPond;
        }
        //Sinon
        else {
            //Algorithme de calcul de la moyenne
            if( calculmoy != 0) {
                calculmoy = calculmoy / nbNote;
            }
        }
        //String contenant la moyenne
        String stringMoy = String.valueOf(calculmoy);
        //Si la moyenne est supérieur ou égale à 4
        if(calculmoy >= 4)
        {
            //Texte en vert
            moyenne.setTextColor(Color.parseColor("#4caf50"));
        }
        //Si la moyenne est plus petite que 4
        if(calculmoy < 4)
        {
            //Texte en rouge
            moyenne.setTextColor(Color.parseColor("#f44336"));
        }
        //Set le texte d'un textview avec la moyenne
        moyenne.setText(stringMoy);


    }

    @Override
    //Quand on clique sur les 3 ronds dans le coin supérieur droit
    public boolean onCreateOptionsMenu(Menu menu) {
        //Affiche le menu des notes
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    //Quand on choisit une des options dans le menu
    public boolean onOptionsItemSelected(MenuItem item) {
        //Récuperation des extras fournis dans l'activité précédente
        Bundle extras = getIntent().getExtras();
        final String branchName = extras.getString("branchName");
        final String years = extras.getString("year");
        switch (item.getItemId()) {
            //Si c'est le bouton d'option
            case R.id.menu_option:
                //Crée une activité option et la démarre
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            //Si c'est le bouton d'ajout de note
            case R.id.menu_note:
                //Crée le dialogue pour ajouter une note
                onCreateDialog();
                return true;
            //Si c'est le bouton d'ajout de note pondérée
            case R.id.menu_notePond:
                //Crée le dialogue pour ajouter une note pondérée
                onCreateDialogPond();
                return true;
            //Si c'est le bouton de suppression de la branche
            case R.id.menu_erase:
                alertDialogBranch(years, branchName);
                return true;
            case R.id.menu_eraseMark:
                alertDialogMark(years, branchName);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("InlinedApi")
    //Méthode affichant les notes à l'ouverture de l'activité
    private void displayNote(String name, String mark, String pond) {
        //Initialisation de layout et des textView
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.layout_shownote;
        LinearLayout relativeLayout = (LinearLayout) inflater.inflate(id, null, false);
        txtOne = (TextView) relativeLayout.findViewById(R.id.txtViewOne);
        txtTwo = (TextView) relativeLayout.findViewById(R.id.txtViewTwo);

        //Si la pondération n'est pas null
        if(pond != null) {
            //Affiche un texte dans le premier textView
            //Exemple du texte : "Site PHP (Pondération 70%)"
            txtOne.setText(name + " (Pondération : " + pond + "%)");
        }
        //sinon
        else
        {
            //Affiche seulement le nom de la note
            txtOne.setText(name);
        }
        //affiche la note dans le 2ème TextView
        txtTwo.setText(mark);
        //Ajoute le textView au layout
        layout.addView(relativeLayout);
    }

    private void alertDialogBranch(final String years, final String branchName)
    {
        //Crée un dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Titre du dialogue et texte
        builder.setTitle("Effacer la branche");
        builder.setMessage("Voulez-vous vraiment effacer cette branche ?");
        //Bouton oui
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int which) {
                //Pour chaque note
                List<MarkClass> markClassList = db.getAllMark(years,branchName);
                for (MarkClass c : markClassList) {
                    //La retire de la base de donnée
                    db.deleteMark(c);
                }
                //Appel de la méthode pour effacer la branche
                db.deleteBranch(db.getBranch(Integer.parseInt(branchName)));
                //Ferme le dialogue
                dialog.dismiss();
                //Créé une nouvelle activité Main Activity et ferme les toutes les autres
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }

        });
        //Bouton non
        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ferme le dialogue
                dialog.dismiss();
            }
        });
        //Affiche le dialogue
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void alertDialogMark(final String years, final String branchName)
    {
        //Crée un dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Titre du dialogue et texte
        builder.setTitle("Effacer toutes les notes");
        builder.setMessage("Voulez-vous vraiment effacer toutes les notes de cette branche ?");
        //Bouton oui
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Pour chaque note
                List<MarkClass> markClassList = db.getAllMark(years,branchName);
                for (MarkClass c : markClassList) {
                    //La retire de la base de donnée
                    db.deleteMark(c);
                }
                //Ferme le dialogue
                dialog.dismiss();
                finish();
            }

        });
        //Bouton non
        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ferme le dialogue
                dialog.dismiss();
            }
        });
        //Affiche le dialogue
        AlertDialog alert = builder.create();
        alert.show();
    }

    //Méthode créant un dialogue pour ajouter une note
    public void onCreateDialog()
    {
        //Nouveau dialogue selon le layout de "layout_addnote"
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(NoteActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.layout_addnote, null);

        //Initialisation du boutton d'envoi
        Button bSend = (Button) mView.findViewById(R.id.bSend);
        //Quand le bouton Envoyer est cliqué
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialisation de deux EditText
                final EditText txtName = (EditText) mView.findViewById(R.id.txtName);
                final EditText txtNote = (EditText) mView.findViewById(R.id.txtNote);
                // //Appel de la méthode addNoteDB avec le contenu des editText en paramètre
                addNoteDB(txtName.getText().toString(), txtNote.getText().toString());
            }
        });
        //Affichage du dialogue
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    //Méthode créant un dialogue pour ajouter une note pondérée
    public void onCreateDialogPond()
    {
        //Nouveau dialogue selon le layout de "layout_addnotepond"
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(NoteActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.layout_addnotepond, null);
        Button bSend = (Button) mView.findViewById(R.id.bSend);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText txtName = (EditText) mView.findViewById(R.id.txtName);
                final EditText txtNote = (EditText) mView.findViewById(R.id.txtNote);
                //Différence avec l'autre dialogue il y a un editText en plus
                final EditText txtPond = (EditText) mView.findViewById(R.id.txtPond);
                addNotePondDB(txtName.getText().toString(), txtNote.getText().toString(),txtPond.getText().toString());
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    //Méthode ajoutant une note à la base de donnée
    private void addNoteDB(String name, String note){
        //Récuperation des extras fournis dans l'activité précédente
        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");
        String branchName = extras.getString("branchName");

        //appel de la méthode addMark qui ajoute à la base de donnée une note
        db.addMark(new MarkClass(name, note,years,branchName));
        //appel de la méthode display note
        displayNote(name, note, null);

        //Algorithme de calcul de la moyenne
        calculmoy = calculmoy*nbNote;
        //Incrémentation du nombre de notes
        nbNote++;
        double note2 = Double.parseDouble(note);
        calculmoy = calculmoy + note2;
        if(calculmoy != 0) {
            calculmoy = calculmoy / nbNote;
        }

    //String contenant la moyenne
    String stringMoy = String.valueOf(calculmoy);
    //Si la moyenne est supérieur ou égale à 4
        if(calculmoy >= 4)
    {
        //Texte en vert
        moyenne.setTextColor(Color.parseColor("#4caf50"));
    }
    //Si la moyenne est plus petite que 4
        if(calculmoy < 4)
    {
        //Texte en rouge
        moyenne.setTextColor(Color.parseColor("#f44336"));
    }
    //Set le texte d'un textview avec la moyenne
        moyenne.setText(stringMoy);

    }

    //Méthode ajoutant une note pondérée à la base de donnée
    private void addNotePondDB(String name, String note, String pond){

        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");
        String branchName = extras.getString("branchName");
        //appel de la méthode addMarkPond qui ajoute à la base de donnée une note pondérée
        db.addMarkPond(new MarkClass(name, note, years,branchName, pond));
        displayNote(name, note, pond);

        //Algorithme de calcul de la moyenne
        calculmoy = calculmoy * totalPond;
        totalPond = totalPond + Integer.parseInt(pond);
        calculmoy = calculmoy + Double.parseDouble(pond) * Double.parseDouble(note);
        //Algorithme de calcul de la moyenne
        if(calculmoy != 0) {
            calculmoy = calculmoy / totalPond;
        }
    //String contenant la moyenne
    String stringMoy = String.valueOf(calculmoy);
    //Si la moyenne est supérieur ou égale à 4
        if(calculmoy >= 4)
    {
        //Texte en vert
        moyenne.setTextColor(Color.parseColor("#4caf50"));
    }

    //Si la moyenne est plus petite que 4
        if(calculmoy < 4)
    {
        //Texte en rouge
        moyenne.setTextColor(Color.parseColor("#f44336"));
    }
    //Set le texte d'un textview avec la moyenne
        moyenne.setText(stringMoy);

    }
}
