package unibs.progetto;
import static unibs.util.Aiuto.print;
public class SessioneConf extends Sessione{

    SessioneConf(Utente arg) {
        super();
        setUtente(arg);
        setSucc(null);
    }


    public void avvia(){
        print("Sezione non ancora disponibile");
        while(!finita()){
            //attività del configuratore che si 
            //deve concludere con il setFinita()
            //per uscire dal loop
            setFinita();
        }
    }
    
}