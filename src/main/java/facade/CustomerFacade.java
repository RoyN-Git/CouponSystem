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

    /**
     * This method returns customerId from the database
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * This method set customerId from the database
     * @param customerId set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * This method return all Coupons from the database
     * @return all Coupons
     */
    public List<Coupon> getAllCoupons() {
        return this.allCoupons;
    }

    /**
     * This method set all Coupons in the database
     * @param allCoupons
     */
    public void setAllCoupons(List<Coupon> allCoupons) {
        this.allCoupons = allCoupons;
    }

    /**
     * Method used to log in as an admin by checking email and password.
     * The check is hard coded.
     * @param email is the email the user entered.
     * @param password is the password the user entered.
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
     * This method purchase coupon and set it to the database
     * @param coupon purchase Coupon
     */
    public void purchaseCoupon(Coupon coupon){
        this.couponsDAO.addCouponPurchase(this.customerId, coupon.getId());
        //setAllCoupons(this.couponsDAO.getAllCoupons(DBmanager.VIEW_ALL_COUPONS,new HashMap<>()));
    }

    /**
     * This method get Customer Coupons and from the database
     * @return get Customer Coupons
     */
    public List<Coupon> getCustomerCoupons(){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS,values);
    }

    /**
     * This method get Customer Coupons from the database
     * @param category set category
     * @return get All Coupons
     */
    public List<Coupon> getCustomerCoupons(Category category){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        values.put(2,category.value);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS_BY_CATEGORY,values);
    }

    /**
     * This method get Customer Coupons from the database and put them in order from maxPrice
     * @param maxPrice will be first. from high to low price order
     * @return  Customer Coupons from high to low price order
     */
    public List<Coupon> getCustomerCoupons(double maxPrice){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        values.put(2,maxPrice);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS_UP_TO_PRICE,values);
    }

    /**
     * This method return one Customer Details
     * @return One Customer Details
     */
    public Customer getCustomerDetails(){
        return this.customersDAO.getOneCustomer(this.customerId);
    }


}
