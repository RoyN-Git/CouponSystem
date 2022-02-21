package beans;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Coupon> coupons;

    /**
     * Full constructor
     * @param id is the customer's id
     * @param firstName is the first name of the customer
     * @param lastName is the last name of the customer
     * @param email is the email of the customer
     * @param password is the password of the customer
     * @param coupons is the list of coupons the customer has purchased
     */
    public Customer(int id, String firstName, String lastName, String email, String password, List<Coupon> coupons) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //todo:First check if the email doesn't belong to another customer
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return this.coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: ").append(this.id).append("\t");
        stringBuilder.append("First name: ").append(this.firstName).append("\t");
        stringBuilder.append("Last name: ").append(this.lastName).append("\n");
        stringBuilder.append("email: ").append(this.email).append("\n");
        stringBuilder.append("Coupons: ").append("\n");
        stringBuilder.append("-----------------");
        for (Coupon item:this.coupons) {
            stringBuilder.append("\n");
            stringBuilder.append(item.toString());
            stringBuilder.append("-----------------");
        }
        return stringBuilder.toString();
    }
}
