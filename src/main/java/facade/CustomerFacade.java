package facade;

import com.mysql.cj.xdevapi.Client;

public class CustomerFacade extends ClientFacade {
    private int customerId;

    public CustomerFacade(int customerId) {
        super();
        this.customerId = customerId;
    }
}
