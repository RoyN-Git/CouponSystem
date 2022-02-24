package beans;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final int id;
    private final String name;
    private String email;
    private String password;
    private List<Coupon> coupons;

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
