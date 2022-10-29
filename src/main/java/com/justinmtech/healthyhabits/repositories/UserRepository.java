package com.justinmtech.healthyhabits.repositories;

import com.justinmtech.healthyhabits.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
