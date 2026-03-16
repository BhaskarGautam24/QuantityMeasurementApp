package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.LinkedList;

public class ConnectionPool {

    private static LinkedList<Connection> pool = new LinkedList<>();

    static {

        try {

            Class.forName("org.h2.Driver");

            String url = "jdbc:h2:mem:quantitydb";
            String user = "sa";
            String password = "";

            for (int i = 0; i < 5; i++) {

                Connection connection =
                        DriverManager.getConnection(url, user, password);

                pool.add(connection);
            }

            // Create table automatically
            Connection connection = pool.getFirst();

            Statement statement = connection.createStatement();

            statement.execute(
                    "CREATE TABLE IF NOT EXISTS quantity_measurement_entity (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "operation VARCHAR(50)," +
                    "this_quantity VARCHAR(100)," +
                    "that_quantity VARCHAR(100)," +
                    "result VARCHAR(50))"
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Connection getConnection() {
        return pool.removeFirst();
    }

    public static synchronized void release(Connection connection) {
        pool.addLast(connection);
    }
}