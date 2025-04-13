package unibs.progetto;
import static unibs.util.Aiuto.print;
import static unibs.util.Aiuto.readLine;
public class SessioneFruitore extends Sessione{

    SessioneFruitore(Utente arg){
        super();
        setUtente(arg);
        setSucc(null);
    }

    @Override
    public void avvia(){
        print("Sezione non ancora disponibile");
        while(!finita()){
        //attività del fruitore che deve terminare
        //con l'invocazione di setFinita() per uscire
        //dal loop
        setFinita();
        }
    }

    @Override
    public String acquisisci(int i){
        return switch(i){
            default->readLine().toLowerCase();
        };
    }

}