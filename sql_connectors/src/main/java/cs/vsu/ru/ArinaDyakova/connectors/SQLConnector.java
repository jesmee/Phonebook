package cs.vsu.ru.ArinaDyakova.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLConnector {
    ResultSet select(String query) throws SQLException;

    int insert(String query, String colToRet) throws SQLException;

    int update(String query) throws SQLException;

    int delete(String query) throws SQLException;
}
