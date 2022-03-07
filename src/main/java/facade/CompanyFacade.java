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


    /**
     * Empty constructor.
     * The constructor's default is to set the companyId as 0,
     * because in the database all ids are natural numbers (1,2,...)
     */
    public CompanyFacade() {
        super();
        this.companyId = 0;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * This method logs in as a company by checking email and password.
     * The check of the credentials is through the database.
     * The method also sets the field companyId as the id of the logged company.
     * @param email is the email the user typed in.
     * @param password is the password the user typed in.
     * @return true if login succeed, false if not.
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
     *This method adds a coupon to the database.
     * @param coupon is the coupon we want to add.
     */
    public void addCoupon(Coupon coupon) {
        this.couponsDAO.addCoupon(coupon);
    }

    /**
     *This method updates a coupon in the database.
     * @param coupon is the coupon we want to update.
     */
    public void updateCoupon(Coupon coupon) {
        this.couponsDAO.updateCoupon(coupon);
    }

    /**
     *This method deletes a coupon from the database based on its id.
     * @param couponId is the id of the coupon we wat to delete.
     */
    public void deleteCoupon(int couponId) {
        this.couponsDAO.deleteCoupon(couponId);
    }

    /**
     * This method receives a list of the company's coupons from the database.
     * @return list of Company's Coupons from the database.
     */
    public List<Coupon> getCompanyCoupons() {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS, values);
    }

    /**
     * This method returns a list of company's coupons from a single category.
     * @param category is the category we want the coupons to be filtered by.
     * @return a list of company's coupons from a single category
     */
    public List<Coupon> getCompanyCoupons(Category category) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        values.put(2, category.value);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_BY_CATEGORY, values);
    }

    /**
     * This method returns a list of company's coupons up to a maximum price.
     * @param maxPrice is the maximum price we want the coupons to be filtered by.
     * @return a list of company's coupons up to a maximum price.
     */
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        values.put(2, maxPrice);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_UP_TO_PRICE, values);
    }

    /**
     * This method returns the details of the logged company based on field companyId.
     * @return the details of the logged company.
     */
    public Company getCompanyDetails() {
        return this.companiesDAO.getOneCompany(companyId);
    }
}
