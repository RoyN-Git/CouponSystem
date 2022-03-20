package facade;

import beans.Company;
import beans.Customer;
import db.DBmanager;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminFacade extends ClientFacade{

    /**
     * This method logs in as an admin by checking email and password.
     * The check is hard coded.
     * @param email is the email the user typed in.
     * @param password is the password the user typed in.
     * @return true if login succeed, false if not.
     */
    @Override
    public boolean login(String email, String password) {
        return DBmanager.ADMIN_EMAIL.equals(email)&&DBmanager.ADMIN_PASSWORD.equals(password);
    }

    /**
     * Empty constructor
     */
    public AdminFacade() {
        super();
    }

    /**
     * This method adds a new company to the database
     * @param company is the new company
     */
    public void addCompany(Company company){
        companiesDAO.addCompany(company);
    }

    /**
     * This method updates an existing company in the database.
     * @param company is the company we want to update.
     */
    public void updateCompany(Company company){
        companiesDAO.updateCompany(company);
    }

    /**
     * This method deletes a company from the database by its id
     * @param companyId is the id of the company we want to delete
     */
    public void deleteCompany(int companyId){
        companiesDAO.deleteCompany(companyId);
    }

    /**
     * This method returns a list of companies from the database
     * @return list of companies from the database.
     */
    public List<Company> getAllCompanies(){
        return this.companiesDAO.getAllCompanies(DBmanager.GET_ALL_COMPANIES,new HashMap<>());
    }

    /**
     * This method returns one company from the database based on its id.
     * @param companyId is the id of the company we want to get
     * @return the customer.
     */
    public Company getOneCompany(int companyId){
        return this.companiesDAO.getOneCompany(companyId);
    }

    /**
     * This method adds a single customer to the database.
     * @param customer is the customer we want to add.
     */
    public void addCustomer(Customer customer){
        this.customersDAO.addCustomer(customer);
    }

    /**
     * This method update a Customer to the database
     * @param customer is the customer we want to update.
     */
    public void updateCustomer(Customer customer){
        this.customersDAO.updateCustomer(customer);
    }

    /**
     * This method delete customer from the database based on its id
     * @param customerId is the id of the customer we want to delete from the database.
     */
    public void deleteCustomer(int customerId){
        this.customersDAO.deleteCustomer(customerId);
    }

    /**
     * This method returns a list of companies from the database
     * @return the list of companies from the database.
     */
    public List<Customer> getAllCustomers(){
        return  this.customersDAO.getAllCustomers(DBmanager.GET_ALL_CUSTOMERS, new HashMap<>());
    }

    /**
     *This method returns one customer from the database based on its id
     * @param customerId is the id of the customer we want to receive from the database.
     * @return the customer
     */
    public Customer getOneCustomer(int customerId){
        return this.customersDAO.getOneCustomer(customerId);
    }
}
