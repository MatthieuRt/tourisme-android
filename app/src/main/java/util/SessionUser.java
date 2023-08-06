package util;

import android.content.Context;
import android.content.SharedPreferences;

public final class SessionUser {
    private String idUser;
    private String username;
    private String erroMessage = null;
    private static SessionUser sessionUser= null;
    private SessionUser(){}

    public static final SessionUser getSessionUser (){
        if(SessionUser.sessionUser==null)
            SessionUser.sessionUser = new SessionUser();
        return SessionUser.sessionUser;
    }
    public void setIdUser(String id){
        idUser = id;
    }
    public String getIdUser(){
        return  idUser;
    }
    public void setUsername(String id){
        username = id;
    }
    public String getUsername(){
        return  idUser;
    }
    public String getErroMessage(){
        return  erroMessage;
    }
    public void setErroMessage(String err){
        erroMessage = err;
    }

    public static void saveSession(Context context, String identifiant) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("identifiant", identifiant);
        editor.apply();
    }
}
