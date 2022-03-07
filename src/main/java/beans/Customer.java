package beans;

import exception.CouponSystemException;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Coupon> coupons;

    public Customer(){}

    /**
     * Constructor without receiving list of coupons.
     * The list is initialized inside the constructor.
     * This constructor is used when receiving customer from the database.
     * @param id is the customer's id
     * @param firstName is the first name of the customer
     * @param lastName is the last name of the customer
     * @param email is the email of the customer
     * @param password is the password of the customer
     */
    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
        setPassword(password);
        this.coupons = new ArrayList<>();
    }

    /**
     * This constructor is used when creating new customer.
     * @param firstName is the first name of the customer
     * @param lastName is the last name of the customer
     * @param email is the email of the customer
     * @param password is the password of the customer
     */
    public Customer(String firstName, String lastName, String email, String password) {
        this(0,firstName,lastName,email,password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws CouponSystemException {
        throw new CouponSystemException(ErrorType.VALUE_CANNOT_BE_CHANGED.getMessage());
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
        if (email.equals("")){
            System.out.println("invalid email !");
        }
        else {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.equals("")){
            System.out.println("invalid password");
        }
        else {
            this.password = password;
        }
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
