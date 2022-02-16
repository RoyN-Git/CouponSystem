package beans;

public enum Category {
    FOOD,
    ELECTRICITY,
    RESTAURANT,
    VACATION;

    public final int value = 1 + ordinal();
}
