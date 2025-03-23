package unibs.progetto;
import java.util.List;

public class Gerarchia{
    private Categoria radice;
    private List<Categoria> foglie;
    //Costruttore non accessibile dall'esterno, per poter effettuare
    //controlli all'interno del metodo che invoca tale costruttore
    private Gerarchia(Categoria arg){
        radice=arg;
    }
    static Gerarchia costruisci(Categoria arg){
    // da definire i controlli prima di costruire la gerarchia
        Gerarchia res=new Gerarchia(arg);
        res.getFoglie();
        return res;
    }

    private void getFoglie(){
        //da implementare
    }
}