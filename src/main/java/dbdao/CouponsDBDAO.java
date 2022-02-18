package dbdao;

import beans.Coupon;
import dao.CouponsDAO;
import db.DBUtils;
import db.DBmanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements CouponsDAO {

    @Override
    public void addCoupon(Coupon coupon) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,coupon.getCompanyID());
        values.put(2,coupon.getCategory());
        values.put(3,coupon.getTitle());
        values.put(4,coupon.getDescription());
        values.put(4,coupon.getStartDate());
        values.put(5,coupon.getEndDate());
        values.put(6,coupon.getAmount());
        values.put(7,coupon.getPrice());
        values.put(8,coupon.getImage());

        System.out.println("new coupon added ? " + DBUtils.runQuery(DBmanager.ADD_NEW_COUPON,values));


    }

    @Override
    public void updateCoupon(Coupon coupon) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,coupon.getCategory());
        values.put(2,coupon.getTitle());
        values.put(3,coupon.getDescription());
        values.put(4,coupon.getStartDate());
        values.put(5,coupon.getEndDate());
        values.put(6,coupon.getAmount());
        values.put(7,coupon.getPrice());
        values.put(8,coupon.getPrice());
        values.put(9,coupon.getImage());
        values.put(10,coupon.getId());
        values.put(11,coupon.getCompanyID());

        System.out.println("coupon update ? " + DBUtils.runQuery(DBmanager.UPDATE_COUPON_BY_ID,values));

    }

    @Override
    public void deleteCoupon(int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,couponId);
        DBUtils.runQuery(DBmanager.UPDATE_COUPON_BY_ID,values);

    }

    // todo : check the DBManger
    @Override
    public List<Coupon> getAllCoupons() {

        List<Coupon> coupons = new ArrayList<>();
        return null;

    }

    @Override
    public Coupon getOneCoupon(int couponId) {
        return null;
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) {

    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) {

    }
}
