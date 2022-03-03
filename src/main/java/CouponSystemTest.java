import beans.*;

import java.lang.reflect.*;

import dao.CompaniesDAO;
import dao.CouponsDAO;
import dao.CustomersDAO;
import db.DBUtils;
import db.DBmanager;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import dbdao.CustomerDBDAO;
import facade.*;
import test.Test;


import java.sql.Date;
import java.sql.ResultSet;
import java.util.*;
import java.util.function.Consumer;

public class CouponSystemTest {
    private static boolean isSuccess;

    public static void main(String[] args) {
        //new Test();
        //dropDataBase();
        //createDataBase();
        /*
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(
                0,
                11,
                Category.getCategoryByValue(1),
                "hi hi hi",
                "just by me",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 9 * DBmanager.ONE_DAY),
                false,
                50,
                200,
                "picture"));
        coupons.add(new Coupon(
                0,
                11,
                Category.getCategoryByValue(2),
                "check expired coupon",
                "its a coupon",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 5* DBmanager.ONE_DAY),
                false,
                30,
                70,
                "picture"));


         */
        /*
        Company company=new Company(
                0,
                "Shahar Company",
                "newCompany@company.com",
                "password11");
        //company.setCoupons(coupons);
         */
        /*
        Customer customer=new Customer(
                0,
                "stam",
                "stam",
                "stam@customer.com",
                "stam");

         */
        //customer.setCoupons(coupons);
        //CompaniesDAO companiesDAO = new CompaniesDBDAO();
        //companiesDAO.addCompany(company);//insert new company into DB, working
        //companiesDAO.deleteCompany(2);//delete company from DB, working
        //System.out.println(companiesDAO.isCompanyExists("company@company.com","password"));//check if the company exist, working
        //Company company1=companiesDAO.getOneCompany(1);//get one company from DB, working
        //companiesDAO.deleteCompany(company1.getId());
        //System.out.println(company1);
        //company1.setEmail("couponComp@compCoup.com");
        //companiesDAO.updateCompany(company1);//update company and push the changes to DB, working
        //List<Company> companies = companiesDAO.getAllCompanies(DBmanager.GET_ALL_COMPANIES, new HashMap<>());// working
        //companies.forEach(System.out::println);


        //CustomersDAO customersDAO = new CustomerDBDAO();
        //customersDAO.addCustomer(customer);//working
        //customersDAO.deleteCustomer(6);//working
        //Customer customer1 = customersDAO.getOneCustomer(1);//working
        //System.out.println(customer1);
        //customer1.setPassword("firstlast");//working
        //customersDAO.updateCustomer(customer1);//working
        //List<Customer> customers = customersDAO.getAllCustomers(DBmanager.GET_ALL_CUSTOMERS, new HashMap<>());//working
        //customers.forEach(System.out::println);

        //CouponsDAO couponsDAO = new CouponsDBDAO();
        //for (Category item : Category.values()) {
        //    couponsDAO.addCategory(item);//working
        //}
        /*
        for (Coupon item : coupons) {
           couponsDAO.addCoupon(item);//working
        }

         */
        //couponsDAO.deleteCoupon(5);//working
        //Coupon coupon = couponsDAO.getOneCoupon(17);//working
        //System.out.println(coupon);
        //coupon.setAmount(coupon.getAmount() - 1);
        //coupon.setExpired(true);
        //couponsDAO.updateCoupon(coupon);//working
        /*
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,company1.getId());
        company1.setCoupons(couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS,values));//working
        TablePrinter.print(company1);
        company1.printDetails();
         */
        /*
        values.clear();
        values.put(1,company1.getId());
        values.put(2,Category.FOOD.value);
        company1.setCoupons(couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_BY_CATEGORY,values));//working
        System.out.println(company1);
        values.clear();
        values.put(1,company1.getId());
        values.put(2,50.0);
        company1.setCoupons(couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS_UP_TO_PRICE,values));//working
        System.out.println(company1);

         */
        /*
        for (Company company:companies) {
            Map<Integer,Object> values=new HashMap<>();
            values.put(1,company.getId());
            company.setCoupons(couponsDAO.getAllCoupons(DBmanager.GET_ALL_COMPANY_COUPONS,values));
        }

         */
        //companies.forEach(System.out::println);
        /*
        for (Customer customer:customers) {
            for (Company company:companies) {
                couponsDAO.addCouponPurchase(customer.getId(),company.getCoupons().get(0).getId());
            }
        }
         */
        /*
        for (Customer item:customers) {
            couponsDAO.addCouponPurchase(item.getId(), 17);
        }
         */
        /*
        for (Customer customer:customers) {
            Map<Integer,Object> values=new HashMap<>();
            values.put(1,customer.getId());
            values.put(2,100.0);
            customer.setCoupons(couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS_UP_TO_PRICE,values));
        }
        customers.forEach(System.out::println);
         */
        /*
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,customer1.getId());
        customer1.setCoupons(couponsDAO.getAllCoupons(DBmanager.GET_ALL_CUSTOMER_COUPONS,values));
        System.out.println(customer1);
         */
        /*
        Company company2=companiesDAO.getOneCompany(4);
        company2.setEmail("company@company.com");
        couponsDAO.addCoupon(new Coupon(
                0,
                1,
                Category.getCategoryByValue(3),
                "title",
                "description",
                new Date(System.currentTimeMillis()-10*DBmanager.ONE_DAY),
                new Date(System.currentTimeMillis()-5*DBmanager.ONE_DAY),
                false,
                100,
                100,
                "picture"
        ));
        System.out.println(company2);
        companiesDAO.updateCompany(company2);
        company1=companiesDAO.getOneCompany(4);
        System.out.println(company1);

 */

        //todo: login manager check, use it where you need to write the login manager
        /*
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;

        //todo: how to work with admin facade

        clientFacade= loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);
        if(clientFacade instanceof AdminFacade){
            System.out.println("admin");
            AdminFacade adminFacade=(AdminFacade) clientFacade;
            adminFacade.addCompany(new Company(0,"WANT TO CHECK", "checkCompany@comp.com","check"));
            List<Company> companyList=adminFacade.getAllCompanies();
            //adminFacade.addCustomer(customer);
            List<Customer> customerList=adminFacade.getAllCustomers();
            //companyList.forEach(System.out::println);
            adminFacade.deleteCompany(100);

        }

         */


        //todo: how to work with company facade
/*
        clientFacade= loginManager.login("newCompany@company.com","password11",ClientType.COMPANY);
        if(clientFacade instanceof CompanyFacade){
            System.out.println("company");
            CompanyFacade companyFacade=(CompanyFacade) clientFacade;
            System.out.println(companyFacade.getCompanyId());

            companyFacade.addCoupon(new Coupon(
                    0,
                    companyFacade.getCompanyId(),
                    Category.FOOD,
                    "amazing coupon",
                    "amazing description",
                    new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis() + 5* DBmanager.ONE_DAY),
                    false,
                    500,
                    10,
                    "picture"
            ));
            Company myCompany=companyFacade.getCompanyDetails();
            myCompany.setCoupons(companyFacade.getCompanyCoupons());
            companyFacade.deleteCoupon(100);
        }
        */

        //todo: how to work with customer facade
        /*
        clientFacade= loginManager.login("customer@customer.com","firstlast",ClientType.CUSTOMER);
        if(clientFacade instanceof CustomerFacade) {
            System.out.println("customer");
            CustomerFacade customerFacade = (CustomerFacade) clientFacade;
            System.out.println(customerFacade.getCustomerId());
            Customer myCustomer=customerFacade.getCustomerDetails();
            //customerFacade.purchaseCoupon();
            myCustomer.setCoupons(customerFacade.getCustomerCoupons());
            //System.out.println(myCustomer);
            //customerFacade.getAllCoupons().forEach(System.out::println);
            List<Coupon> viewCoupons=customerFacade.getAllCoupons();
            //viewCoupons.forEach(System.out::println);
            System.out.println(viewCoupons.get(3));
            customerFacade.purchaseCoupon(viewCoupons.get(3));
            customerFacade.purchaseCoupon(viewCoupons.get(3));


        }

         */




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
