package facade;

import db.DBmanager;

public class AdminFacade extends ClientFacade{

    @Override
    public boolean login(String email, String password) {
        return DBmanager.ADMIN_EMAIL.equals(email)&&DBmanager.ADMIN_PASSWORD.equals(password);
    }
}
