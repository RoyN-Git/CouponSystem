package facade;

import beans.Customer;
import dao.CompaniesDAO;
import dao.CouponsDAO;
import dao.CustomersDAO;

public abstract class ClientFacade {
    protected CouponsDAO couponsDAO;
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;


    public boolean login(String email, String password){
        return false;
    }
}
