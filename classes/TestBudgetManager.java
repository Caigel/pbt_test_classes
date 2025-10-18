import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TestBudgetManager.java
 * 
 * Test driver for the core classes of the Personal Budget Tracker.
 * This class tests all fields and methods from:
 * - User.java
 * - Category.java
 * - Transaction.java
 * - BudgetManager.java
 * 
 * Note: These tests use simple conditional checks to simulate assertions.
 * Note: Expected value of all is passed
 */
public class TestBudgetManager {

    // Helper method for simulated assertions
    private static void assertEquals(Object expected, Object actual, String testName) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            System.out.println("  " + testName + " passed.");
        } else {
            System.out.println("  " + testName + " failed. Expected: " + expected + " | Got: " + actual);
        }
    }

    private static void assertTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println("  " + testName + " passed.");
        } else {
            System.out.println("  " + testName + " failed.");
        }
    }

    private static void assertFalse(boolean condition, String testName) {
        if (!condition) {
            System.out.println("  " + testName + " passed.");
        } else {
            System.out.println("  " + testName + " failed.");
        }
    }

    public static void main(String[] args) {
        System.out.println("PERSONAL BUDGET TRACKER TEST\n");

        // TEST 1: USER CLASS 
        System.out.println("---- Testing User class ----");
        User user = new User(1, "Caige", "caige@email.com", "pass123");

        // toString and field access
        assertEquals("Caige", user.getUsername(), "User getUsername()");
        assertTrue(user.getEmail().contains("@"), "User email format check");
        assertTrue(user.validateLogin("pass123"), "User password validation (correct)");
        assertFalse(user.validateLogin("wrongpass"), "User password validation (incorrect)");

        // Setter validation
        user.setUsername("CaigeL");
        assertEquals("CaigeL", user.getUsername(), "User setUsername() valid input");

        user.setEmail("invalidEmail");
        assertEquals("caige@email.com", user.getEmail(), "User setEmail() invalid input should not change");

        user.setEmail("new.email@email.com");
        assertEquals("new.email@email.com", user.getEmail(), "User setEmail() valid input should change");
        System.out.println();

        // TEST 2: CATEGORY CLASS 
        System.out.println("---- Testing Category class ----");
        Category groceries = new Category(1, "Groceries", "Expense");
        Category paycheck = new Category(2, "Paycheck", "Income");

        assertEquals("Groceries", groceries.getName(), "Category getName()");
        groceries.setName("Weekly Groceries");
        assertEquals("Weekly Groceries", groceries.getName(), "Category setName()");
        groceries.setType("InvalidType");
        assertEquals("Expense", groceries.getType(), "Category setType() rejects invalid type");
        groceries.setType("Expense");
        assertEquals("Expense", groceries.getType(), "Category setType() valid change");
        System.out.println();

        // TEST 3: TRANSACTION CLASS
        System.out.println("---- Testing Transaction class ----");
        Transaction t1 = new Transaction(1, 50.0, LocalDate.of(2025, 10, 1), "Walmart Groceries", groceries, user);
        Transaction t2 = new Transaction(2, 1200.0, LocalDate.of(2025, 10, 2), "Monthly Paycheck", paycheck, user);

        assertEquals(50.0, t1.getAmount(), "Transaction getAmount()");
        t1.setAmount(-5.0); // Invalid amount
        assertEquals(50.0, t1.getAmount(), "Transaction setAmount() rejects negative values");

        assertEquals("Walmart Groceries", t1.getDescription(), "Transaction getDescription()");
        t1.setDescription("");
        assertEquals("Walmart Groceries", t1.getDescription(), "Transaction setDescription() rejects empty input");

        assertEquals("Weekly Groceries", t1.getCategory().getName(), "Transaction getCategory()");
        assertEquals(user, t1.getUser(), "Transaction getUser()");
        System.out.println();

        // TEST 4: BUDGET MANAGER CLASS
        System.out.println("---- Testing BudgetManager class ----");
        BudgetManager manager = new BudgetManager();
        manager.addUser(user);
        manager.addTransaction(t1);
        manager.addTransaction(t2);

        ArrayList<Transaction> userTransactions = manager.getUserTransactions(user);
        assertEquals(2, userTransactions.size(), "BudgetManager getUserTransactions() count");

        double totalSpending = manager.calculateTotalSpending(user);
        // Only "Expense" categories should count
        assertEquals(50.0, totalSpending, "BudgetManager calculateTotalSpending()");

        System.out.println("\nTransactions for " + user.getUsername() + ":");
        manager.printAllTransactions(user);
        System.out.println();

        System.out.println("===== ALL TESTS COMPLETED =====");
    }
}
