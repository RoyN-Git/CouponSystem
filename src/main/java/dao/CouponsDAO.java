package dao;

import beans.Category;
import beans.Coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CouponsDAO {
    void addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponId);
    List<Coupon> getAllCoupons(String sql, Map<Integer, Object> values);
    Coupon getOneCoupon(int couponId);
    void addCouponPurchase(int customerId, int couponId);
    void deleteCouponPurchase(int customerId, int couponId);
    void addCategory(Category category);
}