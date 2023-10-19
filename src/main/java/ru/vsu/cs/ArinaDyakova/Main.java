package ru.vsu.cs.ArinaDyakova;

import ru.vsu.cs.ArinaDyakova.database.Database;
import ru.vsu.cs.ArinaDyakova.database.MemoryDatabase;
import ru.vsu.cs.ArinaDyakova.json.*;
import ru.vsu.cs.ArinaDyakova.models.*;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static ru.vsu.cs.ArinaDyakova.Logic.*;

public class Main {
    public static void soutAll(Database database){
        // Выводим всех людей
        List<Person> allPersons = database.getAllPersons();
        System.out.println("Все люди:");
        for (Person person : allPersons) {
            System.out.println("id: " + person.getId() + " имя: " + person.getFirstName() + " " + person.getLastName());
        }

        // Выводим все записные книги
        List<Phonebook> allPhonebooks = database.getAllPhonebooks();
        System.out.println("\nВсе записные книги:");
        for (Phonebook phonebook : allPhonebooks) {
            System.out.println("Название: " + phonebook.getName());

            // Находим всех людей, которые находятся в данной записной книге
            List<Person> personsInPhonebook = getAllPersonsInPhonebook(database, phonebook);

            if (!personsInPhonebook.isEmpty()) {
                System.out.println("Люди в этой записной книге:");
                for (Person person : personsInPhonebook) {
                    System.out.println("id: " + person.getId() + " имя: " + person.getFirstName() + " " + person.getLastName());

                    // Находим все номера для данного человека
                    List<Phonenumber> personNumbers = getAllPersonNumbers(database, person);
                    if (!personNumbers.isEmpty()) {
                        System.out.print("Номера для этого человека(вывод первых 3):");
                        System.out.println(personNumbers.size());
                        for (int i = 0; i < 5; i++) {
                            Phonenumber number = personNumbers.get(i);
                            System.out.print("(id: " + number.getId() + ", person_id: " + number.getPersonId() +
                                    ", тип: " + number.getPhoneType());
                            System.out.print("Номер: " + number.getPhoneNumber() + "), ");
                        }
                        System.out.println();
                        System.out.println();
                    }
                }
            } else {
                System.out.println("В этой записной книге нет людей.");
            }

            System.out.println();
        }

        System.out.println("Hello world!");
    }


    public static void main(String[] args) {
        Database database = MemoryDatabase.getInstance();

        System.out.println("do you want to generate and write or read file? w/r");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        File file = new File("test.json");

        if (Objects.equals(s, "w")) {
            Generator generator = new Generator(database);
            generator.generate();
            soutAll(database);
            Serializer serializer = new Serializer(database);
            file = new File("test.json");
            serializer.serialize(file);

            System.out.println("wrote");
        }
        else {

            Deserializer deserializer = new Deserializer(database);
            deserializer.deserialize(file);

            System.out.println("read");
            soutAll(database);
        }

    }
}