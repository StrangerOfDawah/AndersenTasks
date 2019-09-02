package jdbc.connecter;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class Connector {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/andersen?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static DataSource getConnection(){

        MysqlDataSource mySqlDS = new MysqlDataSource();
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            mySqlDS.setURL(DB_URL);
            mySqlDS.setUser(USER);
            mySqlDS.setPassword(PASS);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mySqlDS;
    }
}
