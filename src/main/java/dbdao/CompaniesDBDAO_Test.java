package dbdao;
public class CompaniesDBDAO_Test {

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Company;
import dao.CompaniesDAO;
import db.ConnectionPool;

public class CompaniesDBDAO_Test implements CompaniesDAO {

    @Override
    public boolean isCompanyExists(String email, String password) {
        Map<Integer, Object>
        }
    @Override
    public int getCompanyIdByCredentials(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            //get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            String sqlStatement = "select id from companies where email = ? and password = ?";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //replace the question marks in the statement above with the relevant data
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getInt("id");
            }

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_GET_ERROR, "error running method getCompanyIdByCredentials in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement, result);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }
        return 0;
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            //company id is defined as primary key and auto increment
            String sqlStatement = "insert into companies (name, email, password) " + "values (?,?,?)";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //replace the question marks in the statement above with the relevant data
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getEmail());
            preparedStatement.setString(3, company.getPassword());

            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_SET_ERROR, "error running method addCompany in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }
    }

    @Override
    public void updateCompany(Company company) throws CouponSystemException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            String sqlStatement = "update companies set email=?, password=? where id=?";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //replace the question marks in the statement above with the relevant data
            preparedStatement.setString(1, company.getEmail());
            preparedStatement.setString(2, company.getPassword());
            preparedStatement.setInt(3, company.getId());

            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_SET_ERROR, "error running method updateCompany in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }
    }

    @Override
    public void deleteCompany(int companyID) throws CouponSystemException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            String sqlStatement = "delete from companies where id=?";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //replace the question marks in the statement above with the relevant data
            preparedStatement.setInt(1, companyID);
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_SET_ERROR, "error running method deleteCompany in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }

    }

    @Override
    public ArrayList<Company> getAllCompanies() throws CouponSystemException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        ArrayList<Company> companiesList = new ArrayList<Company>();

        try {
            //get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            String sqlStatement = "select * from companies";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            result = preparedStatement.executeQuery();

            if(!result.next()) {
                return companiesList;
            }

            do {
                companiesList.add(extractCompanyFromResult(result));
            } while(result.next());

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_GET_ERROR, "error running method getAllCompanies in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement, result);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }
        return companiesList;
    }

    @Override
    public Company getOneCompany(int companyID) throws CouponSystemException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Company company = null;

        try {
            //get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            String sqlStatement = "select * from companies where id = ?";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //replace the question marks in the statement above with the relevant data
            preparedStatement.setInt(1, companyID);

            result = preparedStatement.executeQuery();

            if (result.next()) {
                return extractCompanyFromResult(result);
            }

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_GET_ERROR, "error running method getOneCompany in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement, result);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }
        return company;
    }

    private Company extractCompanyFromResult(ResultSet result) throws CouponSystemException{
        Company company = null;
        try {
            //extracting the data from db into a Company object
            company = new Company();
            company.setId(result.getInt("id"));
            company.setName(result.getString("name"));
            company.setEmail(result.getString("email"));
            company.setPassword(result.getString("password"));
            company.setCoupons(couponsDBDAO.getAllCouponsByCompanyID(result.getInt("id")));
        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_SET_ERROR, "error running method extractCompanyFromResult in CompaniesDBDAO");
        }
        return company;
    }

    public boolean isCompanyNameExists(String name) throws CouponSystemException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            //get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            String sqlStatement = "select * from companies where name = ?";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //replace the question marks in the statement above with the relevant data
            preparedStatement.setString(1, name);

            result = preparedStatement.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_GET_ERROR, "error running method isCompanyNameExists in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement, result);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }
        return false;
    }

    public boolean isCompanyEmailExists(String email) throws CouponSystemException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            //get connection from the pool
            connection = ConnectionPool.getInstance().getConnection();

            String sqlStatement = "select * from companies where email = ?";

            //combine between the syntax and the connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //replace the question marks in the statement above with the relevant data
            preparedStatement.setString(1, email);

            result = preparedStatement.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (Exception exception) {
            throw new CouponSystemException(exception, ErrorType.DB_GET_ERROR, "error running method isCompanyEmailExists in CompaniesDBDAO");
        } finally {
            if (connection != null) {
                //closing all resources
                ConnectionPool.closeResources(preparedStatement, result);
                ConnectionPool.getInstance().restoreConnection(connection);
            }
        }
        return false;
    }
}
