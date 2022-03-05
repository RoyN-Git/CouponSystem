package beans;

import exception.CouponSystemException;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Coupon> coupons;

    public Company(){}

    /**
     * Constructor without receiving list of coupons.
     * The list is initialized inside the constructor.
     * @param id is company's id
     * @param name is company's name
     * @param email is company's email
     * @param password is company's password
     */
    public Company(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws CouponSystemException {
        throw new CouponSystemException(ErrorType.VALUE_CANNOT_BE_CHANGED.getMessage());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws CouponSystemException {
        throw new CouponSystemException(ErrorType.VALUE_CANNOT_BE_CHANGED.getMessage());
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
        if (password.equals("")){
            System.out.println("invalid password !");
        }
        else {
            this.password = password;
        }
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Company name: ").append(this.name).append("\tid: ").append(this.id).append("\n");
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
