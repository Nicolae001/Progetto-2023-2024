package unibs.progetto;

abstract class Sessione implements Acquisitore{
    private Utente usr;
    private Sessione succ=null;
    private boolean fine=false;
    
    Utente getUtente(){
        return usr;
    }
    
    void setUtente(Utente arg){
        usr=arg;
    }

    void setSucc(Sessione arg){
        succ=arg;
    }
    
    public Sessione getSucc(){
        return succ;
    }

    public  boolean finita(){
        return fine;
    }

    void setFinita(){
        fine=true;
    }

    public abstract void avvia();
  
}