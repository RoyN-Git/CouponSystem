package dbdao;

import beans.Customer;
import dao.CustomersDAO;
import db.DBUtils;
import db.DBmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDBDAO implements CustomersDAO {
    @Override
    public boolean isCustomerExists(String email, String password) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBUtils.runQueryForResult(DBmanager.LOGIN_CUSTOMER, values);
        try {
            assert resultSet != null;
            resultSet.next();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void addCustomer(Customer customer) /*throws SQLIntegrityConstraintViolationException*/ {
        Map<Integer, Object> values = new HashMap<>();
        /*
        ResultSet resultSet;
        values.put(1, customer.getEmail());
        resultSet = DBUtils.runQueryForResult(DBmanager.IS_CUSTOMER_EMAIL_EXISTS, values);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(customer.getEmail())) {
                    //todo: throw company email already exists exception
                    return;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         */
        values.put(1,customer.getFirstName());
        values.put(2,customer.getLastName());
        values.put(3,customer.getEmail());
        values.put(4,customer.getPassword());
        System.out.println((DBUtils.runQuery(DBmanager.CREATE_NEW_CUSTOMER, values) ?
                "Customer created successfully" :
                "Customer creation failed"));
    }





    @Override
    public void updateCustomer(Customer customer) /*throws SQLIntegrityConstraintViolationException*/{
        Map<Integer, Object> values = new HashMap<>();
        /*
        ResultSet resultSet;
        values.put(1, customer.getEmail());
        values.put(2,customer.getId());
        resultSet = DBUtils.runQueryForResult(DBmanager.IS_CUSTOMER_EMAIL_EXISTS, values);

        try {
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(customer.getEmail())) {
                    //todo: throw company email already exists exception
                    return;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        values.clear();
         */
        values.put(1,customer.getFirstName());
        values.put(2,customer.getLastName());
        values.put(3,customer.getEmail());
        values.put(4,customer.getPassword());
        values.put(5,customer.getId());
        System.out.println((DBUtils.runQuery(DBmanager.UPDATE_CUSTOMER_BY_ID, values) ?
                "Customer updated successfully" :
                "Customer update failed"));
    }

    @Override
    public void deleteCustomer(int customerId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);

        System.out.println((DBUtils.runQuery(DBmanager.DELETE_CUSTOMER_BY_ID, values) ?
                "Customer deleted successfully" :
                "Customer deletion failed"));

    }

    @Override
    public List<Customer> getAllCustomers(String sql, Map<Integer, Object> values) {
        List<Customer> customers = new ArrayList<>();
        ResultSet resultSet = DBUtils.runQueryForResult(sql, values);
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
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

    @Override
    public Customer getOneCustomer(int customerId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        List<Customer> customers = getAllCustomers(DBmanager.GET_CUSTOMER_BY_ID, values);
        if (customers == null) {
            //todo: throw customer doesn't exists exception
            return null;
        }
        return customers.get(0);
    }
}
