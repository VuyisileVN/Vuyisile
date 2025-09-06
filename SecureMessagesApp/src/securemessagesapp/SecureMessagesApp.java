
package securemessagesapp;

import securemessages.SecureMessagesFrame;

public class SecureMessagesApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new SecureMessagesFrame().setVisible(true);
        });
    }
}

