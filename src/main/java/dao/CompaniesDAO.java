package dao;

public interface CompaniesDAO {
    //todo: Remove from comment when merging Company class
    boolean isCompanyExists(String email, String password);
    //void addCompany(Company company);
    //void updateCompany(Company company);
    void deleteCompany(int companyId);
    //List<Company> getAllCompanies();
    //Company getOneCompany(int companyId);
}
