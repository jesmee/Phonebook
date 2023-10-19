package ru.vsu.cs.ArinaDyakova.repository;

import ru.vsu.cs.ArinaDyakova.models.Base;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepo<T extends Base> implements CRUDRepo<T>{
    List<T> list = new ArrayList<>();
    int id = 0;

    @Override
    public void create(T t) {
        t.setId(id);
        list.add(t);

        id += 1;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public T getById(int id) {
        for (T t : list) {
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    @Override
    public void update(T newItem, int oldId) {

        newItem.setId(oldId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.set(i, newItem);
                return;
            }
        }
        throw new IllegalArgumentException("Object with ID " + id + " not found.");
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Object with ID " + id + " not found.");
    }
}
