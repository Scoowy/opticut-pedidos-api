package com.opticortes.dao;

import java.util.List;


public interface ICRUD<T> {
    List<T> selectAll();

    int deleteAll();

    T select(T entity);

    int insert(T entity);

    int update(T entity);

    int delete(T entity);
}
