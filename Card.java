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
    private final String suit;   
    private final String rank; 
    private final int value;     
    private final String symbol; 
    private final Color color;   

    public Card(String suit, String rank, int value, String symbol, Color color) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    public int getValue() { return value; }
    public String getRank() { return rank; }
    public String getSymbol() { return symbol; }
    public Color getColor() { return color; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
