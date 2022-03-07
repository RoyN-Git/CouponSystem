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
            for (Coupon item:expiredCoupons){
                item.setExpired(true);
                couponsDAO.updateCoupon(item);
            }

            try {
                Thread.sleep(DBmanager.ONE_DAY);//sleep for 24 hours
                //Thread.sleep(1000*30);//sleep for 30 seconds (for testing)
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void stopSystem(){
        quit = true;
    }
}

