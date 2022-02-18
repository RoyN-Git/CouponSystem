package dbdao;

import beans.Coupon;
import dao.CouponsDAO;
import db.DBUtils;
import db.DBmanager;

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

    }

    @Override
    public void deleteCoupon(int couponId) {

    }

    @Override
    public List<Coupon> getAllCoupons() {
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
