import java.time.LocalDate;

/**
 * Transaction.java
 * Represents a financial transaction.
 */
public class Transaction {
    private int transactionId;
    private double amount;
    private LocalDate date;
    private String description;
    private Category category;
    private User user;

    public Transaction(int transactionId, double amount, LocalDate date, String description, Category category, User user) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.isBlank()) {
            this.description = description;
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return date + " | " + category.getName() + " | $" + amount + " | " + description;
    }
}
