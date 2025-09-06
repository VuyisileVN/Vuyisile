
package securemessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SecureMessagesFrame extends JFrame {
    private JTextArea plainTextArea;
    private JTextArea encryptedTextArea;

    public SecureMessagesFrame() {
        setTitle("Secure Messages");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        // Title
        JLabel titleLabel = new JLabel("Message Encryptor", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
        titleLabel.setForeground(Color.BLUE);

        // Text Areas
        plainTextArea = new JTextArea();
        encryptedTextArea = new JTextArea();
        plainTextArea.setLineWrap(true);
        encryptedTextArea.setLineWrap(true);

        JScrollPane scrollPlain = new JScrollPane(plainTextArea);
        JScrollPane scrollEncrypted = new JScrollPane(encryptedTextArea);

        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        textPanel.add(createLabeledPanel("Plain message", scrollPlain));
        textPanel.add(createLabeledPanel("Encrypted message", scrollEncrypted));

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open file...");
        JMenuItem encryptItem = new JMenuItem("Encrypt message...");
        JMenuItem saveItem = new JMenuItem("Save encrypted message...");
        JMenuItem clearItem = new JMenuItem("Clear");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(encryptItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(clearItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Add Components
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        // Actions
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(SecureMessagesFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        plainTextArea.read(reader, null);
                    } catch (IOException ex) {
                        showError("Error reading file: " + ex.getMessage());
                    }
                }
            }
        });

        encryptItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String plainText = plainTextArea.getText();
                if (!plainText.isEmpty()) {
                    Message message = new Message(plainText);
                    String encrypted = MessageEncryptor.encrypt(message.getContent());
                    encryptedTextArea.setText(encrypted);
                }
            }
        });

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showSaveDialog(SecureMessagesFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        encryptedTextArea.write(writer);
                    } catch (IOException ex) {
                        showError("Error saving file: " + ex.getMessage());
                    }
                }
            }
        });

        clearItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plainTextArea.setText("");
                encryptedTextArea.setText("");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        this.setVisible(true);
        
    }
    

    private JPanel createLabeledPanel(String title, JScrollPane scrollPane) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

