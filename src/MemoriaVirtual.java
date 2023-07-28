import java.util.ArrayList;
import java.util.List;

public class MemoriaVirtual {
    private int tamanhoPagina;
    private int tamanhoMemoriaVirtual;
    private List<Pagina> paginas;

    public MemoriaVirtual(int tamanhoPagina, int tamanhoMemoriaVirtual) {
        this.tamanhoPagina = tamanhoPagina;
        this.tamanhoMemoriaVirtual = tamanhoMemoriaVirtual;
        this.paginas = new ArrayList<>();
    }

    public void alocarPagina(Pagina paginaFisica) {
        if (paginas.size() < tamanhoMemoriaVirtual / tamanhoPagina) {
            Pagina paginaVirtual = new Pagina(paginaFisica.getNumeroPagina(), paginaFisica.getProcesso());
            paginas.add(paginaVirtual);
        } else {
            System.out.println("Memória Virtual cheia. Não é possível alocar mais páginas.");
        }
    }

    public void imprimirConteudoMemoriaVirtual() {
        System.out.println("\nConteúdo da Memória Virtual:");
        for (Pagina pagina : paginas) {
            System.out.println("Processo " + pagina.getProcesso().getNome() + ", Página " + pagina.getNumeroPagina());
        }
    }

    public int getTamanhoMemoriaVirtual() {
        return tamanhoMemoriaVirtual;
    }

    public int getTamanhoPagina() {
        return tamanhoPagina;
    }

}
