package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import main.GameState;

public class PokemonSelectionScreen extends JFrame {

    private final List<String> selectedPokemon = new ArrayList<>();

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

        // Title
        JLabel title = new JLabel(
                "CHOOSE 3 POKEMON",
                SwingConstants.CENTER
        );

        title.setForeground(Color.WHITE);
        title.setFont(
                new Font("Arial", Font.BOLD, 36)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 0, 10, 0
                )
        );

        background.add(title, BorderLayout.NORTH);

        // Pokemon cards
        JPanel pokemonPanel = new JPanel(
                new GridLayout(2, 3, 20, 20)
        );

        pokemonPanel.setOpaque(false);

        pokemonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 50, 20, 50
                )
        );

        for (int i = 0; i < pokemon.length; i++) {

            JButton button = createPokemonButton(
                    pokemon[i],
                    types[i]
            );

            String name = pokemon[i];

            button.addActionListener(e -> {

                if (selectedPokemon.contains(name)) {

                    selectedPokemon.remove(name);

                    button.setText(
                            "<html><center>"
                                    + name
                                    + "<br><br>"
                                    + getType(name)
                                    + "</center></html>"
                    );

                    button.setBackground(
                            new Color(45, 55, 85)
                    );

                } else if (selectedPokemon.size() < 3) {

                    selectedPokemon.add(name);

                    button.setText(
                            "<html><center>"
                                    + name
                                    + "<br><br>"
                                    + getType(name)
                                    + "<br><br>"
                                    + "✓ SELECTED"
                                    + "</center></html>"
                    );

                    button.setBackground(
                            new Color(65, 95, 130)
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "You can select only 3 Pokemon!"
                    );
                }
            });

            pokemonPanel.add(button);
        }

        background.add(
                pokemonPanel,
                BorderLayout.CENTER
        );

        // Bottom panel
        JPanel bottomPanel = new JPanel(
                new BorderLayout()
        );

        bottomPanel.setOpaque(false);

        JLabel selectedLabel = new JLabel(
                "Select exactly 3 Pokemon",
                SwingConstants.CENTER
        );

        selectedLabel.setForeground(
                Color.LIGHT_GRAY
        );

        selectedLabel.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        JButton continueButton = new JButton(
                "CONTINUE"
        );

        continueButton.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        continueButton.setFocusPainted(false);

        continueButton.addActionListener(e -> {

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
                        10, 30, 20, 30
                )
        );

        background.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(background);

        setVisible(true);
    }

    private JButton createPokemonButton(
            String name,
            String type
    ) {

        JButton button = new JButton(
                "<html><center>"
                        + name
                        + "<br><br>"
                        + type
                        + "</center></html>"
        );

        button.setFont(
                new Font("Arial", Font.BOLD, 20)
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

    private String getType(String name) {

        for (int i = 0; i < pokemon.length; i++) {

            if (pokemon[i].equals(name)) {
                return types[i];
            }
        }

        return "";
    }
}