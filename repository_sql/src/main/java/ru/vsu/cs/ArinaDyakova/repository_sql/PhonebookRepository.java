package ru.vsu.cs.ArinaDyakova.repository_sql;

import cs.vsu.ru.ArinaDyakova.connectors.PostgreSQL;
import cs.vsu.ru.ArinaDyakova.connectors.SQLConnector;
import ru.vsu.cs.ArinaDyakova.models.Person;
import ru.vsu.cs.ArinaDyakova.models.Phonebook;
import ru.vsu.cs.ArinaDyakova.repository.CRUDRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhonebookRepository implements CRUDRepo<Phonebook> {
    protected final SQLConnector conn;

    protected final String tableName = "Phonebook";

    public PhonebookRepository(){
        try {
            conn = PostgreSQL.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Phonebook phonebook) {
        String query = String.format("INSERT INTO %s (", tableName);
        if(phonebook.getId() != null) {
            query += "id, ";
        }
        query += "name ) VALUES ( ";

        if(phonebook.getId() != null) {
            query += phonebook.getId();
        }

        query += String.format(", '%s');", phonebook.getName());

        try {
            int id = conn.insert(query, "id");
            phonebook.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected Phonebook getFromResultSet(ResultSet rs) throws SQLException {
        Phonebook p = new Phonebook(
                rs.getString("name"));
        p.setId(rs.getInt("id"));
        return p;
    }

    @Override
    public List<Phonebook> getAll() {
        List<Phonebook> result = new ArrayList<>();

        String query = String.format("SELECT id, name FROM %s",
                tableName);
        try {
            ResultSet rs = conn.select(query);
            while (rs.next()) {
                result.add(getFromResultSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Phonebook getById(int id) {
        String query = String.format("SELECT id, name FROM %s WHERE id = %d",
                tableName, id);
        try {
            ResultSet rs = conn.select(query);
            rs.next();
            Phonebook p = getFromResultSet(rs);
            rs.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Phonebook phonebook, int oldId) {
        String query = String.format("UPDATE %s SET name = '%s' WHERE id = %d",
                tableName, phonebook.getName(), phonebook.getId());

        try {
            if (conn.update(query) != 1) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM %s WHERE id = %d",
                tableName, id);
        try {
            if (conn.delete(query) != 1) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
