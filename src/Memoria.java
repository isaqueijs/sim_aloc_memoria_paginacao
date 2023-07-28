import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private int tamanhoPagina;
    private int tamanhoMemoria;
    private List<Pagina> paginas;
    private MemoriaVirtual memoriaVirtual;

    public Memoria(int tamanhoPagina, int tamanhoMemoria, MemoriaVirtual memoriaVirtual) {
        this.tamanhoPagina = tamanhoPagina;
        this.tamanhoMemoria = tamanhoMemoria;
        this.paginas = new ArrayList<>();

        for (int i = 1; i <= tamanhoMemoria / tamanhoPagina; i++) {
            paginas.add(new Pagina(i, null));
        }
        this.memoriaVirtual = memoriaVirtual;
    }

    public void alocarProcesso(Processo processo) {
        int paginasNecessarias = (int) Math.ceil((double) processo.getTamanho() / tamanhoPagina);
        int numPagDisponiveis = qtdPagDisponiveis();
        if (numPagDisponiveis >= paginasNecessarias) {
            for (int i = 0; i < paginasNecessarias; i++) {
                paginas.set(primeiraPagDisponivel(), new Pagina(i+1, processo));
            }
            System.out.println("Processo " + processo.getNome() + " alocado na Memória.");
        } else {
            substituirPaginaFIFO(processo, paginasNecessarias);
        }
    }

    private int qtdPagDisponiveis() {
        int qtdDisponiveis = 0;

        for (Pagina pag : paginas) {
            if (pag.getProcesso() == null) {
                qtdDisponiveis++;
            }
        }
        return qtdDisponiveis;
    }

    private int primeiraPagDisponivel() {
        int index = -1;
        for (int i = 0 ; i < paginas.size(); i++) {
            if (paginas.get(i).getProcesso() == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void substituirPaginaFIFO(Processo processo, int paginasNecessarias) {
        System.out.println("Memória cheia. Aplicando algoritmo de substituição FIFO...");

        Pagina paginaSubstituir;
        int index = 0;

        for (int i = 0; i < paginasNecessarias; i++) {
            Main.page_fault++;
            index = primeiraPagDisponivel();
            if (index < 0) {
                paginaSubstituir = paginas.remove(0);
                memoriaVirtual.alocarPagina(paginaSubstituir);
                System.out.println("Página " + paginaSubstituir.getNumeroPagina() + " do processo " + paginaSubstituir.getProcesso().getNome() + " foi movida para a Memória Virtual.");
                paginas.add(new Pagina(i+1, processo));
            } else {
                paginas.set(index, new Pagina(i+1, processo));
            }
        }
        System.out.println("Processo " + processo.getNome() + " alocado na Memória.");
    }

    public void imprimirConteudoMemoria() {
        System.out.println("\nConteúdo da Memória:");
        for (int i = 0; i < paginas.size(); i++) {
            if (paginas.get(i).getProcesso() == null) {
                System.out.println("Frame " + i + " Disponivel");
            } else {
                System.out.println("Frame "+ i +" Ocupado: Página " + paginas.get(i).getNumeroPagina() + " do Processo: Nome:" + paginas.get(i).getProcesso().getNome() + ", PID:" + paginas.get(i).getProcesso().getPid());
            }
        }
    }

    public int getTamanhoMemoria() {
        return tamanhoMemoria;
    }
}
