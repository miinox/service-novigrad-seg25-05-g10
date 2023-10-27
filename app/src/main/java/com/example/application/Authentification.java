package com.example.application;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Authentification {
    private static String admin = "admin";
    private static String adminMP = "123456";

    Profil profil;

    /**
     * Vérification des informations de connexion
     * @param user
     * @param pass
     * @return
     */
    public static Profil verificationInfoLogin(Context context, DBHelper DB, String user, String pass) {

        if (user.equals(admin) && pass.equals(adminMP)) { // c'est l'administrateur qui se connecte
            return new Profil(user, "admin@uottawa.ca", pass, 2);
        }
        else {
            Boolean checkuserpass = DB.checkusernamepassword(user, pass);
            if (checkuserpass == true) {
                Toast.makeText(context, "Sign in successfull", Toast.LENGTH_SHORT).show();
                return DB.getProfil(user);
            } else {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }
}
