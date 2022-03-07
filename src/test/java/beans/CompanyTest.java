package beans;

import beans.Company;
import exception.CouponSystemException;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    public static Company company;
    @BeforeClass
    public void init(){
        company=new Company(1,"testCompany","test@gmail.com","testPassword");

    }

    @Test
    void getId() throws CouponSystemException {
        assertEquals(1,company.getId());
    }

    @Test(expected = CouponSystemException.class)
    void setId() throws CouponSystemException{
        company.setId(2);
    }

    @Test()
    void getName() {
        assertEquals("testCompany",company.getName());
    }

    @Test(expected=CouponSystemException.class)
    void setName() throws CouponSystemException{
        company.setName("change");
    }

    @Test
    void getEmail() {
        assertEquals("test@gmail.com",company.getEmail());
    }

    @Test
    void setEmail() {
        company.setEmail("change@change.com");
        assertEquals("change@change.com",company.getEmail());
    }

    @Test
    void getPassword() {
    }

    @Test
    void setPassword() {
    }

    @Test
    void getCoupons() {
    }

    @Test
    void setCoupons() {
    }
}