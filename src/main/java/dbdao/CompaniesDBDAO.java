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
    /**
     * this method checks if the company exist based on its email and password.
     * the method is used when trying to log in as a company.
     * @param email is company email
     * @param password is company password
     * @return return true if company exist false if not.
     */
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


    /**
     * this method adds new company in tow the database.
     * the method first checks if there is no other company in the database with the same email or name,
     * if not, add the company to the database.
     * @param company is the company we want to add to the database.
     */
    @Override
    public void addCompany(Company company)  {
        Map<Integer, Object> values = new HashMap<>();

        //check constraints
        ResultSet resultSet;
        try {
            values.put(1, company.getEmail());
            values.put(2, company.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(company.getEmail()))
                    throw new CouponSystemException(ErrorType.EMAIL_ALREADY_EXIST.getMessage());
            }
            values.clear();

            values.put(1, company.getName());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_NAME_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("name").equals(company.getName())) {
                    throw new CouponSystemException(ErrorType.NAME_ALREADY_EXIST.getMessage());
                }
            }
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
            return;
        }

        values.clear();
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

    }

    /**
     * this method update an existing company in the database.
     * The function checks if there is another identical email in the database and then changes,
     * if not it updates the company.
     * @param company is the company that we want to update.
     */
    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> values = new HashMap<>();

        //check constraints
        ResultSet resultSet;
        try {
            values.put(1, company.getEmail());
            values.put(2, company.getId());
            resultSet = DBUtils.runQueryForResult(DBmanager.IS_COMPANY_EMAIL_EXISTS, values);
            assert resultSet != null;
            if (resultSet.next()) {
                if (resultSet.getString("email").equals(company.getEmail()))
                    throw new CouponSystemException(ErrorType.EMAIL_ALREADY_EXIST.getMessage());
            }
        } catch (SQLException |CouponSystemException e) {
            System.out.println(e.getMessage());
            return;
        }

        values.clear();
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
    }

    /**
     * this method deleted company by its id.
     * @param companyId its company id we want to delete.
     */
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
    }

    /**
     * this method get all companies from the database.
     * @param sql is a sql query.
     * @param values is the map of the values to insert to the query.
     * @return list of all the companies.
     */
    @Override
    public List<Company> getAllCompanies(String sql, Map<Integer, Object> values) {
        List<Company> companies = new ArrayList<>();
        ResultSet resultSet = DBUtils.runQueryForResult(sql, values);
        try {
            while (resultSet.next()) {
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

    /**
     * this method gets one company by its id from the database.
     * @param companyId the id of the company that we want to get.
     * @return
     */
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
