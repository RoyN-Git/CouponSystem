package facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import db.DBUtils;
import db.DBmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyFacade extends ClientFacade{
    private int companyId;

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

    @Override
    public boolean login(String email, String password) {
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,email);
        values.put(2,password);
        if(this.companiesDAO.isCompanyExists(email,password)){
            try {
                ResultSet resultSet= DBUtils.runQueryForResult(DBmanager.LOGIN_COMPANY,values);
                assert resultSet != null;
                if(resultSet.next()){
                    setCompanyId(resultSet.getInt("id"));
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //setCompanyId();A
            return true;
        }
        return false;
    }

    public void addCoupon(Coupon coupon){
        this.couponsDAO.addCoupon(coupon);
    }

    public void updateCoupon(Coupon coupon){
        //todo: check if need to ask what to update here
        this.couponsDAO.updateCoupon(coupon);
    }

    public void deleteCoupon(int couponId){
        this.couponsDAO.deleteCoupon(couponId);
    }

    public List<Coupon> getCompanyCoupons(){
        Map<Integer, Object> values=new HashMap<>();
        values.put(1,companyId);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS,values);
    }

    public List<Coupon> getCompanyCoupons(Category category){
        Map<Integer, Object> values=new HashMap<>();
        values.put(1,companyId);
        values.put(2,category.value);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_BY_CATEGORY, values);
    }

    public List<Coupon> getCompanyCoupons(double maxPrice){
        Map<Integer, Object> values=new HashMap<>();
        values.put(1,companyId);
        values.put(2, maxPrice);
        return this.couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_UP_TO_PRICE, values);
    }

     public Company getCompanyDetails(){
        return this.companiesDAO.getOneCompany(companyId);
     }
}
