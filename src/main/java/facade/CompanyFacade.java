package facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import db.DBUtils;
import db.DBmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyFacade extends ClientFacade {
    private int companyId;


    public CompanyFacade() {
        super();
        this.companyId = 0;
    }

    /**
     * This method returns a companyId from the database
     * @return companyId from the database.
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * This method returns a CompanyId from the database
     * @param companyId returns from the database.
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * This method login with an email and password to the database
     * @param email for Username
     * @param password as password
     * @return
     */
    @Override
    public boolean login(String email, String password) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        if (this.companiesDAO.isCompanyExists(email, password)) {
            try {
                ResultSet resultSet = DBUtils.runQueryForResult(DBmanager.LOGIN_COMPANY, values);
                assert resultSet != null;
                if (resultSet.next()) {
                    if (resultSet.getInt("counter") == 1) {
                        setCompanyId(resultSet.getInt("id"));
                        return true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    /**
     *This method add a coupon to the database
     * @param coupon add to the database
     */
    public void addCoupon(Coupon coupon) {
        this.couponsDAO.addCoupon(coupon);
    }

    /**
     *This method update a coupon in the database
     * @param coupon update in the database
     */
    public void updateCoupon(Coupon coupon) {
        this.couponsDAO.updateCoupon(coupon);
    }

    /**
     *This method delete a couponID from the database
     * @param couponId delete from the database
     */
    public void deleteCoupon(int couponId) {
        this.couponsDAO.deleteCoupon(couponId);
    }

    /**
     * This method returns a list of Company Coupons from the database
     * @return list of Company Coupons from the database.
     */
    public List<Coupon> getCompanyCoupons() {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS, values);
    }

    /**
     * This method returns a list of Company Coupons Divided by categories
     * @param category returns a list of Company Coupons in a category
     * @return
     */
    public List<Coupon> getCompanyCoupons(Category category) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        values.put(2, category.value);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_BY_CATEGORY, values);
    }

    /**
     * This method returns a list of Company Coupons Divided by price, maxPrice first
     * @param maxPrice put a list of Company Coupons Divided by price, maxPrice first
     * @return
     */
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        values.put(2, maxPrice);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_UP_TO_PRICE, values);
    }

    /**
     * This method returns a Company Details
     * @return returns a Company Details
     */
    public Company getCompanyDetails() {
        return this.companiesDAO.getOneCompany(companyId);
    }
}
