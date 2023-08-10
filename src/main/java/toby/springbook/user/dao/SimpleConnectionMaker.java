package toby.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker{

    public Connection makeNewConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/byun/h2/dbfile/h2_214/spring-book", "sa", ""
        );
    }
}
