package com.example.gomesan.p_gestionnote_ags_gpt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
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
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.List;

public class BranchActivity extends AppCompatActivity {

    private ViewGroup layout;
    private Button bBranch;
    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        layout = (ViewGroup) findViewById(R.id.content);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.content);
        gridLayout.setColumnCount(3);

        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");


        List<BranchClass> branchClasses = db.getAllBranch(years);
        for (BranchClass c : branchClasses) {
            displayBranch(c.getBraText());
        }

        final Button button = (Button) layout.findViewById(R.id.bBranch);
        button.setOnClickListener(goToNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.branch_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ) {
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.menu_branch:
               onCreateDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View.OnClickListener goToNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), NoteActivity.class);
            i.putExtra("year", "1");
            startActivity(i);
        }
    };

    public void onCreateDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(BranchActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.layout_addbutton, null);


        Button bSend = (Button) mView.findViewById(R.id.bSend);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText txtName = (EditText) mView.findViewById(R.id.txtName);
                Log.i("test", ("nya " + txtName.getText().toString() ));
                addButtonBD(txtName.getText().toString());
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void addButtonBD(String name){

        Bundle extras = getIntent().getExtras();
        String years = extras.getString("year");

        db.addBranch(new BranchClass(name, years));
        displayBranch(name);

    }

    @SuppressLint("InlinedApi")
    private void displayBranch(String name) {

        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.layout_showbutton;


        LinearLayout relativeLayout = (LinearLayout) inflater.inflate(id, null, false);
        bBranch = (Button) relativeLayout.findViewById(R.id.bBranch);

        bBranch.setText(name);

        layout.addView(relativeLayout);
    }


}
