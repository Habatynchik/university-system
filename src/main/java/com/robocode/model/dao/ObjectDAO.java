package com.robocode.model.dao;

public interface ObjectDAO<E> {
    boolean create(E entity);

    E getById(long id);

    E update(E entity);

    boolean deleteById(long id);
}
