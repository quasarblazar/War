import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class CardPanel extends JPanel {
    private Card card;
    private boolean isFaceDown;

    public CardPanel() {
        setPreferredSize(new Dimension(120, 180));
        setOpaque(false); 
        this.isFaceDown = true;
    }

    public void setCard(Card card) {
        this.card = card;
        this.isFaceDown = (card == null); 
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, width - 1, height - 1, 15, 15);

        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, width - 1, height - 1, 15, 15);

        if (isFaceDown) {
            g2.setColor(new Color(178, 34, 34)); 
            g2.fillRoundRect(5, 5, width - 11, height - 11, 10, 10);
            
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(1));
            for(int i = 10; i < width; i += 10) g2.drawLine(i, 5, i, height - 6);
            for(int i = 10; i < height; i += 10) g2.drawLine(5, i, width - 6, i);
            
        } else if (card != null) {
            g2.setColor(card.getColor());

            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString(card.getRank(), 10, 25);
            g2.drawString(card.getSymbol(), 10, 45);

            FontMetrics fm = g2.getFontMetrics();
            int rankWidth = fm.stringWidth(card.getRank());
            g2.drawString(card.getRank(), width - rankWidth - 10, height - 30);
            g2.drawString(card.getSymbol(), width - rankWidth - 10, height - 10);

            g2.setFont(new Font("Arial", Font.PLAIN, 60));
            FontMetrics fmBig = g2.getFontMetrics();
            int symWidth = fmBig.stringWidth(card.getSymbol());
            g2.drawString(card.getSymbol(), (width - symWidth) / 2, (height + fmBig.getAscent() / 2) / 2);
        }
    }
}
