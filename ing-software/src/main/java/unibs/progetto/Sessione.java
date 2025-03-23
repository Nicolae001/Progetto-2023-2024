package unibs.progetto;

abstract class Sessione{
    private Utente usr;
    private Sessione succ;
    private boolean fine=false;
    
    Utente getUtente(){
        return usr;
    }
    
    void setUtente(Utente arg){
        usr=arg;
    }

    Sessione getSucc(){
        return succ;
    }

    void setSucc(Sessione arg){
        succ=arg;
    }
    
    public Sessione next(){
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