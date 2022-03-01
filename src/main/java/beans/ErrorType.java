package beans;

public enum ErrorType {
    DB_GET_ERROR("error fetching data from db", "general error. please try again in a few minutes"),//how to I use it?
    DB_SET_ERROR("error setting data to db", "general error. please try again in a few minutes"),//how to I use it?
    NAME_ALREADY_EXISTS("error creating new entity since the name chosen exists", "the name chosen is already exists"),//maybe we don't need it
    EMAIL_ALREADY_EXISTS("error creating new entity since the name chosen exists", "the name chosen is already exists"),//maybe we don't need it
    INVALID_UPDATE("error update entity. invalid field update requested", "the field cannot be updated"),
    COUPON_ALREADY_PURCHASED("customer already purchased the chosen coupon","you cannot purchase the same coupon more than one time"),
    OUT_OF_STOCK("the chosen coupon is out of stock", "you cannot purchase this coupon since it's out of stock"),
    EXPIRED("the chosen coupon is expired", "you cannot purchase this coupon since it's expired");

    private String internalMessage;
    private String externalMessage;

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

}


