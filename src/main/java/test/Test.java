package test;

import beans.ClientType;
import beans.Company;
import db.DBUtils;
import db.DBmanager;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.LoginManager;
import jobs.CouponExpirationDailyJob;

public class Test {
    private CouponExpirationDailyJob couponExpirationDailyJob;
    private Thread thread;
//    private static boolean isSuccess;

    public void Test(){
//        dropDataBase();
        createDataBase();
        thread = new Thread(couponExpirationDailyJob);
        thread.start();
        loginMangerAdministrator();


    }

    public void loginMangerAdministrator(){
        LoginManager loginManager=LoginManager.getInstance();
        ClientFacade clientFacade;
        clientFacade= loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);
        AdminFacade adminFacade=(AdminFacade) clientFacade;
        Company golan = new Company(1,"golan","golan@gmail.com","122333");
        adminFacade.addCompany(golan);
        golan.setEmail("golanNew@gmail.com");
        golan.setPassword("104045342332");
        adminFacade.updateCompany(golan);

        Company sahar = new Company(2,"sahar","sahar@gmail.com","64576gs");
        adminFacade.addCompany(sahar);
        golan.setEmail("saharNew@gmail.com");
        golan.setPassword("gfh5t5tg");
        adminFacade.updateCompany(sahar);

        Company roy = new Company(3,"roy","roy@gmail.com","fsgth54");
        adminFacade.addCompany(roy);
        golan.setEmail("royNew@gmail.com");
        golan.setPassword("32445687gefer");
        adminFacade.updateCompany(roy);

        Company shahar = new Company(4,"shahar","shahar@gmail.com","fsf45");
        adminFacade.addCompany(shahar);
        golan.setEmail("shaharNew@gmail.com");
        golan.setPassword("fruefw35g");
        adminFacade.updateCompany(shahar);

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

    }

    public static void dropDataBase() {
        boolean isSuccess = DBUtils.runQuery(DBmanager.DROP_DB);
        System.out.println(isSuccess ?
                "delete db successfully" :
                "delete failed");
    }


}
