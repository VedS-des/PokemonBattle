package gui;

import javax.swing.*;
import java.awt.*;

public class ResultScreen extends JFrame {

    public ResultScreen(
            String playerTrainer,
            String opponentTrainer,
            boolean playerWon
    ) {

        setTitle("Pokémon Battle - Result");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon backgroundIcon =
                new ImageIcon(
                        "assets/backgrounds/BattleBackground.png"
                );

        Image backgroundImage =
                backgroundIcon.getImage().getScaledInstance(
                        1000,
                        600,
                        Image.SCALE_SMOOTH
                );

        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                g.drawImage(
                        backgroundImage,
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        this
                );
            }
        };

        panel.setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel(
                playerWon ? "YOU WIN!" : "YOU LOSE!",
                SwingConstants.CENTER
        );

        resultLabel.setForeground(Color.WHITE);

        resultLabel.setFont(
                new Font("Arial", Font.BOLD, 56)
        );
        JLabel trainerLabel = new JLabel(
                playerTrainer + "  VS  " + opponentTrainer,
                SwingConstants.CENTER
        );

        trainerLabel.setForeground(Color.WHITE);

        trainerLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        panel.add(
                trainerLabel,
                BorderLayout.NORTH
        );

        panel.add(
                resultLabel,
                BorderLayout.CENTER
        );
        JPanel buttonPanel = new JPanel();

        buttonPanel.setOpaque(false);

        JButton tryAgainButton =
                new JButton("TRY AGAIN");

        JButton mainMenuButton =
                new JButton("MAIN MENU");

        tryAgainButton.addActionListener(e -> {

            dispose();

            new PokemonSelectionScreen();
        });

        mainMenuButton.addActionListener(e -> {

            dispose();

            new IntroScreen();
        });

        tryAgainButton.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        mainMenuButton.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        buttonPanel.add(tryAgainButton);
        buttonPanel.add(mainMenuButton);

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(panel);

        setVisible(true);
    }
}