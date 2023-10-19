package ru.vsu.cs.ArinaDyakova.database;

import ru.vsu.cs.ArinaDyakova.models.Person;
import ru.vsu.cs.ArinaDyakova.models.PersonPhonebook;
import ru.vsu.cs.ArinaDyakova.models.Phonebook;
import ru.vsu.cs.ArinaDyakova.models.Phonenumber;

import java.util.List;

public interface Database {
    // create
    void createPerson(Person t);
    // read
    List<Person> getAllPersons();
    Person getPersonById(int id);
    // update
    void updatePerson(Person person, int oldId);
    // delete
    void deletePerson(int id);


    // create
    void createPhonebook(Phonebook t);
    // read
    List<Phonebook> getAllPhonebooks();
    Phonebook getPhonebookById(int id);
    // update
    void updatePhonebook(Phonebook phonebook, int oldId);
    // delete
    void deletePhonebook(int id);


    // create
    void createPhonenumber(Phonenumber t);
    // read
    List<Phonenumber> getAllPhonenumbers();
    Phonenumber getPhonenumberById(int id);
    // update
    void updatePhonenumber(Phonenumber phonenumber, int oldId);
    // delete
    void deletePhonenumber(int id);



    // create
    void createPersonPhonebook(PersonPhonebook t);
    // read
    List<PersonPhonebook> getAllPersonPhonebooks();
    PersonPhonebook getPersonPhonebookById(int id);
    // update
    void updatePersonPhonebook(PersonPhonebook personPhonebook, int oldId);
    // delete
    void deletePersonPhonebook(int id);
}
