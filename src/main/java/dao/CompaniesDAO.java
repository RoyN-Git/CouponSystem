package dao;

import beans.Company;

import java.util.List;

public interface CompaniesDAO {
    boolean isCompanyExists(String email, String password);
    void addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(int companyId);
    List<Company> getAllCompanies();
    Company getOneCompany(int companyId);
}
