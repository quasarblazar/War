import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Card {
    private final String suit;   // Hearts, Diamonds, Clubs, Spades
    private final String rank;   // 2, 3... J, Q, K, A
    private final int value;     // 2-14 (Used to determine who wins the round)
    private final String symbol; // Unicode symbol (♥, ♦, ♣, ♠) for the GUI
    private final Color color;   // Red or Black

    public Card(String suit, String rank, int value, String symbol, Color color) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    // Getters allowing other classes to read the card's data
    public int getValue() { return value; }
    public String getRank() { return rank; }
    public String getSymbol() { return symbol; }
    public Color getColor() { return color; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
