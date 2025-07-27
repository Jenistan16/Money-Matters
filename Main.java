
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FinanceManager fm = new FinanceManager();

        while (true) {
            System.out.println("\n=== Money Matters App ===");
            System.out.println("1. Add Transaction");
            System.out.println("2. View History");
            System.out.println("3. Check Balance");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Type (Income/Expense): ");
                    String type = sc.nextLine();
                    System.out.print("Amount: ₹");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    fm.addTransaction(type, amount, desc);
                    break;

                case 2:
                    fm.showHistory();
                    break;

                case 3:
                    System.out.println("Current Balance: ₹" + fm.getBalance());
                    break;

                case 4:
                    ReportGenerator.generateReport(fm.getAllTransactions());
                    break;

                case 5:
                    System.out.println("Exiting. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
