import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FinancialReport {
    public void generateReport(List<Transaction> transactions, User user) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    FINANCIAL REPORT");
        System.out.println("=".repeat(70));

        double totalIncome = 0;
        double totalExpense = 0;
        Map<String, Double> categoryExpenses = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("INCOME")) {
                totalIncome += t.getAmount();
            } else {
                totalExpense += t.getAmount();
                categoryExpenses.merge(t.getCategory(), t.getAmount(), Double::sum);
            }
        }

        System.out.printf("Current Balance: $%.2f\n", user.getBalance());
        System.out.printf("Total Income: $%.2f\n", totalIncome);
        System.out.printf("Total Expenses: $%.2f\n", totalExpense);
        System.out.printf("Net Savings: $%.2f\n", totalIncome - totalExpense);

        if (!categoryExpenses.isEmpty()) {
            System.out.println("\n--- Expenses by Category ---");
            categoryExpenses.forEach((category, amount) -> 
                System.out.printf("  %s: $%.2f (%.1f%%)\n", 
                    category, amount, (amount / totalExpense) * 100));
        }

        System.out.println("=".repeat(70) + "\n");
    }

    public void generateMonthlyReport(List<Transaction> transactions, User user) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                 MONTHLY FINANCIAL SUMMARY");
        System.out.println("=".repeat(70));

        int currentMonth = java.time.LocalDate.now().getMonthValue();
        double monthlyIncome = 0;
        double monthlyExpense = 0;

        for (Transaction t : transactions) {
            if (t.getDate().getMonthValue() == currentMonth) {
                if (t.getType().equalsIgnoreCase("INCOME")) {
                    monthlyIncome += t.getAmount();
                } else {
                    monthlyExpense += t.getAmount();
                }
            }
        }

        System.out.printf("Monthly Income: $%.2f\n", monthlyIncome);
        System.out.printf("Monthly Expenses: $%.2f\n", monthlyExpense);
        System.out.printf("Monthly Savings: $%.2f\n", monthlyIncome - monthlyExpense);
        System.out.println("=".repeat(70) + "\n");
    }
}