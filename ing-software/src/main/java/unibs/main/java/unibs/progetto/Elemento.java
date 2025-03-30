package unibs.progetto;


public class Elemento{
  
    private String descr;
    private String nome;
    private Elemento(String arg){
        nome=arg;
    }
    static Elemento nuovo(String arg){
        if(arg.equals(""))
            return null;
        return new Elemento(arg);
    }
    public String getDescr(){
        return descr;
    }

    public String getNome(){
        return nome;
    }

    void setDescr(String arg){
        descr=arg;
    }

    void setNome(String arg){
        nome=arg;
    }

}