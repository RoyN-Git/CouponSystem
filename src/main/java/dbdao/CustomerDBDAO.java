package dbdao;

import beans.Customer;
import beans.ErrorType;
import dao.CustomersDAO;
import db.DBUtils;
import db.DBmanager;
import exception.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDBDAO implements CustomersDAO {
    /**
     * This method checks if a company exist based on its email and password.
     * The method is used when trying to log in as a customer.
     * @param email is the customer's email
     * @param password is the customer's password
     * @return true if the login was successful, false if not
     */
    @Override
    public boolean isCustomerExists(String email, String password) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBUtils.runQueryForResult(DBmanager.LOGIN_CUSTOMER, values);
        try {
            assert resultSet != null;
            resultSet.next();
            if(resultSet.getInt("counter")==1) {
                return true;
            }else{
                throw new CouponSystemException(ErrorType.AUTHENTICATION_FAILED.getMessage());
            }
        } catch (SQLException |CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * This method adds new customer into the database.
     * The method first check if there is no other customer in the database with the sane email.
     * If not, adds the customer to the database.
     * @param customer is the customer we want to add to the database.
     */
    @Override
    public void addCustomer(Customer customer)  {
        Map<Integer, Object> values = new HashMap<>();
        //check constraints
        ResultSet resultSet;
        try {
            values.put(1, customer.getEmail());
            values.put(2, customer.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_CUSTOMER_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(customer.getEmail())) {
                    throw new CouponSystemException(ErrorType.EMAIL_ALREADY_EXIST.getMessage());
                }
            }
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
            return;
        }

        values.clear();
        values.put(1,customer.getFirstName());
        values.put(2,customer.getLastName());
        values.put(3,customer.getEmail());
        values.put(4,customer.getPassword());
        try {
            if(DBUtils.runQuery(DBmanager.CREATE_NEW_CUSTOMER, values)){
                System.out.println("Customer created successfully");
            }else{
                System.out.println("Customer creation failed");
                throw new SQLIntegrityConstraintViolationException();
            }
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method updates an existing customer in the database.
     * The method first check if there is no other customer in the database with the same email.
     * If not, it updates the customer
     * @param customer is the customer we want to update.
     */
    @Override
    public void updateCustomer(Customer customer){
        Map<Integer, Object> values = new HashMap<>();

        //check constraints
        ResultSet resultSet;
        try {
            values.put(1, customer.getEmail());
            values.put(2,customer.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_CUSTOMER_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(customer.getEmail())) {
                    throw new CouponSystemException(ErrorType.EMAIL_ALREADY_EXIST.getMessage());
                }
            }
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
            return;
        }


        values.clear();
        values.put(1,customer.getFirstName());
        values.put(2,customer.getLastName());
        values.put(3,customer.getEmail());
        values.put(4,customer.getPassword());
        values.put(5,customer.getId());
        try {
            if(DBUtils.runQuery(DBmanager.UPDATE_CUSTOMER_BY_ID, values)){
                System.out.println("Customer updated successfully");
            }else{
                System.out.println("Customer update failed");
                throw new SQLIntegrityConstraintViolationException();
            }
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deletes a customer based on id
     * @param customerId is the id of the customer we want to delete
     */
    @Override
    public void deleteCustomer(int customerId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        try {
            if(DBUtils.runQuery(DBmanager.DELETE_CUSTOMER_BY_ID, values)){
                System.out.println("Customer deleted successfully");
            }else{
                System.out.println("Customer deletion failed");
                throw new SQLException();
            }
        }catch (SQLException e){
            System.out.println(ErrorType.CUSTOMER_NOT_EXIST.getMessage());
        }

    }

    /**
     * This method receive a lit of all customers in the database.
     * @param sql is a sql query that receives all the customers.
     * @param values is a map of values to insert into the query.
     * @return a list of customers from the database.
     */
    @Override
    public List<Customer> getAllCustomers(String sql, Map<Integer, Object> values) {
        List<Customer> customers = new ArrayList<>();
        ResultSet resultSet = DBUtils.runQueryForResult(sql, values);
        try {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    /**
     * This method gets one customer from the database based on its id
     * @param customerId is the id of the customer we want to receive
     * @return the customer from the database
     */
    @Override
    public Customer getOneCustomer(int customerId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        List<Customer> customers = getAllCustomers(DBmanager.GET_CUSTOMER_BY_ID, values);
        try {
            if (customers.size()>0) {
                return customers.get(0);
            }else{
                throw new CouponSystemException();
            }
        }catch (CouponSystemException e){
            System.out.println(ErrorType.CUSTOMER_NOT_EXIST.getMessage());
            return null;
        }
    }
}
