
import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

class SistemaVotacaoBBBGUI {
    private JFrame frame;
    private ArrayList<Jogador> participantes;

    public SistemaVotacaoBBBGUI() {
        frame = new JFrame("Sistema de Votação BBB");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        participantes = new ArrayList<>();

        JButton cadastrarButton = new JButton("Cadastrar Participante");
        cadastrarButton.setBounds(50, 30, 250, 30);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarParticipante();
            }
        });
        frame.add(cadastrarButton);

        JButton votarButton = new JButton("Votar para Eliminar Participante");
        votarButton.setBounds(50, 80, 250, 30);
        votarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                votarParaEliminar();
            }
        });
        frame.add(votarButton);

        JButton mostrarEliminadoButton = new JButton("Mostrar Participante Eliminado");
        mostrarEliminadoButton.setBounds(50, 130, 250, 30);
        mostrarEliminadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarParticipanteEliminado();
            }
        });
        frame.add(mostrarEliminadoButton);

        JButton mostrarListaButton = new JButton("Mostrar Lista de Participantes");
        mostrarListaButton.setBounds(50, 180, 250, 30);
        mostrarListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarListaParticipantes();
            }
        });
        frame.add(mostrarListaButton);

        frame.setVisible(true);
    }

    private void cadastrarParticipante() {
        String nome = JOptionPane.showInputDialog(frame, "Digite o nome do participante:");
        participantes.add(new Jogador(nome));
        JOptionPane.showMessageDialog(frame, "Participante cadastrado com sucesso.");
    }

    private void votarParaEliminar() {
        if (participantes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há participantes cadastrados.");
            return;
        }

        String[] nomesParticipantes = new String[participantes.size()];
        for (int i = 0; i < participantes.size(); i++) {
            nomesParticipantes[i] = participantes.get(i).getNome();
        }

        String escolha = (String) JOptionPane.showInputDialog(frame, "Selecione o participante a eliminar:",
                "Votar para Eliminar", JOptionPane.QUESTION_MESSAGE, null, nomesParticipantes, nomesParticipantes[0]);

        if (escolha != null) {
            for (Jogador participante : participantes) {
                if (participante.getNome().equals(escolha)) {
                    participante.incrementarVoto();
                    JOptionPane.showMessageDialog(frame, "Voto registrado com sucesso para eliminar " + escolha + ".");
                    return;
                }
            }
        }
    }

    private void mostrarParticipanteEliminado() {
        if (participantes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há participantes cadastrados.");
            return;
        }

        Jogador eliminado = participantes.get(0);
        for (Jogador participante : participantes) {
            if (participante.getNumeroVotos() > eliminado.getNumeroVotos()) {
                eliminado = participante;
            }
        }

        JOptionPane.showMessageDialog(frame, "O participante eliminado é: \n" + eliminado);
    }

    private void mostrarListaParticipantes() {
        if (participantes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há participantes cadastrados.");
            return;
        }

        StringBuilder listaParticipantes = new StringBuilder("Lista de participantes:\n");
        for (Jogador participante : participantes) {
            listaParticipantes.append(participante.getNome()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, listaParticipantes.toString());
    }

    public static void main(String[] args) {
        new SistemaVotacaoBBBGUI();
    }
}