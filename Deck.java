import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    // Creates the 52 cards with their proper values, colors, and symbols
    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] symbols = {"♥", "♦", "♣", "♠"};
        Color[] colors = {Color.RED, Color.RED, Color.BLACK, Color.BLACK};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                // Values are j + 2 (so "2" is 2, "A" is 14)
                cards.add(new Card(suits[i], ranks[j], j + 2, symbols[i], colors[i]));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Splits the 52 cards evenly between two players
    public void deal(Player p1, Player p2) {
        for (int i = 0; i < cards.size(); i++) {
            if (i % 2 == 0) {
                p1.addCardToBottom(cards.get(i));
            } else {
                p2.addCardToBottom(cards.get(i));
            }
        }
    }
}
