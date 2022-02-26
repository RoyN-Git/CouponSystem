package beans;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Coupon {
    private final int id;
    private final int companyID;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean expired;
    private int amount;
    private double price;
    private String image;


    /**
     * Full constructor
     * @param id is coupon's id
     * @param companyID is the id of the coupon's company
     * @param category is the category of the coupon
     * @param title is he title of the coupon
     * @param description is the description of the coupon
     * @param startDate is the date in which the coupon was created
     * @param endDate is the day in which the coupon will be removed
     * @param expired is true when today is after endDate
     * @param amount is the amount of the available coupons to purchase
     * @param price is the price of a single coupon
     * @param image is the string of the file
     */
    public Coupon(int id, int companyID, Category category, String title, String description, Date startDate, Date endDate, boolean expired, int amount, double price, String image) {
        this.id = id;
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expired=expired;
        setAmount(amount);
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getCompanyID() {
        return companyID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        if(this.amount<=0){
            this.amount=0;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        SimpleDateFormat myFormatObj=new SimpleDateFormat("dd/MM/yyyy");
        String formattedStartDate = myFormatObj.format(this.startDate);
        String formattedEndDate = myFormatObj.format(this.endDate);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: ").append(this.id).append("\t");
        stringBuilder.append("Company: ").append(this.companyID).append("\t");
        stringBuilder.append("Category: ").append(this.category.getName()).append("\n");
        stringBuilder.append(this.title).append("\n").append(this.description).append("\n");
        if(!this.expired) {
            stringBuilder.append("start date: ").append(formattedStartDate).append("\t");
            stringBuilder.append("amount left: ").append(this.amount).append("\n");
            stringBuilder.append("end date:   ").append(formattedEndDate).append("\t");
            stringBuilder.append("price: ").append(this.price).append("$\n");
        }else{
            stringBuilder.append(expiredBanner());
        }
        return stringBuilder.toString();

    }

    private String expiredBanner(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ###### #    # #####  # #####  ###### #####  \n");
        stringBuilder.append(" #       #  #  #    # # #    # #      #    # \n");
        stringBuilder.append(" #####    ##   #    # # #    # #####  #    # \n");
        stringBuilder.append(" #        ##   #####  # #####  #      #    # \n");
        stringBuilder.append(" #       #  #  #      # #   #  #      #    # \n");
        stringBuilder.append(" ###### #    # #      # #    # ###### #####  \n");
        return stringBuilder.toString();
    }
}