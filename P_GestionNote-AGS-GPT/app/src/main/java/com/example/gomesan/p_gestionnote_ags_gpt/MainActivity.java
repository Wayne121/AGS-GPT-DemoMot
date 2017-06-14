package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ParameterActivity {
    //Variable de préférence pour thème de l'utilisateur
    // /!\ à remplacer par le theme dans le base de donnée /!\
private String theme = "redTheme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button bFirstYear = (Button) findViewById(R.id.bFirstYear);
        bFirstYear.setOnClickListener(branchFirstYear);

        Button bSecondYear = (Button) findViewById(R.id.bSecondYear);
        bSecondYear.setOnClickListener(branchSecondYear);

        Button bThirdYear = (Button) findViewById(R.id.bThirdYear);
        bThirdYear.setOnClickListener(branchThirdYear);

        Button bFourthYear = (Button) findViewById(R.id.bFourthYear);
        bFourthYear.setOnClickListener(branchFourthYear);

        Button bResult = (Button) findViewById(R.id.bResult);
        bResult.setOnClickListener(noteResult);

    }

    private View.OnClickListener branchFirstYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "1");
            startActivity(i);
        }
    };

    private View.OnClickListener branchSecondYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "2");
            Log.i("test", "2");
            startActivity(i);
        }
    };

    private View.OnClickListener branchThirdYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "3");
            startActivity(i);
        }
    };

    private View.OnClickListener branchFourthYear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), BranchActivity.class);
            i.putExtra("year", "4");
            startActivity(i);
        }
    };

    private View.OnClickListener noteResult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), NoteActivity.class);
            i.putExtra("nom", "George");
            startActivity(i);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parameter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ) {
            case R.id.menu_option:
                Intent intent = new Intent(this, ParameterActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
