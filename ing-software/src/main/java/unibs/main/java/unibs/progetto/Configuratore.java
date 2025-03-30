package unibs.progetto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static unibs.util.Aiuto.print;
import unibs.util.InvalidArgumentException;


public class Configuratore extends Utente{

    public Configuratore(String n, String p){
        super();
        setUsername(n);
        setPswd(p);

    }

    void setAcq(Acquisitore arg){
        acq=arg;
    }

    Comprensorio nuovoCompr(String nome, String ... args){
        Set<String> comuni= new TreeSet<>();
        for(String s:args)
            comuni.add(s);
        
        return new Comprensorio(nome,comuni);
    }

    Categoria nuovaCat(Campo cmp, int a){
        print("Inserire nome categoria\n");
        Categoria res=Categoria.nuova(acq.acquisisci(a));
        res.setCampo(cmp);
        return res;
    }

    Campo nuovoCampo(Map<Elemento, Categoria> dom, int a){
        print("Inserire nome campo\n");
        Campo res= new Campo(acq.acquisisci(a));
        res.setDom(dom);
        return res;
    }

    private Elemento nuovoElem(int a){
        print("Inserire nome elemento\n");
        Elemento res=Elemento.nuovo(acq.acquisisci(a));
        print("Inserire eventuale descrizione\n");
        res.setDescr(acq.acquisisci(a));
        return res;
    }

    Map<Elemento, Categoria> nuovoDom(List<Elemento>elem){
        Map<Elemento, Categoria> res=new TreeMap();
        for(Elemento e:elem)
            res.put(e,null);
        return res;
    }

    List<Elemento> accumulaElem(int a){
        print("Procedere con l'aggiunta degli elementi nel dominio del campo\n");
        List<Elemento> res=new ArrayList();
            Elemento e;
        do{
            e=nuovoElem(a);
            if(e!=null)
                res.add(e);
        }while(e!=null);
        return res;
    }

    Gerarchia creaGer(Categoria cat, Gerarchia ger, int a){
      while(cat!=null){
        if(ger==null){
            ger=Gerarchia.costruisci(cat);
        }else {
            print("Inserire valore a cui associare la nuova categoria\n"+
            "e successivamete il nome della categoria genitore\n");
            try {
                ger.aggiungiRamo(acq.acquisisci(a), getCategoria(ger, acq.acquisisci(a)), cat);
            } catch (InvalidArgumentException ex) {
            }
        }
        creaGer(creaCategoria(a), ger, a);
      }
      return ger;
    }    

    private Categoria getCategoria(Gerarchia g, String s) throws InvalidArgumentException{
        if(g.getCategoria(s)!=null)
            return g.getCategoria(s);
        throw new InvalidArgumentException();
    }

    private Categoria creaCategoria(int a){
        print("Procedere con la creazione della nuova categoria");
        Categoria res=nuovaCat(nuovoCampo(nuovoDom(accumulaElem(a)),a),a);
        return res;
    }
    
}