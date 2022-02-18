package beans;

public enum Category {
    FOOD("Food"),
    ELECTRICITY("Electricity"),
    RESTAURANT("Restaurant"),
    VACATION("Vacation");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }



    public final int value = 1 + ordinal();
}
