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

    public static Category getCategoryByValue(int value) {

        int option=(value-1)%Category.values().length;
        switch (option) {
            case 1:
                return FOOD;
            case 2:
                return ELECTRICITY;
            case 3:
                return RESTAURANT;
            case 4:
                return  VACATION;
        }
        return null;
    }


    public final int value = 1 + ordinal();
}
