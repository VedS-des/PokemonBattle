package gui;

import javax.swing.*;

public class IntroScreen extends JFrame {

    public IntroScreen() {
        setTitle("Pokemon Battle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("POKEMON BATTLE", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(40f));

        JButton startButton = new JButton("CLICK TO START");

        startButton.addActionListener(e -> {
            dispose();
            new TrainerSelectionScreen();
        });

        add(title, "North");
        add(startButton, "Center");

        setVisible(true);
    }
}