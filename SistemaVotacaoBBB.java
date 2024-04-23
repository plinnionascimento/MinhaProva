import java.util.ArrayList;
import java.util.Scanner;

public class SistemaVotacaoBBB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jogador> participantes = new ArrayList<Jogador>();
        int opcao;

        do {
            System.out.println("1 - Cadastrar participante do BBB?");
            System.out.println("2 - Votar para eliminar um participante?");
            System.out.println("3 - Mostrar participante eliminado?");
            System.out.println("4 - Mostrar lista de participantes?");
            System.out.println("0 - Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarParticipante(participantes);
                    break;
                case 2:
                    votarParaEliminar(participantes);
                    break;
                case 3:
                    mostrarParticipanteEliminado(participantes);
                    break;
                case 4:
                    mostrarListaParticipantes(participantes);
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarParticipante(ArrayList<Jogador> participantes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do participante: ");
        String nome = scanner.nextLine();
        Jogador participante = new Jogador(nome);
        participantes.add(participante);
        System.out.println("Participante cadastrado com sucesso.");
    }

    private static void votarParaEliminar(ArrayList<Jogador> participantes) {
        Scanner scanner = new Scanner(System.in);
        if (participantes.isEmpty()) {
            System.out.println("Não há participantes cadastrados.");
            return;
        }

        System.out.println("Lista de participantes:");
        for (int i = 0; i < participantes.size(); i++) {
            System.out.println((i + 1) + " - " + participantes.get(i).getNome());
        }

        System.out.println("Digite o número do participante para votar pela eliminação:");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > participantes.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Jogador participanteEscolhido = participantes.get(escolha - 1);
        participanteEscolhido.incrementarVoto();
        System.out.println("Voto registrado com sucesso para eliminar " + participanteEscolhido.getNome() + ".");
    }

    private static void mostrarParticipanteEliminado(ArrayList<Jogador> participantes) {
        if (participantes.isEmpty()) {
            System.out.println("Não há participantes cadastrados.");
            return;
        }

        Jogador eliminado = participantes.get(0);
        for (Jogador participante : participantes) {
            if (participante.getNumeroVotos() > eliminado.getNumeroVotos()) {
                eliminado = participante;
            }
        }

        System.out.println("O participante eliminado é: " + eliminado.getNome());
    }

    private static void mostrarListaParticipantes(ArrayList<Jogador> participantes) {
        if (participantes.isEmpty()) {
            System.out.println("Não há participantes cadastrados.");
            return;
        }

        System.out.println("Lista de participantes:");
        for (Jogador participante : participantes) {
            System.out.println(participante.getNome());
        }
    }
}
