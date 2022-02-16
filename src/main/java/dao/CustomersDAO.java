package dao;

public interface CustomersDAO {
    //todo: Remove from comment when merging Customer class
    boolean isCustomerExists(String email, String password);
    //void addCustomer(Customer customer);
    //void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    //List<Customer> getAllCustomers();
    //Customer getOneCustomer(int customerId);
}
