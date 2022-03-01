package jobs;

import dao.CouponsDAO;
import dbdao.CouponsDBDAO;
import exception.CouponSystemException;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDAO couponsDAO;
    boolean quit=false;

    public CouponExpirationDailyJob(CouponsDAO couponsDAO, boolean quit) {
        this.couponsDAO = couponsDAO;
        this.quit = quit;
    }

    @Override
    public void run() {
        //todo: finish the job
        while (!quit){

        }
    }

    public void stop(){

    }
}

