public class Pagina {
    private int numeroPagina;
    private Processo processo;

    public Pagina(int numeroPagina, Processo processo) {
        this.numeroPagina = numeroPagina;
        this.processo = processo;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}
