package dbdao;

import beans.Category;
import beans.Coupon;
import beans.ErrorType;
import dao.CouponsDAO;
import db.DBUtils;
import db.DBmanager;
import exception.CouponSystemException;


import java.sql.*;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import java.util.Date;

public class CouponsDBDAO implements CouponsDAO {

    /**
     * This method add new coupon to the database.
     * The method checks first if the received coupon doesn't have a title
     * which already exist in the database for the sane company
     * @param coupon is the new coupon we want to add to the database
     */
    @Override
    public void addCoupon(Coupon coupon) {
        Map<Integer, Object> values = new HashMap<>();

         //check constraints
        ResultSet resultSet;
        values.put(1, coupon.getTitle());
        values.put(2, coupon.getCompanyID());
        values.put(3, coupon.getId());
        resultSet = DBUtils.runQueryForResult(DBmanager.IS_COUPON_TITLE_EXISTS, values);
        assert resultSet != null;
        try {
            if (resultSet.next()) {
                if (resultSet.getString("title").equals(coupon.getTitle())) {
                    throw new CouponSystemException(ErrorType.TITLE_ALREADY_EXIST.getMessage());
                }
            }

        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
            return;
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

        try {
            if (DBUtils.runQuery(DBmanager.ADD_NEW_COUPON, values)) {
                System.out.println("Coupon added successfully");
            } else {
                System.out.println("Coupon addition failed");
                throw new SQLIntegrityConstraintViolationException();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method updates a coupon in the database.
     * The method checks first if the received coupon doesn't have a title
     * which already exist in the database for the sane company
     * @param coupon is the coupon we want to update
     */
    @Override
    public void updateCoupon(Coupon coupon)  {
        Map<Integer, Object> values = new HashMap<>();

        //check constraints
        ResultSet resultSet;
        values.put(1, coupon.getTitle());
        values.put(2, coupon.getCompanyID());
        values.put(3, coupon.getId());
        resultSet = DBUtils.runQueryForResult(DBmanager.IS_COUPON_TITLE_EXISTS, values);
        assert resultSet != null;
        try {
            if (resultSet.next()) {
                if (resultSet.getString("title").equals(coupon.getTitle())) {
                    throw new CouponSystemException(ErrorType.TITLE_ALREADY_EXIST.getMessage());
                }
            }

        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
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
        values.put(9, coupon.getImage());
        values.put(10, coupon.getId());

        try {
            if (DBUtils.runQuery(DBmanager.UPDATE_COUPON_BY_ID, values)) {
                System.out.println("Coupon update succeed");
            } else {
                System.out.println("Coupon deletion failed");
                throw new SQLIntegrityConstraintViolationException();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deletes a coupon from the database based on it's id
     * @param couponId is the id of the coupon we want to delete
     */
    @Override
    public void deleteCoupon(int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, couponId);
        try {
            if(DBUtils.runQuery(DBmanager.DELETE_COUPON, values)){
                System.out.println("Coupon deleted successfully");
            }else{
                System.out.println("Coupon deletion failed");
                throw new SQLException();
            }
        }catch (SQLException e){
            System.out.println(ErrorType.COUPON_NOT_EXIST.getMessage());

        }
    }

    /**
     * This method receives a list of coupons from the database based on our filters:
     * 1- all coupons, 2- all coupons by category, 3- all coupons up to a price
     * @param sql is a sql query. Each time we receive a query based on the filter we want.
     * @param values is a map of the values to insert into the query based on the filter we want.
     * @return list of coupons.
     */
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

    /**
     * This method returns a coupon from the database based on its id.
     * @param couponId is the id of the coupon we want to get from the database.
     * @return a coupon from the database.
     */
    @Override
    public Coupon getOneCoupon(int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, couponId);
        List<Coupon> coupons = getAllCoupons(DBmanager.GET_ONE_COUPON, values);
        try {
            if (coupons.size() > 0) {
                return coupons.get(0);
            } else {
                throw new CouponSystemException();
            }
        } catch (CouponSystemException e) {
            System.out.println(ErrorType.COUPON_NOT_EXIST.getMessage());
            return null;
        }
    }

    /**
     * This method adds a new coupon purchase in the database.
     * The method checks first if a coupon can be purchased:
     * coupon amount >0, coupon is not expired and coupon not purchased
     * @param customerId is the id of the customer who wants to purchase the cooupon.
     * @param couponId is the id of the coupon that we want to purchase
     */
    @Override
    public void addCouponPurchase(int customerId, int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        ResultSet resultSet;
        values.put(1, couponId);
        resultSet = DBUtils.runQueryForResult(DBmanager.COUPON_AMOUNT, values);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getInt("amount") == 0) {
                    throw new CouponSystemException(ErrorType.COUPON_AMOUNT_IS_ZERO.getMessage());
                }
            }
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
            return;
        }
        resultSet = DBUtils.runQueryForResult(DBmanager.IS_COUPON_EXPIRED, values);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                if (/*resultSet.getBoolean("expired")||*/
                resultSet.getDate("end_date").before(new Date(System.currentTimeMillis()))) {
                    throw new CouponSystemException(ErrorType.COUPON_EXPIRED.getMessage());
                }
            }
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
            return;
        }
        values.put(1, customerId);
        values.put(2, couponId);

        resultSet = DBUtils.runQueryForResult(DBmanager.IS_COUPON_PURCHASED, values);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getInt("counter") == 1) {
                    throw new CouponSystemException(ErrorType.COUPON_ALREADY_PURCHASED.getMessage());
                }
            }
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            if(DBUtils.runQuery(DBmanager.PURCHASE_COUPON,values)){
                System.out.println("Coupon purchased");
                Coupon coupon = getOneCoupon(couponId);
                coupon.setAmount(coupon.getAmount() - 1);
                updateCoupon(coupon);
            }else{
                System.out.println("Coupon purchase failed");
                throw new SQLIntegrityConstraintViolationException();
            }
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method deletes a single coupon purchase based on customer id and coupon id
     * @param customerId is the id of the customer.
     * @param couponId is the id of the coupon
     */
    @Override
    public void deleteCouponPurchase(int customerId, int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        values.put(2, couponId);

        try {
            if(DBUtils.runQuery(DBmanager.DELETE_COUPON_PURCHASE, values)){
                System.out.println("Coupon deleted successfully");
            }else{
                System.out.println("Coupon deletion failed");
                throw new SQLException();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method adds a new category to the database.
     * The method checks first if there is no category with the same name in the database.
     * @param category is the category we want to add.
     */
    @Override
    public void addCategory(Category category) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, category.getName());
        ResultSet resultSet = DBUtils.runQueryForResult(DBmanager.IS_CATEGORY_EXISTS, values);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("name").equals(category.getName())) {
                    throw new CouponSystemException(ErrorType.CATEGORY_ALREADY_EXIST.getMessage());
                }
            }
        } catch (SQLException|CouponSystemException e) {
            System.out.println(e.getMessage());

        }

        System.out.println(DBUtils.runQuery(DBmanager.CREATE_NEW_CATEGORY, values) ?
                "Category added successfully" :
                "Category addition failed");
    }
}
