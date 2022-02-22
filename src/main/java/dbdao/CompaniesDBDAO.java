package dbdao;

import beans.Company;
import dao.CompaniesDAO;

import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {
    @Override
    public boolean isCompanyExists(String email, String password) {
        return false;
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(int companyId) {

    }

    @Override
    public List<Company> getAllCompanies(String sql, Map<Integer, Object> values) {
        return null;
    }

    @Override
    public Company getOneCompany(int companyId) {
        return null;
    }
}
