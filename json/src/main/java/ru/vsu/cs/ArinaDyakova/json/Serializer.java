package ru.vsu.cs.ArinaDyakova.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ru.vsu.cs.ArinaDyakova.database.Database;
import ru.vsu.cs.ArinaDyakova.models.*;

import java.io.File;
import java.io.IOException;

public class Serializer {
    protected final Database db;
    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    public Serializer(Database db){
        this.db = db;
    }

    public void serialize(File file){
        ListWrapper listWrapper = new ListWrapper(
                db.getAllPersons(),
                db.getAllPhonenumbers(),
                db.getAllPhonebooks(),
                db.getAllPersonPhonebooks()
        );
        try {
            objectWriter.writeValue(file, listWrapper);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException("wrong file");
        }
    }
}
