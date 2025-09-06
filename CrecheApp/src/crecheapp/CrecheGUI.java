
package crecheapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CrecheGUI extends JFrame{
    private JTextField txtName;
    private JRadioButton rbMale, rbFemale;
    private JButton btnRegister, btnDisplay;
    private JTextArea txtArea;
    private ArrayList<Child> kiddies;
    
    public CrecheGUI() {
        super("CRECHE 4 YOUR KIDDIE");

        kiddies = new ArrayList<>();

        
        JPanel panelTop = new JPanel(new GridLayout(3, 2));

        
        panelTop.add(new JLabel("Name:"));
        txtName = new JTextField(15);
        panelTop.add(txtName);

        
        panelTop.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        panelTop.add(genderPanel);

        
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnRegister = new JButton("Register kiddie");
        btnDisplay = new JButton("Display kiddies");
        panelButtons.add(btnRegister);
        panelButtons.add(btnDisplay);

        
        txtArea = new JTextArea(10, 30);
        txtArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtArea);

        
        add(panelTop, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        
        btnRegister.addActionListener(e -> registerKiddie());

        
        btnDisplay.addActionListener(e -> displayKiddies());

        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void registerKiddie() {
        String name = txtName.getText().trim();
        String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";

        if (name.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter name and select gender.");
            return;
        }

        kiddies.add(new Child(name, gender));
        txtName.setText("");
        rbMale.setSelected(false);
        rbFemale.setSelected(false);
    }

    private void displayKiddies() {
        txtArea.setText("");
        if (kiddies.isEmpty()) {
            txtArea.append("No kiddies registered yet.\n");
        } else {
            for (Child c : kiddies) {
                txtArea.append(c.toString() + "\n");
            }
        }
    }
}
