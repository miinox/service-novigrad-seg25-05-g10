package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnConnexion;
    private Button btnInscription;
    private TextView txtInvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initEntrees();

        // bouton connexion est cliqué
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // aller à la page de connexion
                goToPage(Login.class);
            }
        });

        // bouton inscription est cliqué
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // aller à la page d'inscription
                goToPage(Register.class);
            }
        });

        // continuer en tant qu'invité
        txtInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToPage(Verification.class);
            }
        });
    }

    /**
     * Navition vers une page.
     * @param page
     */
    private void goToPage(Class page) {
        Intent intent = new Intent(this, page);
        startActivities(new Intent[]{intent});
    }

    /**
     * Initiatlisation des entrées sur cette page
     */
    private void initEntrees() {
        this.btnConnexion = (Button)findViewById(R.id.BtnConnexion);
        this.btnInscription = (Button)findViewById(R.id.BtnInscrire);
        this.txtInvite = (TextView)findViewById(R.id.TxtInvite);
    }

}