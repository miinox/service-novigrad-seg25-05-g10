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
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText txtNomUtilisateur;
    private EditText txtEmail;
    private EditText txtMp;
    private EditText txtConfirmeMp;

    private ImageView imgRetour;
    private CheckBox chkEmploye;
    private Button btnInscription;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initEntrees(); // initialiser les entrées

        DB= new DBHelper(this); // base de données
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtNomUtilisateur.getText().toString();
                String pass = txtMp.getText().toString();
                String email = txtEmail.getText().toString();
                String repass = txtConfirmeMp.getText().toString();
                if (user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(Register.this, "Erreur", Toast.LENGTH_SHORT).show();
                }else{
                    if (pass.equals(repass)){
                        int role = 0; // client
                        if(chkEmploye.isChecked()) {
                            role = 1; // employé
                        }
                        Profil profil = new Profil(user, email, pass, role); // créer un nouveau profil
                        if (DB.checkusername(profil) == false){
                            if (DB.insertData(profil)){ // On enregiste le profil dans la base de donnée. Retourne 1 si tout est ok
                                Toast.makeText(Register.this, "Vous etes enregistré",Toast.LENGTH_SHORT).show();
                                goToPage(MainActivity.class);
                            }else {
                                Toast.makeText(Register.this, "enregistrement sans succes", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "ce nom d'utilisateur existe deja, connecter vous", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "le mot de pass ne correspond pas au nom d'utilisateur", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // appuyer sur le retour
        imgRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToPage(MainActivity.class);
            }
        });
    }

    /**
     * Inititialisation des entrées sur la page
     */
    private void initEntrees() {
        this.btnInscription= (Button)findViewById(R.id.BtnInscription);
        this.txtEmail = (EditText)findViewById(R.id.TxtEmail);
        this.txtMp = (EditText)findViewById(R.id.TxtMp);
        this.txtConfirmeMp = (EditText)findViewById(R.id.TxtConfirmerMp);
        this.txtNomUtilisateur = (EditText)findViewById(R.id.TxtNomUtilisateur);
        this.imgRetour = (ImageView)findViewById(R.id.imgRetour);
        this.chkEmploye = (CheckBox)findViewById(R.id.ChkEmploye);
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