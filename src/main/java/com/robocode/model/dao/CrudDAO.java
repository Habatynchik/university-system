package com.robocode.model.dao;

import java.util.Optional;

public interface CrudDAO<E> {
    boolean create(E entity);

    Optional<E> getById(long id);

    Optional<E> update(E entity);

    boolean deleteById(long id);
}
