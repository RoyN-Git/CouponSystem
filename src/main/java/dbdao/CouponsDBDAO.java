package dbdao;

import beans.Category;
import beans.Coupon;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
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
        values.put(1,coupon.getCompanyID());
        values.put(2,coupon.getCategory());
        values.put(3,coupon.getTitle());
        values.put(4,coupon.getDescription());
        values.put(5,coupon.getStartDate());
        values.put(6,coupon.getEndDate());
        values.put(7,coupon.getAmount());
        values.put(8,coupon.getPrice());
        values.put(9,coupon.getImage());

        System.out.println("new coupon added ? " + DBUtils.runQuery(DBmanager.ADD_NEW_COUPON,values));


    }

    @Override
    public void updateCoupon(Coupon coupon) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,coupon.getCategory());
        values.put(2,coupon.getTitle());
        values.put(3,coupon.getDescription());
        values.put(4,coupon.getStartDate());
        values.put(5,coupon.getEndDate());
        values.put(6,coupon.getAmount());
        values.put(7,coupon.getPrice());
        values.put(8,coupon.getPrice());
        values.put(9,coupon.getImage());
        values.put(10,coupon.getId());
        System.out.println("coupon update ? " + DBUtils.runQuery(DBmanager.UPDATE_COUPON_BY_ID,values));

    }

    @Override
    public void deleteCoupon(int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,couponId);
        DBUtils.runQuery(DBmanager.DELETE_COUPON,values);

    }

    // todo : check the DBManger
    @Override
    public List<Coupon> getAllCoupons(String sql,Map<Integer,Object> values) {
        List<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet=DBUtils.runQueryForResult(sql, values);
        try{
            while(resultSet.next()){
                Coupon coupon=new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        Category.getCategoryByValue(resultSet.getInt("category_id")),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
                coupons.add(coupon);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return coupons;

    }

    @Override
    public Coupon getOneCoupon(int couponId) {
        Coupon coupon=null;
        Connection connection=null;
        try{
            connection= ConnectionPool.getInstance().getConnection();
            PreparedStatement statement= connection.prepareStatement(DBmanager.GET_ONE_COUPON);
            statement.setInt(1,couponId);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()){
                coupon=new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        Category.getCategoryByValue(resultSet.getInt("category_id")),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
            }
        }catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return coupon;
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,customerId);
        values.put(2,couponId);
        DBUtils.runQuery(DBmanager.PURCHASE_COUPON,values);
    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,customerId);
        values.put(2,couponId);
        DBUtils.runQuery(DBmanager.DELETE_COUPON_PURCHASE,values);
    }
}
