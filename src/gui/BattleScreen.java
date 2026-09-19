package gui;

import javax.swing.*;
import main.GameState;

public class BattleScreen extends JFrame {

    public BattleScreen() {

        setTitle("Pokemon Battle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "BATTLE SCREEN",
                SwingConstants.CENTER
        );
        title.setFont(title.getFont().deriveFont(30f));

        JPanel pokemonPanel = new JPanel();

        for (String pokemon : GameState.playerPokemon) {
            pokemonPanel.add(new JLabel(pokemon));
        }

        add(title, "North");
        add(pokemonPanel, "Center");

        setVisible(true);
    }
}