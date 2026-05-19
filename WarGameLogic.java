import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
class WarGameLogic {
    private final Player p1;
    private final Player p2;
    
    // Variables exposed so Partner 2 (GUI) can read them and update the screen
    public Card currentP1Card;
    public Card currentP2Card;
    public String statusMessage;
    public boolean gameOver;
    public int warLootSize; // Keeps track of how many cards are at stake in a war

    public WarGameLogic(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        resetGame();
    }

    public void resetGame() {
        p1.clearHand();
        p2.clearHand();
        Deck deck = new Deck();
        deck.shuffle();
        deck.deal(p1, p2);
        
        currentP1Card = null;
        currentP2Card = null;
        statusMessage = "Game Started! Click Draw to play a turn.";
        gameOver = false;
        warLootSize = 0;
    }

    // Called when the user clicks the 'Draw' button
    public void playTurn() {
        if (gameOver) return;

        List<Card> loot = new ArrayList<>();
        warLootSize = 0;
        executeBattle(loot, false);
    }

    // Recursive method that handles normal turns and Ties (Wars)
    private void executeBattle(List<Card> loot, boolean isWar) {
        // 1. Check if anyone has lost before drawing
        if (checkGameOver()) return;

        // 2. Both players draw their top card
        currentP1Card = p1.drawTopCard();
        currentP2Card = p2.drawTopCard();
        
        // Add drawn cards to the "loot" pile (the cards the winner will take)
        if (currentP1Card != null) loot.add(currentP1Card);
        if (currentP2Card != null) loot.add(currentP2Card);

        // Safety check in case someone ran out of cards mid-draw
        if (currentP1Card == null || currentP2Card == null) {
            checkGameOver();
            return;
        }

        warLootSize = loot.size();

        // 3. Compare values
        if (currentP1Card.getValue() > currentP2Card.getValue()) {
            p1.addCardsToBottom(loot);
            statusMessage = (isWar ? "WAR WON! " : "") + p1.getName() + " wins the round!";
        } 
        else if (currentP2Card.getValue() > currentP1Card.getValue()) {
            p2.addCardsToBottom(loot);
            statusMessage = (isWar ? "WAR WON! " : "") + p2.getName() + " wins the round!";
        } 
        else {
            // TIE! IT IS TIME FOR WAR
            handleWar(loot);
        }
        
        // Final check to see if that turn ended the game
        checkGameOver();
    }

    private void handleWar(List<Card> loot) {
        statusMessage = "WAR! Both players drew " + currentP1Card.getRank() + "!";
        
        // In war, each player puts up to 3 cards face down into the loot pile
        for (int i = 0; i < 3; i++) {
            if (p1.getCardCount() > 1) loot.add(p1.drawTopCard());
            if (p2.getCardCount() > 1) loot.add(p2.drawTopCard());
        }
        
        warLootSize = loot.size();

        // The next click will resolve the war via executeBattle again.
        // For simplicity in this GUI version, we auto-resolve the war instantly.
        executeBattle(loot, true); 
    }

    private boolean checkGameOver() {
        if (p1.hasLost()) {
            statusMessage = p2.getName() + " WINS THE GAME!";
            gameOver = true;
            return true;
        } else if (p2.hasLost()) {
            statusMessage = p1.getName() + " WINS THE GAME!";
            gameOver = true;
            return true;
        }
        return false;
    }
}
