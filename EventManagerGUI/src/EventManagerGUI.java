
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import risingStars.md.me.vn.r.n.Event;  // Import the Event class
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class EventManagerGUI {

    // Define the text file for storing events
    private final File eventFile = new File("events.txt");
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventManagerGUI().createAndShowGUI());
    }
    
    public void createAndShowGUI() {
        // Create the main application frame
        JFrame frame = new JFrame("Rising Stars Event Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        
        // --- Input Panel: Three text fields for event details ---
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();
        JLabel dateLabel = new JLabel("Event Date:");
        JTextField dateField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();
        
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryField);
        
        // --- Button Panel: Four buttons for file operations ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton   = new JButton("Save Event");
        JButton loadButton   = new JButton("Load Events");
        JButton editButton   = new JButton("Edit Event");
        JButton deleteButton = new JButton("Delete Event");
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        // --- Text Area: For displaying event data ---
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Top Panel holds input and buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        ////////////////////// Action Listeners //////////////////////
        
        // Save button: Append a new event to the file
        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String date = dateField.getText().trim();
            String category = categoryField.getText().trim();
            
            if(name.isEmpty() || date.isEmpty() || category.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                return;
            }else{
                // Create an Event instance using the input data
                  Event event = new Event(name, date, category);
                  try (FileWriter writer = new FileWriter(eventFile, true)) {
                    writer.write(event.toCSV() + "\n");
                    writer.flush();
                    JOptionPane.showMessageDialog(frame, "Event saved successfully!");
                
                // Clear the input fields
                    nameField.setText("");
                    dateField.setText("");
                    categoryField.setText("");
                 } catch (IOException ioEx) {
                   JOptionPane.showMessageDialog(frame, "Error saving event: " + ioEx.getMessage());
                 }
            
            }
        });
        
        // Load button: Read and display events from the file
        loadButton.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(eventFile))) {
                textArea.setText(""); // Clear previous text
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        Event event = new Event(parts[0], parts[1], parts[2]);
                        textArea.append(event.toString() + "\n");
                    }
                }
            } catch (IOException ioEx) {
                JOptionPane.showMessageDialog(frame, "Error loading events: " + ioEx.getMessage());
            }
        });
        
        // Edit button: Update an existing event using the event name as a key
        editButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String newDate = dateField.getText().trim();
            String newCategory = categoryField.getText().trim();
            
            if(name.isEmpty() || newDate.isEmpty() || newCategory.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please fill all fields for editing.");
                return;
            }
            
            ArrayList<String> eventLines = new ArrayList<>();
            boolean found = false;
            
            // Read all events from the file into memory
            try (BufferedReader reader = new BufferedReader(new FileReader(eventFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3 && parts[0].trim().equalsIgnoreCase(name)) {
                        // Create an updated event (keep original event name)
                        Event updatedEvent = new Event(name, newDate, newCategory);
                        eventLines.add(updatedEvent.toCSV());
                        found = true;
                    } else {
                        eventLines.add(line);
                    }
                }
            } catch (IOException ioEx) {
                JOptionPane.showMessageDialog(frame, "Error reading events for editing: " + ioEx.getMessage());
                return;
            }
            
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Event not found for editing.");
                return;
            }
            
            // Write the updated list of events to the file (overwrite mode)
            try (FileWriter writer = new FileWriter(eventFile, false)) {
                for (String eventLine : eventLines) {
                    writer.write(eventLine + "\n");
                }
                writer.flush();
                JOptionPane.showMessageDialog(frame, "Event edited successfully!");
                
                // Clear the input fields after editing
                nameField.setText("");
                dateField.setText("");
                categoryField.setText("");
            } catch (IOException ioEx) {
                JOptionPane.showMessageDialog(frame, "Error saving edited event: " + ioEx.getMessage());
            }
        });
        
        // Delete button: Remove an event using the event name as a key
        deleteButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter an event name to delete.");
                return;
            }
            
            ArrayList<String> eventLines = new ArrayList<>();
            boolean found = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(eventFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3 && parts[0].trim().equalsIgnoreCase(name)) {
                        found = true;  // skip this event
                    } else {
                        eventLines.add(line);
                    }
                }
            } catch (IOException ioEx) {
                JOptionPane.showMessageDialog(frame, "Error reading events for deletion: " + ioEx.getMessage());
                return;
            }
            
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Event not found for deletion.");
                return;
            }
            
            // Overwrite the file with the remaining events
            try (FileWriter writer = new FileWriter(eventFile, false)) {
                for (String eventLine : eventLines) {
                    writer.write(eventLine + "\n");
                }
                writer.flush();
                JOptionPane.showMessageDialog(frame, "Event deleted successfully!");
                
                // Clear input fields after deletion
                nameField.setText("");
                dateField.setText("");
                categoryField.setText("");
            } catch (IOException ioEx) {
                JOptionPane.showMessageDialog(frame, "Error saving changes after deletion: " + ioEx.getMessage());
            }
        });
        
        // Finalize frame display
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}