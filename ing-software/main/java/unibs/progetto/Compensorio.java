package unibs.progetto;
import java.io.Serializable;
import java.util.Set;

class Comprensorio implements Serializable {
	private static final long serialVersionUID = 1L;
    private String nome;
    private Set<String> comuni;

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
        return "Comprensorio: " + nome + ", Comuni: " + comuni;
    }
}