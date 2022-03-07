package beans;

import beans.Company;
import exception.CouponSystemException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    static Company company;
    @BeforeClass
    public void init(){
        company=new Company(1,"testCompany","test@gmail.com","testPassword");
        //company.setCoupons();

    }

    @After
    public void reset(){
        company.setPassword("testPassword");
        company.setEmail("test@gmail.com");
    }

    @Test
    public void getId() throws CouponSystemException {
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
        assertEquals("testPassword", company.getPassword());
    }

    @Test
    void setPassword() {
        company.setPassword("change");
        assertEquals("change",company.getPassword());
    }
    /*
    @Test
    void getCoupons() {
    }

    @Test
    void setCoupons() {
    }

     */
}