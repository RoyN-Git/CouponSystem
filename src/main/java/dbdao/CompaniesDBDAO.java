package dbdao;

import beans.Company;
import beans.Coupon;
import beans.ErrorType;
import dao.CompaniesDAO;
import db.DBUtils;
import db.DBmanager;
import exception.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
            if(resultSet.getInt("counter")==1) {
                return true;
            }else{
                throw new CouponSystemException(ErrorType.AUTHENTICATION_FAILED.getMessage());
            }

        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public void addCompany(Company company)  {
        Map<Integer, Object> values = new HashMap<>();
        /*
        //delete from here
        ResultSet resultSet;
        try {

            values.put(1, company.getEmail());
            values.put(2, company.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(company.getEmail()))
                    //throw new CouponSystemException(ErrorType.EMAIL_ALREADY_EXIST.getMessage());
                    return;
            }


            values.clear();

            values.put(1, company.getName());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_NAME_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("name").equals(company.getName())) {
                    //throw new CouponSystemException(ErrorType.NAME_ALREADY_EXIST.getMessage());
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        values.clear();
        //delete to here

         */
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        values.put(3, company.getPassword());
        try{
            if(DBUtils.runQuery(DBmanager.CREATE_NEW_COMPANY, values)){
                System.out.println("Company created successfully");
            }else{
                System.out.println("Company creation failed");
                throw new SQLIntegrityConstraintViolationException();
            }
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
        }
        /*
        System.out.println((DBUtils.runQuery(DBmanager.CREATE_NEW_COMPANY, values) ?
                "Company created successfully" :
                "Company creation failed"));

         */

    }

    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> values = new HashMap<>();
        /*
        //delete from here
        ResultSet resultSet;
        try {
            values.put(1, company.getEmail());
            values.put(2, company.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(company.getEmail()))
                    //throw new CouponSystemException(ErrorType.EMAIL_ALREADY_EXIST.getMessage());
                    return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        values.clear();
        //delete to here
         */
        values.put(1, company.getEmail());
        values.put(2, company.getPassword());
        values.put(3, company.getId());
        try {
            if(DBUtils.runQuery(DBmanager.UPDATE_COMPANY_BY_ID, values)){
                System.out.println("Company updated successfully");
            }else{
                System.out.println("Company update failed");
                throw new SQLIntegrityConstraintViolationException();
            }
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
        }
        /*
        System.out.println((DBUtils.runQuery(DBmanager.UPDATE_COMPANY_BY_ID, values) ?
                "Company updated successfully" :
                "Company update failed"));

         */
    }

    @Override
    public void deleteCompany(int companyId){
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        try {
            if(DBUtils.runQuery(DBmanager.DELETE_COMPANY_BY_ID, values)){
                System.out.println("Company deleted successfully");
            }else{
                System.out.println("Company deletion failed");
                throw new SQLException();
            }
        }catch (SQLException e){
            System.out.println(ErrorType.COMPANY_NOT_EXIST.getMessage());
        }
        /*
        System.out.println((DBUtils.runQuery(DBmanager.DELETE_COMPANY_BY_ID, values) ?
                "Company deleted successfully" :
                "Company deletion failed"));

         */
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
        try {
            if (companies.size() > 0) {
                return companies.get(0);
            } else {
                throw new CouponSystemException();
            }
        } catch (CouponSystemException e) {
            System.out.println(ErrorType.COMPANY_NOT_EXIST.getMessage());
            return null;
        }
    }
}
