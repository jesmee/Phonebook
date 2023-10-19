package ru.vsu.cs.ArinaDyakova;

import ru.vsu.cs.ArinaDyakova.database.Database;
import ru.vsu.cs.ArinaDyakova.models.Person;
import ru.vsu.cs.ArinaDyakova.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Logic {

    public static List<Person> getAllPersonsInPhonebook(Database database, Phonebook phonebook){
        List<Person> personsInPhonebook = new ArrayList<>();

        List<PersonPhonebook> personPhonebooks = database.getAllPersonPhonebooks();

        for (PersonPhonebook pp : personPhonebooks) {
            if (database.getPhonebookById(pp.getPhonebookId()).equals(phonebook)) {
                Person person = database.getPersonById(pp.getPersonId());
                personsInPhonebook.add(person);
            }
        }

        return personsInPhonebook;
    }

    public static List<Phonenumber> getAllPersonNumbers(Database database, Person person){
        List<Phonenumber> personNumbers = new ArrayList<>();
        int personId = person.getId();

        List<Phonenumber> phonenumbers = database.getAllPhonenumbers();

        for (Phonenumber number : phonenumbers) {
            if (number.getPersonId() == personId) {
                personNumbers.add(number);
            }
        }

        return personNumbers;
    }
}
