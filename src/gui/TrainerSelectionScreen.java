package gui;

import javax.swing.*;
import main.GameState;

public class TrainerSelectionScreen extends JFrame {

    public TrainerSelectionScreen() {
        setTitle("Choose Your Trainer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "CHOOSE YOUR TRAINER",
                SwingConstants.CENTER
        );

        title.setFont(title.getFont().deriveFont(30f));

        JButton boyButton = new JButton("BOY");
        JButton girlButton = new JButton("GIRL");

        boyButton.addActionListener(e -> {
            GameState.playerTrainer = "Boy";
            dispose();
            new OpponentSelectionScreen();
        });

        girlButton.addActionListener(e -> {
            GameState.playerTrainer = "Girl";
            dispose();
            new OpponentSelectionScreen();
        });

        JPanel panel = new JPanel();
        panel.add(boyButton);
        panel.add(girlButton);

        add(title, "North");
        add(panel, "Center");

        setVisible(true);
    }
}