package beans;

import exception.CouponSystemException;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    @BeforeClass
    public void init(){
        Company company=new Company(0,"testCompany","test@gmail.com","testPassword");

    }

    @Test
    void getId() {

    }

    @Test
    void setId() throws CouponSystemException{
    }

    @Test
    void getName() {
    }

    @Test
    void setName() throws CouponSystemException{
    }

    @Test
    void getEmail() {
    }

    @Test
    void setEmail() {
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