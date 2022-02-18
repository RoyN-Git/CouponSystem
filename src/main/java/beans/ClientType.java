package beans;

public enum ClientType {
    ADMINISTRATOR("Administrator"),
    COMPANY("Company"),
    CUSTOMER("Customer");

    private final String clientType;

    ClientType(String type){
        this.clientType=type;
    }

    public final int value=1+ordinal();

    public String getClientType() {
        return clientType;
    }
}
