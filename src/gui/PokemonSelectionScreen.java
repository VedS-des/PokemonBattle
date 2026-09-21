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

    public PokemonSelectionScreen() {
        setTitle("Choose Your Pokemon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "CHOOSE 3 POKEMON",
                SwingConstants.CENTER
        );
        title.setFont(title.getFont().deriveFont(30f));

        JPanel pokemonPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        for (String name : pokemon) {
            JButton button = new JButton(name);

            button.addActionListener(e -> {

                if (selectedPokemon.contains(name)) {
                    selectedPokemon.remove(name);
                    button.setText(name);
                }
                else if (selectedPokemon.size() < 3) {
                    selectedPokemon.add(name);
                    button.setText(name + " ✓");
                }
                else {
                    JOptionPane.showMessageDialog(
                            this,
                            "You can select only 3 Pokemon!"
                    );
                }
            });

            pokemonPanel.add(button);
        }

        JButton continueButton = new JButton("CONTINUE");

        continueButton.addActionListener(e -> {

            if (selectedPokemon.size() != 3) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select exactly 3 Pokemon!"
                );
                return;
            }

            GameState.playerPokemon = selectedPokemon.toArray(new String[0]);

            dispose();
            new BattleScreen();
        });

        add(title, BorderLayout.NORTH);
        add(pokemonPanel, BorderLayout.CENTER);
        add(continueButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}