package com.example.gomesan.p_gestionnote_ags_gpt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private ViewGroup layout;
    private TextView txtOne;
    private TextView txtTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        layout = (ViewGroup) findViewById(R.id.content);

        DatabaseHelper db = new DatabaseHelper(this);

        db.addMark(new MarkClass("test",2,1,1));

        //List<MarkClass> markClassList = db.getAllMark();

        //for(MarkClass c : markClassList){
           displayNote();
       //}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ) {
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.menu_note:
                Toast.makeText(this, "Settings menu selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("InlinedApi")
    private void displayNote()
    {

        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.layout_add_note;


        LinearLayout relativeLayout = (LinearLayout) inflater.inflate(id, null, false);

        txtOne = (TextView) relativeLayout.findViewById(R.id.txtViewOne);
        txtTwo = (TextView) relativeLayout.findViewById(R.id.txtViewTwo);

        layout.addView(relativeLayout);
    }
}
