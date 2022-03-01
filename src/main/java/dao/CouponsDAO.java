package dao;

import beans.Category;
import beans.Coupon;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface CouponsDAO {
    void addCoupon(Coupon coupon) /*throws SQLIntegrityConstraintViolationException*/;
    void updateCoupon(Coupon coupon) /*throws SQLIntegrityConstraintViolationException*/;
    void deleteCoupon(int couponId);
    List<Coupon> getAllCoupons(String sql, Map<Integer,Object> values);
    Coupon getOneCoupon(int couponId);
    void addCouponPurchase(int customerId, int couponId);
    void deleteCouponPurchase(int customerId, int couponId);
    void addCategory(Category category);
}
