package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtMp;
    private Button btnSeConnecter;
    private TextView txtMpPerdu;
    private TextView txtCompteActif;
    private ImageView imgRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initEntrees(); // initialisation des entrées

        // appuyer sur le retour
        imgRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToPage(MainActivity.class);
            }
        });
    }

    /**
     * Initialisation des entrées sur la page
     */
    private void initEntrees() {
        this.btnSeConnecter= (Button)findViewById(R.id.BtnSeConnecter);
        this.txtEmail = (EditText)findViewById(R.id.TxtEmail);
        this.txtMpPerdu = (TextView)findViewById(R.id.TxtMp);
        this.txtCompteActif = (TextView)findViewById(R.id.TxtCompteActif);
        this.imgRetour = (ImageView)findViewById(R.id.ImgRetour);
    }

    /**
     * Navition vers une page.
     * @param page
     */
    private void goToPage(Class page) {
        Intent intent = new Intent(this, page);
        startActivities(new Intent[]{intent});
    }
}