package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    public static final int NUMBER_OF_CONNECTIONS = 10;
    private static ConnectionPool instance = null;
    private final Stack<Connection> connections = new Stack<>();

    /**
     * Empty constructor
     * Makes new instance of the connection pool
     * Opens all connections
     * @throws SQLException
     */
    private ConnectionPool() throws SQLException {
        openAllConnections();
    }

    /**
     * Opens NUMBER_OF_CONNECTIONS connections to our database
     * NUMBER_OF_CONNECTIONS is the max amount of connections to the database that we can have
     * @throws SQLException
     */
    private void openAllConnections() throws SQLException {
        for (int counter = 0; counter < NUMBER_OF_CONNECTIONS; counter++) {
            Connection connection = DriverManager.getConnection(DBmanager.URL, DBmanager.SQL_USER, DBmanager.SQL_PASSWORD);
            connections.push(connection);
        }
    }

    /**
     * Method closes all connections to the database when we finish the work
     * @throws InterruptedException
     */
    public void closeAllConnections() throws InterruptedException{
        synchronized (connections){
            while (connections.size()<NUMBER_OF_CONNECTIONS){
                connections.wait();
            }
            connections.removeAllElements();
        }
    }

    /**
     * Create an instance of the connection Pool
     * Using double check to ensure that we will create only onr instance
     * @return the created instance
     */
    public static ConnectionPool getInstance()  {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    try {
                        instance = new ConnectionPool();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Method used to get one connection to the database
     * @return the connection for further use
     * @throws InterruptedException
     */
    public Connection getConnection() throws InterruptedException {
        synchronized (connections) {
            if (connections.isEmpty()) {
                connections.wait();
            }
            return connections.pop();
        }
    }

    /**
     * Method used to return a connection back to the connection pool
     * @param connection is the connection that is being returned
     */
    public void returnConnection(Connection connection){
        synchronized (connections) {
            connections.push(connection);
            connections.notify();
        }
    }
}
