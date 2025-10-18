/**
 * User.java
 * Represents a user of the Personal Budget Tracker system.
 */
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;

    public User(int userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && !username.isBlank()) {
            this.username = username;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        }
    }

    public boolean validateLogin(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Email: " + email;
    }
}
