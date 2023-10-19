package ru.vsu.cs.ArinaDyakova.database;

import ru.vsu.cs.ArinaDyakova.models.Person;
import ru.vsu.cs.ArinaDyakova.models.PersonPhonebook;
import ru.vsu.cs.ArinaDyakova.models.Phonebook;
import ru.vsu.cs.ArinaDyakova.models.Phonenumber;
import ru.vsu.cs.ArinaDyakova.repository.*;

import java.util.ArrayList;
import java.util.List;

public class MemoryDatabase implements Database{
    protected CRUDRepo<Person> personRepo;
    protected CRUDRepo<PersonPhonebook> personPhoneRepo;
    protected CRUDRepo<Phonebook> phonebookRepo;
    protected CRUDRepo<Phonenumber> phonenumberRepo;

    private static MemoryDatabase instance;

    private MemoryDatabase(){
        personRepo = new MemoryRepo<>();
        personPhoneRepo = new MemoryRepo<>();
        phonebookRepo = new MemoryRepo<>();
        phonenumberRepo = new MemoryRepo<>();
    }

    public static MemoryDatabase getInstance(){
        if (instance == null){
            instance = new MemoryDatabase();
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
        if(getPersonById(id) == null) {
            throw new IllegalArgumentException("cant find person with given id");
        }
        // CASCADE DELETE
        for (Phonenumber phonenumber : new ArrayList<>(getAllPhonenumbers())) {
            if(phonenumber.getPersonId() == id) {
                deletePhonenumber(phonenumber.getId());
            }
        }
        for (PersonPhonebook personPhonebook : new ArrayList<>(getAllPersonPhonebooks())) {
            if(personPhonebook.getPersonId() == id) {
                deletePhonebook(personPhonebook.getId());
            }
        }

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
        if(getPhonebookById(id) == null) {
            throw new IllegalArgumentException("cant find phonebook with given id");
        }
        // CASCADE DELETE
        for (PersonPhonebook personPhonebook : new ArrayList<>(getAllPersonPhonebooks())) {
            if(personPhonebook.getPhonebookId() == id) {
                deletePhonebook(personPhonebook.getId());
            }
        }

        phonebookRepo.delete(id);
    }


    protected void checkPhonenumber(Phonenumber t){
        if(getPersonById(t.getPersonId()) == null){
            throw new IllegalArgumentException("given phonenumber linked to person with illegal id");
        }
    }
    @Override
    public void createPhonenumber(Phonenumber t) {
        checkPhonenumber(t);
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
        checkPhonenumber(phonenumber);
        phonenumberRepo.update(phonenumber, oldId);
    }

    @Override
    public void deletePhonenumber(int id) {
        phonenumberRepo.delete(id);
    }


    protected void checkPersonPhonebook(PersonPhonebook t){
        if(getPersonById(t.getPersonId()) == null){
            throw new IllegalArgumentException("given PersonPhonebook linked to person with illegal id");
        }
        if(getPhonebookById(t.getPhonebookId()) == null){
            throw new IllegalArgumentException("given PersonPhonebook linked to phonebook with illegal id");
        }
    }
    @Override
    public void createPersonPhonebook(PersonPhonebook t) {
        checkPersonPhonebook(t);
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
        checkPersonPhonebook(personPhonebook);
        personPhoneRepo.update(personPhonebook, oldId);
    }

    @Override
    public void deletePersonPhonebook(int id) {
        personPhoneRepo.delete(id);
    }
}
