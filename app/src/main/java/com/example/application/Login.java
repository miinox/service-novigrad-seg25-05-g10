package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class Login extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtMp;
    private Button btnSeConnecter;
    private TextView txtMpPerdu;
    private TextView txtCompteActif;
    private ImageView imgRetour;
    DBHelper DB;

    Profil profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DB = new DBHelper(this); // Initialisation de la base de données
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initEntrees(); // Initialisation des entrées sur la page

        // Gestion du clic sur l'icône de retour
        imgRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPage(MainActivity.class);
            }
        });

        // Gestion du clic sur le bouton de connexion
        btnSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String utilisateur = txtUser.getText().toString();
                String pass = txtMp.getText().toString();
                if(utilisateur.equals("") || pass.equals("")) {
                    // L'utilisateur n'a rien saisi dans l'une des cases
                    Toast.makeText(Login.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Les champs ne sont pas vides, on vérifie les informations de connexion
                    setProfil(Authentification.verificationInfoLogin(Login.this, DB, utilisateur, pass));
                    if (profil != null) {
                        // L'utilisateur s'est connecté avec succès
                        Intent intent = new Intent(Login.this, Verification.class);
                        intent.putExtra("profil", profil); // "profil" est la clé pour récupérer le profil
                        startActivity(intent);
                    }
                }
            }
        });
    }

    /**
     * Initialisation des entrées sur la page
     */
    private void initEntrees() {
        this.btnSeConnecter = (Button) findViewById(R.id.BtnSeConnecter);
        this.txtUser = (EditText) findViewById(R.id.txtUser);
        this.txtMp = (EditText) findViewById(R.id.txtMp);
        this.txtMpPerdu = (TextView) findViewById(R.id.txtMpPerdu);
        this.txtCompteActif = (TextView) findViewById(R.id.TxtCompteActif);
        this.imgRetour = (ImageView) findViewById(R.id.ImgRetour);
    }

    /**
     * Naviguer vers une autre page.
     * @param page
     */
    private void goToPage(Class page) {
        Intent intent = new Intent(this, page);
        startActivities(new Intent[]{intent});
    }

    /**
     * Envoyer le profil en serialisation pour la page suivante
     * @param profil
     */
    private void setProfil(Profil profil) {
        this.profil = profil;
    }
}
