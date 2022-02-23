package facade;

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
        //boolean isExist=;
        if(this.companiesDAO.isCompanyExists(email,password)){
            //setCompanyId();A
            return true;
        }
        return false;
    }
}
