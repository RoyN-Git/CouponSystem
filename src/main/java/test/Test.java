package test;

import beans.*;
import dao.CouponsDAO;
import db.ConnectionPool;
import db.DBUtils;
import db.DBmanager;
import dbdao.CouponsDBDAO;
import facade.*;
import jobs.CouponExpirationDailyJob;

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
//        loginMangerCompany();
//        loginMangerCustomer();
        try {
            ConnectionPool.getInstance().closeAllConnections();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        thread.interrupt();
        endSystem();

    }

    public void loginMangerAdministrator(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        clientFacade= loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);
        AdminFacade adminFacade=(AdminFacade) clientFacade;
        Company golan = new Company(1,"golan","golan@gmail.com","122333");
        adminFacade.addCompany(golan);
//        golan.setEmail("golanNew@gmail.com");
//        golan.setPassword("104045342332");
        Company newGolan = adminFacade.getOneCompany(1);
        newGolan.setEmail("golanNew@gmail.com");
        newGolan.setPassword("dfr43d33r");
        adminFacade.updateCompany(newGolan);

        Company sahar = new Company(2,"sahar","sahar@gmail.com","64576gs");
        adminFacade.addCompany(sahar);
        sahar.setEmail("saharNew@gmail.com");
        sahar.setPassword("gfh5t5tg");
        adminFacade.updateCompany(sahar);

        Company roy = new Company(3,"roy","roy@gmail.com","fsgth54");
        adminFacade.addCompany(roy);
        roy.setEmail("royNew@gmail.com");
        roy.setPassword("32445687gefer");
        adminFacade.updateCompany(roy);

        Company shahar = new Company(4,"shahar","shahar@gmail.com","fsf45");
        adminFacade.addCompany(shahar);
        shahar.setEmail("shaharNew@gmail.com");
        shahar.setPassword("fruefw35g");
        adminFacade.updateCompany(shahar);

        adminFacade.deleteCompany(adminFacade.getOneCompany(1).getId());

        for (Company company:adminFacade.getAllCompanies()) {
            System.out.println(company);
        }

        System.out.println(adminFacade.getOneCompany(2));

        Customer golanC = new Customer(1,"golan","klein","golanC@walla.com","12345678");
        Customer c2 = new Customer(2,"grdg","fredse","frfd@walla.com","fr44b5");
        Customer c3 = new Customer(3,"fsfre","efrfwfwee","hjmkj@walla.com","fffrf44br43");
        Customer c4 = new Customer(1,"gfgrdgtr","hbtyhry","gtrmhrtmk@walla.com","65et3ferg3");
        Customer c5 = new Customer(1,"dnewufn","frejnfre","gtgter@walla.com","grege3");

        adminFacade.addCustomer(golanC);
        adminFacade.addCustomer(c2);
        adminFacade.addCustomer(c3);
        adminFacade.addCustomer(c4);
        adminFacade.addCustomer(c5);

        c3.setPassword("gtrgerf");
        c3.setEmail("change@gmail.com");
        c3.setLastName("change");

        adminFacade.updateCustomer(c3);

        adminFacade.deleteCustomer(c2.getId());

        for (Customer customer:adminFacade.getAllCustomers()){
            System.out.println(customer);
        }

        System.out.println(adminFacade.getOneCustomer(4));


    }

    public void loginMangerCompany(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        clientFacade= loginManager.login("saharNew@gmail.com","gfh5t5tg", ClientType.COMPANY);
        CompanyFacade companyFacade =(CompanyFacade) clientFacade;

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(
                0,
                2,
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
                2,
                Category.getCategoryByValue(2),
                "check expired coupon",
                "its a coupon",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 5* DBmanager.ONE_DAY),
                false,
                30,
                70,
                "picture"));
        coupons.add(new Coupon(
                0,
                2,
                Category.getCategoryByValue(1),
                "new",
                "its a new coupon",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 7* DBmanager.ONE_DAY),
                false,
                40,
                3434,
                "picture"));

        companyFacade.addCoupon(coupons.get(0));
        companyFacade.addCoupon(coupons.get(1));
        companyFacade.addCoupon(coupons.get(2));

        List<Coupon> coupons2 = companyFacade.getCompanyCoupons();
        coupons2.forEach(System.out::println);
        coupons2.get(1).setImage("new image");

        companyFacade.updateCoupon(coupons2.get(1));  // todo: ask zeev

        companyFacade.deleteCoupon(coupons2.get(2).getId());

        System.out.println(" all coupons");
        for (Coupon coupon:companyFacade.getCompanyCoupons()){
            System.out.println(coupon);
        }
        System.out.println("coupons by category");
        for (Coupon coupon:companyFacade.getCompanyCoupons(Category.getCategoryByValue(2))){
            System.out.println(coupon);
        }

        System.out.println("coupons by price");
        for (Coupon coupon:companyFacade.getCompanyCoupons(700)){
            System.out.println(coupon);
        }

//        System.out.println(companyFacade.getCompanyDetails());
        Company company = companyFacade.getCompanyDetails();
        company.setCoupons(companyFacade.getCompanyCoupons());
        System.out.println(company);

    }

    public void loginMangerCustomer(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        // todo : take care when login failed
        clientFacade= loginManager.login("golanC@walla.com","12345678", ClientType.CUSTOMER);
        CustomerFacade customerFacade =(CustomerFacade) clientFacade;

        // todo : add query for get all coupons
        List<Coupon> coupons =
        customerFacade.getAllCoupons();
        System.out.println("-------------------");
        for (Coupon coupon:coupons) {
            System.out.println(coupon);
        }
//        customerGolan.getCoupons().add(coupons.get(0));  // todo : maybe make a new method  ( buy new coupon)
        // testing a not exists coupon
        Coupon coupon = new Coupon(12,4,Category.ELECTRICITY,"nnnn","aaaaa",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis() + 9 * DBmanager.ONE_DAY),false,3,33.3,"dadadf");
//        customerFacade.purchaseCoupon(coupons.get(0));
        // trying purchase coupon that not exist
        customerFacade.purchaseCoupon(coupon);
        List<Coupon> customerCoupons = customerFacade.getCustomerCoupons();
        customerCoupons.forEach(System.out::println);

        List<Coupon> customerCouponsByCatgory = customerFacade.getCustomerCoupons(Category.FOOD);
        customerCouponsByCatgory.forEach(System.out::println);

        List<Coupon> customerCouponsByPrice = customerFacade.getCustomerCoupons(300);
        customerCouponsByPrice.forEach(System.out::println);

        Customer customerGolan = customerFacade.getCustomerDetails();
        System.out.println(customerGolan);




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
