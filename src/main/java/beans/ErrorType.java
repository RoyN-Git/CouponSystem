package beans;

public enum ErrorType {
    VALUE_CANNOT_BE_CHANGED("This value cannot be changed"),
    EMAIL_ALREADY_EXIST("This email is already taken, try different email"),
    NAME_ALREADY_EXIST("This name is already taken, try different name"),
    TITLE_ALREADY_EXIST("This coupon title is already taken, try different title"),
    CATEGORY_ALREADY_EXIST("This category is already exist"),
    COMPANY_NOT_EXIST("Company doesn't exist"),
    CUSTOMER_NOT_EXIST("Customer doesn't exist"),
    COUPON_NOT_EXIST("Coupon doesn't exist"),
    COUPON_AMOUNT_IS_ZERO("Coupon's amount is 0, cannot be purchased"),
    COUPON_EXPIRED("Coupon is expired, cannot be purchased"),
    COUPON_ALREADY_PURCHASED("Coupon was already purchased"),
    PURCHASE_FAILED("The purchase failed"),
    AUTHENTICATION_FAILED("Your email or password is wrong, please try again"),
    INVALID_INPUT("Invalid details, please try again");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

