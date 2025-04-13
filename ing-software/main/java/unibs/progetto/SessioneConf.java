package unibs.progetto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static unibs.util.Aiuto.maiuscola;
import static unibs.util.Aiuto.print;
import static unibs.util.Aiuto.readDec;
import static unibs.util.Aiuto.readInt;
import static unibs.util.Aiuto.readLine;
public class SessioneConf extends Sessione{

    private List<Comprensorio>comp=new ArrayList<>();
    private List<Gerarchia> ger=new ArrayList<>();
    private Fattori fatt=Fattori.fattori();
    private SessioneConf(Utente arg) {
        super();
        setUtente(arg);
        setSucc(null);
    }

    public static SessioneConf nuova(Utente arg){
        SessioneConf res=new SessioneConf(arg);
        Configuratore cfg=(Configuratore)arg;
        cfg.setAcq(res);
        res.setUtente(cfg);
        return res;
    }


    @Override
    public void avvia(){
        print("\t\tBenvenuto "+getUtente().getUsername()+"\n");
        String com;
        while(!finita()){  
            com=acquisisci(1);
            agisci(com);
        }
    }
    
    @Override
    public String acquisisci(int i){
        return switch(i){
            case 1->azioni();
            default-> readLine().toLowerCase();
        };
    }



    private String azioni(){
        print("\t\tScegli cosa vorresti fare\n\n");
        print("1. Inserisci nuovo comprensorio geografico\n");
        print("2. Crea nuova gerarchia\n");
        print("3. Aggiungi fattori di conversione\n");
        print("4. Visualizza i comprensori disponibili\n");
        print("5. Visualizza gerarchie presenti\n");
        print("6. Visualizza fattori di conversione disponibili per una certa categoria\n");
        print("7. Salva le modifiche\n");
        print("Per esprimere la scelta digitare la cifra identificativa della scelta\n");
        print("oppure \'fine\' per uscire: ");
        return readLine();
    }

    private void agisci(String arg){
        switch(arg.toLowerCase()){
            case "fine"->setFinita();
            case "1"->nuovoComp();
            case "2"->nuovaGer();
            case "3"->aggiungiFattori();
            case "4"->visComp();
            case "5"->visGer();
            case "6"->visFatt();
            case "7"->salva();
            default-> print("\nOpzione non disponibile\n");
        }
    }

    private void nuovoComp(){
        print("\nInserire il nome del comprensorio: ");
        String nome=acquisisci(0);
        print("\nInserire la lista dei nomi dei comuni che fanno parte\n");
        print("di tale comprensorio separati da uno spazio\n");
        String tmp=acquisisci(0);
        String[] comuni=tmp.split(" ");
        comp.add(((Configuratore)getUtente()).nuovoCompr(nome, comuni));
    }

    private void nuovaGer(){
       Configuratore cfg=(Configuratore)getUtente();
       print("\nProcedere alla creazione della radice\n");
       Categoria cat=cfg.creaCategoria();
       ger.add(cfg.creaGer(cat, null));
    }

    private void aggiungiFattori(){
        Configuratore cfg=(Configuratore)getUtente();
        String opt="";
        print("\nDigitare i nomi della coppia di categorie per cui si vuole inserire\n");
        print("il fattore di conversione oppure digitare \'finito\' per concludere.\n");
        print("I nomi delle categorie devono essere separate da uno spazio\n");
        while(!opt.equals("finito")){
            print("\nProcedere con l'inserimento: ");
            opt=acquisisci(0);
            if(opt.equals("finito")) break;
            Categoria[] cat=estrai(opt);
            if(cat!=null){
                print("\nInserire il valore del fattore di conversione tra tale coppia: ");
                double val=readDec();
                cfg.insFattori(cat[0], cat[1], val);
            }

            print("Se si vuole concludere l\'inserimento digitare \'finito\'\n");
            print("oppure inserire un\'altra coppia\n");
        }
       
    }

    private Categoria[] estrai(String arg){
        int i=0;
        String[] nomi=arg.split(" ");
        Categoria[] res=new Categoria[2];
        for(String nome:nomi){
            for(Gerarchia g:ger){
                Categoria c=g.getCategoria(nome);
                if(c!=null)
                    res[i++]=c;
            }
             if(i>1) break;
        }
        if(res[0]==null || res[1]==null){
            print("\nImpossibile trovare una delle categorie, pertanto\n");
            print("non sara\' possibile procedere con l\'inserimento\n");
            return null;
        }
        return res;
    }

