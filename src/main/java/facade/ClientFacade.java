package facade;

import beans.Customer;
import dao.CompaniesDAO;
import dao.CouponsDAO;
import dao.CustomersDAO;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import dbdao.CustomerDBDAO;

public abstract class ClientFacade {
    protected CouponsDAO couponsDAO;
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;

    public ClientFacade() {
        this.couponsDAO = new CouponsDBDAO();
        this.companiesDAO = new CompaniesDBDAO();
        this.customersDAO = new CustomerDBDAO();
    }

    public abstract boolean login(String email, String password);
}
