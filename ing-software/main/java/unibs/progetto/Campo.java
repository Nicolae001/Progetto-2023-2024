package unibs.progetto;
import java.util.Map;

public class Campo {
     private String nome;
     private Map<Elemento, Categoria> dom=null;
  
     public Campo (String arg){
      nome=arg;
     }

     public String getNome (){
      return nome;
     }

     void setNome(String arg){
        nome=arg;
     }

     public Map<Elemento, Categoria> getDom(){
          return dom;
     }

     void setDom( Map<Elemento, Categoria> arg){
      dom=arg;
     }

     public Categoria getCat(String arg){
          for(Elemento e:dom.keySet()){
               if(e.getNome().equals(arg))
               return dom.get(e);
          }
          return null;
     }

     public void setCat(Elemento key, Categoria val){
          dom.put(key, val);
     }

}
