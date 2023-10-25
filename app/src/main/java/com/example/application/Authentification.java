package com.example.application;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Authentification {
    private static String admin = "admin";
    private static String adminMP = "123456";

    /**
     * Vérification des informations de connexion
     * @param user
     * @param pass
     * @return
     */
    public static boolean verificationInfoLogin(Context context, String user, String pass) {
        DBHelper DB = new DBHelper(context);

            Boolean checkuserpass = DB.checkusernamepassword(user, pass);
            if (user.equals(admin) || pass.equals(adminMP)) {
                return true;

            } else {
                return false;
            }

    }



}
