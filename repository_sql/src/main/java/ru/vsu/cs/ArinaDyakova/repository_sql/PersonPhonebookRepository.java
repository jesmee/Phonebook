package ru.vsu.cs.ArinaDyakova.repository_sql;

import cs.vsu.ru.ArinaDyakova.connectors.PostgreSQL;
import cs.vsu.ru.ArinaDyakova.connectors.SQLConnector;
import ru.vsu.cs.ArinaDyakova.models.PersonPhonebook;
import ru.vsu.cs.ArinaDyakova.repository.CRUDRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonPhonebookRepository implements CRUDRepo<PersonPhonebook> {
    protected final SQLConnector conn;

    protected final String tableName = "PersonPhonebook";

    public PersonPhonebookRepository(){
        try {
            conn = PostgreSQL.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(PersonPhonebook personPhonebook) {
        String query = String.format("INSERT INTO %s (", tableName);
        if(personPhonebook.getId() != null) {
            query += "id, ";
        }
        query += "person_id, phonebook_id ) VALUES ( ";

        if(personPhonebook.getId() != null) {
            query += personPhonebook.getId();
        }

        query += String.format(", %d, %d );", personPhonebook.getPersonId(), personPhonebook.getPhonebookId());

        try {
            int id = conn.insert(query, "id");
            personPhonebook.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected PersonPhonebook getFromResultSet(ResultSet rs) throws SQLException {
        PersonPhonebook p = new PersonPhonebook(
                rs.getInt("person_id"),
                rs.getInt("phonebook_id"));
        p.setId(rs.getInt("id"));
        return p;
    }

    @Override
    public List<PersonPhonebook> getAll() {
        List<PersonPhonebook> result = new ArrayList<>();

        String query = String.format("SELECT id, person_id, phonebook_id FROM %s",
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
    public PersonPhonebook getById(int id) {
        String query = String.format("SELECT id, person_id, phonebook_id FROM %s WHERE id = %d",
                tableName, id);
        try {
            ResultSet rs = conn.select(query);
            rs.next();
            PersonPhonebook p = getFromResultSet(rs);
            rs.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(PersonPhonebook person, int oldId) {
        String query = String.format("UPDATE %s SET person_id = %d, phonebook_id = %d WHERE id = %d",
                tableName, person.getPersonId(), person.getPhonebookId(), person.getId());

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
