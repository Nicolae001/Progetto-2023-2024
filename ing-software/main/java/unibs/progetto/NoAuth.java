package unibs.progetto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static unibs.util.Aiuto.print;
import static unibs.util.Aiuto.readFile;
import static unibs.util.Aiuto.readLine;

public class NoAuth extends Sessione{
   private static final String pathConf="C:\\ScambioOre\\Configuratori";
   private static final String pathFru="C:\\ScambioOre\\Fruitori";
   private  NoAuth(Utente arg){
        super();
        setUtente(arg);
    }

    NoAuth(){
        this(null);
    }

  
    
 
    public void avvia(){
        while(!finita()){
            print("Benvenuto nel programma di scambio ore\n");
            String scelta=acquisisci(1);
            String uname=acquisisci(2);
            String pswd=acquisisci(3);
            switch(scelta){
                case"fine" -> setFinita();
                case"1" -> {try{autenticaConf(uname, pswd);}catch(Exception e){}}
                case"2" -> {try{autenticaFru(uname, pswd);}catch(Exception e){}}
                default -> {
                    print("La scelta indicata non è corretta\n");
                    print("Riprovare\n");
                }
            }
        }
    }

   @Override
     public String acquisisci(int i){
       return switch (i) {
            case 1 -> identifica();
            case 2 -> getUname();
            case 3 -> getPswd();
           default -> readLine();
       };
        
    }

    private String identifica(){
        print("Effettuare la scelta del ruolo, digitando la cifra identificativa\n");
        print("1. Configuratore\n");
        print("2. Fruitore\n");
        print("Se si vuole uscire digitare \"fine\"\n");
        print("Inserire scelta: ");
        return readLine().toLowerCase();
    }

    private String getUname(){
        print("\nIndicrae il proprio username: ");
        return readLine().toLowerCase();
    }

    private String getPswd(){
        print("\nIndicare la propria password: ");
        return readLine();
    }

    private void autenticaFru(String uname, String pswd) throws FileNotFoundException, IOException{
        if (autenticato(uname,pswd,pathFru)){
            Utente u=new Fruitore(uname,pswd);
            setSucc(new SessioneFruitore(u));
            setFinita();
        }else{
            print("\nNome utente o password non corretto\n");
            print("Accesso negato\n");
        }

    }

    private void autenticaConf(String uname, String pswd) throws FileNotFoundException, IOException{
     if (autenticato(uname,pswd,pathConf)){
            Utente u=new Configuratore(uname,pswd);
            setSucc(new SessioneConf(u));
            setFinita();
        }else{
            print("\nNome utente o password non corretto\n");
            print("Accesso negato\n");
        }

    }

    private boolean autenticato(String uname, String pswd, String path) throws FileNotFoundException, IOException{
        File dir=new File(path);
        for (File f:dir.listFiles()) {
            if(f.getName().equals(uname+".txt")){
                if(readFile(new FileInputStream(f)).equals(pswd))
                    return true;
            }
        }
        return false;
    }

}

