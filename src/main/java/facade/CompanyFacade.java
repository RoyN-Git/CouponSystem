package facade;

import beans.Company;
import db.DBUtils;
import db.DBmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CompanyFacade extends ClientFacade{
    private int companyId;

    public CompanyFacade() {
        super();
        this.companyId = 0;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean login(String email, String password) {
        Map<Integer,Object> values=new HashMap<>();
        values.put(1,email);
        values.put(2,password);
        if(this.companiesDAO.isCompanyExists(email,password)){
            try {
                ResultSet resultSet= DBUtils.runQueryForResult(DBmanager.LOGIN_COMPANY,values);
                assert resultSet != null;
                if(resultSet.next()){
                    setCompanyId(resultSet.getInt("id"));
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //setCompanyId();A
            return true;
        }
        return false;
    }
}
