package facade;

import beans.ClientType;
import beans.ErrorType;
import db.DBUtils;
import db.DBmanager;
import exception.CouponSystemException;

public class LoginManager {
    private static LoginManager instance = null;

    
    private LoginManager() {
    }

    /**
     * Creates an instance of the login manager if not null.
     * @return the created instance.
     */
    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    /**
     * This method manages the login to the system.
     * It uses the clientType param to call the right login method for each type of client:
     * administrator, company and customer.
     * @param email is the email the user typed in.
     * @param password is the password the user typed in.
     * @param clientType is the client type that the user wants to log in as.
     * @return the corresponding clientFacade to the clientType on successful login,
     *         null when the login failed.
     */
    public ClientFacade login(String email, String password, ClientType clientType) {
        AdminFacade adminFacade=new AdminFacade();
        CompanyFacade companyFacade=new CompanyFacade();
        CustomerFacade customerFacade=new CustomerFacade();
        try {
            switch (clientType) {
                case ADMINISTRATOR:
                    if (adminFacade.login(email, password)) {
                        return adminFacade;
                    }else{
                        throw new CouponSystemException(ErrorType.AUTHENTICATION_FAILED.getMessage());
                    }
                case COMPANY:
                    if (companyFacade.login(email, password)) {
                        return companyFacade;
                    }else{
                        throw new CouponSystemException(ErrorType.AUTHENTICATION_FAILED.getMessage());
                    }
                case CUSTOMER:
                    if (customerFacade.login(email, password)) {
                        return customerFacade;
                    }else{
                        throw new CouponSystemException(ErrorType.AUTHENTICATION_FAILED.getMessage());
                    }
            }
        }catch (CouponSystemException e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

}
