package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends ParameterActivity {
    private Button bLogin;
    private EditText txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        bLogin = (Button)findViewById(R.id.bConnexion);
        txtLogin = (EditText) findViewById(R.id.txtLogin);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtLogin.getText().toString().equals(getPassword())) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong password",Toast.LENGTH_SHORT).show();

                    txtLogin.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
