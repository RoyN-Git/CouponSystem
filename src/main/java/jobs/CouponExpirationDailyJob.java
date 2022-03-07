package jobs;

import beans.Coupon;
import dao.CouponsDAO;
import db.DBmanager;
import dbdao.CouponsDBDAO;
import exception.CouponSystemException;

import java.util.HashMap;
import java.util.List;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDAO couponsDAO;
    boolean quit=false;
  
  
    public CouponExpirationDailyJob() {
        this.couponsDAO = new CouponsDBDAO();
    }

  
    @Override
    public void run() {

        while (!quit){
            List<Coupon> expiredCoupons = couponsDAO.getAllCoupons(DBmanager.GET_EXPIRED_COUPONS,new HashMap<>());
            expiredCoupons.forEach(System.out::println);  // todo: delete
            for (Coupon item:expiredCoupons){
                item.setExpired(true);
                couponsDAO.updateCoupon(item);
            }
            try {
                Thread.sleep(DBmanager.ONE_DAY);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void stopSystem(){
        quit = true;
    }
}

