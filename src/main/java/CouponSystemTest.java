import beans.Category;
import beans.Company;
import beans.Coupon;

import java.lang.reflect.*;

import beans.Customer;
import dao.CompaniesDAO;
import dao.CouponsDAO;
import dao.CustomersDAO;
import db.DBUtils;
import db.DBmanager;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import dbdao.CustomerDBDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CouponSystemTest {
    private static boolean isSuccess;

    public static void main(String[] args) {
        //dropDataBase();
        //createDataBase();


        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(
                0,
                1,
                Category.getCategoryByValue(1),
                "hello world",
                "bla bla bla",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 7 * DBmanager.ONE_DAY),
                false,
                100,
                100,
                "picture"));
        coupons.add(new Coupon(
                0,
                1,
                Category.getCategoryByValue(2),
                "whazzup",
                "description",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 3 * DBmanager.ONE_DAY),
                false,
                10,
                100,
                "picture"));


        /*
        Company company=new Company(
                0,
                "ShaharCompany",
                "couponComp@compCoup.com",
                "password11");
        //company.setCoupons(coupons);

         */
        /*
        Customer customer=new Customer(
                0,
                "stam",
                "stam",
                "stamstamstam@gmail.com",
                "stam");
        //customer.setCoupons(coupons);
         */

        CompaniesDAO companiesDAO = new CompaniesDBDAO();
        //companiesDAO.addCompany(company);//insert new company into DB, working
        //companiesDAO.deleteCompany(2);//delete company from DB, working
        //System.out.println(companiesDAO.isCompanyExists("company@company.com","password"));//check if the company exist, working
        //Company company1=companiesDAO.getOneCompany(5);//get one company from DB, working
        //System.out.println(company1);
        //company1.setEmail("couponComp@compCoup.com");
        //companiesDAO.updateCompany(company1);//update company and push the changes to DB, working
        //List<Company> companies=companiesDAO.getAllCompanies(DBmanager.GET_ALL_COMPANIES,new HashMap<>());// working
        //companies.forEach(System.out::println);
        //System.out.println(company.toString());
        //System.out.println(customer.toString());

        CustomersDAO customersDAO = new CustomerDBDAO();
        //customersDAO.addCustomer(customer);//working
        //customersDAO.deleteCustomer(6);//working
        //Customer customer1=customersDAO.getOneCustomer(1);//working
        //System.out.println(customer1);
        //customer1.setPassword("firstlast");//working
        //customersDAO.updateCustomer(customer1);//working
        //List<Customer> customers=customersDAO.getAllCustomers(DBmanager.GET_ALL_CUSTOMERS, new HashMap<>());//working
        //customers.forEach(System.out::println);

        CouponsDAO couponsDAO = new CouponsDBDAO();
        for (Category item : Category.values()) {
            couponsDAO.addCategory(item);
        }
        for (Coupon item : coupons) {
            couponsDAO.addCoupon(item);
        }

    }

    public static void createDataBase() {
        isSuccess = DBUtils.runQuery(DBmanager.CREATE_DB);
        System.out.println(isSuccess ?
                "database created successfully" :
                "database creation failed");
        isSuccess = DBUtils.runQuery(DBmanager.CREATE_CUSTOMERS_TABLE);
        System.out.println(isSuccess ?
                "customers table created successfully" :
                "customers table creation failed");
        isSuccess = DBUtils.runQuery(DBmanager.CREATE_CATEGORIES_TABLE);
        System.out.println(isSuccess ?
                "categories table created successfully" :
                "categories table creation failed");
        isSuccess = DBUtils.runQuery(DBmanager.CREATE_COMPANIES_TABLE);
        System.out.println(isSuccess ?
                "companies table created successfully" :
                "companies table creation failed");
        isSuccess = DBUtils.runQuery(DBmanager.CREATE_COUPONS_TABLE);
        System.out.println(isSuccess ?
                "coupons table created successfully" :
                "coupons table creation failed");
        isSuccess = DBUtils.runQuery(DBmanager.CREATE_CUSTOMERS_VS_COUPONS_TABLE);
        System.out.println(isSuccess ?
                "customers vs coupons table created successfully" :
                "customers vs coupons table creation failed");

    }

    public static void dropDataBase() {
        isSuccess = DBUtils.runQuery(DBmanager.DROP_DB);
        System.out.println(isSuccess ?
                "delete db successfully" :
                "delete failed");
    }
}
