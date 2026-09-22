package gui;

import javax.swing.*;
import java.awt.*;
import main.GameState;
import model.Trainer;
import data.TrainerDatabase;
import battle.BattleEngine;

public class BattleScreen extends JFrame {

    private JLabel messageLabel;
    private JProgressBar playerHPBar;
    private JProgressBar enemyHPBar;
    private JPanel partyPanel;
    private JPanel movePanel;
    private JLabel playerPokemonLabel;
    private JLabel enemyPokemonLabel;
    private JLabel playerNameLabel;
    private JLabel enemyNameLabel;
    private JLabel playerHPTextLabel;
    private JLabel enemyHPTextLabel;

    private int playerMaxHP;
    private int enemyMaxHP;
    private BattleEngine engine;
    private boolean animationRunning = false;
    private JPanel battleArea;

    public BattleScreen() {

        audio.SoundManager.stopSound();
        audio.SoundManager.playMusic("battle_music.wav");


        setTitle("Pokemon Battle");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        Trainer player =
                TrainerDatabase.createPlayerTrainerByNames(
                        GameState.playerTrainer,
                        GameState.playerPokemon
                );

        Trainer enemy =
                TrainerDatabase.getTrainer(
                        GameState.opponentTrainer
                );

        engine = new BattleEngine(player, enemy);

        model.Pokemon playerModel =
                player.getPokemon(0);

        model.Pokemon enemyModel =
                enemy.getPokemon(0);

        playerMaxHP = playerModel.getMaxHP();
        enemyMaxHP = enemyModel.getMaxHP();

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

        battleArea = new JPanel(null) {

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
                enemyMaxHP
        );

        enemyInfo.setBounds(620, 50, 300, 100);
        battleArea.add(enemyInfo);

        // =========================
        // PLAYER INFORMATION
        // =========================

        String playerName = GameState.playerPokemon[0];

        JPanel playerInfo = createInfoBox(
                playerName,
                playerMaxHP
        );

        playerInfo.setBounds(80, 440, 300, 100);
        battleArea.add(playerInfo);

        // =========================
        // ENEMY SPRITE
        // =========================

        enemyPokemonLabel = createSpriteLabel(
                getOpponentPokemon() + "_front.png"
        );

        // OPPONENT TRAINER SPRITE
        String opponentTrainerFile;

        if (GameState.opponentTrainer.equals("Red")) {
            opponentTrainerFile = "Red_front.png";
        } else if (GameState.opponentTrainer.equals("Steven")) {
            opponentTrainerFile = "Steven_front.png";
        } else {
            opponentTrainerFile = "Cynthia_front.png";
        }

        JLabel opponentTrainer = createTrainerSpriteLabel(
                opponentTrainerFile
        );

        opponentTrainer.setBounds(
                500,
                160,
                120,
                180
        );

        battleArea.add(opponentTrainer);

        enemyPokemonLabel.setBounds(
                650,
                150,
                220,
                220
        );

        battleArea.add(enemyPokemonLabel);

        // =========================
        // PLAYER SPRITE
        // =========================

        playerPokemonLabel = createSpriteLabel(
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
                70,
                270,
                120,
                180
        );

        battleArea.add(playerTrainer);

        int pokemonY = 220;

        if (playerName.equals("Gardevoir") ||
                playerName.equals("Gengar")) {
            pokemonY = 250;
        }

        playerPokemonLabel.setBounds(
                150,
                pokemonY,
                220,
                220
        );

        battleArea.add(playerPokemonLabel);

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

        movePanel = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        movePanel.setBackground(
                new Color(25, 30, 45)
        );

        updateMoveButtons();

        String[] moves;



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

    private void updateActivePokemon() {

        model.Pokemon currentPlayer =
                engine.getState().getPlayerPokemon();

        model.Pokemon currentEnemy =
                engine.getState().getEnemyPokemon();

        playerPokemonLabel.setIcon(
                new ImageIcon(
                        new ImageIcon(
                                "assets/pokemon/"
                                        + currentPlayer.getName()
                                        + "_back.png"
                        ).getImage().getScaledInstance(
                                220,
                                220,
                                Image.SCALE_SMOOTH
                        )
                )
        );

        enemyPokemonLabel.setIcon(
                new ImageIcon(
                        new ImageIcon(
                                "assets/pokemon/"
                                        + currentEnemy.getName()
                                        + "_front.png"
                        ).getImage().getScaledInstance(
                                220,
                                220,
                                Image.SCALE_SMOOTH
                        )
                )
        );
    }

    private void updateMoveButtons() {

        model.Pokemon currentPokemon =
                engine.getState().getPlayerPokemon();

        model.Move[] currentMoves =
                currentPokemon.getMoves();

        movePanel.removeAll();

        for (model.Move move : currentMoves) {

            System.out.println(
                    currentPokemon.getName()
                            + " MOVE = "
                            + move.getName()
                            + " | HEAL = "
                            + move.getHealAmount()
            );

            JButton moveButton =
                    new JButton(move.getName());

            moveButton.setFont(
                    new Font("Arial", Font.BOLD, 18)
            );

            moveButton.setFocusPainted(false);

            moveButton.addActionListener(e -> {

                audio.SoundManager.playSound("click.wav");

                if (animationRunning) {
                    return;
                }

                animationRunning = true;

                messageLabel.setText(
                        currentPokemon.getName()
                                + " used "
                                + move.getName()
                                + "!"
                );

                engine.prepareTurn(move);

                boolean playerFirst =
                        engine.doesPlayerAttackFirst();

                model.Move enemyMove =
                        engine.getPreparedEnemyMove();

                if (playerFirst) {

                    audio.SoundManager.playSound("attack.wav");

                    playMoveAnimation(
                            playerPokemonLabel,
                            enemyPokemonLabel,
                            () -> {

                                playMoveEffect(
                                        move.getName(),
                                        playerPokemonLabel,
                                        enemyPokemonLabel,
                                        () -> {

                                            model.Pokemon enemyBeforeMove =
                                                    engine.getState().getEnemyPokemon();

                                            engine.executeSingleMove(
                                                    engine.getState()
                                                            .getPlayerPokemon(),
                                                    enemyBeforeMove,
                                                    move
                                            );

                                            if (enemyBeforeMove.isFainted()) {

                                                if (engine.getState().getEnemy().isDefeated()) {

                                                    refreshBattleUI();
                                                    animationRunning = false;

                                                    audio.SoundManager.stopMusic();
                                                    audio.SoundManager.playSound("victory.wav");

                                                    new ResultScreen(
                                                            GameState.playerTrainer,
                                                            GameState.opponentTrainer,
                                                            true
                                                    );

                                                    return;
                                                }

                                                refreshBattleUI();
                                                animationRunning = false;
                                                return;
                                            }

                                            if (engine.getState()
                                                    .getEnemy()
                                                    .isDefeated()) {

                                                refreshBattleUI();

                                                animationRunning = false;

                                                audio.SoundManager.stopMusic();
                                                audio.SoundManager.playSound("victory.wav");

                                                new ResultScreen(
                                                        GameState.playerTrainer,
                                                        GameState.opponentTrainer,
                                                        true
                                                );

                                                return;
                                            }

                                            messageLabel.setText(
                                                    engine.getState()
                                                            .getEnemyPokemon()
                                                            .getName()
                                                            + " used "
                                                            + enemyMove.getName()
                                                            + "!"
                                            );

                                            audio.SoundManager.playSound("attack.wav");

                                            playMoveAnimation(
                                                    enemyPokemonLabel,
                                                    playerPokemonLabel,
                                                    () -> {

                                                        playMoveEffect(
                                                                enemyMove.getName(),
                                                                enemyPokemonLabel,
                                                                playerPokemonLabel,
                                                                () -> {

                                                                    engine.executeSingleMove(
                                                                            engine.getState()
                                                                                    .getEnemyPokemon(),
                                                                            engine.getState()
                                                                                    .getPlayerPokemon(),
                                                                            enemyMove
                                                                    );

                                                                    refreshBattleUI();
                                                                    animationRunning = false;
                                                                }
                                                        );
                                                    }
                                            );
                                        }
                                );
                            }
                    );

                } else {

                    messageLabel.setText(
                            engine.getState()
                                    .getEnemyPokemon()
                                    .getName()
                                    + " used "
                                    + enemyMove.getName()
                                    + "!"
                    );

                    playMoveAnimation(
                            enemyPokemonLabel,
                            playerPokemonLabel,
                            () -> {

                                playMoveEffect(
                                        enemyMove.getName(),
                                        enemyPokemonLabel,
                                        playerPokemonLabel,
                                        () -> {

                                            engine.executeSingleMove(
                                                    engine.getState()
                                                            .getEnemyPokemon(),
                                                    engine.getState()
                                                            .getPlayerPokemon(),
                                                    enemyMove
                                            );

                                            if (engine.getState()
                                                    .getPlayer()
                                                    .isDefeated()) {

                                                animationRunning = false;

                                                audio.SoundManager.stopMusic();
                                                audio.SoundManager.playSound("victory.wav");

                                                new ResultScreen(
                                                        GameState.playerTrainer,
                                                        GameState.opponentTrainer,
                                                        false
                                                );

                                                dispose();

                                                return;
                                            }

                                            messageLabel.setText(
                                                    engine.getState()
                                                            .getPlayerPokemon()
                                                            .getName()
                                                            + " used "
                                                            + move.getName()
                                                            + "!"
                                            );

                                            playMoveAnimation(
                                                    playerPokemonLabel,
                                                    enemyPokemonLabel,
                                                    () -> {

                                                        playMoveEffect(
                                                                move.getName(),
                                                                playerPokemonLabel,
                                                                enemyPokemonLabel,
                                                                () -> {

                                                                    engine.executeSingleMove(
                                                                            engine.getState()
                                                                                    .getPlayerPokemon(),
                                                                            engine.getState()
                                                                                    .getEnemyPokemon(),
                                                                            move
                                                                    );

                                                                    if (engine.getState()
                                                                            .getEnemy()
                                                                            .isDefeated()) {

                                                                        refreshBattleUI();
                                                                        animationRunning = false;

                                                                        new ResultScreen(
                                                                                GameState.playerTrainer,
                                                                                GameState.opponentTrainer,
                                                                                true
                                                                        );

                                                                        return;
                                                                    }

                                                                    System.out.println(
                                                                            "PLAYER DEFEATED = "
                                                                                    + engine.getState().getPlayer().isDefeated()
                                                                    );



                                                                    if (engine.getState()
                                                                            .getPlayer()
                                                                            .isDefeated()) {

                                                                        refreshBattleUI();

                                                                        animationRunning = false;

                                                                        new ResultScreen(
                                                                                GameState.playerTrainer,
                                                                                GameState.opponentTrainer,
                                                                                false
                                                                        );

                                                                        return;
                                                                    }

                                                                    refreshBattleUI();
                                                                    animationRunning = false;
                                                                }
                                                        );
                                                    }
                                            );
                                        }
                                );
                            }
                    );
                }
            });

            movePanel.add(moveButton);
        }

        movePanel.revalidate();
        movePanel.repaint();

        playerHPBar.revalidate();
        playerHPBar.repaint();

        enemyHPBar.revalidate();
        enemyHPBar.repaint();
    }

    private void refreshBattleUI() {

        updateActivePokemon();
        updateMoveButtons();

        model.Pokemon updatedPlayer =
                engine.getState().getPlayerPokemon();

        model.Pokemon updatedEnemy =
                engine.getState().getEnemyPokemon();

        playerNameLabel.setText(
                updatedPlayer.getName()
                        + "  Lv. 50"
        );

        enemyNameLabel.setText(
                updatedEnemy.getName()
                        + "  Lv. 50"
        );

        playerHPBar.setMaximum(
                updatedPlayer.getMaxHP()
        );

        playerHPBar.setValue(
                updatedPlayer.getCurrentHP()
        );

        playerHPBar.setString(
                "HP "
                        + updatedPlayer.getCurrentHP()
                        + " / "
                        + updatedPlayer.getMaxHP()
        );

        playerHPTextLabel.setText(
                "HP "
                        + updatedPlayer.getCurrentHP()
                        + " / "
                        + updatedPlayer.getMaxHP()
        );

        enemyHPBar.setMaximum(
                updatedEnemy.getMaxHP()
        );

        enemyHPBar.setValue(
                updatedEnemy.getCurrentHP()
        );

        enemyHPBar.setString(
                "HP "
                        + updatedEnemy.getCurrentHP()
                        + " / "
                        + updatedEnemy.getMaxHP()
        );
        enemyHPTextLabel.setText(
                "HP "
                        + updatedEnemy.getCurrentHP()
                        + " / "
                        + updatedEnemy.getMaxHP()
        );

        movePanel.revalidate();
        movePanel.repaint();
    }
    private void playMoveAnimation(
            JLabel attacker,
            JLabel defender,
            Runnable afterAnimation
    ) {

        int startX = attacker.getX();
        int startY = attacker.getY();

        int endX = defender.getX();
        int endY = defender.getY();

        int steps = 15;
        int delay = 20;

        Timer timer = new Timer(delay, null);

        final int[] currentStep = {0};

        timer.addActionListener(e -> {

            currentStep[0]++;

            double progress =
                    (double) currentStep[0] / steps;

            int newX =
                    (int) (startX +
                            (endX - startX) * progress);

            int newY =
                    (int) (startY +
                            (endY - startY) * progress);

            attacker.setLocation(newX, newY);

            if (currentStep[0] >= steps) {

                timer.stop();

                attacker.setLocation(
                        startX,
                        startY
                );

                afterAnimation.run();
            }
        });

        timer.start();
    }

    private void playMoveEffect(
            String moveName,
            JLabel attacker,
            JLabel defender,
            Runnable afterEffect
    ) {

        // Moves that only use the lunge
        if (moveName.equals("Extreme Speed")
                || moveName.equals("Mirror Coat")) {

            afterEffect.run();
            return;
        }

        // Special Earthquake animation
        if (moveName.equals("Earthquake")) {

            playEarthquakeEffect(
                    attacker,
                    defender,
                    afterEffect
            );

            return;
        }

        // Choose the sprite for the move
        String fileName = null;

        if (moveName.equals("Scratch")) {

            fileName = "Scratch.png";

        } else if (
                moveName.equals("Moonblast")
                        || moveName.equals("Psychic")
                        || moveName.equals("Air Slash")
                        || moveName.equals("Aerial Ace")
                        || moveName.equals("Dazzling Gleam")
        ) {

            fileName = "Moonblast.png";

        } else if (
                moveName.equals("Shadow Ball")
                        || moveName.equals("Sludge Bomb")
                        || moveName.equals("Dark Pulse")
                        || moveName.equals("Sucker Punch")
        ) {

            fileName = "Shadowball.png";

        } else if (
                moveName.equals("Dragon Claw")
        ) {

            fileName = "Scratch.png";

        } else if (
                moveName.equals("Dragon Rush")
        ) {

            fileName = "Scratch.png";

        } else if (
                moveName.equals("Thunderbolt")
                        || moveName.equals("Thunder Punch")
        ) {

            fileName = "Thunderbolt.png";

        } else if (
                moveName.equals("Energy Ball")
                        || moveName.equals("Giga Drain")
        ) {

            fileName = "Energyball.png";

        } else if (
                moveName.equals("Flamethrower")
                        || moveName.equals("Fire Fang")
        ) {

            fileName = "Flamethrower.png";

        } else if (
                moveName.equals("Ice Beam")
                        || moveName.equals("Surf")
                        || moveName.equals("Aqua Tail")
        ) {

            fileName = "Icebeam.png";

        } else if (
                moveName.equals("Aura Sphere")
                        || moveName.equals("Flash Cannon")
                        || moveName.equals("Body Slam")
                        || moveName.equals("Crunch")
                        || moveName.equals("Meteor Mash")
                        || moveName.equals("Bullet Punch")
        ) {

            fileName = "Punch.png";

        } else if (
                moveName.equals("Earthquake")
                        || moveName.equals("Earth Power")
                        || moveName.equals("Rock Slide")
                        || moveName.equals("Stone Edge")
                        || moveName.equals("Ancient Power")
        ) {

            fileName = "Earth.png";
        }

        // If no sprite exists for this move,
        // just continue with the battle.
        if (fileName == null) {

            afterEffect.run();
            return;
        }

        ImageIcon icon =
                new ImageIcon(
                        "assets/moves/" + fileName
                );

        Image scaledImage =
                icon.getImage().getScaledInstance(
                        60,
                        60,
                        Image.SCALE_SMOOTH
                );

        JLabel effect = new JLabel(
                new ImageIcon(scaledImage)
        );

        int startX =
                attacker.getX()
                        + attacker.getWidth() / 2;

        int startY =
                attacker.getY()
                        + attacker.getHeight() / 2;

        int endX =
                defender.getX()
                        + defender.getWidth() / 2;

        int endY =
                defender.getY()
                        + defender.getHeight() / 2;

        effect.setBounds(
                startX - 30,
                startY - 30,
                60,
                60
        );

        battleArea.add(effect);
        battleArea.repaint();

        Timer timer = new Timer(20, null);

        final int[] step = {0};

        int totalSteps = 20;

        timer.addActionListener(e -> {

            step[0]++;

            double progress =
                    (double) step[0] / totalSteps;

            int x =
                    (int) (
                            startX
                                    + (endX - startX)
                                    * progress
                    );

            int y =
                    (int) (
                            startY
                                    + (endY - startY)
                                    * progress
                    );

            effect.setLocation(
                    x - 30,
                    y - 30
            );

            if (step[0] >= totalSteps) {

                timer.stop();

                battleArea.remove(effect);
                battleArea.repaint();

                // Special flash for Flash Cannon
                // and Dazzling Gleam
                if (moveName.equals("Flash Cannon")
                        || moveName.equals("Dazzling Gleam")
                        || moveName.equals("Moonblast")) {

                    playFlashEffect(afterEffect);

                } else if (moveName.equals("Surf")) {

                    playBlueFlashEffect(afterEffect);

                } else {

                    afterEffect.run();
                }
            }
        });

        timer.start();
    }
    private void playEarthquakeEffect(
            JLabel attacker,
            JLabel defender,
            Runnable afterEffect
    ) {

        int originalAttackerX = attacker.getX();
        int originalDefenderX = defender.getX();

        Timer timer = new Timer(40, null);

        final int[] step = {0};

        timer.addActionListener(e -> {

            step[0]++;

            int offset;

            if (step[0] % 2 == 0) {
                offset = 8;
            } else {
                offset = -8;
            }

            attacker.setLocation(
                    originalAttackerX + offset,
                    attacker.getY()
            );

            defender.setLocation(
                    originalDefenderX - offset,
                    defender.getY()
            );

            if (step[0] >= 8) {

                timer.stop();

                attacker.setLocation(
                        originalAttackerX,
                        attacker.getY()
                );

                defender.setLocation(
                        originalDefenderX,
                        defender.getY()
                );

                afterEffect.run();
            }
        });

        timer.start();
    }
    private void playFlashEffect(Runnable afterEffect) {

        JPanel flashPanel = new JPanel();

        flashPanel.setBackground(Color.WHITE);

        flashPanel.setBounds(
                0,
                0,
                battleArea.getWidth(),
                battleArea.getHeight()
        );

        battleArea.add(flashPanel);
        battleArea.setComponentZOrder(flashPanel, 0);
        battleArea.repaint();

        Timer timer = new Timer(120, e -> {

            battleArea.remove(flashPanel);
            battleArea.repaint();

            ((Timer) e.getSource()).stop();

            afterEffect.run();
        });

        timer.setRepeats(false);
        timer.start();
    }
    private void playBlueFlashEffect(Runnable afterEffect) {

        JPanel bluePanel = new JPanel();

        bluePanel.setBackground(Color.BLUE);

        bluePanel.setBounds(
                0,
                0,
                battleArea.getWidth(),
                battleArea.getHeight()
        );

        battleArea.add(bluePanel);
        battleArea.setComponentZOrder(bluePanel, 0);
        battleArea.repaint();

        Timer timer = new Timer(120, e -> {

            battleArea.remove(bluePanel);
            battleArea.repaint();

            ((Timer) e.getSource()).stop();

            afterEffect.run();
        });

        timer.setRepeats(false);
        timer.start();
    }
    // ========================
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
            int hp
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

        if (pokemonName.equals(GameState.playerPokemon[0])) {
            playerNameLabel = name;
        } else {
            enemyNameLabel = name;
        }

        name.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        name.setBorder(
                BorderFactory.createEmptyBorder(
                        3, 10, 0, 10
                )
        );

        JLabel hpText =
                new JLabel(
                        "HP " + hp + " / " + hp
                );
        if (pokemonName.equals(GameState.playerPokemon[0])) {
            playerHPTextLabel = hpText;
        } else {
            enemyHPTextLabel = hpText;
        }

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
                hp
        );

        if (pokemonName.equals(GameState.playerPokemon[0])) {
            playerHPBar = hpBar;
        } else {
            enemyHPBar = hpBar;
        }

        hpBar.setValue(hp);
        hpBar.setStringPainted(true);
        hpBar.setString(
                "HP " + hp + " / " + hp
        );

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