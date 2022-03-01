package facade;

import beans.ClientType;
import db.DBUtils;
import db.DBmanager;

public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }
    //todo: Finish the login logic later
    /*
    package.facade;

import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import dao.CompaniesDAO;
import dao.CouponsDAO;
import dao.CustomersDAO;
import beans.ClientType;
import exception.CouponSystemException;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

public class LoginManager {

    private static LoginManager instance = new LoginManager();
    private CustomersDAO customersDAO;
    private CompaniesDAO companiesDAO;
    private CouponsDAO couponsDAO;

    public static LoginManager getInstance() {
        return instance;
    }

    private LoginManager() {
        customersDAO = new CustomersDBDAO();
        companiesDAO = new CompaniesDBDAO();
        couponsDAO = new CouponsDBDAO();
    }

    public ClientFacade login(String email, String password, ClientType clientType) {
        ClientFacade clientFacade = null;
        switch (clientType) {
            case ADMINISTRATOR:
                if (clientFacade.login(email, password)){
                    clientFacade = new AdminFacade();
                    return clientFacade;
                }
            case COMPANY:
                if (clientFacade.login(email, password)){
                    clientFacade = new CompanyFacade();
                    return clientFacade;
                }
            case CUSTOMER:
                if (clientFacade.login(email, password)){
                    clientFacade = new CustomerFacade();
                    return clientFacade;
                }
        }
        return clientFacade;
    }


     */

}
