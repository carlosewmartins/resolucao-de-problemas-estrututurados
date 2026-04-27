public class BinarySearchTree {
    private Node root;

    public BinarySearchTree (){
        this.root = null;
    }

    public void insert(Player player) {
        root = insert(root, player);
        System.out.println("Inserindo " + player.getNickname() + " na arvore");
    }

    public boolean search(String name) {
        Node found = search(root, name);
        return found != null;
    }

    public Player remove(String name) {
        Node alvo = search(root, name);
        if (alvo == null) {
            System.out.println("Jogador " + name + " nao encontrado na arvore");
            return null;
        }
        Player removido = alvo.getPlayer();
        root = remove(root, removido.getRanking());
        System.out.println("Removendo " + name + " da arvore");
        return removido;
    }

    public void inOrder() {
        inOrder(root);
    }

    private Node insert(Node current, Player player) {
        if(current == null){
            return new Node(player);
        }
        if(player.getRanking() < current.getPlayer().getRanking()) {
            current.setLeft(insert(current.getLeft(), player));
        } else if (player.getRanking() > current.getPlayer().getRanking()) {
            current.setRight(insert(current.getRight(), player));
        } else {
            // Criar logica para insert de valor ja existente na arvore
            System.out.println("Ja existe um valor igual na arvore!");
        }

        return current;
    }

    private Node search(Node current, String name) {
        if(current == null){
            return null;
        }

        if(current.getPlayer().getNickname().equalsIgnoreCase(name)) {
            return current;
        }

        Node foundLeft = search(current.getLeft(), name);

        if(foundLeft != null){
            return foundLeft;
        }

        return search(current.getRight(), name);
    }

    private Node remove(Node current, int ranking) {
        if(current == null){
            return null;
        }

        if(ranking < current.getPlayer().getRanking()) {
           current.setLeft(remove(current.getLeft(), ranking));
        } else if(ranking > current.getPlayer().getRanking()) {
            current.setRight(remove(current.getRight(), ranking));
        } else {
            if(current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            if(current.getLeft() == null) {
                return current.getRight();
            }

            if(current.getRight() == null) {
                return current.getLeft();
            }

            Node sucessor = encontraMinimo(current.getRight());
            current.setPlayer(sucessor.getPlayer());
            current.setRight(remove(current.getRight(), sucessor.getPlayer().getRanking()));
        }

        return current;
    }

    private void inOrder(Node current) {
        if (current != null) {
            inOrder(current.getLeft());
            System.out.println(current.getPlayer().getNickname() + " (" + current.getPlayer().getRanking() + ")");
            inOrder(current.getRight());
        }

    }

    public Node getRoot() {
        return root;
    }

    // Helper
    private Node encontraMinimo(Node current){
        if(current.getLeft() == null){
            return current;
        }
        return encontraMinimo(current.getLeft());
    }

}
