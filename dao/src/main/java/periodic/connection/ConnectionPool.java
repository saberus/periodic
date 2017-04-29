package periodic.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    //TODO : Make comments and loging

    private static ConnectionPool instance;
    private BlockingQueue<Connection> connectionPool;

    private String db_url;
    private String db_user;
    private String db_password;
    private String db_driver;
    private int db_maxCon;

    private ConnectionPool(){
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        db_url = bundle.getString("db_url");
        db_user = bundle.getString("db_user");
        db_password = bundle.getString("db_password");
        db_driver = bundle.getString("db_driver");
        db_maxCon = Integer.parseInt(bundle.getString("db_maxCon"));
        connectionPool = new ArrayBlockingQueue<Connection>(db_maxCon);
    }


    public static synchronized ConnectionPool getInstance(){
        if (instance ==null){
            instance =  new ConnectionPool();
            System.out.printf("new Instance");
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection connection = null;
        if (connectionPool.isEmpty()){
            createConnection();
        }try {
            connectionPool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("new Connection");
        return connection;
    }

    public synchronized void freeConnection(Connection connection){
        connectionPool.add(connection);
    }

    private void createConnection() throws SQLException{
        if (connectionPool.size() < db_maxCon){
            try {
                Class.forName(db_driver);
                Connection connection = DriverManager.getConnection(db_url,db_user,db_password);
                connectionPool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
