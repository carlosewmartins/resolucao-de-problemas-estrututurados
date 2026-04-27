import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TreeWindow extends JFrame {

    BinarySearchTree tree;
    TreePanel treePanel;
    JTextField nicknameField;
    JTextField rankingField;
    JButton insertButton;
    JButton searchButton;
    JButton removeButton;
    JButton inOrderButton;
    JLabel messageLabel;

    public TreeWindow() {
        tree = new BinarySearchTree();

        setTitle("Rank de Jogadores");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();

        nicknameField = new JTextField(10);
        rankingField = new JTextField(5);
        insertButton = new JButton("Adicionar");
        searchButton = new JButton("Buscar");
        removeButton = new JButton("Remover");
        inOrderButton = new JButton("Organizar");

        controlPanel.add(new JLabel("Nick:"));
        controlPanel.add(nicknameField);
        controlPanel.add(new JLabel("Rank:"));
        controlPanel.add(rankingField);
        controlPanel.add(insertButton);
        controlPanel.add(searchButton);
        controlPanel.add(removeButton);
        controlPanel.add(inOrderButton);

        treePanel = new TreePanel(tree.getRoot());
        messageLabel = new JLabel("");

        add(controlPanel, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        loadCSV();
        configureButtons();
    }

    private void configureButtons() {
        insertButton.addActionListener(e -> {
            String nickname = nicknameField.getText();
            int ranking = Integer.parseInt(rankingField.getText());
            tree.insert(new Player(nickname, ranking));
            treePanel.setRoot(tree.getRoot());
            messageLabel.setText("Jogador " + nickname + " adicionado!");
        });

        searchButton.addActionListener(e -> {
            String nickname = nicknameField.getText();
            boolean found = tree.search(nickname);
            if (found) {
                messageLabel.setText("Jogador " + nickname + " encontrado");
            } else {
                messageLabel.setText("Jogador " + nickname + " não encontrado.");
            }
        });

        removeButton.addActionListener(e -> {
            String nickname = nicknameField.getText();
            Player removed = tree.remove(nickname);
            treePanel.setRoot(tree.getRoot());
            if (removed != null) {
                messageLabel.setText("Jogador " + nickname + " removido");
            } else {
                messageLabel.setText("Jogador " + nickname + " não encontrado.");
            }
        });

        inOrderButton.addActionListener(e -> {
            System.out.println("=== Jogadores em ordem ===");
            tree.inOrder();
            messageLabel.setText("Lista de jogadores impressa no console");
        });
    }

    private void loadCSV() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/players.csv"));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String nickname = parts[0].trim();
                int ranking = Integer.parseInt(parts[1].trim());
                tree.insert(new Player(nickname, ranking));
            }
            br.close();
            treePanel.setRoot(tree.getRoot());
            messageLabel.setText("CSV carregado");
        } catch (Exception e) {
            messageLabel.setText("erro ao carregar");
        }
    }
}