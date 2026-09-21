package gui;

import javax.swing.*;
import java.awt.*;
import main.GameState;

public class OpponentSelectionScreen extends JFrame {

    public OpponentSelectionScreen() {
        setTitle("Choose Your Opponent");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(new Color(20, 25, 45));

        // Title
        JLabel title = new JLabel(
                "CHOOSE YOUR OPPONENT",
                SwingConstants.CENTER
        );
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setBorder(
                BorderFactory.createEmptyBorder(30, 0, 20, 0)
        );

        background.add(title, BorderLayout.NORTH);

        // Opponent buttons
        JButton redButton = createOpponentButton(
                "RED",
                "EASY"
        );

        JButton stevenButton = createOpponentButton(
                "STEVEN",
                "MEDIUM"
        );

        JButton cynthiaButton = createOpponentButton(
                "CYNTHIA",
                "HARD"
        );

        // Red
        redButton.addActionListener(e -> {
            GameState.opponentTrainer = "Red";
            dispose();
            new PokemonSelectionScreen();
        });

        // Steven
        stevenButton.addActionListener(e -> {
            GameState.opponentTrainer = "Steven";
            dispose();
            new PokemonSelectionScreen();
        });

        // Cynthia
        cynthiaButton.addActionListener(e -> {
            GameState.opponentTrainer = "Cynthia";
            dispose();
            new PokemonSelectionScreen();
        });

        JPanel opponentPanel = new JPanel(
                new GridLayout(1, 3, 25, 0)
        );

        opponentPanel.setOpaque(false);

        opponentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 60, 40, 60
                )
        );

        opponentPanel.add(redButton);
        opponentPanel.add(stevenButton);
        opponentPanel.add(cynthiaButton);

        background.add(
                opponentPanel,
                BorderLayout.CENTER
        );

        // Instruction
        JLabel instruction = new JLabel(
                "Choose your opponent",
                SwingConstants.CENTER
        );

        instruction.setForeground(Color.LIGHT_GRAY);
        instruction.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        instruction.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 0, 25, 0
                )
        );

        background.add(
                instruction,
                BorderLayout.SOUTH
        );

        add(background);
        setVisible(true);
    }

    private JButton createOpponentButton(
            String name,
            String difficulty
    ) {

        JButton button = new JButton(
                "<html><center>"
                        + name
                        + "<br><br>"
                        + difficulty
                        + "</center></html>"
        );

        button.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(45, 55, 85)
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 130, 160),
                        2
                )
        );

        return button;
    }
}