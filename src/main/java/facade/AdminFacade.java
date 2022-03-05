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

    @Override
    public boolean login(String email, String password) {
        return DBmanager.ADMIN_EMAIL.equals(email)&&DBmanager.ADMIN_PASSWORD.equals(password);
    }

    public AdminFacade() {
        super();
    }

    public void addCompany(Company company){
        companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company){
        companiesDAO.updateCompany(company);
    }

    public void deleteCompany(int companyId){
        try {
            companiesDAO.deleteCompany(companyId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Company> getAllCompanies(){
        return this.companiesDAO.getAllCompanies(DBmanager.GET_ALL_COMPANIES,new HashMap<>());
    }

    public Company getOneCompany(int companyId){
        return this.companiesDAO.getOneCompany(companyId);
    }

    public void addCustomer(Customer customer){
        this.customersDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        this.customersDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId){
        this.customersDAO.deleteCustomer(customerId);
    }

    public List<Customer> getAllCustomers(){
        return  this.customersDAO.getAllCustomers(DBmanager.GET_ALL_CUSTOMERS, new HashMap<>());
    }

    public Customer getOneCustomer(int customerId){
        return this.customersDAO.getOneCustomer(customerId);
    }
}
