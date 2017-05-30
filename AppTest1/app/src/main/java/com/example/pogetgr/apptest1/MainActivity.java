package com.example.pogetgr.apptest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView textView2;
    private Button buttonCount;
    private int nbButton = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCount = (Button) findViewById(R.id.buttonCount);
        textView2 = (TextView) findViewById(R.id.textView2);
        buttonCount.setOnClickListener( buttonCountListener);

    }

    private View.OnClickListener buttonCountListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            nbButton++;
        String count =  "Vous avez appuyé "+nbButton+" fois";
            textView2.setText(count);
        }
    };
}
