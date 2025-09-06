
package crecheapp;


import javax.swing.SwingUtilities;

public class CrecheApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CrecheGUI().setVisible(true);
        });
    }
    
}
