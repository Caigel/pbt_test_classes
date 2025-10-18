import java.util.ArrayList;

/**
 * BudgetManager.java
 * Manages users, transactions, and budgets.
 */
public class BudgetManager {
    private ArrayList<User> users;
    private ArrayList<Transaction> transactions;

    public BudgetManager() {
        users = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public double calculateTotalSpending(User user) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getUser().equals(user) && t.getCategory().getType().equalsIgnoreCase("Expense")) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public ArrayList<Transaction> getUserTransactions(User user) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getUser().equals(user)) {
                result.add(t);
            }
        }
        return result;
    }

    public void printAllTransactions(User user) {
        System.out.println("Transactions for " + user.getUsername() + ":");
        for (Transaction t : getUserTransactions(user)) {
            System.out.println(t);
        }
    }
}
