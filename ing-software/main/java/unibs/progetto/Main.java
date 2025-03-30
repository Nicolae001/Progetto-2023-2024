package unibs.progetto;
import static unibs.util.Aiuto.print;

public class Main {
    private static Sessione s;
    public static void main(String[] args) throws Exception {
         print("Gestione scambio ore V 0.1");
         Thread.sleep(1000);
        s=new NoAuth();
       do { 
            if(s.getSucc()!=null)
                s=s.getSucc();
            s.avvia();
        }while(s.getSucc()!=null);
    }
}