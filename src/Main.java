public class Main {
    public static void main(String[] args) {
        int tamanhoPagina = 4; // Tamanho de cada página
        int tamanhoMemoria = 8; // Número total de páginas na memória

        Memoria memoria = new Memoria(tamanhoPagina, tamanhoMemoria);

        // Criar processos (nome, pid, tamanho)
        Processo processo1 = new Processo("Processo A", 1, 10);
        Processo processo2 = new Processo("Processo B", 2, 8);
        Processo processo3 = new Processo("Processo C", 3, 6);
        Processo processo4 = new Processo("Processo D", 4, 12);
        Processo processo5 = new Processo("Processo E", 5, 7);

        // Simular solicitações de páginas (Processo, Número da Página)
        int[][] solicitacoesPagina = {
                {1, 0}, {2, 1}, {3, 2}, {1, 3}, {4, 4}, {2, 5}, {5, 6}, {6, 7},
                {1, 1}, {2, 2}, {3, 3}, {4, 0}, {5, 5}, {6, 6}, {7, 7}, {8, 0}
        };

        for (int[] solicitacao : solicitacoesPagina) {
            int pid = solicitacao[0];
            int numeroPagina = solicitacao[1];

            Processo processo = null;
            switch (pid) {
                case 1:
                    processo = processo1;
                    break;
                case 2:
                    processo = processo2;
                    break;
                case 3:
                    processo = processo3;
                    break;
                case 4:
                    processo = processo4;
                    break;
                case 5:
                    processo = processo5;
                    break;
                default:
                    System.out.println("ID de Processo Inválido");
            }

            if (processo != null) {
                if (memoria.paginaEstaNaMemoria(processo, numeroPagina)) {
                    System.out.println("Página " + numeroPagina + " do " + processo.getNome() + " já está na memória.");
                } else {
                    System.out.println("Página " + numeroPagina + " do " + processo.getNome() + " não está na memória. Adicionando...");
                    memoria.adicionarPaginaNaMemoria(processo, numeroPagina);
                }
                memoria.imprimirConteudoMemoria();
            }
        }
    }
}