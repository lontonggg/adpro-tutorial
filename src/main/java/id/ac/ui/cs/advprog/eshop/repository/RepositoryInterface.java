package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface Repository<T> {
    T create(T inventory);
    void delete(String id);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id, T updated);
}
