package gui;

import javax.swing.*;
import java.awt.*;

public class IntroScreen extends JFrame {

    public IntroScreen() {
        setTitle("Pokemon Battle");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main background
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        background.setBackground(new Color(20, 25, 45));

        // Title
        JLabel title = new JLabel("POKEMON BATTLE", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 48));

        // Subtitle
        JLabel subtitle = new JLabel(
                "A 3v3 Pokemon Battle",
                SwingConstants.CENTER
        );
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 20));

        // Start button
        JButton startButton = new JButton("CLICK TO START");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(260, 60));

        startButton.addActionListener(e -> {
            dispose();
            new TrainerSelectionScreen();
        });

        // Title section
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);
        titlePanel.add(title);
        titlePanel.add(subtitle);

        // Button section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton);

        background.add(titlePanel, BorderLayout.CENTER);
        background.add(buttonPanel, BorderLayout.SOUTH);

        add(background);

        setVisible(true);
    }
}