package dao;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * General interface for getting connection to your Data Base
 *
 * @author R.Kotyk
 *
 * */
public interface InterfaceDAO {
    /**
     * for getting connection
     * @return connection to your DB
     * @throws SQLException
     * */
    Connection getConnection() throws SQLException;
}
