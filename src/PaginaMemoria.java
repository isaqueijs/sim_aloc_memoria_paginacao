public class PaginaMemoria {
    private int numeroPagina;
    private Processo processo;

    public PaginaMemoria(int numeroPagina, Processo processo) {
        this.numeroPagina = numeroPagina;
        this.processo = processo;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public Processo getProcesso() {
        return processo;
    }
}
