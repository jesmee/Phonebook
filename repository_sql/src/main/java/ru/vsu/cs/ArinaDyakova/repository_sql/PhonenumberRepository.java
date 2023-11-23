package ru.vsu.cs.ArinaDyakova.repository_sql;

import cs.vsu.ru.ArinaDyakova.connectors.PostgreSQL;
import cs.vsu.ru.ArinaDyakova.connectors.SQLConnector;
import ru.vsu.cs.ArinaDyakova.models.Phonenumber;
import ru.vsu.cs.ArinaDyakova.repository.CRUDRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhonenumberRepository implements CRUDRepo<Phonenumber> {
    protected final SQLConnector conn;

    protected final String tableName = "Phonenumber";

    public PhonenumberRepository(){
        try {
            conn = PostgreSQL.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Phonenumber phonenumber) {
        String query = String.format("INSERT INTO %s (", tableName);
        if(phonenumber.getId() != null) {
            query += "id, ";
        }
        query += "phone_type, phone_number, person_id ) VALUES ( ";

        if(phonenumber.getId() != null) {
            query += phonenumber.getId();
        }

        query += String.format(", '%s', '%s', %d );", phonenumber.getPhoneType(), phonenumber.getPhoneNumber(), phonenumber.getPersonId());

        try {
            int id = conn.insert(query, "id");
            phonenumber.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected Phonenumber getFromResultSet(ResultSet rs) throws SQLException {
        Phonenumber p = new Phonenumber(
                rs.getString("phone_type"),
                rs.getString("phone_number"),
                rs.getInt("person_id"));
        p.setId(rs.getInt("id"));
        return p;
    }

    @Override
    public List<Phonenumber> getAll() {
        List<Phonenumber> result = new ArrayList<>();

        String query = String.format("SELECT id, phone_type, phone_number, person_id FROM %s",
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
    public Phonenumber getById(int id) {
        String query = String.format("SELECT id, phone_type, phone_number, person_id FROM %s WHERE id = %d",
                tableName, id);
        try {
            ResultSet rs = conn.select(query);
            rs.next();
            Phonenumber p = getFromResultSet(rs);
            rs.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Phonenumber phonenumber, int oldId) {
        String query = String.format("UPDATE %s SET phone_type = '%s', phone_number = '%s', person_id = %d, WHERE id = %d",
                tableName, phonenumber.getPhoneType(), phonenumber.getPhoneNumber(), phonenumber.getPersonId(), phonenumber.getId());

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
