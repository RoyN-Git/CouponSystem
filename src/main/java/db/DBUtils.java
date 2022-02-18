package db;

import java.sql.*;
import java.util.Map;

public class DBUtils {
    /**
     * Method is being used to run a query on sql server
     * Mainly used to run queries that are not returning views and don't use params
     * @param sql is the query that we want to run
     * @return true-> if the query ran successfully
     *         false-> if the query failed
     */
    public static boolean runQuery(String sql) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            return true;
        } catch (SQLException | InterruptedException err) {
            System.out.println(err.getMessage());
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * Method is being used to run a query on sql server
     * Mainly used to run queries that are not returning views and use params
     * @param sql is the query that we want to run
     * @param params is a map of params to insert in the query
     * @return true-> if the query ran successfully
     *         false-> if the query failed
     */
    public static boolean runQuery(String sql, Map<Integer, Object> params) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return false;
    }

    /**
     * Method is being used to run a query on sql server
     * Mainly used to run queries that are returning views and use params
     * @param sql is the query that we want to run
     * @param params is a map of params to insert in the query
     * @return view converted into ResultSet for further use
     */
    public static ResultSet runQueryForResult(String sql, Map<Integer,Object> params){
        Connection connection=null;
        try {
            connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            params.forEach((key,value)->{
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            return preparedStatement.executeQuery();
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}
