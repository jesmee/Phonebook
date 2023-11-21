package ru.vsu.cs.ArinaDyakova;

import cs.vsu.ru.ArinaDyakova.database.DatabaseSQL;
import ru.vsu.cs.ArinaDyakova.database.Database;
import ru.vsu.cs.ArinaDyakova.database.MemoryDatabase;
import ru.vsu.cs.ArinaDyakova.json.Deserializer;

import java.io.File;
import java.util.Scanner;

import static ru.vsu.cs.ArinaDyakova.Main.soutAll;

public class JSONToDB {
    public static void main(String[] args) {
        Database database = DatabaseSQL.getInstance();

        Scanner scanner = new Scanner(System.in);
        File file = new File("test.json");

        Deserializer deserializer = new Deserializer(database);
        deserializer.deserialize(file);

        System.out.println("read");
        soutAll(database);
    }
}
