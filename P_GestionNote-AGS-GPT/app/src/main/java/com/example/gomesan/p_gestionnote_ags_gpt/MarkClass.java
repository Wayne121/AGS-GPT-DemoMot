package com.example.gomesan.p_gestionnote_ags_gpt;

/**
 * Created by gomesan on 07.06.2017.
 */

public class MarkClass {

    int idMark;
    String marName;
    String marNote;
    String marYear;
    String idBranch;

    public MarkClass() {

    }

    public MarkClass(String marName, String marNote, String marYear, String idBranch) {
        this.marName = marName;
        this.marNote = marNote;
        this.marYear = marYear;
        this.idBranch = idBranch;
    }

    public MarkClass(int idMark, String marName, String marNote, String marYear, String idBranch) {
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
