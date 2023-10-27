package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Verification extends AppCompatActivity {

    /* Zone d'affichage */
    private TextView afficheurRole;
    private Profil profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        initAfficheur(); // Initialisation de l'afficheur TextView

        // Récupération du profil de l'utilisateur depuis l'intent
        profil = (Profil) getIntent().getSerializableExtra("profil");

        // Affichage des informations du profil dans l'afficheur TextView
        ecrire(afficheurRole, profil);
    }

    // Méthode pour afficher les informations du profil dans le TextView
    private void ecrire(TextView afficheur, Profil profil) {
        afficheur.setText(profil.toString());
    }

    // Méthode pour initialiser le TextView
    private void initAfficheur() {
        afficheurRole = (TextView) findViewById(R.id.afficheurRole);
    }
}