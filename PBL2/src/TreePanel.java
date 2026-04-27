import javax.swing.*;
import java.awt.*;

public class TreePanel extends JPanel {
    Node root;

    public TreePanel(Node root) {
        this.root = root;
    }

    public void setRoot(Node root) {
        this.root = root;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            drawNode(g, root, getWidth() / 2, 40, getWidth() / 4);
        }
    }

    private void drawNode(Graphics g, Node node, int x, int y, int hGap) {
        if (node == null) return;

        if (node.getLeft() != null) {
            g.drawLine(x, y, x - hGap, y + 80);
            drawNode(g, node.getLeft(), x - hGap, y + 80, hGap / 2);
        }

        if (node.getRight() != null) {
            g.drawLine(x, y, x + hGap, y + 80);
            drawNode(g, node.getRight(), x + hGap, y + 80, hGap / 2);
        }

        g.setColor(Color.GREEN);
        g.fillOval(x - 25, y - 25, 50, 50);

        g.setColor(Color.BLACK);
        g.drawString(node.getPlayer().getNickname(), x - 20, y + 5);
    }
}