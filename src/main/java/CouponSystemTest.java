import beans.Category;
import beans.Company;
import beans.Coupon;
import java.lang.reflect.*;

import beans.Customer;
import dao.CompaniesDAO;
import db.DBUtils;
import db.DBmanager;
import dbdao.CompaniesDBDAO;
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
                Category.getCategoryByValue(1),
                "hello world",
                "bla bla bla",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+7*DBmanager.ONE_DAY),
                false,
                100,
                100,
                "picture"));
        Company company=new Company(
                1,
                "My Company",
                "company@company.com",
                "password");
        company.setCoupons(coupons);
        Customer customer=new Customer(
                1,
                "first",
                "last",
                "customer@customer.com",
                "password");
        customer.setCoupons(coupons);

        CompaniesDAO companiesDAO=new CompaniesDBDAO();
        companiesDAO.addCompany(company);
        //companiesDAO.deleteCompany(2);
        System.out.println(companiesDAO.isCompanyExists("company@company.com","password"));

        //System.out.println(company.toString());
        //System.out.println(customer.toString());
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
