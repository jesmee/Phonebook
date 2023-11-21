package cs.vsu.ru.ArinaDyakova.database;

import ru.vsu.cs.ArinaDyakova.database.Database;
import ru.vsu.cs.ArinaDyakova.models.Person;
import ru.vsu.cs.ArinaDyakova.models.PersonPhonebook;
import ru.vsu.cs.ArinaDyakova.models.Phonebook;
import ru.vsu.cs.ArinaDyakova.models.Phonenumber;
import ru.vsu.cs.ArinaDyakova.repository.*;
import ru.vsu.cs.ArinaDyakova.repository_sql.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSQL implements Database {
    protected CRUDRepo<Person> personRepo;
    protected CRUDRepo<PersonPhonebook> personPhoneRepo;
    protected CRUDRepo<Phonebook> phonebookRepo;
    protected CRUDRepo<Phonenumber> phonenumberRepo;

    private static DatabaseSQL instance;

    private DatabaseSQL(){
        personRepo = new PersonRepository();
        personPhoneRepo = new PersonPhonebookRepository();
        phonebookRepo = new PhonebookRepository();
        phonenumberRepo = new PhonenumberRepository();
    }

    public static DatabaseSQL getInstance(){
        if (instance == null){
            instance = new DatabaseSQL();
        }
        return instance;
    }

    @Override
    public void createPerson(Person t) {
        personRepo.create(t);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepo.getAll();
    }

    @Override
    public Person getPersonById(int id) {
        return personRepo.getById(id);
    }

    @Override
    public void updatePerson(Person person, int oldId) {
        personRepo.update(person, oldId);
    }

    @Override
    public void deletePerson(int id) {
        personRepo.delete(id);
    }


    @Override
    public void createPhonebook(Phonebook t) {
        phonebookRepo.create(t);
    }

    @Override
    public List<Phonebook> getAllPhonebooks() {
        return phonebookRepo.getAll();
    }

    @Override
    public Phonebook getPhonebookById(int id) {
        return phonebookRepo.getById(id);
    }

    @Override
    public void updatePhonebook(Phonebook phonebook, int oldId) {
        phonebookRepo.update(phonebook, oldId);
    }

    @Override
    public void deletePhonebook(int id) {
        phonebookRepo.delete(id);
    }

    @Override
    public void createPhonenumber(Phonenumber t) {
        phonenumberRepo.create(t);
    }

    @Override
    public List<Phonenumber> getAllPhonenumbers() {
        return phonenumberRepo.getAll();
    }

    @Override
    public Phonenumber getPhonenumberById(int id) {
        return phonenumberRepo.getById(id);
    }

    @Override
    public void updatePhonenumber(Phonenumber phonenumber, int oldId) {
        phonenumberRepo.update(phonenumber, oldId);
    }

    @Override
    public void deletePhonenumber(int id) {
        phonenumberRepo.delete(id);
    }

    @Override
    public void createPersonPhonebook(PersonPhonebook t) {
        personPhoneRepo.create(t);
    }

    @Override
    public List<PersonPhonebook> getAllPersonPhonebooks() {
        return personPhoneRepo.getAll();
    }

    @Override
    public PersonPhonebook getPersonPhonebookById(int id) {
        return personPhoneRepo.getById(id);
    }

    @Override
    public void updatePersonPhonebook(PersonPhonebook personPhonebook, int oldId) {
        personPhoneRepo.update(personPhonebook, oldId);
    }

    @Override
    public void deletePersonPhonebook(int id) {
        personPhoneRepo.delete(id);
    }
}
