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

public class SignUpActivity extends AppCompatActivity {

    Button btn_inscription;
    EditText mail,nom,prenom,password;
    TextView errorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_inscription = findViewById(R.id.btn_inscription);
        //mail = findViewById(R.id.editTextMail);
        //nom = findViewById(R.id.editTextNom);
        //prenom = findViewById(R.id.editTextPrenom);
        //password = findViewById(R.id.editTextPassword);
        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //récupération des données utilisateur
                String mail = SignUpActivity.this.mail.getText().toString();
                String prenom = SignUpActivity.this.prenom.getText().toString();
                String nom = SignUpActivity.this.nom.getText().toString();
                String pass = SignUpActivity.this.password.getText().toString();
                IdentificationService identificationService = new IdentificationService();
                identificationService.inscription(mail, nom, prenom, pass, SignUpActivity.this, new IdentificationService.LogupCallback() {
                    @Override
                    public void onSuccess() {
                        // Redirection vers MenuActivity si l'inscription est réussie
                        Intent intent = new Intent(SignUpActivity.this, Menu.class);
                        startActivity(intent);
                        // Fermer SignUpActivity pour empêcher le retour en arrière
                        finish();
                    }
                    @Override
                    public void onError(String errorMessage) {
                        SignUpActivity.this.errorMessage.setText(errorMessage);
                        Toast.makeText(SignUpActivity.this, "Erreur d'inscription : " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}