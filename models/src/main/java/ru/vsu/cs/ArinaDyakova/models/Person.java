package ru.vsu.cs.ArinaDyakova.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends Base{
    protected String firstName;

    protected String lastName;
}
