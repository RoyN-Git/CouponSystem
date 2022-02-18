import beans.Category;
import beans.Coupon;
import java.lang.reflect.*;
import db.DBUtils;
import db.DBmanager;
import dbdao.CouponsDBDAO;

import java.sql.Date;

public class CouponSystemTest {
    public static void main(String[] args) {
        boolean isSuccess;
        /*
        isSuccess=DBUtils.runQuery(DBmanager.DROP_DB);
        System.out.println(isSuccess?
                "delete db successfullk":
                "delete failed");
        */
        isSuccess= DBUtils.runQuery(DBmanager.CREATE_DB);
        System.out.println(isSuccess?
                "database created successfully":
                "database creation failed");
        isSuccess=DBUtils.runQuery(DBmanager.CREATE_CUSTOMERS_TABLE);
        System.out.println(isSuccess?
                "customers table created successfully":
                "customers table creation failed");
        isSuccess=DBUtils.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        System.out.println(isSuccess?
                "categories table created successfully":
                "categories table creation failed");
        isSuccess=DBUtils.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        System.out.println(isSuccess?
                "companies table created successfully":
                "companies table creation failed");
        isSuccess=DBUtils.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        System.out.println(isSuccess?
                "coupons table created successfully":
                "coupons table creation failed");
        isSuccess=DBUtils.runQuery(DBmanager.CREATE_CUSTOMERS_VS_COUPONS_TABLE);
        System.out.println(isSuccess?
                "customers vs coupons table created successfully":
                "customers vs coupons table creation failed");








    }
}
