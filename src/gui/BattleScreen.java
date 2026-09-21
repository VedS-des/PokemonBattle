package gui;

import javax.swing.*;
import java.awt.*;
import main.GameState;

public class BattleScreen extends JFrame {

    private JLabel messageLabel;

    public BattleScreen() {

        setTitle("Pokemon Battle");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 80, 100));

        // =========================
        // BATTLE AREA
        // =========================

        JPanel battleArea = new JPanel(null);
        battleArea.setBackground(new Color(80, 140, 150));

        // =========================
        // ENEMY INFORMATION
        // =========================

        JPanel enemyInfo = createInfoBox(
                getOpponentPokemon(),
                "HP  ██████████"
        );

        enemyInfo.setBounds(620, 50, 300, 100);
        battleArea.add(enemyInfo);

        // =========================
        // PLAYER INFORMATION
        // =========================

        String playerName = GameState.playerPokemon[0];

        JPanel playerInfo = createInfoBox(
                playerName,
                "HP  ██████████"
        );

        playerInfo.setBounds(80, 360, 300, 100);
        battleArea.add(playerInfo);

        // =========================
        // ENEMY SPRITE
        // =========================

        JLabel enemyPokemon = createSpriteLabel(
                getOpponentPokemon() + "_front.png"
        );

        enemyPokemon.setBounds(
                650,
                150,
                220,
                220
        );

        battleArea.add(enemyPokemon);

        // =========================
        // PLAYER SPRITE
        // =========================

        JLabel playerPokemon = createSpriteLabel(
                playerName + "_back.png"
        );

        playerPokemon.setBounds(
                120,
                230,
                220,
                220
        );

        battleArea.add(playerPokemon);

        mainPanel.add(
                battleArea,
                BorderLayout.CENTER
        );

        // =========================
        // MESSAGE + MOVES
        // =========================

        JPanel bottomPanel = new JPanel(
                new BorderLayout()
        );

        bottomPanel.setBackground(
                new Color(25, 30, 45)
        );

        messageLabel = new JLabel(
                "What will " + playerName + " do?",
                SwingConstants.CENTER
        );

        messageLabel.setForeground(Color.WHITE);

        messageLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        messageLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 10, 15, 10
                )
        );

        bottomPanel.add(
                messageLabel,
                BorderLayout.NORTH
        );

        // =========================
        // MOVE BUTTONS
        // =========================

        JPanel movePanel = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        movePanel.setBackground(
                new Color(25, 30, 45)
        );

        String[] moves = {
                "MOVE 1",
                "MOVE 2",
                "MOVE 3",
                "MOVE 4"
        };

        for (String move : moves) {

            JButton moveButton = new JButton(move);

            moveButton.setFont(
                    new Font("Arial", Font.BOLD, 18)
            );

            moveButton.setFocusPainted(false);

            moveButton.addActionListener(e -> {

                messageLabel.setText(
                        playerName
                                + " used "
                                + move + "!"
                );
            });

            movePanel.add(moveButton);
        }

        bottomPanel.add(
                movePanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        setVisible(true);
    }

    // =========================
    // CREATE POKEMON SPRITE
    // =========================

    private JLabel createSpriteLabel(String fileName) {

        JLabel label = new JLabel();

        ImageIcon icon = new ImageIcon(
                "assets/pokemon/" + fileName
        );

        Image image = icon.getImage()
                .getScaledInstance(
                        220,
                        220,
                        Image.SCALE_SMOOTH
                );

        label.setIcon(
                new ImageIcon(image)
        );

        return label;
    }

    // =========================
    // CREATE INFO BOX
    // =========================

    private JPanel createInfoBox(
            String pokemonName,
            String hp
    ) {

        JPanel panel = new JPanel(
                new GridLayout(2, 1)
        );

        panel.setBackground(
                new Color(235, 235, 220)
        );

        panel.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );

        JLabel name = new JLabel(
                pokemonName
        );

        name.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        name.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 10, 0, 10
                )
        );

        JLabel hpLabel = new JLabel(
                hp
        );

        hpLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 10, 5, 10
                )
        );

        panel.add(name);
        panel.add(hpLabel);

        return panel;
    }

    // =========================
    // GET OPPONENT POKEMON
    // =========================

    private String getOpponentPokemon() {

        if (GameState.opponentTrainer == null) {
            return "Opponent";
        }

        switch (GameState.opponentTrainer) {

            case "Red":
                return "Lapras";

            case "Steven":
                return "Metagross";

            case "Cynthia":
                return "Togekiss";

            default:
                return "Opponent";
        }
    }
}