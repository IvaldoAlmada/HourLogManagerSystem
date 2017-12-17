package com.greenmile.presentation.query.service.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
    @Override
    Page<T> findAll(Pageable pageable);

    @Override
    T findOne(ID id);

    @Override
    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    default T findById(ID id) {
        return findOne(id);
    }

}
