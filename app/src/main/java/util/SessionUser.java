package util;

public final class SessionUser {
    private String idUser;
    private String username;
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
        idUser = id;
    }
    public String getUsername(){
        return  idUser;
    }
}
