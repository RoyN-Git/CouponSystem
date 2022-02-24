package dbdao;

import beans.Category;
import beans.Coupon;
import dao.CouponsDAO;
import db.ConnectionPool;
import db.DBUtils;
import db.DBmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CouponsDBDAO implements CouponsDAO {

    @Override
    public void addCoupon(Coupon coupon) {
        Map<Integer, Object> values = new HashMap<>();
        ResultSet resultSet;

        values.put(1, coupon.getTitle());
        values.put(2, coupon.getCompanyID());
        resultSet = DBUtils.runQueryForResult(DBmanager.IS_COUPON_TITLE_EXISTS, values);
        assert resultSet != null;
        try {
            if(resultSet.next()){
                if(resultSet.getString("title").equals(coupon.getTitle())){
                    //todo: throw coupon title already exists exception
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        values.clear();


        values.put(1, coupon.getCompanyID());
        values.put(2, coupon.getCategory().value);
        values.put(3, coupon.getTitle());
        values.put(4, coupon.getDescription());
        values.put(5, coupon.getStartDate());
        values.put(6, coupon.getEndDate());
        values.put(7, coupon.isExpired());
        values.put(8, coupon.getAmount());
        values.put(9, coupon.getPrice());
        values.put(10, coupon.getImage());

        System.out.println((DBUtils.runQuery(DBmanager.ADD_NEW_COUPON, values) ?
                "Coupon added successfully" :
                "Coupon addition failed"));
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        Map<Integer, Object> values = new HashMap<>();
        ResultSet resultSet;
        values.put(1, coupon.getTitle());
        values.put(2, coupon.getCompanyID());
        resultSet = DBUtils.runQueryForResult(DBmanager.IS_COUPON_TITLE_EXISTS, values);
        assert resultSet != null;
        try {
            if(resultSet.next()){
                if(resultSet.getString("title").equals(coupon.getTitle())){
                    //todo: throw coupon title already exists exception
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        values.clear();
        values.put(1, coupon.getCategory().value);
        values.put(2, coupon.getTitle());
        values.put(3, coupon.getDescription());
        values.put(4, coupon.getStartDate());
        values.put(5, coupon.getEndDate());
        values.put(6, coupon.isExpired());
        values.put(7, coupon.getAmount());
        values.put(8, coupon.getPrice());
        values.put(9, coupon.getPrice());
        values.put(10, coupon.getImage());
        values.put(11, coupon.getId());

        System.out.println((DBUtils.runQuery(DBmanager.UPDATE_COUPON_BY_ID, values) ?
                "Coupon update succeed" :
                "Coupon update failed"));
    }

    @Override
    public void deleteCoupon(int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, couponId);

        System.out.println((DBUtils.runQuery(DBmanager.DELETE_COUPON, values) ?
                "Coupon deleted successfully" :
                "Coupon deletion failed"));
    }


    @Override
    public List<Coupon> getAllCoupons(String sql, Map<Integer, Object> values) {
        List<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DBUtils.runQueryForResult(sql, values);
        try {
            while (resultSet.next()) {
                Coupon coupon = new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        Category.getCategoryByValue(resultSet.getInt("category_id")),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getBoolean("expired"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return coupons;

    }

    @Override
    public Coupon getOneCoupon(int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, couponId);
        List<Coupon> coupons = getAllCoupons(DBmanager.GET_ONE_COUPON, values);
        if (coupons == null) {
            //todo: throw coupon  doesn't exist exception
            return null;
        }
        return coupons.get(0);
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        values.put(2, couponId);
        System.out.println((DBUtils.runQuery(DBmanager.PURCHASE_COUPON, values) ?
                "Coupon purchased" :
                "Coupon purchase failed"));
    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        values.put(2, couponId);
        System.out.println((DBUtils.runQuery(DBmanager.DELETE_COUPON_PURCHASE, values) ?
                "Coupon purchase deleted" :
                "Coupon purchase deletion failed"));
    }

    @Override
    public void addCategory(Category category) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, category.getName());
        ResultSet resultSet = DBUtils.runQueryForResult(DBmanager.IS_CATEGORY_EXISTS, values);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("name").equals(category.getName())) {
                    //todo: throw category already exists exception
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(DBUtils.runQuery(DBmanager.CREATE_NEW_CATEGORY, values) ?
                "Category added successfully" :
                "Category addition failed");
    }
}
