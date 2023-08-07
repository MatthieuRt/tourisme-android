package com.example.roadtomadagascar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roadtomadagascar.R;
import com.example.roadtomadagascar.service.IdentificationService;

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
        pseudo=(EditText)findViewById(R.id.mail);
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
                String identifiant = LoginActivity.this.pseudo.getText().toString();
                String password = LoginActivity.this.password.getText().toString();
                IdentificationService identificationService = new IdentificationService();
                identificationService.login(identifiant, password, LoginActivity.this, new IdentificationService.LoginCallback() {
                    @Override
                    public void onSuccess() {
                        // Login réussi, vous pouvez maintenant rediriger l'utilisateur vers la page d'accueil par exemple
                        Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Menu.class);
                        startActivity(intent);
                        finish(); // Fermer l'activité de login pour éviter de revenir en arrière
                    }

                    @Override
                    public void onError(String errorMessage) {
                        // Gérer les erreurs de login ici, afficher un message d'erreur par exemple
                        Toast.makeText(LoginActivity.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //Creation
        this.creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
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