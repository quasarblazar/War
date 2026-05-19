import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class WarGUI extends JFrame {
    
    private final WarGameLogic gameLogic;
    private final Player p1;
    private final Player p2;

    // UI Components
    private JLabel p2NameLabel, p1NameLabel;
    private JLabel p2CountLabel, p1CountLabel;
    private JLabel statusLabel;
    private CardPanel p1CardPanel;
    private CardPanel p2CardPanel;
    private JButton drawButton;

    public WarGUI() {
        // Initialize Partner 1's backend logic
        p1 = new Player("Player 1");
        p2 = new Player("Computer");
        gameLogic = new WarGameLogic(p1, p2);

        // Setup the Main Window
        setTitle("War: The Card Game");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        
        // A classic Casino Green background color
        JPanel mainContainer = new JPanel(new BorderLayout(10, 10));
        mainContainer.setBackground(new Color(34, 139, 34));
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(mainContainer);

        setupTopPanel();
        setupCenterPanel();
        setupBottomPanel();
        
        updateUI(); // Set initial text
    }

    // Top Panel: Holds Computer/Player 2's info
    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setOpaque(false);
        
        p2NameLabel = new JLabel(p2.getName(), SwingConstants.CENTER);
        p2NameLabel.setForeground(Color.WHITE);
        p2NameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        p2CountLabel = new JLabel("Cards: 26", SwingConstants.CENTER);
        p2CountLabel.setForeground(Color.LIGHT_GRAY);
        p2CountLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        topPanel.add(p2NameLabel);
        topPanel.add(p2CountLabel);
        add(topPanel, BorderLayout.NORTH);
    }

    // Center Panel: Holds the drawn cards and the battle text
    private void setupCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // P2's Card (Top)
        p2CardPanel = new CardPanel();
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(p2CardPanel, gbc);

        // Status / VS Label (Middle)
        statusLabel = new JLabel("VS", SwingConstants.CENTER);
        statusLabel.setForeground(Color.YELLOW);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(statusLabel, gbc);

        // P1's Card (Bottom)
        p1CardPanel = new CardPanel();
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(p1CardPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    // Bottom Panel: Holds Player 1's info and the interactable Draw Button
    private void setupBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        
        p1NameLabel = new JLabel(p1.getName(), SwingConstants.CENTER);
        p1NameLabel.setForeground(Color.WHITE);
        p1NameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        p1CountLabel = new JLabel("Cards: 26", SwingConstants.CENTER);
        p1CountLabel.setForeground(Color.LIGHT_GRAY);
        p1CountLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        infoPanel.add(p1NameLabel);
        infoPanel.add(p1CountLabel);

        drawButton = new JButton("DRAW CARDS");
        drawButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        drawButton.setBackground(Color.WHITE);
        drawButton.setFocusPainted(false);
        drawButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Connect the button to Partner 1's logic engine
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameLogic.gameOver) {
                    // Reset game
                    gameLogic.resetGame();
                    drawButton.setText("DRAW CARDS");
                } else {
                    // Play a turn
                    gameLogic.playTurn();
                }
                updateUI(); // Refresh the screen
            }
        });

        bottomPanel.add(infoPanel, BorderLayout.CENTER);
        bottomPanel.add(drawButton, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Reads variables from Partner 1's logic and updates Partner 2's labels/graphics
    private void updateUI() {
        p1CountLabel.setText("Cards: " + p1.getCardCount());
        p2CountLabel.setText("Cards: " + p2.getCardCount());
        
        // If there's a war, show how many cards are in the loot pile
        if (gameLogic.warLootSize > 2) {
             statusLabel.setText(gameLogic.statusMessage + " (" + gameLogic.warLootSize + " cards won)");
        } else {
             statusLabel.setText(gameLogic.statusMessage);
        }

        // Update card graphics
        p1CardPanel.setCard(gameLogic.currentP1Card);
        p2CardPanel.setCard(gameLogic.currentP2Card);

        if (gameLogic.gameOver) {
            drawButton.setText("PLAY AGAIN");
            // Set cards to null so they display as face-down card backs
            p1CardPanel.setCard(null);
            p2CardPanel.setCard(null);
        }
    }
}
