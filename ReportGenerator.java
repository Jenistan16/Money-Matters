
import java.util.List;

public class ReportGenerator {
    public static void generateReport(List<Transaction> transactions) {
        double totalIncome = 0, totalExpense = 0;

        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("Income")) totalIncome += t.getAmount();
            else totalExpense += t.getAmount();
        }

        System.out.println("\n--- Financial Report ---");
        System.out.println("Total Income  : ₹" + totalIncome);
        System.out.println("Total Expense : ₹" + totalExpense);
        System.out.println("Net Balance   : ₹" + (totalIncome - totalExpense));
    }
}