    private void visComp(){
        if(comp.isEmpty()){
            print("\nAl momento non ci sono comprensori da visualizzare\n");
            return;
        }
        for(Comprensorio c:comp){
            print(c.toString()+"\n");
        }
            
    }

    private void visGer(){
        if(ger.isEmpty()){
            print("\nNon sono presenti gerarchie al momento\n");
            return;
        }
        print("\nLe gerarchie disponibili sono le seguenti\n");
        print("Per uscire digitare \'fine\'\n");
        for(Gerarchia g: ger)
            print(g.getRadice().getNome()+" ");
        String scelta;
        do{
        print("\n\nInserire nome radice della gerarchia che si vuole visualizzare\n");
        scelta=acquisisci(0);
        Gerarchia ge=null;
        for(Gerarchia g:ger)
            if(g.getRadice().getNome().equals(scelta)) {
                ge=g;
                break;
            }
        if(ge==null) print("\nGerarchia con questa radice non trovata\n");
        else {
            visRic(ge);
            return;
        }
        }while(!scelta.equals("fine"));
    }

    private void visRic(Gerarchia arg){
        Categoria c=arg.getRadice();
        visualizza(c);
        int scelta=readInt()-1;
        while(scelta!=c.getCampo().getDom().size()+1){
            while(c.getCampo().getDom()!=null && scelta!=c.getCampo().getDom().size()+1){
                if(scelta<0||scelta>c.getCampo().getDom().size()+1)
                    print("\nScelta non valida. Inserisci la tua scelta: ");           
                if(scelta==c.getCampo().getDom().size()){
                    if(c.equals(arg.getRadice())){
                        visGer();
                        return;
                    }else{
                        c=c.getGenitore();
                        visualizza(c);
                    }
                }

                if(scelta>=0 && scelta<c.getCampo().getDom().size()){
                    Map<Elemento, Categoria> dom=c.getCampo().getDom();
                    Elemento e=dom.keySet().toArray(new Elemento[0])[scelta];
                    c=dom.get(e);
                    visualizza(c);
                }

                scelta=readInt()-1;
            }
            if(c.getCampo().getDom()==null){
                switch (scelta){
                    case 0 -> {
                        c=c.getGenitore();
                        visualizza(c);
                        scelta=readInt()-1;
                    }
                    case 1 -> {
                        return;
                    }
                    default -> print("\nScelta non valida. Inserisci la tua scelta: ");
                }
            }
        }
  
    }   

    private void visualizza(Categoria arg){
        int i=0;
        print(arg.getNome());
        print("-->");
        print(arg.getCampo().getNome());
        print(":\n\n");
        Map<Elemento,Categoria> dom=arg.getCampo().getDom();
        if(dom!=null){
            for(Elemento e:dom.keySet()){
                print(++i);
                print(". ");
                print(e.getNome());
                print("\tDescrizione: ");
                print(e.getDescr());
                print("\n");
            }
        }
        print(i+1);
        print(". Indietro\n");
        print(i+2);
        print(". Esci\n");
        print("\nPer esprimere la propria scelta, digitare\n");
        print("il numero identificativo di tale scelta\n");
    }

    private void visFatt(){
        print("\nInserire il nome della categoria per cui si desidera\n");
        print("visulaizzare la lista dei fattori di conversione: ");
        String s=acquisisci(0);
        Categoria cat=null;
        for(Gerarchia g:ger){
            cat=g.getCategoria(s);
            if(cat!=null) break;
        }
        if(cat!=null){
            Map<Categoria, Double> lista=fatt.getFattori(cat);
            print("\nI valori di conversione per "+ maiuscola(cat.getNome())+" sono i seguenti:\n");
            for(Categoria c:lista.keySet()){
                print(maiuscola(c.getNome()));
                print(": "+ lista.get(c));
                print(" ");
            }
        }else
            print("Non e\' stata trovata una categoria con tale nome\n");
        
    }

    private void salva(){
        //da implementare
    }


}