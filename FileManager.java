import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileManager {
    private static final String TRANSACTIONS_FILE = "transactions.dat";
    private static final String USER_FILE = "user.dat";

    public void saveTransactions(List<Transaction> transactions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> loadTransactions() {
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTIONS_FILE))) {
            return (List<Transaction>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveUser(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public User loadUser() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading user: " + e.getMessage());
            return null;
        }
    }
}
