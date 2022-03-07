package beans;

import db.DBmanager;
import exception.CouponSystemException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

//import static org.junit.jupiter.api.Assertions.*;

class CouponTest {
    static Coupon coupon;

    @BeforeClass
    public void init(){
        coupon = new Coupon(1,
                1,
                Category.FOOD,
                "title",
                "description",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+5* DBmanager.ONE_DAY),
                false,
                20,
                100,
                "image");
    }
    @Test
    void getId() {
        assertEquals(1,coupon.getId());
    }

    @Test(expected = CouponSystemException.class)
    void setId() throws CouponSystemException {
        coupon.setId(2);
    }

    @Test
    void getCompanyID() {
        assertEquals(1,coupon.getCompanyID());
    }

    @Test(expected = CouponSystemException.class)
    void setCompanyID() throws CouponSystemException {
        coupon.setCompanyID(2);
    }

    @Test
    void getCategory() {
        assertEquals(Category.FOOD,coupon.getCategory());
    }

    @Test
    void setCategory() {
        coupon.setCategory(Category.ELECTRICITY);
        assertEquals(Category.ELECTRICITY, coupon.getCategory());
    }

    @Test
    void getTitle() {
        assertEquals("title",coupon.getTitle());
    }

    @Test
    void setTitle() {
        coupon.setTitle("newTitle");
        assertEquals("newTitle", coupon.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("description", coupon.getDescription());
    }

    @Test
    void setDescription() {
        coupon.setDescription("newDescription");
        assertEquals("newDescription", coupon.getDescription());
    }

    @Test
    void getStartDate() {
        assertEquals(new Date(System.currentTimeMillis()), coupon.getStartDate());
    }

    @Test
    void setStartDate() {
        coupon.setStartDate(new Date(System.currentTimeMillis()+DBmanager.ONE_DAY));
        assertEquals(new Date(System.currentTimeMillis()+DBmanager.ONE_DAY), coupon.getStartDate());
    }

    @Test
    void getEndDate() {
        assertEquals(new Date(System.currentTimeMillis()+5*DBmanager.ONE_DAY), coupon.getEndDate());
    }

    @Test
    void setEndDate() {
        coupon.setEndDate(new Date(System.currentTimeMillis()));
    }

    @Test
    void isExpired() {
        assertFalse(coupon.isExpired());
    }

    @Test
    void setExpired() {
    }

    @Test
    void getAmount() {
        assertEquals(20, coupon.getAmount());
    }

    @Test
    void setAmount() {
    }

    @Test
    void getPrice() {
        assertEquals(100, coupon.getPrice());
    }

    @Test
    void setPrice() {
    }

    @Test
    void getImage() {
        assertEquals("image", coupon.getImage());
    }

    @Test
    void setImage() {
    }

    @After
    public void reset(){
       coupon.setCategory(Category.FOOD);
       coupon.setTitle("title");
        coupon.setDescription("description");
        coupon.setStartDate(new Date(System.currentTimeMillis()));


    }
}