public class Processo {
    private String nome;
    private int pid;
    private int tamanho;

    public Processo(String nome, int pid, int tamanho) {
        this.nome = nome;
        this.pid = pid;
        this.tamanho = tamanho;
    }

    public String getNome() {
        return nome;
    }

    public int getPid() {
        return pid;
    }

    public int getTamanho() {
        return tamanho;
    }
}