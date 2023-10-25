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
        DB= new DBHelper(this);
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtNomUtilisateur.getText().toString();
                String pass = txtMp.getText().toString();
                String repass = txtConfirmeMp.getText().toString();
                if (user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(Register.this, "completer tout le formulaire", Toast.LENGTH_SHORT).show();
                }else{
                    if (pass.equals(repass)){
                        boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            boolean emp = chkEmploye.isChecked();
                            Boolean insert = DB.insertData(user,pass,emp);
                            if (insert == true){
                                Toast.makeText(Register.this, "Vous etes enregistre",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
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