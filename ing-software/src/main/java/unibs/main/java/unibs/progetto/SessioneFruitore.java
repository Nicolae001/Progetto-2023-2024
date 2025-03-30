package unibs.progetto;
import static unibs.util.Aiuto.print;
public class SessioneFruitore extends Sessione{

    SessioneFruitore(Utente arg){
        super();
        setUtente(arg);
        setSucc(null);
    }

    public void avvia(){
        print("Sezione non ancora disponibile");
        while(!finita()){
        //attività del fruitore che deve terminare
        //con l'invocazione di setFinita() per uscire
        //dal loop
        setFinita();
        }
    }

}