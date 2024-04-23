class Jogador {
    private String nome;
    private int votos;

    public Jogador(String nome) {
        this.nome = nome;
        this.votos = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroVotos() {
        return votos;
    }

    public void incrementarVoto() {
        this.votos++;
    }
}

