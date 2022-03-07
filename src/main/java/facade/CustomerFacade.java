package facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import com.mysql.cj.xdevapi.Client;
import db.DBUtils;
import db.DBmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerFacade extends ClientFacade {
    private int customerId;
    List<Coupon> allCoupons;


    /**
     * Empty constructor.
     * The constructor's default is to set the customerId as 0,
     * because in the database all ids are natural numbers (1,2,...).
     * It also sets the field allCoupons as the list of all not expired coupons in the database.
     */
    public CustomerFacade() {
        super();
        this.customerId = 0;
        this.allCoupons=this.couponsDAO.getAllCoupons(DBmanager.VIEW_ALL_COUPONS,new HashMap<>());
        /*
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        setAllCoupons(this.couponsDAO.getAllCoupons(DBmanager.VIEW_ALL_COUPONS,values));

         */
    }


    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public List<Coupon> getAllCoupons() {
        return this.allCoupons;
    }


    public void setAllCoupons(List<Coupon> allCoupons) {
        this.allCoupons = allCoupons;
    }

    /**
     * Method used to log in as a customer by checking email and password.
     * The check of the credentials is through the database.
     * The method also sets the field companyId as the id of the logged customer.
     * @param email is the email the user typed in.
     * @param password is the password the user typed in.
     * @return true if login succeed, false if not.
     */
    @Override
    public boolean login(String email, String password) {
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,email);
        values.put(2,password);
        if(this.customersDAO.isCustomerExists(email,password)){
            try {
                ResultSet resultSet= DBUtils.runQueryForResult(DBmanager.LOGIN_CUSTOMER,values);
                assert resultSet != null;
                if(resultSet.next()){
                    if(resultSet.getInt("counter")==1){
                        setCustomerId(resultSet.getInt("id"));
                        return true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    /**
     * This method purchase a single coupon and updates the purchase in the database.
     * @param coupon is the coupon the customer wants to purchase.
     */
    public void purchaseCoupon(Coupon coupon){
        this.couponsDAO.addCouponPurchase(this.customerId, coupon.getId());
        //setAllCoupons(this.couponsDAO.getAllCoupons(DBmanager.VIEW_ALL_COUPONS,new HashMap<>()));
    }

    /**
     * This method receives a list of coupons from the database that the customer has purchased.
     * @return list of coupons that the customer has purchased.
     */
    public List<Coupon> getCustomerCoupons(){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS,values);
    }

    /**
     * This method receives a list of coupons from the database that the customer has purchased from a single category.
     * @param category is the category we want the coupons to be filtered by.
     * @return list of coupons that the customer has purchased from a single category.
     */
    public List<Coupon> getCustomerCoupons(Category category){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        values.put(2,category.value);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS_BY_CATEGORY,values);
    }

    /**
     * This method receives a list of coupons from the database that the customer has purchased up to a maximum price.
     * @param maxPrice is the maximum price we want the coupons to be filtered by.
     * @return list of coupons that the customer has purchased up to a maximum price.
     */
    public List<Coupon> getCustomerCoupons(double maxPrice){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        values.put(2,maxPrice);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS_UP_TO_PRICE,values);
    }

    /**
     * This method returns the details of the logged company based on field customerId.
     * @return the details of the logged customer.
     */
    public Customer getCustomerDetails(){
        return this.customersDAO.getOneCustomer(this.customerId);
    }


}
