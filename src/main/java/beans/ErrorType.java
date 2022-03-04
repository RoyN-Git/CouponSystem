package beans;

public enum ErrorType {
    /*
    DB_GET_ERROR("error fetching data from db", "general error. please try again in a few minutes"),//how to I use it?
    DB_SET_ERROR("error setting data to db", "general error. please try again in a few minutes"),//how to I use it?
    NAME_ALREADY_EXISTS("error creating new entity since the name chosen exists", "the name chosen is already exists"),//maybe we don't need it
    EMAIL_ALREADY_EXISTS("error creating new entity since the name chosen exists", "the name chosen is already exists"),//maybe we don't need it
    INVALID_UPDATE("error update entity. invalid field update requested", "the field cannot be updated"),
    COUPON_ALREADY_PURCHASED("customer already purchased the chosen coupon","you cannot purchase the same coupon more than one time"),
    OUT_OF_STOCK("the chosen coupon is out of stock", "you cannot purchase this coupon since it's out of stock"),
    EXPIRED("the chosen coupon is expired", "you cannot purchase this coupon since it's expired");



    //private String internalMessage;
    //private String externalMessage;

    private ErrorType(String internalMessage, String externalMessage) {
        this.internalMessage = internalMessage;
        this.externalMessage = externalMessage;
    }

    public String getInternalMessage() {
        return internalMessage;
    }

    public String getExternalMessage() {
        return externalMessage;
    }

     */
    VALUE_CANNOT_BE_CHANGED("This value cannot be changed"),
    EMAIL_ALREADY_EXIST("This email is already taken, try different name"),
    NAME_ALREADY_EXIST("This name is already taken, try different name"),
    TITLE_ALREADY_EXIST("This coupon title is already taken, try different title"),
    CATEGORY_ALREADY_EXIST("This category is already exist"),
    COMPANY_NOT_EXIST("Company doesn't exist"),
    CUSTOMER_NOT_EXIST("Customer doesn't exist"),
    COUPON_NOT_EXIST("Coupon doesn't exist"),
    COUPON_AMOUNT_IS_ZERO("Coupon's amount is 0, cannot be purchased"),
    COUPON_EXPIRED("Coupon is expired, cannot be purchased"),
    COUPON_ALREADY_PURCHASED("Coupon was already purchased"),
    PURCHASE_FAILED("The purchase failed");


    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

