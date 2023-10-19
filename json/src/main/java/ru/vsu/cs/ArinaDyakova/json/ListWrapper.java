package ru.vsu.cs.ArinaDyakova.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import ru.vsu.cs.ArinaDyakova.models.*;

import java.util.List;

@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListWrapper {
    private List<Person> personList;
    private List<Phonenumber> phonenumberList;
    private List<Phonebook> phonebookList;
    private List<PersonPhonebook> personPhonebookList;
}