package ru.vsu.cs.ArinaDyakova.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vsu.cs.ArinaDyakova.database.Database;
import ru.vsu.cs.ArinaDyakova.models.*;
import java.io.File;
import java.io.IOException;

public class Deserializer {
    protected final Database db;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Deserializer(Database db){
        this.db = db;
    }

    public void deserialize(File file){
        try {
            ListWrapper listWrapper = objectMapper.readValue(file, ListWrapper.class);
            for (Person person: listWrapper.getPersonList()) {
                db.createPerson(person);
            }
            for (Phonenumber phonenumber : listWrapper.getPhonenumberList()) {
                db.createPhonenumber(phonenumber);
            }
            for (Phonebook phonebook : listWrapper.getPhonebookList()) {
                db.createPhonebook(phonebook);
            }
            for (PersonPhonebook personPhonebook : listWrapper.getPersonPhonebookList()) {
                db.createPersonPhonebook(personPhonebook);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException("wrong file");
        }
    }
}
