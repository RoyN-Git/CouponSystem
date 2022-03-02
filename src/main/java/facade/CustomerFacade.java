package facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import com.mysql.cj.xdevapi.Client;
import db.DBmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerFacade extends ClientFacade {
    private int customerId;

    public CustomerFacade(int customerId) {
        super();
        this.customerId = customerId;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    public void purchaseCoupon(Coupon coupon){
        this.couponsDAO.addCouponPurchase(this.customerId, coupon.getId());
    }

    public List<Coupon> getCustomerCoupons(){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS,values);
    }

    public List<Coupon> getCustomerCoupons(Category category){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        values.put(2,category.value);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS_BY_CATEGORY,values);
    }

    public List<Coupon> getCustomerCoupons(double maxPrice){
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,this.customerId);
        values.put(2,maxPrice);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS_UP_TO_PRICE,values);
    }

    public Customer getCustomerDetails(){
        return this.customersDAO.getOneCustomer(this.customerId);
    }
}
