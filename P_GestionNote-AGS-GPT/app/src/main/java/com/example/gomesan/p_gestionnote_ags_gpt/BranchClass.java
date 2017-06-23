package com.example.gomesan.p_gestionnote_ags_gpt;

/**
 * Created by gomesan on 07.06.2017.
 */

public class BranchClass {

    //Déclaration des variables
    int idBranch;
    String braText;
    String braYear;

    //Constructeur sans paramètre
    public BranchClass(){

    }

    //Constructeur avec deux paramètres
    public BranchClass(String braText, String braYear){
        this.braText = braText;
        this.braYear = braYear;
    }

    //Constructeur avec toutes les variables
    public BranchClass(int idBranch, String braText, String braYear){
        this.idBranch = idBranch;
        this.braText = braText;
        this.braYear = braYear;
    }

    //Getter et Setter de toute les variables
    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public String getBraText() {
        return braText;
    }

    public void setBraText(String braText) {
        this.braText = braText;
    }

    public String getBraYear() {
        return braYear;
    }

    public void setBraYear(String braYear) {
        this.braYear = braYear;
    }

}
