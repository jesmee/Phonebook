package ru.vsu.cs.ArinaDyakova.repository_sql;

import cs.vsu.ru.ArinaDyakova.connectors.PostgreSQL;
import cs.vsu.ru.ArinaDyakova.connectors.SQLConnector;
import ru.vsu.cs.ArinaDyakova.models.Base;
import ru.vsu.cs.ArinaDyakova.models.Person;
import ru.vsu.cs.ArinaDyakova.repository.CRUDRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements CRUDRepo<Person> {
    protected final SQLConnector conn;

    protected final String tableName = "Person";

    public PersonRepository(){
        try {
            conn = PostgreSQL.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Person person) {
        String query = String.format("INSERT INTO %s (", tableName);
        if(person.getId() != null) {
            query += "id, ";
        }
        query += "first_name, last_name ) VALUES ( ";

        if(person.getId() != null) {
            query += person.getId();
        }

        query += String.format(", '%s', '%s' );", person.getFirstName(), person.getLastName());

        try {
            int id = conn.insert(query, "id");
            person.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected Person getFromResultSet(ResultSet rs) throws SQLException {
        Person p = new Person(
                rs.getString("first_name"),
                rs.getString("last_name"));
        p.setId(rs.getInt("id"));
        return p;
    }

    @Override
    public List<Person> getAll() {
        List<Person> result = new ArrayList<>();

        String query = String.format("SELECT id, first_name, last_name FROM %s",
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
    public Person getById(int id) {
        String query = String.format("SELECT id, first_name, last_name FROM %s WHERE id = %d",
                tableName, id);
        try {
            ResultSet rs = conn.select(query);
            rs.next();
            Person p = getFromResultSet(rs);
            rs.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Person person, int oldId) {
        String query = String.format("UPDATE %s SET first_name = '%s', last_name = '%s' WHERE id = %d",
                tableName, person.getFirstName(), person.getLastName(), person.getId());

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
