package cs.vsu.ru.ArinaDyakova.connectors;

import java.sql.*;

public class PostgreSQL implements SQLConnector{
    protected final Connection connection;


    private PostgreSQL() throws SQLException {
        String db = "phonebook";
        String user = "admin";
        String pass = "admin";
        String url = "jdbc:postgresql://localhost:5432/" + db + "?user=" + user + "&password=" + pass;

        connection = DriverManager.getConnection(url);
    }

    private static PostgreSQL INSTANCE;

    public static PostgreSQL getInstance() throws SQLException {
        if(INSTANCE == null){
            INSTANCE = new PostgreSQL();
        }
        return INSTANCE;
    }

    @Override
    public ResultSet select(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public int insert(String query, String colRet) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();

        if(!resultSet.next()) {
            throw new RuntimeException();
        }
        int id = resultSet.getInt(colRet);
        if(resultSet.next()){
            throw new RuntimeException();
        }
        return id;
    }

    @Override
    public int update(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        return statement.getUpdateCount();
    }

    @Override
    public int delete(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        return statement.getUpdateCount();
    }
}
