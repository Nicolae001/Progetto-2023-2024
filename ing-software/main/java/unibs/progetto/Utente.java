package unibs.progetto;

import java.io.Serializable;

 abstract class Utente implements Serializable{
    private String username;
    private String pass;
    Acquisitore acq;
    
    public String getUsername(){
        return username;
    }
    
    public String getPswd(){
        return pass;
    }
    
    void setPswd(String arg){
        pass=arg;
    }

    void setUsername(String arg){
        username=arg;
    }
    
}