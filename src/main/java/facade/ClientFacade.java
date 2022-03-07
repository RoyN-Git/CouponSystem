package facade;

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

    /**
     * Empty constructor.
     * Initiates all daos.
     */
    public ClientFacade() {
        this.couponsDAO = new CouponsDBDAO();
        this.companiesDAO = new CompaniesDBDAO();
        this.customersDAO = new CustomerDBDAO();
    }

    /**
     * Abstract login method.
     * Each child class implements it differently
     * @param email is the email the user typed in.
     * @param password is the password the user typed in.
     * @return true if login succeed, false if not.
     */
    public abstract boolean login(String email, String password);
}
