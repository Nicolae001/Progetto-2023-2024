package unibs.progetto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import unibs.util.Aiuto;
import static unibs.util.Aiuto.print;
import unibs.util.InvalidArgumentException;

public class Gerarchia{
    private Categoria radice;
    private List<Categoria> foglie;
    private static List<Gerarchia> contabile=new ArrayList();
    //Costruttore non accessibile dall'esterno, per poter effettuare
    //controlli all'interno del metodo che invoca tale costruttore
    private Gerarchia(Categoria arg){
        radice=arg;
    }
    static Gerarchia costruisci(Categoria arg){
        Gerarchia res=new Gerarchia(arg);
        for(Gerarchia g: contabile){
            if(res.getRadice().getNome().equals(g.getRadice().getNome())){
                print("Impossibile creare nuova gerarchia\nUna gerarchia con"+
                "questa radice esiste già\n\n");
                return null;
            }
        }
        contabile.add(res);
        res.getFoglie();
        return res;
    }

    private void getFoglie(){
        List<Categoria> res = new ArrayList();
        List<Categoria> tmp= appiattisci(radice);
        for(Categoria c : tmp){
            if(c.getCampo()==null)
                res.add(c);
        }
        foglie=res;
    }
    private boolean conforme(Categoria arg){
       List<Categoria> tmp= appiattisci(radice);
       for(Categoria c : tmp){
        if(arg.getNome().equals(c.getNome()))
            return false;
       }
       return true;
    }

    private List<Categoria> appiattisci(Categoria rad){
        List<Categoria> res= new ArrayList();
        res.add(rad);
        Map<Elemento, Categoria> dom=rad.getCampo().getDom();
        if(dom!=null){
            for(Elemento e:dom.keySet()){
                if(dom.get(e)!=null)
                    appiattisci(dom.get(e));
            }
                
        }
        return res;
    }

    boolean aggiungiRamo(String val, Categoria genitore, Categoria figlio)throws InvalidArgumentException{
        if(conforme(figlio)&& !foglia(genitore)){
         Map<Elemento, Categoria> tmp=genitore.getCampo().getDom();
         for(Elemento e: tmp.keySet()){
            if(e.getNome().equals(val)){
                tmp.put(e, figlio);
                figlio.setGenitore(genitore);
                }
         }
         genitore.getCampo().setDom(tmp);
         return true;
        }
        if(foglia(genitore))
            throw new InvalidArgumentException(genitore.getNome()+" non ha un campo\n");
        return false;
    }

    

    private boolean sottoGer(Categoria arg){
        List<Categoria> lista=Aiuto.listaValori(arg.getCampo().getDom());
        for(Categoria c : lista){
         if(c!=null)
          return false;
         }

        return true;
    }

    private boolean foglia(Categoria arg){
        if(arg.getCampo()==null)
            return true;
        return false;
    }
    
    void rimuoviRamo(String val, Categoria genitore, Categoria figlio){
        assert(!foglia(genitore));
        if(!sottoGer(figlio)){
        Map<Elemento, Categoria> tmp=genitore.getCampo().getDom();
         for(Elemento e: tmp.keySet()){
            if(e.getNome().equals(val)){
                tmp.remove(e);
                figlio.setGenitore(null);
                }
         } 
         genitore.getCampo().setDom(tmp);
         }else{
             Map<Elemento, Categoria> tmp=figlio.getCampo().getDom();
            for(Elemento e : tmp.keySet()){
                if(tmp.get(e)!=null){
                    rimuoviRamo(e.getNome(), figlio, tmp.get(e));
                }
            }
         }
    }
    
    public Categoria getRadice(){
        return radice;
    }

    public Categoria getCategoria(String arg){
        List<Categoria> tmp=appiattisci(radice);
        for(Categoria c : tmp){
            if(c.getNome().equals(arg))
                return c;
        }
        return null;
    }
}