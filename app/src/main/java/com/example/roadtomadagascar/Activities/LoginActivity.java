package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roadtomadagascar.R;

public class LoginActivity extends AppCompatActivity {

    EditText pseudo,password;
    Button connexion;
    TextView creation,mdp_oublie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onClick();
    }

    private void initView(){

        //init View
        pseudo=(EditText)findViewById(R.id.pseudo);
        password=(EditText)findViewById(R.id.password);
        connexion=(Button)findViewById(R.id.btn_login);
        creation=(TextView)findViewById(R.id.creation_compte);
        mdp_oublie=(TextView)findViewById(R.id.mot_de_passe_oublie);
    }

    private void onClick(){
        //Connexion
        this.connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Login.this, "Creation", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,Menu.class);
                startActivity(intent);
            }
        });

        //Creation
        this.creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
                //Intent intent=new Intent(LoginActivity.this,Inscription.class);
                //startActivity(intent);
            }
        });

        //mot de passe oublié
        this.mdp_oublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}