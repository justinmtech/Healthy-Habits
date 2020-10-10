package com.justin.healthyhabits.repositories;

import com.justin.healthyhabits.user.SiteUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<SiteUser, Integer> {
}
