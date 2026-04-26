public class Node {
    private Player player;
    private Node left;
    private Node right;

    public Node(Player player) {
        this.player = player;
        this.left = null;
        this.right = null;
    }
}
