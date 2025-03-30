package unibs.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Aiuto{

    private static final  Scanner sc = new Scanner(System.in);
     public static <T> void print(T arg) {
        System.out.println(arg.toString());
    }

    public static <T> void print(T a[]){
         for (T a1 : a) {
             print(a1);
         }
    }

    public static <K,T> List<T> listaValori(Map<K,T> arg){
        List<T> res=new ArrayList();
        for(K key:arg.keySet())
            res.add(arg.get(key));
        return res;
    }

    private static int readInt() {
        int res = sc.nextInt();
        return res;
    }

    private static double readDec() {
        double res = sc.nextDouble();
        return res;
    }

    public static String readLine() {
        String res = sc.nextLine();
        return res;
    }

    public static String readFile(FileInputStream arg)throws FileLockInterruptionException, IOException{
        StringBuilder sb=new StringBuilder();
        char c;
        int i;
        while(true){
           i=arg.read();
           if(i==-1) break;
           c=(char)i;
           sb.append(c);
        }
        return sb.toString();
    }
}