
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FinanceManager {
    private final String FILE_NAME = "transactions.txt";
    private List<Transaction> transactions = new ArrayList<>();

    public FinanceManager() {
        loadTransactions();
    }

    private void loadTransactions() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    private void saveTransaction(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(t.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public void addTransaction(String type, double amount, String description) {
        Transaction t = new Transaction(type, amount, description, LocalDate.now());
        transactions.add(t);
        saveTransaction(t);
        System.out.println("Transaction added!");
    }

    public void showHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n--- Transaction History ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public double getBalance() {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("Income")) income += t.getAmount();
            else expense += t.getAmount();
        }
        return income - expense;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}
