package com.example.gomesan.p_gestionnote_ags_gpt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class NoteActivity extends AppCompatActivity {


    private ViewGroup layout;
    private TextView txtOne;
    private TextView txtTwo;
    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        layout = (ViewGroup) findViewById(R.id.content);

        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");
        String branchName = extras.getString("branchName");

        Log.i("test", years);
        Log.i("test", branchName);

        List<MarkClass> markClassList = db.getAllMark(years,branchName);

        for (MarkClass c : markClassList) {
            displayNote(c.getMarName(), c.getMarNote());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.menu_note:
                onCreateDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("InlinedApi")
    private void displayNote(String name, String mark) {

        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.layout_shownote;


        LinearLayout relativeLayout = (LinearLayout) inflater.inflate(id, null, false);

        txtOne = (TextView) relativeLayout.findViewById(R.id.txtViewOne);
        txtTwo = (TextView) relativeLayout.findViewById(R.id.txtViewTwo);

        txtOne.setText(name);
        txtTwo.setText(mark);

        layout.addView(relativeLayout);
    }

    public void onCreateDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(NoteActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.layout_addnote, null);


        Button bSend = (Button) mView.findViewById(R.id.bSend);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText txtName = (EditText) mView.findViewById(R.id.txtName);
                final EditText txtNote = (EditText) mView.findViewById(R.id.txtNote);
                Log.i("test", ("nya " + txtName.getText().toString() ));
                addNoteDB(txtName.getText().toString(), txtNote.getText().toString());
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void addNoteDB(String name, String note){

        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");
        String branchName = extras.getString("branchName");

        db.addMark(new MarkClass(name, note,years,branchName));
        displayNote(name, note);

    }
}
