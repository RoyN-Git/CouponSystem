package beans;

import exception.CouponSystemException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    static Customer customer;

    @BeforeClass
    public void init(){
        customer=new Customer(
                1,
                "test first name",
                "test last name",
                "test@email.com",
                "testPassword");
    }

    @After
    public void reset(){
        customer.setPassword("testPassword");
        customer.setEmail("test@email.com");
        customer.setLastName("test last name");
        customer.setFirstName("test first name");
    }

    @Test
    void getId() {
        assertEquals(1,customer.getId());
    }

    @Test(expected = CouponSystemException.class)
    void setId() throws CouponSystemException{
        customer.setId(2);
    }

    @Test
    void getFirstName() {
        assertEquals("test first name",customer.getFirstName());
    }

    @Test
    void setFirstName() {
        customer.setFirstName("change");
        assertEquals("change",customer.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("test last name",customer.getLastName());
    }

    @Test
    void setLastName() {
        customer.setFirstName("change");
        assertEquals("change",customer.getLastName());
    }

    @Test
    void getEmail() {
        assertEquals("test@email.com",customer.getEmail());
    }

    @Test
    void setEmail() {
        customer.setEmail("change@email.cpm");
        assertEquals("change@email.com",customer.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("testPassword",customer.getPassword());
    }

    @Test
    void setPassword() {
        customer.setPassword("change");
        assertEquals("change",customer.getPassword());
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