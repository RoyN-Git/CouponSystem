package beans;

import java.util.ArrayList;
import java.util.List;

public class Company {

    //todo: Reorganize fields id and name as final and and rebuild c'tors
    private /*final*/ int id;
    private /*final*/ String name;
    private String email;
    private String password;
    private List<Coupon> coupons;

    public Company() {
    }

    /*Full c'tor
    public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.coupons = coupons;
    }
     */

    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Company(String name, String email, String password, ArrayList<Coupon> coupons) {
        this(name, email, password);
        this.coupons = coupons;
    }

    public Company(int id, String name, String email, String password) {
        this(name, email, password);
        this.id = id;
    }

    public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
        this(id, name, email, password);
        this.coupons = coupons;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //todo:Check if the email doesn't belong to another company
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return String.format("Company [id=%s, name=%s, email=%s, password=%s, coupons=%s]", id, name, email, password,
                coupons);
    }

}
