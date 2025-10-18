import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class BudgetTrackerApp extends Application {

    private BudgetManager manager = new BudgetManager();
    private User activeUser;

    private TextField txtUsername, txtEmail, txtPassword;
    private TextField txtAmount, txtDescription;
    private ComboBox<String> cmbCategoryType;
    private DatePicker dpDate;
    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Personal Budget Tracker");

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(createUserTab(), createTransactionTab(), createReportTab());

        Scene scene = new Scene(tabPane, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ----- USER TAB -----
    private Tab createUserTab() {
        Tab tab = new Tab("User", new VBox(10));
        VBox layout = (VBox) tab.getContent();
        layout.setPadding(new Insets(15));

        txtUsername = new TextField();
        txtEmail = new TextField();
        txtPassword = new TextField();
        Button btnCreateUser = new Button("Create User");

        btnCreateUser.setOnAction(e -> {
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            if (!email.contains("@")) {
                outputArea.setText("Invalid email format.");
                return;
            }

            activeUser = new User(1, username, email, password);
            manager.addUser(activeUser);
            outputArea.setText("User created successfully: " + username);
        });

        layout.getChildren().addAll(
                new Label("Username:"), txtUsername,
                new Label("Email:"), txtEmail,
                new Label("Password:"), txtPassword,
                btnCreateUser
        );
        tab.setClosable(false);
        return tab;
    }

    // ----- TRANSACTION TAB -----
    private Tab createTransactionTab() {
        Tab tab = new Tab("Transactions", new VBox(10));
        VBox layout = (VBox) tab.getContent();
        layout.setPadding(new Insets(15));

        txtAmount = new TextField();
        txtDescription = new TextField();
        dpDate = new DatePicker(LocalDate.now());
        cmbCategoryType = new ComboBox<>();
        cmbCategoryType.getItems().addAll("Expense", "Income");
        Button btnAddTransaction = new Button("Add Transaction");

        btnAddTransaction.setOnAction(e -> {
            if (activeUser == null) {
                outputArea.setText("Please create a user first.");
                return;
            }

            try {
                double amount = Double.parseDouble(txtAmount.getText());
                String desc = txtDescription.getText();
                String type = cmbCategoryType.getValue();

                if (type == null || desc.isEmpty()) {
                    outputArea.setText("Please fill all fields.");
                    return;
                }

                Category category = new Category(1, desc, type);
                Transaction t = new Transaction(
                        manager.getAllTransactions().size() + 1,
                        amount,
                        dpDate.getValue(),
                        desc,
                        category,
                        activeUser
                );
                manager.addTransaction(t);
                outputArea.setText("Transaction added: " + t.toString());
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid amount value.");
            }
        });

        layout.getChildren().addAll(
                new Label("Amount:"), txtAmount,
                new Label("Description:"), txtDescription,
                new Label("Date:"), dpDate,
                new Label("Category Type:"), cmbCategoryType,
                btnAddTransaction
        );
        tab.setClosable(false);
        return tab;
    }

    // ----- REPORT TAB -----
    private Tab createReportTab() {
        Tab tab = new Tab("Reports", new VBox(10));
        VBox layout = (VBox) tab.getContent();
        layout.setPadding(new Insets(15));

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(300);

        Button btnShowReport = new Button("Show Spending Report");
        btnShowReport.setOnAction(e -> {
            if (activeUser == null) {
                outputArea.setText("Please create a user first.");
                return;
            }

            double total = manager.calculateTotalSpending(activeUser);
            outputArea.setText("Total Spending for " + activeUser.getUsername() + ": $" + total +
                    "\n\nTransactions:\n");
            for (Transaction t : manager.getUserTransactions(activeUser)) {
                outputArea.appendText(t + "\n");
            }
        });

        layout.getChildren().addAll(btnShowReport, outputArea);
        tab.setClosable(false);
        return tab;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
