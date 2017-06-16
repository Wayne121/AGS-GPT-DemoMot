package com.example.gomesan.p_gestionnote_ags_gpt;

/**
 * Author: Gomesan
 * Lieu: ETML
 * Description: Classe d'objet pour les notes
 */

public class MarkClass {

    //Déclaration des variables
    int idMark;
    String marName;
    String marNote;
    String marYear;
    String idBranch;

    //Constructeur sans paramètre
    public MarkClass() {

    }

    //Constructeur avec quatre paramètres
    public MarkClass(String marName, String marNote, String marYear, String idBranch) {
        this.marName = marName;
        this.marNote = marNote;
        this.marYear = marYear;
        this.idBranch = idBranch;
    }

    //Constructeur avec tous les paramètres
    public MarkClass(int idMark, String marName, String marNote, String marYear, String idBranch) {
        this.idMark = idMark;
        this.marName = marName;
        this.marNote = marNote;
        this.marYear = marYear;
        this.idBranch = idBranch;
    }

    //Getter et Setter de toutes les variables
    public int getIdMark() {
        return idMark;
    }

    public void setIdMark(int idMark) {
        this.idMark = idMark;
    }

    public String getMarName() {
        return marName;
    }

    public void setMarName(String marName) {
        this.marName = marName;
    }

    public String getMarNote() {
        return marNote;
    }

    public void setMarNote(String marNote) {
        this.marNote = marNote;
    }

    public String getMarYear() {
        return marYear;
    }

    public void setMarYear(String marYear) {
        this.marYear = marYear;
    }

    public String getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(String idBranch) {
        this.idBranch = idBranch;
    }

}
