package unibs.progetto;
import java.io.Serializable;
import java.util.Set;

import static unibs.util.Aiuto.maiuscola;

class Comprensorio implements Serializable {
	private static final long serialVersionUID = 1L;
    private final String nome;
    private final Set<String> comuni;

    public Comprensorio(String nome, Set<String> comuni) {
        this.nome = nome;
        this.comuni = comuni;
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getComuni() {
        return comuni;
    }

    @Override
    public String toString() {
        String res= "Comprensorio: " + maiuscola(nome) + "\tComuni: ";
        for(String s:comuni)
           res=res+maiuscola(s)+" ";
        return res;
    }
}