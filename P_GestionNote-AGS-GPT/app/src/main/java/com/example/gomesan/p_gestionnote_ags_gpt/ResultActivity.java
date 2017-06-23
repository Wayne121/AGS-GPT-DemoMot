package com.example.gomesan.p_gestionnote_ags_gpt;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Créé par : André Gomes & Grégory Poget
 * Dans la période du 29 mai au 26 juin 2017
 * Description : Activité affichant les différentes moyennes des années passées à l'ETML
 * ETML
 */

public class ResultActivity extends ParameterActivity{

    //Variables pour les calculs et la base de données
    private DatabaseHelper db = new DatabaseHelper(this);
    private double yearMoy;
    private double branchMoy;
    private int totalPond;
    private boolean isPond;
    private int nbNote;
    private int nbMoy;
    private int year =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Tableau de TextView
        TextView []tv=new TextView[4];
        tv[0]=(TextView)findViewById(R.id.moy1);
        tv[1]=(TextView)findViewById(R.id.moy2);
        tv[2]=(TextView)findViewById(R.id.moy3);
        tv[3]=(TextView)findViewById(R.id.moy4);

        //Pour chaque années
        for (int i=0; i < 4; i++) {
            //Réinitialise les variables pour l'année
            yearMoy =0;
            nbMoy=0;
            //pour chaques branches
            List<BranchClass> BranchClassList = db.getAllBranch(Integer.toString(year));
            for (BranchClass c : BranchClassList) {
                //Réinitialise les variables pour la branche
                totalPond = 0;
                nbNote =0;
                branchMoy =0;
                //Pour chaque notes de la branche
                List<MarkClass> markClassList = db.getAllMark(Integer.toString(year), Integer.toString(c.getIdBranch()));
                for (MarkClass c2 : markClassList) {
                    //si la note n'a pas de pourcentage
                    if (c2.getMarPourcent() != null) {
                        //Fait le calcul de pondération
                        totalPond = totalPond + Integer.parseInt(c2.getMarPourcent());
                        branchMoy = branchMoy + Double.parseDouble(c2.getMarPourcent()) * Double.parseDouble(c2.getMarNote());
                        //Dit que la branche est pondérée
                        isPond = true;
                    }
                    //sinon
                    else {
                        //Fait le calcul d'une moyenne normale
                        nbNote++;
                        branchMoy = branchMoy + Double.parseDouble(c2.getMarNote());
                        //La branche n'est pas pondérée
                        isPond = false;
                    }
                }

                //Si la branche est pondérée
                if (isPond == true && branchMoy != 0.0) {
                    //Incrémente le nombre de moyenne qu'il y a sur l'année
                    nbMoy++;
                    //ajoute la moyenne de la branche à la moyenne de l'année
                    yearMoy += branchMoy / totalPond;

                    //Si la branche est pas pondérée
                }
                if(isPond == false && branchMoy != 0.0)
                {
                    nbMoy++;
                    //ajoute la moyenne de la branche à la moyenne de l'année
                    yearMoy += branchMoy / nbNote;
                }

            }

            //Divise le total par le nombre de moyenne pour avoir la moyenne de l'année
            yearMoy = yearMoy / nbMoy;
            //Si la moyenne n'est pas null
            if(String.valueOf(yearMoy) != null) {
                //inscrit la moyenne dans le textView correspondant
                tv[i].setText(String.valueOf(yearMoy));
            }
            //Si la moyenne est plus grand ou egale à 4 => texte en vert
            if (yearMoy >= 4) {
                tv[i].setTextColor(Color.parseColor("#4caf50"));
            }
            //Si la moyenne est plus petite que 4 => texte en rouge
            else if (yearMoy < 4) {
                tv[i].setTextColor(Color.parseColor("#f44336"));
            }

            //Ajoute une année
            year++;

        }
    }



    }

