package periodic.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikaricpConnection {
    //Logger
    private static HikaricpConnection instance;
    private static Connection connection;
    private static final String FILE_PROPERTIES = "hikariMySQL.properties";

    HikariConfig config = new HikariConfig(FILE_PROPERTIES);
    HikariDataSource dataSource = new HikariDataSource(config);

    public static synchronized HikaricpConnection getInstance(){
        if (instance == null) {
            instance = new HikaricpConnection();
        }
        return instance;
    }

    public Connection getHikaricpConnection(){
        try {
            connection = dataSource.getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
