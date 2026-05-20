import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WarGameApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WarGUI gui = new WarGUI();
            gui.setVisible(true);
        });
    }
}
