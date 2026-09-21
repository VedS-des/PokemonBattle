package gui;

import javax.swing.*;
import main.GameState;

public class OpponentSelectionScreen extends JFrame {

    public OpponentSelectionScreen() {
        setTitle("Choose Your Opponent");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("CHOOSE YOUR OPPONENT", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(30f));

        JButton redButton = new JButton("RED - EASY");
        JButton stevenButton = new JButton("STEVEN - MEDIUM");
        JButton cynthiaButton = new JButton("CYNTHIA - HARD");

        redButton.addActionListener(e -> {
            GameState.opponentTrainer = "Red";
            dispose();
            new PokemonSelectionScreen();
        });

        stevenButton.addActionListener(e -> {
            GameState.opponentTrainer = "Steven";
            dispose();
            new PokemonSelectionScreen();
        });

        cynthiaButton.addActionListener(e -> {
            GameState.opponentTrainer = "Cynthia";
            dispose();
            new PokemonSelectionScreen();
        });

        JPanel panel = new JPanel();
        panel.add(redButton);
        panel.add(stevenButton);
        panel.add(cynthiaButton);

        add(title, "North");
        add(panel, "Center");

        setVisible(true);
    }
}