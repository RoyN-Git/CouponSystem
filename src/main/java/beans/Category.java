package beans;

public enum Category {
    FOOD("Food"),
    ELECTRICITY("Electricity"),
    RESTAURANT("Restaurant"),
    VACATION("Vacation");

    private final String name;

    /**
     * Full constructor
     * @param name is the given name for the category
     */
    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Static method used to return a category based on its value.
     * @param value is the value of the category we want to return
     * @return category if there is a category for the value, null if not
     */
    public static Category getCategoryByValue(int value) {
        for (Category item:Category.values()) {
            if(item.value==value){
                return item;
            }
        }
        return null;
    }


    public final int value = 1 + ordinal();
}
