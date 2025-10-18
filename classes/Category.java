/**
 * Category.java
 * Represents an income or expense category.
 */
public class Category {
    private int categoryId;
    private String name;
    private String type; // "Income" or "Expense"

    public Category(int categoryId, String name, String type) {
        this.categoryId = categoryId;
        this.name = name;
        this.type = type;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equalsIgnoreCase("Income") || type.equalsIgnoreCase("Expense")) {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "Category: " + name + " (" + type + ")";
    }
}
