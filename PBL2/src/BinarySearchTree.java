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
        System.out.println("Removendo " + name + " da arvore");
        return null;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println("Organizando arvore");
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

    private Node remove(Node current, String name) {return null;}

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

}
