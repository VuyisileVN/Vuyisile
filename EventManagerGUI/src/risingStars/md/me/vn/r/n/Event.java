package risingStars.md.me.vn.r.n;

public class Event {
    // Attributes
    private String eventName;
    private String eventDate;
    private String category;
    
    // Constructor
    public Event(String eventName, String eventDate, String category) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.category = category;
    }
    
    // Getters
    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getCategory() {
        return category;
    }
    
    // Setters
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    // Helper Method: returns CSV formatted string representation of the event
    public String toCSV() {
        return eventName + "," + eventDate + "," + category;
    }
    
    // Overridden method to return a friendly string representation of the event
    @Override
    public String toString() {
        return "Name: " + eventName + ",\nDate: " + eventDate + ",\nCategory: " + category +"\n";
    }
    
    // Additional helper method: checks if this event belongs to a given category
    public boolean isCategory(String searchCategory) {
        return this.category.equalsIgnoreCase(searchCategory);
    }
}