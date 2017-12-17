package com.greenmile.presentation.repository;

import com.greenmile.presentation.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
