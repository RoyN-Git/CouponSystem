package dao;

import beans.Customer;

import java.util.List;

public interface CustomersDAO {
    boolean isCustomerExists(String email, String password);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerId);
}
