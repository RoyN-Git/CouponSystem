package jobs;

import dbdao.CouponsDBDAO;
import dao.CouponsDAO;
import exception.CouponSystemException;

    public class CouponExpirationDailyJob implements Runnable {

        private CouponsDAO couponsDAO;
        private static final long TWENTY_FOUR_HOURS = 24 * 60 * 60 * 1000;
        private boolean quit;

        public CouponExpirationDailyJob() {

            couponsDAO = new CouponsDBDAO();
        }

        @Override
        public void run() {
            while (!quit) {
                try {
                    couponsDAO.deleteExpiredCoupons();
                } catch (CouponSystemException couponSystemException) {
                    System.out.println(couponSystemException.getMessage());
                }

                try {
                    Thread.sleep(TWENTY_FOUR_HOURS);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    break;
                }
            }
        }
        public void stop() {
            quit = true;
        }

    }

