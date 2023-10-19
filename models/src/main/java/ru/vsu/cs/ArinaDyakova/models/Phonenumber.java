package ru.vsu.cs.ArinaDyakova.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Phonenumber extends Base {
    protected String phoneType;

    protected String phoneNumber;

    protected int personId;
}
