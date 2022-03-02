package dao;

import beans.Customer;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface CustomersDAO {
    boolean isCustomerExists(String email, String password);
    void addCustomer(Customer customer) /*throws SQLIntegrityConstraintViolationException*/;
    void updateCustomer(Customer customer) /*throws SQLIntegrityConstraintViolationException*/;
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers(String sql, Map<Integer,Object> values);
    Customer getOneCustomer(int customerId);
}
