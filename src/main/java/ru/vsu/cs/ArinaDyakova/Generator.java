package ru.vsu.cs.ArinaDyakova;

import ru.vsu.cs.ArinaDyakova.database.Database;
import ru.vsu.cs.ArinaDyakova.models.*;

import java.util.List;
import java.util.Random;

public class Generator {
    protected final Database database;
    protected final Random random;
    public Generator(Database database){
        this.database = database;
        this.random = new Random();
    }

    protected int pickRandomId(List<? extends Base> list) {
        return list.get(random.nextInt(list.size())).getId();
    }

    public void generate(){
        // Generate Persons
        String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Olivia", "James", "Sophia", "William", "Emma"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Taylor", "Anderson", "Wilson", "Miller", "Davis", "Garcia", "Martinez"};

        for (String firstName : firstNames) {
            for (String lastName : lastNames) {
                database.createPerson(new Person(firstName, lastName));
            }
        }

        // Generate Phonebooks
        for (int i = 0; i < 10; i++) {
            String name = "Phonebook " + i;
            database.createPhonebook(new Phonebook(name));
        }

        List<Person> persons = database.getAllPersons();
        List<Phonebook> phonebooks = database.getAllPhonebooks();

        // Generate PersonPhonebooks
        for (Person person : persons) {
            int personId = person.getId();
            int phonebookId = pickRandomId(phonebooks);
            database.createPersonPhonebook(new PersonPhonebook(personId, phonebookId));
        }

        // Generate Phonenumbers
        String[] phoneTypes = {"Mobile", "Home", "Work"};

        for (Person person : persons) {
            int personId = person.getId();
            for (int i = 0; i < 10000; i++) {
                String phoneNumber = "+1" + random.nextInt(100000000, 999999999);
                String phoneType = phoneTypes[random.nextInt(phoneTypes.length)];
                database.createPhonenumber(new Phonenumber(phoneType, phoneNumber, personId));
            }
        }
    }
}
