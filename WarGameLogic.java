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
    
    public Card currentP1Card;
    public Card currentP2Card;
    public String statusMessage;
    public boolean gameOver;
    public int warLootSize; 
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
        statusMessage = "Game Start";
        gameOver = false;
        warLootSize = 0;
    }

    public void playTurn() {
        if (gameOver) return;

        List<Card> loot = new ArrayList<>();
        warLootSize = 0;
        executeBattle(loot, false);
    }

    private void executeBattle(List<Card> loot, boolean isWar) {
        if (checkGameOver()) return;

        currentP1Card = p1.drawTopCard();
        currentP2Card = p2.drawTopCard();
        
        if (currentP1Card != null) loot.add(currentP1Card);
        if (currentP2Card != null) loot.add(currentP2Card);

        if (currentP1Card == null || currentP2Card == null) {
            checkGameOver();
            return;
        }

        warLootSize = loot.size();

        if (currentP1Card.getValue() > currentP2Card.getValue()) {
            p1.addCardsToBottom(loot);
            statusMessage = (isWar ? "" : "") + p1.getName() + " wins the round";
        } 
        else if (currentP2Card.getValue() > currentP1Card.getValue()) {
            p2.addCardsToBottom(loot);
            statusMessage = (isWar ? " " : "") + p2.getName() + " wins the round";
        } 
        else {
            handleWar(loot);
        }
        
        checkGameOver();
    }

    private void handleWar(List<Card> loot) {
        statusMessage = "Both players drew " + currentP1Card.getRank() + "";
        
        for (int i = 0; i < 3; i++) {
            if (p1.getCardCount() > 1) loot.add(p1.drawTopCard());
            if (p2.getCardCount() > 1) loot.add(p2.drawTopCard());
        }
        
        warLootSize = loot.size();

 
        executeBattle(loot, true); 
    }

    private boolean checkGameOver() {
        if (p1.hasLost()) {
            statusMessage = p2.getName() + " WINS ";
            gameOver = true;
            return true;
        } else if (p2.hasLost()) {
            statusMessage = p1.getName() + " WINS ";
            gameOver = true;
            return true;
        }
        return false;
    }
}
