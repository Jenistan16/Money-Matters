import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MoneyMattersApp {
    private User user;
    private List<Transaction> transactions;
    private FileManager fileManager;
    private FinancialReport report;
    private Scanner scanner;

    public MoneyMattersApp() {
        fileManager = new FileManager();
        report = new FinancialReport();
        scanner = new Scanner(System.in);
        loadData();
    }

    private void loadData() {
        user = fileManager.loadUser();
        transactions = fileManager.loadTransactions();

        if (user == null) {
            System.out.println("Welcome to Money Matters!");
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter initial balance: $");
            double initialBalance = scanner.nextDouble();
            scanner.nextLine();
            user = new User(username, initialBalance);
            fileManager.saveUser(user);
        } else {
            System.out.println("Welcome back, " + user.getUsername() + "!");
        }
    }

    private void saveData() {
        fileManager.saveUser(user);
        fileManager.saveTransactions(transactions);
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addExpense();
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    viewBalance();
                    break;
                case 5:
                    report.generateReport(transactions, user);
                    break;
                case 6:
                    report.generateMonthlyReport(transactions, user);
                    break;
                case 7:
                    deleteTransaction();
                    break;
                case 8:
                    System.out.println("Saving data and exiting... Goodbye!");
                    saveData();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           MONEY MATTERS - Main Menu");
        System.out.println("=".repeat(50));
        System.out.println("1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View Transaction History");
        System.out.println("4. View Current Balance");
        System.out.println("5. Generate Financial Report");
        System.out.println("6. Generate Monthly Report");
        System.out.println("7. Delete Transaction");
        System.out.println("8. Exit");
        System.out.println("=".repeat(50));
    }

    private void addIncome() {
        System.out.println("\n--- Add Income ---");
        System.out.print("Enter amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category (e.g., Salary, Freelance, Investment): ");
        String category = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        String id = UUID.randomUUID().toString().substring(0, 8);
        Transaction transaction = new Transaction(id, "INCOME", amount, category, description);
        transactions.add(transaction);
        user.addIncome(amount);

        System.out.printf("Income of $%.2f added successfully!\n", amount);
        System.out.printf("New Balance: $%.2f\n", user.getBalance());
        saveData();
    }

    private void addExpense() {
        System.out.println("\n--- Add Expense ---");
        System.out.print("Enter amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (!user.deductExpense(amount)) {
            System.out.println("Insufficient balance! Transaction cancelled.");
            return;
        }

        System.out.print("Enter category (e.g., Food, Transport, Entertainment): ");
        String category = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        String id = UUID.randomUUID().toString().substring(0, 8);
        Transaction transaction = new Transaction(id, "EXPENSE", amount, category, description);
        transactions.add(transaction);

        System.out.printf("Expense of $%.2f recorded successfully!\n", amount);
        System.out.printf("New Balance: $%.2f\n", user.getBalance());
        saveData();
    }

    private void viewTransactions() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    TRANSACTION HISTORY");
        System.out.println("=".repeat(70));

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
                System.out.println("-".repeat(70));
            }
        }
    }

    private void viewBalance() {
        System.out.println("\n" + "=".repeat(50));
        System.out.printf("Current Balance: $%.2f\n", user.getBalance());
        System.out.println("=".repeat(50));
    }

    private void deleteTransaction() {
        viewTransactions();
        if (transactions.isEmpty()) return;

        System.out.print("\nEnter transaction ID to delete: ");
        String id = scanner.nextLine();

        Transaction toDelete = null;
        for (Transaction t : transactions) {
            if (t.getId().equals(id)) {
                toDelete = t;
                break;
            }
        }

        if (toDelete != null) {
            if (toDelete.getType().equalsIgnoreCase("INCOME")) {
                user.setBalance(user.getBalance() - toDelete.getAmount());
            } else {
                user.setBalance(user.getBalance() + toDelete.getAmount());
            }
            transactions.remove(toDelete);
            System.out.println("Transaction deleted successfully!");
            saveData();
        } else {
            System.out.println("Transaction ID not found!");
        }
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static void main(String[] args) {
        MoneyMattersApp app = new MoneyMattersApp();
        app.run();
    }
}