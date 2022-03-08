package test;

import beans.*;
import dao.CouponsDAO;
import db.ConnectionPool;
import db.DBUtils;
import db.DBmanager;
import dbdao.CouponsDBDAO;
import facade.*;
import jobs.CouponExpirationDailyJob;

import javax.management.ObjectName;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private CouponExpirationDailyJob couponExpirationDailyJob;
    private Thread thread;
//    private static boolean isSuccess;

    public Test()  {
        dropDataBase();
        createDataBase();
        this.couponExpirationDailyJob = new CouponExpirationDailyJob();
        thread = new Thread(couponExpirationDailyJob);
        thread.start();
        loginMangerAdministrator();
        loginMangerCompany();
        loginMangerCustomer();
        wrongLogin();
        //in order to see the coupon change to expired, make lines 33-40 as a comment :)
        try {
            ConnectionPool.getInstance().closeAllConnections();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        couponExpirationDailyJob.stopSystem();
        thread.interrupt();
        endSystem();


    }

    public void loginMangerAdministrator(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        clientFacade= loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);
        if(clientFacade instanceof AdminFacade) {
            AdminFacade adminFacade = (AdminFacade) clientFacade;

            Company golan = new Company("golan", "golan@gmail.com", "122333");
            adminFacade.addCompany(golan);

            Company newGolan = adminFacade.getOneCompany(1);
            newGolan.setEmail("golanNew@gmail.com");
            newGolan.setPassword("dfr43d33r");
            adminFacade.updateCompany(newGolan);

            Company sahar = new Company("sahar", "sahar@gmail.com", "64576gs");
            adminFacade.addCompany(sahar);

            Company roy = new Company("roy", "roy@gmail.com", "fsgth54");
            adminFacade.addCompany(roy);

            Company shahar = new Company("shahar", "shahar@gmail.com", "fsf45");
            adminFacade.addCompany(shahar);

            Company cloneCompany = new Company( "shahar", "shahar@gmail.com", "fsf45");
            adminFacade.addCompany(cloneCompany);


            List<Company> companyList=adminFacade.getAllCompanies();

            adminFacade.deleteCompany(companyList.get(2).getId());

            for (Company company : companyList) {
                System.out.println(company);
            }

            System.out.println(adminFacade.getOneCompany(2));

            Customer golanC = new Customer( "golan", "klein", "golanC@walla.com", "12345678");
            Customer c2 = new Customer("grdg", "fredse", "frfd@walla.com", "fr44b5");
            Customer c3 = new Customer("fsfre", "efrfwfwee", "hjmkj@walla.com", "fffrf44br43");
            Customer c4 = new Customer("gfgrdgtr", "hbtyhry", "gtrmhrtmk@walla.com", "65et3ferg3");
            Customer c5 = new Customer("dnewufn", "frejnfre", "gtgter@walla.com", "grege3");
            Customer cloneCustomer = new Customer("clone", "clone", "gtgter@walla.com", "clone");

            adminFacade.addCustomer(golanC);
            adminFacade.addCustomer(c2);
            adminFacade.addCustomer(c3);
            adminFacade.addCustomer(c4);
            adminFacade.addCustomer(c5);
            adminFacade.addCustomer(cloneCustomer);

            Customer golanNew=adminFacade.getOneCustomer(1);
            golanNew.setPassword("gtrgerf");
            golanNew.setEmail("change@gmail.com");
            golanNew.setLastName("change");
            adminFacade.updateCustomer(golanNew);

            List<Customer> customerList=adminFacade.getAllCustomers();

            adminFacade.deleteCustomer(customerList.get(2).getId());

            for (Customer customer : customerList) {
                System.out.println(customer);
            }

            System.out.println(adminFacade.getOneCustomer(4));
        }


    }

    public void loginMangerCompany(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        clientFacade= loginManager.login("golanNew@gmail.com","dfr43d33r", ClientType.COMPANY);
        if(clientFacade instanceof CompanyFacade) {
            CompanyFacade companyFacade = (CompanyFacade) clientFacade;

            List<Coupon> coupons = new ArrayList<>();
            coupons.add(new Coupon(
                    companyFacade.getCompanyId(),
                    Category.FOOD,
                    "hi hi hi",
                    "just by me",
                    10,
                    50,
                    200,
                    "picture"));
            coupons.add(new Coupon(
                    companyFacade.getCompanyId(),
                    Category.ELECTRICITY,
                    "check expired coupon",
                    "its a coupon",
                    5,
                    30,
                    70,
                    "picture"));
            coupons.add(new Coupon(
                    companyFacade.getCompanyId(),
                    Category.FOOD,
                    "new",
                    "its a new coupon",
                    9,
                    40,
                    3434,
                    "picture"));
            coupons.add(new Coupon(
                    0,
                    companyFacade.getCompanyId(),
                    Category.RESTAURANT,
                    "expired coupon",
                    "to check expired",
                    new Date(System.currentTimeMillis()-10* DBmanager.ONE_DAY),
                    new Date(System.currentTimeMillis()-9*DBmanager.ONE_DAY),
                    false,
                    100,
                    100,
                    "picture"));

            for (Coupon item :coupons) {
                companyFacade.addCoupon(item);
            }

            List<Coupon> coupons2 = companyFacade.getCompanyCoupons();
            coupons2.forEach(System.out::println);
            coupons2.get(1).setImage("new image");

            companyFacade.updateCoupon(coupons2.get(1));

            companyFacade.deleteCoupon(coupons2.get(2).getId());

            System.out.println("All company coupons");
            for (Coupon coupon : companyFacade.getCompanyCoupons()) {
                System.out.println(coupon);
            }
            System.out.println("Company coupons by category");
            for (Coupon coupon : companyFacade.getCompanyCoupons(Category.ELECTRICITY)) {
                System.out.println(coupon);
            }

            System.out.println("Compant coupons by price");
            for (Coupon coupon : companyFacade.getCompanyCoupons(700)) {
                System.out.println(coupon);
            }

//        System.out.println(companyFacade.getCompanyDetails());
            Company company = companyFacade.getCompanyDetails();
            company.setCoupons(companyFacade.getCompanyCoupons());
            System.out.println(company);
        }

    }

    public void loginMangerCustomer(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        clientFacade= loginManager.login("frfd@walla.com","fr44b5", ClientType.CUSTOMER);
        if(clientFacade instanceof CustomerFacade) {
            CustomerFacade customerFacade = (CustomerFacade) clientFacade;

            // todo : add query for get all coupons
            List<Coupon> coupons = customerFacade.getAllCoupons();
            System.out.println("-------------------");
            for (Coupon coupon : coupons) {
                System.out.println(coupon);
            }
//        customerGolan.getCoupons().add(coupons.get(0));  // todo : maybe make a new method  ( buy new coupon)
            // testing a not exists coupon
            //Coupon coupon = new Coupon(12,4,Category.ELECTRICITY,"nnnn","aaaaa",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis() + 9 * DBmanager.ONE_DAY),false,3,33.3,"dadadf");
//        customerFacade.purchaseCoupon(coupons.get(0));
            // trying purchase coupon that not exist
            //customerFacade.purchaseCoupon(coupon);

            //purchase all coupons


            for (Coupon item : coupons) {
                customerFacade.purchaseCoupon(item);
            }

            List<Coupon> customerCoupons = customerFacade.getCustomerCoupons();
            System.out.println("Customer coupons");
            customerCoupons.forEach(System.out::println);

            List<Coupon> customerCouponsByCategory = customerFacade.getCustomerCoupons(Category.FOOD);
            System.out.println("Customer coupons by category");
            customerCouponsByCategory.forEach(System.out::println);

            List<Coupon> customerCouponsByPrice = customerFacade.getCustomerCoupons(300);
            System.out.println("Customer coupons max price");
            customerCouponsByPrice.forEach(System.out::println);

            Customer customerGolan = customerFacade.getCustomerDetails();
            customerGolan.setCoupons(customerCoupons);
            System.out.println(customerGolan);


        }

    }

    public void wrongLogin(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        clientFacade= loginManager.login("wrong@wrong.com","wrongPassword", ClientType.CUSTOMER);
        if(clientFacade instanceof CustomerFacade){
            CustomerFacade customerFacade=(CustomerFacade) clientFacade ;
            System.out.println(customerFacade.getCustomerId());
        }
    }

    public  void createDataBase() {
        boolean isSuccess = DBUtils.runQuery(DBmanager.CREATE_DB);
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
        CouponsDAO couponsDAO = new CouponsDBDAO();
        for (Category item:Category.values()) {
            couponsDAO.addCategory(item);

        }
    }

    public static void dropDataBase() {
        boolean isSuccess = DBUtils.runQuery(DBmanager.DROP_DB);
        System.out.println(isSuccess ?
                "delete db successfully" :
                "delete failed");
    }

    public void endSystem(){
        System.exit(0);
    }


}