package unibs.util;

public class Aiuto{
     public static <T> void print(T arg) {
        System.out.println(arg.toString());
    }

    public static <T> void print(T a[]){
        for (int i=0;i<a.length;i++)
            print(a[i]);
    }
}