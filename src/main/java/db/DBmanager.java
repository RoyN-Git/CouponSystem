package db;

public class DBmanager {

    //sql connection
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String SQL_USER = "root";//change the user to your root username//admin
    public static final String SQL_PASSWORD = "12345678";//change password to your root password//Sa12345678!!

    //Calculating one day in milliseconds
    public static final int ONE_DAY = 1000 * 60 * 60 * 24;

    //creating database
    public static final String CREATE_DB = "CREATE SCHEMA IF NOT EXISTS `coupon_project`";
    public static final String DROP_DB = "DROP SCHEMA `coupon_project`";

    //Initiate tables
    public static final String CREATE_COMPANIES_TABLE = "CREATE TABLE IF NOT EXISTS `coupon_project`.`companies` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL UNIQUE," +
            "  `email` VARCHAR(45) NOT NULL UNIQUE," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS `coupon_project`.`customers` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `first_name` VARCHAR(45) NOT NULL," +
            "  `last_name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL UNIQUE," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS `coupon_project`.`categories` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL UNIQUE," +
            "  PRIMARY KEY (`id`));";

    public static final String CREATE_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS `coupon_project`.`coupons` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `company_id` INT NOT NULL," +
            "  `category_id` INT NOT NULL," +
            "  `title` VARCHAR(45) NOT NULL," +
            "  `description` VARCHAR(45) NOT NULL," +
            "  `start_date` DATE NOT NULL," +
            "  `end_date` DATE NOT NULL," +
            "  `expired` TINYINT NOT NULL," +
            "  `amount` INT NOT NULL," +
            "  `price` DOUBLE NOT NULL," +
            "  `image` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`)," +
            "  CONSTRAINT `uc_company_title` UNIQUE (`company_id`, `title`)," +
            "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE," +
            "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE," +
            "  CONSTRAINT `company_id`" +
            "    FOREIGN KEY (`company_id`)" +
            "    REFERENCES `coupon_project`.`companies` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE CASCADE," +
            "  CONSTRAINT `category_id`" +
            "    FOREIGN KEY (`category_id`)" +
            "    REFERENCES `coupon_project`.`categories` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE CASCADE)";

