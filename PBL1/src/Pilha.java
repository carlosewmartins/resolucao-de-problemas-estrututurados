public class Pilha<T> {
    private No<T> topo;
    private int tamanho;

    public Pilha() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void empilhar(T dado) {
        No<T> novo = new No<>(dado);
        novo.setProximo(topo);
        topo = novo;
        tamanho++;
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha vazia!");
        }
        T dado = topo.getDado();
        topo = topo.getProximo();
        tamanho--;
        return dado;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}