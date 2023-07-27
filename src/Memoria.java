import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private int tamanhoPagina;
    private int tamanhoMemoria;
    private List<PaginaMemoria> paginas;

    public Memoria(int tamanhoPagina, int tamanhoMemoria) {
        this.tamanhoPagina = tamanhoPagina;
        this.tamanhoMemoria = tamanhoMemoria;
        this.paginas = new ArrayList<>();
    }

    public boolean paginaEstaNaMemoria(Processo processo, int numeroPagina) {
        for (PaginaMemoria pagina : paginas) {
            if (pagina.getProcesso().getPid() == processo.getPid() && pagina.getNumeroPagina() == numeroPagina) {
                return true;
            }
        }
        return false;
    }

    public void adicionarPaginaNaMemoria(Processo processo, int numeroPagina) {
        if (paginas.size() < tamanhoMemoria) {
            PaginaMemoria novaPagina = new PaginaMemoria(numeroPagina, processo);
            paginas.add(novaPagina);
        } else {
            System.out.println("Memória está cheia. Realizando substituição FIFO.");
            substituirPaginaFIFO(processo, numeroPagina);
        }
    }

    private void substituirPaginaFIFO(Processo processo, int numeroPagina) {
        PaginaMemoria paginaMaisAntiga = paginas.get(0);
        paginas.remove(0);
        System.out.println("Página " + paginaMaisAntiga.getNumeroPagina() + " do processo " + paginaMaisAntiga.getProcesso().getNome() +
                " foi removida da memória.");
        PaginaMemoria novaPagina = new PaginaMemoria(numeroPagina, processo);
        paginas.add(novaPagina);
    }

    public void imprimirConteudoMemoria() {
        System.out.println("\nConteúdo da Memória:");
        for (PaginaMemoria pagina : paginas) {
            System.out.println("Processo " + pagina.getProcesso().getNome() + ", Página " + pagina.getNumeroPagina());
        }
        System.out.println();
    }
}
