package com.example.gomesan.p_gestionnote_ags_gpt;

/**
 * Created by gomesan on 07.06.2017.
 */

public class MarkClass {

    int idMark;
    String marName;
    double marNote;
    int marYear;
    int idBranch;

    public MarkClass(){

    }

    public MarkClass(String marName, double marNote, int marYear, int idBranch){
        this.marName = marName;
        this.marNote = marNote;
        this.marYear = marYear;
        this.idBranch = idBranch;
    }

    public MarkClass(int idMark, String marName, double marNote, int marYear, int idBranch){
        this.idMark = idMark;
        this.marName = marName;
        this.marNote = marNote;
        this.marYear = marYear;
        this.idBranch = idBranch;
    }

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

    public double getMarNote() {
        return marNote;
    }

    public void setMarNote(double marNote) {
        this.marNote = marNote;
    }

    public int getMarYear() {
        return marYear;
    }

    public void setMarYear(int marYear) {
        this.marYear = marYear;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }
}
