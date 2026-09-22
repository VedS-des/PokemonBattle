package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroScreen extends JFrame {

    public IntroScreen() {

        audio.SoundManager.playMusic("end.wav");

        setTitle("Pokemon Battle");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // =========================
        // MAIN BACKGROUND
        // =========================

        JPanel background = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                // Dark blue gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0,
                        new Color(8, 12, 35),
                        0, getHeight(),
                        new Color(35, 15, 55)
                );

                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Decorative circles
                g2.setColor(new Color(255, 255, 255, 20));
                g2.fillOval(-120, -100, 350, 350);
                g2.fillOval(700, 430, 350, 350);

                // Center glow
                g2.setColor(new Color(100, 70, 180, 25));
                g2.fillOval(170, 80, 560, 430);
            }
        };

        background.setLayout(null);


        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel(
                "<html><center><font color='white'>POKÉMON</font><br>" +
                        "<font color='#FFD83D'>BATTLE</font></center></html>",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 58));
        title.setBounds(150, 80, 600, 150);

        // Shadow behind title
        JLabel titleShadow = new JLabel(
                "<html><center>POKÉMON<br>BATTLE</center></html>",
                SwingConstants.CENTER
        );

        titleShadow.setForeground(new Color(0, 0, 0, 130));
        titleShadow.setFont(new Font("Arial", Font.BOLD, 58));
        titleShadow.setBounds(155, 85, 600, 150);


        // =========================
        // SUBTITLE
        // =========================

        JLabel subtitle = new JLabel(
                "A 3v3 Trainer Battle",
                SwingConstants.CENTER
        );

        subtitle.setForeground(new Color(210, 210, 230));
        subtitle.setFont(new Font("Arial", Font.PLAIN, 22));
        subtitle.setBounds(200, 235, 500, 40);


        // =========================
        // START BUTTON
        // =========================

        JButton startButton = new JButton("CLICK TO START") {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Button background
                if (getModel().isRollover()) {
                    g2.setColor(new Color(255, 190, 40));
                } else {
                    g2.setColor(new Color(230, 160, 25));
                }

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        25,
                        25
                );

                // Border
                g2.setColor(new Color(255, 220, 100));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(
                        1,
                        1,
                        getWidth() - 2,
                        getHeight() - 2,
                        25,
                        25
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        startButton.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        startButton.setForeground(Color.WHITE);

        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setOpaque(false);

        startButton.setBounds(320, 340, 260, 65);


        // =========================
        // BUTTON HOVER EFFECT
        // =========================

        startButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setFont(
                        new Font("Arial", Font.BOLD, 26)
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setFont(
                        new Font("Arial", Font.BOLD, 24)
                );
            }
        });


        // =========================
        // START GAME ACTION
        // =========================

        startButton.addActionListener(e -> {

            audio.SoundManager.stopMusic();

            audio.SoundManager.playSound("click.wav");

            Timer timer = new Timer(150, event -> {

                audio.SoundManager.playSound("pokemon.wav");

                dispose();

                new TrainerSelectionScreen();
            });

            timer.setRepeats(false);
            timer.start();
        });


        // =========================
        // FOOTER
        // =========================

        JLabel footer = new JLabel(
                "Choose your trainer • Build your team • Battle!",
                SwingConstants.CENTER
        );

        footer.setForeground(new Color(170, 170, 190));
        footer.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        footer.setBounds(200, 520, 500, 30);


        // =========================
        // ADD EVERYTHING
        // =========================

        background.add(titleShadow);
        background.add(title);
        background.add(subtitle);
        background.add(startButton);
        background.add(footer);

        add(background);

        setVisible(true);
    }
}