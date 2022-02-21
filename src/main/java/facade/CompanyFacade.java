package facade;

public class CompanyFacade extends ClientFacade{
    private int companyId;

    public CompanyFacade(int companyId) {
        this.companyId = companyId;
    }
}
