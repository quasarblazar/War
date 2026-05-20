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
    private final LinkedList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public String getName() { return name; }

    public void addCardToBottom(Card card) {
        hand.addLast(card);
    }

    public void addCardsToBottom(List<Card> wonCards) {
        hand.addAll(wonCards);
    }

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
