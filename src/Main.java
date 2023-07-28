import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int page_fault = 0;
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        ArrayList<Processo> listaProcessos = new ArrayList<Processo>();

        System.out.println("--------------------------------------------------------------\n"
                + "Gerenciamento de Memória - Paginação" + "\n--------------------------------------------------------------");

        System.out.print("Digite o tamanho de cada página (KB): ");
        int tamanhoPagina = ler.nextInt();

        System.out.print("Digite o tamanho da memória física (KB): ");
        int tamanhoMemoria = ler.nextInt();

        System.out.print("Digite o tamanho da memória virtual (KB): ");
        int tamanhoMemoriaVirtual = ler.nextInt();

        System.out.print("Digite o número de processos que deseja criar: ");
        int qtdProcessos = ler.nextInt();

        for (int i = 0; i < qtdProcessos; i++) {
//			System.out.print("Digite o NOME do processo: ");
            System.out.print("Digite o NOME, PID e TAMANHO (KB) do processo: ");
            String nome = ler.next();
//			System.out.print("Digite o ID do processo: ");
            int id = ler.nextInt();
//			System.out.print("Digite o TAMANHO do processo (KB): ");
            int tamanhoProcesso = ler.nextInt();

            listaProcessos.add(new Processo(nome, id, tamanhoProcesso));
        }
        ler.close();
        System.out.println("Pronto, " + qtdProcessos + " processos criados com sucesso!");

        MemoriaVirtual memoriaVirtual = new MemoriaVirtual(tamanhoPagina, tamanhoMemoriaVirtual);
        Memoria memoria = new Memoria(tamanhoPagina, tamanhoMemoria, memoriaVirtual);

        memoria.imprimirConteudoMemoria();
        memoriaVirtual.imprimirConteudoMemoriaVirtual();

        System.out.println("--------------------------------------------------------------\n"
                + "Agora vamos começar a alocar os processos"
                + "\n--------------------------------------------------------------");

        for (Processo processo : listaProcessos) {
            System.out.println("\nAlocando Processo : " + processo.toString() + "...");
            memoria.alocarProcesso(processo);
            memoria.imprimirConteudoMemoria();
            memoriaVirtual.imprimirConteudoMemoriaVirtual();
        }

        System.out.println("\nQuantidade de Page Faut: " + page_fault);
    }
}