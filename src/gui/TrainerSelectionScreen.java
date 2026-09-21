package gui;

import javax.swing.*;
import java.awt.*;
import main.GameState;

public class TrainerSelectionScreen extends JFrame {

    public TrainerSelectionScreen() {
        setTitle("Choose Your Trainer");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(new Color(20, 25, 45));

        // Title
        JLabel title = new JLabel(
                "CHOOSE YOUR TRAINER",
                SwingConstants.CENTER
        );
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        background.add(title, BorderLayout.NORTH);

        // Trainer buttons
        JButton boyButton = createTrainerButton("BOY");
        JButton girlButton = createTrainerButton("GIRL");

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

        JPanel trainerPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        trainerPanel.setOpaque(false);
        trainerPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 100, 50, 100)
        );

        trainerPanel.add(boyButton);
        trainerPanel.add(girlButton);

        background.add(trainerPanel, BorderLayout.CENTER);

        // Instruction
        JLabel instruction = new JLabel(
                "Choose your trainer",
                SwingConstants.CENTER
        );
        instruction.setForeground(Color.LIGHT_GRAY);
        instruction.setFont(new Font("Arial", Font.PLAIN, 18));
        instruction.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 25, 0)
        );

        background.add(instruction, BorderLayout.SOUTH);

        add(background);
        setVisible(true);
    }

    private JButton createTrainerButton(String name) {

        JButton button = new JButton(name);

        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(45, 55, 85));

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