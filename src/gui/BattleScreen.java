package gui;

import javax.swing.*;
import java.awt.*;
import main.GameState;

public class BattleScreen extends JFrame {

    private JLabel messageLabel;
    private JProgressBar playerHPBar;
    private JProgressBar enemyHPBar;
    private JPanel partyPanel;

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

        ImageIcon backgroundIcon =
                new ImageIcon(
                        "assets/backgrounds/BattleBackground.png"
                );

        Image backgroundImage =
                backgroundIcon.getImage().getScaledInstance(
                        1000,
                        500,
                        Image.SCALE_SMOOTH
                );

        JPanel battleArea = new JPanel(null) {

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

        // =========================
        // ENEMY INFORMATION
        // =========================

        JPanel enemyInfo = createInfoBox(
                getOpponentPokemon(),
                "HP  100 / 100"
        );

        enemyInfo.setBounds(620, 50, 300, 100);
        battleArea.add(enemyInfo);

        // =========================
        // PLAYER INFORMATION
        // =========================

        String playerName = GameState.playerPokemon[0];

        JPanel playerInfo = createInfoBox(
                playerName,
                "HP  100 / 100"
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

        // =========================
// PLAYER TRAINER SPRITE
// =========================

        String playerTrainerFile;

        if (GameState.playerTrainer.equals("Boy")) {
            playerTrainerFile = "Boy_back.png";
        } else {
            playerTrainerFile = "Girl_back.png";
        }

        JLabel playerTrainer = createTrainerSpriteLabel(
                playerTrainerFile
        );

        playerTrainer.setBounds(
                40,
                250,
                120,
                180
        );

        battleArea.add(playerTrainer);

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
        partyPanel = new JPanel(
                new GridLayout(3, 1, 5, 5)
        );

        partyPanel.setBackground(
                new Color(25, 30, 45)
        );

        partyPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.WHITE),
                        "YOUR PARTY"
                )
        );

        for (String pokemon : GameState.playerPokemon) {

            String symbol = pokemon.equals(
                    GameState.playerPokemon[0]
            ) ? "> " : "  ";

            JLabel pokemonLabel = new JLabel(
                    symbol + pokemon
            );

            pokemonLabel.setForeground(Color.WHITE);
            pokemonLabel.setFont(
                    new Font("Arial", Font.BOLD, 16)
            );

            partyPanel.add(pokemonLabel);
        }

        mainPanel.add(
                partyPanel,
                BorderLayout.EAST
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

        String[] moves;

        switch (playerName) {

            case "Gardevoir":
                moves = new String[]{
                        "Psychic",
                        "Moonblast",
                        "Shadow Ball",
                        "Energy Ball"
                };
                break;

            case "Charizard":
                moves = new String[]{
                        "Flamethrower",
                        "Air Slash",
                        "Dragon Claw",
                        "Thunder Punch"
                };
                break;

            case "Lucario":
                moves = new String[]{
                        "Aura Sphere",
                        "Flash Cannon",
                        "Shadow Claw",
                        "Extreme Speed"
                };
                break;

            case "Gengar":
                moves = new String[]{
                        "Shadow Ball",
                        "Sludge Bomb",
                        "Dark Pulse",
                        "Thunderbolt"
                };
                break;

            case "Dragonite":
                moves = new String[]{
                        "Dragon Claw",
                        "Dragon Rush",
                        "Aerial Ace",
                        "Thunder Punch"
                };
                break;

            case "Milotic":
                moves = new String[]{
                        "Surf",
                        "Ice Beam",
                        "Aqua Tail",
                        "Mirror Coat"
                };
                break;

            default:
                moves = new String[]{
                        "Move 1",
                        "Move 2",
                        "Move 3",
                        "Move 4"
                };
        }

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

                int currentEnemyHP = enemyHPBar.getValue();

                int newHP = Math.max(
                        0,
                        currentEnemyHP - 25
                );

                enemyHPBar.setValue(newHP);
                enemyHPBar.setString(
                        "HP " + newHP + " / 100"
                );
                if (newHP <= 25) {
                    enemyHPBar.setForeground(Color.RED);
                } else if (newHP <= 50) {
                    enemyHPBar.setForeground(Color.YELLOW);
                } else {
                    enemyHPBar.setForeground(new Color(60, 180, 75));
                }
                if (newHP > 0) {

                    int currentPlayerHP = playerHPBar.getValue();

                    int newPlayerHP = Math.max(
                            0,
                            currentPlayerHP - 15
                    );

                    playerHPBar.setValue(newPlayerHP);
                    playerHPBar.setString(
                            "HP " + newPlayerHP + " / 100"
                    );

                    if (newPlayerHP <= 25) {
                        playerHPBar.setForeground(Color.RED);
                    } else if (newPlayerHP <= 50) {
                        playerHPBar.setForeground(Color.YELLOW);
                    } else {
                        playerHPBar.setForeground(
                                new Color(60, 180, 75)
                        );
                    }
                }
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
    private JLabel createTrainerSpriteLabel(String fileName) {

        JLabel label = new JLabel();

        ImageIcon icon = new ImageIcon(
                "assets/trainers/" + fileName
        );

        Image image = icon.getImage()
                .getScaledInstance(
                        120,
                        180,
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
                new GridLayout(3, 1)
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
                pokemonName + "  Lv. 50"
        );

        name.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        name.setBorder(
                BorderFactory.createEmptyBorder(
                        3, 10, 0, 10
                )
        );

        JLabel hpText = new JLabel(
                hp
        );

        hpText.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        hpText.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 10, 0, 10
                )
        );

        JProgressBar hpBar = new JProgressBar(
                0,
                100
        );

        if (pokemonName.equals(GameState.playerPokemon[0])) {
            playerHPBar = hpBar;
        } else {
            enemyHPBar = hpBar;
        }

        hpBar.setValue(100);
        hpBar.setStringPainted(true);
        hpBar.setString("HP 100 / 100");

        hpBar.setForeground(
                new Color(60, 180, 75)
        );

        hpBar.setBackground(
                new Color(80, 80, 80)
        );

        hpBar.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        1
                )
        );

        panel.add(name);
        panel.add(hpText);
        panel.add(hpBar);

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