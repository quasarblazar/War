import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
class Player {
    private final String name;
    // We use a LinkedList because it acts like a real deck of cards.
    // We can pull from the top (first) and add to the bottom (last).
    private final LinkedList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public String getName() { return name; }

    // Adds a single card to the bottom of the player's stack
    public void addCardToBottom(Card card) {
        hand.addLast(card);
    }

    // Adds a pile of won cards to the bottom of the stack
    public void addCardsToBottom(List<Card> wonCards) {
        hand.addAll(wonCards);
    }

    // Removes and returns the top card. Returns null if out of cards.
    public Card drawTopCard() {
        return hand.isEmpty() ? null : hand.pollFirst();
    }

    public int getCardCount() {
        return hand.size();
    }

    public boolean hasLost() {
        return hand.isEmpty();
    }

    public void clearHand() {
        hand.clear();
    }
}
