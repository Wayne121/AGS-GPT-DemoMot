package com.example.gomesan.p_gestionnote_ags_gpt;

/**
 * Created by gomesan on 07.06.2017.
 */

public class BranchClass {

    int idBranch;
    String braText;
    String braYear;

    public BranchClass(){

    }

    public BranchClass(String braText, String braYear){
        this.braText = braText;
        this.braYear = braYear;
    }

    public BranchClass(int idBranch, String braText, String braYear){
        this.idBranch = idBranch;
        this.braText = braText;
        this.braYear = braYear;
    }

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
