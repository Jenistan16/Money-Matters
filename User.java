import java.io.Serializable;

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private double balance;

    public User(String username, double initialBalance) {
        this.username = username;
        this.balance = initialBalance;
    }

    public String getUsername() { return username; }
    public double getBalance() { return balance; }

    public void addIncome(double amount) {
        balance += amount;
    }

    public boolean deductExpense(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
