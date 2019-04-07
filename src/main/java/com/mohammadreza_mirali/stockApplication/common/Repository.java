package com.mohammadreza_mirali.stockApplication.common;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * This is an interface for our Repositories
 * @param <T>
 */
public interface  Repository <T extends Serializable> {
    T save(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
}
