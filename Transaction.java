
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; // "Income" or "Expense"
    private double amount;
    private String description;
    private LocalDate date;

    public Transaction(String type, double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public String toFileString() {
        return type + "," + amount + "," + description + "," + date;
    }

    public static Transaction fromFileString(String line) {
        String[] parts = line.split(",");
        return new Transaction(parts[0], Double.parseDouble(parts[1]), parts[2], LocalDate.parse(parts[3]));
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return type + " | " + amount + " | " + description + " | " + date.format(formatter);
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
}
