package dbdao;

import beans.Company;
import beans.Coupon;
import dao.CompaniesDAO;
import db.DBUtils;
import db.DBmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {
    @Override
    public boolean isCompanyExists(String email, String password) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBUtils.runQueryForResult(DBmanager.LOGIN_COMPANY, values);
        try {
            assert resultSet != null;
            resultSet.next();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    private void checkInfoAlreadyExists(String sql, String checkValue) throws SQLException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,checkValue);
        ResultSet resultSet=DBUtils.runQueryForResult(sql,values);
        assert resultSet!=null;
        if(resultSet.next()){
            if(resultSet.getString(1).equals(checkValue)){
                //todo:throw coupon system exception
                return;
            }
        }
    }

     */

    @Override
    public void addCompany(Company company) {
        Map<Integer, Object> values = new HashMap<>();
        ResultSet resultSet;
        try {

            values.put(1, company.getEmail());
            values.put(2,company.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(company.getEmail()))
                    //todo: throw company email already exists exception
                    return;
            }


            values.clear();
            /*
            if(isCompanyExists(company.getEmail(), company.getPassword())){
                //todo: throw exception
                return;
            }

             */
            values.put(1, company.getName());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_NAME_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("name").equals(company.getName())) {
                    //todo: throw company name already exists exception
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        values.clear();
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        values.put(3, company.getPassword());
        System.out.println((DBUtils.runQuery(DBmanager.CREATE_NEW_COMPANY, values) ?
                "Company created successfully" :
                "Company creation failed"));
    }

    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> values = new HashMap<>();
        ResultSet resultSet;
        //todo: find out why the email check doesn't work properly
        try {
            values.put(1, company.getEmail());
            values.put(2, company.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(company.getEmail()))
                    //todo: throw company email already exists exception
                    return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        values.clear();

        /*
        if(isCompanyExists(company.getEmail(), company.getPassword())){
            //todo: throw exception
            return;
        }

         */
        values.put(1, company.getEmail());
        values.put(2, company.getPassword());
        values.put(3, company.getId());
        System.out.println((DBUtils.runQuery(DBmanager.UPDATE_COMPANY_BY_ID, values) ?
                "Company updated successfully" :
                "Company update failed"));
    }

    @Override
    public void deleteCompany(int companyId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);

        System.out.println((DBUtils.runQuery(DBmanager.DELETE_COMPANY_BY_ID, values) ?
                "Company deleted successfully" :
                "Company deletion failed"));
    }

    @Override
    public List<Company> getAllCompanies(String sql, Map<Integer, Object> values) {
        List<Company> companies = new ArrayList<>();
        ResultSet resultSet = DBUtils.runQueryForResult(sql, values);
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                Company company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                companies.add(company);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return companies;
    }

    @Override
    public Company getOneCompany(int companyId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        List<Company> companies = getAllCompanies(DBmanager.GET_COMPANY_BY_ID, values);
        if (companies == null) {
            //todo: throw company doesn't exists exception
            return null;
        }
        return companies.get(0);
    }
}
