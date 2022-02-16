package beans;

import java.util.ArrayList;

public class Customer {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Coupon> coupons;


    public Customer(int id, String firstName, String lastName, String email, String password /*,ArrayList<Coupon> coupons*/) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        //this.coupons = coupons;
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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
