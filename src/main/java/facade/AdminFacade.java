package facade;

import beans.Company;
import db.DBmanager;

public class AdminFacade extends ClientFacade{

    @Override
    public boolean login(String email, String password) {
        return DBmanager.ADMIN_EMAIL.equals(email)&&DBmanager.ADMIN_PASSWORD.equals(password);
    }

    public AdminFacade() {
        super();
    }

    public void addCompany(Company company){
        companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company){
        companiesDAO.updateCompany(company);
    }

    public void deleteCompany(Company company){
        companiesDAO.deleteCompany(company.getId());
    }
}
