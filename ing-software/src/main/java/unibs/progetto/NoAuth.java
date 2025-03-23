package unibs.progetto;

public class NoAuth extends Sessione{

   private  NoAuth(Utente arg){
        super();
        setUtente(arg);
    }

    NoAuth(){
        this(null);
    }

  
    
    public void avvia(){
        while(!finita()){
                //attività di richiesta e verifica credenziali
                //che deve completarsi con la creazione di una nuova
                //istanza di SessioneConf o SessioneFruitore e 
                //successiva assegnazione di tale oggetto a succ
                //tramite setSucc() e invocazione setFinita() per
                //uscire dal loop
        }
    }

}

