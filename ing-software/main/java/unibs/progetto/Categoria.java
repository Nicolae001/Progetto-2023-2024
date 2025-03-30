package unibs.progetto;

public class Categoria{

    private String nome;
    private Categoria genitore=null;
    private Campo campo=null;

    private Categoria(String n){
        nome=n;
    }

    static Categoria nuova(String arg){
        if(arg.equals(""))
            return null;
        return new Categoria(arg);
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

    public Campo getCampo(){
        return campo;
    }
    
    void setCampo(Campo arg){
        campo=arg;
    }

@Override
    public String toString(){
        return nome;
    }
    

}