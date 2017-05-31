package com.example.pogetgr.celtofah;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView celTempResult;
    private TextView fahTempResult;
    private EditText celTemp;
    private EditText fahTemp;
    private double fahrenheit;
    private double celsius;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        celTempResult = (TextView) findViewById(R.id.celTempResult);
        fahTempResult = (TextView) findViewById(R.id.fahTempResult);
        celTemp = (EditText) findViewById(R.id.celTemp);

        fahTemp = (EditText) findViewById(R.id.fahTemp);

        fahTemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s)
            {
                fahTemp.setOnKeyListener(new View.OnKeyListener()
                {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event)
                    {
                        if( keyCode == KeyEvent.KEYCODE_ENTER )
                        {
                            fahrenheit = Integer.parseInt(s.toString());
                            String txtFahTemp =  "Il fait : "+fahrenheit+" F°";
                            fahTempResult.setText(txtFahTemp);
                            celsius = (fahrenheit-32)*5/9;
                            String txtCelResult =  "Il fait : "+celsius+" C°";
                            celTempResult.setText(txtCelResult);
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        celTemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {

                celTemp.setOnKeyListener(new View.OnKeyListener()
                {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event)
                    {
                        if( keyCode == KeyEvent.KEYCODE_ENTER )
                        {
                            celsius = Integer.parseInt(s.toString());
                            String txtCelResult =  "Il fait : "+celsius+" C°";
                            celTempResult.setText(txtCelResult);
                            fahrenheit = celsius * 9/5 +32;
                            String txtFahResult =  "Il fait : "+fahrenheit+" F°";
                            fahTempResult.setText(txtFahResult);
                            return true;
                        }
                        return false;
                    }
                });

            }


        });
    }
}
