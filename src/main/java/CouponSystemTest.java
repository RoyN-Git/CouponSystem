import beans.Category;
import beans.Company;
import beans.Coupon;
import java.lang.reflect.*;

import beans.Customer;
import db.DBUtils;
import db.DBmanager;
import dbdao.CouponsDBDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CouponSystemTest {
    private static boolean isSuccess;
    public static void main(String[] args) {
        //dropDataBase();
        //createDataBase();

        List<Coupon> coupons=new ArrayList<>();
        coupons.add(new Coupon(
                1,
                1,
                Category.ELECTRICITY,
                "hello world",
                "bla bla bla",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+7*DBmanager.ONE_DAY),
                100,
                100,
                "picture"));
        Company company=new Company(
                1,
                "My Company",
                "company@company.com",
                "password",
                coupons);
        Customer customer=new Customer(
                1,
                "first",
                "last",
                "customer@customer.com",
                "password",
                coupons);

        System.out.println(company.toString());
        System.out.println(customer.toString());
    }

    public static void createDataBase(){
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

    public static void dropDataBase(){
        isSuccess=DBUtils.runQuery(DBmanager.DROP_DB);
        System.out.println(isSuccess?
                "delete db successfully":
                "delete failed");
    }
}
