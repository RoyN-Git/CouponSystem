package facade;

import beans.ClientType;
import db.DBUtils;
import db.DBmanager;
import exception.CouponSystemException;

public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }



    public ClientFacade login(String email, String password, ClientType clientType) {
        AdminFacade adminFacade=new AdminFacade();
        CompanyFacade companyFacade=new CompanyFacade();
        CustomerFacade customerFacade=new CustomerFacade();
        switch (clientType) {
            case ADMINISTRATOR:
                if (adminFacade.login(email, password)) {
                    return adminFacade;
                }
            case COMPANY:
                if (companyFacade.login(email, password)) {
                    return companyFacade;
                }
            case CUSTOMER:
                if (customerFacade.login(email, password)) {
                    return customerFacade;
                }
        }
        return null;
    }

    
}

