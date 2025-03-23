package unibs.progetto;
import java.util.Map;

public class Categoria{

    private String nome;
    private Map<String, Categoria> dominio;
    private Categoria genitore=null;

    Categoria(String n, Map<String, Categoria> d){
        nome=n;
        dominio=d;
    }

    Categoria(String arg){
        this(arg, null);
    }

    void setDominio(Map<String, Categoria> arg){
        dominio=arg;
    }

    void setGenitore(Categoria arg){
        genitore=arg;
    }

    public String getNome(){
        return nome;
    }

    public Categoria getGenitore(){
        return genitore;
    }

    public Map<String, Categoria> getDominio(){
        return dominio;
    }


@Override
    public String toString(){
        return nome;
    }
    

}