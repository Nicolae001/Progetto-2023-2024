package unibs.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static unibs.util.Aiuto.print;


public class Fattori implements Serializable{
    private static final  Double min=0.5;
    private static final  Double max=2.0;
    private static Fattori f=null;
    private static final List<Map<Categoria, Double>> fatt=new ArrayList();
    private Fattori(){}
    
    static Fattori fattori(){
        if(f==null)
            return new Fattori();
        return f;
    }

    Map<Categoria, Double> getFattori(Categoria cat){
        Map<Categoria, Double> res=new LinkedHashMap();
        double val;
        for(Map<Categoria, Double> m :fatt){
            if(m.containsKey(cat)){
                for(Categoria c:m.keySet()){
                    if(!cat.equals(c)){
                        val=m.get(c)/m.get(cat);
                        res.put(c, val);
                    }
                }
            }
        }
        return res;
    }



    void aggFattore(Categoria c1, Categoria c2, Double f){
        assert(min<=f&&f<=max);
        if(fatt.isEmpty())
           aggNuovo(c1, c2, f);
        else{
            boolean reg=false;
            int nf=0;
            for(Map<Categoria, Double> m:fatt){
                for(Categoria c:m.keySet()){
                    if(c.equals(c1)){
                     reg=true;
                     if(check(f,m)){
                        m.put(c2, f*m.get(c));
                     }else
                        nonReg();
                     break;
                    }
                    if(c.equals(c2)){
                      reg=true;
                     if(check(1/f, m)){
                      m.put(c1, 1/f*m.get(c));
                     }else
                      nonReg();
                     break;
                    }
                }
                if(reg){
                    unisci();
                    break;
                } 
                nf++;
                if(nf==fatt.size())
                    aggNuovo(c1, c2, f);
            }
        }
    }


    private boolean check(Double f, Map<Categoria, Double> m){
        for(Categoria c : m.keySet()){
            double tmp=m.get(c)*f;
            if(tmp<min || tmp>max)
                return false;
        }
        return true;
    }

    private void nonReg(){
        print("Fattore di conversione è fuori dai limiti\n");
        print("rispetto a Categorie già registrate\n");
        print("Pertanto questo fattore non verrà registrato\n");
    }

    private void unisci(){
        boolean flag;
        for(int i=0;i<fatt.size()-1;i++){
            for(int j=1;j<fatt.size();j++){
                flag=false;
                for(Categoria c1:fatt.get(i).keySet()){
                    for(Categoria c2:fatt.get(j).keySet()){
                        if(c1.equals(c2)){
                            trasferisci(fatt.get(i), fatt.get(j), c1);
                            if(fatt.get(j).size()==1)fatt.remove(j);
                            flag=true;
                            break;
                        }
                    }
                    if(flag) break;
                }

                }
            }
    }
    
    private void aggNuovo(Categoria c1, Categoria c2, Double f){
        fatt.add(new LinkedHashMap());
        fatt.get(0).put(c1,1.0);
        fatt.get(0).put(c2,f);
    }

    private void trasferisci(Map<Categoria, Double> m1, Map<Categoria, Double> m2, Categoria cat){
        double molt=m1.get(cat)/m2.get(cat);
        double val;
        for(Categoria c:m2.keySet()){
                val=molt*m2.get(c);
            if(min<=val && val<=max && !c.equals(cat)){
                m1.put(c, val);
                m2.remove(c);
            }
        }
        if(m2.size()>1){
            for(Categoria c: m2.keySet()){
                if(!c.equals(cat))
                    print(c.getNome()+", ");
                m2.put(c,(m2.get(c)/m2.get(cat)));
            }
            print("non rispettano i limiti imposti sui fattori di conversione\n");

        }
    }
}