package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import main.GameState;

public class PokemonSelectionScreen extends JFrame {

    private final List<String> selectedPokemon = new ArrayList<>();
    private JLabel selectedLabel;

    private final String[] pokemon = {
            "Gardevoir",
            "Charizard",
            "Lucario",
            "Gengar",
            "Dragonite",
            "Milotic"
    };

    private final String[] types = {
            "Psychic / Fairy",
            "Fire / Flying",
            "Fighting / Steel",
            "Ghost / Poison",
            "Dragon / Flying",
            "Water"
    };

    public PokemonSelectionScreen() {

        setTitle("Choose Your Pokemon");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(new Color(20, 25, 45));

        // =========================
        // TITLE
        // =========================

        JLabel headingLabel = new JLabel(
                "CHOOSE 3 POKEMON",
                SwingConstants.CENTER
        );

        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(
                new Font("Arial", Font.BOLD, 36)
        );

        headingLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 0, 10, 0
                )
        );

        background.add(
                headingLabel,
                BorderLayout.NORTH
        );

        // =========================
        // POKEMON CARDS
        // =========================

        JPanel pokemonPanel = new JPanel(
                new GridLayout(2, 3, 15, 15)
        );

        pokemonPanel.setOpaque(false);

        pokemonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 35, 10, 35
                )
        );

        for (int i = 0; i < pokemon.length; i++) {

            JPanel card = createPokemonCard(
                    pokemon[i],
                    types[i]
            );

            pokemonPanel.add(card);
        }

        background.add(
                pokemonPanel,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM PANEL
        // =========================

        JPanel bottomPanel = new JPanel(
                new BorderLayout()
        );

        bottomPanel.setOpaque(false);

        selectedLabel = new JLabel();
        selectedLabel.setText("Selected: 0 / 3");
        selectedLabel.setHorizontalAlignment(SwingConstants.CENTER);


        selectedLabel.setForeground(Color.LIGHT_GRAY);
        selectedLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        JButton continueButton = new JButton(
                "CONTINUE"
        );

        continueButton.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        continueButton.setFocusPainted(false);

        continueButton.addActionListener(e -> {

            audio.SoundManager.playSound("click.wav");

            if (selectedPokemon.size() != 3) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select exactly 3 Pokemon!"
                );

                return;
            }

            GameState.playerPokemon =
                    selectedPokemon.toArray(
                            new String[0]
                    );

            dispose();

            new BattleScreen();
        });

        bottomPanel.add(
                selectedLabel,
                BorderLayout.CENTER
        );

        bottomPanel.add(
                continueButton,
                BorderLayout.EAST
        );

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 30, 15, 30
                )
        );

        background.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(background);

        setVisible(true);
    }

    // =========================
    // CREATE POKEMON CARD
    // =========================

    private JPanel createPokemonCard(
            String pokemonName,
            String pokemonType
    ) {

        JPanel card = new JPanel(
                new BorderLayout()
        );

        card.setBackground(
                new Color(45, 55, 85)
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 130, 160),
                        2
                )
        );

        // =========================
        // SPRITE
        // =========================

        JLabel spriteLabel = new JLabel();
        spriteLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(
                "assets/pokemon/"
                        + pokemonName
                        + "_front.png"
        );

        Image image = icon.getImage()
                .getScaledInstance(
                        120,
                        120,
                        Image.SCALE_SMOOTH
                );

        spriteLabel.setIcon(
                new ImageIcon(image)
        );

        card.add(
                spriteLabel,
                BorderLayout.CENTER
        );

        // =========================
        // INFORMATION
        // =========================

        JLabel infoLabel = new JLabel(
                "<html><center>"
                        + "<b>"
                        + pokemonName
                        + "</b>"
                        + "<br>"
                        + pokemonType
                        + "</center></html>"
        );

        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoLabel.setForeground(Color.WHITE);

        infoLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        infoLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 5, 5, 5
                )
        );

        card.add(
                infoLabel,
                BorderLayout.SOUTH
        );

        // =========================
        // CLICK SELECTION
        // =========================

        card.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        audio.SoundManager.playSound("click.wav");

                        if (selectedPokemon.contains(
                                pokemonName
                        )) {

                            selectedPokemon.remove(
                                    pokemonName
                            );

                            card.setBackground(
                                    new Color(45, 55, 85)

                            );

                            infoLabel.setText(
                                    "<html><center>"
                                            + "<b>"
                                            + pokemonName
                                            + "</b>"
                                            + "<br>"
                                            + pokemonType
                                            + "</center></html>"
                            );

                        } else if (
                                selectedPokemon.size() < 3
                        ) {

                            selectedPokemon.add(
                                    pokemonName
                            );

                            card.setBackground(
                                    new Color(65, 95, 130)

                            );

                            infoLabel.setText(
                                    "<html><center>"
                                            + "<b>✓ "
                                            + pokemonName
                                            + "</b>"
                                            + "<br>"
                                            + pokemonType
                                            + "<br>"
                                            + "SELECTED"
                                            + "</center></html>"
                            );

                        } else {

                            JOptionPane.showMessageDialog(
                                    PokemonSelectionScreen.this,
                                    "You can select only 3 Pokemon!"
                            );
                        }

                        updateSelectedLabel();
                    }
                }
        );

        return card;
    }

    // =========================
    // UPDATE COUNTER
    // =========================

    private void updateSelectedLabel() {

        selectedLabel.setText(
                "Selected: "
                        + selectedPokemon.size()
                        + " / 3"
        );
    }
}