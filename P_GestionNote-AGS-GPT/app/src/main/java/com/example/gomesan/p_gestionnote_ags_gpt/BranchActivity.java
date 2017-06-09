package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BranchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        Button bBranch1 = (Button) findViewById(R.id.bBranch1);
        bBranch1.setOnClickListener(noteLaco);
    }

    private View.OnClickListener noteLaco = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), NoteActivity.class);
            i.putExtra("branchName", "1");
            i.putExtra("year", "1");
            startActivity(i);
        }
    };

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
                Toast.makeText(this, "Settings menu selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