    public static final String CREATE_CUSTOMERS_VS_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS `coupon_project`.`customers_coupons` (" +
            "  `customer_id` INT NOT NULL," +
            "  `coupon_id` INT NOT NULL," +
            "  PRIMARY KEY (`customer_id`, `coupon_id`)," +
            "  CONSTRAINT `uc_customer_coupon` UNIQUE (`customer_id`, `coupon_id`)," +
            "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE," +
            "  CONSTRAINT `customer_id`" +
            "    FOREIGN KEY (`customer_id`)" +
            "    REFERENCES `coupon_project`.`customers` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE CASCADE," +
            "  CONSTRAINT `coupon_id`" +
            "    FOREIGN KEY (`coupon_id`)" +
            "    REFERENCES `coupon_project`.`coupons` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE CASCADE)";


    //Admin email and password
    public static final String ADMIN_EMAIL = "admin@admin.com";

    public static final String ADMIN_PASSWORD = "admin";

    //Login queries
    public static final String LOGIN_COMPANY = "SELECT count(*) AS counter, id" +
            " FROM `coupon_project`.`companies`" +
            " WHERE email=? AND password=?" +
            " GROUP BY id";

    public static final String LOGIN_CUSTOMER = "SELECT count(*) AS counter, id" +
            " FROM `coupon_project`.`customers`" +
            " WHERE email=? AND password=?" +
            " GROUP BY id";

    //Companies' queries
    public static final String CREATE_NEW_COMPANY = "INSERT INTO `coupon_project`.`companies`" +
            " (`name`,`email`,`password`)" +
            " VALUES (?,?,?)";


    public static final String IS_COMPANY_NAME_EXISTS = "SELECT name" +
            " FROM `coupon_project`.`companies`" +
            " WHERE name=?";


    public static final String IS_COMPANY_EMAIL_EXISTS = "SELECT email" +
            " FROM `coupon_project`.`companies`" +
            " WHERE email=? AND id !=?";

    public static final String DELETE_COMPANY_BY_ID = "DELETE FROM `coupon_project`.`companies` WHERE id=?";

    public static final String UPDATE_COMPANY_BY_ID = "UPDATE `coupon_project`.`companies`" +
            " SET email=?, password=?" +
            " WHERE id=?";

    public static final String GET_ALL_COMPANIES = "SELECT * FROM `coupon_project`.`companies`";


    public static final String GET_COMPANY_BY_ID = "SELECT * FROM `coupon_project`.`companies` WHERE id=?";

    //Customers' queries
    public static final String CREATE_NEW_CUSTOMER = "INSERT INTO `coupon_project`.`customers`" +
            " (`first_name`,`last_name`,`email`,`password`)" +
            " VALUES (?,?,?,?)";


    public static final String IS_CUSTOMER_EMAIL_EXISTS = "SELECT email" +
            " FROM `coupon_project`.`customers`" +
            " WHERE email=? AND id !=?";

    public static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM `coupon_project`.`customers` WHERE id=?";

    public static final String UPDATE_CUSTOMER_BY_ID = "UPDATE `coupon_project`.`customers`" +
            " SET first_name=?, last_name=?, email=?, password=?" +
            " WHERE id=?";

    public static final String GET_ALL_CUSTOMERS = "SELECT * FROM `coupon_project`.`customers`";


    public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM `coupon_project`.`customers` WHERE id=?";

    //Categories' queries
    public static final String CREATE_NEW_CATEGORY = "INSERT INTO `coupon_project`.`categories`" +
            " (`name`)" +
            " VALUES (?)";
    public static final String IS_CATEGORY_EXISTS = "SELECT * FROM `coupon_project`.`categories`" +
            " WHERE name=?";

    //Coupons' queries
    public static final String ADD_NEW_COUPON = "INSERT INTO `coupon_project`.`coupons`" +
            " (`company_id`,`category_id`,`title`,`description`,`start_date`,`end_date`,`expired`,`amount`,`price`,`image`)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?)";

    public static final String GET_ONE_COUPON = "SELECT *" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE id=?";


    public static final String IS_COUPON_TITLE_EXISTS = "SELECT title" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE title=? AND company_id=? AND id!=?";

    public static final String UPDATE_COUPON_BY_ID = "UPDATE `coupon_project`.`coupons`" +
            "SET category_id=?,title=?,description=?,start_date=?,end_date=?,expired=?,amount=?,price=?,image=?" +
            "WHERE id=?";

    public static final String DELETE_COUPON = "DELETE FROM `coupon_project`.`coupons` WHERE id=?";

    public static final String GET_ALL_COMPANY_COUPONS = "SELECT *" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE company_id=?" +
            " ORDER BY id";

    public static final String GET_ALL_COMPANY_COUPONS_BY_CATEGORY = "SELECT *" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE company_id=? AND category_id=?" +
            " ORDER BY id";

    public static final String GET_ALL_COMPANY_COUPONS_UP_TO_PRICE = "SELECT *" +
            "FROM `coupon_project`.`coupons`" +
            " WHERE company_id=? AND price<=?" +
            " ORDER BY id";



    public static final String COUPON_AMOUNT = "SELECT amount" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE id=?";


    public static final String COUPON_END_DATE = "SELECT end_date" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE id=?";

    public static final String GET_EXPIRED_COUPONS="SELECT *" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE end_date<CURDATE() AND expired=false";

    public static final String IS_COUPON_EXPIRED="SELECT expired, end_date" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE id=?";

    //customers vs coupons queries
    public static final String PURCHASE_COUPON = "INSERT INTO `coupon_project`.`customers_coupons`" +
            " (`customer_id`,`coupon_id`)" +
            " VALUES (?,?)";

    public static final String IS_COUPON_PURCHASED = "SELECT count(*) as counter" +
            " FROM `coupon_project`.`customers_coupons`" +
            " WHERE customer_id=? AND coupon_id=?";

    public static final String GET_ALL_CUSTOMER_COUPONS = "SELECT `coupon_project`.`coupons`.*" +
            " FROM `coupon_project`.`coupons`, `coupon_project`.`customers_coupons`" +
            " WHERE `coupon_project`.`customers_coupons`.customer_id=? " +
            " AND `coupon_project`.`coupons`.id=`coupon_project`.`customers_coupons`.coupon_id" +
            " ORDER BY id";

    public static final String GET_ALL_CUSTOMER_COUPONS_BY_CATEGORY = "SELECT `coupon_project`.`coupons`.*" +
            " FROM `coupon_project`.`coupons`, `coupon_project`.`customers_coupons`" +
            " WHERE `coupon_project`.`customers_coupons`.customer_id=? " +
            " AND `coupon_project`.`coupons`.category_id=?" +
            " AND `coupon_project`.`coupons`.id=`coupon_project`.`customers_coupons`.coupon_id" +
            " ORDER BY id";

    public static final String GET_ALL_CUSTOMER_COUPONS_UP_TO_PRICE = "SELECT `coupon_project`.`coupons`.*" +
            " FROM `coupon_project`.`coupons`, `coupon_project`.`customers_coupons`" +
            " WHERE `coupon_project`.`customers_coupons`.customer_id=?" +
            " AND `coupon_project`.`coupons`.price<=?" +
            " AND `coupon_project`.`coupons`.id=`coupon_project`.`customers_coupons`.coupon_id" +
            " ORDER BY id";

    public static final String DELETE_COUPON_PURCHASE = "DELETE FROM `coupon_project`.`customers_coupons`" +
            " WHERE customer_id=? AND coupon_id=?";

    public static final String VIEW_ALL_COUPONS="SELECT *" +
            " FROM `coupon_project`.`coupons`" +
            " WHERE expired=false"/*AND id NOT IN" +
            " (SELECT coupon_id" +
            " FROM `coupon_project`.`customers_coupons`" +
            " WHERE customer_id=?)"*/;
}
