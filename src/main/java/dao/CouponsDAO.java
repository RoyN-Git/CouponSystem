package dao;

public interface CouponsDAO {
    //todo: Remove from comment when merging Coupons class
    //void addCoupon(Coupon coupon);
    //void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponId);
    //List<Coupon> getAllCoupons();
    //Coupon getOneCoupon(int couponId);
    void addCouponPurchase(int customerId, int couponId);
    void deleteCouponPurchase(int customerId, int couponId);;
}
