package unibs.progetto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static unibs.util.Aiuto.print;
import unibs.util.InvalidArgumentException;


public class Configuratore extends Utente{
    private static final int def=0;
    public Configuratore(String n, String p){
        super();
        setUsername(n);
        setPswd(p);

    }

    void setAcq(Acquisitore arg){
        acq=arg;
    }

    void insFattori(Categoria c1, Categoria c2, Double val){
        Fattori f= Fattori.fattori();
        f.aggFattore(c1, c2, val);
    }
    Comprensorio nuovoCompr(String nome, String ... args){
        Set<String> comuni= new TreeSet<>();
        for(String s:args)
            comuni.add(s);
        return new Comprensorio(nome,comuni);
    }

    Categoria nuovaCat(String arg, Campo cmp){
        Categoria res=Categoria.nuova(arg);
        if(res!=null)
            res.setCampo(cmp);
        return res;
    }

    Campo nuovoCampo(String arg, Map<Elemento, Categoria> dom){
        Campo res= new Campo(arg);
        res.setDom(dom);
        return res;
    }

    private Elemento nuovoElem(){
        print("Inserire nome elemento\n");
        Elemento res=Elemento.nuovo(acq.acquisisci(def));
        if(res!=null){
            print("Inserire eventuale descrizione\n");
            String s=acq.acquisisci(def);
            res.setDescr(s);
        }
        return res;
    }

    Map<Elemento, Categoria> nuovoDom(List<Elemento>elem){
        Map<Elemento, Categoria> res=new LinkedHashMap<>();
        for(Elemento e:elem)
            if(e!=null)
                res.put(e,null);
        return res;
    }

    List<Elemento> accumulaElem(){
        print("Procedere con l'aggiunta degli elementi nel dominio del campo\n");
        List<Elemento> res=new ArrayList();
        Elemento e;
        do{
            e=nuovoElem();
            if(e!=null)
                res.add(e);
        }while(e!=null);
        return res;
    }

    Gerarchia creaGer(Categoria cat, Gerarchia ger){
      if(cat==null) return ger;
        if(ger==null){
            ger=Gerarchia.costruisci(cat);
        }else {
                print("Inserire valore del dominio a cui associare la nuova categoriae\n");
                print(" e successivamete il nome della categoria genitore\n");
                try {
                ger.aggiungiRamo(acq.acquisisci(def), ger.getCategoria(acq.acquisisci(def)), cat);
                } catch (InvalidArgumentException ex) {}
        }
        return creaGer(creaCategoria(), ger);
}    

    private Categoria getCategoria(Gerarchia g, String s) throws InvalidArgumentException{
        if(g.getCategoria(s)!=null)
            return g.getCategoria(s);
        throw new InvalidArgumentException();
    }

        Categoria creaCategoria(){
        print("Procedere con la creazione della nuova categoria\n");
        print("Per concludere il processo di creazione lasciare tutti i campi vuoti\n");
        print("Inserire nome della categoria\n");
        String ncat=acq.acquisisci(0);
        print("Inserisci nome dell\'eventuale campo\n");
        String ncmp=acq.acquisisci(0);
        if(ncmp.equals("")){
            Campo cmp=new Campo(ncmp);
            return nuovaCat(ncat, cmp);
        }
        print("Procedere ora alla creazione del dominio del campo, inserendo gli evetuali valori\n");
        Map<Elemento, Categoria> map=nuovoDom(accumulaElem());
        return nuovaCat(ncat, nuovoCampo(ncmp, map));
    }
    
}