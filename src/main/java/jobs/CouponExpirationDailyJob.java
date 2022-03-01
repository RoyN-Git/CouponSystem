package jobs;

import dao.CouponsDAO;
import dbdao.CouponsDBDAO;
import exception.CouponSystemException;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDAO couponsDAO;
    boolean quit=false;

    @Override
    public void run() {
        //todo: finish the job
    }
}

