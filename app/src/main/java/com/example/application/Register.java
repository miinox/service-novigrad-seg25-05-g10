package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    private EditText txtNomUtilisateur;
    private EditText txtEmail;
    private EditText txtMp;
    private EditText txtConfirmeMp;
    private EditText txtNumeroEmploye;
    private ImageView imgRetour;
    //private CheckBox chkEmploye;
    private Button btnInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initEntrees();

        // appuyer sur le retour
        imgRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToPage(MainActivity.class);
            }
        });
    }

    private void initEntrees() {
        this.btnInscription= (Button)findViewById(R.id.BtnInscription);
        this.txtEmail = (EditText)findViewById(R.id.TxtEmail);
        this.txtMp = (EditText)findViewById(R.id.TxtMp);
        this.txtConfirmeMp = (EditText)findViewById(R.id.TxtConfirmerMp);
        this.txtNomUtilisateur = (EditText)findViewById(R.id.TxtNomUtilisateur);
        this.imgRetour = (ImageView)findViewById(R.id.imgRetour);
        //this.chkEmploye = (CheckBox)findViewById(R.id.ChkEmploye);
        //this.txtNumeroEmploye = (EditText)findViewById(R.id.TxtNumeroEmploye);
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