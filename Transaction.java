import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String type;
    private double amount;
    private String category;
    private String description;
    private LocalDateTime date;

    public Transaction(String id, String type, double amount, String category, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("ID: %s | Type: %s | Amount: $%.2f | Category: %s | Date: %s | Description: %s",
                id, type, amount, category, date.format(formatter), description);
    }
}
