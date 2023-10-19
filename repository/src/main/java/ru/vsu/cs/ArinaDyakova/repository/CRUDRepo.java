package ru.vsu.cs.ArinaDyakova.repository;

import ru.vsu.cs.ArinaDyakova.models.Base;

import java.util.List;

public interface CRUDRepo<T extends Base> {
    // create
    void create(T t);
    // read
    List<T> getAll();
    T getById(int id);
    // update
    void update(T t, int oldId);
    // delete
    void delete(int id);
}
