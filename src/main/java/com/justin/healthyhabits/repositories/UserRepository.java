package com.justin.healthyhabits.repositories;

import com.justin.healthyhabits.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
