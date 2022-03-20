package dao;

import beans.Company;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompaniesDAO {
    boolean isCompanyExists(String email, String password);
    void addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(int companyId) ;
    List<Company> getAllCompanies(String sql, Map<Integer,Object> values);
    Company getOneCompany(int companyId);
}

